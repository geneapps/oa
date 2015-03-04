package com.ydt.oa.filter;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


import com.giro.common.action.BaseAction;
import com.ydt.oa.entity.ActionUrl;
import com.ydt.oa.entity.User;
import com.ydt.oa.service.RoleManageService;


/**
 * 后台管理权限过滤器
 * @author caochun master
 *
 */
public class WebClientFilter implements Filter {
	
//	@Autowired
	private RoleManageService roleManageService;

	protected FilterConfig filterConfig = null;
	private WebApplicationContext wac;
	

	// --------------------------------------------------------- Public Methods

	/**
	 * Take this filter out of service.
	 */
	@Override
	public void destroy() {

		this.filterConfig = null;

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
							FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpReq = (HttpServletRequest)request;
		HttpSession session = httpReq.getSession();
		HttpServletResponse httpRes = (HttpServletResponse)response;
		String currentURL = httpReq.getRequestURI().substring(httpReq.getContextPath().length());
		
//		System.out.println(currentURL);


		if(currentURL.indexOf("login.action")==-1 && currentURL.indexOf("logout.action")==-1 && currentURL.indexOf("uploadphoto.action")==-1){
			System.out.println(currentURL);
			User user =  (User)session.getAttribute(BaseAction.SESSION_USER);		
		
			if(user==null){
				httpRes.sendRedirect(httpReq.getContextPath() + "/redirect.html");
				return;
			}else{
				roleManageService = (RoleManageService)wac.getBean("roleManageService");
				if(roleManageService.canAccess(currentURL, user)){
					chain.doFilter(request, response);
				}else{
					httpRes.sendRedirect(httpReq.getContextPath() + "/unauthorized.html");
					return;
				}
	
			}

		}else{
			chain.doFilter(request, response);
		}
		

		// Pass control on to the next filter
//		

	}
	
//	private UserInfo getUserInfo(String agent,UserInfo user){
//		
//		// 验证串md5(手机id+用户名+客户端版本对应的密钥)；手机型号；操作系统；操作系统版本;客户端名称；app类型;客户端版本；手机id；用户名；
//		agent = "a6fa71c02a94a7627d895057a1ec8655;iPhone4S;ios;5.0;bolan iphone;JRQ;1.0;12345678;test;";
//		String[] userAgents = agent.split(";");
//		try{
//			if(user!=null && user.getUserName().equals(userAgents[8])){
//				user.setLogin(true);
//				return user;
//			}
//		}catch(Exception e){
//			
//		}
//		
//		if(user==null) user = new UserInfo();
//		
//		if(userAgents.length>=9){
//			
//			String check = MD5.getMD5(userAgents[7]+userAgents[8]+ClientKey.getClientKey(userAgents[4], userAgents[5],userAgents[6]));
//			System.out.println("check:"+check);
//			System.out.println("userAgent[0]:"+userAgents[0]);
//			
//			if(check!=null && check.equals(userAgents[0])){
//				
//				user.setClientId(userAgents[7]);
//				
//				if(userAgents[8]==null || userAgents[8].equals("")){
//					user.setLogin(false);
//				}else{
//				
//					user.setUserName(userAgents[8]);
//				}
//				
//			}
//		}
//		return user;
//		
//	}
//	

	@SuppressWarnings("unchecked")
	/**
	 * Place this filter into service.
	 * 
	 * @param filterConfig
	 *            The filter configuration object
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
//		System.out.println("web filter init");
		this.filterConfig = filterConfig;
		
		wac =WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
//		HashMap<String,ActionUrl> urls = (HashMap<String,ActionUrl>)filterConfig.getServletContext().getAttribute(BaseAction.APPLICATION_URLS);
//		
//		if(urls==null){
//			roleManageService = (RoleManageService)wac.getBean("roleManageService");
//			filterConfig.getServletContext().setAttribute(BaseAction.APPLICATION_URLS,roleManageService.authorityUrls());
//		}

	}

}
