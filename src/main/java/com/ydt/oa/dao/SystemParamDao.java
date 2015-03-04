package com.ydt.oa.dao;


import org.springframework.stereotype.Repository;
import com.giro.common.dao.BaseDaoHibernate;
import com.ydt.oa.entity.SystemParam;




/**
 * 参数设置数据库层
 * @author caochun
 *
 */
@Repository
public class SystemParamDao extends BaseDaoHibernate<SystemParam>{

	public String getSystemParamValue(String key){
		SystemParam obj = this.findByProperty("paramName", key);
		
		if(obj==null) return "";
		
		return obj.getParamValue();
	}
	
	public SystemParam getSystemParam(String key){
		SystemParam obj = this.findByProperty("paramName", key);
		return obj;
	}
	
	public void setSystemParam(String name,String key,String value){
		SystemParam obj = findByProperty("paramName", key);
		
		if(obj==null){
			obj = new SystemParam();
			obj.setShowName(name);
			obj.setParamName(key);
			obj.setParamValue(value);
		}else{
			obj.setParamValue(value);
			obj.setShowName(name);
			
		}
		this.saveOrUpdate(obj);
		
	}
	
	public void delSystemParam(String key){
		SystemParam obj = findByProperty("paramName", key);
		
		if(obj!=null){
			this.delete(obj);
			
		}
		
	}
	

	
}
