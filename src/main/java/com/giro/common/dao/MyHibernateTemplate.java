package com.giro.common.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author zhuzhu
 *
 */
public class MyHibernateTemplate extends HibernateTemplate {

	public MyHibernateTemplate() {

	}

	public MyHibernateTemplate(SessionFactory sessionFactory) {

		super(sessionFactory);
	}

	public List<?> pageList(final String hql, final Object[] values, final Boolean enableCache,
			int pageNumber, final int pageSize) {

		List<?> result = null;
		final int firstResult = pageSize * (pageNumber - 1);
		result = (List<?>) super.executeFind(new HibernateCallback<List<?>>() {

			public List<?> doInHibernate(Session session) throws HibernateException, SQLException
				{

				Query query = session.createQuery(hql);
				prepareQuery(query, enableCache);
				if (values != null)
				{
					for (int i = 0; i < values.length; i++)
						query.setParameter(i, values[i]);
				}
				if (firstResult >= 0)
					query.setFirstResult(firstResult);
				if (pageSize > 0)
					query.setMaxResults(pageSize);
				return query.list();
			}
		});
		return result;
	}

	public List<?> pageList(final String hql, final Map<String, Object> values,
			final Boolean enableCache, int pageNumber, final int pageSize) {

		List<?> result = null;
		final int firstResult = pageSize * (pageNumber - 1);
		result = (List<?>) super.executeFind(new HibernateCallback<List<?>>() {

			public List<?> doInHibernate(Session session) throws HibernateException, SQLException
				{

				Query query = session.createQuery(hql);
				prepareQuery(query, enableCache);
				if (values != null)
				{
					for (Entry<String, Object> en : values.entrySet())
						applyNamedParameterToQuery(query, en.getKey(), en.getValue());
				}
				if (firstResult >= 0)
					query.setFirstResult(firstResult);
				if (pageSize > 0)
					query.setMaxResults(pageSize);
				return query.list();
			}
		});
		return result;
	}

	public List<?> find(final String queryString, final Object[] values, final Boolean enableCache)
			throws DataAccessException {

		return (List<?>) executeWithNativeSession(new HibernateCallback<List<?>>() {

			public List<?> doInHibernate(Session session) throws HibernateException {

				Query queryObject = session.createQuery(queryString);
				prepareQuery(queryObject, enableCache);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						queryObject.setParameter(i, values[i]);
					}
				}
				return queryObject.list();
			}
		});
	}

	public List<?> find(final String queryString, final Map<String, Object> values,
			final Boolean enableCache) throws DataAccessException {

		return (List<?>) executeWithNativeSession(new HibernateCallback<List<?>>() {

			public List<?> doInHibernate(Session session) throws HibernateException {

				Query queryObject = session.createQuery(queryString);
				prepareQuery(queryObject, enableCache);
				if (values != null) {
					for (Entry<String, Object> en : values.entrySet()) {
						applyNamedParameterToQuery(queryObject, en.getKey(), en.getValue());
					}
				}
				return queryObject.list();
			}
		});
	}

	protected void prepareQuery(Query queryObject, Boolean enableCache) {

		super.prepareQuery(queryObject);
		if (enableCache == null)
			return;
		queryObject.setCacheable(enableCache.booleanValue());
	}

}
