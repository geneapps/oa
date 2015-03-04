package com.ydt.oa.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.giro.common.entity.StringUUIDEntity;
import com.giro.common.util.AmountUtils;

/**
 * 用户数据库持久化Bean
 * 
 * @author huchuqiao
 * 
 */
@Entity
@Table(name = "oa_paymentorder")
// 付款单
public class PaymentOrder extends StringUUIDEntity implements Serializable {

	private static final long serialVersionUID = -1468296967149240306L;
	// private static final Logger logger =
	// Logger.getLogger(PaymentOrder.class);
	public static final int ORDER_STATUS_WAITINGAPPROVE = -1; // 等待审批
	public static final int ORDER_STATUS_PAYING = 0; // 等待付款
	public static final int ORDER_STATUS_PAID = 1; // 全部付清
	public static final int ORDER_STATUS_PAIDPART = 2; // 部分付款
	private String title; // 付款单标题
	private String actualAmount; // 已经付款金额
	private String payAmount; // 付款金额
	private String readyAmount; // 准备付款金额 实际付款=付款金额，状态为paid；实际付款=准备付款，状态为部分付款
	private String requiredPayTime; // 要求付款时间
	private String payTime; // 付款时间
	private String description; // 付款事由
	// private int paymentType; // 付款方式;现金 转账 支票
	private String businessType; // 业务类型，为申请单类型
	private Application application; // 申请ID
	private int status; // 付款状态
	private User user; // 经办人
	private List<PaymentLog> paymentLogs;
	private String updateTime;// 更新时间
	
	
	//收款方的信息
	private String payee; // 收款方名称
	private String account; // 账户信息
	private String contractNo; // 合同编号
	

	
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

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getActualAmount() {

		return actualAmount;
	}

	public void setActualAmount(String actualAmount) {

		this.actualAmount = actualAmount;
	}

	public String getPayAmount() {

		return payAmount;
	}

	public void setPayAmount(String payAmount) {

		this.payAmount = payAmount;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	@OneToOne(
				cascade = { CascadeType.REFRESH },
				targetEntity = Application.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "application_id")
	public Application getApplication() {

		return application;
	}

	public void setApplication(Application application) {

		this.application = application;
	}

	public int getStatus() {

		return status;
	}

	public void setStatus(int status) {

		this.status = status;
	}

	public String getRequiredPayTime() {

		return requiredPayTime;
	}

	public void setRequiredPayTime(String requiredPayTime) {

		this.requiredPayTime = requiredPayTime;
	}

	public String getPayTime() {

		return payTime;
	}

	public void setPayTime(String payTime) {

		this.payTime = payTime;
	}

	@OneToMany(
				mappedBy = "paymentOrder", cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
						CascadeType.MERGE },
				targetEntity = PaymentLog.class, fetch = FetchType.LAZY)
	public List<PaymentLog> getPaymentLogs() {

		return paymentLogs;
	}

	public void setPaymentLogs(List<PaymentLog> paymentLogs) {

		this.paymentLogs = paymentLogs;
	}

	public String getUpdateTime() {

		return updateTime;
	}

	public void setUpdateTime(String updateTime) {

		this.updateTime = updateTime;
	}

	public String getBusinessType() {

		return businessType;
	}

	public void setBusinessType(String businessType) {

		this.businessType = businessType;
	}

	public String getReadyAmount() {

		return readyAmount;
	}

	public void setReadyAmount(String readyAmount) {

		this.readyAmount = readyAmount;
	}

	@Transient
	public String getPayable() {

		return AmountUtils.subtract(readyAmount, actualAmount);
	}


	
	
}
