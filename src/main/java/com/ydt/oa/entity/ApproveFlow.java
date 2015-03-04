package com.ydt.oa.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.giro.common.entity.StringUUIDEntity;

/**
 * 用户数据库持久化Bean
 * @author huchuqiao
 *
 */
@Entity
@Table(name = "oa_approveflow") //审批流程表
public class ApproveFlow extends StringUUIDEntity implements Serializable{
	
	private static final long serialVersionUID = 6187865911066097906L;
	
	public static final int FLOW_STATUS_APPROVED = 1; // 审批通过
	public static final int FLOW_STATUS_NOTAPPROVED = 2; // 审批不通过
	public static final int FLOW_STATUS_WAITINGAPPROVE = 0;// 等待审批 
	public static final int FLOW_STATUS_CANCEL = 4;
	
	private String flowView; //审批意见
	private String flowDate; //审批时间
	private int status; // 状态位
	private User user;  //审批之后填的用户
	private int actionType; // 审批人类型，0 角色 1权限 2用户
	private String actionId; //角色、权限、用户的ID
	private  Application application;  //申请编号
	private int orderBy;  //序号
	private boolean activate; // 可审批 只有当前是可审批状态才能做审批操作
	private String remark;
	
	
	public String getRemark() {
	
		return remark;
	}

	
	public void setRemark(String remark) {
	
		this.remark = remark;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	@ManyToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = Application.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "application_id")
	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getFlowView() {
		return flowView;
	}
	
	public void setFlowView(String flowView) {
		this.flowView = flowView;
	}
	
	public String getFlowDate() {
		return flowDate;
	}
	
	public void setFlowDate(String flowDate) {
		this.flowDate = flowDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
     
	@OneToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
//	@OneToOne(
//			cascade = { CascadeType.REFRESH },
//			targetEntity = Role.class, fetch = FetchType.EAGER)
//	@JoinColumn(name="role_id")
//	public Role getRole() {
//		return role;
//	}
//
//	public void setRole(Role role) {
//		this.role = role;
//	}

	
	
	public boolean isActivate() {
	
		return activate;
	}

	
	
	public int getActionType() {
	
		return actionType;
	}

	
	public void setActionType(int actionType) {
	
		this.actionType = actionType;
	}

	
	public String getActionId() {
	
		return actionId;
	}

	
	public void setActionId(String actionId) {
	
		this.actionId = actionId;
	}

	public void setActivate(boolean activate) {
	
		this.activate = activate;
	}
   
	
	
	


}
