package com.ydt.oa.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.giro.common.dao.BaseDaoHibernate;
import com.giro.common.dao.Pagination;
import com.ydt.oa.entity.Project;
import com.ydt.oa.entity.StoreRoom;



/**
 * 读取有关合同的数据
 * @author caochun
 *
 */
@Repository
public class StoreRoomDao extends BaseDaoHibernate<StoreRoom>{

	@SuppressWarnings("unchecked")
	public List<StoreRoom> list(Project project){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from StoreRoom where status <> ? and project.id = ? order by createTime DESC");
        params.add(StoreRoom.STOREROOM_DELETE);
        params.add(project.getId());
        return  (List<StoreRoom>) queryList(buf.toString(), params.toArray());
		
	}
	@SuppressWarnings("unchecked")
	public List<StoreRoom> listName(String id){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from StoreRoom where status <> ? and id <> ?");
        params.add(StoreRoom.STOREROOM_DELETE);
        params.add(id);
        return  (List<StoreRoom>) queryList(buf.toString(), params.toArray());
		
	}
	//详细清单   分页
     public Pagination list(int currPage,int pageSize){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from StoreRoom where status <> ?");
        params.add(StoreRoom.STOREROOM_DELETE);
        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
	}

     public StoreRoom findByProject(Project project){
 		return findByProperty("project", project);
 	}
     public StoreRoom findByName(String name){
  		return findByProperty("name", name);
  	}
	public List<StoreRoom> listAllStoreroom() {
		  StringBuilder buf=new StringBuilder();
	        List<Object> params=new ArrayList<Object>();
	        buf.append("from StoreRoom ");
	        return  (List<StoreRoom>) queryList(buf.toString(), params.toArray());
	}
	public Pagination listStoreRoomMaterialByProject(StoreRoom storeRoom, int pageNum, int numPerPage) {
		StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from StoreRoomMaterial where storeRoom.id = ?");
        params.add(storeRoom.getId());
        return pageQuery(buf.toString(), params.toArray(),pageNum, numPerPage);

	}
}
