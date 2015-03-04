package com.ydt.oa.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Request;
import org.apache.poi.hssf.record.formula.ExpPtg;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;

import com.giro.common.action.PageAction;
import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.giro.common.util.AmountUtils;
import com.sun.org.apache.bcel.internal.generic.Type;
import com.ydt.oa.bean.AjaxResult;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.ApproveFlow;
import com.ydt.oa.entity.Contract;
import com.ydt.oa.entity.ContractDetails;
import com.ydt.oa.entity.CostDetails;
import com.ydt.oa.entity.Expense;
import com.ydt.oa.entity.FileLog;
import com.ydt.oa.entity.PaymentOrder;
import com.ydt.oa.entity.SystemParam;

import com.ydt.oa.interfaces.ExpenseAppInterface;

import com.ydt.oa.service.ApproveService;
import com.ydt.oa.service.ExpenseService;
import com.ydt.oa.service.FileService;
import com.ydt.oa.service.PaymentOrderService;
import com.ydt.oa.service.SystemConfigService;

/**
 * 报销管理Action
 * 
 * @author caochun
 * 
 */
public class ExpenseAction extends PageAction {

	public boolean buttonFlag = false;
	@Autowired
	private ApproveService approveService;
	@Autowired
	private ExpenseAppInterface expenseAppService;
	@Autowired
	private ExpenseService expenseService;
	@Autowired
	private PaymentOrderService paymentOrderService;
	@Autowired
	private SystemConfigService configService;
	@Autowired
	private FileService fileLogService;
	private String fileHttpUrl;
	private PaymentOrder paymentOrder;
	private Pagination pagination;
	private Application application;
	private Expense expense;
	private String formAction;
	private AjaxResult ajaxResult;
	private ApproveFlow approveFlow;
	private CostDetails costDetails;
	private List<CostDetails> costDetailsList;
	private String totalPrice;
	private List<FileLog> fileLogList;
	private String typeParma;

	public String getTypeParma() {

		return typeParma;
	}

	public void setTypeParma(String typeParma) {

		this.typeParma = typeParma;
	}

	public String getFileHttpUrl() {

		return fileHttpUrl;
	}

	public void setFileHttpUrl(String fileHttpUrl) {

		this.fileHttpUrl = fileHttpUrl;
	}

	public List<FileLog> getFileLogList() {

		return fileLogList;
	}

	public void setFileLogList(List<FileLog> fileLogList) {

		this.fileLogList = fileLogList;
	}

