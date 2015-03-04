package com.ydt.oa.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ydt.oa.dao.SystemParamDao;
import com.ydt.oa.entity.SystemParam;


/**
 * 系统设置业务逻辑层
 * @author caochun
 *
 */

@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class SystemConfigService {
	
	@Autowired
	private SystemParamDao paramDao;	

	public String findParamValue(String name){
		return paramDao.getSystemParamValue(name);
	}
	
	public SystemParam findParamByName(String name){
		return paramDao.getSystemParam(name);
	}
	
	public SystemParam findParamById(String id){
		return paramDao.findById(id);
	}

	
	public void setParam(String name,String key,String value){
		paramDao.setSystemParam(name, key, value);
	}
	
	public void delParam(String id){
		SystemParam param = paramDao.findById(id);
		paramDao.delete(param);
	}
	
	
	public List<SystemParam> listParam(){
		return paramDao.getAll();
	}

	
}
