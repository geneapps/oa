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
 * 记录上传文件信息
 * @author caochun
 *
 */
@Entity
@Table(name = "oa_filelog")
public class FileLog extends StringUUIDEntity implements Serializable{

	private static final long serialVersionUID = 7911340747999404976L;
	private String businessType;
	private String businessNo;
	private String fileName;
	private String fileType;
	private String fileExt;
	private long fileSize;
	private String filePath;
	private String uploadTime;
	private User uploadUser;
	private String status;
	
	public String getBusinessType() {
	
		return businessType;
	}
	
	public void setBusinessType(String businessType) {
	
		this.businessType = businessType;
	}
	
	public String getBusinessNo() {
	
		return businessNo;
	}
	
	public void setBusinessNo(String businessNo) {
	
		this.businessNo = businessNo;
	}
	
	public String getFileName() {
	
		return fileName;
	}
	
	public void setFileName(String fileName) {
	
		this.fileName = fileName;
	}
	
	public String getFileType() {
	
		return fileType;
	}
	
	public void setFileType(String fileType) {
	
		this.fileType = fileType;
	}
	
	public long getFileSize() {
	
		return fileSize;
	}
	
	public void setFileSize(long fileSize) {
	
		this.fileSize = fileSize;
	}
	
	public String getFilePath() {
	
		return filePath;
	}
	
	public void setFilePath(String filePath) {
	
		this.filePath = filePath;
	}
	
	public String getUploadTime() {
	
		return uploadTime;
	}
	
	public void setUploadTime(String uploadTime) {
	
		this.uploadTime = uploadTime;
	}
	

	
	@ManyToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public User getUploadUser() {
	
		return uploadUser;
	}

	
	public void setUploadUser(User uploadUser) {
	
		this.uploadUser = uploadUser;
	}

	public String getStatus() {
	
		return status;
	}
	
	public void setStatus(String status) {
	
		this.status = status;
	}

	
	public String getFileExt() {
	
		return fileExt;
	}

	
	public void setFileExt(String fileExt) {
	
		this.fileExt = fileExt;
	}



}
