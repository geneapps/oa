package com.ydt.oa.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.giro.common.action.PageAction;
import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.ydt.oa.bean.AjaxResult;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.ApproveFlow;
import com.ydt.oa.entity.Contract;
import com.ydt.oa.entity.Project;
import com.ydt.oa.entity.PurchasePlan;
import com.ydt.oa.entity.User;
import com.ydt.oa.service.ApproveService;
import com.ydt.oa.service.ProjectService;
import com.ydt.oa.service.UserService;

/**
 * 审批管理Action
 * 
 * @author caochun
 * 
 */
public class ApproveAction extends PageAction {

	@Autowired
	private ApproveService approveService;
	private Pagination pagination;
	private Application application;
	private List<ApproveFlow> approveFlows;
	// private String formAction;
	private AjaxResult ajaxResult;
	private PurchasePlan purchasePlan;
	private User user;

	public User getUser() {

		return user;
	}

	public void setUser(User user) {

		this.user = user;
	}

	public PurchasePlan getPurchasePlan() {

		return purchasePlan;
	}

	public void setPurchasePlan(PurchasePlan purchasePlan) {

		this.purchasePlan = purchasePlan;
	}

	public Application getApplication() {

		return application;
	}

	public void setApplication(Application application) {

		this.application = application;
	}

	public List<ApproveFlow> getApproveFlows() {

		return approveFlows;
	}

	public void setApproveFlows(List<ApproveFlow> approveFlows) {

		this.approveFlows = approveFlows;
	}

	/**
	 * 列出我的申请
	 * 
	 * @return
	 */
	@Action(value = "/approve/myapplication",
			results = { @Result(name = "success", location = "/approve/myapplication.jsp", type = "dispatcher") }
			)
			public String applicationList() {

		// System.out.println(this.getLoginUser().getRealName()+"@@@@@");
		pagination = approveService.findApplicationByUser(this.getLoginUser(), this.getPageNum(), this.getNumPerPage());
		return "success";
	}

	/**
	 * 历史申请
	 * 
	 * @return
	 */
	@Action(value = "/approve/historyapplication",
			results = { @Result(name = "success", location = "/approve/historyapplication.jsp", type = "dispatcher") }
			)
			public String historyApplicationList() {

		pagination = approveService.findHistoryApplicationByUser(this.getLoginUser(), this.getPageNum(),
				this.getNumPerPage());
		return "success";
	}

