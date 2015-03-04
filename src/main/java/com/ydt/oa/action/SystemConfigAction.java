package com.ydt.oa.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import com.giro.common.action.BaseAction;
import com.ydt.oa.bean.AjaxResult;
import com.ydt.oa.entity.SystemParam;
import com.ydt.oa.service.SystemConfigService;


/**
 * 系统参数管理Action
 * @author caochun
 *
 */
public class SystemConfigAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(SystemConfigAction.class);

	@Autowired
	private SystemConfigService sysService;
	private String formAction;
	private AjaxResult ajaxResult;
	
	private List<SystemParam> ParamList;
	private SystemParam systemParam;

	

	
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

	
	public List<SystemParam> getParamList() {
	
		return ParamList;
	}

	
	public void setParamList(List<SystemParam> paramList) {
	
		ParamList = paramList;
	}

	
	public SystemParam getSystemParam() {
	
		return systemParam;
	}

	
	public void setSystemParam(SystemParam systemParam) {
	
		this.systemParam = systemParam;
	}

	/**
	 * 显示全部参数
	 * @return
	 */
	@Action(value = "/system/list",
			results = { @Result(name = "success", location = "/system/list.jsp", type = "dispatcher")}
			)
	public String list() {		
		
		ParamList = sysService.listParam();
		return "success";
	}

	/**
	 * 编辑参数
	 * @return
	 */
	@Action(value = "/system/edit",
			results = {
						@Result(name = "success", location = "/system/edit.jsp", type = "dispatcher"),
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
	public String edit() {
		if (formAction != null && formAction.equals("save")) {
			sysService.setParam(systemParam.getShowName(),systemParam.getParamName(), systemParam.getParamValue());
			ajaxResult = new AjaxResult();
			ajaxResult.setNavTabId("main");
			ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
			return "save";
		} else {
			
			if (systemParam != null && systemParam.getId() != null) {				
				systemParam = sysService.findParamById(systemParam.getId());
				logger.info(systemParam.getParamName());
			}else{
				systemParam = new SystemParam();
			}
			return "success";
		}
	}

	/**
	 * 删除参数
	 * @return
	 */
	@Action(value = "/system/del",
			results = {
						@Result(name = "success", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
	public String del() {

		ajaxResult = new AjaxResult();
		if (systemParam != null && systemParam.getId() != null) {
			sysService.delParam(systemParam.getId());
			ajaxResult.setNavTabId("main");
			ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_FORWARD);
		}
		return "success";
	}

}
