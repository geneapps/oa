package com.ydt.oa.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.giro.common.entity.StringUUIDEntity;

/**
 *分包商
 * @author hcq
 *
 */
@Entity
@Table(name = "oa_contractor")
public class Contractor extends StringUUIDEntity implements Serializable {
	
	private static final long serialVersionUID = -7038480078292736041L;
	
	private String contractorName;		//分包商名称
	private String contact;			//分包商负责人
	private String phone;			//负责人电话
	private String credit;          // 信誉等级
	private Project project;    // 合作项目
	
	public String getContractorName() {
	
		return contractorName;
	}
	
	public void setContractorName(String contractorName) {
	
		this.contractorName = contractorName;
	}
	
	public String getContact() {
	
		return contact;
	}
	
	public void setContact(String contact) {
	
		this.contact = contact;
	}
	
	public String getPhone() {
	
		return phone;
	}
	
	public void setPhone(String phone) {
	
		this.phone = phone;
	}
	
	public String getCredit() {
	
		return credit;
	}
	
	public void setCredit(String credit) {
	
		this.credit = credit;
	}
	
	@OneToOne(cascade = { CascadeType.REFRESH }, targetEntity = Project.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id")
	public Project getProject() {
	
		return project;
	}
	
	public void setProject(Project project) {
	
		this.project = project;
	}
	


}

