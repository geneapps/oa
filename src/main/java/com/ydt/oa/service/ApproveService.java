package com.ydt.oa.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.giro.common.util.DateUtils;
import com.ydt.oa.dao.ApplicationDao;
import com.ydt.oa.dao.ApproveFlowConfigDao;
import com.ydt.oa.dao.ApproveFlowDao;
import com.ydt.oa.dao.RoleDao;
import com.ydt.oa.dao.UserDao;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.ApproveFlow;
import com.ydt.oa.entity.ApproveFlowConfig;
import com.ydt.oa.entity.Department;
import com.ydt.oa.entity.OaAction;
import com.ydt.oa.entity.Role;
import com.ydt.oa.entity.User;
import com.ydt.oa.interfaces.AdminAppInterface;
import com.ydt.oa.interfaces.BorrowMoneyAppInterface;
import com.ydt.oa.interfaces.ContractAppInterface;
import com.ydt.oa.interfaces.ExpenseAppInterface;
import com.ydt.oa.interfaces.ProjectAppInterface;
import com.ydt.oa.interfaces.PurchaseApplyAppInterface;
import com.ydt.oa.interfaces.PurchasePlanAppInterface;
import com.ydt.oa.interfaces.RequestMoneyAppInterface;


/**
 * 申请的业务逻辑层，主要实现了申请单创建、修改、删除以及审批流程控制
 * 
 * @author caochun
 * 
 */
