package com.ydt.oa.service;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.giro.common.util.DateUtils;
import com.giro.common.util.StringUtils;
import com.ydt.oa.bean.AjaxResult;
import com.ydt.oa.dao.DepartmentDao;
import com.ydt.oa.dao.ProjectDao;
import com.ydt.oa.dao.RoleDao;
import com.ydt.oa.dao.StoreRoomDao;
import com.ydt.oa.dao.UserDao;
import com.ydt.oa.entity.Department;
import com.ydt.oa.entity.Project;
import com.ydt.oa.entity.Role;
import com.ydt.oa.entity.StoreRoom;
import com.ydt.oa.entity.User;

/**
 * 组织管理相关的逻辑层
 * 
 * @author caochun
 * 
 */
@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class OrganizationService {

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserService userService;
	@Autowired
	private StoreRoomDao storeRoomDao;
	@Autowired
	private ProjectDao projectDao;
	private Department department;
	private Department parent;

	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Department findDepartmentById(String id) {
        
		return departmentDao.findById(id);
	}

	public Role findRoleById(String id) {

		return roleDao.findById(id);
	}
	
	public User findUserById(String id) {

		return userDao.findById(id);
	}
	
	public List<Department> departmentlist(){
		
		List<Department> deps = departmentDao.list();
		System.out.println(deps.size()+"****");
		for(Department dep:deps){
			System.out.println(dep.getDepName());
			List<Department> dep1s = dep.getChilds();
			for(Department dep1:dep1s){
				System.out.println(dep1.getDepName());
				
			}
		}
		
		return departmentDao.list();
	}
	
/*	public List<Role> rolelist(Department department){

		return roleDao.list(department);
	}*/


	/**
	 * 部门更新
	 * */
	public void upDepartment(Department department, User user)
			throws GiroException {
		user = userService.findByUserId(user.getId());
		if (StringUtils.isNull(department.getId())) {
			// 保存部门信息
			department.setDepName(department.getDepName());
			System.out.println(department.getDepName());
			department.setDescription(department.getDescription());
			System.out.println(department.getDescription());
			department.setUpdateTime(DateUtils.getNowDateStr());
			department.setStatus(department.DEP_STATUS_VALID);
			departmentDao.saveOrUpdate(department);
			System.out.println("sava Dao OK $$$$$$$$$$$");
		}else {
			Department oldDepartment = departmentDao.findById(department.getId());
			oldDepartment.setDepName(department.getDepName());
			System.out.println(department.getDepName());
			oldDepartment.setDescription(department.getDescription());
			System.out.println(department.getDescription());
			oldDepartment.setUpdateTime(DateUtils.getNowDateStr());
			departmentDao.saveOrUpdate(oldDepartment);
			System.out.println("sava Dao OK @@@@@@@@@@@@@@@");
		}
	}

	/**
	 * 删除部门
	 * 
	 * @return
	 * @throws GiroException
	 */

	public void deleteDepartment(Department department, User user)
			throws GiroException {
		department = departmentDao.findById(department.getId());
		if(department.getId() !=null) {
			 if(department.getChilds().size()>0){
					List<Department> dep1s =departmentDao.listChild(department);
					System.out.println(dep1s.size());
					for(Department dep1:dep1s){
						dep1.setStatus(Department.DEP_STATUS_DELETE);
						departmentDao.saveOrUpdate(dep1);
						}
					
					}
			
			department.setStatus(Department.DEP_STATUS_DELETE);
			department.setUpdateTime(DateUtils.getNowDateStr());
			departmentDao.saveOrUpdate(department);
		}

	}
	
	
	/**
	 * 角色更新
	 * */
	public void upRole(Role role)
			throws GiroException {
//		if (StringUtils.isNull(role.getId())) {
//			role.setName(role.getName());
//			role.setDescription(role.getDescription());
//			role.setUpdateTime(DateUtils.getNowDateStr());
//			roleDao.saveOrUpdate(role);
//		}else {
//			Role oldRole = roleDao.findById(role.getId());
//			oldRole.setName(role.getName());
//			oldRole.setDescription(role.getDescription());
//		}
		
		roleDao.saveOrUpdate(role);
	}
	
	/**
	 * 删除岗位
	 * 
	 * @return
	 * @throws GiroException
	 */

	public void deleteRole(Role role, User user)
			throws GiroException {
		role = roleDao.findById(role.getId());
		role.setStatus(Role.ROLE_STATUS_DELETE);
		role.setUpdateTime(DateUtils.getNowDateStr());
		roleDao.saveOrUpdate(role);
		}
	
	/**
	 * 添加员工
	 * 
	 * @return
	 * @throws GiroException
	 */

	public void upUser(Role role, Department department,User user,String[] ids)
			throws GiroException {		
		for(int i=0;i<ids.length;i++) {
			 user=userDao.findById(ids[i]);
			System.out.println(user.getId()+"^^^^");
			user.setRole(role); // 把当前岗位赋给用户
		
			user.setDepartment(department);
			
			userDao.saveOrUpdate(user);
            
			//System.out.println(roleDao.findById(role.getId()).getName()+"***");
			if(roleDao.findById(role.getId()).getName().equals("库房管理员")){
				Department dept1=departmentDao.findById(user.getDepartment().getId());
				//System.out.println(dept1.getDepName()+"()()()");
				Department dept=dept1.getParent();
				//System.out.println(dept.getId()+"^^**&");
				Project project=projectDao.findByDepart(dept.getId());
			    
				StoreRoom storeRoom=storeRoomDao.findByProject(project);
				storeRoom.setUser(user);
				storeRoomDao.saveOrUpdate(storeRoom);
			}
		}
		
	}
	
	/**
	 * 员工信息更新
	 * */
	public void modifyUser(User user)
			throws GiroException {
		System.out.println(user.getAge()+"**");
		     User oldUser = userDao.findById(user.getId());
		     oldUser.setUserNo(user.getUserNo());
		     oldUser.setRealName(user.getRealName());
		     oldUser.setAge(user.getAge());
		     oldUser.setMobile(user.getMobile());
		     oldUser.setAddress(user.getAddress());
		     System.out.println(oldUser.getAge()+"****");
			userDao.saveOrUpdate(oldUser);
			System.out.println("sava Dao OK $$$$$$$$$$$");
		
	}
	
	/**
	 * 移除员工
	 * 
	 * @return
	 * @throws GiroException
	 */

	public void moveUser(User olduser, User user)
			throws GiroException {
		olduser = userDao.findById(olduser.getId());
		olduser.setDepartment(null);
		olduser.setRole(null);
		userDao.saveOrUpdate(olduser);
		}


}
