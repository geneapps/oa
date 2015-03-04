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
 * 操作url关联表
 * @author caochun
 *
 */
@Entity
@Table(name = "oa_action_url")
public class ActionUrl extends StringUUIDEntity implements Serializable{

	private static final long serialVersionUID = -1947699494671548674L;
	
	private OaAction action;
	private String description;
	private String url;
	
	@ManyToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = OaAction.class, fetch = FetchType.EAGER)
	@JoinColumn
	public OaAction getAction() {
	
		return action;
	}
	
	public void setAction(OaAction action) {
	
		this.action = action;
	}
	
	public String getDescription() {
	
		return description;
	}
	
	public void setDescription(String description) {
	
		this.description = description;
	}
	
	public String getUrl() {
	
		return url;
	}
	
	public void setUrl(String url) {
	
		this.url = url;
	}
	


}