@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class ApproveService {

	@Autowired
	private ApplicationDao applicationDao;
	@Autowired
	private ApproveFlowDao approveFlowDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private SmsService smsService;
	
	
	@Autowired
	private ApproveFlowConfigDao approveFlowConfigDao;
	@Autowired
	private ContractAppInterface contractInterface;
	@Autowired
	private ProjectAppInterface projectInterface;
	@Autowired
	private UserService userService;
	@Autowired
	private PurchasePlanAppInterface purchasePlanInterface;
	@Autowired
	private PurchaseApplyAppInterface purchaseApplyInterface;
	@Autowired
	private RequestMoneyAppInterface requestMoneyInterface;
	@Autowired
	private BorrowMoneyAppInterface borrowMoneyInterface;
	@Autowired
	private ExpenseAppInterface expenseInterface;
	@Autowired
	private AdminAppInterface adminInterface;

	/**
	 * 显示用户全部的申请，按时间排序
	 * 
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public Pagination findApplicationByUser(User user, int currPage, int pageSize) {

		return applicationDao.findApplicationByUser(user, currPage, pageSize);
	}
	//根据申请编号来得到申请表记录
	public Application findByApplyNo(String type,String id) {
		return applicationDao.findByApplyNo(type,id);
	}
	
	public List<Application> findByApplyIdDep(String id) {
		return applicationDao.findByApplyIdDep(id);
	}
	/**
	 * 显示历史申请
	 * @param user
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public Pagination findHistoryApplicationByUser(User user, int currPage, int pageSize) {

		return applicationDao.findHistoryApplicationByUser(user, currPage, pageSize);
	}

	
	/**
	 * 显示需要用户的审批的申请表
	 * 
	 * @param role
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public Pagination findApprove(User user, int currPage, int pageSize) {

		user = userService.findByUserId(user.getId());
		return applicationDao.findApprove(user, currPage, pageSize);
	}
	
	/**
	 * 历史审批
	 * @param user
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public Pagination findApproveByUser(User user, int currPage, int pageSize) {

		user = userService.findByUserId(user.getId());
		return applicationDao.findApproveByUser(user, currPage, pageSize);
	}
	/**
	 * 审批通过了的
	 * @param user
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public Pagination findApproveByCashier(int currPage, int pageSize) {
        return applicationDao.findApproveByCashier(currPage, pageSize);
	}
	public Application findById(String appId) {

		return applicationDao.findById(appId);
	}

	public List<ApproveFlow> findApproveFlowsByAppId(String appId) {

		return applicationDao.findById(appId).getApproveFlows();
		
	}

	// 创建申请单和审批流程，由于不同类型的申请单都有具体的业务，该方法只负责申请单通用部分，具体到表结构就是Application和Flow表
	public void createApp(Application application, User user) throws GiroException {
		try{
			user = userService.findByUserId(user.getId());
			application.setUser(user);
			if(application.getDepartment()==null){
				application.setDepartment(user.getDepartment());
			}
			application.setStatus(Application.APP_STATUS_WAITINGAPPROVE);
			application.setCreateTime(DateUtils.getNowDateStr());
			applicationDao.saveOrUpdate(application);
			// 根据类型生成审批流程
			List<ApproveFlow> flows = generateFlows(application);
		//	System.out.println("审批步骤："+flows.size());
			approveFlowDao.saveAllFlows(flows);
			
			// 发送短信
			for(ApproveFlow flow:flows){
				if(flow.isActivate()){
					System.out.println("员工id"+flow.getActionId()  + "Action type  " + flow.getActionType());
					sms(flow);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new GiroException(-1,e.getMessage());
		}
		
		

	}

	/**
	 * 更新申请单信息，由于不同类型的申请单都有具体的业务，该方法只负责申请单通用部分，具体到表结构就是Application和Flow表
	 * 
	 * @param application
	 * @param user
	 * @return
	 * @throws GiroException
	 */
	public void updateApp(Application application, User user) throws GiroException {

		Application oldApp = applicationDao.findById(application.getId());

		//System.err.println(application.getStatus());
			if (oldApp.getStatus() == Application.APP_STATUS_APPROVED) {
				throw new GiroException(-1, "审批已经通过，不能修改");
			} else if (oldApp.getStatus() == Application.APP_STATUS_APPROVEING) {
				throw new GiroException(-2, "正在审批，不能修改");
			} else if (oldApp.getStatus() == Application.APP_STATUS_DELETE) {
				throw new GiroException(-3, "修改的申请不存在");
			}
			application.setDepartment(user.getDepartment());
			if (!oldApp.getApplyType().equals(application.getApplyType())) {
				throw new GiroException(-4, "申请类型不能修改");
			}
			if (!oldApp.getUser().getId().equals(user.getId())) {
				throw new GiroException(-5, "只能本人修改申请");
			}
			//System.out.println("你应该看不到我！！！");
			oldApp.setTitle(application.getTitle());
			oldApp.setDepartment(application.getDepartment());
			oldApp.setStatus(Application.APP_STATUS_WAITINGAPPROVE);
			oldApp.setCreateTime(DateUtils.getNowDateStr());
			applicationDao.saveOrUpdate(oldApp);
			List<ApproveFlow> flows = oldApp.getApproveFlows();
			for (ApproveFlow flow : flows) {
				// 审批排序从大到小，当orderby为0时表示最后一步审批
				if (flow.getOrderBy() == flows.size()-1) {
					flow.setActivate(true);
				} else {
					flow.setActivate(false);
				}	
				flow.setStatus(ApproveFlow.FLOW_STATUS_WAITINGAPPROVE);
			}
			approveFlowDao.saveAllFlows(flows);
			// 发送短信
			for(ApproveFlow flow:flows){
				if(flow.isActivate()){
					sms(flow);
				}
			}
			
	}

	/**
	 * 删除申请，由于不同类型的申请单都有具体的业务，改方法最后会调用具体业务的deleteApp方法
	 * 
	 * @param application
	 * @return
	 * @throws GiroException
	 */
	public void deleteApp(Application application, User user) throws GiroException {

		Application oldApp = applicationDao.findById(application.getId());
		if (oldApp.getStatus() == Application.APP_STATUS_APPROVED) {
			throw new GiroException(-1, "审批已经通过，不能删除");
		/*} else if (oldApp.getStatus() == Application.APP_STATUS_APPROVEING) {
			throw new GiroException(-2, "正在审批，不能删除");*/
		} else if (oldApp.getStatus() == Application.APP_STATUS_DELETE) {
			throw new GiroException(-3, "修改的申请不存在");
		}
		if (!oldApp.getUser().getId().equals(user.getId())) {
			throw new GiroException(-5, "只能本人删除申请");
		}
		oldApp.setStatus(Application.APP_STATUS_DELETE);
		oldApp.setCreateTime(DateUtils.getNowDateStr());
		applicationDao.saveOrUpdate(oldApp);
		List<ApproveFlow> flows = oldApp.getApproveFlows();
		for (ApproveFlow flow : flows) {
			// 审批排序从大到小，当orderby为0时表示最后一步审批
			flow.setActivate(false);
			flow.setStatus(ApproveFlow.FLOW_STATUS_CANCEL);
		}
		approveFlowDao.saveAllFlows(flows);
		// 删除申请相关信息
		if (Application.APP_TYPE_MATERIALCONTRACT.equals(oldApp.getApplyType())) {
			contractInterface.deleteApp(oldApp);
		}
		else if (Application.APP_TYPE_MANCONTRACT.equals(oldApp.getApplyType())) {
			contractInterface.deleteApp(oldApp);
		}
		else if(Application.APP_TYPE_PURCHASEPLAN.equals(oldApp.getApplyType())) {
			purchasePlanInterface.deleteApp(oldApp);
		}else if(Application.APP_TYPE_PROJECT.equals(oldApp.getApplyType())) {
			projectInterface.deleteApp(oldApp);
		}else if(Application.APP_TYPE_REQUESTMONEYMATERIAL.equals(oldApp.getApplyType())||
				Application.APP_TYPE_REQUESTMONEYENGINEER.equals(oldApp.getApplyType())) {
			requestMoneyInterface.deleteApp(oldApp);
		}else if(Application.APP_TYPE_BORROWMONEY.equals(oldApp.getApplyType())) {
			borrowMoneyInterface.deleteApp(oldApp);
		}else if(Application.APP_TYPE_PURCHASE.equals(oldApp.getApplyType())) {
			purchaseApplyInterface.deleteApp(oldApp);
		}else if(Application.APP_TYPE_EXPENSEOTHER.equals(oldApp.getApplyType())) {
			expenseInterface.deleteApp(oldApp);
		}else if(Application.APP_TYPE_ADMIN.equals(oldApp.getApplyType())){
			adminInterface.deleteApp(oldApp);
		}
		
	}

	/**
	 * 根据申请类型生成对应的类型
	 * 
	 * @param app
	 * @return
	 */
	private List<ApproveFlow> generateFlows(Application app) {

		if (app == null) {
			return null;
		}
		List<ApproveFlow> flows = new ArrayList<ApproveFlow>();
		System.out.println("appType:"+app.getApplyType());
		List<ApproveFlowConfig> flowConfigs = approveFlowConfigDao.list(app.getApplyType());
		System.out.println("flowConfigs:"+flowConfigs.size());
		int steps = flowConfigs.size()-1;//当orderby为0时表示最后一步审批
		for (ApproveFlowConfig config : flowConfigs) {
			ApproveFlow flow = new ApproveFlow();
			flow.setApplication(app);
			flow.setOrderBy(steps--);
			// 审批排序从大到小，
			if (flow.getOrderBy() == flowConfigs.size()-1) {
				flow.setActivate(true);
			} else {
				flow.setActivate(false);
			}
			flow.setActionId(config.getActionId());
			flow.setActionType(config.getActionType());
			flow.setStatus(ApproveFlow.FLOW_STATUS_WAITINGAPPROVE);
			flows.add(flow);
		}
		return flows;
	}
	
	private boolean canApprove(ApproveFlow flow,User user){
		if(flow.getActionType()==ApproveFlowConfig.ACTIONTYPE_ACTION){
			
			Set<OaAction> actions = user.getRole().getActions();
			for(OaAction action:actions){
				if(action.getId().equals(flow.getActionId())){
					
					for(Department dep:user.getAllChilds()){
						if(flow.getApplication().getDepartment().getId().equals(dep.getId())){
							return true;
						}
					}
					
//					return true;
				}
			}
			
		}else if(flow.getActionType()==ApproveFlowConfig.ACTIONTYPE_ROLE){
//			Role role = roleDao.findById(flow.getActionId());
			
			if(flow.getActionId().equals(user.getRole().getId())){
				return true;
			}
			
		}else if(flow.getActionType()==ApproveFlowConfig.ACTIONTYPE_USER){
			User flowUser = userService.findByUserId(flow.getActionId());
			if(flow.getActionId().equals(user.getId())){
				return true;
			}
		}
		
		return false;

		
	}
	
	private Set<User> findApproveFlowUser(ApproveFlow flow){
		Set<User> returnUsers = new HashSet<User>();
		if(flow.getActionType()==ApproveFlowConfig.ACTIONTYPE_ACTION){
			
			List<Role> roles = roleDao.list(flow.getActionId());
		//	System.out.println(">>>>findApproveFlowUser.roles.size="+roles.size());
			List<User> users = new ArrayList<User>();
			List<User> temp = new ArrayList<User>();
			for(Role r : roles){
				temp = userDao.listByRole(r);
				users.addAll(temp);
			}
		//	System.out.println(">>>>findApproveFlowUser.user.size="+users.size());
			String depId = flow.getApplication().getDepartment().getId();
			for(User user:users){
				for(Department dep:user.getAllChilds()){
					if(depId.equals(dep.getId())){
						returnUsers.add(user);
					}
				}
				
				
			}
		//	System.out.println(">>>>findApproveFlowUser.returnUser.size="+returnUsers.size());
			
		}else if(flow.getActionType()==ApproveFlowConfig.ACTIONTYPE_ROLE){
			Role role = roleDao.findById(flow.getActionId());
			Set<User> users = role.getUsers();
			
			for(User user:users){
				returnUsers.add(user);
			}
			
		}else if(flow.getActionType()==ApproveFlowConfig.ACTIONTYPE_USER){
			User flowUser = userService.findByUserId(flow.getActionId());
			returnUsers.add(flowUser);
		}
		
		return returnUsers;
	}
	
	
	
	public List<ApproveFlowConfig> findApproveFlowConfigs(String appType){
		return approveFlowConfigDao.list(appType);
	}
	
	public void saveOrUpdateApproveFlowConfig(ApproveFlowConfig flowConfig){
		approveFlowConfigDao.saveOrUpdate(flowConfig);
	}
	
	public ApproveFlowConfig findApproveFlowConfig(String id){
		return approveFlowConfigDao.findById(id);
	}
	
	public void delApproveFlowConfig(String id){
		approveFlowConfigDao.delete(findApproveFlowConfig(id));
	}
	
	public void approve(Application application, ApproveFlow appFlow, User user) throws GiroException {
		approve(application, appFlow, user,false) ;
	}

	// 创建申请单和审批流程
	public void approve(Application application, ApproveFlow appFlow, User user,boolean flags) throws GiroException {

		application = findById(application.getId());
		user = userService.findByUserId(user.getId());
		List<ApproveFlow> flows = application.getApproveFlows();
		ApproveFlow flow = null;
		for (int i = 0; i < flows.size(); i++) {
			flow = flows.get(i);
			if (flow.isActivate()) {
				if (canApprove(flow,user)) {
					flow.setUser(user);
					flow.setFlowDate(DateUtils.getNowDateStr());
					flow.setActivate(false);
					flow.setStatus(appFlow.getStatus());
					//System.out.print(flow.getStatus()+"【sososososososoososos】");
					if((appFlow.getFlowView())==null||(appFlow.getFlowView().trim().equals(""))){
						if (flow.getStatus()==1) {
							flow.setFlowView("通过");
						} else {
							flow.setFlowView("不通过");
						}
					}else {
						flow.setFlowView(appFlow.getFlowView());
					}
					approveFlowDao.saveFlow(flow);
					if(flags){
						application.setStatus(Application.APP_STATUS_APPROVED);
						sms(application);
					}else{
						if (flow.getStatus() == ApproveFlow.FLOW_STATUS_NOTAPPROVED) {
							application.setStatus(flow.getStatus());
						} else {
							if (flow.getOrderBy() == 0) {
								application.setStatus(Application.APP_STATUS_APPROVED);
								sms(application);
							} else {
								ApproveFlow preFlow = flows.get(i);
								flow = flows.get(i + 1);
								flow.setActivate(true);
								approveFlowDao.saveFlow(flow);
								//前一个审批完了给申请人发送短信
								sms(application,preFlow,user);
								// 给下一个审批发送短信
								sms(flow);
								application.setStatus(Application.APP_STATUS_APPROVEING);
							}
						}
					}
					applicationDao.saveOrUpdate(application);
					// 处理具体申请单，需要具体业务实现approve方法
					if (application.getApplyType().equals(Application.APP_TYPE_MATERIALCONTRACT)) {
						contractInterface.approve(application);
					}
					else if (application.getApplyType().equals(Application.APP_TYPE_MANCONTRACT)) {
						contractInterface.approve(application);
					}
					else if (application.getApplyType().equals(Application.APP_TYPE_PROJECT)) {
						projectInterface.approve(application);
					}else if(application.getApplyType().equals(Application.APP_TYPE_PURCHASEPLAN)) {
						purchasePlanInterface.approve(application);
					}else if(application.getApplyType().equals(Application.APP_TYPE_REQUESTMONEYENGINEER)||
							application.getApplyType().equals(Application.APP_TYPE_REQUESTMONEYMATERIAL)) {
						requestMoneyInterface.approve(application);
					}else if(application.getApplyType().equals(Application.APP_TYPE_BORROWMONEY)) {    
						borrowMoneyInterface.approve(application);
					}else if(application.getApplyType().equals(Application.APP_TYPE_PURCHASE)) {
						purchaseApplyInterface.approve(application);
					}else if(application.getApplyType().equals(Application.APP_TYPE_EXPENSEOTHER)) {
						expenseInterface.approve(application);
					}else if(application.getApplyType().equals(Application.APP_TYPE_ADMIN)){
						adminInterface.approve(application);
					}
				} else {
					throw new GiroException(-1, "权限不足");
				}
				break;
			}
		}
	}
	
	private void sms(Application application, ApproveFlow appFlow,User user) {

	/*	Set<User> users = findApproveFlowUser(appFlow);
		//User user = users.get(users.size()-1);
		User user = null;
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()){
			 user = iterator.next();
		}*/
		System.out.println("-----审批人姓名  " + user.getRealName() + "+++++审批人职位    " + user.getRole().getName() + "  "  + application.getUser().getRealName() );
		smsService.sendSms(application.getUser().getMobile(), "您的申请《"+appFlow.getApplication().getTitle()+"》" + user.getRole().getName() + " " + user.getRealName() + " 已完成审批。");
		
	}
	public void sms(ApproveFlow appFlow){
		
		Set<User> users = findApproveFlowUser(appFlow);
	//	System.out.println("-------------" + users.size() + "user  ");
		for(User user:users){
	//		System.out.println("-----" + user.getRealName() + " " + user.getMobile());
			smsService.sendSms(user.getMobile(), "《"+appFlow.getApplication().getTitle()+"》需要您审批！");
		}
		
	}
	
	public void sms(Application app){
		smsService.sendSms(app.getUser().getMobile(), "您的申请《"+app.getTitle()+"》已经完成审批！");
		
	}
	
	public boolean showButton(String  appId){
		Application application = findById(appId);
		List<ApproveFlow> flows = application.getApproveFlows();
		if(flows.size() <= 2){
			return true;
		}else if(application.getStatus()==Application.APP_STATUS_APPROVEING){
		
		
		
		ApproveFlow flow1 = flows.get(flows.size()-2);
		ApproveFlow flow0 = flows.get(flows.size()-1);
		
		if(flow1.isActivate() && flow0.getActionType()==0 && flow0.getActionId().equals("9C406B92CDDF449CA7237BAB34360EAB")){
			return true;
			
		}
		}
		return false;
		
	}
	public Pagination findHistoryApprove(Application application, int currPage, int pageSize) {
		
		return applicationDao.findHistoryApprove(application,currPage, pageSize);
	}
	
}
