
package com.ydt.oa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.giro.common.entity.StringUUIDEntity;

/**
 * 供货商
 * @author Cruise
 *
 */
@Entity
@Table(name = "oa_supplier")
public class Supplier extends StringUUIDEntity implements Serializable {

	private static final long serialVersionUID = 1921377493094828962L;
	
	
	private String supplyName;		//供货商名称
	private String contact;			//供货商的联系人
	private String phone;			//供货商电话
	private String address;			//供货商地址
	public String getSupplyName() {
		return supplyName;
	}
	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	


}

