package com.ydt.oa.dao;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.giro.common.dao.BaseDaoHibernate;
import com.giro.common.dao.Pagination;
import com.ydt.oa.entity.Material;
import com.ydt.oa.entity.Supplier;




/**
 * 供货商DAO
 * @author caochun
 *
 */
@Repository
public class SupplierDao extends BaseDaoHibernate<Supplier>{

	@SuppressWarnings("unchecked")
	public Supplier findBySupplierName(String name) {
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from Supplier where supplyName = ?");
        params.add(name);
        List<Supplier> list =  (List<Supplier>) queryList(buf.toString(), params.toArray());

      	 if(list!=null && list.size()>1){
      		 return list.get(0);
      	 }else return null;
	}

	public Pagination list(int currPage, int pageSize) {

		StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from Supplier");
        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
		
	}
}
