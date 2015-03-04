package com.ydt.oa.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.giro.common.entity.StringUUIDEntity;

/**
 * 库房材料表
 * 
 * @author huchuqiao
 * 
 */
@Entity
@Table(name = "oa_storeroom_material")
// 库房表
public class StoreRoomMaterial extends StringUUIDEntity implements Serializable {

	private static final long serialVersionUID = -5632839673587832191L;
	private StoreRoom storeRoom;
	private Material material; // 材料
	private long number; // 数量
	
	@ManyToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = StoreRoom.class, fetch = FetchType.EAGER)
	@JoinColumn
	public StoreRoom getStoreRoom() {
	
		return storeRoom;
	}
	
	public void setStoreRoom(StoreRoom storeRoom) {
	
		this.storeRoom = storeRoom;
	}
	
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
	
	public long getNumber() {
	
		return number;
	}
	
	public void setNumber(long number) {
	
		this.number = number;
	}
	
	
 
}
