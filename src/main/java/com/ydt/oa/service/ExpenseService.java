package com.ydt.oa.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.giro.common.util.StringUtils;
import com.ydt.oa.dao.CostDetailsDao;
import com.ydt.oa.dao.ExpenseDao;

import com.ydt.oa.entity.CostDetails;
import com.ydt.oa.entity.Expense;
import com.ydt.oa.entity.User;

/**
 * 系统设置业务逻辑层
 * 
 * @author caochun
 * 
 */

@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class ExpenseService {

	@Autowired
	private ExpenseDao expenseDao;
	@Autowired
	private UserService userService;
	@Autowired
	private CostDetailsDao costDetailsDao;

	/**
	 * 通过id从数据库中获取值
	 * 
	 * @param id
	 * @return
	 */
	public Expense findById(String id) {
		return expenseDao.findById(id);
	}
    
	public List<Expense> list() {

		return expenseDao.list();
	}

	// 分页
	public Pagination list(int currPage, int pageSize) {

		return expenseDao.list(currPage, pageSize);
	}

   public void saveCostDetails(CostDetails costDetails,User user) throws GiroException{
	   user =userService.findByUserId(user.getId());
	   costDetails.setUser(user);
	   costDetails.setExpenseType(costDetails.getExpenseType().substring(2));
	   costDetailsDao.saveOrUpdate(costDetails);
  }
   public void saveCostDetails1(CostDetails costDetails,User user) throws GiroException{
	   user =userService.findByUserId(user.getId());
	   costDetails.setUser(user);
	   System.out.println(costDetails.getExpenseType()+"@@@@@@@@@@@@@");
	   costDetails.setExpenseType(costDetails.getExpenseType().substring(2));
	   costDetailsDao.saveOrUpdate(costDetails);
  }
   public void saveCostDetails2(CostDetails costDetails) throws GiroException{
		CostDetails costDetailsold=costDetailsDao.findById(costDetails.getId());
		costDetailsold.setPayTime(costDetails.getPayTime());
		costDetailsold.setPayMoney(costDetails.getPayMoney());
		
		costDetailsold.setExpenseType(costDetails.getExpenseType().substring(2));
		costDetailsold.setExpenseTime(costDetails.getExpenseTime());
		costDetailsold.setDescription(costDetails.getDescription());
		
	   costDetailsDao.saveOrUpdate(costDetailsold);
  }
  public CostDetails findDetailsById(String id) {
	 return  costDetailsDao.findById(id);
  }


	public void saveCostDetails(CostDetails costDetails) {
		costDetailsDao.save(costDetails);
	}
	public void delcostdetails(CostDetails costDetails) {
		costDetailsDao.delete(costDetails);
	}


  /**
   * 根据当前用户和报销单ID为空查询出明细
   */
    public List<CostDetails> getCostDetailsList(User user) {
    	return costDetailsDao.getCostDetailsList(user);
    }
    public List<CostDetails> getCostDetailsByExpense(Expense expense) {
    	return expenseDao.getCostDetailsByExpense(expense);
    }

	public void saveExpense(Expense expense) {
		 expenseDao.save(expense);
	}

	public Pagination listCostDetailNoExpense(String id, int pageNum, int numPerPage) {

		return costDetailsDao.listCostDetailNoExpense(id, pageNum, numPerPage);

	}
}
