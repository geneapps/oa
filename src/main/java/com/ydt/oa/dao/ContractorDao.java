package com.ydt.oa.dao;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.giro.common.dao.BaseDaoHibernate;
import com.giro.common.dao.Pagination;
import com.ydt.oa.entity.Contractor;
import com.ydt.oa.entity.Material;
import com.ydt.oa.entity.Supplier;




/**
 * 分包商DAO
 * @author hcq
 *
 */
@Repository
public class ContractorDao extends BaseDaoHibernate<Contractor>{

	public Contractor findByContractorName(String name) {
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from Contractor where contractorName = ?");
        params.add(name);
        List<Contractor> list =  (List<Contractor>) queryList(buf.toString(), params.toArray());

      	 if(list!=null && list.size()>1){
      		 return list.get(0);
      	 }else return null;
	}

	public Pagination list(int currPage, int pageSize) {

		StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from Contractor");
        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
		
	}
	
	public Pagination lookupContractor(Contractor contractor,int currPage,int pageSize) {
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
          buf.append("from Contractor where contractorName like ? "); 
          params.add('%'+contractor.getContractorName()+'%');
        	return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
	}
	
	@SuppressWarnings("unchecked")
	public List<Contractor> suggest(String suggestFields, String keywords) {
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
          buf.append("from Contractor");        
  
        	buf.append(" where "+suggestFields+" like ?" );
        	params.add('%'+keywords+'%');
        	
        	return (List<Contractor>) this.queryList(buf.toString(), params.toArray());
	}
}
