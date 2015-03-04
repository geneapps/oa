package com.ydt.oa.action;


import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.giro.common.action.PageAction;
import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.ydt.oa.bean.AjaxResult;

import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.ApproveFlow;

import com.ydt.oa.entity.Material;
import com.ydt.oa.entity.Project;
import com.ydt.oa.entity.PurchaseApply;
import com.ydt.oa.entity.PurchaseApplyDetails;
import com.ydt.oa.entity.User;

import com.ydt.oa.interfaces.PurchaseApplyAppInterface;
import com.ydt.oa.service.ApproveService;
import com.ydt.oa.service.PurchasePlanService;

import com.ydt.oa.service.PurchaseApplyService;

/**
 * 审批管理Action
 * 
 * @author caochun
 * 
 */
public class PurchaseApplyAction extends PageAction {

	private static final Logger logger = Logger.getLogger(PurchaseApplyAction.class);
	
	public boolean buttonFlag = false;
	
	public boolean isButtonFlag() {
	
		return buttonFlag;
	}

	@Autowired
	private ApproveService approveService;
	@Autowired
	private PurchaseApplyAppInterface purchaseApplyAppService;
	@Autowired
	private PurchasePlanService purchasePlanService;
	@Autowired
	private PurchaseApplyService purchaseApplyService;
//	@Autowired
//	private PurchaseApplyDao purchaseApplyDao;
//	private List<PurchaseApplyDetails> purchaseApplydetails;
	private Application application;
	private ApproveFlow approveFlow;
	private PurchaseApply purchaseApply;
	private Pagination pagination;
	private List<PurchaseApplyDetails> purchaseApplyList;
	private List<Material> materialList;
	private String formAction;
	private AjaxResult ajaxResult;
	private String number;
	private Project project;
	private Material material;
	private boolean flag; 
	private User user;

	
	
	
	
	public User getUser() {
	
		return user;
	}





	
	public void setUser(User user) {
	
		this.user = user;
	}





	public boolean isFlag() {
		return flag;
	}




	
	public void setFlag(boolean flag) {
	
		this.flag = flag;
	}






	
	public String getNumber() {
	
		return number;
	}





	
	public void setNumber(String number) {
	
		this.number = number;
	}





	public Material getMaterial() {
	
		return material;
	}


	
	public void setMaterial(Material material) {
	
		this.material = material;
	}


	public Project getProject() {
	
		return project;
	}

	
	public void setProject(Project project) {
	
		this.project = project;
	}
	

	
	public Application getApplication() {

		return application;
	}

	public AjaxResult getAjaxResult() {

		return ajaxResult;
	}

	public void setAjaxResult(AjaxResult ajaxResult) {

		this.ajaxResult = ajaxResult;
	}

	public String getFormAction() {

		return formAction;
	}

	public void setFormAction(String formAction) {

		this.formAction = formAction;
	}

	public List<PurchaseApplyDetails> getPurchaseApplyList() {

		return purchaseApplyList;
	}

