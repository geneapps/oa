package com.ydt.oa.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.ydt.oa.dao.ActionDao;
import com.ydt.oa.dao.ActionUrlDao;
import com.ydt.oa.entity.ActionUrl;
import com.ydt.oa.entity.OaAction;
import com.ydt.oa.entity.User;


/**
 * 权限业务逻辑层
 * @author caochun
 *
 */
@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class RoleManageService {

	@Autowired
	private ActionDao actionDao;
	@Autowired
	private ActionUrlDao actionUrlDao;
	


	public Pagination find(int pageNumber,int pageSize) {
		
		return actionDao.getAll(pageNumber, pageSize);

	}
	
	public OaAction findById(String id) {
		
		return actionDao.findById(id);

	}
	
	public List<OaAction> findByUrl(String url) {
		
		List<OaAction> oaActions = new ArrayList<OaAction>();
		
		List<ActionUrl> urls = actionUrlDao.findByUrl(url);
		
		for(ActionUrl au: urls ){
			oaActions.add(au.getAction());
		}
		
		return oaActions;

	}
	
	public HashMap<String,ActionUrl> authorityUrls() {
	
		HashMap<String,ActionUrl> authorityUrls = new HashMap<String,ActionUrl>();
			List<ActionUrl> urls = actionUrlDao.getAll();
			for(ActionUrl url:urls){
				authorityUrls.put(url.getUrl(), url);
			}
			
			return authorityUrls;
	
		}
	
	public boolean canAccess(String url,User user) {
		
		List<OaAction> actions = findByUrl(url);
		
		if(actions==null || actions.size()==0){
			return true;
		}
		
		for(OaAction a: actions){
			System.out.println(a.getName());
				if(user.canAccess(a.getActionValue())){
					return true;
				}
		}
		
		return false;

	}
	
	public List<OaAction> findAction(){
		
		return actionDao.findAction();
	}

}
