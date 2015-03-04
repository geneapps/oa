package com.ydt.oa.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.log4j.Logger;

import com.giro.common.entity.StringUUIDEntity;

/**
 * 用户数据库持久化Bean
 */
@Entity
@Table(name = "oa_admin") //行政申请表
public class Admin extends StringUUIDEntity implements Serializable{

	private static final long serialVersionUID = -8456630710553876151L;
	public static final String FILE_TYPE="ADMIN";
	
	public static final int ADMIN_WAITINGAPPROVE = 0;   //等待审批
	public static final int ADMIN_VALID = 1;            //有效地
	public static final int ADMIN_INVALID = 2;           //无效的
	public static final int ADMIN_DELETE = 99;          //已被删除
	private String title; //标题
	private String adminType;            //申请类型      办公用品，招待费，交通费，生活费，差旅费，其他
	private int status;
	private String fileName; // 附件
	private String instruction; // 申请说明
	
	
	
	public String getInstruction() {
	
		return instruction;
	}

	
	public void setInstruction(String instruction) {
	
		this.instruction = instruction;
	}

	public String getTitle() {
	
		return title;
	}
	
	public void setTitle(String title) {
	
		this.title = title;
	}
	
	public String getAdminType() {
	
		return adminType;
	}
	
	public void setAdminType(String adminType) {
	
		this.adminType = adminType;
	}
	
	public int getStatus() {
	
		return status;
	}
	
	public void setStatus(int status) {
	
		this.status = status;
	}

	public void setFileName(String fileName) {

		this.fileName = fileName;
	}

	public String getFileName() {

		return fileName;
	}
	
	
	
}
