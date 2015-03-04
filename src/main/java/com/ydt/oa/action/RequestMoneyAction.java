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
import com.ydt.oa.entity.Expense;
import com.ydt.oa.entity.PaymentOrder;
import com.ydt.oa.entity.RequestMoney;
import com.ydt.oa.entity.SystemParam;

import com.ydt.oa.entity.FileLog;
import com.ydt.oa.entity.Material;
import com.ydt.oa.interfaces.RequestMoneyAppInterface;

import com.ydt.oa.service.ApproveService;
import com.ydt.oa.service.PaymentOrderService;
import com.ydt.oa.service.RequestMoneyService;
import com.ydt.oa.service.FileService;
import com.ydt.oa.service.SystemConfigService;

/**
 * 审批管理Action
 * 
 * @author caochun
 * 
 */
public class RequestMoneyAction extends PageAction {

	public boolean buttonFlag = false;

	public boolean isButtonFlag() {

		return buttonFlag;
	}

	@Autowired
	private ApproveService approveService;
	@Autowired
	private FileService fileLogService;
	@Autowired
	private SystemConfigService configService;
	@Autowired
	private RequestMoneyAppInterface requestMoneyAppService;
	@Autowired
	private RequestMoneyService requestMoneyService;
	private Pagination pagination;
	private Application application;
	private RequestMoney requestMoney;
	private List<FileLog> fileLogList;
	private String formAction;
	private AjaxResult ajaxResult;
	private ApproveFlow approveFlow;
	private String fileHttpUrl;
	@Autowired
	private PaymentOrderService paymentOrderService;
	private PaymentOrder paymentOrder;

	public PaymentOrder getPaymentOrder() {

		return paymentOrder;
	}

	public void setPaymentOrder(PaymentOrder paymentOrder) {

		this.paymentOrder = paymentOrder;
	}

	public String getFileHttpUrl() {

		return fileHttpUrl;
	}

	public void setFileHttpUrl(String fileHttpUrl) {

		this.fileHttpUrl = fileHttpUrl;
	}

	public String getFormAction() {

		return formAction;
	}

	public ApproveFlow getApproveFlow() {

		return approveFlow;
	}

	public void setApproveFlow(ApproveFlow approveFlow) {

		this.approveFlow = approveFlow;
	}

	public Application getApplication() {

		return application;
	}

	public void setApplication(Application application) {

		this.application = application;
	}

	public Pagination getPagination() {

		return pagination;
	}

	/**
	 * 编辑采购申请 当已经开始审批的时候则不能修改
	 * 
	 * @return
	 */
	@Action(value = "/approve/editrequestmoneyapp",
			results = {
						@Result(name = "success", location = "/approve/editrequestmoneyapp.jsp", type = "dispatcher"),
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
			public String editRequestMoneyApp() {

		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				// System.out.println(requestMoney.getRequestType()+"类型");
				requestMoneyAppService.updateRequestMoneyApp(application, requestMoney, fileLogList, getLoginUser());
				ajaxResult.setNavTabId("main");
				// ajaxResult.setRel("approveRel");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
				// ajaxResult.setForwardUrl("/bolan/user/list.action?pageNum="+this.getPageNum()+"&numPerPage="+this.getNumPerPage());
			} catch (GiroException e) {
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("数据保存失败");
			}
			return "save";
		} else {
			if (application != null && application.getId() != null) {
				application = approveService.findById(application.getId());
				requestMoney = requestMoneyService.findById(application.getApplyNo());
				// System.out.println("))))))))))))))))))0applyNo="+application.getApplyNo());
				// System.out.println("))))))))))))))))))0applyNo="+requestMoney.getRequestMoney());
				fileLogList = fileLogService.find(RequestMoney.FILE_TYPE, requestMoney.getId());
				// photoHttpUrl =
				fileHttpUrl = configService.findParamValue(SystemParam.PARAM_FILE_HTTP_URL);
			} else {
				application = new Application();
				application.setApplyType(Application.APP_TYPE_REQUESTMONEYENGINEER);
			}
			return "success";
		}
	}

