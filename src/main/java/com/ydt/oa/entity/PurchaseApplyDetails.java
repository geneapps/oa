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
 * 采购清单
 * @author Cruise
 *
 */
@Entity
@Table(name = "oa_purchaseapplydetails")
public class PurchaseApplyDetails extends StringUUIDEntity implements Serializable {

	private static final long serialVersionUID = -4963333001972376517L;
	private static final Logger logger = Logger.getLogger(PurchaseApplyDetails.class);

	private String catagory;    //品类
	private String unit;        //单位
	private String type;       //型号
	private String brand;      //品牌
	private long houseNumber; //入库数量
	private long number;      //需要采购的数量
	private String budgetPrice; //预算单价
	private String refPrice;    //参考单价
	private String actualPrice;    //实际购买价格
	private String isContract;   //有无合同
	private String isBill;      //有无发票
	private PurchaseApply purchaseApply;   //申请编号
	
	
    
	public String getBrand() {
	
		return brand;
	}

	
	public void setBrand(String brand) {
	
		this.brand = brand;
	}


	private Material material;   //材料
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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
			targetEntity = PurchaseApply.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "purchaseApply_id")
	public PurchaseApply getPurchaseApply() {
		return purchaseApply;
	}
	public void setPurchaseApply(PurchaseApply purchaseApply) {
		this.purchaseApply = purchaseApply;
	}
	public String getCatagory() {
		return catagory;
	}
	public void setCatagory(String catagory) {
		this.catagory = catagory;
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
	public String getRefPrice() {
		return refPrice;
	}
	public void setRefPrice(String refPrice) {
		this.refPrice = refPrice;
	}
	
	public String getIsContract() {
		return isContract;
	}

	public void setIsContract(String isContract) {
		this.isContract = isContract;
	}

	public String getIsBill() {
		return isBill;
	}
	public void setIsBill(String isBill) {
		this.isBill = isBill;
	}
	
	 public void copyFromMaterial(Material material){
			
			this.material = material;
			this.catagory=material.getCategory();   
			this.type=material.getMaterialType();
			this.unit = material.getUnit();
			this.refPrice = material.getPrice();
			this.brand=material.getBrand();
		}

	
	public String getActualPrice() {
	
		return actualPrice;
	}

	
	public void setActualPrice(String actualPrice) {
	
		this.actualPrice = actualPrice;
	}
	 
	 

	
}
