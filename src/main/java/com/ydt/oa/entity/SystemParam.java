package com.ydt.oa.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.giro.common.entity.StringUUIDEntity;




/**
 * 系统参数配置
 * @author caochun
 *
 */
@Entity
@Table(name = "oa_systemparam")
public class SystemParam extends StringUUIDEntity implements Serializable{
	
	public static final String PARAM_PHOTO_HTTP_URL = "PHOTO_HTTP_URL";
	public static final String PARAM_PHOTO_LOCAL_PATH = "PHOTO_LOCAL_PATH";
	public static final String PARAM_FILE_HTTP_URL = "FILE_HTTP_URL";
	public static final String PARAM_FILE_LOCAL_PATH = "FILE_LOCAL_PATH";
	public static final String PARAM_SMS_GATEWAY_URL = "SMS_GATEWAY_URL";
	public static final String PARAM_SMS_TASK_FLAG = "SMS_TASK_FLAG";

	private static final long serialVersionUID = -6609830573797304015L;
	private String paramName;
	private String paramValue;
	private String showName;
	
	
	
	
	public String getShowName() {
	
		return showName;
	}

	
	public void setShowName(String showName) {
	
		this.showName = showName;
	}

	public String getParamName() {
	
		return paramName;
	}
	
	public void setParamName(String paramName) {
	
		this.paramName = paramName;
	}
	
	public String getParamValue() {
	
		return paramValue;
	}
	
	public void setParamValue(String paramValue) {
	
		this.paramValue = paramValue;
	}

}
