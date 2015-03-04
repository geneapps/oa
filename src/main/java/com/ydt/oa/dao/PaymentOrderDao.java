package com.ydt.oa.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.giro.common.dao.BaseDaoHibernate;
import com.giro.common.dao.Pagination;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.PaymentOrder;
import com.ydt.oa.entity.User;
import com.ydt.oa.service.PaymentOrderService;



/**
 * 支付单数据库操作
 * @author caochun
 *
 */
@Repository
public class PaymentOrderDao extends BaseDaoHibernate<PaymentOrder>{

	
	/**
	 * 列出需要审批的，一种是等待审批的，一种是已经完成部分付款的
	 * @param status
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public Pagination listApprove(int currPage,int pageSize){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from PaymentOrder where status = ? or status = ?");
        buf.append(" order by updateTime DESC");
        params.add(PaymentOrder.ORDER_STATUS_WAITINGAPPROVE);
        params.add(PaymentOrder.ORDER_STATUS_PAIDPART);
        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
		
	}
	
	/**
	 * 列出需要付款的
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public Pagination listPay(int currPage,int pageSize){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from PaymentOrder where status = ?");
        buf.append(" order by updateTime DESC");
        params.add(PaymentOrder.ORDER_STATUS_PAYING);
        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
		
	}
	
	/**
	 * 列出已经付款的
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public Pagination listAlreadyPay(int currPage,int pageSize){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from PaymentOrder where status = ? or status = ?");
        buf.append(" order by updateTime DESC");
        params.add(PaymentOrder.ORDER_STATUS_PAID);
        params.add(PaymentOrder.ORDER_STATUS_PAIDPART);
        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
		
	}
	

	public Pagination listMyPay(User user, int currPage, int pageSize) {

		StringBuilder buf = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		buf.append("from PaymentOrder where user.id = ?");
		buf.append(" order by updateTime DESC");
		params.add(user.getId());
		return pageQuery(buf.toString(), params.toArray(), currPage, pageSize);

	}
	
	@SuppressWarnings("unchecked")
	public PaymentOrder findByApplyId(String id)  {
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from PaymentOrder where application.id = ?");
        params.add(id);
        List<PaymentOrder> list = (List<PaymentOrder>) queryList(buf.toString(), params.toArray());        
        if(list!=null && list.size()>0){
        	return list.get(0);
        }else return null;

	}


	/**
	 * 通过Application的Id查找PaymenOrder
	 * 
	 */
	@SuppressWarnings("unchecked")
	public PaymentOrder findByAppId(String id) {
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from PaymentOrder where application.id = ? ");
        params.add(id);
        
        List<PaymentOrder> list = (List<PaymentOrder>) queryList(buf.toString(), params.toArray());
        
        System.out.println("-------------------------" + list.size());
        if(list!=null && list.size()>0){
        	return  list.get(0);
        }else
        	return null;
	}
	
	@SuppressWarnings("unchecked")
	public PaymentOrder listProjectPay(PaymentOrder paymentOrder) {

		StringBuilder buf = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		buf.append("from PaymentOrder where id = ?");
		buf.append(" order by updateTime DESC");
		params.add(paymentOrder.getId());
		List<PaymentOrder> list = (List<PaymentOrder>) queryList(buf.toString(), params.toArray());        
        if(list!=null && list.size()>0){
        	return list.get(0);
        }else return null;

	}
 

}
