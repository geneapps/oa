package com.ydt.oa.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.giro.common.entity.StringUUIDEntity;
/**
 * 分包商下属工人
 * @author hcq
 *
 */
@Entity
@Table(name = "oa_worker")
public class Worker extends StringUUIDEntity implements Serializable {

	private static final long serialVersionUID = 8487831984793179929L;
	
	private String workerName;   //工人名
	private String workerType;  //工种类型
	private String telephone; //联系方式
	private Contractor contractor;   //分包商
	private String remark;
	
	public String getWorkerName() {
	
		return workerName;
	}
	
	public void setWorkerName(String workerName) {
	
		this.workerName = workerName;
	}
	
	public String getWorkerType() {
	
		return workerType;
	}
	
	public void setWorkerType(String workerType) {
	
		this.workerType = workerType;
	}
	
	public String getTelephone() {
	
		return telephone;
	}
	
	public void setTelephone(String telephone) {
	
		this.telephone = telephone;
	}
	
	@ManyToOne(cascade = { CascadeType.REFRESH }, targetEntity = Contractor.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "contractor_id")
	public Contractor getContractor() {
	
		return contractor;
	}
	
	public void setContractor(Contractor contractor) {
	
		this.contractor = contractor;
	}
	
	public String getRemark() {
	
		return remark;
	}
	
	public void setRemark(String remark) {
	
		this.remark = remark;
	}



	
	
	


	

}
