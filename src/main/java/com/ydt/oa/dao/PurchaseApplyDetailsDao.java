package com.ydt.oa.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.giro.common.dao.BaseDaoHibernate;
import com.giro.common.dao.Pagination;

import com.ydt.oa.entity.PaymentOrder;
import com.ydt.oa.entity.PurchaseApply;
import com.ydt.oa.entity.PurchaseApplyDetails;
import com.ydt.oa.entity.PurchasePlanDetails;
import com.ydt.oa.entity.StoreRoomMaterial;




/**
 * 用户数据库层    采购申请详单
 * @author caochun
 *
 */
@Repository
public class PurchaseApplyDetailsDao extends BaseDaoHibernate<PurchaseApplyDetails>{



	
	@SuppressWarnings("unchecked")
	public List<PurchaseApplyDetails> list(){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from PurchaseapplyDetails");
        return  (List<PurchaseApplyDetails>) queryList(buf.toString(), params.toArray());
		
	}
  //详细清单   分页
     public Pagination list(int currPage,int pageSize){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from PurchaseApplyDetails");
        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
	}
	@SuppressWarnings("unchecked")
	public List<PurchaseApplyDetails> getDetailsById(String id) {
		    StringBuilder buf=new StringBuilder();
	        List<Object> params=new ArrayList<Object>();
	        buf.append("from PurchaseapplyDetails where purchaseApply.id=?");
	        params.add(id);
	        return  (List<PurchaseApplyDetails>) queryList(buf.toString(), params.toArray());
	}
   

	@SuppressWarnings("unchecked")
	public PurchaseApplyDetails findByPurchaseApplyDetails(String purchaseApplyId,String MaterialId){
	       StringBuilder buf=new StringBuilder();
	        List<Object> params=new ArrayList<Object>();
	          buf.append("from PurchaseApplyDetails");
           	  buf.append(" where purchaseApply.id = ? and material.id = ?" );	      
		      params.add(purchaseApplyId);
		      params.add(MaterialId);
		     List<PurchaseApplyDetails> list=(List<PurchaseApplyDetails>) queryList(buf.toString(), params.toArray());
		     if(list!=null && list.size()>0){
		        	return list.get(0);
		        }else return null;
		     
	}
	
	@SuppressWarnings("unchecked")
	public List<PurchaseApplyDetails> findDetailsByAppId(String id)  {
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from PurchaseApplyDetails where purchaseApply.id = ?");
        params.add(id);
       return  (List<PurchaseApplyDetails>) queryList(buf.toString(), params.toArray());
	}
}
