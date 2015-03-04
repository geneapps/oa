package com.ydt.oa.action;


import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;


import com.giro.common.action.BaseAction;
import com.giro.common.action.PageAction;
import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.ydt.oa.bean.AjaxResult;
import com.ydt.oa.dao.PurchasePlanDao;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.ApproveFlow;
import com.ydt.oa.entity.ContractDetails;
import com.ydt.oa.entity.Department;
import com.ydt.oa.entity.Material;
import com.ydt.oa.entity.PurchasePlan;
import com.ydt.oa.entity.PurchasePlanDetails;
import com.ydt.oa.entity.User;
import com.ydt.oa.interfaces.ContractAppInterface;
import com.ydt.oa.interfaces.PurchasePlanAppInterface;
import com.ydt.oa.service.ApproveService;
import com.ydt.oa.service.PurchasePlanDetailsService;
import com.ydt.oa.service.PurchasePlanService;
import com.ydt.oa.service.UserService;

/**
 * 审批管理Action
 * @author caochun
 *
 */
public class PurchasePlanAction extends PageAction {
	
	private static final Logger logger = Logger.getLogger(PurchasePlanAction.class);

	public boolean buttonFlag = false;
	
	public boolean isButtonFlag() {
	
		return buttonFlag;
	}
	@Autowired
	private ApproveService approveService;
	@Autowired
	private PurchasePlanAppInterface purchasePlanAppService;
	@Autowired
	private PurchasePlanDao purchasePlanDao;
	@Autowired
	private PurchasePlanService purchasePlanService;
   
	private List<PurchasePlanDetails> purchasePlandetails;
	private Application application;
	private ApproveFlow approveFlow;
	private PurchasePlan purchasePlan;
	private Pagination pagination;
	private List<PurchasePlanDetails> purchasePlanList;
	private List<Material> materialList;
	private String formAction;
	private AjaxResult ajaxResult;
	private User user;
	private Department department;
	
	public User getUser() {
	
		return user;
	}

	public void setUser(User user) {
	
		this.user = user;
	}
	
	public Department getDepartment() {
	
		return department;
	}
	
	public void setDepartment(Department department) {
	
		this.department = department;
	}

	
	public Application getApplication() {
		return application;
	}

  
  
   


	public String getFormAction() {
		return formAction;
	}






	public void setFormAction(String formAction) {
		this.formAction = formAction;
	}






	public List<PurchasePlanDetails> getPurchasePlanList() {
		return purchasePlanList;
	}






	public void setPurchasePlanList(List<PurchasePlanDetails> purchasePlanList) {
		this.purchasePlanList = purchasePlanList;
	}






	public List<Material> getMaterialList() {
		return materialList;
	}






	public void setMaterialList(List<Material> materialList) {
		this.materialList = materialList;
	}






	public void setApplication(Application application) {
		this.application = application;
	}






	public ApproveFlow getApproveFlow() {
		return approveFlow;
	}






	public void setApproveFlow(ApproveFlow approveFlow) {
		this.approveFlow = approveFlow;
	}






	public List<PurchasePlanDetails> getPurchasePlandetails() {
		return purchasePlandetails;
	}






	public void setPurchasePlandetails(List<PurchasePlanDetails> purchasePlandetails) {
		this.purchasePlandetails = purchasePlandetails;
	}






	public Pagination getPagination() {
		return pagination;
	}






	public AjaxResult getAjaxResult() {
		return ajaxResult;
	}






	public void setAjaxResult(AjaxResult ajaxResult) {
		this.ajaxResult = ajaxResult;
	}






	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}






	public PurchasePlan getPurchasePlan() {
		return purchasePlan;
	}






	public void setPurchasePlan(PurchasePlan purchasePlan) {
		this.purchasePlan = purchasePlan;
	}





	/**
	 * 列出所有计划单   分页
	 * @return
	 */
	@Action(value = "/purchaseplan/list",
			results = { @Result(name = "success", location = "/purchase/purchaseplanlist.jsp", type = "dispatcher")}
			)
	public String listByPage() {
		
		pagination = purchasePlanService.list(this.getPageNum(),this.getNumPerPage());
		return "success";
	}

	/**
	 * 显示具体的采购计划单信息
	 */
	@Action(value = "/purchaseplan/view",
			results = { @Result(name = "success", location = "/purchase/purchaseplanbyID.jsp", type = "dispatcher")}
			)
	public String view() {
		
		if(purchasePlan != null || purchasePlan.getId() != null ){
			purchasePlan = purchasePlanService.findById(purchasePlan.getId());
			application = approveService.findByApplyNo("PURCHASEPLAN", purchasePlan.getId());
		}
		return "success";
	}
	

	/**
	 * 根据采购计划单查出明细
	 * @return
	 */
	@Action(value = "/purchaseplanbyID/list",
			results = { @Result(name = "success", location = "/purchase/purchaseplandetailsbyID.jsp", type = "dispatcher")}
			)
	public String list() {
		purchasePlandetails =   purchasePlanService.list(purchasePlan);
		return "success";
	}
