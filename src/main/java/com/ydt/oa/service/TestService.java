package com.ydt.oa.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ydt.oa.dao.TestDao;
import com.ydt.oa.entity.Test;
/**
 * 业务逻辑层
 * @author caochun
 *
 */
@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class TestService {
	
	@Autowired
	private TestDao testDao;

	
	
	public String sayHello(){
		
	
		Test test = new Test();
		test.setName("giro");
		testDao.save(test);
		return test.getId();
	}
	
}
