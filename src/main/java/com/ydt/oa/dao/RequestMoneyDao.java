  package com.ydt.oa.dao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.giro.common.dao.BaseDaoHibernate;
import com.giro.common.dao.Pagination;
import com.ydt.oa.entity.RequestMoney;




	/**
	 * 读取有关合同的数据
	 * @author caochun
	 *
	 */
	@Repository
	public class RequestMoneyDao extends BaseDaoHibernate<RequestMoney>{
     
		@SuppressWarnings("unchecked")
		public List<RequestMoney> list(){		
			
	        StringBuilder buf=new StringBuilder();
	        List<Object> params=new ArrayList<Object>();
	        buf.append("from RequestMoney");
	        return  (List<RequestMoney>) queryList(buf.toString(), params.toArray());
			
		}
		
		//详细清单   分页
	     public Pagination list(int currPage,int pageSize){		
			
	        StringBuilder buf=new StringBuilder();
	        List<Object> params=new ArrayList<Object>();
	        buf.append("from RequestMoney");
	        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
		}

		
	}