	/**
	 * 付款 请款
	 */
	@Action(value = "/financial/editrequestmoneypayment",
			results = {
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher"),
						@Result(name = "payment", location = "/payment/requestmoney.jsp", type = "dispatcher")
					}
			)
			public String editRequestMoneyPayment() {

		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				paymentOrder.setActualAmount(requestMoney.getRequestMoney());
				paymentOrder.setUser(getLoginUser());
				paymentOrder.setApplication(application);
				paymentOrder.setDescription(requestMoney.getReason());
				paymentOrder.setTitle(application.getTitle());
				paymentOrder.setAccount(requestMoney.getAccount());
				paymentOrder.setContractNo(requestMoney.getContractNo());
				paymentOrder.setPayee(requestMoney.getPayee());
				paymentOrderService.savePaymentOrder(paymentOrder);
				ajaxResult.setNavTabId("main");
				// ajaxResult.setRel("approveRel");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
				// ajaxResult.setForwardUrl("/bolan/user/list.action?pageNum="+this.getPageNum()+"&numPerPage="+this.getNumPerPage());
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("数据保存失败");
			}
			return "save";
		} else {
			if (application != null && application.getId() != null) {
				application = approveService.findById(application.getId());
				requestMoney = requestMoneyService.findById(application.getApplyNo());
				return "payment";
			}
		}
		return "success";
	}

	/**
	 * 审批
	 * 
	 * @return
	 */
	@Action(value = "/approve/approverequestmoneyapp",
			results = {
						@Result(name = "success", location = "/approve/approverequestmoneyapp.jsp", type = "dispatcher"),
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher"),
						@Result(name = "payment", location = "/payment/requestmoney.jsp", type = "dispatcher")
					}
			)
			public String approveRequestMoneyApp() {

		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				approveService.approve(application, approveFlow, getLoginUser());
				ajaxResult.setNavTabId("main");
				// ajaxResult.setRel("approveRel");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
				// ajaxResult.setForwardUrl("/bolan/user/list.action?pageNum="+this.getPageNum()+"&numPerPage="+this.getNumPerPage());
			} catch (GiroException e) {
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("数据保存失败");
			}
			return "save";
		} else if (formAction != null && formAction.equals("end")) {
			ajaxResult = new AjaxResult();
			try {
				approveService
						.approve(application, approveFlow, getLoginUser(), true);
				ajaxResult.setNavTabId("main");
				// ajaxResult.setRel("approveRel");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
				// ajaxResult.setForwardUrl("/bolan/user/list.action?pageNum="+this.getPageNum()+"&numPerPage="+this.getNumPerPage());
			} catch (GiroException e) {
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("数据保存失败");
			}
			return "save";
		} else {
			if (application != null && application.getId() != null) {
				application = approveService.findById(application.getId());
				buttonFlag = approveService.showButton(application.getId());
				if (application.getStatus() != 1) {
					requestMoney = requestMoneyService.findById(application.getApplyNo());
					fileLogList = fileLogService.find(RequestMoney.FILE_TYPE, requestMoney.getId());
					fileHttpUrl = configService.findParamValue(SystemParam.PARAM_FILE_HTTP_URL);
					return "success";
				} else if (application.getStatus() == 1) {
					requestMoney = requestMoneyService.findById(application.getApplyNo());
					return "payment";
				}
			}
			return "success";
		}
	}

	/**
	 * 编辑采购申请 当已经开始审批的时候则不能修改
	 * 
	 * @return
	 */
	@Action(value = "/approve/viewrequestmoneyapp",
			results = {
						@Result(name = "success", location = "/approve/viewrequestmoneyapp.jsp", type = "dispatcher")
					}
			)
			public String viewRequestMoneyApp() {

		if (application != null && application.getId() != null) {
			application = approveService.findById(application.getId());
			requestMoney = requestMoneyService.findById(application.getApplyNo());
			fileLogList = fileLogService.find(RequestMoney.FILE_TYPE, requestMoney.getId());
			fileHttpUrl = configService.findParamValue(SystemParam.PARAM_FILE_HTTP_URL);
		}
		return "success";
	}

	/**
	 * 显示请款申请列表
	 * 
	 * @return
	 */
	@Action(value = "/requestmoney/requestmoneylist",
			results = {
						@Result(name = "success", location = "/requestmoney/list.jsp", type = "dispatcher")
					}
			)
			public String listRequestMoney() {

		pagination = requestMoneyService.list(getPageNum(), getNumPerPage());
		return "success";
	}

	/**
	 * 显示请款某个申请的详细信息
	 * 
	 * @return
	 */
	@Action(value = "/requestmoney/view",
			results = {
						@Result(name = "success", location = "/requestmoney/viewdetails.jsp", type = "dispatcher")
					}
			)
			public String listRequestById() {

		if (requestMoney != null && requestMoney.getId() != null) {
			requestMoney = requestMoneyService.findById(requestMoney.getId());
			application = approveService.findByApplyNo(requestMoney.getRequestType(), requestMoney.getId());
			fileLogList = fileLogService.find(RequestMoney.FILE_TYPE, requestMoney.getId());
			fileHttpUrl = configService.findParamValue(SystemParam.PARAM_FILE_HTTP_URL);
		}
		return "success";
	}

	public AjaxResult getAjaxResult() {

		return ajaxResult;
	}

	public RequestMoney getRequestMoney() {

		return requestMoney;
	}

	public void setRequestMoney(RequestMoney requestMoney) {

		this.requestMoney = requestMoney;
	}

	public void setPagination(Pagination pagination) {

		this.pagination = pagination;
	}

	public void setAjaxResult(AjaxResult ajaxResult) {

		this.ajaxResult = ajaxResult;
	}

	public void setFormAction(String formAction) {

		this.formAction = formAction;
	}

	public List<FileLog> getFileLogList() {

		return fileLogList;
	}

	public void setFileLogList(List<FileLog> fileLogList) {

		this.fileLogList = fileLogList;
	}
}