	public String getTotalPrice() {

		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {

		this.totalPrice = totalPrice;
	}

	public List<CostDetails> getCostDetailsList() {

		return costDetailsList;
	}

	public void setCostDetailsList(List<CostDetails> costDetailsList) {

		this.costDetailsList = costDetailsList;
	}

	public CostDetails getCostDetails() {

		return costDetails;
	}

	public void setCostDetails(CostDetails costDetails) {

		this.costDetails = costDetails;
	}

	public String getFormAction() {

		return formAction;
	}

	public ApproveFlow getApproveFlow() {

		return approveFlow;
	}

	public void setApproveFlow(ApproveFlow approveFlow) {

		this.approveFlow = approveFlow;
	}

	public Application getApplication() {

		return application;
	}

	public boolean isButtonFlag() {

		return buttonFlag;
	}

	public void setApplication(Application application) {

		this.application = application;
	}

	public Pagination getPagination() {

		return pagination;
	}

	@Action(value = "/expense/costlist",
			results = { @Result(name = "success", location = "/expense/costlist.jsp", type = "dispatcher") }
			)
			public String list() {

		try {
			pagination = expenseService.listCostDetailNoExpense(this.getLoginUser().getId(), getPageNum(), getNumPerPage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 添加费用明细
	 * 
	 * @return
	 */
	@Action(value = "/expense/editcostdetails",
			results = {
						@Result(name = "success", location = "/expense/costdetailsByExpense.jsp", type = "dispatcher"),
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher"),
						@Result(name = "costdetails", location = "/expense/costdetails.jsp", type = "dispatcher")
					}
			)
			public String editcostdetails() {

		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				expenseService.saveCostDetails(costDetails, getLoginUser());
				// costDetailsList = new ArrayList<CostDetails>();
				// costDetailsList.add(costDetails);
				ajaxResult.setNavTabId("costDetails");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("数据保存失败");
			}
			return "save";
		} else {
			if (costDetails != null && costDetails.getId() != null) {
				costDetails = expenseService.findDetailsById(costDetails.getId());
				return "success";
			} else {
				// System.out.println("-------------第一次");
				// System.out.println(costDetailsList.size() + "=+==++++");
				return "costdetails";
			}
		}
	}

	/**
	 * 我的申请 添加费用明细
	 * 
	 * @return
	 */
	@Action(value = "/expense/newaddcostdetails",
			results = {
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher"),
						@Result(name = "success", location = "/expense/costdetails1.jsp", type = "dispatcher")
					}
			)
			public String newaddcostdetails() {

		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				// expense = expenseService.findById(expense.getId());
				// costDetails.setExpense(expense);
				System.out.println("costDetails  " + costDetails.getPayMoney());
				expenseService.saveCostDetails1(costDetails, getLoginUser());
				// costDetailsList =
				// expenseService.getCostDetailsByExpense(expense);
				costDetailsList = expenseService.getCostDetailsList(getLoginUser());
				System.out.println(costDetailsList.size() + " ------------------");
				ajaxResult.setNavTabId("main");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("数据保存失败");
			}
			return "save";
		} else {
			if (expense != null) {
				expense = expenseService.findById(expense.getId());
				costDetailsList = expenseService.getCostDetailsByExpense(expense);
				try {
					ajaxResult = new AjaxResult();
					expenseService.saveCostDetails1(costDetails, getLoginUser());
					// costDetailsList.add(costDetails);
				} catch (GiroException e) {
					e.printStackTrace();
					ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
					ajaxResult.setMessage("数据保存失败");
				}
				return "success";
			}
			return "success";
		}
	}

	/**
	 * 我的申请 修改费用明细
	 * 
	 * @return
	 */
	@Action(value = "/expense/neweditcostdetails",
			results = {
						@Result(name = "success", location = "/expense/costdetailsByExpense1.jsp", type = "dispatcher"),
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
			public String neweditcostdetails() {

		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				// expense=expenseService.findById(expense.getId());
				// costDetails.setExpense(expense);
				expenseService.saveCostDetails2(costDetails);
				ajaxResult.setNavTabId("main");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
				// ajaxResult.setForwardUrl("/bolan/user/list.action?pageNum="+this.getPageNum()+"&numPerPage="+this.getNumPerPage());
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("数据保存失败");
			}
			return "save";
		} else {
			if (costDetails != null && costDetails.getId() != null) {
				costDetails = expenseService.findDetailsById(costDetails.getId());
				return "success";
			}
			return "success";
		}
	}

	/**
	 * 编辑采购申请 当已经开始审批的时候则不能修改
	 * 
	 * @return
	 */
	@Action(value = "/approve/editexpenseapp",
			results = {
						@Result(name = "success", location = "/approve/editexpenseapp1.jsp", type = "dispatcher"),
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
			public String editExpenseApp() {

		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				System.out.println("expense1:" + costDetailsList);
				expenseAppService.updateExpenseApp(application, expense, typeParma, costDetailsList, fileLogList,
						getLoginUser());
				ajaxResult.setNavTabId("main");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
			} catch (GiroException e) {
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("数据保存失败");
			}
			return "save";
		} else {
			totalPrice = "0";
			// System.out.println("Application " + application);
			if (application != null && application.getId() != null) {
				application = approveService.findById(application.getId());
				expense = expenseService.findById(application.getApplyNo());
				// System.out.println("expense22" + expense);
				costDetailsList = expenseService.getCostDetailsByExpense(expense);
				for (int i = 0; i < costDetailsList.size(); i++) {
					String price = costDetailsList.get(i).getPayMoney();
					totalPrice = AmountUtils.add(totalPrice, price);
				}
			} /*
			 * else { System.out.println("expense3" + expense); expense = new
			 * Expense(); costDetailsList =
			 * expenseService.getCostDetailsList(getLoginUser()); for (int i =
			 * 0; i < costDetailsList.size(); i++) { int price =
			 * Integer.parseInt(costDetailsList.get(i).getPayMoney());
			 * totalPrice += price; } }
			 */
			return "success";
		}
	}

