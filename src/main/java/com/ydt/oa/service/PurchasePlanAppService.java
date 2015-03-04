package com.ydt.oa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.giro.common.exception.GiroException;
import com.giro.common.util.DateUtils;
import com.giro.common.util.StringUtils;

import com.ydt.oa.dao.PurchasePlanDao;
import com.ydt.oa.dao.PurchasePlanDetailsDao;
import com.ydt.oa.entity.Application;

import com.ydt.oa.entity.ContractDetails;
import com.ydt.oa.entity.Project;
import com.ydt.oa.entity.PurchaseApply;
import com.ydt.oa.entity.PurchaseApplyDetails;
import com.ydt.oa.entity.PurchasePlan;
import com.ydt.oa.entity.PurchasePlanDetails;
import com.ydt.oa.entity.User;
import com.ydt.oa.interfaces.PurchasePlanAppInterface;


/**
 * 系统设置业务逻辑层
 * @author caochun
 *
 */

@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class PurchasePlanAppService implements PurchasePlanAppInterface{
	
	@Autowired
	private ApproveService approveService;
	@Autowired
	private PurchasePlanDao purchasePlanDao;
	@Autowired
	private PurchasePlanDetailsDao purchasePlanDetailsDao;
	@Autowired
	private PurchasePlanService purchasePlanService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectService projectService;
	
	private PurchasePlan purchasePlan;
	

	
	public PurchasePlan getPurchasePlan() {
		return purchasePlan;
	}


	public void setPurchasePlan(PurchasePlan purchasePlan) {
		this.purchasePlan = purchasePlan;
	}


	/**
	 * 更新采购申请单
	 */
	@Override
	public void updatePurchasePlanApp(Application application, PurchasePlan purchasePlan, User user) throws GiroException {
		
		user = userService.findByUserId(user.getId());
		application.setUser(user);
		application.setDepartment(user.getDepartment());
		application.setApplyType(Application.APP_TYPE_PURCHASEPLAN);
		purchasePlan.setTitle(application.getTitle());
		//Project pro = projectService.findByDepart(user.getDepartment().getDepName());
		//purchasePlan.setProject(pro);
		if(StringUtils.isNull(application.getId())){
			
			
			// 保存采购申请信息
			//TODO
			purchasePlan.setStatus(PurchasePlan.PURCHASEPLAN_WAITINGAPPROVE);
			purchasePlan.setApplytime(DateUtils.getNowDateStr());
			purchasePlanDao.saveOrUpdate(purchasePlan);
			
			List<PurchasePlanDetails> detailsList = purchasePlan.getPurchasePlanDetails();
			for(PurchasePlanDetails purchasePlanDetails:detailsList){
				purchasePlanDetails.setPurchasePlan(purchasePlan);
			}
			
			purchasePlanDetailsDao.saveOrUpdateAll(detailsList);
			
			
			// 将采购计划id保存到Application中
			application.setApplyNo(purchasePlan.getId());
			
			
			// 修改之后要重新审批所以需要创建新的申请
			approveService.createApp(application,user);
			
		}else{
			
			// 修改申请
			approveService.updateApp(application,user);
				
			PurchasePlan oldpurchasePlan = purchasePlanDao.findById(purchasePlan.getId());
			// 删除合同明细
			purchasePlanDetailsDao.deleteAll(oldpurchasePlan.getPurchasePlanDetails());
			
			
			// 保存采购申请信息
			oldpurchasePlan.setTitle(purchasePlan.getTitle());
			oldpurchasePlan.setTotalPrice(purchasePlan.getTotalPrice());
			oldpurchasePlan.setInstruction(purchasePlan.getInstruction());
			oldpurchasePlan.setStatus(PurchaseApply.PURCHASE_STATUS_WAITINGAPPROVE);
			oldpurchasePlan.setApplytime(DateUtils.getNowDateStr());
			purchasePlanDao.saveOrUpdate(oldpurchasePlan);
			
			// 重新保存明细
			List<PurchasePlanDetails> purchasePlanDetails = purchasePlan.getPurchasePlanDetails();
			
			if(purchasePlanDetails!=null){			
				for(PurchasePlanDetails contractDetails:purchasePlanDetails){
					//System.out.println(contractDetails.getId());
					//System.out.println(contractDetails.getNumber());
					contractDetails.setPurchasePlan(oldpurchasePlan);
				}
				purchasePlanDetailsDao.saveOrUpdateAll(purchasePlanDetails);
			}
			
			
		}		
	}
	

	@Override
	public void deleteApp(Application application) {

		// TODO Auto-generated method stub
		application = approveService.findById(application.getId());
		
		if(application.getApplyType().equals(Application.APP_TYPE_PURCHASEPLAN)){
			purchasePlan = purchasePlanService.findById(application.getApplyNo());
			purchasePlan.setStatus(PurchasePlan.PURCHASEPLAN_DELETE);
			purchasePlanDao.saveOrUpdate(purchasePlan);
		}
		
		
	}


	@Override
	public void approve(Application application) {

		// TODO Auto-generated method stub
		application = approveService.findById(application.getId());
		
		if(application.getApplyType().equals(Application.APP_TYPE_PURCHASEPLAN)){
			purchasePlan = purchasePlanService.findById(application.getApplyNo());
			
			if(application.getStatus()==Application.APP_STATUS_APPROVED){
				purchasePlan.setStatus(PurchasePlan.PURCHASEPLAN_VALID);
			}else{
				purchasePlan.setStatus(PurchasePlan.PURCHASEPLAN_INVALID);
			}
			purchasePlanDao.saveOrUpdate(purchasePlan);
		}
		
		
	}
	

	
}
