package com.ydt.oa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.ydt.oa.dao.PurchaseApplyDao;
import com.ydt.oa.dao.PurchaseApplyDetailsDao;
import com.ydt.oa.dao.PurchasePlanDao;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.Project;
import com.ydt.oa.entity.PurchaseApply;
import com.ydt.oa.entity.PurchaseApplyDetails;
import com.ydt.oa.entity.PurchasePlan;

@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class PurchaseApplyService {

	@Autowired
	private PurchaseApplyDao purchaseApplyDao;
	@Autowired
	private PurchasePlanService purchasePlanService;
	@Autowired
	private PurchaseApplyDetailsDao purchaseApplyDetailsDao;
	@Autowired
	private ApproveService approveService;
	@Autowired
	private PaymentOrderService paymentOrderService;

	
	
	
	
	public PurchaseApply findById(String id) {

		return purchaseApplyDao.findById(id);
	}

	public Pagination listPurchaseApply(int currPage, int pageSize) {

		return purchaseApplyDao.listPurchaseApply(currPage, pageSize);
	}
	/**
	 * 查找相应项目对应材料的数量
	 * @param id
	 * @param materialId
	 * @return
	 */
	public int getApplyTotalNum(String id,String materialId) {
		int totalNum=0;
		List<PurchaseApply> applyList=this.findByProId(id);
		for(int i=0;i<applyList.size();i++) {
			List<PurchaseApplyDetails> purchaseApplyDetailsList= purchaseApplyDetailsDao
			.findDetailsByAppId(applyList.get(i).getId());
			
			for(int j=0;j<purchaseApplyDetailsList.size();j++) {
				if(purchaseApplyDetailsList.get(j).getMaterial().getId().equals(materialId)) {
					totalNum+=purchaseApplyDetailsList.get(j).getNumber();
				}
			}
		}
		return totalNum;
	}
	public boolean compare(String id,String materialId,int number) {
		
		if(purchasePlanService.getPlanTotalNum(id, materialId)<this.getApplyTotalNum(id, materialId)+number) {
			return true;
		}else {
			return false;
		}
		
	}
	public List<PurchaseApply> findByProId(String id){
		return purchaseApplyDao.findByProject(id);
		
	}

	/**
	 * 采购单已经付款
	 */
	public void payPurchaseApply(Application app) {

		PurchaseApply purchaseApply = findById(app.getApplyNo());
		System.out.println("purchaseApply.status=" + purchaseApply.getStatus());
		if (purchaseApply.getStatus() == PurchaseApply.PURCHASE_STATUS_WAITINGPAY) {
			purchaseApply.setStatus(PurchaseApply.PURCHASE_STATUS_STARTBUY);
		}
		purchaseApplyDao.saveOrUpdate(purchaseApply);
	}

	// 采购入库后报销
	public void submitExpense(PurchaseApply purchase) throws GiroException {

		PurchaseApply purchaseApply = findById(purchase.getId());
		Application app = approveService.findByApplyNo(Application.APP_TYPE_PURCHASE, purchaseApply.getId());
		paymentOrderService.createPayOrder(app.getId());
	}

	/**
	 * 显示可以入库的采购单
	 * @param pro 
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	public Pagination listStartBuyPurchaseApply(String id, int pageNum, int numPerPage) {

		return purchaseApplyDao.listPurchaseApplyByStatus(id,PurchaseApply.PURCHASE_STATUS_STARTBUY, pageNum, numPerPage);
	}
}
