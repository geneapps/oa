package com.ydt.oa.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.giro.common.dao.BaseDaoHibernate;
import com.giro.common.util.StringUtils;
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
public class ApproveFlowConfigDao extends BaseDaoHibernate<ApproveFlowConfig>{

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private ActionDao actionDao;
	@Autowired
	private UserDao userDao;

	/**
	 * 获取指定申请的审批流程
	 * @param appType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ApproveFlowConfig> list(String appType){		
		List<ApproveFlowConfig> configList = null;
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from ApproveFlowConfig where appType = ?");
        buf.append(" order by orderBy ASC");
        params.add(appType);
        
        
        
        configList =  (List<ApproveFlowConfig>) this.queryList(buf.toString(), params.toArray());
        
        for(ApproveFlowConfig config:configList){
        	// 0 角色 1权限 2用户
        	try{
        	if(!StringUtils.isNull(config.getActionId())){
        	
	        	if(config.getActionType()==0){
	        		// 
	        		Role role = roleDao.findById(config.getActionId());
	        		config.setRemark(role.getName());
	        	}else if(config.getActionType()==1){
	        		// 
	        		OaAction action = actionDao.findById(config.getActionId());
	        		config.setRemark(action.getName());
	        	}else if(config.getActionType()==2){
	        		// 
	        		User user = userDao.findById(config.getActionId());
	        		config.setRemark(user.getRealName());
	        	}
        	}
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        	
        }
        
        
        return configList;
		
	}

}
