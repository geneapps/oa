package com.ydt.oa.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

import javax.persistence.OneToOne;
import javax.persistence.Table;


import com.giro.common.entity.StringUUIDEntity;

/**
 * 流程配置表
 * @author caochun
 *
 */
@Entity
@Table(name = "oa_approveflowconfig") //审批流程表
public class ApproveFlowConfig extends StringUUIDEntity implements Serializable{

	public final static int ACTIONTYPE_ROLE = 0;
	public final static int ACTIONTYPE_ACTION = 1;
	public final static int ACTIONTYPE_USER = 2;
	
	private static final long serialVersionUID = -1619163562773820145L;
	private int actionType; // 审批类型 0 角色 1权限 2用户
	private String  actionId; //角色或者权限id
	private String appType;  //流程类型
	private int orderBy;  //序号
	private String remark; // 备注
	
	
	
//	@OneToOne(
//			cascade = { CascadeType.REFRESH },
//			targetEntity = Role.class, fetch = FetchType.EAGER)
//	@JoinColumn
//	public Role getRole() {
//	
//		return role;
//	}
//	
//	public void setRole(Role role) {
//	
//		this.role = role;
//	}
	
	
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

	public String getAppType() {
	
		return appType;
	}
	
	public void setAppType(String appType) {
	
		this.appType = appType;
	}
	
	public int getOrderBy() {
	
		return orderBy;
	}
	
	public void setOrderBy(int orderBy) {
	
		this.orderBy = orderBy;
	}


	@Transient
	public String getRemark() {
	
		return remark;
	}


	
	public void setRemark(String remark) {
	
		this.remark = remark;
	}
	
	


}
