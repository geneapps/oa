package com.ydt.oa.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.giro.common.exception.GiroException;
import com.giro.common.util.AmountUtils;
import com.giro.common.util.DateUtils;
import com.giro.common.util.StringUtils;

import com.ydt.oa.dao.DepartmentDao;
import com.ydt.oa.dao.PurchaseApplyDao;
import com.ydt.oa.dao.PurchaseApplyDetailsDao;
import com.ydt.oa.entity.Application;

import com.ydt.oa.entity.ApproveFlow;
import com.ydt.oa.entity.Contract;
import com.ydt.oa.entity.ContractDetails;
import com.ydt.oa.entity.Department;
import com.ydt.oa.entity.FileLog;
import com.ydt.oa.entity.OaAction;
import com.ydt.oa.entity.Project;
import com.ydt.oa.entity.PurchaseApply;
import com.ydt.oa.entity.PurchaseApplyDetails;
import com.ydt.oa.entity.PurchasePlan;
import com.ydt.oa.entity.User;
import com.ydt.oa.interfaces.PurchaseApplyAppInterface;


/**
 * 系统设置业务逻辑层
 * @author caochun
 *
 */

@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class PurchaseApplyAppService implements PurchaseApplyAppInterface{
	
	@Autowired
	private ApproveService approveService;
	@Autowired
	private PurchaseApplyDao purchaseApplyDao;
	@Autowired
	private PurchaseApplyDetailsDao purchaseApplyDetailsDao;
	@Autowired
	private PurchaseApplyService purchaseApplyService;
	@Autowired
	private UserService userService;
	@Autowired
	private DepartmentDao depDao;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private PaymentOrderService orderService;
	
	private PurchaseApply purchaseApply;
	

	
	public PurchaseApply getPurchaseApply() {
		return purchaseApply;
	}


	public void setPurchaseApply(PurchaseApply purchaseApply) {
		this.purchaseApply = purchaseApply;
	}


	/**
	 * 更新采购申请单
	 * @param application
	 * @param purchaseApply
	 * @throws GiroException 
	 */
	@Override
	public void updatePurchaseApplyApp(Application application, PurchaseApply purchaseApply, User user) throws GiroException {
		
		user = userService.findByUserId(user.getId());
		application.setUser(user);
		application.setDepartment(user.getDepartment());
		 //Project project=projectService.findByName(purchaseApply.getProject().getName());
		application.setApplyType(Application.APP_TYPE_PURCHASE);
		//Project project=projectService.findById(user.getUserProject().DEP_TYPE_PROJECT);
		//Project project = null;
	    Project project=projectService.findByDepart(user.getUserProject().getId());
		purchaseApply.setProject(project);
		purchaseApply.setTitle(application.getTitle());
		purchaseApply.setUser(user);
		if(StringUtils.isNull(application.getId())){		
			// 保存采购申请信息
			//TODO
			purchaseApply.setStatus(PurchaseApply.PURCHASE_STATUS_WAITINGAPPROVE);
			purchaseApply.setApplyTime(DateUtils.getNowDateStr());
			purchaseApplyDao.saveOrUpdate(purchaseApply);
			System.out.println("------------------------------");
			//计算采购金额总价
			String totalPrice = "";
			List<PurchaseApplyDetails> detailsList = purchaseApply.getPurchaseApplyDetails();
			for(PurchaseApplyDetails detail:detailsList){			
				totalPrice = AmountUtils.add(totalPrice,AmountUtils.multiply(detail.getBudgetPrice(), detail.getNumber()+""));
				detail.setPurchaseApply(purchaseApply);				
			}
			// 更新总价
			purchaseApply.setTotalPrice(totalPrice);
			purchaseApplyDao.saveOrUpdate(purchaseApply);
			System.out.println(purchaseApply.getTotalPrice()+"人民币");
			System.out.println("------------------------------");
		
			try{
				purchaseApplyDetailsDao.saveOrUpdateAll(detailsList);
			}catch(Exception e){
				e.printStackTrace();
			}		
			// 将采购申请id保存到Application中
			application.setApplyNo(purchaseApply.getId());
			// 添加新的审批申请
			approveService.createApp(application,user);		
		}else{
			
			// 修改申请
			approveService.updateApp(application,user);
			
			PurchaseApply oldpurchaseApply = purchaseApplyDao.findById(purchaseApply.getId());
			// 删除合同明细
			purchaseApplyDetailsDao.deleteAll(oldpurchaseApply.getPurchaseApplyDetails());	
			// 保存采购申请信息
			oldpurchaseApply.setTitle(purchaseApply.getTitle());
			oldpurchaseApply.setTotalPrice(purchaseApply.getTotalPrice());
			oldpurchaseApply.setInstruction(purchaseApply.getInstruction());
			oldpurchaseApply.setAdvancePay(purchaseApply.getAdvancePay());
			oldpurchaseApply.setPrePurchaseTime(purchaseApply.getPrePurchaseTime());
			oldpurchaseApply.setStatus(PurchaseApply.PURCHASE_STATUS_WAITINGAPPROVE);
			oldpurchaseApply.setApplyTime(DateUtils.getNowDateStr());
			purchaseApplyDao.saveOrUpdate(oldpurchaseApply);
			
			// 重新保存明细
			List<PurchaseApplyDetails> purchaseApplyDetails = purchaseApply.getPurchaseApplyDetails();
			
			if(purchaseApplyDetails!=null){			
				for(PurchaseApplyDetails details:purchaseApplyDetails){
					details.setPurchaseApply(oldpurchaseApply);
				}
				purchaseApplyDetailsDao.saveOrUpdateAll(purchaseApplyDetails);
			}
			
		
				
			
		}		
	}
	

	@Override
	public void deleteApp(Application application) {

		// TODO Auto-generated method stub
		application = approveService.findById(application.getId());
		
		if(application.getApplyType().equals(Application.APP_TYPE_PURCHASE)){
			purchaseApply = purchaseApplyService.findById(application.getApplyNo());
			purchaseApply.setStatus(PurchaseApply.PURCHASE_STATUS_DEL);
			purchaseApplyDao.saveOrUpdate(purchaseApply);
		}
		
		
	}


	@Override
	public void approve(Application application) {

		// TODO Auto-generated method stub
		application = approveService.findById(application.getId());
		
		if(application.getApplyType().equals(Application.APP_TYPE_PURCHASE)){
			purchaseApply = purchaseApplyService.findById(application.getApplyNo());
			
			if(application.getStatus()==Application.APP_STATUS_APPROVED){
				
				// 设置采购人
  			  List<ApproveFlow> flows = application.getApproveFlows();
			  
  			purchaseApply.setBuyUser(application.getUser());
			  User user = null;
			  for(ApproveFlow flow : flows){
				  user = flow.getUser();
				 try{
						Set<OaAction> actions = user.getRole().getActions();
						
						for(OaAction action:actions){
							if(action.getActionValue()==2104){
								purchaseApply.setBuyUser(user);
							}
						}
					}catch(Exception e){
						e.printStackTrace();
					}
			  }
				
//				if(AmountUtils.amount(purchaseApply.getAdvancePay())==0){
					purchaseApply.setStatus(PurchaseApply.PURCHASE_STATUS_STARTBUY);
//				}else{
//					purchaseApply.setStatus(PurchaseApply.PURCHASE_STATUS_WAITINGPAY);
					// 创建付款单
					try {
						orderService.createPayOrder(application.getId());
					} catch (GiroException e) {
						e.printStackTrace();
//					}
				}

			}else{
				purchaseApply.setStatus(PurchaseApply.PURCHASE_STATUS_APPROVEFAIL);
			}
			purchaseApplyDao.saveOrUpdate(purchaseApply);
		}
		
		
	}
	

	
}
