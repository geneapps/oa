package com.ydt.oa.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.giro.common.action.PageAction;
import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.ydt.oa.bean.AjaxResult;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.ApproveFlow;
import com.ydt.oa.entity.Contract;
import com.ydt.oa.entity.Department;
import com.ydt.oa.entity.Material;
import com.ydt.oa.entity.PaymentOrder;
import com.ydt.oa.entity.Project;
import com.ydt.oa.entity.PurchaseApply;
import com.ydt.oa.entity.PurchaseApplyDetails;
import com.ydt.oa.entity.StoreRoom;
import com.ydt.oa.entity.StoreRoomLog;
import com.ydt.oa.entity.StoreRoomMaterial;
import com.ydt.oa.entity.SystemParam;
import com.ydt.oa.entity.User;
import com.ydt.oa.interfaces.ProjectAppInterface;
import com.ydt.oa.interfaces.ProjectAppInterface;
import com.ydt.oa.service.ApproveService;
import com.ydt.oa.service.PaymentOrderService;
import com.ydt.oa.service.ProjectService;
import com.ydt.oa.service.PurchaseApplyDetailsService;
import com.ydt.oa.service.PurchaseApplyService;
import com.ydt.oa.service.StoreRoomService;
import com.ydt.oa.service.UserService;


/**
 * chain 用来处理Action链
 * dispatcher 用来转向页面，通常处理JSP
 * freemaker 处理FreeMarker模板
 * httpheader 控制特殊HTTP行为的结果类型
 * redirect 重定向到一个URL
 * redirectAction 重定向到一个Action
 * stream 向浏览器发送InputSream对象，通常用来处理文件下载，还可用于返回AJAX数据
 * velocity 处理Velocity模板
 * xslt 处理XML/XLST模板
 * plainText 显示原始文件内容，例如文件源代码
 * redirect-action 重定向到一个Action
 * @author caochun
 *
 */

/**
 * 项目请求Action
 */

public class ProjectAction extends PageAction {
	private static final Logger logger = Logger.getLogger(ProjectAction.class);
	public boolean buttonFlag = false;

	@Autowired
	private ApproveService approveService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ProjectAppInterface projectAppService;
	@Autowired
	private UserService userService;
	@Autowired
	private PaymentOrderService paymentOrderService;
	@Autowired
	private PurchaseApplyService purchaseApplyService;
	@Autowired
	private PurchaseApplyDetailsService purchaseApplyDetailsService;
	@Autowired
	private StoreRoomService storeRoomService;
	


	private Pagination pagination;
	private Project project;
	private Application application;
	private ApproveFlow approveFlows;
	private String formAction;
	private AjaxResult ajaxResult;
	private List<User> users;
	private String keywords;
	private ApproveFlow approveFlow;
	private Department department;
	private PaymentOrder paymentOrder;
	private List<PaymentOrder> paymentOrderList;
	private List<PurchaseApplyDetails>  purchaseDetList;
	private PurchaseApplyDetails purchaseApplyDetails;
	private List<StoreRoomLog> storeRoomLogList;
	
	private BigDecimal totalPrice; //采购实际金额
	private BigDecimal inputTotalMoney; //入库实际金额
	private BigDecimal outputTotalMoney; //出库实际金额
	private BigDecimal stockTotalMoney; //出库实际金额
	
	
	
	
	
	
	public BigDecimal getStockTotalMoney() {
	
		return stockTotalMoney;
	}




	
	public void setStockTotalMoney(BigDecimal stockTotalMoney) {
	
		this.stockTotalMoney = stockTotalMoney;
	}




	
	public boolean isButtonFlag() {
	
		return buttonFlag;
	}





	public List<StoreRoomLog> getStoreRoomLogList() {
	
		return storeRoomLogList;
	}



	
	public void setStoreRoomLogList(List<StoreRoomLog> storeRoomLogList) {
	
		this.storeRoomLogList = storeRoomLogList;
	}



	
	public BigDecimal getInputTotalMoney() {
	
		return inputTotalMoney;
	}



	
	public void setInputTotalMoney(BigDecimal inputTotalMoney) {
	
		this.inputTotalMoney = inputTotalMoney;
	}



