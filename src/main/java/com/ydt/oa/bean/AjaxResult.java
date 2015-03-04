package com.ydt.oa.bean;

/**
 * Ajax返回结果
 * @author caochun
 *
 */
public class AjaxResult {
	
	public static final int CODE_OK = 200;
	public static final int CODE_ERROR = 300;
	public static final String CALLBACKTYPE_CLOSE = "closeCurrent";
	public static final String CALLBACKTYPE_FORWARD = "forward";
	
	public AjaxResult(int code,String msg){
		this.statusCode = code;
		this.message = msg;
	}
	
	public AjaxResult(){
		this.statusCode = CODE_OK;
		this.message = "操作成功！";
		this.callbackType = CALLBACKTYPE_CLOSE;
	}
	
	private int statusCode;
	private String message;
	private String navTabId;
	private String rel;
	private String callbackType;
	private String forwardUrl;
	private String confirmMsg;
	
	public int getStatusCode() {
	
		return statusCode;
	}
	
	public void setStatusCode(int statusCode) {
	
		this.statusCode = statusCode;
	}
	
	public String getMessage() {
	
		return message;
	}
	
	public void setMessage(String message) {
	
		this.message = message;
	}
	
	public String getNavTabId() {
	
		return navTabId;
	}
	
	public void setNavTabId(String navTabId) {
	
		this.navTabId = navTabId;
	}
	
	public String getRel() {
	
		return rel;
	}
	
	public void setRel(String rel) {
	
		this.rel = rel;
	}
	
	public String getCallbackType() {
	
		return callbackType;
	}
	
	public void setCallbackType(String callbackType) {
	
		this.callbackType = callbackType;
	}
	
	public String getForwardUrl() {
	
		return forwardUrl;
	}
	
	public void setForwardUrl(String forwardUrl) {
	
		this.forwardUrl = forwardUrl;
	}
	
	public String getConfirmMsg() {
	
		return confirmMsg;
	}
	
	public void setConfirmMsg(String confirmMsg) {
	
		this.confirmMsg = confirmMsg;
	}

}
