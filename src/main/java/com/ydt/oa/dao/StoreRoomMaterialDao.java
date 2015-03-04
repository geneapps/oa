package com.ydt.oa.dao;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.giro.common.dao.BaseDaoHibernate;
import com.giro.common.dao.Pagination;
import com.ydt.oa.entity.FileLog;
import com.ydt.oa.entity.StoreRoomMaterial;




/**
 * 用户数据库层
 * @author caochun
 *
 */
@Repository
public class StoreRoomMaterialDao extends BaseDaoHibernate<StoreRoomMaterial>{
	
	
	@SuppressWarnings("unchecked")
	public StoreRoomMaterial findByRoomAndMaterial(String storeRoomId,String MaterialId){
	       StringBuilder buf=new StringBuilder();
	        List<Object> params=new ArrayList<Object>();
	          buf.append("from StoreRoomMaterial");
           	  buf.append(" where storeRoom.id = ? and material.id = ?" );	      
		      params.add(storeRoomId);
		      params.add(MaterialId);
		     List<StoreRoomMaterial> list=(List<StoreRoomMaterial>) queryList(buf.toString(), params.toArray());
		     if(list!=null && list.size()>0){
		        	return list.get(0);
		        }else return null;
		     
	}
	/**
	 * 根据库房查出对应的库房材料
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<StoreRoomMaterial> list(String id) {
		 StringBuilder buf=new StringBuilder();
	     List<Object> params=new ArrayList<Object>();
	     buf.append("from StoreRoomMaterial where storeRoom.id=?");
	     params.add(id);
	     return (List<StoreRoomMaterial>) queryList(buf.toString(), params.toArray());
     
	}
	/**
	 * 查出库房材料
	 * @param id
	 * @return
	 */
	public Pagination listStoreRoomMaterial(int currPage,int pageSize) {
		 StringBuilder buf=new StringBuilder();
	     List<Object> params=new ArrayList<Object>();
	     buf.append("from StoreRoomMaterial order by storeRoom ");
	     return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
     
	}

	/**
	 * 根据库房Id查出对应的库房材料和数量
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<StoreRoomMaterial> findStoreRoomMaterialById(String id) {
		 StringBuilder buf=new StringBuilder();
	     List<Object> params=new ArrayList<Object>();
	     buf.append("from StoreRoomMaterial where storeRoom.id=?");
	     params.add(id);
	     return (List<StoreRoomMaterial>) queryList(buf.toString(), params.toArray());
     
	}
}
