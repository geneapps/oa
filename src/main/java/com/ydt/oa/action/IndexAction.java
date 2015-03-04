package com.ydt.oa.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.giro.common.action.BaseAction;
import com.giro.common.action.PageAction;
import com.giro.common.dao.Pagination;
import com.ydt.oa.entity.User;
import com.ydt.oa.service.ApproveService;

/**
 * chain 用来处理Action链
 * dispatcher 用来转向页面，通常处理JSP
 * freemaker 处理FreeMarker模板
 * httpheader 控制特殊HTTP行为的结果类型
 * redirect 重定向到一个URL
 * redirectAction 重定向到一个Action
 * stream 向浏览器发送InputSream对象，通常用来处理文件下载，还可用于返回AJAX数据
 * velocity 处理Velocity模板
 * xslt 处理XML/XLST模板
 * plainText 显示原始文件内容，例如文件源代码
 * redirect-action 重定向到一个Action
 * @author caochun
 *
 */

/**
 * 主页Action
 */
@Namespace("/")
public class IndexAction extends PageAction{
	
	@Autowired
	private ApproveService approveService;
	private User user;
	private Pagination pagination;
	private long applicationNum;
	private long approveNum;
	
	/**
	 * 管理首页
	 * @return
	 */
	@Action(value="/index",
			results={@Result(name = "success", location = "/index.jsp",type="dispatcher")}
	)
	public String index() {
		user = getLoginUser();
		// 我的申请
		try{
		pagination = approveService.findApplicationByUser(user,this.getPageNum(), this.getNumPerPage());
		
		applicationNum = pagination.getTotalCount();		
		}catch(Exception e){
			applicationNum = 0l;
		}
		try{
		pagination = approveService.findApprove(getLoginUser(),this.getPageNum(), this.getNumPerPage());
		approveNum = pagination.getTotalCount();
	}catch(Exception e){
		approveNum = 0l;
	}
		
		return "success";
		
	}
	
	/**
	 * 管理首页
	 * @return
	 */
	@Action(value="/main",
			results={@Result(name = "success", location = "/main.jsp",type="dispatcher")}
	)
	public String main() {
		user = getLoginUser();
		// 我的申请
		try{
		pagination = approveService.findApplicationByUser(user,this.getPageNum(), this.getNumPerPage());
		
		applicationNum = pagination.getTotalCount();
		}catch(Exception e){
			applicationNum = 0l;
		}
		
		try{
		pagination = approveService.findApprove(getLoginUser(),this.getPageNum(), this.getNumPerPage());
		approveNum = pagination.getTotalCount();
		}catch(Exception e){
			approveNum = 0l;
		}
		
		return "success";
		
	}

	
	public User getUser() {
	
		return user;
	}

	
	public void setUser(User user) {
	
		this.user = user;
	}


	
	public long getApplicationNum() {
	
		return applicationNum;
	}


	
	public void setApplicationNum(long applicationNum) {
	
		this.applicationNum = applicationNum;
	}


	
	public long getApproveNum() {
	
		return approveNum;
	}


	
	public void setApproveNum(long approveNum) {
	
		this.approveNum = approveNum;
	}
	

	
	
	
}