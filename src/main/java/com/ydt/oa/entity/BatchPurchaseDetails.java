package com.ydt.oa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.log4j.Logger;

import com.giro.common.entity.StringUUIDEntity;

/**
 * 用户数据库持久化Bean
 * 
 * @author caochun
 * 
 */
@Entity
@Table(name = "oa_batchpurchasedetails")
// 分批采购清单表
public class BatchPurchaseDetails extends StringUUIDEntity implements Serializable {

	private static final Logger logger = Logger.getLogger(BatchPurchaseDetails.class);
	private static final long serialVersionUID = 5499554226092818380L;
	private String budgetPrice; // 预算单价
	private String category; // 品类
	private String houseNumber; // 库房数量
	private long number; // 采购数量
	private String refPrice; // 参考单价
	private String type; // 型号
	private String unit; // 单位

	public String getBudgetPrice() {

		return budgetPrice;
	}

	public String getCategory() {

		return category;
	}

	public String getHouseNumber() {

		return houseNumber;
	}

	public long getNumber() {

		return number;
	}

	public String getRefPrice() {

		return refPrice;
	}

	public String getType() {

		return type;
	}

	public String getUnit() {

		return unit;
	}

	public void setBudgetPrice(String budgetPrice) {

		this.budgetPrice = budgetPrice;
	}

	public void setCategory(String category) {

		this.category = category;
	}

	public void setHouseNumber(String houseNumber) {

		this.houseNumber = houseNumber;
	}

	public void setNumber(long number) {

		this.number = number;
	}

	public void setRefPrice(String refPrice) {

		this.refPrice = refPrice;
	}

	public void setType(String type) {

		this.type = type;
	}

	public void setUnit(String unit) {

		this.unit = unit;
	}
}
