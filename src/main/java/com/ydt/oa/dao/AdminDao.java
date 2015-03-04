package com.ydt.oa.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.giro.common.dao.BaseDaoHibernate;
import com.giro.common.dao.Pagination;
import com.ydt.oa.entity.Admin;

@Repository
public class AdminDao extends BaseDaoHibernate<Admin> {

	@SuppressWarnings("unchecked")
	public List<Admin> list() {

		StringBuilder buf = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		buf.append("from Admin");
		return (List<Admin>) queryList(buf.toString(), params.toArray());
	}

	// 详细清单 分页
	public Pagination list(int currPage, int pageSize) {

		StringBuilder buf = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		buf.append("from Admin");
		return pageQuery(buf.toString(), params.toArray(), currPage, pageSize);
	}
}