	public BigDecimal getTotalPrice() {
	
		return totalPrice;
	}


	
	public void setTotalPrice(BigDecimal totalPrice) {
	
		this.totalPrice = totalPrice;
	}


	public List<PurchaseApplyDetails> getPurchaseDetList() {
	
		return purchaseDetList;
	}

	
	public void setPurchaseDetList(List<PurchaseApplyDetails> purchaseDetList) {
	
		this.purchaseDetList = purchaseDetList;
	}

	
	public PurchaseApplyDetails getPurchaseApplyDetails() {
	
		return purchaseApplyDetails;
	}

	
	public void setPurchaseApplyDetails(PurchaseApplyDetails purchaseApplyDetails) {
	
		this.purchaseApplyDetails = purchaseApplyDetails;
	}

	public List<PaymentOrder> getPaymentOrderList() {
		return paymentOrderList;
	}

	public void setPaymentOrderList(List<PaymentOrder> paymentOrderList) {
		this.paymentOrderList = paymentOrderList;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public PaymentOrder getPaymentOrder() {
		return paymentOrder;
	}

	public void setPaymentOrder(PaymentOrder paymentOrder) {
		this.paymentOrder = paymentOrder;
	}

	public ApproveFlow getApproveFlow() {
		return approveFlow;
	}

	public void setApproveFlow(ApproveFlow approveFlow) {
		this.approveFlow = approveFlow;
	}
//	private User user;
	


//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public ApproveFlow getApproveFlows() {
		return approveFlows;
	}

