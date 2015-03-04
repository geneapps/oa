package com.ydt.oa.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.giro.common.dao.BaseDaoHibernate;
import com.giro.common.dao.Pagination;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.Department;
import com.ydt.oa.entity.OaAction;
import com.ydt.oa.entity.PaymentOrder;
import com.ydt.oa.entity.Role;
import com.ydt.oa.entity.User;



/**
 * 用户数据库层
 * @author caochun
 *
 */
@Repository
public class ApplicationDao extends BaseDaoHibernate<Application>{



	public Pagination list(int currPage,int pageSize){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from Application where status <> ?");
        buf.append(" order by createTime DESC");
        params.add(Application.APP_STATUS_DELETE);
        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
		
	}   
	
	/**
	 * 查询用户的申请
	 * @param user
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public Pagination findApplicationByUser(User user,int currPage,int pageSize){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from Application where status in(?,?) and user.id = ?");
        buf.append(" order by createTime DESC");
        params.add(Application.APP_STATUS_APPROVEING);
        params.add(Application.APP_STATUS_WAITINGAPPROVE);
        params.add(user.getId());
        System.out.println(user.getRealName()+"$$$$$");
        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
		
	}
	
	/**
	 * 历史申请
	 * @param user
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public Pagination findHistoryApplicationByUser(User user,int currPage,int pageSize){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from Application where status in (1,2,4) and user.id = ?");
        buf.append(" order by createTime DESC");
        params.add(user.getId());
        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
		
	}
	
	/**
	 * 查询需要审批的申请
	 * @param role
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public Pagination findApprove(User user,int currPage,int pageSize){
		
	        StringBuilder buf=new StringBuilder();
	        List<Object> params=new ArrayList<Object>();
	        
	        Set<OaAction> actions = user.getRole().getActions();
	        
	        StringBuffer sb = new StringBuffer();
	        for(OaAction action:actions){
	        	if(sb.length()>0){
	        		sb.append(",");
	        	}
	        	sb.append("'").append(action.getId()).append("'");
	        }
	        
	        StringBuffer departs = new StringBuffer();
//	    	System.out.println(user.getAllChilds().size());
	        for(Department d:user.getAllChilds()){
	        
	        	if(departs.length()>0){
	        		departs.append(",");
	        	}
	        	departs.append("'").append(d.getId()).append("'");
	        }
	    	
	        buf.append("from Application where status <> ? and id in ( select application.id from ApproveFlow where ( (actionType=0 and actionId=? ) or (actionType=1 and (actionId in ("+sb.toString()+")) and (application.department.id in ("+departs.toString()+"))) or (actionType=2 and actionId=? ) ) and activate = ?)");
	        buf.append(" order by createTime DESC");
	        params.add(Application.APP_STATUS_DELETE);
	        params.add(user.getRole().getId());
	        
	       
	        System.out.println(buf.toString());
	        //System.out.println((departs.toString()));
//	        params.add(user.getDepartment().getId());
	        params.add(user.getId());
	        params.add(Boolean.TRUE);
	        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
			
	}

	public Pagination findApproveByUser(User user, int currPage, int pageSize) {

		StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from Application where status <> ? and id in ( select application.id from ApproveFlow where user.id=? and activate = ?)");
        buf.append(" order by createTime DESC");
        params.add(Application.APP_STATUS_DELETE);
        params.add(user.getId());
        params.add(Boolean.FALSE);
        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
	}
	/**
	 * 查询审批通过了的申请
	 * @param user
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public Pagination findApproveByCashier(int currPage, int pageSize) {

		StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from Application where status = ? and applyType not in ('CONTRACT','PURCHASEPLAN','PROJECT') " +
        		"and id not in ( select application.id from PaymentOrder)");
        params.add(Application.APP_STATUS_APPROVED);
        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
	}
	
	/**
	 * 根据类型和编号查询申请单
	 * @param type
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Application findByApplyNo(String type,String id)  {
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from Application where applyType = ? and applyNo = ?");
        params.add(type);
        params.add(id);
        
        List<Application> list = (List<Application>) queryList(buf.toString(), params.toArray());
        
        if(list!=null && list.size()>0){
        	return list.get(0);
        }else return null;

	}
	
	/**
	 * 根据类型和编号查询申请单
	 * @param type
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Application> findByApplyIdDep(String id)  {
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from Application where department.id = ? and status = ? and applyType not in ('CONTRACT','PURCHASEPLAN','PROJECT') " 
        		+" and id in ( select application.id from PaymentOrder)");
        params.add(id);
        params.add(Application.APP_STATUS_APPROVED);
       return (List<Application>) queryList(buf.toString(), params.toArray());        
       


	}

	public Pagination findHistoryApprove(Application application, int currPage, int pageSize) {
		StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from Application where title like ? and user.realName like ? order by createTime DESC");
        params.add('%'+application.getTitle()+'%');
        params.add('%'+application.getUser().getRealName()+'%');
        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
	}
}
