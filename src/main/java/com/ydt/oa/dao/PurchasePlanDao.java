package com.ydt.oa.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.giro.common.dao.BaseDaoHibernate;
import com.giro.common.dao.Pagination;

import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.PurchaseApply;
import com.ydt.oa.entity.PurchasePlan;
import com.ydt.oa.entity.PurchasePlanDetails;
import com.ydt.oa.entity.Role;
import com.ydt.oa.entity.User;





/**
 * 用户数据库层
 * @author caochun
 *
 */
@Repository
public class PurchasePlanDao extends BaseDaoHibernate<PurchasePlan>{



	 //根據采购计划查清单明细
	 @SuppressWarnings("unchecked")
		public List<PurchasePlanDetails> list(PurchasePlan purchasePlan){		
			
	        StringBuilder buf=new StringBuilder();
	        List<Object> params=new ArrayList<Object>();
	        buf.append("from PurchasePlanDetails where purchasePlan.id = ?");
	        params.add(purchasePlan.getId());
	        return  (List<PurchasePlanDetails>) queryList(buf.toString(), params.toArray());
		}
	 
	//所有采购计划   分页
     public Pagination list(int currPage,int pageSize){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from PurchasePlan");
        buf.append(" order by applytime DESC");
        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
	}
     
 //根据已经审批通过了的采购计划单来查询相应的明细
     
     public Pagination listPurchasePlan(int currPage,int pageSize){		
 		
         StringBuilder buf=new StringBuilder();
         List<Object> params=new ArrayList<Object>();
        // buf.append("from PurchasePlan where id " +
         		//"in (select applyNo from Application where applyType=?  and status=?)");
         
         buf.append("from PurchasePlan where id " +
  		"in (select applyNo from Application where applyType=?)");
         
         params.add(Application.APP_TYPE_PURCHASEPLAN);
        // params.add(Application.APP_STATUS_APPROVED);
         return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
 	}
     
/*     //根据已经审批通过了的采购计划单来查询相应的明细
     
     @SuppressWarnings("unchecked")
	public List<PurchasePlanDetails> list(){		
 		
         StringBuilder buf=new StringBuilder();
         List<Object> params=new ArrayList<Object>();
         buf.append("from PurchasePlanDetails where id in " +
         		"(select application.applyNo from Application where applyType='PURCHASEPLAN'  and status=1)");
         return (List<PurchasePlanDetails>) this.queryList(buf.toString(), params.toArray());
 	}*/
     /**
		 * 根据项目找出采购计划单
		 * @param id
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public PurchasePlan findByProject(String id) {
			StringBuilder buf=new StringBuilder();
	        List<Object> params=new ArrayList<Object>();
	        buf.append("from PurchasePlan where  project.id = ?");
	        params.add(id);
	        List<PurchasePlan> list= (List<PurchasePlan>) queryList(buf.toString(), params.toArray());
		    if(list!=null&&list.size()>0) {
		    	return list.get(0);
		    }else {
		    	return null;
		    }
		}
}
