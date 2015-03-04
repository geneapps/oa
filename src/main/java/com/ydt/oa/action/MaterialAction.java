package com.ydt.oa.action;


import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.giro.common.action.PageAction;
import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.giro.common.util.StringUtils;
import com.ydt.oa.bean.AjaxResult;
import com.ydt.oa.entity.Material;
import com.ydt.oa.entity.Supplier;
import com.ydt.oa.service.MaterialService;
import com.ydt.oa.service.SupplierService;

/**
 * 材料管理的action
 * @author caochun
 *
 */
public class MaterialAction extends PageAction {
	

	@Autowired
	private MaterialService materialService;
	@Autowired
	private SupplierService supplierService;
	private Pagination pagination;
	private List<Material> materialList;

	private String formAction;
	private AjaxResult ajaxResult;
	
	private Material material;

	private String keywords;
	private Supplier supplier;
	


	/**
	 * 材料查找
	 * @return
	 */
	@Action(value = "/common/materiallookup",
			results = {
						@Result(name = "result", location = "/tools/common/materiallookup.jsp", type = "dispatcher")
					}
			)			
	public String materialLookup() {
		
		if(material!=null) {
			pagination = materialService.lookupMaterial(material, getPageNum(), getNumPerPage());
		}else{
			pagination = materialService.list(getPageNum(), getNumPerPage());
		}
		
		return "result";
	}
	/**
	 * 材料查找
	 * @return
	 */
	@Action(value = "/material/materiallookup",
			results = {
						@Result(name = "result", location = "/system/materiallist.jsp", type = "dispatcher")
					}
			)			
	public String findMaterial() {
		
		if(material!=null) {
			pagination = materialService.findMaterial(material, getPageNum(), getNumPerPage());
		}else{
			pagination = materialService.list(getPageNum(), getNumPerPage());
		}
		
		return "result";
	}
	/**
	 * 材料提示
	 * @return
	 */
	@Action(value = "/common/materiallookupsuggest",
			results = {
						@Result(name = "result", location = "/tools/common/materiallookupsuggest.jsp", type = "dispatcher")
					}
			)			
	public String materialLookupSuggest() {
		materialList = materialService.suggest("materialName",keywords);
		return "result";
	}
	/**
	 * 编辑材料
	 */
	@Action(value = "/material/edit", results = {
			@Result(name = "success", location = "/system/editmaterial.jsp", type = "dispatcher"),
			@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher") })
	public String editMaterial() throws GiroException {
		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
              // System.out.println(material.getSupplier().getSupplyName()+"***");
				materialService.updateMaterial(material);
				ajaxResult.setNavTabId("main");
				// ajaxResult.setRel("approveRel");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
				// ajaxResult.setForwardUrl("/bolan/user/list.action?pageNum="+this.getPageNum()+"&numPerPage="+this.getNumPerPage());
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("数据保存失败");
			}
			return "save";
		} else {
			   if(material!=null&&material.getId()!=null) {
				   material=materialService.findMaterialById(material);
			}
			return "success";
		}
	}
	
	/**
	 * 删除材料
	 * 
	 * @return
	 */
	@Action(value = "/material/delmaterial", results = {
			@Result(name = "success", location = "/tools/common/ajaxresult.jsp", type = "dispatcher") })
	public String delmaterial() {
		
			ajaxResult = new AjaxResult();
				if(material!=null&&material.getId()!=null) {
					materialService.del(material);
					ajaxResult.setNavTabId("main");
					
					ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
				}
			return "success";
		
	}
	/**
	 * 材料列表
	 * @return
	 */
	@Action(value = "/material/list",
			results = { @Result(name = "success", location = "/system/materiallist.jsp", type = "dispatcher")}
			)
	public String list() {
//		ajaxResult = new AjaxResult();
		try{
			pagination = materialService.list(this.getPageNum(), this.getNumPerPage());
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	
		return "success";
	}
	
	/**
	 * 一般员工材料维护列表
	 * @return
	 */
	@Action(value = "/material/commonlist",
			results = { @Result(name = "success", location = "/system/commonmateriallist.jsp", type = "dispatcher")}
			)
	public String commonList() {
//		ajaxResult = new AjaxResult();
		try{
			pagination = materialService.list(this.getPageNum(), this.getNumPerPage());
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	
		return "success";
	}
	
	/**
	 * 显示供货商对应产品
	 * @return 
	 */
	@Action(value = "/supplier/viewMaterial",
			results = {
			@Result(name = "success", location = "/supplier/materialList.jsp", type = "dispatcher")
	}
	)
	public String listMaterial() {
		
		if(supplier != null && (!StringUtils.isNull(supplier.getId()) )){
			System.out.println(supplier.getId());
			pagination =  supplierService.findMaterialBySupplier(supplier.getId(), getPageNum(), getNumPerPage());
			//System.out.println("------material-----" + pagination.getResult().size() + "||||||" + pagination.getResult().get(0) );
		}
		return "success";
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

	public Pagination getPagination() {

		return pagination;
	}

	public void setPagination(Pagination pagination) {

		this.pagination = pagination;
	}

	
	public Material getMaterial() {
	
		return material;
	}

	
	public void setMaterial(Material material) {
	
		this.material = material;
	}


	public String getKeywords() {
	
		return keywords;
	}

	
	public void setKeywords(String keywords) {
	
		this.keywords = keywords;
	}

	
	public List<Material> getMaterialList() {
	
		return materialList;
	}
	
	public Supplier getSupplier() {
	
		return supplier;
	}
	
	public void setSupplier(Supplier supplier) {
	
		this.supplier = supplier;
	}
	
	
	
}