/*	
	*//**
	 * 回到我的申请页面
	 * @return
	 *//*
	@Action(value = "/purchaseplan",
			results = { @Result(name = "success", location = "/approve/list.jsp", type = "dispatcher")}
			)
	public String save() {	
		System.out.println(purchasePlan.getApplication()+"****");
		purchasePlanService.savePurchasePlan(purchasePlan);
		return "success";
	}
*/

	/**
	 * 编辑采购申请 当已经开始审批的时候则不能修改
	 * 
	 * @return
	 */
	@Action(value = "/approve/editpurchaseplanapp",
			results = {
						@Result(name = "success", location = "/approve/editpurchaseplanapp.jsp", type = "dispatcher"),
						@Result(name = "save", location =  "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
	public String editPurchasePlanApp() {
		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				
				if(purchasePlanList!=null && materialList!=null){
					PurchasePlanDetails detail =  null;
					Material material = null;
					
					for(int i=0;i<purchasePlanList.size();i++){
						detail = purchasePlanList.get(i);
						material = materialList.get(i);
						//System.out.println("--------");
						//System.out.println(detail.getId());
						//System.out.println(material.getId());
						detail.copyFromMaterial(material);					
					}
					purchasePlan.setPurchasePlanDetails(purchasePlanList);
					}
				
				purchasePlanAppService.updatePurchasePlanApp(application, purchasePlan, getLoginUser());
				ajaxResult.setNavTabId("main");
				
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
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
			user = this.getLoginUser();
			department = user.getDepartment();
			System.out.println(user.getRealName()+"@@@@@@@@@");
			System.out.println(department.getDepName()+"@@@@@@@@@@");
			if (application != null && application.getId()!= null) {
				
				application = approveService.findById(application.getId());
				purchasePlan = purchasePlanService.findById(application.getApplyNo());
				//System.out.println(application.getApplyNo());
				// photoHttpUrl =
				// sysService.findParamValue(Param.PARAM_PHOTO_HTTP_URL);
			}
			return "success";
		}
	}

	/**
	 * 编辑采购申请 当已经开始审批的时候则不能修改
	 * 
	 * @return
	 */
	@Action(value = "/approve/approvepurchaseplanapp",
			results = {
						@Result(name = "success", location = "/approve/approvepurchaseplanapp.jsp", type = "dispatcher"),
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
			public String approvePurchasePlanApp() {

		if(formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				approveService.approve(application, approveFlow ,getLoginUser());
				ajaxResult.setNavTabId("main");
				// ajaxResult.setRel("approveRel");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
				// ajaxResult.setForwardUrl("/bolan/user/list.action?pageNum="+this.getPageNum()+"&numPerPage="+this.getNumPerPage());
			}
			 catch (GiroException e) {
			 ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
			 ajaxResult.setMessage(e.getMessage());
			 }
			catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("数据保存失败");
			}
			return "save";
		} else if (formAction != null && formAction.equals("end")) {
			ajaxResult = new AjaxResult();
			try {
				approveService
						.approve(application, approveFlow, getLoginUser(),true);
				
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
		}else {
			if(application != null && application.getId() != null) {
				application = approveService.findById(application.getId());
				buttonFlag = approveService.showButton(application.getId());
				purchasePlan = purchasePlanService.findById(application.getApplyNo());
				purchasePlandetails= purchasePlanDao.list(purchasePlan);
			}
			return "success";
		}
	}
	/**
	 * 编辑采购申请 当已经开始审批的时候则不能修改
	 * 
	 * @return
	 */
	@Action(value = "/approve/viewpurchaseplanapp",
			results = {
						@Result(name = "success", location = "/approve/viewpurchaseplanapp.jsp", type = "dispatcher")
					}
			)
			public String viewPurchaseplanApp() {


			if (application != null && application.getId() != null) {
				application = approveService.findById(application.getId());
				purchasePlan = purchasePlanService.findById(application.getApplyNo());
				purchasePlandetails= purchasePlanDao.list(purchasePlan);
			}
			return "success";
	}

}

