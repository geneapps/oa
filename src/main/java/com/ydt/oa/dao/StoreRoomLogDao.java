package com.ydt.oa.dao;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.giro.common.dao.BaseDaoHibernate;
import com.giro.common.dao.Pagination;
import com.ydt.oa.entity.FileLog;
import com.ydt.oa.entity.PurchaseApply;
import com.ydt.oa.entity.PurchaseApplyDetails;
import com.ydt.oa.entity.StoreRoomLog;
import com.ydt.oa.entity.StoreRoomMaterial;
import com.ydt.oa.entity.User;




/**
 * 用户数据库层
 * @author caochun
 *
 */
@Repository
public class StoreRoomLogDao extends BaseDaoHibernate<StoreRoomLog>{
	@Autowired
	private PurchaseApplyDao purchaseApplyDao;
	public StoreRoomLog findById(String id){
		return findById(id);
	}
	
	@SuppressWarnings("unchecked")
	public StoreRoomLog findByPurchaseId(String id) {
		 StringBuilder buf=new StringBuilder();
	     List<Object> params=new ArrayList<Object>();
	     buf.append("from StoreRoomLog where applyNo=?");
		 params.add(id);
		    
			List<StoreRoomLog> list=(List<StoreRoomLog>) queryList(buf.toString(), params.toArray());
		     if(list!=null && list.size()>0){
		        	return list.get(0);
		        }else return null;
	}
	/**
	 * 入库查询
	 */
	
	public Pagination listStoreRoomLog(int currPage,int pageSize) {
		 StringBuilder buf=new StringBuilder();
	     List<Object> params=new ArrayList<Object>();
	     buf.append("from StoreRoomLog  where type =? group by applyNo order by applyNo ");
	     params.add(StoreRoomLog.HOUSELOG_TYPE1);
	     return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
	}
	/**
	 * 入库查询2
	 */
	
	@SuppressWarnings("unchecked")
	public List<StoreRoomLog> listStoreRoomLog2() {
		 StringBuilder buf=new StringBuilder();
	     List<Object> params=new ArrayList<Object>();
	     buf.append("from StoreRoomLog where type =?");
	     params.add(StoreRoomLog.HOUSELOG_TYPE1);
	     return (List<StoreRoomLog>) queryList(buf.toString(), params.toArray());
	}
	/*public List<PurchaseIncoming> listnew() {
		 //StringBuilder buf=new StringBuilder();
	     List<PurchaseIncoming> params=new ArrayList<PurchaseIncoming>();
	     List<StoreRoomLog> storeRoomLogList=this.listStoreRoomLog2();
	     PurchaseIncoming purchaseIncoming=null;
	     for(int i=0;i<storeRoomLogList.size();i++) {
	     purchaseIncoming=new PurchaseIncoming();
	     PurchaseApply purchaseApply= purchaseApplyDao.findById(storeRoomLogList.get(i).getApplyNo());
	     purchaseIncoming.setApplyNo(storeRoomLogList.get(i).getApplyNo());
	     purchaseIncoming.setLogTime(storeRoomLogList.get(i).getLogTime());
	     purchaseIncoming.setUser(storeRoomLogList.get(i).getUser());
	    
	     purchaseIncoming.setTitle(purchaseApply.getTitle());
	     params.add(purchaseIncoming);
	     }
	    
	     return params;
	}*/
	/**
	 * 出库查询
	 */
	
	public Pagination listStoreRoomLogout(int currPage,int pageSize) {
		 StringBuilder buf=new StringBuilder();
	     List<Object> params=new ArrayList<Object>();
	     buf.append("from StoreRoomLog where type =?");
	     params.add(StoreRoomLog.HOUSELOG_TYPE2);
	     return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
	}
	/**
	 * 根据领用人查询出库情况
	 */
	
	public Pagination listStoreRoomLogoutByUser(User user,int currPage,int pageSize) {
		 StringBuilder buf=new StringBuilder();
	     List<Object> params=new ArrayList<Object>();
	     buf.append("from StoreRoomLog where user=?");
	     params.add(user);
	     return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
	}

	@SuppressWarnings("unchecked")
	public List<StoreRoomLog> findStoreRoomByAppId(String id)  {
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from StoreRoomLog where applyNo = ?");
        params.add(id);
       return  (List<StoreRoomLog>) queryList(buf.toString(), params.toArray());
	}
	
	@SuppressWarnings("unchecked")
	public List<StoreRoomLog> findStoreRoomLogByUser(String id) {

		StringBuilder buf = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		buf.append("from StoreRoomLog where type = ? and user.id = ?");
		params.add(StoreRoomLog.HOUSELOG_TYPE2);
		params.add(id);
		return  (List<StoreRoomLog>) queryList(buf.toString(), params.toArray());
	}

	public Pagination findStoreRoomByTime(String logTime, int pageNum, int numPerPage) {

		 StringBuilder buf=new StringBuilder();
	     List<Object> params=new ArrayList<Object>();
	     buf.append("from StoreRoomLog where logTime=? ");
	     params.add(logTime);
	     return pageQuery(buf.toString(), params.toArray(),pageNum, numPerPage);
	}
}
