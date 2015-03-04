package com.ydt.oa.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.giro.common.dao.BaseDaoHibernate;
import com.giro.common.dao.Pagination;
import com.ydt.oa.entity.Material;
import com.ydt.oa.entity.Role;
import com.ydt.oa.entity.User;



/**
 * 用户数据库层
 * @author caochun
 *
 */
@Repository
public class UserDao extends BaseDaoHibernate<User>{

	
public Pagination query(String key,String type, int currPage,int pageSize){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
          buf.append("from User");
        
  
        	buf.append(" where "+type+" like ?" );
//        	params.add(type);
        	params.add('%'+key+'%');
	        
	        buf.append(" order by lastLogin DESC");

	        logger.info(buf.toString());
	        logger.info(params.toString());
        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
		
	}

public Pagination lookupUser(User user,int currPage,int pageSize) {
	
    StringBuilder buf=new StringBuilder();
    List<Object> params=new ArrayList<Object>();
      buf.append("from User where realName like ?");
      params.add('%'+user.getRealName()+'%');
      buf.append("and status <> 99");
    	return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
}


public Pagination queryByDepartment(String departmentId,String roleId, int currPage,int pageSize){		
	
    StringBuilder buf=new StringBuilder();
    List<Object> params=new ArrayList<Object>();
      buf.append("from User where department.id = ? and role.id = ? ");
    
        buf.append(" order by lastLogin DESC");
        
        params.add(departmentId);
        params.add(roleId);
        logger.info(buf.toString());
        logger.info(params.toString());
    return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
	
}


	public Pagination list(int currPage,int pageSize){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from User where status = ?");
        buf.append(" order by lastLogin DESC");
        params.add(Boolean.TRUE);
        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
		
	}
	
@SuppressWarnings("unchecked")
public List<User> list(){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from User where status = ?");
        buf.append(" order by lastLogin DESC");
        params.add(Boolean.TRUE);
        return (List<User>) queryList(buf.toString(), params.toArray());
		
	}	

public List<User> listByRole(Role role){		
	
    StringBuilder buf=new StringBuilder();
    List<Object> params=new ArrayList<Object>();
    buf.append("from User where status = ? and role.id = ?");
    buf.append(" order by lastLogin DESC");
    params.add(1);
    
    StringBuffer sb = new StringBuffer();
//    sb.append("(");
//    boolean first = true;
//    for(Role role:roles){
//    	
//    	if(first){
//    		first = false;
//    	}else{    		
//    		sb.append(",");
//    	}
//    	sb.append("'").append(role.getId()).append("'");
//    }
//    sb.append(")");
    sb.append(role.getId());
    params.add(sb.toString());
    List<User> users = (List<User>) queryList(buf.toString(), params.toArray());
    //System.out.println(users.size() + " users____________________");
    return users;
	
}	
	
	public User findByUserNo(String userNo){
		return findByProperty("userNo", userNo);
	}
	public User findByUserName(String userName){
		return findByProperty("realName", userName);
	}
	@SuppressWarnings("unchecked")
	public List<User> suggest(String suggestFields, String keywords) {
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
          buf.append("from User");        
  
        	buf.append(" where "+suggestFields+" like ?" );
        	params.add('%'+keywords+'%');
        	
        	return (List<User>) this.queryList(buf.toString(), params.toArray());


	}
	
//	public User findByName(String name) {
//		return findByProperty("realName", name);
//	}
	
	public Pagination userList(int currPage,int pageSize) {
		
	    StringBuilder buf=new StringBuilder();
	    List<Object> params=new ArrayList<Object>();
	    buf.append("from User where status <> 99 order by userNo "); 
	    return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
	}
	
}
