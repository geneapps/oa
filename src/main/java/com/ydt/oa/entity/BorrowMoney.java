package com.ydt.oa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.log4j.Logger;

import com.giro.common.entity.StringUUIDEntity;

/**
 * 用户数据库持久化Bean
 * @author huchuqiao
 *
 */
@Entity
@Table(name = "oa_borrowmoney") //借款申请单
public class BorrowMoney extends StringUUIDEntity implements Serializable{
	
	private static final long serialVersionUID = 8518288146812530362L;
	public static final int BORROWMONEY_WAITINGAPPROVE = 0;   //等待审批
	public static final int BORROWMONEY_VALID = 1;            //有效地
	public static final int BORROWMONEY_INVALID = 2;           //无效的
	public static final int BORROWMONEY_DELETE = 99;          //已被删除
	private static final Logger logger = Logger.getLogger(BorrowMoney.class);

	private String borrowTitle; //借款标题
	private String borrowType;  // 借款类型
	private String borrowMoney; //借款金额
	private String borrowTime; //借款日期
	private String borrowReason; //借款理由
	private String applyTime; //提交时间
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBorrowTitle() {
		return borrowTitle;
	}
	
	public void setBorrowTitle(String borrowTitle) {
		this.borrowTitle = borrowTitle;
	}
	
	public String getBorrowType() {
		return borrowType;
	}
	
	public void setBorrowType(String borrowType) {
		this.borrowType = borrowType;
	}
	
	public String getBorrowMoney() {
		return borrowMoney;
	}
	
	public void setBorrowMoney(String borrowMoney) {
		this.borrowMoney = borrowMoney;
	}
	
	public String getBorrowTime() {
		return borrowTime;
	}
	
	public void setBorrowTime(String borrowTime) {
		this.borrowTime = borrowTime;
	}
	
	public String getBorrowReason() {
		return borrowReason;
	}
	
	public void setBorrowReason(String borrowReason) {
		this.borrowReason = borrowReason;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	

	
	
}
