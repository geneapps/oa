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
import com.ydt.oa.entity.PurchaseApplyDetails;
import com.ydt.oa.entity.PurchasePlan;
import com.ydt.oa.entity.PurchasePlanDetails;
import com.ydt.oa.entity.SystemParam;
import com.ydt.oa.entity.User;
import com.ydt.oa.service.ApproveService;
import com.ydt.oa.service.PurchaseApplyDetailsService;
import com.ydt.oa.service.PurchasePlanDetailsService;
import com.ydt.oa.service.PurchasePlanService;
import com.ydt.oa.service.UserService;

/**
 * 采购管理Action
 * @author caochun
 *
 */
public class PurchaseApplyDetailsAction extends PageAction {
	
	private static final Logger logger = Logger.getLogger(PurchaseApplyDetailsAction.class);

	@Autowired
	private PurchaseApplyDetailsService purchaseApplydetailsService;
   
	private Pagination pagination;
	private List<PurchaseApplyDetails> purchaseApplydetails;
	
	private PurchaseApplyDetails purchaseApplyDetails;
	
	
	private String formAction;
	private AjaxResult ajaxResult;
	
   

	public String getFormAction() {
		return formAction;
	}






	public List<PurchaseApplyDetails> getPurchaseApplydetails() {
		return purchaseApplydetails;
	}






	public void setPurchaseApplydetails(
			List<PurchaseApplyDetails> purchaseApplydetails) {
		this.purchaseApplydetails = purchaseApplydetails;
	}






	public PurchaseApplyDetails getPurchaseApplyDetails() {
		return purchaseApplyDetails;
	}






	public void setPurchaseApplyDetails(PurchaseApplyDetails purchaseApplyDetails) {
		this.purchaseApplyDetails = purchaseApplyDetails;
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








	public Pagination getPagination() {
		return pagination;
	}





	/**
	 * 列出所有详细清单    分页
	 * @return
	 */
	@Action(value = "/purchaseapplyDetails/list",
			results = { @Result(name = "success", location = "/purchase/purchaseapplydetails.jsp", type = "dispatcher")}
			)
	public String listByPage() {
		
		pagination = purchaseApplydetailsService.list(this.getPageNum(),this.getNumPerPage());
		return "success";
	}


	/**
	 * 列出所有采购清单
	 * @return
	 */
	@Action(value = "/purchaseapply/list",
			results = { @Result(name = "success", location = "/purchase/purchaseapply.jsp", type = "dispatcher")}
			)
	public String list() {
		
		purchaseApplydetails =   purchaseApplydetailsService.list();
	//System.out.println(purchasePlandetails.size());
		return "success";
	}



	


	/**
	 * 删除详细清单
	 * @return
	 */
	@Action(value = "/purchaseapplyDetails/del",
			results = {
						@Result(name = "success", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
	public String del() {

		ajaxResult = new AjaxResult();
		if (purchaseApplyDetails != null &&purchaseApplyDetails.getId() != null) {
			purchaseApplydetailsService.delPurchaseApplyDetails(purchaseApplyDetails);
		    ajaxResult.setNavTabId("main");
			ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_FORWARD);
		}
		return "success";
	}
	
	
	





}

