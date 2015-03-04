package com.ydt.oa.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.giro.common.action.PageAction;
import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.giro.common.util.StringUtils;
import com.ydt.oa.bean.AjaxResult;
import com.ydt.oa.entity.Department;
import com.ydt.oa.entity.OaAction;
import com.ydt.oa.entity.Role;
import com.ydt.oa.entity.User;
import com.ydt.oa.service.OrganizationService;
import com.ydt.oa.service.RoleManageService;
import com.ydt.oa.service.UserService;

/**
 * 项目请求Action
 */
public class OrganizationAction extends PageAction {
	private static final Logger logger = Logger.getLogger(OrganizationAction.class);

	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleManageService roleManageService;

	

	private Department department;
	private List<Department> departments;
	private Role role;
	private User user;
	private Pagination pagination;
	private String formAction;
	private AjaxResult ajaxResult;
    private List<Role> roles;
    private List<User> users;
    private String[] ids;
//    private OaAction[] oaActions;
	private String[] roleActionIds;
    private List<OaAction> oaActionList;
    private Role roleMap;
    private Department departmentMap;
    
    
	

	



	
	
	public String[] getRoleActionIds() {
	
		return roleActionIds;
	}



	
	public void setRoleActionIds(String[] roleActionIds) {
	
		this.roleActionIds = roleActionIds;
	}



	public List<OaAction> getOaActionList() {
	
		return oaActionList;
	}


	
	public void setOaActionList(List<OaAction> oaActionList) {
	
		this.oaActionList = oaActionList;
	}


	public String[] getIds() {
		return ids;
	}


	public void setIds(String[] ids) {
		this.ids = ids;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}


	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public List<Department> getDepartments() {
	
		return departments;
	}

	
	public void setDepartments(List<Department> departments) {
	
		this.departments = departments;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	/**
	 * 部门列表
	 * 
	 * @return
	 */
	@Action(value = "/organization/organizationlist", results = { @Result(name = "success", location = "/organization/organizationlist.jsp", type = "dispatcher") })
	public String departmentlist() {
		departments = organizationService.departmentlist();
		return "success";
	}
	
	
	/**
	 * 部门岗位列表
	 * @return
	 */
	@Action(value = "/organization/departmentrolelist",
			results = { @Result(name = "success", location = "/organization/departmentrolelist.jsp", type = "dispatcher")}
			)
	public String departmentRoleList() {
		if(department!=null && department.getId()!=null){
			department= organizationService.findDepartmentById(department.getId());
			/*roles = organizationService.rolelist(department);
			//System.out.println(roles.size()+"*****");
*/		}
	
		return "success";
	}
	
	
	
	/**
	 * 部门员工列表
	 * @return
	 */
	@Action(value = "/organization/departmentuserlist",
			results = { @Result(name = "success", location = "/organization/departmentuserlist.jsp", type = "dispatcher")}
			)
	public String departmentUserList() {
		if(department.getId()!=null&&role.getId()!=null){
		pagination = userService.departmentUser(department.getId(),role.getId(), this.getPageNum(), this.getNumPerPage());
		}
	
		return "success";
	}
		
	
	/**
	 * 查找部门
	 * @return
	 */
	@Action(value = "/organization/departmentlookup",
			results = { @Result(name = "success", location = "/organization/departmentlookup.jsp", type = "dispatcher")}
			)
	public String lookup() {
		
		departments = organizationService.departmentlist();
		return "success";
	}

	/**
	 * 创建、编辑部门
	 * 
	 * @return
	 */
	@Action(value = "/organization/editdepartment", results = {
			@Result(name = "success", location = "/organization/editdepartment.jsp", type = "dispatcher"),
			@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher") })
	public String editDepartment() {
	//	System.out.println("departMentId="+department.getId());

		//System.out.println("editDepartment^^^^^^^^^^^^^^^^^^666");

		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				organizationService.upDepartment(department, getLoginUser());
				ajaxResult.setNavTabId("main");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("数据保存失败");
			}
			return "save";
		} else if (department != null && department.getId() != null) {
			department = organizationService.findDepartmentById(department.getId());
		}else if (department != null && department.getParent().getId() != null) {
			Department parent = organizationService.findDepartmentById(department.getParent().getId());
			if(department==null){
				department = new Department();
			}
			department.setParent(parent);
		}
		return "success";
	}
	
	/**
	 * 删除部门
	 * 
	 * @return
	 */

	@Action(value = "/organization/deldepartment", results = {

	@Result(name = "success", location = "/tools/common/ajaxresult.jsp", type = "dispatcher") })
	public String deldepartment() {
		ajaxResult = new AjaxResult();
		if (department != null && department.getId() != null) {
			try {
				organizationService.deleteDepartment(department,
						this.getLoginUser());
				ajaxResult.setNavTabId("main");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_FORWARD);
			} catch (GiroException e) {
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("删除失败");
			}
		}
		return "success";
	}
	
