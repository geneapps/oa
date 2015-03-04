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
 * 仓库材料出库入库记录
 * 
 * @author zld
 * 
 */
@Entity
@Table(name = "oa_storeroom_log")
public class StoreRoomLog extends StringUUIDEntity implements Serializable {

	private static final long serialVersionUID = 5918681719816812781L;
	public static final int HOUSELOG_TYPE1 = 1; // 入库
	public static final int HOUSELOG_TYPE2 = 2; // 出库
	private String applyNo; // 入库时填采购单编号；出库时填材料领用单编号
	private String logTime; // 发生时间
	private Material material; // 材料
	private long number; // 数量
	private int type;// 
	public String title;    //采购标题
	private User user; // 领用人
  
	
	
	
	public String getTitle() {
	
		return title;
	}

	
	public void setTitle(String title) {
	
		this.title = title;
	}

	public String getApplyNo() {

		return applyNo;
	}

	public String getLogTime() {

		return logTime;
	}

	@ManyToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = Material.class, fetch = FetchType.EAGER)
	@JoinColumn
	public Material getMaterial() {

		return material;
	}

	public long getNumber() {

		return number;
	}

	public int getType() {

		return type;
	}

	@ManyToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn
	public User getUser() {

		return user;
	}

	public void setApplyNo(String applyNo) {

		this.applyNo = applyNo;
	}

	public void setLogTime(String logTime) {

		this.logTime = logTime;
	}

	public void setMaterial(Material material) {

		this.material = material;
	}

	public void setNumber(long number) {

		this.number = number;
	}

	public void setType(int type) {

		this.type = type;
	}

	public void setUser(User user) {

		this.user = user;
	}
}
