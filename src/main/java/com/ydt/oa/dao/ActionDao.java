package com.ydt.oa.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.giro.common.dao.BaseDaoHibernate;
import com.ydt.oa.entity.OaAction;
import com.ydt.oa.entity.StoreRoom;




/**
 * 权限数据库操作
 * @author caochun
 *
 */
@Repository
public class ActionDao extends BaseDaoHibernate<OaAction>{

	@SuppressWarnings("unchecked")
	public List<OaAction> findAction(){			
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from OaAction ");
        return  (List<OaAction>) queryList(buf.toString(), params.toArray());
		
	}

}
