package com.ydt.oa.tag;

import java.util.HashMap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.giro.common.action.BaseAction;
import com.ydt.oa.entity.ActionUrl;
import com.ydt.oa.entity.User;
import com.ydt.oa.service.RoleManageService;


public class AuthorityTag extends TagSupport {

	private static final long serialVersionUID = -1911916303622057369L;

	private String code;
	
	public String getCode() {
	
		return code;
	}

	
	public void setCode(String code) {
	
		this.code = code;
	}


	@Override  
    public int doStartTag() throws JspException { 

		User user =  (User) pageContext.getSession().getAttribute(BaseAction.SESSION_USER); 		
		
		if(user==null){
			return SKIP_BODY;  
		}
		
		if(code!=null){
			String[] codes = code.split(",");
			
			for(int i=0;i<codes.length;i++){
			
				if(user.canAccess(Integer.parseInt(codes[i]))){
					return EVAL_BODY_INCLUDE; 
				}
			}
			
		}
		
	    return SKIP_BODY;  
    }
	

	
	
	
	
	
	
}
