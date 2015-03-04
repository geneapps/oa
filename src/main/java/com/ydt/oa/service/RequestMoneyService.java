package com.ydt.oa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.giro.common.util.DateUtils;

import com.ydt.oa.dao.RequestMoneyDao;

import com.ydt.oa.entity.RequestMoney;




/**
 * 系统设置业务逻辑层
 * @author caochun
 *
 */

@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class RequestMoneyService{
	

	@Autowired
	private RequestMoneyDao requestMoneyDao;
	
	
	/**
	 * 通过id从数据库中获取值
	 * @param id
	 * @return
	 */
	public RequestMoney findById(String id){
		return requestMoneyDao.findById(id);
	}
	

	
	public List<RequestMoney> list() {

		return requestMoneyDao.list();
	}
	
	//分页
	public Pagination list(int currPage, int pageSize) {

		return requestMoneyDao.list(currPage, pageSize);
	}


}
