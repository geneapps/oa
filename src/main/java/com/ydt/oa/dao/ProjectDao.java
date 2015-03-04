package com.ydt.oa.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.giro.common.dao.BaseDaoHibernate;
import com.giro.common.dao.Pagination;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.Project;
import com.ydt.oa.entity.SystemParam;
import com.ydt.oa.entity.User;



/**
 * 用户数据库层
 * @author caochun
 *
 */
@Repository
public class ProjectDao extends BaseDaoHibernate<Project>{


	public Pagination list(int currPage,int pageSize){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from Project ");
        buf.append(" order by updateTime DESC");
       // params.add(Boolean.TRUE);
        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
		
	}
	
public Pagination findProject(Project project,int currPage,int pageSize){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from Project where name like ? order by updateTime DESC");
        params.add('%'+project.getName()+'%');
        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
		
	}

	public Project findByName(String name){
		return findByProperty("name", name);
	}
	
	@SuppressWarnings("unchecked")
	public Project findByDepart(String id){
		 StringBuilder buf=new StringBuilder();
	        List<Object> params=new ArrayList<Object>();
	        buf.append("from Project where department.id=?");
	        params.add(id);
	        List<Project> list = (List<Project>) queryList(buf.toString(), params.toArray());
	        if(list!=null && list.size()>0){
	        	return list.get(0);
	        }else return null;
	}
	
	public List<Project> queryProjects() {

		StringBuilder buf = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		buf.append("from Project");
		return (List<Project>) queryList(buf.toString(), params.toArray());
	}
}
