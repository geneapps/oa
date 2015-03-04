package com.ydt.oa.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;
//import org.apache.log4j.Logger;

import com.giro.common.entity.StringUUIDEntity;



/**
 * 用户数据库持久化Bean
 * @author caochun
 *
 */
@Entity
@Table(name = "oa_department")
public class Department extends StringUUIDEntity implements Serializable{

	private static final long serialVersionUID = 4067136635130658023L;
	public static final int DEP_TYPE_NORMAL = 0;// 普通部门
	public static final int DEP_TYPE_PROJECT = 1;// 项目
	public static final int DEP_TYPE_HOUSE = 2;// 仓库
	
	public static final int DEP_STATUS_VALID = 1;
	public static final int DEP_STATUS_INVALID = 0;
	public static final int DEP_STATUS_DELETE = 99;// 删除部门
	
	private String depName;
	private int depType;
	private int status;
	private Department parent;
	private String description;
	private User manager;
	private List<Role> roles;
	private Set<User> users;
	private String updateTime;
	private List<Department> childs;
	
	
	
	@OneToMany(
			mappedBy = "parent", cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
		            CascadeType.MERGE },
			targetEntity = Department.class, fetch = FetchType.LAZY)
	@Where(clause="status <> 99")
	public List<Department> getChilds() {
	
		return childs;
	}

	
	public void setChilds(List<Department> childs) {
	
		this.childs = childs;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getDepName() {
	
		return depName;
	}
	
	public void setDepName(String depName) {
	
		this.depName = depName;
	}
	
	public int getDepType() {
	
		return depType;
	}
	
	public void setDepType(int depType) {
	
		this.depType = depType;
	}
	
	public int getStatus() {
	
		return status;
	}
	
	public void setStatus(int status) {
	
		this.status = status;
	}
	
	@ManyToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = Department.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id")
	public Department getParent() {
	
		return parent;
	}
	
	public void setParent(Department parent) {
	
		this.parent = parent;
	}
	
	public String getDescription() {
	
		return description;
	}
	
	public void setDescription(String description) {
	
		this.description = description;
	}

	@ManyToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public User getManager() {
	
		return manager;
	}

	
	public void setManager(User manager) {
	
		this.manager = manager;
	}
	
	@OneToMany(
			mappedBy = "department", cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
		            CascadeType.MERGE },
			targetEntity = Role.class, fetch = FetchType.LAZY)
	@Where(clause="status <> 99")
	public List<Role> getRoles() {
	
		return roles;
	}

	
	public void setRoles(List<Role> roles) {
	
		this.roles = roles;
	}

	@OneToMany(
			mappedBy = "department", cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
		            CascadeType.MERGE },
			targetEntity = User.class, fetch = FetchType.LAZY)
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}
