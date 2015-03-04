package com.ydt.oa.entity;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.giro.common.entity.StringUUIDEntity;

/**
 * 数据库持久化Bean
 * @author caochun
 *
 */
@Entity
@Table(name = "test")
public class Test extends StringUUIDEntity implements Serializable{

	private static final long serialVersionUID = -4741942883176171258L;
	

	private String name;	

	
	public String getName() {
	
		return name;
	}
	
	public void setName(String name) {
	
		this.name = name;
	}
}
