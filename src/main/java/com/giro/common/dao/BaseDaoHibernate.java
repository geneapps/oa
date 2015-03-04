/*
 * @(#)BaseDaoHibernate.java 2011-6-16
 * 
 * Copy Right@ Hewlett-Packard
 */
package com.giro.common.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.Work;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.giro.common.entity.StringUUIDEntity;
import com.giro.common.util.BooleanUtil;
import com.giro.common.util.UUIDUtil;



/**
 * 
 * Hibernate基础操作实现
 * 
 * @author caochun
 * @version ver 1.0
 * @date 2011-6-16 下午12:59:31
 * 
 * 
 */
public abstract class BaseDaoHibernate<E extends StringUUIDEntity> extends HibernateDaoSupport implements
		AbstractDao<E> {

	protected static final String COUNT_STR = "select count(*) ";
	protected static final String COUNT_ID = "select count(id) ";
	protected static final int PAGE_SIZE = 20;
	protected final Class<E> clazz;

	protected boolean cacheable = false;

	public boolean isCacheable() {

		return cacheable;
	}

	protected BaseDaoHibernate() {

		this(false);
	}

	@SuppressWarnings("unchecked")
	protected BaseDaoHibernate(boolean cacheable) {

		clazz = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		this.cacheable = cacheable;
	}

	protected BaseDaoHibernate(Class<E> clazz) {

		this(clazz, false);
	}

	protected BaseDaoHibernate(Class<E> clazz, boolean cacheable) {

		this.clazz = clazz;
		if (clazz == null)
			throw new IllegalArgumentException("父类的clazz必须初始化,且不能为空.");
		this.cacheable = cacheable;
	}

	@Override
	protected MyHibernateTemplate createHibernateTemplate(SessionFactory sessionFactory) {

		MyHibernateTemplate template = new MyHibernateTemplate(sessionFactory);
		template.setCacheQueries(isCacheable());
		return template;
	}

	public MyHibernateTemplate getMyHibernateTemplate() {

		return (MyHibernateTemplate) super.getHibernateTemplate();
	}

	@Override
	public Serializable save(E obj) {

		if (obj == null)
			return null;
		if (BooleanUtil.isEmpty(obj.getId()))
			obj.setId(UUIDUtil.getUUID());
//		if (BooleanUtil.isEmpty(obj.getCreationDate()))
//			obj.generateCreationDate(new Timestamp(new Date().getTime()));
		return getHibernateTemplate().save(obj);
	}

	@Override
	public void saveOrUpdate(E obj) {

		if (obj == null)
			return;
		if (BooleanUtil.isEmpty(obj.getId()))
			obj.setId(UUIDUtil.getUUID());
//		if (BooleanUtil.isEmpty(obj.getCreationDate()))
//			obj.generateCreationDate(new Timestamp(new Date().getTime()));
		getHibernateTemplate().saveOrUpdate(obj);
	}

	@Override
	public void saveOrUpdateAll(Collection<E> entities) {

		for (E e : entities) {
			if (BooleanUtil.isEmpty(((StringUUIDEntity) e).getId()))
				e.setId(UUIDUtil.getUUID());
//			if (BooleanUtil.isEmpty(e.getCreationDate()))
//				e.generateCreationDate(new Timestamp(new Date().getTime()));

		}

		getHibernateTemplate().saveOrUpdateAll(entities);

	}

	@Override
	public E findById(Serializable id) {

		return (id == null) ? null : getHibernateTemplate().get(clazz, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<E> findByIdList(Serializable[] IDs) {

		List<Object> params = new ArrayList<Object>();
		String queryString = "";

		for (Serializable ID : IDs) {
			queryString += "?,";
			params.add(ID);
		}
		if (queryString.length() > 1) {
			queryString = queryString.substring(0, queryString.length() - 1);
		}

		String hql = " FROM " + clazz.getName() + " WHERE id in (" + queryString + ")";

		return (Collection<E>) queryList(hql, params.toArray());
	}

	@Override
	public Pagination pageById(Serializable id) {

		Pagination pg = new Pagination();
		List<E> rs = queryById(id);
		long totalCount = rs.size();
		pg.setTotalCount(totalCount);
		pg.setResult(rs);
		pg.setCurrentPage(1);
		pg.setPageSize(PAGE_SIZE);
		pg.setPageCount(Pagination.getPageCount(totalCount, PAGE_SIZE));
		return pg;
	}

	@Override
	public List<E> queryById(Serializable id) {

		List<E> rs = new ArrayList<E>();
		E record = findById(id);
		if (record != null)
			rs.add(record);
		return rs;
	}

	@Override
	@SuppressWarnings("unchecked")
	public E findByProperty(final String propertyName, final Object value) {

		Assert.hasText(propertyName, "propertyName must specified.");
		return getHibernateTemplate().execute(new HibernateCallback<E>() {

			@Override
			public E doInHibernate(Session session) throws HibernateException, SQLException
				{

					Criteria criteria = session.createCriteria(clazz).add(
							Restrictions.eq(propertyName, value));
					return (E) criteria.uniqueResult();
				}
		});
	}

	@Override
	public void update(E obj) {

		if (obj == null)
			return;
		getHibernateTemplate().update(obj);
	}

	@Override
	public void delete(E obj) {

		if (obj == null)
			return;
		getHibernateTemplate().delete(obj);
	}

	@Override
	public void deleteAll(Serializable[] IDs) {

		Collection<E> collections = null;

		collections = findByIdList(IDs);

		deleteAll(collections);
	}

	@Override
	public void deleteAll(Collection<E> collections) {

		if (collections == null)
			return;
		getHibernateTemplate().deleteAll(collections);
	}

	@Override
	public int deleteAll() {

		String hql = "delete from " + clazz.getName();
		return getHibernateTemplate().bulkUpdate(hql);
	}

	@Override
	public List<E> getAll() {

		return getAllInOrder(null);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<E> getAllInOrder(String orderHql) {

		if (clazz == null)
			return new ArrayList<E>();
		String hql = "from " + clazz.getName();
		if (org.springframework.util.StringUtils.hasText(orderHql))
			hql = hql + " " + orderHql;
		return getHibernateTemplate().find(hql);
	}

	protected Pagination pageQuery(DetachedCriteria criteria, int pageNumber, int pageSize) {

		Pagination pagination = new Pagination();
		// if (totalCount == null)
		long totalCount = querySize(criteria);
		long pageCount = Pagination.getPageCount(totalCount, pageSize);
		
		if(pageNumber<1) pageNumber = 1;		
//		pageNumber = (int) (pageNumber > pageCount ? pageCount : pageNumber);
		int firstResult = pageSize * (pageNumber - 1);
		int maxResults = pageSize;
		List<?> result = getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
		pagination.setTotalCount(totalCount);
		pagination.setResult(result);
		pagination.setCurrentPage(pageNumber);
		pagination.setPageSize(pageSize);
		pagination.setPageCount(pageCount);

		return pagination;
	}
	private static String prepareCountHql(String orgHql) {
		String fromHql = orgHql;
		//select子句与order by子句会影响count查询,进行简单的排除.
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");

		String countHql = "select count(*) " + fromHql;
		return countHql;
	}
	protected Pagination pageQuery(String hql, Object[] values,int pageNo,
			int pageSize) {
        String countHql=prepareCountHql(hql);
		long totalCount = querySize(countHql, values);
		return pageQuery(totalCount, hql, values, pageNo, pageSize);
	}
	
	protected Pagination pageQuery(String hql, Object[] values, String countHql, int pageNumber,
			int pageSize) {

		long totalCount = querySize(countHql, values);
		return pageQuery(totalCount, hql, values, pageNumber, pageSize);
	}

	protected Pagination pageQuery(String hql, Map<String, Object> values, String countHql,
			int pageNumber, int pageSize) {

		Assert.notNull(countHql, "countHql can not be null.");
		long totalCount = querySize(countHql, values);
		return pageQuery(totalCount, hql, values, pageNumber, pageSize);
	}

	protected Pagination pageQuery(String hql, String countHql, int pageNumber, int pageSize) {

		return pageQuery(hql, (Object[]) null, countHql, pageNumber, pageSize);
	}

	protected Pagination pageQuery(Long totalCount, String hql, Object[] values, int pageNumber,
			int pageSize) {

		Pagination pagination = new Pagination();
		// if (totalCount == null)
		totalCount = querySize(hql, values);
		long count = Pagination.getPageCount(totalCount, pageSize);
		
		if(pageNumber<1) pageNumber = 1;		
//		pageNumber = (int) (pageNumber > count ? count : pageNumber);
		List<?> result = pageList(hql, values, pageNumber, pageSize);
		pagination.setTotalCount(totalCount);
		pagination.setResult(result);
		pagination.setCurrentPage(pageNumber);
		pagination.setPageSize(pageSize);
		pagination.setPageCount(count);
		return pagination;
	}

	protected Pagination pageQuery(Long totalCount, String hql, Map<String, Object> values,
			int pageNumber, int pageSize) {

		Pagination pagination = new Pagination();
		// if (totalCount == null)
		totalCount = querySize(hql, values);
		long count = Pagination.getPageCount(totalCount, pageSize);
		if(pageNumber<1) pageNumber = 1;		
//		pageNumber = (int) (pageNumber > count ? count : pageNumber);
		List<?> result = pageList(hql, values, pageNumber, pageSize);
		pagination.setTotalCount(totalCount);
		pagination.setResult(result);
		pagination.setCurrentPage(pageNumber);
		pagination.setPageSize(pageSize);
		pagination.setPageCount(count);
		return pagination;
	}

	@Override
	public Pagination getAll(int pageNumber, int pageSize) {

		Pagination pagination = new Pagination();
		if (clazz == null)
			return pagination;
		long totalCount = querySize(clazz);
		long count = Pagination.getPageCount(totalCount, pageSize);
		if(pageNumber<1) pageNumber = 1;		
//		pageNumber = (int) (pageNumber > count ? count : pageNumber);
		String hql = "from " + clazz.getName();
		List<?> result = pageList(hql, pageNumber, pageSize);
		pagination.setTotalCount(totalCount);
		pagination.setResult(result);
		pagination.setCurrentPage(pageNumber);
		pagination.setPageSize(pageSize);
		pagination.setPageCount(count);
		return pagination;
	}

	@SuppressWarnings("unchecked")
	protected E queryOne(String hql) {

		HibernateTemplate template = getHibernateTemplate();
		template.setMaxResults(0);
		List<?> e = template.find(hql);
		if (null != e & e.size() > 0)
			return (E) e.get(0);
		return null;
	}

	protected List<?> queryList(String hql) {

		return getHibernateTemplate().find(hql);
	}

	protected List<?> queryList(String hql, Object value) {

		return getHibernateTemplate().find(hql, value);
	}

	protected List<?> queryList(String hql, Object[] values) {

		return getHibernateTemplate().find(hql, values);
	}

	protected List<?> queryList(String hql, Map<String, Object> values, Boolean enableCache) {

		return getMyHibernateTemplate().find(hql, values, enableCache);
	}

	protected List<?> queryList(String hql, Map<String, Object> values) {

		return getMyHibernateTemplate().find(hql, values, null);
	}

	protected List<?> queryList(String hql, Object[] values, Boolean enableCache) {

		return getMyHibernateTemplate().find(hql, values, enableCache);
	}

	protected List<?> queryList(DetachedCriteria criteria) {

		if (criteria == null)
			return new ArrayList<Object>();
		return getHibernateTemplate().findByCriteria(criteria);
	}

	protected List<?> pageList(String hql, int pageNumber, int pageSize) {

		return pageList(hql, (Object[]) null, pageNumber, pageSize);
	}

	@Override
	public boolean isPropertyExist(final String propertyName, final Object value) {

		Assert.hasText(propertyName, "propertyName must specified.");
		List<E> list = getHibernateTemplate().execute(new HibernateCallback<List<E>>() {

			@Override
			@SuppressWarnings("unchecked")
			public List<E> doInHibernate(Session session) throws HibernateException, SQLException
				{

					Criteria criteria = session.createCriteria(clazz).add(
							Restrictions.eq(propertyName, value));
					return criteria.list();
				}
		});
		return !(list.isEmpty());
	}

	protected List<?> pageList(DetachedCriteria criteria, int pageNumber, int pageSize) {

		if (criteria == null)
			return new ArrayList<Object>();
		final int firstResult = pageSize * (pageNumber - 1);
		return getHibernateTemplate().findByCriteria(criteria, firstResult, pageSize);
	}

	protected List<?> pageList(final String hql, final Object[] values, int pageNumber,
			final int pageSize) {

		return pageList(hql, values, null, pageNumber, pageSize);
	}

	protected List<?> pageList(final String hql, final Object[] values, final Boolean enableCache,
			int pageNumber, final int pageSize) {

		return getMyHibernateTemplate().pageList(hql, values, enableCache, pageNumber, pageSize);
	}

	protected List<?> pageList(final String hql, final Map<String, Object> values, int pageNumber,
			final int pageSize) {

		return pageList(hql, values, null, pageNumber, pageSize);
	}

	protected List<?> pageList(final String hql, final Map<String, Object> values,
			final Boolean enableCache, int pageNumber, final int pageSize) {

		return getMyHibernateTemplate().pageList(hql, values, enableCache, pageNumber, pageSize);
	}

	protected int executeHql(String hql) {

		return getHibernateTemplate().bulkUpdate(hql);
	}

	protected int executeHql(String hql, Object value) {

		return getHibernateTemplate().bulkUpdate(hql, value);
	}

	protected int executeHql(String hql, Object[] values) {

		return getHibernateTemplate().bulkUpdate(hql, values);
	}

	protected <T> List<T> executeSqlFind(final String sql, final ResultSetHandler<T> handler) {

		Assert.notNull(handler);
		Assert.hasText(sql);
		return getHibernateTemplate().execute(new HibernateCallback<List<T>>() {

			@Override
			public List<T> doInHibernate(Session session) throws HibernateException, SQLException
				{

					final List<T> rs = new ArrayList<T>();
					session.doWork(new Work() {

						@Override
						public void execute(Connection con) throws SQLException
						{

							PreparedStatement stmt = con.prepareStatement(sql);
							ResultSet row = stmt.executeQuery(sql);
							while (row.next())
						{
							rs.add(handler.handle(row));
						}
						row.close();
						stmt.close();
					}
					});
					return rs;
				}
		});
	}

	protected int executeSqlUpdate(final String sql) {

		Assert.hasText(sql);
		return getHibernateTemplate().execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException, SQLException
				{

					final List<Integer> rs = new ArrayList<Integer>();
					session.doWork(new Work() {

						@Override
						public void execute(Connection con) throws SQLException
						{

							PreparedStatement stmt = con.prepareStatement(sql);
							int count = stmt.executeUpdate(sql);
							rs.add(count);
							stmt.close();
						}
					});
					return rs.get(0);
				}
		});
	}

	protected long querySize(Class<E> clazz) {

		String hql = "select count(*) from " + clazz.getName();
		return querySize(hql);
	}

	protected long querySize(String hql) {

		return querySize(hql, (Object[]) null);
	}

	protected long querySize(String hql, Object value) {

		return querySize(hql, new Object[] { value });
	}

	@Override
	public long querySize(String hql, Object[] values) {

		if (hql == null)
			return 0;
		List<?> list = queryList(hql, values);
		if (list.size() == 1) {
			Object obj = list.get(0);
			if (obj instanceof Long)
				return ((Long) obj);
			return 1;
		} else if (list.size() > 1)
			return list.size();
		return 0;
	}

	@Override
	public long querySize(String hql, Map<String, Object> values) {

		if (hql == null)
			return 0;
		List<?> list = queryList(hql, values);
		if (list.size() == 1) {
			Object obj = list.get(0);
			if (obj instanceof Long)
				return ((Long) obj);
			return 1;
		} else if (list.size() > 1)
			return list.size();
		return 0;
	}

	public long querySize(DetachedCriteria criteria) {

		long result = 0;

		if (null == criteria) {
			return 0;
		}

		List<?> list = queryList(criteria);
		if (list.size() == 1) {
			Object obj = list.get(0);
			if (obj instanceof Long)
				return ((Long) obj);
			return 1;
		} else if (list.size() > 1)
			return list.size();

		return result;
	}

	@Override
	public long getTotalCount() {

		String hql = "select count(*) from " + clazz.getName();
		List<?> list = queryList(hql);
		return (Long) list.get(0);
	}

	@Override
	public List<E> queryListByProperty(final String propertyName, final Object value) {

		Assert.hasText(propertyName, "propertyName must specified.");
		List<E> list = getHibernateTemplate().execute(new HibernateCallback<List<E>>() {

			@Override
			@SuppressWarnings("unchecked")
			public List<E> doInHibernate(Session session) throws HibernateException, SQLException {

				Criteria criteria = session.createCriteria(clazz).add(
							Restrictions.eq(propertyName, value));
				return criteria.list();
			}
		});
		return list;
	}
}
