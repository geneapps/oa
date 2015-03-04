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
import com.giro.common.entity.StringUUIDEntity;


/**
 * 用户数据库持久化Bean
 * 
 * @author zld
 * 
 */
@Entity
@Table(name = "oa_contract")
public class Contract extends StringUUIDEntity implements Serializable {
	
	public static final String FILE_TYPE="CONTRACT";
	
	public static final int CONTRACT_WAITINGAPPROVE = 0;
	public static final int CONTRACT_VALID = 1;
	public static final int CONTRACT_INVALID = 2;
	public static final int CONTRACT_DELETE = 99;
	
	public static final int CONTRACT_TYPE_MAN = 0;
	public static final int CONTRACT_TYPE_MATERIAL = 1;

	private static final long serialVersionUID = 8891403437494319356L;
	private String title; // 合同标题
	private int contractType; //合同类型 1 材料 0 人工
	private int meterialContractType;//材料合同类型 -1为非材料合同 0为水电材料合同 1为装饰材料合同
	private String companyA; // 甲方
	private String companyB; // 乙方
	private String b_contact; //乙方经办人
	private String b_contact_phone;//乙方经办人电话
	private String contractReason; // 合同缘由
	private String fileName; // 文件名
	private String updateTime;
	private int status;
	private List<ContractDetails> contractDetails; // 合同详细
	private List<ManContractDetails> manContractDetails; // 合同详细
	private Project project;
	

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




	public String getB_contact() {
	
		return b_contact;
	}



	
	public void setB_contact(String b_contact) {
	
		this.b_contact = b_contact;
	}



	
	public String getB_contact_phone() {
	
		return b_contact_phone;
	}



	
	public void setB_contact_phone(String b_contact_phone) {
	
		this.b_contact_phone = b_contact_phone;
	}



	public int getMeterialContractType() {
	
		return meterialContractType;
	}


	
	public void setMeterialContractType(int meterialContractType) {
	
		this.meterialContractType = meterialContractType;
	}


	@OneToMany(
			mappedBy = "contract", cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
					CascadeType.MERGE },
			targetEntity = ManContractDetails.class, fetch = FetchType.LAZY)
	public List<ManContractDetails> getManContractDetails() {
	
		return manContractDetails;
	}

	
	public void setManContractDetails(List<ManContractDetails> manContractDetails) {
	
		this.manContractDetails = manContractDetails;
	}



	@OneToMany(
			mappedBy = "contract", cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
					CascadeType.MERGE },
			targetEntity = ContractDetails.class, fetch = FetchType.LAZY)
	public List<ContractDetails> getContractDetails() {

		return contractDetails;
	}

	public void setContractDetails(List<ContractDetails> contractDetails) {

		this.contractDetails = contractDetails;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getCompanyA() {

		return companyA;
	}

	public void setCompanyA(String companyA) {

		this.companyA = companyA;
	}

	public String getCompanyB() {

		return companyB;
	}

	public void setCompanyB(String companyB) {

		this.companyB = companyB;
	}

	public String getContractReason() {

		return contractReason;
	}

	public void setContractReason(String contractReason) {

		this.contractReason = contractReason;
	}

	public String getFileName() {

		return fileName;
	}

	public void setFileName(String fileName) {

		this.fileName = fileName;
	}

	
	public String getUpdateTime() {
	
		return updateTime;
	}

	
	public void setUpdateTime(String updateTime) {
	
		this.updateTime = updateTime;
	}

	
	public int getStatus() {
	
		return status;
	}

	
	public void setStatus(int status) {
	
		this.status = status;
	}

	
	public int getContractType() {
	
		return contractType;
	}

	
	public void setContractType(int contractType) {
	
		this.contractType = contractType;
	}

}