	/**
	 * 我的申请 修改申请
	 * 
	 * @return
	 */
	@Action(value = "/approve/editexpenseapp1",
			results = {
						@Result(name = "success", location = "/approve/editexpenseapp1.jsp", type = "dispatcher"),
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
			public String editExpenseApp1() {

		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				System.out
						.println("---------------------------------------------------------------------------------------");
				System.out.println("expense1:" + costDetailsList);
				System.out.println("typeParma:" + typeParma);
				expenseAppService.updateExpenseApp(application, expense, typeParma, costDetailsList, fileLogList,
						getLoginUser());
				ajaxResult.setNavTabId("main");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
			} catch (GiroException e) {
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("数据保存失败");
			}
			return "save";
		} else {
			totalPrice = "0";
			if (application != null && application.getId() != null) {
				application = approveService.findById(application.getId());
				expense = expenseService.findById(application.getApplyNo());
				costDetailsList = expenseService.getCostDetailsByExpense(expense);
				for (int i = 0; i < costDetailsList.size(); i++) {
					// double price =
					// Double.parseDouble(costDetailsList.get(i).getPayMoney());
					// totalPrice += price;
					String price = costDetailsList.get(i).getPayMoney();
					totalPrice = AmountUtils.add(totalPrice, price);
				}
				fileLogList = fileLogService.find(Expense.FILE_TYPE, expense.getId());
				fileHttpUrl = configService.findParamValue(SystemParam.PARAM_FILE_HTTP_URL);
			} else {
				costDetailsList = expenseService.getCostDetailsList(getLoginUser());
				for (int i = 0; i < costDetailsList.size(); i++) {
					// double price =
					// Double.parseDouble(costDetailsList.get(i).getPayMoney());
					// totalPrice += price;
					String price = costDetailsList.get(i).getPayMoney();
					totalPrice = AmountUtils.add(totalPrice, price);
				}
			}
			return "success";
		}
	}

	/**
	 * 编辑采购申请 当已经开始审批的时候则不能修改
	 * 
	 * @return
	 */
	@Action(value = "/approve/viewexpenseapp",
			results = {
						@Result(name = "success", location = "/approve/viewexpenseapp.jsp", type = "dispatcher")
					}
			)
			public String viewExpenseApp() {

		if (application != null && application.getId() != null) {
			application = approveService.findById(application.getId());
			expense = expenseService.findById(application.getApplyNo());
			costDetailsList = expenseService.getCostDetailsByExpense(expense);
		}
		return "success";
	}