	/**
	 * 创建、编辑岗位
	 * 
	 * @return
	 */
	@Action(value = "/organization/editrole", results = {
			@Result(name = "success", location = "/organization/editrole.jsp", type = "dispatcher"),
			@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher") })
	public String editRole() {
		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
//				for(int i=0;i<actionIds.length;i++) {
//					System.out.println(actionIds[i]+"XXXXXXXXXXX");
//				}
				//System.out.println(roleAction.size()+"#########");
//				department=organizationService.findDepartmentById(department.getId());
//				role.setDepartment(department);
				
				Set<OaAction> actionSet = new HashSet<OaAction>();
				OaAction action = null;
				if(roleActionIds!=null){
					for(String id:roleActionIds){
					action = new OaAction();
					action.setId(id);
					actionSet.add(action);
					}
				}				
				role.setActions(actionSet);
				organizationService.upRole(role);
				this.departmentRoleList();
				ajaxResult.setNavTabId("addrole");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("数据保存失败");
			}
			return "save";
		}  else if(role!= null && role.getId() != null){
			System.out.println(role.getId()+"*****");
			role = organizationService.findRoleById(role.getId());
			oaActionList = roleManageService.findAction();
//			department = organizationService.findDepartmentById(department.getId());
		}
		else if (department!= null && department.getId() != null) {
			department = organizationService.findDepartmentById(department.getId());
			role = new Role();
			role.setDepartment(department);
			oaActionList = roleManageService.findAction();
		}
		return "success";
	}
	
	/**
	 * 删除角色
	 * 
	 * @return
	 */

	@Action(value = "/organization/delrole", results = {

	@Result(name = "success", location = "/tools/common/ajaxresult.jsp", type = "dispatcher") })
	public String delrole() {
		ajaxResult = new AjaxResult();
		if (role != null && role.getId() != null) {
			try {
				organizationService.deleteRole(role,
						this.getLoginUser());
				ajaxResult.setNavTabId("main");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_FORWARD);
			} catch (GiroException e) {
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("删除失败");
			}
		}
		return "success";
	}
	
	/**
	 * 添加员工
	 * @return
	 */
	@Action(value = "/organization/edituser",
			results = { @Result(name = "success", location = "/organization/edituser.jsp", type = "dispatcher"),
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher") }
			)
	public String edituser() {
		System.out.println(formAction);
		if (formAction != null && formAction.equals("save")) {
			
			try {
				ajaxResult = new AjaxResult();
				organizationService.upUser(role, department, user,ids); // 这个地方还要处理一下，表单里面是否包含的全部用户的属性信息？如果没有，直接这样会丢失没有包含的信息
				ajaxResult.setNavTabId("main");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("数据保存失败");
			}
			return "save";
		} else {
			
			if(user!=null && user.getRealName()!=null){
				System.out.println(user.getRealName());
				pagination=userService.query(user.getRealName(), "realName", getPageNum(), getNumPerPage());
			}else{
				pagination=userService.list(this.getPageNum(), this.getNumPerPage());
			}

			return "success";
			}
				
			
			 
		
		
	}
	
	
	/**
	 * 修改员工
	 * @return
	 */
	@Action(value = "/organization/modifyuser",
			results = { @Result(name = "success", location = "/organization/modifyuser.jsp", type = "dispatcher"),
						@Result(name = "save", location = "/tools/common/ajaxresult.jsp", type = "dispatcher") }
			)
	public String modifyuser() {
		
		if (formAction != null && formAction.equals("save")) {
			ajaxResult = new AjaxResult();
			try {
				//System.out.println(user.getId()+"@@@@@@@@@@@@");
				organizationService.modifyUser(user);
				ajaxResult.setNavTabId("main");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_CLOSE);
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("数据保存失败");
			}
			return "save";
		} else if(user != null && user.getId() != null) {
			user = userService.findByUserId(user.getId());
			//System.out.println(user.getId()+"###################");
		}
		return "success";
	}
	
	

	/**
	 * 移除员工
	 * 
	 * @return
	 */

	@Action(value = "/organization/deluser", results = {

	@Result(name = "success", location = "/tools/common/ajaxresult.jsp", type = "dispatcher") })
	public String moveuser() {
		ajaxResult = new AjaxResult();
		if (user!= null && user.getId() != null) {
			try {
				organizationService.moveUser(user,
						this.getLoginUser());
				ajaxResult.setNavTabId("main");
				ajaxResult.setCallbackType(AjaxResult.CALLBACKTYPE_FORWARD);
			} catch (GiroException e) {
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setStatusCode(AjaxResult.CODE_ERROR);
				ajaxResult.setMessage("删除失败");
			}
		}
		return "success";
	}
	
	
}
