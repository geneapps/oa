package com.ydt.oa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.log4j.Logger;

import com.giro.common.entity.StringUUIDEntity;

/**
 * 
 * @author Cruise
 * 请款申请实体类
 */
@Entity
@Table(name = "oa_requestmoney")
public class RequestMoney extends StringUUIDEntity implements Serializable  {
	public static final String FILE_TYPE="REQUESTMONEY";

	private static final long serialVersionUID = 6002036543440067201L;
	public static final int REQUESTMONEY_WAITINGAPPROVE = 0;   //等待审批
	public static final int REQUESTMONEY_VALID = 1;            //有效地
	public static final int REQUESTMONEY_INVALID = 2;           //无效的
	public static final int REQUESTMONEY_DELETE = 99;          //已被删除
	
	//private static final Logger logger = Logger.getLogger(RequestMoney.class);
	
	private String requestTitle;    //请款申请标题
	
	private String requestType;     //请款类型
	
	private String requestMoney;    //请款金额
	
	private String payTime;         //付款时间
	
	private String reason;  		//请款缘由
	
	
	private String applyTime;       //申请时间
	

	private int status;
	
	private String payee;    //收款方名称
	
	private String account;  //账户信息
	
	private String contractNo;  //合同编号
	
	
	
	public String getPayee() {
	
		return payee;
	}

	
	public void setPayee(String payee) {
	
		this.payee = payee;
	}

	
	public String getAccount() {
	
		return account;
	}

	
	public void setAccount(String account) {
	
		this.account = account;
	}

	
	public String getContractNo() {
	
		return contractNo;
	}

	
	public void setContractNo(String contractNo) {
	
		this.contractNo = contractNo;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRequestTitle() {
		return requestTitle;
	}

	public void setRequestTitle(String requestTitle) {
		this.requestTitle = requestTitle;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getRequestMoney() {
		return requestMoney;
	}

	public void setRequestMoney(String requestMoney) {
		this.requestMoney = requestMoney;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}	

}
