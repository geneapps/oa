package com.ydt.oa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.log4j.Logger;

import com.giro.common.entity.StringUUIDEntity;

/**
 * 用户数据库持久化Bean
 * @author caochun
 *
 */
@Entity
@Table(name = "oa_runningaccount")
public class RunningAccount extends StringUUIDEntity implements Serializable{
	
	private static final long serialVersionUID = 5767708875734690703L;
	private static final Logger logger = Logger.getLogger(RunningAccount.class);

	private String expenseType; // 费用类型
	private String documnetNum; // 单据号
	private String balanceType; // 结算类型
	private String claim; //结算金额
	private String due; //应付款
	private String payMoney; // 实际付款
	private String totalPayMoney; //总金额
	
	public String getExpenseType() {
		return expenseType;
	}
	
	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}
	
	public String getDocumnetNum() {
		return documnetNum;
	}
	
	public void setDocumnetNum(String documnetNum) {
		this.documnetNum = documnetNum;
	}
	
	public String getBalanceType() {
		return balanceType;
	}
	
	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}
	
	public String getClaim() {
		return claim;
	}
	
	public void setClaim(String claim) {
		this.claim = claim;
	}
	
	public String getDue() {
		return due;
	}
	
	public void setDue(String due) {
		this.due = due;
	}
	
	public String getPayMoney() {
		return payMoney;
	}
	
	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}
	
	public String getTotalPayMoney() {
		return totalPayMoney;
	}
	
	public void setTotalPayMoney(String totalPayMoney) {
		this.totalPayMoney = totalPayMoney;
	}
	
}
