package com.ydt.oa.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.tomcat.util.threads.ThreadPool.ControlRunnable;
import org.springframework.beans.factory.annotation.Autowired;

import com.giro.common.action.PageAction;
import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.giro.common.util.StringUtils;
import com.ydt.oa.bean.AjaxResult;
import com.ydt.oa.entity.Contractor;
import com.ydt.oa.entity.Material;
import com.ydt.oa.entity.Project;
import com.ydt.oa.service.ContractorService;
import com.ydt.oa.service.ProjectService;


/**
 * 供货商Action
 * @author Cruise
 *
 */
public class ContractorAction extends PageAction {
	
	@Autowired
	private ContractorService contractorService;
	@Autowired
	private ProjectService projectService;
	private Pagination pagination;
	private String formAction;
	private AjaxResult ajaxResult;
	private Contractor contractor;
	private List<Project> projectList;
	private String id;
	private List<Contractor> contractorList;
	private String keywords;
	
	
	
	
	
	public List<Contractor> getContractorList() {
	
		return contractorList;
	}



	
	public void setContractorList(List<Contractor> contractorList) {
	
		this.contractorList = contractorList;
	}



	
	public String getKeywords() {
	
		return keywords;
	}



	
	public void setKeywords(String keywords) {
	
		this.keywords = keywords;
	}



	public String getId() {
	
		return id;
	}


	
	public void setId(String id) {
	
		this.id = id;
	}


	public List<Project> getProjectList() {
	
		return projectList;
	}

	
	public void setProjectList(List<Project> projectList) {
	
		this.projectList = projectList;
	}

	/**
	 * 显示分包商列表
	 * @return 
	 */
	@Action(value = "/contractor/contractorlist",
			results = {
						@Result(name = "success", location = "/contractor/list.jsp", type = "dispatcher")
					}
			)
	public String listContractor() {
				
		pagination =  contractorService.list(getPageNum() ,getNumPerPage() );
		return "success";
	}
	
/**
	 * 显示分包商所对应的工人列表
	 * @return 
	 */
	@Action(value = "/contractor/viewWorker",
			results = {
			@Result(name = "success", location = "/contractor/viewWorker.jsp", type = "dispatcher")
	}
	)
	public String listWorker() {
		
		if(contractor != null && (!StringUtils.isNull(contractor.getId()) )){		
			pagination = contractorService.findWorkerByContractor(contractor.getId(), getPageNum(), getNumPerPage());
		}
		return "success";
	}

/**
	 * 编辑分包商
	 * @return
	 */
	@Action(value = "/contractor/editContractor",
			results = {
						@Result(name = "success", location = "/contractor/editContractor.jsp", type = "dispatcher"),
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
	public String edit() {
		
		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			
			try {
				contractorService.updateContractor(contractor,id);
				ajaxResult.setNavTabId("main");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
				
			} catch (Exception e) {
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("数据保存失败");		
			}
			
			return "save";
			
		} else {
			if (contractor != null && contractor.getId() != null) {
				projectList = projectService.queryProjects();
				contractor = contractorService.findByContractorId(contractor.getId());
			}
			return "success";
		}		
	}
	
	/**
	 * 分包商查找
	 * @return
	 */
	@Action(value = "/common/contractorlookup",
			results = {
						@Result(name = "result", location = "/tools/common/contractorlookup.jsp", type = "dispatcher")
					}
			)			
	public String contractorLookUp(){
		
		if(contractor!=null) {
			pagination = contractorService.lookupContractor(contractor, getPageNum(), getNumPerPage());
		}else{
			pagination = contractorService.list(getPageNum(), getNumPerPage());
		}
		
		return "result";
	}

	/**
	 * 分包商提示
	 * @return
	 */
	@Action(value = "/common/contractorlookupsuggest",
			results = {
						@Result(name = "result", location = "/tools/common/contractorlookupsuggest.jsp", type = "dispatcher")
					}
			)			
	public String materialLookupSuggest() {
		contractorList = contractorService.suggest("contractorName",keywords);
		return "result";
	}
	
	public Contractor getContractor() {

		return contractor;
	}


	public void setContractor(Contractor contractor) {

		this.contractor = contractor;
	}
	
	public Pagination getPagination() {
	
		return pagination;
	}

	
	public void setPagination(Pagination pagination) {
	
		this.pagination = pagination;
	}

	
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

}