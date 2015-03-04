package com.ydt.oa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.giro.common.util.DateUtils;

import com.ydt.oa.dao.BorrowMoneyDao;

import com.ydt.oa.entity.BorrowMoney;




/**
 * 系统设置业务逻辑层
 * @author caochun
 *
 */

@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class BorrowMoneyService{
	

	@Autowired
	private BorrowMoneyDao borrowMoneyDao;
	
	
	/**
	 * 通过id从数据库中获取值
	 * @param id
	 * @return
	 */
	public BorrowMoney findById(String id){
		return borrowMoneyDao.findById(id);
	}
	

	
	public List<BorrowMoney> list() {

		return borrowMoneyDao.list();
	}
	
	//分页
	public Pagination list(int currPage, int pageSize) {

		return borrowMoneyDao.list(currPage, pageSize);
	}


}
