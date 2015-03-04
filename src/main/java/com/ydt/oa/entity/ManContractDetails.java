package com.ydt.oa.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.log4j.Logger;

import com.giro.common.entity.StringUUIDEntity;
/**
 * 用户数据库持久化Bean
 * @author ZLD
 *
 */
@Entity
@Table(name = "oa_mancontractDetails")
public class ManContractDetails extends StringUUIDEntity implements Serializable {

	private static final long serialVersionUID = 1648023986253526634L;

	/**
	 * 
	 */


	private Contractor contractor ;   //分包商
	
	private String contact;
	private String phone;
	private String credit;
	
	private Contract contract;    //合同编号



	@ManyToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = Contractor.class, fetch = FetchType.EAGER)
	@JoinColumn
	public Contractor getContractor() {
	
		return contractor;
	}

	
	public void setContractor(Contractor contractor) {
	
		this.contractor = contractor;
	}

	@ManyToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = Contract.class, fetch = FetchType.EAGER)
	@JoinColumn
	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
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
	
	public void copyFromContractor(Contractor contractor){
		
		this.contractor = contractor;
		this.contact = contractor.getContact();
		this.phone = contractor.getPhone();
		this.credit = contractor.getCredit();

	}

    

	
}
