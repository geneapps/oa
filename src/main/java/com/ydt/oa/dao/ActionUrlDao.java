package com.ydt.oa.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.giro.common.dao.BaseDaoHibernate;
import com.giro.common.dao.Pagination;
import com.ydt.oa.entity.ActionUrl;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.OaAction;
import com.ydt.oa.entity.User;




/**
 * 权限数据库操作
 * @author caochun
 *
 */
@Repository
public class ActionUrlDao extends BaseDaoHibernate<ActionUrl>{
	
	public List<ActionUrl> findByUrl(String url) {

        return queryListByProperty("url", url);
	}

}
