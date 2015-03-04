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
import com.giro.common.exception.GiroException;
import com.giro.common.util.StringUtils;
import com.opensymphony.xwork2.ActionContext;
import com.ydt.oa.bean.AjaxResult;
import com.ydt.oa.entity.User;
import com.ydt.oa.service.UserService;

/**
 * 用户管理Action
 * @author caochun
 *
 */
public class UserAction extends PageAction {
	
	private static final Logger logger = Logger.getLogger(UserAction.class);

	@Autowired
	private UserService userService;

	private Pagination pagination;
	private List<User> users;
	private String userId;
	private User user;
	private String formAction;
	private AjaxResult ajaxResult;
	private File uploadFile;
	private String uploadFileContentType;
	private String uploadFileFileName;
	private String photoHttpUrl;
	private String searchKey;
	private String searchType;
	private String checkCode;
	private String oldPwd;
	private String newPwd;
	private String reNewPwd;



	
	
	
	public String getOldPwd() {
	
		return oldPwd;
	}

	
	public void setOldPwd(String oldPwd) {
	
		this.oldPwd = oldPwd;
	}

	
	public String getNewPwd() {
	
		return newPwd;
	}

	
	public void setNewPwd(String newPwd) {
	
		this.newPwd = newPwd;
	}

	
	public String getReNewPwd() {
	
		return reNewPwd;
	}

	
	public void setReNewPwd(String reNewPwd) {
	
		this.reNewPwd = reNewPwd;
	}

	public String getCheckCode() {
	
		return checkCode;
	}
	
	public void setCheckCode(String checkCode) {
	
		this.checkCode = checkCode;
	}

	public String getSearchKey() {
	
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
	
		this.searchKey = searchKey;
	}
	
	public String getSearchType() {
	
		return searchType;
	}
	
	public void setSearchType(String searchType) {
	
		this.searchType = searchType;
	}


