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
@Table(name = "oa_contractdetails")
public class ContractDetails extends StringUUIDEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Material material;   //材料
	
	private String unit;           //单位
	
	private long number;        //数量
	
	private String budgetPrice;   //预算单价
	
	private Contract contract;    //合同编号



	@ManyToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = Material.class, fetch = FetchType.EAGER)
	@JoinColumn
	public Material getMaterial() {
	
		return material;
	}

	
	public void setMaterial(Material material) {
	
		this.material = material;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public String getBudgetPrice() {
		return budgetPrice;
	}

	public void setBudgetPrice(String budgetPrice) {
		this.budgetPrice = budgetPrice;
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
	
	public void copyFromMaterial(Material material){
		
		this.material = material;
		this.unit = material.getUnit();
		this.budgetPrice = material.getPrice();

	}
    

	
}
