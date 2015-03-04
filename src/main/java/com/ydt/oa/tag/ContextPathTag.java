package com.ydt.oa.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;



public class ContextPathTag extends TagSupport {

	private static final long serialVersionUID = -1911916303622057369L;

	private String contextPath;

	@Override  
    public int doStartTag() throws JspException {  
        JspWriter out=pageContext.getOut();  
        try {  
        	contextPath = pageContext.getServletContext().getContextPath();
        	
//        	System.out.println(contextPath);

            out.print(contextPath);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return SKIP_BODY;  
    }

}
