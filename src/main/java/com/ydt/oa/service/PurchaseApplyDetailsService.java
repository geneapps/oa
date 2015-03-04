package com.ydt.oa.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.giro.common.dao.Pagination;
import com.ydt.oa.dao.PurchaseApplyDetailsDao;
import com.ydt.oa.dao.PurchasePlanDao;
import com.ydt.oa.dao.PurchasePlanDetailsDao;

import com.ydt.oa.entity.PurchaseApplyDetails;
import com.ydt.oa.entity.PurchasePlan;
import com.ydt.oa.entity.PurchasePlanDetails;
import com.ydt.oa.entity.SystemParam;



/**
 * 系统设置业务逻辑层
 * @author caochun
 *
 */

@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class PurchaseApplyDetailsService {
	
	@Autowired
	private PurchaseApplyDetailsDao purchaseApplydetailsDao;
	
	public List<PurchaseApplyDetails> list() {

		return purchaseApplydetailsDao.list();
	}
  //分页
	public Pagination list(int currPage, int pageSize) {

		return purchaseApplydetailsDao.getAll(currPage, pageSize);
	}

	//删除
	  public void delPurchaseApplyDetails(PurchaseApplyDetails  purchaseApplyDetails) {
	    this.purchaseApplydetailsDao.delete(purchaseApplyDetails);
	     }
	  
	  public PurchaseApplyDetails findDetailsById(String id){
			return purchaseApplydetailsDao.findById(id);
		}
	  public List<PurchaseApplyDetails> findDetailsByAppId(String id){
			return purchaseApplydetailsDao.findDetailsByAppId(id);
		}
	public PurchaseApplyDetails findDetails(String applyNo, String materialId) {	
		return purchaseApplydetailsDao.findByPurchaseApplyDetails(applyNo, materialId);
	}
	  
}
