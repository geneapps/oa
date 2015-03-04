/*
 * @(#)AbstractDao.java 2011-6-16
 * 
 * Copy Right@ Hewlett-Packard
 */
package com.giro.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;


import com.giro.common.entity.BaseEntity;


/**
 * 
 * 数据库基类接口
 * 
 * @author caochun
 * @version ver 1.0
 * @date 2011-6-16 下午12:54:09
 * 
 * 
 */
public abstract interface AbstractDao<E extends BaseEntity> {
	public abstract Serializable save(E obj);

	public abstract E findById(Serializable id);

	public Collection<E> findByIdList(Serializable[] IDs);

	public abstract List<E> queryById(Serializable id);

	public abstract Pagination pageById(Serializable id);

	public abstract E findByProperty(final String propertyName, final Object value);

	public abstract boolean isPropertyExist(String propertyName, Object value);

	public abstract void saveOrUpdate(E obj);

	public abstract void saveOrUpdateAll(Collection<E> entities);

	public abstract void update(E obj);

	public abstract void delete(E obj);

	public void deleteAll(Serializable[] IDs);

	public abstract void deleteAll(Collection<E> collection);

	public abstract int deleteAll();

	public abstract List<E> getAll();

	public abstract List<E> getAllInOrder(String orderHql);

	public abstract Pagination getAll(int pageNumber, int pageSize);

	public abstract long querySize(String hql, Object[] values);

	public abstract long querySize(String hql, Map<String, Object> values);

	public abstract long getTotalCount();

	public abstract List<E> queryListByProperty(String propertyName, Object value);
}
