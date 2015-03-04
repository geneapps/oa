package com.ydt.oa.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.giro.common.action.PageAction;
import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.sun.net.httpserver.Authenticator.Success;
import com.ydt.oa.bean.AjaxResult;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.ApproveFlow;
import com.ydt.oa.entity.Contract;
import com.ydt.oa.entity.ContractDetails;
import com.ydt.oa.entity.Contractor;
import com.ydt.oa.entity.FileLog;
import com.ydt.oa.entity.ManContractDetails;
import com.ydt.oa.entity.Material;
import com.ydt.oa.entity.Project;
import com.ydt.oa.entity.SystemParam;
import com.ydt.oa.interfaces.ContractAppInterface;
import com.ydt.oa.service.ApproveService;
import com.ydt.oa.service.ContractService;
import com.ydt.oa.service.FileService;
import com.ydt.oa.service.ProjectService;
import com.ydt.oa.service.SystemConfigService;

/**
 * 审批管理Action
 * 
 * @author caochun
 * 
 */
public class ContractAction extends PageAction {
	public boolean buttonFlag = false;

	@Autowired
	private ApproveService approveService;
	@Autowired
	private FileService fileLogService;
	@Autowired
	private ContractAppInterface contractAppService;
	@Autowired
	private ContractService contractService;
	@Autowired
	private SystemConfigService configService;
	@Autowired
	private ProjectService projectService;
	private Pagination pagination;
	private Application application;
	private Contract contract;
	private List<ContractDetails> detailList; // 材料合同明细集合
	private List<ManContractDetails> manDetailList; // 人工合同明细集合
	private String fileHttpUrl;
/*	private ContractDetails contractDetails;
	private ManContractDetails manContractDetails;*/
	private List<Contractor> contractorList;
	private List<Material> materialList;
	private List<FileLog> fileLogList;
	private String formAction;
	private AjaxResult ajaxResult;
	private ApproveFlow approveFlow;
	private List<Project> projectList;
	private String id;
	
	
	
	
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



	public List<ManContractDetails> getManDetailList() {
	
		return manDetailList;
	}

	
	
	/*public ContractDetails getContractDetails() {
	
		return contractDetails;
	}


	
	public void setContractDetails(ContractDetails contractDetails) {
	
		this.contractDetails = contractDetails;
	}*/


	
	public void setPagination(Pagination pagination) {
	
		this.pagination = pagination;
	}


	
	public void setAjaxResult(AjaxResult ajaxResult) {
	
		this.ajaxResult = ajaxResult;
	}


	public void setManDetailList(List<ManContractDetails> manDetailList) {
	
		this.manDetailList = manDetailList;
	}


	
	
	/*public ManContractDetails getManContractDetails() {
	
		return manContractDetails;
	}



	
	public void setManContractDetails(ManContractDetails manContractDetails) {
	
		this.manContractDetails = manContractDetails;
	}*/



	public List<Contractor> getContractorList() {
		
		return contractorList;
	}

	
	
	public boolean isButtonFlag() {
	
		return buttonFlag;
	}





