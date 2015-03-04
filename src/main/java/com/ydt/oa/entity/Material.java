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
 * 用户数据库持久化Bean
 * @author zld
 *
 */
@Entity
@Table(name = "oa_material")
public class Material extends StringUUIDEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String materialName;   //材料名
	private String materialType;  //材料类型
	private String brand; //品牌
	private String price;        //价格
	private String category;     //种类
	private String unit;       //单位
	private Supplier supplier;   //供应商
	private String remark;



	
	
	public String getRemark() {
	
		return remark;
	}


	
	public void setRemark(String remark) {
	
		this.remark = remark;
	}


	public String getMaterialName() {
	
		return materialName;
	}

	
	public void setMaterialName(String materialName) {
	
		this.materialName = materialName;
	}

	
	public String getMaterialType() {
	
		return materialType;
	}

	
	public void setMaterialType(String materialType) {
	
		this.materialType = materialType;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH }, targetEntity = Supplier.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "supplier_id")
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}


	
	public String getBrand() {
	
		return brand;
	}


	
	public void setBrand(String brand) {
	
		this.brand = brand;
	}
	
	

}
