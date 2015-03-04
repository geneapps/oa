package com.ydt.oa.entity;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.giro.common.entity.StringUUIDEntity;

/**
 * 短信持久化Bean
 * @author caochun
 *
 */
@Entity
@Table(name = "oa_sms")
public class Sms extends StringUUIDEntity implements Serializable{


	private static final long serialVersionUID = -1493265957243932203L;
	
	private String mobile;
	private String msg;
	private String createDate;
	private String sendDate;
	private int sendtimes;
	private int status;
	
	public static final int STATUS_WAIT_SEND = 0;
	public static final int STATUS_SUCESS = 1;
	public static final int STATUS_SENDING = 2;
	
	public String getMobile() {
	
		return mobile;
	}
	
	public void setMobile(String mobile) {
	
		this.mobile = mobile;
	}
	
	public String getMsg() {
	
		return msg;
	}
	
	public void setMsg(String msg) {
	
		this.msg = msg;
	}
	
	public String getSendDate() {
	
		return sendDate;
	}
	
	public void setSendDate(String sendDate) {
	
		this.sendDate = sendDate;
	}
	
	public int getSendtimes() {
	
		return sendtimes;
	}
	
	public void setSendtimes(int sendtimes) {
	
		this.sendtimes = sendtimes;
	}
	


	
	
	public int getStatus() {
	
		return status;
	}

	
	public void setStatus(int status) {
	
		this.status = status;
	}

	public String getCreateDate() {
	
		return createDate;
	}

	
	public void setCreateDate(String createDate) {
	
		this.createDate = createDate;
	}
	
	
}
