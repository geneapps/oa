package com.ydt.oa.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.giro.common.dao.BaseDaoHibernate;
import com.giro.common.dao.Pagination;
import com.ydt.oa.entity.Department;


/**
 * 用户数据库层
 * @author caochun
 *
 */
@Repository
public class DepartmentDao extends BaseDaoHibernate<Department>{


	public Pagination list(int currPage,int pageSize){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from Department ");
        buf.append(" order by modifiedDate DESC");
        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
        }
	
	public List<Department> list(){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from Department where parent.id = null and status <> ?");
        params.add(Department.DEP_STATUS_DELETE);
        return (List<Department>) queryList(buf.toString(), params.toArray());
        }
	
public List<Department> listChild(Department department){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from Department where parent_id = ?");
        params.add(department.getId());
        return (List<Department>) queryList(buf.toString(), params.toArray());
        }
/*     public Department findDepById(Department department) {
    	 StringBuilder buf=new StringBuilder();
         List<Object> params=new ArrayList<Object>();
         buf.append("from Department where status <> ? and id = ?");
         params.add(Department.DEP_STATUS_DELETE);
         params.add(department.getId());
         return (Department) queryList(buf.toString(), params.toArray());
     }*/

	public Department findByName(String name) {
	  
		 StringBuilder buf=new StringBuilder();
	        List<Object> params=new ArrayList<Object>();
	        buf.append("from Department where depName = ?");
	        params.add(name);
	        List<Department> list = (List<Department>) queryList(buf.toString(), params.toArray());
	        
	        if(list!=null && list.size()>0){
	        	return list.get(0);
	        }else return null;
	}
}
        

