package com.ydt.oa.service;


import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.giro.common.util.AmountUtils;
import com.giro.common.util.DateUtils;

import com.ydt.oa.dao.BorrowMoneyDao;
import com.ydt.oa.dao.ExpenseDao;
import com.ydt.oa.dao.PaymentLogDao;
import com.ydt.oa.dao.PaymentOrderDao;
import com.ydt.oa.dao.PurchaseApplyDao;
import com.ydt.oa.dao.RequestMoneyDao;

import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.ApproveFlow;
import com.ydt.oa.entity.BorrowMoney;
import com.ydt.oa.entity.CostDetails;
import com.ydt.oa.entity.Expense;
import com.ydt.oa.entity.OaAction;
import com.ydt.oa.entity.PaymentLog;
import com.ydt.oa.entity.PaymentOrder;
import com.ydt.oa.entity.PurchaseApply;
import com.ydt.oa.entity.PurchaseApplyDetails;
import com.ydt.oa.entity.RequestMoney;
import com.ydt.oa.entity.User;



/**
 * 申请的业务逻辑层，主要实现了申请单创建、修改、删除以及审批流程控制
 * 
 * @author caochun
 * 
 */
@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class PaymentOrderService {

	@Autowired
	private PaymentOrderDao paymentOrderDao;
	@Autowired
	private PaymentLogDao paymentLogDao;
	
	@Autowired
	private ApproveService approverService;
	@Autowired
	private BorrowMoneyDao borrowMoneyDao;
	@Autowired
	private RequestMoneyDao requestMoneyDao;
	@Autowired
	private ExpenseDao expenseDao;
	@Autowired
	private PurchaseApplyDao purchaseApplyDao;
	@Autowired
	PurchaseApplyService purchaseApplyService;
	@Autowired
	private ExpenseService expenseService;
	/**
	 * 显示需要审批的
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public Pagination listApprove(int currPage,int pageSize){		
        return paymentOrderDao.listApprove(currPage, pageSize);
	} 
	
	public Pagination listPay(int currPage,int pageSize){		
        return paymentOrderDao.listPay(currPage, pageSize);
	} 


	public Pagination listAlreadyPay(int currPage, int pageSize) {
		return paymentOrderDao.listAlreadyPay(currPage, pageSize);
	}

	public Pagination listMyPay(User user, int currPage, int pageSize) {
		return paymentOrderDao.listMyPay(user, currPage, pageSize);
	}

	public PaymentOrder listProjectPay(PaymentOrder paymentOrder) {
		return paymentOrderDao.listProjectPay(paymentOrder);
	}
      
      public void savePaymentOrder(PaymentOrder paymentOrder) {
    	  if(paymentOrder.getActualAmount().equals(paymentOrder.getPayAmount())) {
    		  paymentOrder.setStatus(PaymentOrder.ORDER_STATUS_PAID);
    	  }else if(paymentOrder.getActualAmount().equals(paymentOrder.getReadyAmount())) {
    		  paymentOrder.setStatus(PaymentOrder.ORDER_STATUS_PAIDPART);
    	  }else{
    		  paymentOrder.setStatus(PaymentOrder.ORDER_STATUS_PAYING);
    	  }
    	  paymentOrderDao.save(paymentOrder);
      }
      
      /**
       * 创建付款单
       * @param appId
       */
      public void createPayOrder(String appId) throws GiroException{
    	  Application app = approverService.findById(appId);
    	  
    	  
    	  
    	  if(app.getStatus()==Application.APP_STATUS_APPROVED){
    		  
    		  PaymentOrder order = null;
    		  
    		  
    		  // 借款申请
    		  if(app.getApplyType().equals(Application.APP_TYPE_BORROWMONEY)){
    			  order = new PaymentOrder();
    			  order.setBusinessType(Application.APP_TYPE_BORROWMONEY);
    			  order.setActualAmount("0");
    			  order.setApplication(app);
    			  
    			  BorrowMoney borrowMoney = borrowMoneyDao.findById(app.getApplyNo());
    			  order.setPayAmount(borrowMoney.getBorrowMoney());
    			  order.setReadyAmount(order.getPayAmount());
    			  order.setActualAmount("0");
    			  order.setDescription(borrowMoney.getBorrowReason());
    			  order.setRequiredPayTime(borrowMoney.getBorrowTime());
    			  order.setTitle(borrowMoney.getBorrowTitle());
    			  order.setUser(app.getUser());
    			  
    			  order.setAccount(borrowMoney.getAccount());
    			  order.setContractNo(borrowMoney.getContractNo());
    			  order.setPayee(borrowMoney.getPayee());
    			  
    			  
    		  }else if(app.getApplyType().equals(Application.APP_TYPE_REQUESTMONEYENGINEER)) {
    			  order = new PaymentOrder();
    			  order.setBusinessType(Application.APP_TYPE_REQUESTMONEYENGINEER);
    			  order.setActualAmount("0");
    			  order.setApplication(app);

    			  RequestMoney requestMoney = requestMoneyDao.findById(app.getApplyNo());
    			  order.setPayAmount(requestMoney.getRequestMoney());
    			  order.setReadyAmount(order.getPayAmount());
    			  order.setActualAmount("0");
    			  order.setDescription(requestMoney.getReason());
    			  order.setRequiredPayTime(requestMoney.getPayTime());
    			  order.setTitle(requestMoney.getRequestTitle());
    			  order.setUser(app.getUser());
    			  
    			  order.setAccount(requestMoney.getAccount());
    			  order.setContractNo(requestMoney.getContractNo());
    			  order.setPayee(requestMoney.getPayee());
    			  
    			  
    		  }else if(app.getApplyType().equals(Application.APP_TYPE_REQUESTMONEYMATERIAL)) {
    			  order = new PaymentOrder();
    			  order.setBusinessType(Application.APP_TYPE_REQUESTMONEYMATERIAL);
    			  order.setActualAmount("0");
    			  order.setApplication(app);
    			  
    			  RequestMoney requestMoney = requestMoneyDao.findById(app.getApplyNo());
    			  order.setPayAmount(requestMoney.getRequestMoney());
    			  order.setReadyAmount(order.getPayAmount());
    			  order.setActualAmount("0");
    			  order.setDescription(requestMoney.getReason());
    			  order.setRequiredPayTime(requestMoney.getPayTime());
    			  order.setTitle(requestMoney.getRequestTitle());
    			  order.setUser(app.getUser());
    			  
    			  order.setAccount(requestMoney.getAccount());
    			  order.setContractNo(requestMoney.getContractNo());
    			  order.setPayee(requestMoney.getPayee());
    			  
    			  
    		  }else if(app.getApplyType().equals(Application.APP_TYPE_EXPENSEOTHER)) {
    			  order = new PaymentOrder();
    			  order.setBusinessType(Application.APP_TYPE_EXPENSEOTHER);
    			  order.setActualAmount("0");
    			  order.setApplication(app);
    			  
    			  Expense expense = expenseDao.findById(app.getApplyNo());
    			  
    			  System.out.println(  expense.getPayTitle() + " =-----------");
    			  
    			 List<CostDetails> costDetailsList = expenseService.getCostDetailsByExpense(expense);
    			 String totalPrice = "0";
    			  for (int i = 0; i < costDetailsList.size(); i++) {
    				String price = costDetailsList.get(i).getPayMoney();
  					totalPrice = AmountUtils.add(totalPrice, price);
  				}
    			  order.setPayAmount(totalPrice+"");
    			  
    			  System.out.println(  totalPrice + " =-----------");
    			  
    			  order.setReadyAmount(order.getPayAmount());
    			  order.setActualAmount("0");
    			  order.setRequiredPayTime(expense.getSubmitTime());
    			  order.setTitle(expense.getPayTitle());
    			  order.setUser(app.getUser());
    			  
    			  
    			  order.setAccount(expense.getAccount());
    			  order.setContractNo(expense.getContractNo());
    			  order.setPayee(expense.getPayee());
    			  
    		  }else if(app.getApplyType().equals(Application.APP_TYPE_PURCHASE)) {
    			  // 采购时创建申请单
    			  
    			  order = app.getPaymentOrder();
    			  
    			  if(order==null){
    				  order = new PaymentOrder();
    				  order.setBusinessType(Application.APP_TYPE_PURCHASE);
    				  order.setApplication(app);
    				  order.setActualAmount("0");
    				  
    			  }    			  
    			  
    			  
    			  PurchaseApply purchaseApply = purchaseApplyDao.findById(app.getApplyNo());
    			  
    			  if(purchaseApply.getStatus()==PurchaseApply.PURCHASE_STATUS_WAITINGPAY){
    				  // 预付款
    				  order.setPayAmount(purchaseApply.getAdvancePay());
       				  order.setReadyAmount(purchaseApply.getAdvancePay());
       				  order.setRequiredPayTime(purchaseApply.getPrePurchaseTime());
    			  }else if(purchaseApply.getStatus()==PurchaseApply.PURCHASE_STATUS_STARTEXPENSE){
    				  // 入库后报销
    				  
    					String totalPrice = "";
    					
    					List<PurchaseApplyDetails> detailsList = purchaseApply.getPurchaseApplyDetails();
    					for(PurchaseApplyDetails detail:detailsList){
    						
    						totalPrice = AmountUtils.add(totalPrice,AmountUtils.multiply(detail.getActualPrice(), detail.getHouseNumber()+""));
      						
    					}
    				  
    				  order.setPayAmount(totalPrice);
       				  order.setReadyAmount(totalPrice);
       				  order.setRequiredPayTime(DateUtils.getNowDateStr());
       				  // 更新采购单完成
       				  purchaseApply.setStatus(PurchaseApply.PURCHASE_STATUS_FINISH);
       				  purchaseApplyDao.saveOrUpdate(purchaseApply);
    			  }   			  
    			  
    			  order.setTitle(purchaseApply.getTitle());

    			  order.setUser(purchaseApply.getBuyUser());			  
    			  
    		  }
    		  order.setStatus(PaymentOrder.ORDER_STATUS_PAYING);
    		  paymentOrderDao.save(order);
    		  
    		  
    		  
    		  
    		  
    	  }else{
    		  throw new GiroException(-1,"申请没有通过审批");
    	  }
    	  
      }
      
      public void approve(PaymentOrder order) throws GiroException{
    	  PaymentOrder oldOrder = paymentOrderDao.findById(order.getId());
    	  oldOrder.setReadyAmount(order.getReadyAmount());

    	  
    	  if(!AmountUtils.greater(oldOrder.getPayAmount(),oldOrder.getReadyAmount())){
    		  throw new GiroException(-1,"付款金额超过了准备付款金额");
    	  }
    	  
    	  oldOrder.setStatus(PaymentOrder.ORDER_STATUS_PAYING);
   		  paymentOrderDao.saveOrUpdate(oldOrder);
    	  
      }
      
      public void pay(PaymentLog log) throws GiroException{
    	  PaymentOrder order = paymentOrderDao.findById(log.getPaymentOrder().getId());
    	  
    	  log.setPayTime(DateUtils.getNowDateStr());
    	  log.setPaymentOrder(order);
    	  log.setStatus(PaymentLog.PAYMENTLOG_STATUS_VALID);
    	  
    	  String number = AmountUtils.add(order.getActualAmount(), log.getPayAmount());
    	  if(!AmountUtils.greater(order.getReadyAmount(),number)){
    		  throw new GiroException(-1,"付款金额超过了准备付款金额");
    	  }
    	  order.setActualAmount(AmountUtils.add(order.getActualAmount(), log.getPayAmount()));
    	  
    	  paymentLogDao.saveOrUpdate(log);
    	  
    	  
    	  if(AmountUtils.equals(order.getActualAmount(), order.getPayAmount())){
    		  order.setStatus(PaymentOrder.ORDER_STATUS_PAID);
    	  }else if(AmountUtils.equals(order.getActualAmount(),order.getReadyAmount())){
    		  order.setStatus(PaymentOrder.ORDER_STATUS_PAIDPART);
    	  }else{
    		  order.setStatus(PaymentOrder.ORDER_STATUS_PAYING);
    	  }    	  
    	  
   		  paymentOrderDao.saveOrUpdate(order);
   		  System.out.println("order.status="+order.getStatus());
   		/*if(order.getStatus()!=PaymentOrder.ORDER_STATUS_PAYING){
  		  // 通知业务付款状态以便开始下步流程
   			System.out.println(order.getApplication().getApplyType() + "----------");
   		   if(order.getApplication().getApplyType().equals(Application.APP_TYPE_PURCHASE)){
  		  
   			   purchaseApplyService.payPurchaseApply(order.getApplication());
   		   }else{
   			throw new GiroException(-1,"申请单类型错误");
   		   }*/
   		
    	  
      }
      
      public PaymentOrder findById(String id){
    	  return paymentOrderDao.findById(id);
    	  
      }
      
      public PaymentOrder findByApplyId(String id){
    	  return paymentOrderDao.findByApplyId(id);
    	  
      }
      
      public PaymentLog findPaymentLogById(String id){
    	  return paymentLogDao.findById(id);
    	  
      }
      
  	
  	
  	 /**
  	  * 通过申请表的id查找付款单
  	  */
      public PaymentOrder findByAppId(String id){
    	  
    	  return paymentOrderDao.findByAppId(id);
      }
}
