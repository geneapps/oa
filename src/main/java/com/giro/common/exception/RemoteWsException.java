package com.giro.common.exception;

/**
 * Webservice异常
 * @author giro
 *
 */
public class RemoteWsException extends Exception {

	private static final long serialVersionUID = -817734806278255006L;
	/**
	 * 异常发生级别 
	 */
	private int type;
	private int code;
	private String message;
	
	public RemoteWsException(int type, int code, String message) {
		super();
		this.type = type;
		this.code = code;
		this.message = message;
	}

	public int getType() {
	
		return type;
	}
	
	public void setType(int type) {
	
		this.type = type;
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
