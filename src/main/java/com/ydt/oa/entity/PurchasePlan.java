

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
import com.giro.common.util.DateUtils;

/**
 * 采购计划清单
 * @author Cruise
 *
 */
@Entity
@Table(name = "oa_purchaseplan")
public class PurchasePlan extends StringUUIDEntity implements
		Serializable {

	private static final long serialVersionUID = 2953537422812119973L;
	public static final int PURCHASEPLAN_WAITINGAPPROVE = 0;   //等待审批
	public static final int PURCHASEPLAN_VALID = 1;            //有效地
	public static final int PURCHASEPLAN_INVALID = 2;           //无效的
	public static final int PURCHASEPLAN_DELETE = 99;          //已被删除

	// 日志记录对象
	//private static final Logger logger = Logger.getLogger(PurchasePlan.class);

	private String title;   //采购计划标题

	private String totalPrice;			//预算采购总价

	private String instruction;			//采购计划说明

	private String applytime;			//申请时间
	 
	
	private Project project;           //项目编号
	
	private int status;
	
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	private List<PurchasePlanDetails> purchasePlanDetails;   //采购计划清单明细
	
	@OneToMany(
			mappedBy = "purchasePlan", cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
					CascadeType.MERGE },
			targetEntity = PurchasePlanDetails.class, fetch = FetchType.LAZY)
	public List<PurchasePlanDetails> getPurchasePlanDetails() {
		return purchasePlanDetails;
	}

	public void setPurchasePlanDetails(List<PurchasePlanDetails> purchasePlanDetails) {
		this.purchasePlanDetails = purchasePlanDetails;
	}


	
	public String getTotalPrice() {
		return totalPrice;
	}
  


	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public String getApplytime() {
		if(applytime==null || applytime.equals("")){
			return DateUtils.getNowDateStr();
		}
		else
		return applytime;
	}

	public void setApplytime(String applytime) {
		this.applytime = applytime;
	}



}











