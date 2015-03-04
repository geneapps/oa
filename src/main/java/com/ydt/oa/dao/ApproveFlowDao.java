package com.ydt.oa.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.giro.common.dao.BaseDaoHibernate;
import com.giro.common.util.StringUtils;
import com.ydt.oa.entity.ApproveFlow;
import com.ydt.oa.entity.ApproveFlowConfig;
import com.ydt.oa.entity.OaAction;
import com.ydt.oa.entity.Role;
import com.ydt.oa.entity.User;



/**
 * 用户数据库层
 * @author caochun
 *
 */
@Repository
public class ApproveFlowDao extends BaseDaoHibernate<ApproveFlow>{
	
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private ActionDao actionDao;
	@Autowired
	private UserDao userDao;
	
	public void saveAllFlows(List<ApproveFlow> flows){
		
        for(ApproveFlow flow:flows){
        	// 0 角色 1权限 2用户
        	try{
        	if(!StringUtils.isNull(flow.getActionId())){
        	
	        	if(flow.getActionType()==0){
	        		// 
	        		Role role = roleDao.findById(flow.getActionId());
	        		flow.setRemark(role.getName());
	        	}else if(flow.getActionType()==1){
	        		// 
	        		OaAction action = actionDao.findById(flow.getActionId());
	        		flow.setRemark(action.getName());
	        	}else if(flow.getActionType()==2){
	        		// 
	        		User user = userDao.findById(flow.getActionId());
	        		flow.setRemark(user.getRealName());
	        	}
        	}
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        	
        }
        
        this.saveOrUpdateAll(flows);
		
	}

	public void saveFlow(ApproveFlow flow) {

		try{
        	if(!StringUtils.isNull(flow.getActionId())){
        	
	        	if(flow.getActionType()==0){
	        		// 
	        		Role role = roleDao.findById(flow.getActionId());
	        		flow.setRemark(role.getName());
	        	}else if(flow.getActionType()==1){
	        		// 
	        		OaAction action = actionDao.findById(flow.getActionId());
	        		flow.setRemark(action.getName());
	        	}else if(flow.getActionType()==2){
	        		// 
	        		User user = userDao.findById(flow.getActionId());
	        		flow.setRemark(user.getRealName());
	        	}
        	}
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        	
        this.saveOrUpdate(flow);
		
		
	}

}
