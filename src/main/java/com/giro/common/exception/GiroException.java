package com.giro.common.exception;

/**
 * Webservice异常
 * @author giro
 *
 */
public class GiroException extends Exception {

	private static final long serialVersionUID = -817734806278255006L;

	private int code;
	private String message;
	
	public GiroException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	
	public int getCode() {
	
		return code;
	}
	
	public void setCode(int code) {
	
		this.code = code;
	}
	
	public String getMessage() {
	
		return message;
	}
	
	public void setMessage(String message) {
	
		this.message = message;
	}


}
