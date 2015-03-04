package com.ydt.oa.action;


import java.io.File;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;


import com.giro.common.action.PageAction;
import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.opensymphony.xwork2.ActionContext;
import com.ydt.oa.bean.AjaxResult;
import com.ydt.oa.entity.FileLog;
import com.ydt.oa.service.ContractorService;
import com.ydt.oa.service.FileService;
import com.ydt.oa.service.MaterialService;

/**
 * 用户管理Action
 * @author caochun
 *
 */
public class UploadFileAction extends PageAction {
	
	private static final Logger logger = Logger.getLogger(UploadFileAction.class);

	@Autowired
	private FileService fileService;
	@Autowired
	private MaterialService materialService;
	@Autowired
	private ContractorService contractorService;
	private Pagination pagination;

	private String formAction;
	private AjaxResult ajaxResult;
	private File uploadFile;
	private String uploadFileContentType;
	private String uploadFileFileName;
	private String fileHttpUrl;
	
	private FileLog fileLog;
	
	


	
	public FileLog getFileLog() {
	
		return fileLog;
	}


	
	public void setFileLog(FileLog fileLog) {
	
		this.fileLog = fileLog;
	}


	public String getFileHttpUrl() {
	
		return fileHttpUrl;
	}

	
	public void setFileHttpUrl(String fileHttpUrl) {
	
		this.fileHttpUrl = fileHttpUrl;
	}

	public String getUploadFileContentType() {
	
		return uploadFileContentType;
	}


	public void setUploadFileContentType(String uploadFileContentType) {
	
		this.uploadFileContentType = uploadFileContentType;
	}



	public String getUploadFileFileName() {
	
		return uploadFileFileName;
	}


	public void setUploadFileFileName(String uploadFileFileName) {
	
		this.uploadFileFileName = uploadFileFileName;
	}


	public File getUploadFile() {
	
		return uploadFile;
	}


	
	public void setUploadFile(File uploadFile) {
	
		this.uploadFile = uploadFile;
	}


	
	/**
	 * 上传文件
	 * @return
	 */
	@Action(value = "/common/filelookup",
			results = {
						@Result(name = "result", location = "/tools/common/filelookup.jsp", type = "dispatcher")
					}
			)
	public String fileLookup() {
		return "result";
	}
	
	/**
	 * 保存保存
	 * @return
	 */
	@Action(value = "/common/fileupload",
			results = {
						@Result(name = "result", location = "/tools/common/filebrightback.jsp", type = "dispatcher")
					}
			)
	public String fileUpload() {
		logger.info("开始上传图片");
		ActionContext actionContext = ActionContext.getContext();
		logger.info(actionContext.getParameters());
		logger.info(uploadFileContentType);
		logger.info(uploadFileFileName);
			ajaxResult = new AjaxResult();
			ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_FORWARD);
			ajaxResult.setNavTabId("main");
			try {
				
				fileLog.setFileName(uploadFileFileName);
				fileLog.setFileType(uploadFileContentType);
				fileLog.setUploadUser(getLoginUser());
				
				fileService.saveFile(fileLog, uploadFile);				
				
			} catch (GiroException e) {
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("保存图片失败！");		
			}
		logger.info("文件上传完成");
		return "result";
	}
	
	/**
	 * 材料批量导入
	 * @return
	 */
	@Action(value = "/material/import",
			results = { @Result(name = "success", location = "/system/materialimport.jsp", type = "dispatcher")}
			)
	public String materialImport() {
		return "success";
	}
	
	/**
	 * 材料批量导入
	 * @return
	 */
	@Action(value = "/material/importupload",
			results = { @Result(name = "success", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")}
			)
	public String materialImportUpload() {
		logger.info("开始上传材料文件");
		ActionContext actionContext = ActionContext.getContext();
		logger.info(actionContext.getParameters());
		logger.info(uploadFileContentType);
		logger.info(uploadFileFileName);
			ajaxResult = new AjaxResult();
			ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
			ajaxResult.setNavTabId("main");
			try {
				
				materialService.importMaterial(uploadFile);				
				
			} catch (GiroException e) {
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("材料批量导入失败");		
			}
		logger.info("材料批量导入完成");
	
		return "success";
	}

	
	/**
	 * 人工清单导入
	 * @return
	 */
	@Action(value = "/worker/import",
			results = { @Result(name = "success", location = "/system/workerimport.jsp", type = "dispatcher")}
			)
	public String workerImport() {
		return "success";
	}
	

	@Action(value = "/worker/importupload",
			results = { @Result(name = "success", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")}
			)
	public String workerImportUpload() {
		logger.info("开始上传人工清单");
		ActionContext actionContext = ActionContext.getContext();
		logger.info(actionContext.getParameters());
		logger.info(uploadFileContentType);
		logger.info(uploadFileFileName);
			ajaxResult = new AjaxResult();
			ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
			ajaxResult.setNavTabId("main");
			try {
				
				contractorService.importWorker(uploadFile);				
				
			} catch (GiroException e) {
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("人工批量导入失败");		
			}
		logger.info("人工批量导入完成");
	
		return "success";
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

	public Pagination getPagination() {

		return pagination;
	}

	public void setPagination(Pagination pagination) {

		this.pagination = pagination;
	}
}

