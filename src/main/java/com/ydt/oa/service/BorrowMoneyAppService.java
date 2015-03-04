package com.ydt.oa.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.giro.common.exception.GiroException;
import com.giro.common.util.DateUtils;
import com.giro.common.util.StringUtils;
import com.ydt.oa.dao.BorrowMoneyDao;
import com.ydt.oa.dao.FileLogDao;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.BorrowMoney;
import com.ydt.oa.entity.User;
import com.ydt.oa.interfaces.BorrowMoneyAppInterface;


/**
 * 系统设置业务逻辑层
 * @author caochun
 *
 */

@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class BorrowMoneyAppService implements BorrowMoneyAppInterface{
	

	@Autowired
	private ApproveService approveService;
	@Autowired
	private BorrowMoneyDao borrowMoneyDao;
	@Autowired
	private BorrowMoneyService borrowMoneyService;
	@Autowired
	private UserService userService;
	@Autowired
	private PaymentOrderService orderService;

	private BorrowMoney borrowMoney;
	
  
	
	/**
	 * 更新请款申请单
	 * @param application
	 * @param purchaseApply
	 * @throws GiroException 
	 */
	@Override
	public void updateBorrowMoneyApp(Application application, BorrowMoney borrowMoney, User user) throws GiroException {
		
		user = userService.findByUserId(user.getId());
		application.setUser(user);
		application.setDepartment(user.getDepartment());
	     application.setApplyType(Application.APP_TYPE_BORROWMONEY);
	
		borrowMoney.setBorrowTitle(application.getTitle());
		if(StringUtils.isNull(application.getId())){
			
			
			// 保存采购申请信息
			borrowMoney.setStatus(BorrowMoney.BORROWMONEY_WAITINGAPPROVE);
			borrowMoney.setApplyTime(DateUtils.getNowDateStr());
			borrowMoneyDao.saveOrUpdate(borrowMoney);
			
			
			// 将合同id保存到Application中
			application.setApplyNo(borrowMoney.getId());
			
			
			// 添加新的申请
			approveService.createApp(application,user);
			
		}else{
			
			// 修改申请
			approveService.updateApp(application,user);
			// 更新采购申请信息
			borrowMoney.setStatus(BorrowMoney.BORROWMONEY_WAITINGAPPROVE);
			borrowMoney.setApplyTime(DateUtils.getNowDateStr());
			
			// 先要删除合同采购明细，再重新添加
			
			borrowMoneyDao.saveOrUpdate(borrowMoney);
			
		}		
	}
	

	@Override
	public void deleteApp(Application application) {

		// TODO Auto-generated method stub
		application = approveService.findById(application.getId());
		
		if(application.getApplyType().equals(Application.APP_TYPE_BORROWMONEY)){
			borrowMoney =borrowMoneyService.findById(application.getApplyNo());
			borrowMoney.setStatus(BorrowMoney.BORROWMONEY_DELETE);
			borrowMoneyDao.saveOrUpdate(borrowMoney);
		}
		
		
	}


	@Override
	public void approve(Application application) {

		// TODO Auto-generated method stub
		application = approveService.findById(application.getId());
		
		if(application.getApplyType().equals(Application.APP_TYPE_BORROWMONEY)){
			borrowMoney = borrowMoneyService.findById(application.getApplyNo());
			
			if(application.getStatus()==Application.APP_STATUS_APPROVED){
				borrowMoney.setStatus(BorrowMoney.BORROWMONEY_VALID);
				
				// 创建付款单
				try {
					orderService.createPayOrder(application.getId());
				} catch (GiroException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				borrowMoney.setStatus(BorrowMoney.BORROWMONEY_INVALID);
			}
			borrowMoneyDao.saveOrUpdate(borrowMoney);
		}
		
		
	}
	

	
}
