package com.giro.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhuzhu
 *
 */
public abstract interface ResultSetHandler<T> {

	public abstract T handle(ResultSet resultSet)
			throws SQLException;
}
