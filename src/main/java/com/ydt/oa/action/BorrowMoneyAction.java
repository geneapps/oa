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
import com.ydt.oa.entity.BorrowMoney;
import com.ydt.oa.entity.RequestMoney;
import com.ydt.oa.entity.SystemParam;
import com.ydt.oa.interfaces.BorrowMoneyAppInterface;

import com.ydt.oa.service.ApproveService;
import com.ydt.oa.service.BorrowMoneyService;
/**
 * 审批管理Action
 * 
 * @author caochun
 * 
 */
public class BorrowMoneyAction extends PageAction {
	
	public boolean buttonFlag = false;
	@Autowired
	private ApproveService approveService;

	@Autowired
	private BorrowMoneyAppInterface borrowMoneyAppService;
	@Autowired
	private BorrowMoneyService borrowMoneyService;
	private Pagination pagination;
	private Application application;
	private BorrowMoney borrowMoney;
	private String formAction;
	private AjaxResult ajaxResult;
	private ApproveFlow approveFlow; 

	public String getFormAction() {
		return formAction;
	}

	public ApproveFlow getApproveFlow() {

		return approveFlow;
	}
	public BorrowMoney getBorrowMoney() {
		return borrowMoney;
	}

	public void setBorrowMoney(BorrowMoney borrowMoney) {
		this.borrowMoney = borrowMoney;
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
	@Action(value = "/approve/editborrowmoneyapp", results = {
			@Result(name = "success", location = "/approve/editborrowmoneyapp.jsp", type = "dispatcher"),
			@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher") })
	public String editBorrowMoneyApp() {
		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				borrowMoneyAppService.updateBorrowMoneyApp(application,
						borrowMoney, getLoginUser());
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
				borrowMoney = borrowMoneyService.findById(application
						.getApplyNo());
				// System.out.println("))))))))))))))))))0applyNo="+application.getApplyNo());
				// System.out.println("))))))))))))))))))0applyNo="+borrowMoney.getBorrowMoney());
				// fileLogList = fileLogService.find(BorrowMoney.FILE_TYPE,
				// BorrowMoney.getId());
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
	@Action(value = "/approve/approveborrowmoneyapp", results = {
			@Result(name = "success", location = "/approve/approveborrowmoneyapp.jsp", type = "dispatcher"),
			@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher") })
	public String approveBorrowMoneyApp() {

		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				approveService
						.approve(application, approveFlow, getLoginUser());
				
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
		}else if (formAction != null && formAction.equals("end")) {
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
		}
		else {
			if (application != null && application.getId() != null) {
				application = approveService.findById(application.getId());
				buttonFlag = approveService.showButton(application.getId());

				borrowMoney = borrowMoneyService.findById(application
						.getApplyNo());

			}
			return "success";
		}
	}
	/**
	 * 付款
	 */
	@Action(value = "/financial/editborrowmoneypayment",
			results = {
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher"),
						@Result(name = "payment", location = "/payment/borrowmoney.jsp", type = "dispatcher")
					}
			)
			public String editBorrowMoneyPayment() {
		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				borrowMoneyAppService.updateBorrowMoneyApp(application, borrowMoney, getLoginUser());
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
		}  else {
			if (application != null && application.getId() != null) {
				application = approveService.findById(application.getId());
				borrowMoney = borrowMoneyService.findById(application.getApplyNo());  
					return "payment";
				}
			}
			return "success";
		}
	/**
	 * 编辑采购申请 当已经开始审批的时候则不能修改
	 * 
	 * @return
	 */
	@Action(value = "/approve/viewborrowmoneyapp",
			results = {
						@Result(name = "success", location = "/approve/viewborrowmoneyapp.jsp", type = "dispatcher")
					}
			)
			public String viewBorrowMoneyApp() {
			if (application != null && application.getId() != null) {
				System.out.println(application.getId()+"********");
				application = approveService.findById(application.getId());
				System.out.println(application.getApplyNo()+"********");
				borrowMoney = borrowMoneyService.findById(application.getApplyNo());  
			}
			return "success";
	}
	/**
	 * 显示请款申请列表
	 * 
	 * @return
	 */
	@Action(value = "/borrowmoney/borrowmoneylist", results = { @Result(name = "success", location = "/borrowmoney/list.jsp", type = "dispatcher") })
	public String listBorrowMoney() {

		pagination = borrowMoneyService.list(getPageNum(), getNumPerPage());
		return "success";
	}

	/**
	 * 根据编号显示详细
	 * 
	 */
	@Action(value = "/borrowmoney/view",results = {
			@Result(name = "success" , location ="/borrowmoney/viewdetails.jsp",type="dispatcher")
		}
	)
	public String listBorrowMoneyByID() {
		
		if(borrowMoney != null && borrowMoney.getId() != null){
			
			borrowMoney = borrowMoneyService.findById(borrowMoney.getId());
			application = approveService.findByApplyNo(Application.APP_TYPE_BORROWMONEY, borrowMoney.getId());
		}
		
		return "success";
	}
	

	public AjaxResult getAjaxResult() {

		return ajaxResult;
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

	
	public boolean isButtonFlag() {
	
		return buttonFlag;
	}

	

}