	public void setPurchaseApplyList(List<PurchaseApplyDetails> purchaseApplyList) {

		this.purchaseApplyList = purchaseApplyList;
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

//	public List<PurchaseApplyDetails> getPurchaseApplydetails() {
//
//		return purchaseApplydetails;
//	}
//
//	public void setPurchaseApplydetails(List<PurchaseApplyDetails> purchaseApplydetails) {
//
//		this.purchaseApplydetails = purchaseApplydetails;
//	}

	public Pagination getPagination() {

		return pagination;
	}

	public void setPagination(Pagination pagination) {

		this.pagination = pagination;
	}

	public PurchaseApply getPurchaseApply() {

		return purchaseApply;
	}

	public void setPurchaseApply(PurchaseApply purchaseApply) {

		this.purchaseApply = purchaseApply;
	}


  /**
	 * 列出所有计划单 分页
	 * 
	 * @return
	 */
	
	 @Action(value = "/purchaseApply/ajaxselect", 
			 results = { 
			 @Result(name = "success", location = "/purchase/purchaseApply.jsp", type ="dispatcher")
      } ) 
	 public String ajaxselect() {
     System.out.println(project.getId()+"***");
		int number1=Integer.parseInt(number);
		if(purchaseApplyService.compare(project.getId(), material.getId(), number1)) {
			flag=true;
		}else {
			flag=false;
		}
		System.out.println(flag);
		return  "success";
	  }
	 
	/*	*//**
	 * 根据采购计划单查出明细
	 * 
	 * @return
	 */
	/*
	 * @Action(value = "/purchaseApplybyID/list", results = { @Result(name =
	 * "success", location = "/purchase/purchaseApplydetailsbyID.jsp", type =
	 * "dispatcher")} ) public String list() { purchaseApplydetails =
	 * purchaseApplyService.list(purchaseApply);
	 * 
	 * return "success"; }
	 */
	/*	
	*//**
	 * 列出我的采购单
	 * 
	 * @return
	 */
	@Action(value = "/purchaseapply/mylist", 
			results = { @Result(name = "success",location = "/purchase/mypurchaseapply.jsp", type = "dispatcher") 
			}
	)
	public String myList() {
		this.pagination = purchaseApplyService.listPurchaseApply(this.getPageNum(), this.getNumPerPage());
		return "success";
	}
	

	
	/**
	 * 查看采购单
	 * @return
	 */
	@Action(value = "/purchase/viewpurchaseapply", 
			results = { @Result(name = "success",location = "/purchase/viewpurchaseapply.jsp", type = "dispatcher") 
			}
	)
	public String view() {
		purchaseApply = purchaseApplyService.findById(purchaseApply.getId());
		return "success";
	}

	/**
	 * 编辑采购申请 当已经开始审批的时候则不能修改
	 * 
	 * @return
	 */
	@Action(value = "/approve/editpurchaseapplyapp",
			results = {
						@Result(name = "success", location = "/approve/editpurchaseapplyapp.jsp", type = "dispatcher"),
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
			public String editPurchaseApplyApp() {
		System.out.println(formAction+"@@@@@@");

		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				if (purchaseApplyList != null && materialList != null) {
					PurchaseApplyDetails detail = null;
					Material material = null;
					for (int i = 0; i < purchaseApplyList.size(); i++) {
						detail = purchaseApplyList.get(i);
						material = materialList.get(i);
						detail.copyFromMaterial(material);
					}
					purchaseApply.setPurchaseApplyDetails(purchaseApplyList);
				}
				purchaseApplyAppService.updatePurchaseApplyApp(application, purchaseApply, getLoginUser());
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
			if (application != null && application.getId() != null) {
				application = approveService.findById(application.getId());
				purchaseApply = purchaseApplyService.findById(application.getApplyNo());
			}
			return "success";
		}
	}

	/**
	 * 编辑采购申请 当已经开始审批的时候则不能修改
	 * 
	 * @return
	 */
	@Action(value = "/approve/approvepurchaseapplyapp",
			results = {
						@Result(name = "success", location = "/approve/approvepurchaseapplyapp.jsp", type = "dispatcher"),
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
			public String approvePurchaseApplyApp() {

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
			if (application != null && application.getId() != null) {
				application = approveService.findById(application.getId());
				buttonFlag = approveService.showButton(application.getId());
				purchaseApply = purchaseApplyService.findById(application.getApplyNo());
				System.out.println(purchaseApply.getInstruction()+"#################");

			}
			return "success";
		}
	}
	
	/**
	 * 采购单流程控制
	 * @return
	 */
	@Action(value = "/purchase/purchaseflow",
			results = {
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
			public String purchaseFlow() {

			ajaxResult = new AjaxResult();
			try {
				purchaseApplyService.submitExpense(purchaseApply);
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

	}
	
	/**
	 * 编辑采购申请 当已经开始审批的时候则不能修改
	 * 
	 * @return
	 */
	@Action(value = "/approve/viewpurchaseapp",
			results = {
						@Result(name = "success", location = "/approve/viewpurchaseapp.jsp", type = "dispatcher")
					}
			)
			public String viewPurchaseapplyApp() {


			if (application != null && application.getId() != null) {
				application = approveService.findById(application.getId());
				purchaseApply = purchaseApplyService.findById(application.getApplyNo());
			}
			return "success";
	}

}
