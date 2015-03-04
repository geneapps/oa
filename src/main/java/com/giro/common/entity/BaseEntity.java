/*
 * @(#)BaseEntity.java 2011-6-16
 * 
 * Copy Right@ Hewlett-Packard
 */
package com.giro.common.entity;

/**
 * 
 * Entity基类
 * 
 * @author caochun
 * @version ver 1.0
 * @date 2011-6-16 下午12:51:52
 * 
 * 
 */
public abstract class BaseEntity {

	protected boolean equals(String child, String other) {

		return objEquals(child, other);
	}

	protected int hashCode(String id) {

		return objHashCode(id);
	}

	protected boolean equals(Long child, Long other) {

		return objEquals(child, other);
	}

	protected int hashCode(Long id) {

		return objHashCode(id);
	}

	protected boolean equals(Integer child, Integer other) {

		return objEquals(child, other);
	}

	protected int hashCode(Integer id) {

		return objHashCode(id);
	}

	protected boolean objEquals(Object child, Object other) {

		if (child == other)
			return true;
		if ((child == null) || (other == null))
			return false;
		return child.equals(other);
	}

	protected int objHashCode(Object id) {

		if (id == null)
			return super.hashCode();
		return id.hashCode();
	}
}