	public void setContractorList(List<Contractor> contractorList) {
	
		this.contractorList = contractorList;
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
	
	/**
	 * 编辑人工合同申请 当已经开始审批的时候则不能修改
	 * 
	 * @return
	 */
	@Action(value = "/approve/editmancontractapp",
			results = {
						@Result(name = "success", location = "/approve/editmancontractapp.jsp", type = "dispatcher"),
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
			public String editManContractApp() {
		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			System.out.println("**********************");
			try {
				if (contractorList != null) {
					ManContractDetails detail = null;
					Contractor contractor = null;
					manDetailList = new ArrayList<ManContractDetails>();
					for (int i = 0; i < contractorList.size(); i++) {
						detail = new ManContractDetails();
						contractor = contractorList.get(i);
						detail.copyFromContractor(contractor);
						manDetailList.add(detail);
						System.out.println(detail.getContact()+"@++++++++++++++++++@");
					}
					contract.setManContractDetails(manDetailList);
				}
				contractAppService.updateContractApp( id,application, contract, fileLogList, getLoginUser());
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
				contract = contractService.findById(application.getApplyNo());
				System.out.println(contract.getManContractDetails());
				fileLogList = fileLogService.find(Contract.FILE_TYPE, contract.getId());
				fileHttpUrl = configService.findParamValue(SystemParam.PARAM_FILE_HTTP_URL);
			}
			return "success";
		}
	}
	

	/**
	 * 编辑材料合同申请 当已经开始审批的时候则不能修改
	 * 
	 * @return
	 */
	@Action(value = "/approve/editcontractapp",
			results = {
						@Result(name = "success", location = "/approve/editcontractapp.jsp", type = "dispatcher"),
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
			public String editContractApp() {

		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				if (detailList != null && materialList != null) {
					ContractDetails detail = null;
					Material material = null;
					for (int i = 0; i < detailList.size(); i++) {
						detail = detailList.get(i);
						material = materialList.get(i);
						detail.copyFromMaterial(material);
					}
					contract.setContractDetails(detailList);
				}
				contractAppService.updateContractApp(id,application, contract, fileLogList, getLoginUser());
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
				contract = contractService.findById(application.getApplyNo());
				fileLogList = fileLogService.find(Contract.FILE_TYPE, contract.getId());
				fileHttpUrl = configService.findParamValue(SystemParam.PARAM_FILE_HTTP_URL);
			}
			projectList = projectService.queryProjects();
			return "success";
		}
	}

	/**
	 * 合同审批流程申请 
	 * 
	 * @return
	 */
	@Action(value = "/approve/approvecontractapp",
			results = {
						@Result(name = "success", location = "/approve/approvecontractapp.jsp", type = "dispatcher"),
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
			public String approveContractApp() {

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
				contract = contractService.findById(application.getApplyNo());
				fileLogList = fileLogService.find(Contract.FILE_TYPE, contract.getId());
				fileHttpUrl = configService.findParamValue(SystemParam.PARAM_FILE_HTTP_URL);
			}
			return "success";
		}
	}

	/**
	 * 编辑采购申请 当已经开始审批的时候则不能修改
	 * 
	 * @return
	 */
	@Action(value = "/approve/viewcontractapp",
			results = {
						@Result(name = "success", location = "/approve/viewcontractapp.jsp", type = "dispatcher")
					}
			)
			public String viewContractApp() {

		if (application != null && application.getId() != null) {
			application = approveService.findById(application.getId());
			contract = contractService.findById(application.getApplyNo());
			fileLogList = fileLogService.find(Contract.FILE_TYPE, contract.getId());
			fileHttpUrl = configService.findParamValue(SystemParam.PARAM_FILE_HTTP_URL);
		}
		return "success";
	}

	/**
	 * 显示材料合同列表
	 * 
	 * @return
	 */
	@Action(value = "/contract/contractList",
			results = {
						@Result(name = "success", location = "/contract/list.jsp", type = "dispatcher")
					}
			)
			public String listContract() {

		pagination = contractService.list(getPageNum(), getNumPerPage());
		return "success";
	}
	
	/**
	 * 显示人工合同列表
	 * 
	 * @return
	 */
	@Action(value = "/contract/mancontractList",
			results = {
						@Result(name = "success", location = "/contract/mancontractlist.jsp", type = "dispatcher")
					}
			)
			public String listManContract() {

		pagination = contractService.listManContract(getPageNum(), getNumPerPage());
		return "success";
	}

	/**
	 * 根据合同编号显示材料合同详细
	 * 
	 * @return
	 */
	@Action(value = "/contract/view",
			results = {
						@Result(name = "success", location = "/contract/viewcontract.jsp", type = "dispatcher")
					}
			)
			public String view() {

		if (contract != null && contract.getId() != null) {
			contract = contractService.findById(contract.getId());
			String appType = null;
			if (contract.getContractType() == Contract.CONTRACT_TYPE_MAN) {
				appType = Application.APP_TYPE_MANCONTRACT;
			} else {
				appType = Application.APP_TYPE_MATERIALCONTRACT;
			}
			application = approveService.findByApplyNo(appType, contract.getId());
			fileLogList = fileLogService.find(Contract.FILE_TYPE, contract.getId());
			fileHttpUrl = configService.findParamValue(SystemParam.PARAM_FILE_HTTP_URL);
		}
		return "success";
	}
	
	/**
	 * 根据合同编号显示人工合同详细
	 * 
	 * @return
	 */
	@Action(value = "/contract/viewmancontract",
			results = {
						@Result(name = "success", location = "/contract/viewmancontract.jsp", type = "dispatcher")
					}
			)
			public String viewmancontract() {

		if (contract != null && contract.getId() != null) {
			contract = contractService.findById(contract.getId());
			manDetailList = contractService.findManDetailsById(contract);
			String appType = null;
			if (contract.getContractType() == Contract.CONTRACT_TYPE_MAN) {
				appType = Application.APP_TYPE_MANCONTRACT;
			} else {
				appType = Application.APP_TYPE_MATERIALCONTRACT;
			}
			application = approveService.findByApplyNo(appType, contract.getId());
			fileLogList = fileLogService.find(Contract.FILE_TYPE, contract.getId());
			fileHttpUrl = configService.findParamValue(SystemParam.PARAM_FILE_HTTP_URL);
		}
		return "success";
	}

	public Contract getContract() {

		return contract;
	}

	public void setContract(Contract contract) {

		this.contract = contract;
	}

	public List<Material> getMaterialList() {

		return materialList;
	}

	public void setMaterialList(List<Material> materialList) {

		this.materialList = materialList;
	}

	public AjaxResult getAjaxResult() {

		return ajaxResult;
	}

	public void setFormAction(String formAction) {

		this.formAction = formAction;
	}

	public List<ContractDetails> getDetailList() {

		return detailList;
	}

	public void setDetailList(List<ContractDetails> detailList) {

		this.detailList = detailList;
	}

	public List<FileLog> getFileLogList() {

		return fileLogList;
	}

	public void setFileLogList(List<FileLog> fileLogList) {

		this.fileLogList = fileLogList;
	}
}
