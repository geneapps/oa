  package com.ydt.oa.dao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.giro.common.dao.BaseDaoHibernate;
import com.giro.common.dao.Pagination;
import com.ydt.oa.entity.CostDetails;
import com.ydt.oa.entity.Expense;




	/**
	 * 读取有关合同的数据
	 * @author caochun
	 *
	 */
	@Repository
	public class ExpenseDao extends BaseDaoHibernate<Expense>{
     
		@SuppressWarnings("unchecked")
		public List<Expense> list(){		
			
	        StringBuilder buf=new StringBuilder();
	        List<Object> params=new ArrayList<Object>();
	        buf.append("from Expense");
	        return  (List<Expense>) queryList(buf.toString(), params.toArray());
			
		}
		
		//详细清单   分页
	     public Pagination list(int currPage,int pageSize){		
			
	        StringBuilder buf=new StringBuilder();
	        List<Object> params=new ArrayList<Object>();
	        buf.append("from Expense where status <> ? order by submitTime DESC");
	        params.add(Expense.EXPENSE_DELETE);
	        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
		}
	     @SuppressWarnings("unchecked")
		public List<CostDetails> getCostDetailsByExpense(Expense expense) {
	    	     StringBuilder buf=new StringBuilder();
		        List<Object> params=new ArrayList<Object>();
		        buf.append("from CostDetails where expense.id=?");
		        params.add(expense.getId());
		        return  (List<CostDetails>) queryList(buf.toString(), params.toArray());
	     }
	     

	}

