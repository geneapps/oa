package com.ydt.oa.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.log4j.Logger;

import com.giro.common.entity.StringUUIDEntity;

/**
 * 用户数据库持久化Bean
 * @author caochun
 *
 */
@Entity
@Table(name = "oa_costdetails") //费用明细表
public class CostDetails extends StringUUIDEntity implements Serializable{

	private static final long serialVersionUID = 575819663800457460L;
	private static final Logger logger = Logger.getLogger(CostDetails.class);
	
	private String expenseType; //费用类型
	private String expenseTime; //费用时间
	private String payTime; //付款时间
	private String payMoney; // 费用金额
	private String description; // 费用说明
	private Expense expense;     //费用明细
	private User user;             //明细的添加用户
	
	@ManyToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = Expense.class, fetch = FetchType.EAGER)
	@JoinColumn
	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}

	public String getExpenseType() {
		return expenseType;
	}
	
	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}
	
	public String getExpenseTime() {
		return expenseTime;
	}
	
	public void setExpenseTime(String expenseTime) {
		this.expenseTime = expenseTime;
	}
	
	public String getPayTime() {
		return payTime;
	}
	
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	
	public String getPayMoney() {
		return payMoney;
	}
	
	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
