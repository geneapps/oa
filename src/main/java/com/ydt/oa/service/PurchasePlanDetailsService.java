package com.ydt.oa.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.giro.common.dao.Pagination;
import com.ydt.oa.dao.PurchasePlanDao;
import com.ydt.oa.dao.PurchasePlanDetailsDao;

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
public class PurchasePlanDetailsService {
	
	@Autowired
	private PurchasePlanDetailsDao purchasePlandetailsDao;


	 //编辑
	  public void editPurchasePlanDetails(PurchasePlanDetails  purchasePlanDetails) {
	    	this.purchasePlandetailsDao.editPurchasePlanDetails(purchasePlanDetails);
	     }
	//删除
	  public void delPurchasePlanDetails(PurchasePlanDetails  purchasePlanDetails) {
	    this.purchasePlandetailsDao.delete(purchasePlanDetails);
	     }
	  
	  public PurchasePlanDetails findDetailsById(String id){
			return purchasePlandetailsDao.findById(id);
		}
}