	/**
	 * 编辑采购申请 当已经开始审批的时候则不能修改
	 * 
	 * @return
	 */
	@Action(value = "/approve/approveexpenseapp",
			results = {
						@Result(name = "success", location = "/approve/approveexpenseapp.jsp", type = "dispatcher"),
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
			public String approveExpenseApp() {

		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				approveService.approve(application, approveFlow, getLoginUser());
				ajaxResult.setNavTabId("main");
				// ajaxResult.setRel("approveRel");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
				// ajaxResult.setForwardUrl("/bolan/user/list.action?pageNum="+this.getPageNum()+"&numPerPage="+this.getNumPerPage());
			} catch (GiroException e) {
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("数据保存失败");
			}
			return "save";
		} else if (formAction != null && formAction.equals("end")) {
			ajaxResult = new AjaxResult();
			try {
				approveService
						.approve(application, approveFlow, getLoginUser(), true);
				ajaxResult.setNavTabId("main");
				// ajaxResult.setRel("approveRel");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
				// ajaxResult.setForwardUrl("/bolan/user/list.action?pageNum="+this.getPageNum()+"&numPerPage="+this.getNumPerPage());
			} catch (GiroException e) {
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("数据保存失败");
			}
			return "save";
		} else {
			if (application != null && application.getId() != null) {
				application = approveService.findById(application.getId());
				buttonFlag = approveService.showButton(application.getId());
				expense = expenseService.findById(application.getApplyNo());
				costDetailsList = expenseService.getCostDetailsByExpense(expense);
				totalPrice = "0";
				for (int i = 0; i < costDetailsList.size(); i++) {
					String price = costDetailsList.get(i).getPayMoney();
					totalPrice = AmountUtils.add(totalPrice, price);
				}
				fileLogList = fileLogService.find(Expense.FILE_TYPE, expense.getId());
				fileHttpUrl = configService.findParamValue(SystemParam.PARAM_FILE_HTTP_URL);
				System.out.println(totalPrice);
			}
			return "success";
		}
	}

	/**
	 * 显示请款申请列表
	 * 
	 * @return
	 */
	@Action(value = "/expense/expenselist",
			results = {
			@Result(name = "success", location = "/expense/list.jsp", type = "dispatcher")
			}
			)
			public String listexpense() {

		pagination = expenseService.list(getPageNum(), getNumPerPage());
		return "success";
	}

	/**
	 * 根据报销单编号显示详细
	 * 
	 * @return
	 */
	@Action(value = "/expense/view",
			results = {
						@Result(name = "success", location = "/expense/viewdetails.jsp", type = "dispatcher")
					}
			)
			public String listExpenseByID() {

		if (expense != null && expense.getId() != null) {
			expense = expenseService.findById(expense.getId());
			if (expense.getExpenseType() == null) {
				application = approveService.findByApplyNo(Application.APP_TYPE_EXPENSEOTHER, expense.getId());
			} else {
				application = approveService.findByApplyNo(expense.getExpenseType(), expense.getId());
			}
			fileLogList = fileLogService.find(Expense.FILE_TYPE, expense.getId());
			fileHttpUrl = configService.findParamValue(SystemParam.PARAM_FILE_HTTP_URL);
			setPaymentOrder(paymentOrderService.findByAppId(application.getId()));
		}
		return "success";
	}

	/**
	 * 删除明细
	 * 
	 * @return
	 */
	@Action(value = "/expense/delcostdetails", results = {
			@Result(name = "success", location = "/tools/common/ajaxresult.jsp", type = "dispatcher") })
	public String delcostdetails() {

		ajaxResult = new AjaxResult();
		if (costDetails.getId() != null) {
			expenseService.delcostdetails(costDetails);
			ajaxResult.setNavTabId("main");
			// ajaxResult.setRel("approveRel");
			ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
		}
		return "success";
	}

	/**
	 * 材料查找
	 * 
	 * @return
	 */
	@Action(value = "/expense/costdetaillookup",
			results = {
						@Result(name = "result", location = "/expense/costdetaillookup.jsp", type = "dispatcher")
					}
			)
			public String findCostDetailNoExpense() {

		pagination = expenseService.listCostDetailNoExpense(this.getLoginUser().getId(), getPageNum(), getNumPerPage());
		return "result";
	}

	public AjaxResult getAjaxResult() {

		return ajaxResult;
	}

	public void setPagination(Pagination pagination) {

		this.pagination = pagination;
	}

	public void setAjaxResult(AjaxResult ajaxResult) {

		this.ajaxResult = ajaxResult;
	}

	public void setFormAction(String formAction) {

		this.formAction = formAction;
	}

	public Expense getExpense() {

		return expense;
	}

	public void setExpense(Expense expense) {

		this.expense = expense;
	}

	public void setPaymentOrder(PaymentOrder paymentOrder) {

		this.paymentOrder = paymentOrder;
	}

	public PaymentOrder getPaymentOrder() {

		return paymentOrder;
	}
}
