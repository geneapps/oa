package com.ydt.oa.dao;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.giro.common.dao.BaseDaoHibernate;
import com.ydt.oa.entity.FileLog;




/**
 * 用户数据库层
 * @author caochun
 *
 */
@Repository
public class FileLogDao extends BaseDaoHibernate<FileLog>{
	
	public FileLog findById(String id){
		return findById(id);
	}
	
	@SuppressWarnings("unchecked")
	public List<FileLog> findByBusinessNo(String businessType,String businessNo){
	       StringBuilder buf=new StringBuilder();
	        List<Object> params=new ArrayList<Object>();
	          buf.append("from FileLog");
           	  buf.append(" where businessType = ? and businessNo = ?" );
		      buf.append(" order by uploadTime DESC");		      
		      params.add(businessType);
		      params.add(businessNo);
		    return (List<FileLog>) queryList(buf.toString(), params.toArray());
	}
	
	public FileLog findOneByBusinessNo(String businessType,String businessNo){
		try{
			return findByBusinessNo(businessType,businessNo).get(0);
		}catch(Exception e){
			return null;
		}

	}
	

}
