package com.ydt.oa.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
//import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
//import javax.persistence.OrderBy;
import javax.persistence.Table;
//import org.apache.log4j.Logger;

import com.giro.common.entity.StringUUIDEntity;



/**
 * 用户数据库持久化Bean
 * @author caochun
 *
 */
@Entity
@Table(name = "oa_role")
public class Role extends StringUUIDEntity implements Serializable{

	private static final long serialVersionUID = -1947699494671548674L;

	public static final int ROLE_STATUS_VALID = 1;
	public static final int ROLE_STATUS_INVALID = 0;
	public static final int ROLE_STATUS_DELETE = 99;
	
	private String name;
	private String description;
	private Department department;
	private Set<OaAction> actions;
	private Set<User> users;
//	private Set<ApproveFlow> approveFlows;
	private String updateTime;
	private int status;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getName() {
	
		return name;
	}
	
	public void setName(String name) {
	
		this.name = name;
	}
	
	public String getDescription() {
	
		return description;
	}
	
	public void setDescription(String description) {
	
		this.description = description;
	}
	
	@ManyToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = Department.class, fetch = FetchType.EAGER)
	@JoinColumn
	public Department getDepartment() {
	
		return department;
	}
	

	public void setDepartment(Department department) {
	
		this.department = department;
	}



	@ManyToMany(targetEntity = OaAction.class,cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
        CascadeType.MERGE },fetch=FetchType.EAGER)
	@JoinTable(name="oa_role_action")
	public Set<OaAction> getActions() {
	
		return actions;
	}

	
	public void setActions(Set<OaAction> actions) {
	
		this.actions = actions;
	}

	@OneToMany(
			mappedBy = "role", cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
		            CascadeType.MERGE },
			targetEntity = User.class, fetch = FetchType.LAZY)
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

//	@OneToMany(
//			mappedBy = "role", cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
//		            CascadeType.MERGE },
//			targetEntity = ApproveFlow.class, fetch = FetchType.LAZY)
//	public Set<ApproveFlow> getApproveFlows() {
//	
//		return approveFlows;
//	}
//
//	
//	public void setApproveFlows(Set<ApproveFlow> approveFlows) {
//	
//		this.approveFlows = approveFlows;
//	}

	


	
	
}
