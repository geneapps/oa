package com.ydt.oa.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.giro.common.entity.StringUUIDEntity;

/**
 * 用户数据库持久化Bean
 * 
 * @author huchuqiao
 * 
 */
@Entity
@Table(name = "oa_application")
// 申请表
public class Application extends StringUUIDEntity implements Serializable {

	private static final long serialVersionUID = -1468296967149240306L;
	public static final int APP_STATUS_APPROVED = 1; // 审批通过
	public static final int APP_STATUS_NOTAPPROVED = 2; // 审批不通过
	public static final int APP_STATUS_WAITINGAPPROVE = -1;// 等待审批
	public static final int APP_STATUS_APPROVEING = 0;// 正在审批
	public static final int APP_STATUS_CANCEL = 4;
	public static final int APP_STATUS_DELETE = 99;// 删除标志位
	public static final String APP_TYPE_PURCHASEPLAN = "PURCHASEPLAN";// 采购计划申请
	public static final String APP_TYPE_PURCHASE = "PURCHASE";// 采购申请
	public static final String APP_TYPE_MANCONTRACT = "MANCONTRACT";// 人工合同申请
	public static final String APP_TYPE_MATERIALCONTRACT = "CONTRACT";// 材料合同申请
	public static final String APP_TYPE_PROJECT="PROJECT";	//项目申请
	public static final String APP_TYPE_REQUESTMONEYMATERIAL="REQUESTMONEYMATERIAL";	//材料请款申请
	public static final String APP_TYPE_REQUESTMONEYENGINEER="REQUESTMONEYENGINEER";	//工程请款申请
	public static final String APP_TYPE_BORROWMONEY="BORROWMONEY";	//借款申请
	public static final String APP_TYPE_EXPENSEMAN="EXPENSEMAN";	//人工费用报销申请
	public static final String APP_TYPE_EXPENSEMATERIAL="EXPENSEMATERIAL";	//材料费用报销申请
	public static final String APP_TYPE_EXPENSEOTHER="EXPENSEOTHER";	//间接费用报销申请
	public static final String APP_TYPE_ADMIN="ADMIN";	//行政申请
	
	private String applyType; // 申请类型
	private String applyNo; // 申请对应的id
	private String title; // 申请标题
	private String description; // 申请描述
	private User user; // 员工ID
	private Department department; // 部门ID
	private List<ApproveFlow> approveFlows; // 流程编号
	private String createTime; // 申请时间
	private int status; // 申请状态
	private PaymentOrder paymentOrder; // 付款单


	@OneToOne(
			mappedBy = "application", cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
					CascadeType.MERGE },
			targetEntity = PaymentOrder.class, fetch = FetchType.LAZY)
	public PaymentOrder getPaymentOrder() {
	
		return paymentOrder;
	}

	
	public void setPaymentOrder(PaymentOrder paymentOrder) {
	
		this.paymentOrder = paymentOrder;
	}

	public String getCreateTime() {

		return createTime;
	}

	public void setCreateTime(String createTime) {

		this.createTime = createTime;
	}

	@OneToMany(
			mappedBy = "application", cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
					CascadeType.MERGE },
			targetEntity = ApproveFlow.class, fetch = FetchType.LAZY)
	@OrderBy("orderBy DESC")
	public List<ApproveFlow> getApproveFlows() {

		return approveFlows;
	}

	public void setApproveFlows(List<ApproveFlow> approveFlows) {

		this.approveFlows = approveFlows;
	}

	@OneToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {

		return user;
	}

	public void setUser(User user) {

		this.user = user;
	}

	@OneToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = Department.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id")
	public Department getDepartment() {

		return department;
	}

	public void setDepartment(Department department) {

		this.department = department;
	}

	public String getApplyType() {

		return applyType;
	}

	public void setApplyType(String applyType) {

		this.applyType = applyType;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public int getStatus() {

		return status;
	}

	public void setStatus(int status) {

		this.status = status;
	}

	public String getApplyNo() {

		return applyNo;
	}

	public void setApplyNo(String applyNo) {

		this.applyNo = applyNo;
	}
}