	public void setApproveFlows(ApproveFlow approveFlows) {
		this.approveFlows = approveFlows;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public Pagination getPagination() {

		return pagination;
	}

	public String getFormAction() {
		return formAction;
	}

	public void setFormAction(String formAction) {
		this.formAction = formAction;
	}

	public AjaxResult getAjaxResult() {
		return ajaxResult;
	}

	public void setAjaxResult(AjaxResult ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

	/**
	 * 项目列表
	 * 
	 * @return
	 */
	@Action(value = "/project/projectlist", 
			results = { @Result(name = "success", location = "/project/projectlist.jsp", type = "dispatcher") }
		)
	public String list() {

		try{
			if(project != null){
				pagination = projectService.findProject(project,getPageNum(), getNumPerPage());
			}else{
				pagination = projectService.list(getPageNum(), getNumPerPage());
			}	
		}catch(Exception e){
			e.printStackTrace();
		}

		return "success";

	}

	/**
	 * 编辑项目
	 * 
	 * @return
	 */
	@Action(value = "/approve/editprojectapp", results = {
			@Result(name = "success", location = "/project/editprojectapp.jsp", type = "dispatcher"),
			@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher") })
	public String editProjectApp() {
		
		//System.out.println("editProjectApp^^^^^^^^^^^^^^^^^^666");

		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				// user=userService.findByUser(user.getId());
				// application=approveService.findById(application.getId());
				// project.setManager(user.getRealName());
				projectAppService.updateProjectApp(application, project, getLoginUser());
				ajaxResult.setNavTabId("main");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("数据保存失败");
			}
			return "save";
		} else if (application != null && application.getId() != null) {
			application = approveService.findById(application.getId());
			project = projectService.findById(application.getApplyNo());
			// logger.info(project.getName());
		} else if (project != null && project.getId() != null) {
			project = projectService.findById(project.getId());
			// user=userService.findByName(project.getManager());
		}
		return "success";
}
	



	/**
	 * 审批  当已经开始审批的时候则不能修改
	 * 
	 * @return
	 */
	@Action(value = "/approve/approveprojectapp", results = {
			@Result(name = "success", location = "/approve/approveprojectapp.jsp", type = "dispatcher"),
			@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher") })
	public String approveProjectApp() {
		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				approveService.approve(application, approveFlow ,getLoginUser());
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
		}else if (formAction != null && formAction.equals("end")) {
			ajaxResult = new AjaxResult();
			try {
				approveService
						.approve(application, approveFlow, getLoginUser(),true);
				
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
				project = projectService.findById(application.getApplyNo());
//				user = userService.findByName(project.getManager());
			}
			return "success";
		}
	}
   
	/**
	 * 项目审批意见
	 * 
	 * @return
	 */
	@Action(value = "/approve/viewprojectapp",
			results = {
						@Result(name = "success", location = "/approve/viewprojectapp.jsp", type = "dispatcher")
					}
			)
			public String viewProjectApp() {


			if (application != null && application.getId() != null) {
				application = approveService.findById(application.getId());
				project = projectService.findById(application.getApplyNo());
			}
			return "success";
	}
	/**
	 * 查找
	 * @return
	 */
	@Action(value = "/common/userlookup",
			results = {
						@Result(name = "result", location = "/tools/common/userlookup.jsp", type = "dispatcher")
					}
	
			)		
	public String userLookup() {
		
		pagination = userService.list(this.getPageNum(), this.getNumPerPage());
		//System.out.println(pagination.getResult().toString());
		return "result";
	}
	
	/**
	 * 提示
	 * @return
	 */
	@Action(value = "/common/userlookupsuggest",
			results = {
						@Result(name = "result", location = "/tools/common/userlookupsuggest.jsp", type = "dispatcher")
					}
			)			
	public String userLookupSuggest() {
		users = userService.suggest("realName",keywords);
		
		return "result";
		}
	
	/**
	 * 项目报表列表
	 * 
	 * @return
	 */
	@Action(value = "/project/projectreportlist", 
			results = { @Result(name = "success", location = "/project/projectreportlist.jsp", type = "dispatcher") }
		)
	public String reportList() {

		try{
		pagination = projectService.list(getPageNum(), getNumPerPage());
		
		}catch(Exception e){
			e.printStackTrace();
		}

		return "success";

	}
	/**
	 * 列出项目并点击后显示基本信息
	 * @return
	 */
	@Action(value = "/project/projectinformationlist",
			results = { @Result(name = "success", location = "/project/projectinformationlist.jsp", type = "dispatcher")}
			)
	public String projectinformationlist() {
		project = projectService.findById(project.getId());
		return "success";
	}
	
	/**
	 * 显示项目的基本信息
	 * 
	 * @return
	 */
	@Action(value = "/project/basicinformation",
			results = {@Result(name = "success", location = "/project/basicinformation.jsp", type = "dispatcher")
					}
			)
			public String basicinformation() {
		project = projectService.findById(project.getId());
			return "success";
	}
	
	/**
	 * 显示项目的支出信息
	 * 
	 * @return
	 */
	@Action(value = "/project/projectexpenditures", 
			results = { @Result(name = "success", location = "/project/projectexpenditures.jsp", type = "dispatcher") })
	public String projectexpenditures() {
		project = projectService.findById(project.getId());
		department = project.getDepartment();
		List<Application> applist = approveService.findByApplyIdDep(department.getId());
		paymentOrderList = new ArrayList<PaymentOrder>();
		for (int i = 0; i < applist.size(); i++) {
			application = (Application) applist.get(i);
			paymentOrder = paymentOrderService.findByApplyId(applist.get(i).getId());
			if (paymentOrder.getStatus() != -1 && paymentOrder.getStatus() != 0) {
				paymentOrder = paymentOrderService.listProjectPay(paymentOrder);
				paymentOrderList.add(paymentOrder);
			}
		}
		return "success";

	}
	
	/**
	 * 显示项目的待付款信息
	 * 
	 * @return
	 */
	@Action(value = "/project/projecttobepaid", 
			results = { @Result(name = "success", location = "/project/projecttobepaid.jsp", type = "dispatcher") })
	public String projecttobepaid() {
		project = projectService.findById(project.getId());
		department = project.getDepartment();
		List<Application> applist = approveService.findByApplyIdDep(department.getId());
		paymentOrderList = new ArrayList<PaymentOrder>();
		for (int i = 0; i < applist.size(); i++) {
			application = (Application) applist.get(i);
			paymentOrder = paymentOrderService.findByApplyId(applist.get(i).getId());
			if (paymentOrder.getStatus() != 1 && paymentOrder.getStatus() != 2) {
				paymentOrder = paymentOrderService.listProjectPay(paymentOrder);
				paymentOrderList.add(paymentOrder);
			}
		}
		return "success";

	}
	
	
	/**
	 * 显示项目的库房信息
	 * 
	 * @return
	 */
	@Action(value = "/project/projectstoreroom",
			results = { @Result(name = "success", location = "/project/projectstoreroom.jsp", type = "dispatcher") })
	public String projectstoreroom() {

		project = projectService.findById(project.getId());
		List<PurchaseApply> purchaseApplyList = purchaseApplyService.findByProId(project.getId());
		totalPrice = new BigDecimal(0); // 采购实际金额
		inputTotalMoney = new BigDecimal(0); // 入库实际金额
		outputTotalMoney = new BigDecimal(0); // 出库实际金额
		stockTotalMoney = new BigDecimal(0); // 出库实际金额
		ajaxResult = new AjaxResult();
		try {
			for (int i = 0; i < purchaseApplyList.size(); i++) {
				purchaseDetList = purchaseApplyDetailsService.findDetailsByAppId(purchaseApplyList.get(i).getId());
				storeRoomLogList = storeRoomService.findStoreRoomByAppId(purchaseApplyList.get(i).getId());
				for (int j = 0; j < purchaseDetList.size(); j++) {
					BigDecimal price = new BigDecimal(purchaseDetList.get(j).getActualPrice());
					BigDecimal totalNumber = new BigDecimal(0);
					for (int z = 0; z < storeRoomLogList.size(); z++) {
						BigDecimal inputNumber = new BigDecimal(storeRoomLogList.get(z).getNumber());
						totalNumber = totalNumber.add(inputNumber);
					}
					BigDecimal inputPartMoney = new BigDecimal(0);
					inputPartMoney = price.multiply(totalNumber);
					inputTotalMoney = inputTotalMoney.add(inputPartMoney);
					BigDecimal number = new BigDecimal(purchaseDetList.get(j).getNumber());
					BigDecimal partPrice = new BigDecimal(0);
					partPrice = price.multiply(number);
					totalPrice = totalPrice.add(partPrice);
				}
			}
			//System.out.println(inputTotalMoney + "inputTotalMoney ----------------");
			//System.out.println("totalPrice--------" + totalPrice);
			StoreRoom storeRoom = storeRoomService.findStoreRoomByProject(project);
			User user = storeRoom.getUser();
			List<StoreRoomLog> outputstoreRoomLog = storeRoomService.findStoreRoomLogByUser(user.getId());
			for (int x = 0; x < outputstoreRoomLog.size(); x++) {
				BigDecimal outputNumber = new BigDecimal(outputstoreRoomLog.get(x).getNumber());
				Material material = outputstoreRoomLog.get(x).getMaterial();
				BigDecimal outputPrice = new BigDecimal(material.getPrice());
				BigDecimal outputPartMoney = new BigDecimal(0);
				outputPartMoney = outputPrice.multiply(outputNumber);
				outputTotalMoney = outputTotalMoney.add(outputPartMoney);
			}
			List<StoreRoomMaterial> StoreRoomMaterialList = storeRoomService.findStoreRoomMaterialById(storeRoom.getId());
			// System.out.println( StoreRoomMaterialList.size()+"@@@@@@@@@@");
			for (int y = 0; y < StoreRoomMaterialList.size(); y++) {
				BigDecimal stockNumber = new BigDecimal(StoreRoomMaterialList.get(y).getNumber());
				Material material = outputstoreRoomLog.get(y).getMaterial();
				BigDecimal stockPrice = new BigDecimal(material.getPrice());
				BigDecimal stockPartMoney = new BigDecimal(0);
				stockPartMoney = stockPrice.multiply(stockNumber);
				stockTotalMoney = stockTotalMoney.add(stockPartMoney);
			}
			ajaxResult.setNavTabId("main");
			ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
			ajaxResult.setMessage("数据保存失败");
		}
		return "success";
	}




	
	public BigDecimal getOutputTotalMoney() {
	
		return outputTotalMoney;
	}




	
	public void setOutputTotalMoney(BigDecimal outputTotalMoney) {
	
		this.outputTotalMoney = outputTotalMoney;
	}
}