	/**
	 * 我的审批
	 * 
	 * @return
	 */
	@Action(value = "/approve/myapprove",
			results = { @Result(name = "success", location = "/approve/myapprove.jsp", type = "dispatcher") }
			)
			public String approveList() {

		ajaxResult = new AjaxResult();
		try {
			// canAccess(1001); //
			pagination = approveService.findApprove(getLoginUser(), this.getPageNum(), this.getNumPerPage());
		
		}
		// catch(GiroException e){
		// //
		// if(e.getCode()==-99){
		// ajaxResult.setNavTabId("approveRel");
		// ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
		// ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
		// ajaxResult.setMessage(e.getMessage());
		// return ACTION_ACCESSERROR;
		// }
		// e.printStackTrace();
		// }
		catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 历史审批
	 * 
	 * @return
	 */
	@Action(value = "/approve/historyapprove",
			results = { @Result(name = "success", location = "/approve/historyapprove.jsp", type = "dispatcher") }
			)
			public String historyAapproveList() {

		try {
			if (application != null) {
				pagination = approveService.findHistoryApprove(application, getPageNum(), getNumPerPage());
			} else {
				pagination = approveService.findApproveByUser(getLoginUser(), this.getPageNum(), this.getNumPerPage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 审批通过的申请
	 * 
	 * @return
	 */
	@Action(value = "/financial/waitpaymentapp",
			results = { @Result(name = "success", location = "/payment/waitpaymentapp.jsp", type = "dispatcher") }
			)
			public String AppapproveList() {

		try {
			pagination = approveService.findApproveByCashier(getPageNum(), getNumPerPage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * /** 列出审批流程
	 * 
	 * @return
	 */
	@Action(value = "/approve/appflowlist",
			results = { @Result(name = "success", location = "/approve/flowlist.jsp", type = "dispatcher") }
			)
			public String appflowlist() {

		// approveFlows =
		// approveService.findApproveFlowsByAppId(application.getId());
		application = approveService.findById(application.getId());
		return "success";
	}

	/**
	 * 根据申请编号列出审批流程
	 * 
	 * @return
	 */
	@Action(value = "/purchaseplan/approveflow",
			results = { @Result(name = "success", location = "/approve/flowlist.jsp", type = "dispatcher") }
			)
			public String approveflow() {

		// System.out.println(purchasePlan.getId());
		application = approveService.findByApplyNo(Application.APP_TYPE_PURCHASEPLAN, purchasePlan.getId());
		System.out.println(application.getApplyType() + "*******");
		approveFlows = approveService.findApproveFlowsByAppId(application.getId());
		return "success";
	}

	public Pagination getPagination() {

		return pagination;
	}

	/**
	 * 编辑采购申请 当已经开始审批的时候则不能修改
	 * 
	 * @return
	 */
	@Action(value = "/approve/editapp",
			results = {
						@Result(name = "contract", location = "editcontractapp", type = "chain"),
						@Result(name = "mancontract", location = "editmancontractapp", type = "chain"),
						@Result(name = "purchaseplan", location = "editpurchaseplanapp", type = "chain"),
						@Result(name = "project", location = "editprojectapp", type = "chain"),
						@Result(name = "purchase", location = "editpurchaseapplyapp", type = "chain"),
						@Result(name = "requestmoney", location = "editrequestmoneyapp", type = "chain"),
						@Result(name = "borrowmoney", location = "editborrowmoneyapp", type = "chain"),
						@Result(name = "expense", location = "editexpenseapp1", type = "chain"),
						@Result(name = "admin", location = "editadminapp", type = "chain"),
					}
			)
			public String editApp() {

		if (application != null && application.getId() != null) {
			application = approveService.findById(application.getId());
			if (application.getApplyType().equals(Application.APP_TYPE_MATERIALCONTRACT)) {
				return "contract";
			} else if (application.getApplyType().equals(Application.APP_TYPE_MANCONTRACT)) {
				return "mancontract";
			} else if (application.getApplyType().equals(Application.APP_TYPE_PURCHASEPLAN)) {
				return "purchaseplan";
			} else if (application.getApplyType().equals(Application.APP_TYPE_PROJECT)) {
				return "project";
			} else if (application.getApplyType().equals(Application.APP_TYPE_PURCHASE)) {
				return "purchase";
			} else if (application.getApplyType().equals(Application.APP_TYPE_REQUESTMONEYENGINEER)) {
				return "requestmoney";
			} else if (application.getApplyType().equals(Application.APP_TYPE_REQUESTMONEYMATERIAL)) {
				return "requestmoney";
			} else if (application.getApplyType().equals(Application.APP_TYPE_BORROWMONEY)) {
				return "borrowmoney";
			} else if (application.getApplyType().equals(Application.APP_TYPE_EXPENSEOTHER)) {
				return "expense";
			} else if (application.getApplyType().equals(Application.APP_TYPE_EXPENSEMAN)) {
				return "expense";
			} else if (application.getApplyType().equals(Application.APP_TYPE_EXPENSEMATERIAL)) {
				return "expense";
			} else if (application.getApplyType().equals(Application.APP_TYPE_ADMIN)) {
				return "admin";
			}
		}
		return "contract";
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@Action(value = "/approve/delapp",
			results = {
						@Result(name = "success", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
			public String del() {

		ajaxResult = new AjaxResult();
		if (application != null && application.getId() != null) {
			try {
				// System.out.println(application.getApplyType() +
				// "-----------");
				approveService.deleteApp(application, this.getLoginUser());
				ajaxResult.setNavTabId("main");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_FORWARD);
			} catch (GiroException e) {
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("删除失败");
			}
		}
		return "success";
	}

	/**
	 * 审批
	 * 
	 * @return
	 */
	@Action(value = "/approve/approve",
			results = {
						@Result(name = "contract", location = "approvecontractapp", type = "chain"),
						@Result(name = "purchaseplan", location = "approvepurchaseplanapp", type = "chain"),
						@Result(name = "project", location = "approveprojectapp", type = "chain"),
						@Result(name = "purchase", location = "approvepurchaseapplyapp", type = "chain"),
						@Result(name = "requestmoney", location = "approverequestmoneyapp", type = "chain"),
						@Result(name = "borrowmoney", location = "approveborrowmoneyapp", type = "chain"),
						@Result(name = "expense", location = "approveexpenseapp", type = "chain"),
						@Result(name = "admin", location = "approveadminapp", type = "chain")
					}
			)
			public String approve() {

		if (application != null && application.getId() != null) {
			application = approveService.findById(application.getId());
			user = application.getUser();
			if (application.getApplyType().equals(Application.APP_TYPE_MATERIALCONTRACT)) {
				return "contract";
			} else if (application.getApplyType().equals(Application.APP_TYPE_MANCONTRACT)) {
				return "contract";
			} else if (application.getApplyType().equals(Application.APP_TYPE_PURCHASEPLAN)) {
				return "purchaseplan";
			} else if (application.getApplyType().equals(Application.APP_TYPE_PROJECT)) {
				return "project";
			} else if (application.getApplyType().equals(Application.APP_TYPE_PURCHASE)) {
				return "purchase";
			} else if (application.getApplyType().equals(Application.APP_TYPE_REQUESTMONEYENGINEER) ||
						application.getApplyType().equals(Application.APP_TYPE_REQUESTMONEYMATERIAL)) {
				return "requestmoney";
			} else if (application.getApplyType().equals(Application.APP_TYPE_BORROWMONEY)) {
				return "borrowmoney";
			} else if (application.getApplyType().equals(Application.APP_TYPE_EXPENSEOTHER)
					|| application.getApplyType().equals(Application.APP_TYPE_EXPENSEMATERIAL)
					|| application.getApplyType().equals(Application.APP_TYPE_EXPENSEMAN)) {
				return "expense";
			} else if (application.getApplyType().equals(Application.APP_TYPE_ADMIN)) {
				return "admin";
			}
		}
		return "contract";
	}

	/**
	 * 查看
	 * 
	 * @return
	 */
	@Action(value = "/approve/view",
			results = {
						@Result(name = "contract", location = "viewcontractapp", type = "chain"),
						@Result(name = "purchaseplan", location = "viewpurchaseplanapp", type = "chain"),
						@Result(name = "project", location = "viewprojectapp", type = "chain"),
						@Result(name = "purchase", location = "viewpurchaseapp", type = "chain"),
						@Result(name = "requestmoney", location = "viewrequestmoneyapp", type = "chain"),
						@Result(name = "borrowmoney", location = "viewborrowmoneyapp", type = "chain"),
						@Result(name = "expense", location = "viewexpenseapp", type = "chain"),
						@Result(name = "admin", location = "viewadminapp", type = "chain")
					}
			)
			public String view() {

		if (application != null && application.getId() != null) {
			application = approveService.findById(application.getId());
			user = application.getUser();
			if (application.getApplyType().equals(Application.APP_TYPE_MATERIALCONTRACT)) {
				return "contract";
			} else if (application.getApplyType().equals(Application.APP_TYPE_MANCONTRACT)) {
				return "contract";
			} else if (application.getApplyType().equals(Application.APP_TYPE_PURCHASEPLAN)) {
				return "purchaseplan";
			} else if (application.getApplyType().equals(Application.APP_TYPE_PROJECT)) {
				return "project";
			} else if (application.getApplyType().equals(Application.APP_TYPE_PURCHASE)) {
				return "purchase";
			} else if (application.getApplyType().equals(Application.APP_TYPE_REQUESTMONEYENGINEER) ||
						application.getApplyType().equals(Application.APP_TYPE_REQUESTMONEYMATERIAL)) {
				return "requestmoney";
			} else if (application.getApplyType().equals(Application.APP_TYPE_BORROWMONEY)) {
				return "borrowmoney";
			} else if (application.getApplyType().equals(Application.APP_TYPE_EXPENSEOTHER)
					|| application.getApplyType().equals(Application.APP_TYPE_EXPENSEMATERIAL)
					|| application.getApplyType().equals(Application.APP_TYPE_EXPENSEMAN)) {
				return "expense";
			}else if (application.getApplyType().equals(Application.APP_TYPE_ADMIN)) {
				return "admin";
			}
		}
		return "contract";
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@Action(value = "/approve/delapp",
			results = {
						@Result(name = "success", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
			public String delAppProject() {

		ajaxResult = new AjaxResult();
		if (application != null && application.getId() != null) {
			try {
				approveService.deleteApp(application, this.getLoginUser());
				ajaxResult.setNavTabId("main");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_FORWARD);
			} catch (GiroException e) {
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("删除失败");
			}
		}
		return "success";
	}

	public AjaxResult getAjaxResult() {

		return ajaxResult;
	}
}