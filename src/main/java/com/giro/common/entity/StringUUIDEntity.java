/*
 * @(#)StringUUIDEntity.java 2011-6-16
 * 
 * Copy Right@ Hewlett-Packard
 */
package com.giro.common.entity;

import java.io.Serializable;



import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;



/**
 * 
 * 数据库主键基类
 * 
 * @author caochun
 * @version ver 1.0
 * @date 2011-6-16 下午12:57:27
 * 
 * 
 */
@MappedSuperclass
public abstract class StringUUIDEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 6191313376755147020L;
	protected String id;
//	protected Timestamp creationDate;
//	protected Timestamp modifiedDate;

	@Id
	@Column(name = "idd", length = 32)
	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

//	@Column(name = "creation_date", updatable = false, nullable = false, columnDefinition = "DATETIME")
//	@XmlJavaTypeAdapter(TimestampAdapter.class)
//	public Timestamp getCreationDate() {
//
//		return creationDate;
//	}
//
//	@SuppressWarnings("unused")
//	private void setCreationDate(Timestamp creationDate) {
//
//		this.creationDate = creationDate;
//	}
//
//	@Column(name = "modified_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
//	@XmlJavaTypeAdapter(TimestampAdapter.class)
//	public Timestamp getModifiedDate() {
//
//		return modifiedDate;
//	}
//
//	@SuppressWarnings("unused")
//	private void setModifiedDate(Timestamp modifiedDate) {
//
//		this.modifiedDate = modifiedDate;
//	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof StringUUIDEntity))
			return false;
		StringUUIDEntity other = (StringUUIDEntity) obj;
		return super.equals(id, other.getId());
	}

	@Override
	public int hashCode() {

		return super.hashCode(id);
	}

//	public void generateCreationDate(Timestamp creationDate) {
//
//		setCreationDate(creationDate);
//	}

}
