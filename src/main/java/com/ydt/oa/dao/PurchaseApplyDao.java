package com.ydt.oa.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.giro.common.dao.BaseDaoHibernate;
import com.giro.common.dao.Pagination;

import com.ydt.oa.entity.Project;
import com.ydt.oa.entity.PurchaseApply;
import com.ydt.oa.entity.PurchaseApplyDetails;
import com.ydt.oa.entity.PurchasePlan;
import com.ydt.oa.entity.PurchasePlanDetails;





/**
 * 用户数据库层   采购申请单
 * @author caochun
 *
 */
@Repository
public class PurchaseApplyDao extends BaseDaoHibernate<PurchaseApply>{

	//根據采购查清单明细
	 @SuppressWarnings("unchecked")
		public PurchaseApply findById(PurchaseApply purchaseApply){		
			
	        StringBuilder buf=new StringBuilder();
	        List<Object> params=new ArrayList<Object>();
	        buf.append("from PurchaseApply where id = ?");
	        params.add(purchaseApply.getId());
	        List<PurchaseApply> list= (List<PurchaseApply>) queryList(buf.toString(), params.toArray());
	        if(list!=null&&list.size()>0) {
	        	return list.get(0);
	        }else {
	        	return null;
	        }
		}
	 
		public Pagination listPurchaseApply(int currPage,int pageSize){		
			
	        StringBuilder buf=new StringBuilder();
	        List<Object> params=new ArrayList<Object>();
	        buf.append("from PurchaseApply");
	        buf.append(" order by applyTime DESC");

	        
	        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
			
		}
		
		public Pagination listPurchaseApplyByStatus(String id,int status,int currPage,int pageSize){		
			
	        StringBuilder buf=new StringBuilder();
	        List<Object> params=new ArrayList<Object>();
	        buf.append("from PurchaseApply p where  status = ? and p.project.id = ?");
	        buf.append(" order by applyTime DESC");
	        params.add(status);
	        params.add(id);
	        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
			
		}
		/**
		 * 根据项目找出采购单
		 * @param id
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public List<PurchaseApply> findByProject(String id) {
			StringBuilder buf=new StringBuilder();
	        List<Object> params=new ArrayList<Object>();
	        buf.append("from PurchaseApply where  project.id = ? and status in(2,3,4)");
	        params.add(id);
	        return  (List<PurchaseApply>) queryList(buf.toString(), params.toArray());
		}
  
}
