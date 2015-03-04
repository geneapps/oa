package com.ydt.oa.dao;




import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.giro.common.dao.BaseDaoHibernate;
import com.giro.common.dao.Pagination;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.Contract;
import com.ydt.oa.entity.PurchaseApplyDetails;
import com.ydt.oa.entity.Role;
import com.ydt.oa.entity.User;


/**
 * 读取有关合同的数据
 * @author caochun
 *
 */
@Repository
public class ContractDao extends BaseDaoHibernate<Contract>{

	@SuppressWarnings("unchecked")
	public List<Contract> list(){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from Contract");
        return  (List<Contract>) queryList(buf.toString(), params.toArray());
		
	}
	
	//详细清单   分页
     public Pagination list(int currPage,int pageSize){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from Contract where contractType = 1 and status <> 99 ");
        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
	}
     
     public Pagination listManContract(int currPage,int pageSize){		
 		
         StringBuilder buf=new StringBuilder();
         List<Object> params=new ArrayList<Object>();
         buf.append("from Contract where contractType = 0 and status <> 99");
         return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
 	}

	
}
