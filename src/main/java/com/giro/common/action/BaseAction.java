package com.giro.common.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.giro.common.exception.GiroException;
import com.opensymphony.xwork2.ActionContext;
import com.ydt.oa.bean.AjaxResult;
import com.ydt.oa.entity.Department;
import com.ydt.oa.entity.Project;
import com.ydt.oa.entity.User;

@Results({
	@Result(name="accessError",location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
})
public class BaseAction {
	
	public static final String APPLICATION_URLS = "APPLICATION_URLS";
	public static final String SESSION_USER = "SESSION_USER";
//	public static final String SESSION_USERURL = "SESSION_USERURL";
	public static final String ACTION_ACCESSERROR = "accessError";
	
	public User getLoginUser(){
		ActionContext actionContext = ActionContext.getContext();
        Map<String, Object> session = actionContext.getSession();        
        return (User) session.get(SESSION_USER);

	}
	
	public void setLoginUser(User user){
		ActionContext actionContext = ActionContext.getContext();
        Map<String, Object> session = actionContext.getSession();
        session.put(SESSION_USER, user); 
//        session.put(SESSION_USERURL, user.canAccessUrl());   
        
	}
	

	
	public void resetLoginUser(){
		ActionContext actionContext = ActionContext.getContext();
        Map<String, Object> session = actionContext.getSession();
        session.remove(SESSION_USER);  
//        session.remove(SESSION_USERURL);  
        
	}
	
	public void canAccess(int actionCode) throws GiroException{
		if(!getLoginUser().canAccess(actionCode)){
			throw new GiroException(-99,"没有操作权限");
		}
	}
	

}
