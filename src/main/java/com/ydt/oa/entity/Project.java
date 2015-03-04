package com.ydt.oa.entity;

import java.io.Serializable;
import java.util.Set;

import javax.management.loading.PrivateClassLoader;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
//import org.apache.log4j.Logger;

import com.giro.common.entity.StringUUIDEntity;

/**
 * 用户数据库持久化Bean
 * @author caochun
 *
 */
@Entity
@Table(name = "oa_project")
public class Project extends StringUUIDEntity implements Serializable{

	public static final int PROJECT_WAITINGAPPROVE = 0;
	public static final int PROJECT_VALID = 1;
	public static final int PROJECT_INVALID = 2;
	public static final int PROJECT_DELETE = 99;
	
	private static final long serialVersionUID = 2913581292278152186L;
	
	public static final int PROJECT_STATUS_NORMAL = 1; //正常
	public static final int PROJECT_STATUS_WARRANTY = 2;//保修 
	public static final int PROJECT_STATUS_FINISHED = 3;//结束
	public static final int PROJECT_STATUS_CANCEL = 4;//取消
	public static final int PROJECT_STATUS_WAITAPPROVE = 0;//等待审批

	private String name;
	private int status;
	private String amount;
	private String address;
	private String startDate;
	private String endDate;
	private String description;
	private Department department;
	private User manager;
	private String updateTime;
	private Set<Contract> contracts;
	

	@OneToMany(
			mappedBy = "project", cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
		            CascadeType.MERGE },
			targetEntity = Contract.class, fetch = FetchType.LAZY)
	public Set<Contract> getContracts() {
	
		return contracts;
	}

	
	public void setContracts(Set<Contract> contracts) {
	
		this.contracts = contracts;
	}

	
	

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}



	@OneToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn
	public User getManager() {
	
		return manager;
	}

	
	public void setManager(User manager) {
	
		this.manager = manager;
	}

	public String getName() {
	
		return name;
	}
	
	public void setName(String name) {
	
		this.name = name;
	}
	
	public int getStatus() {
	
		return status;
	}
	
	public void setStatus(int status) {
	
		this.status = status;
	}
	
	public String getAmount() {
	
		return amount;
	}
	
	public void setAmount(String amount) {
	
		this.amount = amount;
	}
	
	public String getAddress() {
	
		return address;
	}
	
	public void setAddress(String address) {
	
		this.address = address;
	}
	
	public String getStartDate() {
	
		return startDate;
	}
	
	public void setStartDate(String startDate) {
	
		this.startDate = startDate;
	}
	
	public String getEndDate() {
	
		return endDate;
	}
	
	public void setEndDate(String endDate) {
	
		this.endDate = endDate;
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
	@JoinColumn(name = "department_id")
	public Department getDepartment() {
	
		return department;
	}
	
	public void setDepartment(Department department) {
	
		this.department = department;
	}

	
}
