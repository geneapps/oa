package com.ydt.oa.action;


import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;


import com.giro.common.action.PageAction;
import com.giro.common.dao.Pagination;
import com.ydt.oa.bean.AjaxResult;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.ApproveFlow;
import com.ydt.oa.entity.PurchasePlan;
import com.ydt.oa.entity.PurchasePlanDetails;
import com.ydt.oa.entity.SystemParam;
import com.ydt.oa.entity.User;
import com.ydt.oa.service.ApproveService;
import com.ydt.oa.service.PurchasePlanDetailsService;
import com.ydt.oa.service.PurchasePlanService;
import com.ydt.oa.service.UserService;

/**
 * 审批管理Action
 * @author caochun
 *
 */
public class PurchasePlanDetailsAction extends PageAction {
	
	private static final Logger logger = Logger.getLogger(UserAction.class);

	@Autowired
	private PurchasePlanDetailsService purchasePlandetailsService;
   
	private Pagination pagination;
	private List<PurchasePlanDetails> purchasePlandetails;
	
	private PurchasePlanDetails purchasePlanDetails;
	
	
	private String formAction;
	private AjaxResult ajaxResult;
	
   

	public String getFormAction() {
		return formAction;
	}






	public void setFormAction(String formAction) {
		this.formAction = formAction;
	}






	public AjaxResult getAjaxResult() {
		return ajaxResult;
	}






	public void setAjaxResult(AjaxResult ajaxResult) {
		this.ajaxResult = ajaxResult;
	}






	public PurchasePlanDetails getPurchasePlanDetails() {
		return purchasePlanDetails;
	}






	public void setPurchasePlanDetails(PurchasePlanDetails purchasePlanDetails) {
		this.purchasePlanDetails = purchasePlanDetails;
	}



	public Pagination getPagination() {
		return pagination;
	}



	public List<PurchasePlanDetails> getPurchasePlandetails() {
		return purchasePlandetails;
	}



	public void setPurchasePlandetails(List<PurchasePlanDetails> purchasePlandetails) {
		this.purchasePlandetails = purchasePlandetails;
	}





	
	/**
	 * 编辑详细清单
	 * @return
	 */
	@Action(value = "/purchasePlanDetails/edit",
			results = {
						@Result(name = "success", location = "/purchase/edit.jsp", type = "dispatcher"),
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
	public String edit() {
		if (formAction != null && formAction.equals("save")) {
			System.out.println(purchasePlanDetails.getId()+"*****");
			purchasePlandetailsService.editPurchasePlanDetails(purchasePlanDetails);
			
			ajaxResult = new AjaxResult();
			ajaxResult.setNavTabId("main");
			ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
			return "save";
		} 
		else {	if(purchasePlanDetails!=null&&purchasePlanDetails.getId()!=null) {
				purchasePlanDetails = purchasePlandetailsService.findDetailsById(purchasePlanDetails.getId());
		} 
			return "success";
		}
	}

	/**
	 * 删除详细清单
	 * @return
	 */
	@Action(value = "/purchasePlanDetails/del",
			results = {
						@Result(name = "success", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
	public String del() {

		ajaxResult = new AjaxResult();
		if (purchasePlanDetails != null &&purchasePlanDetails.getId() != null) {
			purchasePlandetailsService.delPurchasePlanDetails(purchasePlanDetails);
		    ajaxResult.setNavTabId("main");
			ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_FORWARD);
		}
		return "success";
	}
	
	
	





}

