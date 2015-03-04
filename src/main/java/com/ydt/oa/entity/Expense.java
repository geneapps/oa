package com.ydt.oa.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.log4j.Logger;

import com.giro.common.entity.StringUUIDEntity;

/**
 * 用户数据库持久化Bean
 * @author caochun
 *
 */
@Entity
@Table(name = "oa_expense") //费用报销表
public class Expense extends StringUUIDEntity implements Serializable{

	private static final long serialVersionUID = 575819663800457460L;
	//private static final Logger logger = Logger.getLogger(Expense.class);
	public static final int EXPENSE_WAITINGAPPROVE = 0;   //等待审批
	public static final int EXPENSE_VALID = 1;            //有效地
	public static final int EXPENSE_INVALID = 2;           //无效的
	public static final int EXPENSE_DELETE = 99;          //已被删除
	public static final String FILE_TYPE = "EXPENSE";
	private String payTitle; //报销标题	
	private String submitTime; //提交时间
	private String totalPrice; //总价
	private List<CostDetails> costDetails;   //费用明细
	private String  borrowNumber;        //借款编号
	private String ExpenseType;                 //费用类型
	private User user;     //申请人
	private Department department;  //项目部门的名称
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

	public String getExpenseType() {
		return ExpenseType;
	}

	public void setExpenseType(String expenseType) {
		ExpenseType = expenseType;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	@OneToMany(
			mappedBy = "expense", cascade = { CascadeType.PERSIST, CascadeType.REFRESH},
			targetEntity = CostDetails.class, fetch = FetchType.EAGER)
	public List<CostDetails> getCostDetails() {
		return costDetails;
	}

	public void setCostDetails(List<CostDetails> costDetails) {
		this.costDetails = costDetails;
	}
	
	public String getBorrowNumber() {
		return borrowNumber;
	}

	public void setBorrowNumber(String borrowNumber) {
		this.borrowNumber = borrowNumber;
	}

	public String getPayTitle() {
		return payTitle;
	}
	
	public void setPayTitle(String payTitle) {
		this.payTitle = payTitle;
	}
	
	public String getSubmitTime() {
		return submitTime;
	}
	
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public void setUser(User user) {

		this.user = user;
	}

	@OneToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {

		return user;
	}


	public void setDepartment(Department department) {

		this.department = department;
	}

	@OneToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = Department.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id")
	public Department getDepartment() {

		return department;
	}
	
	
}
