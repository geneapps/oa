package com.ydt.oa.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.giro.common.dao.BaseDaoHibernate;
import com.giro.common.dao.Pagination;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.Contract;
import com.ydt.oa.entity.ContractDetails;
import com.ydt.oa.entity.PurchaseApplyDetails;
import com.ydt.oa.entity.PurchasePlan;
import com.ydt.oa.entity.PurchasePlanDetails;
import com.ydt.oa.entity.Role;
import com.ydt.oa.entity.User;



/**
 * 从数据库中读取有关于合同明细的数据
 * @author caochun
 *
 */
@Repository
public class ContractDetailsDao extends BaseDaoHibernate<ContractDetails>{

      //详细清单   分页
     public Pagination list(int currPage,int pageSize){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from ContractDetails");
        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
	}
	
     
     @SuppressWarnings("unchecked")
	public List<ContractDetails> list(Contract contract){		
			
	        StringBuilder buf=new StringBuilder();
	        List<Object> params=new ArrayList<Object>();
	        buf.append("from ContractDetails where contract.id = ?");
	        params.add(contract.getId());
	        return  (List<ContractDetails>) queryList(buf.toString(), params.toArray());
		}
	
}
