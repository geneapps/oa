package com.ydt.oa.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.giro.common.dao.BaseDaoHibernate;
import com.giro.common.dao.Pagination;
import com.ydt.oa.entity.Department;
import com.ydt.oa.entity.Role;

/**
 * 用户数据库层
 * @author caochun
 *
 */
@Repository
public class RoleDao extends BaseDaoHibernate<Role>{


	public Pagination list(int currPage,int pageSize){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from Role where status <> ?");
        buf.append(" order by modifiedDate DESC");
        params.add(Role.ROLE_STATUS_DELETE);
        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
		
	}
	
	public List<Role> list(String actionId){		

		String sql = "select * from oa_role_action where actions_idd = '"+actionId+"'";
		
		 Session session=this.getSession();
		  
		  Transaction transaction=session.beginTransaction();
		  
		  
		  SQLQuery s=(SQLQuery) session.createSQLQuery(sql); //.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
		  
//		  s.addScalar("id",Hibernate.INTEGER); //列名, 数据类型
		  s.addScalar("oa_role_idd",Hibernate.STRING);
		  
		  List<String> list=s.list(); 
		  String[] roleIds = new String[list.size()];
		  StringBuffer sb = new StringBuffer();
		  List<Role> roles = new ArrayList<Role>();
		  sb.append("(");
		  boolean first = true;
		  for(String objs:list){
			  
			  if(first){
				  first = false;
			  }else{
				  sb.append(",");
			  }
			  sb.append("'").append(objs).append("'");
			  Role temp = findById(objs);
			  roles.add(temp);
		  }
		  sb.append(")");
		System.out.println(">>>>RoleId:"+sb.toString());
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from Role where id in ? and status <> ?");
        params.add(sb.toString());
        params.add(Role.ROLE_STATUS_DELETE);
      //  List<Role> roles = (List<Role>) queryList(buf.toString(), params.toArray());
        System.out.println(roles.size() + "______________________");
        return roles;
     }
	

	/*public List<Role> list(Department department){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from Role where department_idd = ? and status <> ?");
        params.add(department.getId());
        params.add(Role.ROLE_STATUS_DELETE);
        return (List<Role>) queryList(buf.toString(), params.toArray());
        }
*/

	public Role findRoleById(String id) {
		
		return findById(id);
		
	}
	
	
}
