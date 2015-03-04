package com.ydt.oa.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.giro.common.exception.GiroException;
import com.giro.common.util.DateUtils;
import com.giro.common.util.StringUtils;
import com.ydt.oa.dao.RequestMoneyDao;
import com.ydt.oa.dao.FileLogDao;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.Contract;
import com.ydt.oa.entity.ContractDetails;
import com.ydt.oa.entity.RequestMoney;
import com.ydt.oa.entity.FileLog;
import com.ydt.oa.entity.User;
import com.ydt.oa.interfaces.RequestMoneyAppInterface;


/**
 * 系统设置业务逻辑层
 * @author caochun
 *
 */

@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class RequestMoneyAppService implements RequestMoneyAppInterface{
	

	@Autowired
	private ApproveService approveService;
	@Autowired
	private RequestMoneyDao requestMoneyDao;
	@Autowired
	private FileLogDao fileLogDao;
	@Autowired
	private RequestMoneyService requestMoneyService;
	@Autowired
	private UserService userService;
	@Autowired
	private PaymentOrderService orderService;
	
	private RequestMoney requestMoney;
	
  
	
	/**
	 * 更新请款申请单
	 * @param application
	 * @param purchaseApply
	 * @throws GiroException 
	 */
	@Override
	public void updateRequestMoneyApp(Application application, RequestMoney requestMoney, List<FileLog> fileLogList, User user) throws GiroException {
		
		user = userService.findByUserId(user.getId());
		application.setUser(user);
		application.setDepartment(user.getDepartment());
		if(requestMoney.getRequestType().equals("REQUESTMONEYENGINEER")) {
			application.setApplyType(Application.APP_TYPE_REQUESTMONEYENGINEER);
		} else {
		application.setApplyType(Application.APP_TYPE_REQUESTMONEYMATERIAL);}
		requestMoney.setRequestTitle(application.getTitle());
		if(StringUtils.isNull(application.getId())){
			
			
			// 保存采购申请信息
			requestMoney.setStatus(RequestMoney.REQUESTMONEY_WAITINGAPPROVE);
			requestMoney.setApplyTime(DateUtils.getNowDateStr());
			requestMoneyDao.saveOrUpdate(requestMoney);
			
			if(fileLogList!=null){
				
				String[] ids = new String[fileLogList.size()];
				for(int i=0;i< fileLogList.size();i++){
					ids[i]= fileLogList.get(i).getId();
				}
				
				Collection<FileLog> fileLogs = fileLogDao.findByIdList(ids);
				
				for(FileLog fileLog:fileLogs){
					fileLog.setBusinessNo(requestMoney.getId());
				}
				
				fileLogDao.saveOrUpdateAll(fileLogs);
			}
			
			
			
			// 将合同id保存到Application中
			application.setApplyNo(requestMoney.getId());
			
			
			// 添加新的申请
			approveService.createApp(application,user);
			
		}else{
			
			// 修改申请
			approveService.updateApp(application,user);
			
			RequestMoney oldRequestMoney = requestMoneyDao.findById(requestMoney.getId());
			
			// 清楚文件绑定
			
			Collection<FileLog> oldFileList = fileLogDao.findByBusinessNo(RequestMoney.FILE_TYPE, oldRequestMoney.getId());
			
			for(FileLog file:oldFileList){
				file.setBusinessNo(null);
			}
			fileLogDao.saveOrUpdateAll(oldFileList);
			
			// 保存请款申请信息
			oldRequestMoney.setRequestMoney(requestMoney.getRequestMoney());
			oldRequestMoney.setRequestTitle(requestMoney.getRequestTitle());
			oldRequestMoney.setRequestType(requestMoney.getRequestType());
			oldRequestMoney.setReason(requestMoney.getReason());
			oldRequestMoney.setApplyTime(DateUtils.getNowDateStr());
			oldRequestMoney.setStatus(RequestMoney.REQUESTMONEY_WAITINGAPPROVE);
			
			oldRequestMoney.setAccount(requestMoney.getAccount());
			oldRequestMoney.setContractNo(requestMoney.getContractNo());
			oldRequestMoney.setPayee(requestMoney.getPayee());
			
			requestMoneyDao.saveOrUpdate(oldRequestMoney);
	

			if(fileLogList!=null){
				
				String[] ids = new String[fileLogList.size()];
				for(int i=0;i< fileLogList.size();i++){
					ids[i]= fileLogList.get(i).getId();
				}
				
				Collection<FileLog> fileLogs = fileLogDao.findByIdList(ids);
				
				for(FileLog fileLog:fileLogs){
					fileLog.setBusinessType(RequestMoney.FILE_TYPE);
					fileLog.setBusinessNo(oldRequestMoney.getId());
				}
				
				fileLogDao.saveOrUpdateAll(fileLogs);
			}
			
			

		
		
		
		

				
			
		}		
	}
	

	@Override
	public void deleteApp(Application application) {

		// TODO Auto-generated method stub
		application = approveService.findById(application.getId());
		
		if(application.getApplyType().equals(Application.APP_TYPE_REQUESTMONEYENGINEER)||
				application.getApplyType().equals(Application.APP_TYPE_REQUESTMONEYMATERIAL)){
			requestMoney = requestMoneyService.findById(application.getApplyNo());
			requestMoney.setStatus(RequestMoney.REQUESTMONEY_DELETE);
			requestMoneyDao.saveOrUpdate(requestMoney);
		}
		
		
	}


	@Override
	public void approve(Application application) {

		// TODO Auto-generated method stub
		application = approveService.findById(application.getId());
		
		if(application.getApplyType().equals(Application.APP_TYPE_REQUESTMONEYENGINEER)||
				application.getApplyType().equals(Application.APP_TYPE_REQUESTMONEYMATERIAL)){
			requestMoney = requestMoneyService.findById(application.getApplyNo());
			
			if(application.getStatus()==Application.APP_STATUS_APPROVED){
				requestMoney.setStatus(RequestMoney.REQUESTMONEY_VALID);
				// 创建付款单
				try {
					orderService.createPayOrder(application.getId());
				} catch (GiroException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				requestMoney.setStatus(RequestMoney.REQUESTMONEY_INVALID);
			}
			requestMoneyDao.saveOrUpdate(requestMoney);
		}
		
		
	}
	

	
}
