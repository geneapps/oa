package com.ydt.oa.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.giro.common.dao.BaseDaoHibernate;
import com.giro.common.dao.Pagination;
import com.ydt.oa.entity.Contract;
import com.ydt.oa.entity.CostDetails;
import com.ydt.oa.entity.User;

@Repository
public class CostDetailsDao extends BaseDaoHibernate<CostDetails>{

      @SuppressWarnings("unchecked")
	public List<CostDetails> getCostDetailsList(User user) {
    	  StringBuilder buf=new StringBuilder();
          List<Object> params=new ArrayList<Object>();
          buf.append("from CostDetails where user.id=? and expense.id = null");
          params.add(user.getId());
          return   (List<CostDetails>) queryList(buf.toString(), params.toArray());
    }
      
	     public Pagination listCostDetailNoExpense(String userId,int currPage,int pageSize){		
				
		        StringBuilder buf=new StringBuilder();
		        List<Object> params=new ArrayList<Object>();
		        buf.append("from CostDetails where user.id=? and expense.id = null order by payTime desc");
		        params.add(userId);
		        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
			}
		
}
