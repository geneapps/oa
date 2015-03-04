package com.ydt.oa.action;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.giro.common.action.PageAction;
import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.ydt.oa.bean.AjaxResult;
import com.ydt.oa.dao.StoreRoomDao;
import com.ydt.oa.entity.Department;
import com.ydt.oa.entity.Material;
import com.ydt.oa.entity.Project;
import com.ydt.oa.entity.PurchaseApply;
import com.ydt.oa.entity.PurchaseApplyDetails;
import com.ydt.oa.entity.StoreRoom;
import com.ydt.oa.entity.StoreRoomLog;
import com.ydt.oa.entity.StoreRoomMaterial;
import com.ydt.oa.entity.User;
import com.ydt.oa.service.ProjectService;
import com.ydt.oa.service.PurchaseApplyDetailsService;
import com.ydt.oa.service.PurchaseApplyService;
import com.ydt.oa.service.StoreRoomService;
import com.ydt.oa.service.UserService;




/**
 * 审批管理Action
 * 
 * @author caochun
 * 
 */
public class StoreRoomAction extends PageAction {

	@Autowired
	private StoreRoomService storeRoomService;
	@Autowired
	private UserService userService;
	@Autowired
	PurchaseApplyService purchaseApplyService;
	@Autowired
	PurchaseApplyDetailsService purchaseApplyDetailsService;
	@Autowired
	private StoreRoomDao  storeRoomDao;
	@Autowired
	private ProjectService projectService;
	private Pagination pagination;
	private String formAction;
	private AjaxResult ajaxResult;
    private StoreRoom storeRoom;
    private List<StoreRoom> list;
    private List<StoreRoom> listall;
    
	

	private List<StoreRoomMaterial> storeRoomMaterialList;
	private List<Long> outNumbers;
    private List<Material> materialList;
    private PurchaseApply purchaseApply;
    List<PurchaseApplyDetails> purchaseApplyDetails;
    private StoreRoomLog  storeRoomLog;
    public String name;
    public User user;
    private PurchaseApplyDetails purApplyDetails;
    private String logTime;


    
    
	
	public String getLogTime() {
	
		return logTime;
	}





	
	public void setLogTime(String logTime) {
	
		this.logTime = logTime;
	}





	public List<Long> getOutNumbers() {
	
		return outNumbers;
	}




	
	public void setOutNumbers(List<Long> outNumbers) {
	
		this.outNumbers = outNumbers;
	}




	public List<StoreRoom> getListall() {
    	
		return listall;
	}



	
	public void setListall(List<StoreRoom> listall) {
	
		this.listall = listall;
	}
	
	public PurchaseApplyDetails getPurApplyDetails() {
	
		return purApplyDetails;
	}


	
	public void setPuraApplyDetails(PurchaseApplyDetails purApplyDetails) {
	
		this.purApplyDetails = purApplyDetails;
	}


	public User getUser() {
	
		return user;
	}

	
	public void setUser(User user) {
	
		this.user = user;
	}

	public String getName() {
	
		return name;
	}
	
	public void setName(String name) {
	
		this.name = name;
	}
	public List<StoreRoomMaterial> getStoreRoomMaterialList() {
		return storeRoomMaterialList;
	}
	public StoreRoomLog getStoreRoomLog() {
	
		return storeRoomLog;
	}





	
	public void setStoreRoomLog(StoreRoomLog storeRoomLog) {
	
		this.storeRoomLog = storeRoomLog;
	}





	public void setStoreRoomMaterialList(List<StoreRoomMaterial> storeRoomMaterialList) {
	
		this.storeRoomMaterialList = storeRoomMaterialList;
	}




	
	public List<Material> getMaterialList() {
	
		return materialList;
	}




	
	public void setMaterialList(List<Material> materialList) {
	
		this.materialList = materialList;
	}




	public List<PurchaseApplyDetails> getPurchaseApplyDetails() {
	
		return purchaseApplyDetails;
	}



	
	public void setPurchaseApplyDetails(List<PurchaseApplyDetails> purchaseApplyDetails) {
	
		this.purchaseApplyDetails = purchaseApplyDetails;
	}



	public PurchaseApply getPurchaseApply() {
	
		return purchaseApply;
	}


	
	public void setPurchaseApply(PurchaseApply purchaseApply) {
	
		this.purchaseApply = purchaseApply;
	}


	public List<StoreRoom> getList() {
		return list;
	}


	public void setList(List<StoreRoom> list) {
		this.list = list;
	}


	public StoreRoom getStoreRoom() {
		return storeRoom;
	}


	public void setStoreRoom(StoreRoom storeRoom) {
		this.storeRoom = storeRoom;
	}


