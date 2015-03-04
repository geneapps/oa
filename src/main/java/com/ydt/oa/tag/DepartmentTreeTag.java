package com.ydt.oa.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.ydt.oa.entity.Department;


public class DepartmentTreeTag extends TagSupport {

	private static final long serialVersionUID = -1911916303622057369L;
	
	private StringBuilder tag=new StringBuilder(); 
	
	private String list;
	private String url;
	private String rel;
	
	

	
	
	public String getUrl() {
	
		return url;
	}


	
	public void setUrl(String url) {
	
		this.url = url;
	}


	
	public String getRel() {
	
		return rel;
	}


	
	public void setRel(String rel) {
	
		this.rel = rel;
	}



	
	
	public String getList() {
	
		return list;
	}



	
	public void setList(String list) {
	
		this.list = list;
	}



	@SuppressWarnings("unchecked")
	@Override  
    public int doStartTag() throws JspException {  
        JspWriter out=pageContext.getOut();  
        try {  
            
        	List<Department> departments = (List<Department>)pageContext.findAttribute(list);
        	
        	tag.delete(0, tag.length());
        	
//        	tag.append("<ul class=\"tree\">");
//        	tag.append("<li target=\"sid\" rel=\"-1\" ><a href=\""+url+"\" target=\"ajax\" rel=\""+rel+"\">深装总</a>");
        	getChild(departments);
//        	tag.append("</li>");
//        	tag.append("</ul>");
        	
        	System.out.println(tag.toString());

            out.println(tag.toString());  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return SKIP_BODY;  
    }
	
	public void getChild(List<Department> deps){
		
		if(deps!=null && deps.size()>0){
			
			if(tag.length()==0){
				tag.append("<ul class=\"tree expand\">");
			}else{
				tag.append("<ul>");
			}
	    	for(Department dep: deps){
	    		
	    		tag.append("<li target=\"sid\" rel=\""+dep.getId()+"\"><a href=\""+url+"?department.id="+dep.getId()+"\" target=\"ajax\" rel=\""+rel+"\">").append(dep.getDepName()).append("</a>");
	    		
	    		getChild(dep.getChilds());
	    		
	    		tag.append("</li>");
	
	    	}
	    	tag.append("</ul>");
		}
		
	}
	
	
	
	
	
	
}
