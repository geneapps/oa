package com.ydt.oa.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.giro.common.entity.StringUUIDEntity;


/**
 * 用户数据库持久化Bean
 * @author caochun
 *
 */
@Entity
@Table(name = "oa_action")
public class OaAction extends StringUUIDEntity implements Serializable{

	private static final long serialVersionUID = -1947699494671548674L;
	
	private String name;
	private String description;
	private int actionValue;
	private List<ActionUrl> urls;
	
	public String getName() {
	
		return name;
	}
	
	public void setName(String name) {
	
		this.name = name;
	}
	
	public String getDescription() {
	
		return description;
	}
	
	public void setDescription(String description) {
	
		this.description = description;
	}
	
	public int getActionValue() {
	
		return actionValue;
	}
	
	public void setActionValue(int actionValue) {
	
		this.actionValue = actionValue;
	}

	@OneToMany(
			mappedBy = "action", cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
					CascadeType.MERGE },
			targetEntity = ActionUrl.class, fetch = FetchType.LAZY)
	public List<ActionUrl> getUrls() {
	
		return urls;
	}

	
	public void setUrls(List<ActionUrl> urls) {
	
		this.urls = urls;
	}


}
