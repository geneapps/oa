package com.ydt.oa.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.giro.common.dao.BaseDaoHibernate;
import com.giro.common.dao.Pagination;

import com.ydt.oa.entity.PurchasePlanDetails;




/**
 * 用户数据库层
 * @author caochun
 *
 */
@Repository
public class PurchasePlanDetailsDao extends BaseDaoHibernate<PurchasePlanDetails>{


     //详细清单  编辑
     
     public void editPurchasePlanDetails(PurchasePlanDetails  purchasePlanDetails) {
    	 this.saveOrUpdate(purchasePlanDetails);
     }
     //详细清单   删除
     public void delPurchasePlanDetails(PurchasePlanDetails  purchasePlanDetails) {
    	 if(purchasePlanDetails.getId()!=null) {
         this.delete(purchasePlanDetails);
     }
     }
     //根据已经审批通过了的采购计划单来查询相应的明细
     
     @SuppressWarnings("unchecked")
	public List<PurchasePlanDetails> list(){		
 		
         StringBuilder buf=new StringBuilder();
         List<Object> params=new ArrayList<Object>();
         buf.append("from PurchasePlanDetails where id in " +
         		"(select application.applyNo from Application where applyType='PURCHASEPLAN'  and status=1)");
         return (List<PurchasePlanDetails>) this.queryList(buf.toString(), params.toArray());
 	}
}
