package com.ydt.oa.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.giro.common.exception.GiroException;
import com.giro.common.util.DateUtils;
import com.giro.common.util.StringUtils;
import com.ydt.oa.bean.AjaxResult;
import com.ydt.oa.dao.CostDetailsDao;
import com.ydt.oa.dao.ExpenseDao;
import com.ydt.oa.dao.FileLogDao;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.Contract;
import com.ydt.oa.entity.CostDetails;
import com.ydt.oa.entity.Expense;
import com.ydt.oa.entity.FileLog;

import com.ydt.oa.entity.User;
import com.ydt.oa.interfaces.ExpenseAppInterface;

/**
 * 系统设置业务逻辑层
 * 
 * @author caochun
 * 
 */
@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class ExpenseAppService implements ExpenseAppInterface {

	@Autowired
	private ApproveService approveService;
	@Autowired
	private ExpenseDao expenseDao;
	@Autowired
	private CostDetailsDao costDetailsDao;
	@Autowired
	private ExpenseService expenseService;
	@Autowired
	private UserService userService;
	@Autowired
	private PaymentOrderService orderService;
	@Autowired
	private FileLogDao fileLogDao;
	private Expense expense;
	private List<CostDetails> costDetailsList;

	/**
	 * 更新请款申请单
	 * 
	 * @param application
	 * @param purchaseApply
	 * @throws GiroException
	 */
	@Override
	public void updateExpenseApp(Application application, Expense expense,String typeParma, List<CostDetails> costList,
			List<FileLog> fileLogList, User user) throws GiroException {

		user = userService.findByUserId(user.getId());
		application.setUser(user);
		System.out.println(typeParma+"@@@@@@");
		application.setDepartment(user.getDepartment());
		if (typeParma.equals("EXPENSEMAN")) {
			application.setApplyType(Application.APP_TYPE_EXPENSEMAN);
			expense.setExpenseType("EXPENSEMAN");
		}else if (typeParma.equals("EXPENSEMATERIAL")) {
			application.setApplyType(Application.APP_TYPE_EXPENSEMATERIAL);
			expense.setExpenseType("EXPENSEMATERIAL");
		}else {
			application.setApplyType(Application.APP_TYPE_EXPENSEOTHER);
			expense.setExpenseType("EXPENSEOTHER");
		}
		expense.setPayTitle(application.getTitle());
		if (StringUtils.isNull(application.getId())) {	
		//新加入的申请人与项目名称
		expense.setUser(user);
		expense.setDepartment(user.getDepartment());
		
		if(StringUtils.isNull(application.getId())){
			// 保存采购申请信息
			expense.setStatus(Expense.EXPENSE_WAITINGAPPROVE);
			expense.setSubmitTime(DateUtils.getNowDateStr());
			expenseDao.saveOrUpdate(expense);
			
			// 将合同id保存到Application中
			application.setApplyNo(expense.getId());
			// 添加新的申请
			approveService.createApp(application, user);
			// 清空expense原有cost
			try{List<CostDetails> costDetailsList = expense.getCostDetails();
			if (costDetailsList != null) {
				for (CostDetails cost : costDetailsList) {
					cost.setExpense(null);
				}
				costDetailsDao.saveOrUpdateAll(costDetailsList);
			}
			//System.out.println(costList);
			if (costList != null) {
				for (CostDetails cost : costList) {
					System.out.println("id:" + cost.getId());
					cost = costDetailsDao.findById(cost.getId());
					cost.setExpense(expense);
					costDetailsDao.saveOrUpdate(cost);
				}
				// costDetailsDao.saveOrUpdateAll(costList);
			}}catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (fileLogList != null) {
				String[] ids = new String[fileLogList.size()];
				for (int i = 0; i < fileLogList.size(); i++) {
					ids[i] = fileLogList.get(i).getId();
				}
				Collection<FileLog> fileLogs = fileLogDao.findByIdList(ids);
				for (FileLog fileLog : fileLogs) {
					fileLog.setBusinessType(Expense.FILE_TYPE);
					fileLog.setBusinessNo(expense.getId());
				}
				System.out.println(expense.getPayTitle());
				System.out.println(application.getApplyNo()+application.getApplyType());
				fileLogDao.saveOrUpdateAll(fileLogs);
			}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			// 修改申请
			approveService.updateApp(application, user);
			expense = expenseDao.findById(expense.getId());
			// 更新采购申请信息
			expense.setStatus(Expense.EXPENSE_WAITINGAPPROVE);
			expense.setSubmitTime(DateUtils.getNowDateStr());
			expenseDao.saveOrUpdate(expense);
			// 清空expense原有cost
			List<CostDetails> costDetailsList = expense.getCostDetails();
			if (costDetailsList != null) {
				for (CostDetails cost : costDetailsList) {
					cost.setExpense(null);
				}
				costDetailsDao.saveOrUpdateAll(costDetailsList);
			}
			// 清除文件绑定
			Collection<FileLog> oldFileList = fileLogDao.findByBusinessNo(Expense.FILE_TYPE, expense.getId());
			for (FileLog file : oldFileList) {
				file.setBusinessNo(null);
			}
			fileLogDao.saveOrUpdateAll(oldFileList);
			// 先保存费用明细
			// costDetailsList =costDetailsDao.getCostDetailsList(user);
			// costDetailsList = (List<CostDetails>)
			// costDetailsDao.findByIdList(costIds);
			//System.out.println(costList);
			if (costList != null) {
				for (CostDetails cost : costList) {
					System.out.println("id:" + cost.getId());
					cost = costDetailsDao.findById(cost.getId());
					cost.setExpense(expense);
					costDetailsDao.saveOrUpdate(cost);
				}
				// costDetailsDao.saveOrUpdateAll(costList);
			}
			// 将合同id保存到Application中
			application.setApplyNo(expense.getId());
			if (fileLogList != null) {
				String[] ids = new String[fileLogList.size()];
				for (int i = 0; i < fileLogList.size(); i++) {
					ids[i] = fileLogList.get(i).getId();
				}
				Collection<FileLog> fileLogs = fileLogDao.findByIdList(ids);
				for (FileLog fileLog : fileLogs) {
					fileLog.setBusinessType(Expense.FILE_TYPE);
					fileLog.setBusinessNo(expense.getId());
				}
				fileLogDao.saveOrUpdateAll(fileLogs);
			}
			expenseDao.saveOrUpdate(expense);
		}
	}
	}

	@Override
	public void deleteApp(Application application) {

		application = approveService.findById(application.getId());
		if (application.getApplyType().equals(Application.APP_TYPE_EXPENSEMAN) ||
				application.getApplyType().equals(Application.APP_TYPE_EXPENSEOTHER)||
				application.getApplyType().equals(Application.APP_TYPE_EXPENSEMATERIAL)) {
			expense = expenseService.findById(application.getApplyNo());
			expense.setStatus(Expense.EXPENSE_DELETE);
			expenseDao.saveOrUpdate(expense);
		}
	}

	@Override
	public void approve(Application application) {

		application = approveService.findById(application.getId());
		if (application.getApplyType().equals(Application.APP_TYPE_EXPENSEMAN) ||
				application.getApplyType().equals(Application.APP_TYPE_EXPENSEOTHER)||
				application.getApplyType().equals(Application.APP_TYPE_EXPENSEMATERIAL) ){
			expense = expenseService.findById(application.getApplyNo());
			if (application.getStatus() == Application.APP_STATUS_APPROVED) {
				expense.setStatus(Expense.EXPENSE_VALID);
				// 创建付款单
				try {
					orderService.createPayOrder(application.getId());
				} catch (GiroException e) {
					e.printStackTrace();
				}
			} else {
				expense.setStatus(Expense.EXPENSE_INVALID);
			}
			expenseDao.saveOrUpdate(expense);
		}
	}
}
