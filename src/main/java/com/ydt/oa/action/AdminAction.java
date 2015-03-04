package com.ydt.oa.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.giro.common.action.PageAction;
import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.ydt.oa.bean.AjaxResult;
import com.ydt.oa.entity.Admin;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.ApproveFlow;
import com.ydt.oa.entity.Contract;
import com.ydt.oa.entity.PaymentOrder;
import com.ydt.oa.entity.RequestMoney;
import com.ydt.oa.entity.SystemParam;

import com.ydt.oa.entity.FileLog;
import com.ydt.oa.entity.Material;
import com.ydt.oa.interfaces.AdminAppInterface;
import com.ydt.oa.interfaces.RequestMoneyAppInterface;

import com.ydt.oa.service.AdminService;
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
public class AdminAction extends PageAction {
	public boolean buttonFlag = false;

	@Autowired
	private ApproveService approveService;
	@Autowired
	private FileService fileLogService;
	@Autowired
	private SystemConfigService configService;
	@Autowired
	private AdminAppInterface adminAppService;
	@Autowired
	private AdminService adminService;
	private Pagination pagination;
	private Application application;
	private Admin admin;
	private List<FileLog> fileLogList;
	private String formAction;
	private AjaxResult ajaxResult;
	private ApproveFlow approveFlow;
	private String fileHttpUrl;

	/**
	 * 编辑采购申请 当已经开始审批的时候则不能修改
	 * 
	 * @return
	 */
	@Action(value = "/approve/editadminapp",
			results = {
						@Result(name = "success", location = "/approve/editadminapp.jsp", type = "dispatcher"),
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
			public String editAdminApp() {

		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				adminAppService.updateAdminApp(application, admin, fileLogList, getLoginUser());
				//System.out.println(fileLogList.size()+"########");
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
				admin = adminService.findById(application.getApplyNo());
				fileLogList = fileLogService.find(Admin.FILE_TYPE, admin.getId());
				//System.out.println("editAdminapp  " + fileLogList.size());
				fileHttpUrl = configService.findParamValue(SystemParam.PARAM_FILE_HTTP_URL);
			}
			return "success";
		}
	}

	/**
	 * 审批
	 */
	@Action(value = "/approve/approveadminapp",
			results = {
						@Result(name = "success", location = "/approve/approveadminapp.jsp", type = "dispatcher"),
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher"),
					}
			)
			public String approveAdminApp() {

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
					admin = adminService.findById(application.getApplyNo());
					fileLogList = fileLogService.find(Admin.FILE_TYPE, admin.getId());
					System.out.println(fileLogList.size()+"****");
					fileHttpUrl = configService.findParamValue(SystemParam.PARAM_FILE_HTTP_URL);
			}
			return "success";
		}
	}

	@Action(value = "/approve/viewadminapp",
			results = {
						@Result(name = "success", location = "/admin/viewdetails.jsp", type = "dispatcher")
					}
			)
			public String viewAdminApp() {
		if (application != null && application.getId() != null) {
			application = approveService.findById(application.getId());
			admin =adminService.findById(application.getApplyNo());
			fileLogList = fileLogService.find(Admin.FILE_TYPE, admin.getId());
			fileHttpUrl = configService.findParamValue(SystemParam.PARAM_FILE_HTTP_URL);
		}

		return "success";
	}

	/**
	 * 显示请款申请列表
	 * 
	 * @return
	 */
	@Action(value = "/admin/adminlist",
			results = {
						@Result(name = "success", location = "/admin/list.jsp", type = "dispatcher")
					}
			)
			public String listAdmin() {

		pagination = adminService.list(getPageNum(), getNumPerPage());
		return "success";
	}

	public Admin getAdmin() {

		return admin;
	}

	public void setAdmin(Admin admin) {

		this.admin = admin;
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

	public List<FileLog> getFileLogList() {

		return fileLogList;
	}

	public void setFileLogList(List<FileLog> fileLogList) {

		this.fileLogList = fileLogList;
	}

	
	public boolean isButtonFlag() {
	
		return buttonFlag;
	}
}
