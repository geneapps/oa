package com.ydt.oa.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.giro.common.action.PageAction;
import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.giro.common.util.StringUtils;
import com.ydt.oa.bean.AjaxResult;
import com.ydt.oa.entity.Supplier;
import com.ydt.oa.service.SupplierService;

/**
 * 供货商Action
 * @author Cruise
 *
 */
public class SupplierAction extends PageAction {
	
	@Autowired
	private SupplierService supplierService;
	private Pagination pagination;
	private String formAction;
	private AjaxResult ajaxResult;
	private Supplier supplier;
	
	/**
	 * 显示供货商列表
	 * @return 
	 */
	@Action(value = "/supplier/supplierList",
			results = {
						@Result(name = "success", location = "/supplier/list.jsp", type = "dispatcher")
					}
			)
	public String listSupplier() {
				
		pagination =  supplierService.list(getPageNum(), getNumPerPage());
		return "success";
	}
	
//	/**
//	 * 显示供货商对应产品
//	 * @return 
//	 */
//	@Action(value = "/supplier/viewMaterial",
//			results = {
//			@Result(name = "success", location = "/supplier/materialList.jsp", type = "dispatcher")
//	}
//	)
//	public String listMaterial() {
//		
//		if(supplier != null && (!StringUtils.isNull(supplier.getId()) )){
//			
//			pagination =  supplierService.findMaterialBySupplier(supplier.getId(), getPageNum(), getNumPerPage());
//			System.out.println("--------------" + pagination.getResult().size() + "||||||" + pagination.getResult().get(0));
//		}
//		return "success";
//	}

	/**
	 * 编辑供货商
	 * @return
	 */
	@Action(value = "/supplier/editSupplier",
			results = {
						@Result(name = "success", location = "/supplier/editSupplier.jsp", type = "dispatcher"),
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
	public String edit() {
		
		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			
			try {
				supplierService.updateUser(supplier);
				//System.out.println(supplier.getPhone() + "-------------");
				ajaxResult.setNavTabId("main");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
				
			} catch (Exception e) {
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("数据保存失败");		
			}
			
			return "save";
			
		} else {
			if (supplier != null && supplier.getId() != null) {
				supplier = supplierService.findBySupplierId(supplier.getId());
			}
			return "success";
		}
	}
	public Pagination getPagination() {
	
		return pagination;
	}

	
	public void setPagination(Pagination pagination) {
	
		this.pagination = pagination;
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

	
	public Supplier getSupplier() {
	
		return supplier;
	}

	
	public void setSupplier(Supplier supplier) {
	
		this.supplier = supplier;
	}
	
	

	
}