	public String getFormAction() {
		return formAction;
	}


	public Pagination getPagination() {

		return pagination;
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
	/**
	 * 编辑库房
	 * 
	 * @return
	 */
	@Action(value = "/storeroom/editstoreroom", results = {
			@Result(name = "success", location = "/inventoryManagement/editstoreroom.jsp", type = "dispatcher"),
			@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher") })
	public String editStoreroom() {
		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {

				storeRoomService.saveStoreRoom(storeRoom);
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
		   if(storeRoom!=null&&storeRoom.getId()!=null) {
			   storeRoom=storeRoomDao.findById(storeRoom.getId());
			   return "success";
		   } else {
			   return "success";
		   }
			
		}
	}
	/**
	 * 删除库房
	 * 
	 * @return
	 */
	@Action(value = "/storeroom/delstoreroom", results = {
			@Result(name = "success", location = "/tools/common/ajaxresult.jsp", type = "dispatcher") })
	public String delStoreroom() {
		
			ajaxResult = new AjaxResult();
				if(storeRoom!=null&&storeRoom.getId()!=null) {
					storeRoomService.deleteStoreRoom(storeRoom);
					ajaxResult.setNavTabId("main");
					// ajaxResult.setRel("approveRel");
					ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
				}
			return "success";
		
	}
	/**
	 * 查看库房
	 * 
	 * @return
	 */
	@Action(value = "/storeroom/list", results = {
			@Result(name = "success", location = "/storeroom/materialout.jsp", type = "dispatcher") })
	public String viewStoreroom() {		
		User user = getLoginUser();
		Project project = projectService.findByDepart( user.getDepartment().getParent().getId() );
		list=storeRoomService.list(project);
		storeRoom = storeRoomService.findStoreRoomByProject(project);
		//storeRoomMaterialList =storeRoomService.list(storeRoom.getId());
		return "success";
	}
	
	/**
	 * 库房材料查找带回
	 * @return
	 */
	@Action(value = "/storeroom/storeRoomMateriallookup",
			results = {
						@Result(name = "result", location = "/tools/common/storeRoomMateriallookup.jsp", type = "dispatcher")
					}
			)			
	public String findStoreRoomMaterial() {
		User user = getLoginUser();
		Project project = projectService.findByDepart( user.getDepartment().getParent().getId() );
		list=storeRoomService.list(project);
		storeRoom = storeRoomService.findStoreRoomByProject(project);
		storeRoomMaterialList =storeRoomService.list(storeRoom.getId());
		pagination = storeRoomService.listStoreRoomMaterialByProject(storeRoom,getPageNum(), getNumPerPage());
		return "result";
	}
	
	/**
	 * 入库
	 * 
	 * @return
	 */
	@Action(value = "/storeroom/pruchaseincoming",
			results = {
						@Result(name = "success", location = "/storeroom/purchaseincoming.jsp", type = "dispatcher"),
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
	public String purchaseIncoming() {

		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				purchaseApply = purchaseApplyService.findById(purchaseApply.getId());
				//System.out.println(purchaseApply.getStatus()+"!!!!!!!!"+ purchaseApply.getTitle());
				storeRoomService.purchaseIncoming(purchaseApply, purchaseApplyDetails);
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
			if (purchaseApply != null && purchaseApply.getId() != null) {
				purchaseApply = purchaseApplyService.findById(purchaseApply.getId());
			}
			return "success";
		}
	}
   
	
	
	/**
	 * 出库时把材料都弄出来
	 * 
	 * @return
	 */
	@Action(value = "/storeroom/materialout",
			results = {
						@Result(name = "success", location = "/storeroom/materialout.jsp", type = "dispatcher")
					}
			)
	public String materialOut() {

				if(storeRoom != null && storeRoom.getId() != null) {
			
				storeRoom = storeRoomDao.findById(storeRoom.getId());
				storeRoomMaterialList =storeRoomService.list(storeRoom.getId());
				//materialList=storeRoomService.materiallist(storeRoom.getId());
			}
			return "success";
		}
	
	@Action(value = "/storeroom/outmateriallist",
			results = {
			@Result(name = "success", location = "/storeroom/outmateriallist.jsp", type = "dispatcher")
	}
	)
	public String outmateriallist() {
		
		pagination = storeRoomService.listStoreRoomLogoutbyTime(logTime,getPageNum(), getNumPerPage());
		return "success";
	}
	
	
	/**
	 * 库房库存
	 * 
	 * @return
	 */
	@Action(value = "/storeroom/stocklist",
			results = {
						@Result(name = "success", location = "/storeroom/stocklist.jsp", type = "dispatcher")
					}
			)
	public String stocklist() {
				pagination =storeRoomService.listStoreRoomMaterial(getPageNum(), getNumPerPage());
			return "success";
		}
	
	/**
	 * 库房入库
	 * 
	 * @return
	 */
	@Action(value = "/storeroom/storeroomincomelist",
			results = {
						@Result(name = "success", location = "/storeroom/storeroomincomelist.jsp", type = "dispatcher")
					}
			)
	public String storeroomincomelist() {
		  pagination =storeRoomService.listStoreRoomLog(getPageNum(), getNumPerPage());
		 // System.out.println(pagination.getTotalCount()+"***");
		 return "success";
		}
	/**
	 * 库房入库时查看单个采购单明细
	 * 
	 * @return
	 */
	@Action(value = "/storeroom/materiallist",
			results = {
						@Result(name = "success", location = "/storeroom/materiallist.jsp", type = "dispatcher")
					}
			)
	public String materiallist() {
		  purchaseApply=purchaseApplyService.findById(purchaseApply.getId());
				
		  return "success";
		}
	/**
	 * 库房出库列表
	 * 
	 * @return
	 */
	@Action(value = "/storeroom/storeroomoutlist",
			results = {
						@Result(name = "success", location = "/storeroom/storeroomoutlist.jsp", type = "dispatcher")
					}
			)
	public String storeroomoutlist() {
				pagination =storeRoomService.listStoreRoomLogout(getPageNum(), getNumPerPage());
				
			    return "success";
		}
	
	/**
	 * 员工领用材料情况
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@Action(value = "/storeroom/materiallistByUser",
			results = {
						@Result(name = "success", location = "/storeroom/materiallistByUser.jsp", type = "dispatcher")
					}
			)
	public String materiallistByUser() throws UnsupportedEncodingException {
		
		  user=userService.findByUserName(user.getRealName());
		  pagination=storeRoomService.listStoreRoomLogoutByUser(user, getPageNum(), getNumPerPage());
		  
		  return "success";
		}
	
	/**
	 * 材料转库出库
	 * 
	 * @return
	 */
	@Action(value = "/storeroom/turnstoreroom",
			results = {
						@Result(name = "success", location = "/storeroom/turnstoreroom.jsp", type = "dispatcher")
					}
			)
	public String turnstoreroom() {
			//if (storeRoom != null && storeRoom.getId() != null) {
//				storeRoom = storeRoomDao.findById(storeRoom.getId());
//				list=storeRoomDao.listName(storeRoom.getId());
//				
//				storeRoomMaterialList =storeRoomService.list(storeRoom.getId());
				//materialList=storeRoomService.materiallist(storeRoom.getId());
			//}
				
		User user = getLoginUser();
		Project project = projectService.findByDepart(user.getDepartment().getParent().getId());
		list = storeRoomService.list(project);
		storeRoom = storeRoomService.findStoreRoomByProject(project);
		storeRoomMaterialList = storeRoomService.list(storeRoom.getId());
		
		listall = storeRoomService.listAllStoreroom();
		
		
		return "success";
		}
	/**
	 * 材料转库出库
	 * 
	 * @return
	 */
	@Action(value = "/storeroom/turningstoreroom",
			results = {
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
	public String turningstoreroom() {
		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				storeRoomService.turningstoreroom(storeRoom,name,storeRoomMaterialList,outNumbers);
				ajaxResult.setNavTabId("main");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("数据保存失败");
			}
			return "save";
		} 
		return "null";
		}
	

	/**
	 * 出库
	 * 
	 * @return
	 */
	@Action(value = "/storeroom/materialouting",
			results = {
						
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher")
					}
			)
	public String materialouting() {

		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				storeRoomService.materialouting(storeRoomLog,storeRoomMaterialList,outNumbers);
				ajaxResult.setNavTabId("main");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
			}catch (GiroException e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("数据保存失败");
			}
			return "save";
		} 
		return "null";
	}

	/**
	 * 显示可以入库的采购单
	 * @return
	 */
	@Action(value = "/storeroom/purchaselist", 
			results = { @Result(name = "success",location = "/storeroom/purchaselist.jsp", type = "dispatcher") 
			}
	)
	public String purchaseList() {
		Department department = getLoginUser().getDepartment().getParent();
		Project pro = projectService.findByDepart(department.getId());
		ajaxResult = new AjaxResult();
		try {
			pagination = purchaseApplyService.listStartBuyPurchaseApply(pro.getId(),getPageNum(), getNumPerPage());
			ajaxResult.setNavTabId("main");
			ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
			ajaxResult.setMessage("不具有操作权限！");
			
		}	
		return "success";
	}
	
}
