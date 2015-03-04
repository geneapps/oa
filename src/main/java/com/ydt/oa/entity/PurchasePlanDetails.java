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
 * 采购计划单
 * @author Cruise
 *
 */
@Entity
@Table(name = "oa_purchaseplandetails")
public class PurchasePlanDetails extends StringUUIDEntity implements Serializable{

	private static final long serialVersionUID = 2238607288236252572L;
	
	private static final Logger logger = Logger.getLogger(PurchasePlanDetails.class);
	
	private String category;    //品类
	private String type;        //型号
	private String unit;        //单位
	private long houseNumber; //库房现存数量
	private long number;      //需要的采购数量
	private String budgetPrice; //预算单价
	private PurchasePlan purchasePlan;   //采购计划编号
	private Material material;   //材料
	
	@OneToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = Material.class, fetch = FetchType.EAGER)
	@JoinColumn
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@ManyToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = PurchasePlan.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "purchasePlan_id")
	public PurchasePlan getPurchasePlan() {
		return purchasePlan;
	}

	public void setPurchasePlan(PurchasePlan purchasePlan) {
		this.purchasePlan = purchasePlan;
	}

	
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	
	public String getUnit() {
		return unit;
	}
	
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
	public long getHouseNumber() {
		return houseNumber;
	}
	
	
	
	
	public void setHouseNumber(long houseNumber) {
		this.houseNumber = houseNumber;
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
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
     public void copyFromMaterial(Material material){
		
		this.material = material;
		this.category=material.getCategory();   
		this.type=material.getMaterialType();
		this.unit = material.getUnit();
		this.budgetPrice = material.getPrice();

	}

}
