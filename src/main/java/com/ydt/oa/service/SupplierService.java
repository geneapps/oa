package com.ydt.oa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giro.common.dao.Pagination;
import com.ydt.oa.dao.MaterialDao;
import com.ydt.oa.dao.SupplierDao;
import com.ydt.oa.entity.Supplier;

@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class SupplierService {

	@Autowired
	private MaterialDao materialDao;
	@Autowired
	private SupplierDao supplierDao;
	
	
	//分页
	public Pagination list(int currPage, int pageSize) {

		return supplierDao.list(currPage, pageSize);
	}
	
	//通过供货商查找产品
	public Pagination findMaterialBySupplier(String id,int currPage,int pageSize){
		return materialDao.findMaterialBySupplier(id, currPage, pageSize);
	}

	
	public MaterialDao getMaterialDao() {
	
		return materialDao;
	}

	
	public void setMaterialDao(MaterialDao materialDao) {
	
		this.materialDao = materialDao;
	}

	
	public SupplierDao getSupplierDao() {
	
		return supplierDao;
	}

	
	public void setSupplierDao(SupplierDao supplierDao) {
	
		this.supplierDao = supplierDao;
	}

	public Supplier findBySupplierId(String id) {
		
		if(id == null || "".equals(id)){
			return null;
		}else{
			return supplierDao.findById(id);
		}
		
	}

	public void updateUser(Supplier supplier) {

		supplierDao.saveOrUpdate(supplier);
	}
	
	
}
