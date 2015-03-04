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
import com.ydt.oa.entity.PaymentLog;
import com.ydt.oa.entity.PaymentOrder;
import com.ydt.oa.service.ApproveService;
import com.ydt.oa.service.PaymentOrderService;


/**
 * 财务管理Action
 * @author caochun
 *
 */
public class PaymentOrderAction extends PageAction {
	
	@Autowired
	private PaymentOrderService paymentOrderService;
	@Autowired
	private ApproveService approveService;
	private PaymentOrder paymentOrder;
	private PaymentLog paymentLog;
	private Pagination pagination;
	private Application application;

	private String formAction;
	private AjaxResult ajaxResult;

	
	
	
	
	public PaymentLog getPaymentLog() {
	
		return paymentLog;
	}


	
	public void setPaymentLog(PaymentLog paymentLog) {
	
		this.paymentLog = paymentLog;
	}


	public String getFormAction() {
	
		return formAction;
	}

	
	public void setFormAction(String formAction) {
	
		this.formAction = formAction;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public PaymentOrder getPaymentOrder() {
		return paymentOrder;
	}

	public void setPaymentOrder(PaymentOrder paymentOrder) {
		this.paymentOrder = paymentOrder;
	}

	public void setAjaxResult(AjaxResult ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}
	
	
	/**
	 * 列出需要审批的
	 * @return
	 */
	@Action(value = "/paymentorder/listapprove",
			results = { @Result(name = "success", location = "/payment/listapprove.jsp", type = "dispatcher")}
			)
	public String listApprove() {
		
		pagination = paymentOrderService.listApprove(getPageNum(), getNumPerPage());
	
		return "success";
	}
	
	/**
	 * 列出需要付款的
	 * @return
	 */
	@Action(value = "/paymentorder/listpay",
			results = { @Result(name = "success", location = "/payment/listpay.jsp", type = "dispatcher")}
			)
	public String listPay() {
		
		pagination = paymentOrderService.listPay(getPageNum(), getNumPerPage());
	
		return "success";
	}
	

	
	
	/**
	 * 列出已经付款的
	 * @return
	 */
	@Action(value = "/paymentorder/historypay",
			results = { @Result(name = "success", location = "/payment/listhistory.jsp", type = "dispatcher")}
			)
	public String historyList() {
		
		pagination = paymentOrderService.listAlreadyPay(getPageNum(), getNumPerPage());
	
		return "success";
	}
	
	/**
	 * 列出我的付款
	 * @return
	 */
	@Action(value = "/paymentorder/mylist",
			results = { @Result(name = "success", location = "/payment/mylist.jsp", type = "dispatcher")}
			)
	public String myList() {
		
		pagination = paymentOrderService.listMyPay(getLoginUser(),getPageNum(), getNumPerPage());
	
		return "success";
	}
	
	/**
	 * 付款审批
	 * @return
	 */
	@Action(value = "/paymentorder/approve", results = {
			@Result(name = "success", location = "/payment/approve.jsp", type = "dispatcher"),
			@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher") })
	public String approve() {
		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				paymentOrderService.approve(paymentOrder);
				ajaxResult.setNavTabId("main");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
			} catch (GiroException e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("审批失败");
			}
			return "save";
		} else {
			paymentOrder = paymentOrderService.findById(paymentOrder.getId());
		}
		return "success";
}
	
	/**
	 * 付款
	 * @return
	 */
	@Action(value = "/paymentorder/pay", results = {
			@Result(name = "success", location = "/payment/pay.jsp", type = "dispatcher"),
			@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher") })
	public String pay() {
		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				paymentLog.setUser(getLoginUser());
				paymentOrderService.pay(paymentLog);
				ajaxResult.setNavTabId("main");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
			} catch (GiroException e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("付款失败");
			}
			return "save";
		} else {
			
			if(paymentLog!=null && paymentLog.getId()!=null){
				paymentLog = paymentOrderService.findPaymentLogById(paymentLog.getId());
			}

			if(paymentLog!=null && paymentLog.getPaymentOrder().getId()!=null){
				paymentOrder = paymentOrderService.findById(paymentLog.getPaymentOrder().getId());
				paymentLog.setPaymentOrder(paymentOrder);
			}

		}
		return "success";
}
	
	/**
	 * 列出付款记录
	 * @return
	 */
	@Action(value = "/paymentorder/payloglist",
			results = { @Result(name = "success", location = "/payment/payloglist.jsp", type = "dispatcher")}
			)
	public String payLogList() {
		
		paymentOrder = paymentOrderService.findById(paymentOrder.getId());
	
		return "success";
	}
	


	public Pagination getPagination() {
		return pagination;
	}

	public AjaxResult getAjaxResult() {
	
		return ajaxResult;
	}

}