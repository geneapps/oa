package com.ydt.oa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giro.common.dao.Pagination;
import com.ydt.oa.dao.AdminDao;
import com.ydt.oa.entity.Admin;

@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class AdminService {

	@Autowired
	private AdminDao adminDao;
	
	public Admin findById(String id) {
		return adminDao.findById(id);
	}

	public Pagination list(int pageNum, int numPerPage) {

		return adminDao.list(pageNum, numPerPage);
	}
	
	
}
