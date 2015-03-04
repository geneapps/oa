package com.ydt.oa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giro.common.dao.Pagination;
import com.ydt.oa.dao.PurchasePlanDao;
import com.ydt.oa.entity.PurchaseApply;
import com.ydt.oa.entity.PurchaseApplyDetails;
import com.ydt.oa.entity.PurchasePlan;
import com.ydt.oa.entity.PurchasePlanDetails;
@Service

@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class PurchasePlanService {
	@Autowired
	private PurchasePlanDao   purchasePlanDao;
	
	
	public List<PurchasePlanDetails> list(PurchasePlan purchasePlan) {

		return purchasePlanDao.list(purchasePlan);
	}
      //查所有计划分页
	public Pagination list(int currPage, int pageSize) {

		return purchasePlanDao.listPurchasePlan(currPage, pageSize);
	}

	
	  public PurchasePlan findById(String id) {
		  return purchasePlanDao.findById(id);
	  }
	  /**
		 * 查找相应项目对应材料的数量
		 * @param id
		 * @param materialId
		 * @return
		 */
		public int getPlanTotalNum(String id,String materialId) {
			int totalNum=0;
			PurchasePlan purchasePlan=this.findByProId(id);
			
			List<PurchasePlanDetails> purchasePlanDetailsList= purchasePlanDao.list(purchasePlan);
				
				for(int i=0;i<purchasePlanDetailsList.size();i++) {
					if(purchasePlanDetailsList.get(i).getMaterial().getId().equals(materialId)) {
						totalNum+=purchasePlanDetailsList.get(i).getNumber();
					}
				}
			return totalNum;
		}
		
		public PurchasePlan findByProId(String id){
			return purchasePlanDao.findByProject(id);
			
		}

}
