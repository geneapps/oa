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

import org.apache.log4j.Logger;

import com.giro.common.entity.StringUUIDEntity;

/**
 * 采购申请单
 * 
 * @author Cruise
 * 
 */
@Entity
@Table(name = "oa_purchaseapply")
public class PurchaseApply extends StringUUIDEntity implements Serializable {

	private static final long serialVersionUID = -8752099756626650954L;
	public static final int PURCHASE_STATUS_WAITINGAPPROVE = 0; // 等待审批
	public static final int PURCHASE_STATUS_WAITINGPAY = 1; // 等待付款
	public static final int PURCHASE_STATUS_STARTBUY = 2; // 开始采购
	public static final int PURCHASE_STATUS_STARTEXPENSE = 3; // 入库可以开始报销
	public static final int PURCHASE_STATUS_FINISH = 4; // 完成
	public static final int PURCHASE_STATUS_APPROVEFAIL = 5; // 审批没通过
	public static final int PURCHASE_STATUS_DEL = 99; // 删除
	// private static final Logger logger =
	// Logger.getLogger(PurchaseApply.class);
	private String title; // 采购标题
	private String totalPrice; // 预算采购总价
	private String advancePay; // 预付款
	private String prePurchaseTime; // 预计采购时间
	private String instruction; // 采购说明
	private String applyTime; // 申请时间
	private Project project; // 项目编号
	private int status;
	private User user;
	private User buyUser; // 采购人
	private List<PurchaseApplyDetails> purchaseApplyDetails; // 采购清单明细

	
	
	@OneToMany(
			mappedBy = "purchaseApply", cascade = { CascadeType.PERSIST, CascadeType.REFRESH },
			targetEntity = PurchaseApplyDetails.class, fetch = FetchType.EAGER)
	public List<PurchaseApplyDetails> getPurchaseApplyDetails() {

		return purchaseApplyDetails;
	}

	public void setPurchaseApplyDetails(List<PurchaseApplyDetails> purchaseApplyDetails) {

		this.purchaseApplyDetails = purchaseApplyDetails;
	}

	@ManyToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = Project.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id")
	public Project getProject() {

		return project;
	}

	public void setProject(Project project) {

		this.project = project;
	}

	public int getStatus() {

		return status;
	}

	public void setStatus(int status) {

		this.status = status;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getTotalPrice() {

		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {

		this.totalPrice = totalPrice;
	}

	public String getAdvancePay() {

		return advancePay;
	}

	public void setAdvancePay(String advancePay) {

		this.advancePay = advancePay;
	}

	public String getPrePurchaseTime() {

		return prePurchaseTime;
	}

	public void setPrePurchaseTime(String prePurchaseTime) {

		this.prePurchaseTime = prePurchaseTime;
	}

	public String getInstruction() {

		return instruction;
	}

	public void setInstruction(String instruction) {

		this.instruction = instruction;
	}

	public String getApplyTime() {

		return applyTime;
	}

	public void setApplyTime(String applyTime) {

		this.applyTime = applyTime;
	}

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
			targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn
	public User getBuyUser() {
	
		return buyUser;
	}

	
	public void setBuyUser(User buyUser) {
	
		this.buyUser = buyUser;
	}
	
	
}