	public String getPhotoHttpUrl() {
	
		return photoHttpUrl;
	}

	
	public void setPhotoHttpUrl(String photoHttpUrl) {
	
		this.photoHttpUrl = photoHttpUrl;
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
	 * 用户查找
	 * @return
	 */
	@Action(value = "/common/lookup",
			results = {
						@Result(name = "result", location = "/tools/common/userlookup.jsp", type = "dispatcher")
					}
			)			
	public String userLookup() {
		
		if(user!=null) {
			pagination = userService.lookupUser(user, getPageNum(), getNumPerPage());
		}else{
			pagination = userService.list(getPageNum(), getNumPerPage());
		}
		
		return "result";
	}
	/**
	 * 创建用户
	 */
	@Action(value = "/user/addUser",
			results = { @Result(name = "success", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")}
	)
	public String  addUser() {
		ajaxResult=new AjaxResult();
		try {
			userService.regUser(user);
			ajaxResult.setNavTabId("main");
			ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
			
		} catch (GiroException e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	/**
	 * 用户管理中列出用户
	 * @return
	 */
	@Action(value = "/user/userlist",
			results = { @Result(name = "success", location = "/user/userlist.jsp", type = "dispatcher")}
			)
	public String userlist() {
		if(user!=null) {
			pagination = userService.lookupUser(user, getPageNum(), getNumPerPage());
		}else{
			pagination = userService.userList(getPageNum(), getNumPerPage());
		}
		
		return "success";
		
	}
	
	
	/**
	 * 列出用户
	 * @return
	 */
	@Action(value = "/user/list",
			results = { @Result(name = "success", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")}
			)
	public String list() {
		
		
		
		if(user==null){
			user = new User();
		}
		
		logger.info(searchKey);
		logger.info(searchType);

		
		if(!StringUtils.isNull(searchKey) && !StringUtils.isNull(searchType)){
			
			pagination = userService.query(searchKey,searchType,getPageNum(), getNumPerPage());
		}else{
			pagination = userService.list(getPageNum(), getNumPerPage());
			
		}
		
		return "success";
	}
	
	/**
	 * 查找用户
	 * @return
	 */
	@Action(value = "/user/lookup",
			results = { @Result(name = "success", location = "/user/lookup.jsp", type = "dispatcher")}
			)
	public String lookup() {	
		if(StringUtils.isNull(searchKey) && StringUtils.isNull(searchType)){

			pagination = userService.list(getPageNum(), getNumPerPage());
		}else{
			pagination = userService.query(searchKey,searchType, getPageNum(), getNumPerPage());
		}
		
		
		return "success";
	}

	/**
	 * 编辑用户
	 * @return
	 */
	@Action(value = "/user/edit",
			results = {
						@Result(name = "success", location = "/user/edit.jsp", type = "dispatcher"),
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
	public String edit() {
		

		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			
			try {
				userService.updateUser(user);
				
				ajaxResult.setNavTabId("main");
//				ajaxResult.setRel("main");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);

//				ajaxResult.setForwardUrl("/bolan/user/list.action?pageNum="+this.getPageNum()+"&numPerPage="+this.getNumPerPage());
				
			} catch (GiroException e) {
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage(e.getMessage());		
			}catch (Exception e) {
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("数据保存失败，可能是用户名或者手机号已经存在");		
			}
			
			return "save";
			
		} else {
			if (user != null && user.getId() != null) {
				user = userService.findByUserId(user.getId());
//				photoHttpUrl = sysService.findParamValue(Param.PARAM_PHOTO_HTTP_URL);
			}
			return "success";
		}
	}
	
	@Action(value = "/user/editpwd",
			results = {
						@Result(name = "success", location = "/user/editpwd.jsp", type = "dispatcher"),
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
	public String editpwd() {
		

		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			
			try {
				
				if(!newPwd.equals(reNewPwd)){
					throw new GiroException(-1,"两次输入的新密码不一致");
				}
				
				userService.updatePwd(getLoginUser().getId(),oldPwd,newPwd);
				
				ajaxResult.setNavTabId("main");
//				ajaxResult.setRel("main");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);

//				ajaxResult.setForwardUrl("/bolan/user/list.action?pageNum="+this.getPageNum()+"&numPerPage="+this.getNumPerPage());
				
			} catch (GiroException e) {
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage(e.getMessage());		
			}catch (Exception e) {
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("修改密码失败");		
			}
			
			return "save";
			
		} else {
//			if (user != null && user.getId() != null) {
//				user = userService.findByUserId(user.getId());
////				photoHttpUrl = sysService.findParamValue(Param.PARAM_PHOTO_HTTP_URL);
//			}
			return "success";
		}
	}
	
	/**
	 * 上传图片
	 * @return
	 */
	@Action(value = "/user/upload",
			results = {
						@Result(name = "result", location = "/user/upload.jsp", type = "dispatcher")
					}
			)
	public String uploadPhoto() {
		return "result";
	}
	
	/**
	 * 保存图片
	 * @return
	 */
	@Action(value = "/user/uploadphoto",
			results = {
						@Result(name = "result", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
	public String uploadPhotoSubmit() {
		logger.info("开始上传图片");
		ActionContext actionContext = ActionContext.getContext();
		logger.info(actionContext.getParameters());
		logger.info(uploadFileContentType);
		logger.info(uploadFileFileName);
			ajaxResult = new AjaxResult();
			ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_FORWARD);
			ajaxResult.setNavTabId("main");
			try {
				userService.savePhoto(userId, uploadFile, uploadFileFileName.substring(uploadFileFileName.indexOf(".")).toLowerCase());				
				
			} catch (GiroException e) {
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("保存图片失败！");		
			}
		logger.info("图片上传完成");
		return "result";
	}

	
	/**
	 * 删除用户
	 * @return
	 */
	@Action(value = "/user/deleteuser",
			results = {
						@Result(name = "success", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
	public String deleteUser() {
		ajaxResult = new AjaxResult();
		if (user != null && user.getId() != null) {
			try {
				userService.delUser(user);
				ajaxResult.setNavTabId("main");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_FORWARD);
			} catch (GiroException e) {
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("删除失败");
			}
		}
		return "success";
	}
	
	
	
	/**
	 * 删除用户
	 * @return
	 */
	@Action(value = "/user/delete",
			results = {
						@Result(name = "success", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
	public String delete() {

		ajaxResult = new AjaxResult();
		if (user != null && user.getId() != null) {
			userService.deleteUser(user.getId());
			ajaxResult.setNavTabId("main");
			ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_FORWARD);
			ajaxResult.setForwardUrl("/bolan/user/list.action?pageNum="+this.getPageNum()+"&numPerPage="+this.getNumPerPage());
		}
		return "success";
	}
	
	@Action(value = "/user/order",
			results = { @Result(name = "success", location = "/user/orderlist.jsp", type = "dispatcher") }
			)
	public String orderList() {
		
//		pagination= userService.queryOrder(orderClient, orderType, getPageNum(), getNumPerPage());
		
		return "success";
	}
	


	/**
	 * 登录
	 * @return
	 */
	@Action(value = "/login",
			results = {
						@Result(name = "success", location = "/index.action", type = "redirect"),
						@Result(name = "error", location = "/login.jsp", type = "dispatcher")
					}
			)
	public String login() {

		ajaxResult = new AjaxResult();
		if (user != null) {
			String name = user.getUserNo();
			String pwd = user.getPassword();
			try{		
				
				ActionContext actionContext = ActionContext.getContext();
		        Map<String, Object> session = actionContext.getSession();
		        String chk = (String)session.get("randomString");
		        logger.info(chk);
		        logger.info(checkCode);
		        if(chk!=null && checkCode!=null){
		        	if(!chk.equalsIgnoreCase(checkCode)){
		        		throw new GiroException(-1,"验证码不正确");
		        	}
		        }else{
		        	throw new GiroException(-1,"验证码不正确");
		        }
				
				user = userService.login(name, pwd);
				logger.info(user.getRealName());
				setLoginUser(user);
				return "success";
			}catch(GiroException be){
				ajaxResult.setMessage(be.getMessage());	
				
			}catch(Exception e){
				ajaxResult.setMessage("登录失败！");	
			}

		}else{
			ajaxResult.setMessage("请先登录！");	
		}
		return "error";
	}
	
	/**
	 * 注销
	 * @return
	 */
	@Action(value = "/logout",
			results = {
						@Result(name = "success", location = "/login.jsp", type = "dispatcher")
					}
			)
	public String logout() {
		
		resetLoginUser();

		return "success";
	}
	

	public String getFormAction() {

		return formAction;
	}

	public void setFormAction(String formAction) {

		this.formAction = formAction;
	}

	public List<User> getUsers() {

		return users;
	}

	public String getUserId() {

		return userId;
	}

	public void setUserId(String userId) {

		this.userId = userId;
	}

	public User getUser() {

		return user;
	}

	public void setUser(User user) {

		this.user = user;
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

