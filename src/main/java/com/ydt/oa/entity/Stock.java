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
 * 用户数据库持久化Bean
 * @author caochun
 *
 */
@Entity
@Table(name = "oa_stock") //库存项表
public class Stock extends StringUUIDEntity implements Serializable{

	private static final long serialVersionUID = 1436073433401406634L;
	//private static final Logger logger = Logger.getLogger(Stock.class);
    
	private String number; // 数量
	private Material material;   //材料编号
	private StoreRoom storeRoom;    //库房编号
	private String storageTime;    //入库时间
	
	public String getStorageTime() {
		return storageTime;
	}

	public void setStorageTime(String storageTime) {
		this.storageTime = storageTime;
	}
	@OneToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = StoreRoom.class, fetch = FetchType.EAGER)
	@JoinColumn
	public StoreRoom getStoreRoom() {
		return storeRoom;
	}

	public void setStoreRoom(StoreRoom storeRoom) {
		this.storeRoom = storeRoom;
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
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	
}
