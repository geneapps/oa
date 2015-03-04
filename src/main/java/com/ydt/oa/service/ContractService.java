package com.ydt.oa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.giro.common.util.DateUtils;
import com.giro.common.util.StringUtils;
import com.ydt.oa.dao.ContractDao;
import com.ydt.oa.dao.ContractDetailsDao;
import com.ydt.oa.dao.ManContractDetailsDao;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.Contract;
import com.ydt.oa.entity.ContractDetails;
import com.ydt.oa.entity.Contractor;
import com.ydt.oa.entity.ManContractDetails;
import com.ydt.oa.entity.Material;
import com.ydt.oa.entity.PurchaseApplyDetails;
import com.ydt.oa.entity.User;
import com.ydt.oa.interfaces.ContractAppInterface;


/**
 * 系统设置业务逻辑层
 * @author caochun
 *
 */

@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class ContractService{
	

	@Autowired
	private ContractDao contractDao;
	
	@Autowired
	private ContractDetailsDao contractDetailsDao;
	@Autowired
	private ManContractDetailsDao manContractDetailsDao;
	
	
	/**
	 * 通过id从数据库中获取值
	 * @param id
	 * @return
	 */
	public Contract findById(String id){
		return contractDao.findById(id);
	}
	
	public List<ContractDetails>  findDetailsById(Contract contract){
		return contractDetailsDao.list(contract);
	}
	
	
	public List<Contract> list() {

		return contractDao.list();
	}
	
	//分页
	public Pagination list(int currPage, int pageSize) {

		return contractDao.list(currPage, pageSize);
	}
	
	//分页
	public Pagination listManContract(int currPage, int pageSize) {

		return contractDao.listManContract(currPage, pageSize);
	}
	
	public List<ManContractDetails> findManDetailsById(Contract contract){
		return manContractDetailsDao.list(contract);
	}

}
