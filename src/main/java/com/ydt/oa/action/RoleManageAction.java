package com.ydt.oa.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;


import com.giro.common.action.PageAction;
import com.giro.common.dao.Pagination;
import com.ydt.oa.bean.AjaxResult;
import com.ydt.oa.entity.ApproveFlowConfig;
import com.ydt.oa.entity.OaAction;
import com.ydt.oa.service.ApproveService;
import com.ydt.oa.service.RoleManageService;


/**
 * 角色权限管理
 * @author caochun
 *
 */
public class RoleManageAction extends PageAction {
	
	@Autowired
	private RoleManageService roleManageService;
	@Autowired
	private ApproveService approveService;
	private List<String[]> appTypes;
	private String appType;
	private List<ApproveFlowConfig> flowConfigList;
	private Pagination pagination;
	private AjaxResult ajaxResult;
	private OaAction oaAction;
	private String formAction;
	private ApproveFlowConfig approveFlowConfig;

	
	
	
	public ApproveFlowConfig getApproveFlowConfig() {
	
		return approveFlowConfig;
	}



	
	public void setApproveFlowConfig(ApproveFlowConfig approveFlowConfig) {
	
		this.approveFlowConfig = approveFlowConfig;
	}



	public String getFormAction() {
	
		return formAction;
	}


	
	public void setFormAction(String formAction) {
	
		this.formAction = formAction;
	}


	/**
	 * 列出全部权限
	 * @return
	 */
	@Action(value = "/rolemanage/actionlist",
			results = { @Result(name = "success", location = "/system/actionlist.jsp", type = "dispatcher")}
			)
	public String actionList() {
		
		pagination = roleManageService.find(getPageNum(), getNumPerPage());
	
		return "success";
	}
	

	@Action(value = "/rolemanage/actionrul",
			results = { @Result(name = "success", location = "/system/urllist.jsp", type = "dispatcher")}
			)
	public String urlList() {
		
		oaAction = roleManageService.findById(oaAction.getId());
	
		return "success";
	}
	
	
	/**
	 * 列出全部权限
	 * @return
	 */
	@Action(value = "/rolemanage/apptype",
			results = { @Result(name = "success", location = "/system/apptype.jsp", type = "dispatcher")}
			)
	public String appTypes() {
		
		appTypes = new ArrayList<String[]>();
		
		String[] s = {"PURCHASEPLAN","采购计划申请"};
		
		appTypes.add(s);// 采购计划申请
		
		String[] s1 = {"PURCHASE","采购申请"};
		appTypes.add(s1);// 采购申请
		String[] s2 = {"CONTRACT","材料合同申请"};
		appTypes.add(s2);// 合同申请
		String[] s9 = {"MANCONTRACT","人工合同申请"};
		appTypes.add(s9);// 合同申请
		String[] s3 = {"PROJECT","项目申请"};
		appTypes.add(s3);	//项目申请
		String[] s4 = {"REQUESTMONEYMATERIAL","材料请款申请"};
		appTypes.add(s4);	//材料请款申请
		String[] s5 = {"REQUESTMONEYENGINEER","工程请款申请"};
		appTypes.add(s5);	//工程请款申请
		String[] s6 = {"BORROWMONEY","借款申请"};
		appTypes.add(s6);	//借款申请
		String[] s7 = {"EXPENSEMAN","人工费用报销申请"};
		appTypes.add(s7);	//餐费报销申请
		String[] s8 = {"EXPENSEOTHER","间接费用报销申请"};
		appTypes.add(s8);	//间接费用报销申请
		String[] s11 = {"EXPENSEMATERIAL","材料费用报销申请"};
		appTypes.add(s11);	//其他费用报销申请
		String[] s10 = {"ADMIN","行政申请"};
		appTypes.add(s10);	//行政申请

	
		return "success";
	}
	
	/**
	 * 列出全部权限
	 * @return
	 */
	@Action(value = "/rolemanage/flowlist",
			results = { @Result(name = "success", location = "/system/flowlist.jsp", type = "dispatcher")}
			)
	public String flowList() {
		
		flowConfigList = approveService.findApproveFlowConfigs(appType);

	
		return "success";
	}
	
	/**
	 * 编辑项目
	 * 
	 * @return
	 */
	@Action(value = "/rolemanage/modifyflow", results = {
			@Result(name = "success", location = "/system/editflow.jsp", type = "dispatcher"),
			@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher") })
	public String editProjectApp() {
		
		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				approveService.saveOrUpdateApproveFlowConfig(approveFlowConfig);
				ajaxResult.setNavTabId("main");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("数据保存失败");
			}
			return "save";
		} else if (approveFlowConfig != null && approveFlowConfig.getId() != null) {
			approveFlowConfig = approveService.findApproveFlowConfig(approveFlowConfig.getId());
	// logger.info(project.getName());
		}
		return "success";
}
	
	/**
	 * 删除参数
	 * @return
	 */
	@Action(value = "/rolemanage/delflow",
			results = {
						@Result(name = "success", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
	public String del() {

		ajaxResult = new AjaxResult();
		if (approveFlowConfig != null && approveFlowConfig.getId() != null) {
			approveService.delApproveFlowConfig(approveFlowConfig.getId());
			ajaxResult.setNavTabId("main");
			ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_FORWARD);
		}
		return "success";
	}
	




	
	public String getAppType() {
	
		return appType;
	}


	
	public void setAppType(String appType) {
	
		this.appType = appType;
	}


	
	public List<ApproveFlowConfig> getFlowConfigList() {
	
		return flowConfigList;
	}


	
	public void setFlowConfigList(List<ApproveFlowConfig> flowConfigList) {
	
		this.flowConfigList = flowConfigList;
	}


	public Pagination getPagination() {
		return pagination;
	}
	

	
	

	
	public AjaxResult getAjaxResult() {
	
		return ajaxResult;
	}


	
	public OaAction getOaAction() {
	
		return oaAction;
	}


	
	public void setOaAction(OaAction oaAction) {
	
		this.oaAction = oaAction;
	}


	
	public List<String[]> getAppTypes() {
	
		return appTypes;
	}


	
	public void setAppTypes(List<String[]> appTypes) {
	
		this.appTypes = appTypes;
	}


	



	


}

