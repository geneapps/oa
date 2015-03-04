package com.ydt.oa.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.giro.common.util.DateUtils;
import com.giro.common.util.StringUtils;
import com.ydt.oa.dao.ApplicationDao;
import com.ydt.oa.dao.ApproveFlowConfigDao;
import com.ydt.oa.dao.ApproveFlowDao;
import com.ydt.oa.dao.ContractDao;
import com.ydt.oa.dao.DepartmentDao;
import com.ydt.oa.dao.ProjectDao;
import com.ydt.oa.dao.RoleDao;
import com.ydt.oa.dao.StoreRoomDao;
import com.ydt.oa.dao.UserDao;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.ApproveFlow;
import com.ydt.oa.entity.Contract;
import com.ydt.oa.entity.Department;
import com.ydt.oa.entity.Project;
import com.ydt.oa.entity.Role;
import com.ydt.oa.entity.StoreRoom;
import com.ydt.oa.entity.SystemParam;
import com.ydt.oa.entity.User;
import com.ydt.oa.interfaces.ContractAppInterface;
import com.ydt.oa.interfaces.ProjectAppInterface;

/**
 * 项目管理相关的逻辑层
 * 
 * @author caochun
 * 
 */
@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class ProjectAppService implements ProjectAppInterface {

	@Autowired
	private ApproveService approveService;
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private StoreRoomDao storeRoomDao;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private UserService userService;
	private Project project;

	/**
	 * 项目更新
	 * */
	@Override
	public void updateProjectApp(Application application, Project project, User user) throws GiroException {

		user = userService.findByUserId(user.getId());
		// application.setUser(user);
		// application.setDepartment(user.getDepartment());
		application.setApplyType(Application.APP_TYPE_PROJECT);
		application.setTitle(project.getName());
		if (StringUtils.isNull(project.getId())) {
			// 保存项目申请信息
			// project.setManager(user.getRealName());
			project.setStatus(Project.PROJECT_WAITINGAPPROVE);
			project.setUpdateTime(DateUtils.getNowDateStr());
			// System.out.println("Save ProjectDao^^^^^^^^^^^^^^^^^^^6666");
			// System.out.println("manager user="+project.getManager().getId());
			User manager = userService.findByUserId(project.getManager().getId());
			project.setManager(manager);
			projectDao.saveOrUpdate(project);
			// System.out.println("Save ProjectDao END");
			// 将项目ID保存到Application中
			application.setApplyNo(project.getId());
			// 添加新的申请
			approveService.createApp(application, user);
		} else {
			Project oldProject = projectDao.findById(project.getId());
			application = approveService.findByApplyNo("PROJECT", project.getId());
			if (application.getStatus() == Application.APP_STATUS_APPROVEING) {
				throw new GiroException(-2, "正在审批，不能修改");
			} else if (application.getStatus() == Application.APP_STATUS_DELETE) {
				throw new GiroException(-3, "修改的申请不存在");
			}else  if (!application.getUser().getId().equals(user.getId())) {
				throw new GiroException(-5, "只能本人修改申请");
			}

			if(oldProject.getStatus() == Project.PROJECT_STATUS_WARRANTY
					&& oldProject.getStatus() == Project.PROJECT_STATUS_FINISHED
					&& project.getStatus() == Project.PROJECT_STATUS_CANCEL) {
				projectDao.saveOrUpdate(project);
			} else {
				application.setStatus(Application.APP_STATUS_WAITINGAPPROVE);
				oldProject.setStatus(Project.PROJECT_WAITINGAPPROVE);
				// 控制更新字段，不是所以字段都能修改
				oldProject.setAddress(project.getAddress());
				oldProject.setAmount(project.getAmount());
				oldProject.setEndDate(project.getEndDate());
				Department dep = departmentDao.findById(user.getDepartment().getId());
				oldProject.setDepartment(dep);
				// oldProject.setManager(manager);
				oldProject.setName(project.getName());
				oldProject.setUpdateTime(DateUtils.getNowDateStr());
				oldProject.setDescription(project.getDescription());
				projectDao.saveOrUpdate(oldProject);
				approveService.updateApp(application, user);

				// System.out.println("######################");
				// System.out.println("$$$$$$$$$$$$$$$$$$$$$$$");
			}
			/*
			 * if(oldProject.getStatus()!=Project.PROJECT_STATUS_WAITAPPROVE){
			 * 
			 * // 项目已经创建，不需要在审批 projectDao.saveOrUpdate(project);
			 * 
			 * }else{ // 更新项目申请信息,还需要审批
			 * oldProject.setStatus(Project.PROJECT_WAITINGAPPROVE);
			 * projectDao.saveOrUpdate(Project); // 修改申请
			 * approveService.updateApp(application, user);
			 * 
			 * }
			 */
		}
	}

	@Override
	public void deleteApp(Application application) {

		application = approveService.findById(application.getId());
		if (application.getApplyType().equals(Application.APP_TYPE_PROJECT)) {
			project = projectService.findById(application.getApplyNo());
			project.setStatus(Project.PROJECT_DELETE);
			projectDao.saveOrUpdate(project);
		}
	}

	@Override
	public void approve(Application application) {

		// TODO Auto-generated method stub
		application = approveService.findById(application.getId());
		if (application.getApplyType().equals(Application.APP_TYPE_PROJECT)) {
			project = projectService.findById(application.getApplyNo());
			if (application.getStatus() == Application.APP_STATUS_APPROVED) {
				project.setStatus(Project.PROJECT_VALID);
				createProjectDepart(project, application.getUser());
			} else {
				project.setStatus(Project.PROJECT_INVALID);
			}
			projectDao.saveOrUpdate(project);
		}
	}

	/**
	 * 项目创建审批通过后，创建项目组织和角色
	 */
	private void createProjectDepart(Project project, User user) {

		// 根据项目创建部门组织
		Department parentDep = departmentDao.findById(user.getDepartment().getId());
		Department dep = departmentDao.findByName(project.getName());
		if (dep != null) {
			return;
		} else {
			dep = new Department();
			dep.setParent(parentDep);
			dep.setDepName(project.getName());
			dep.setDepType(Department.DEP_TYPE_PROJECT);
			dep.setDescription(project.getDescription());
			User manager = userService.findByUserId(project.getManager().getId());
			dep.setManager(manager);
			dep.setStatus(Department.DEP_STATUS_VALID);
			dep.setUpdateTime(DateUtils.getNowDateYYYYMMDD());
			departmentDao.saveOrUpdate(dep);
			project.setDepartment(dep);
			// 为该部门创建项目经理角色
			Role role = new Role();
			role.setDepartment(dep);
			role.setDescription("项目经理");
			role.setName("项目经理");
			role.setUpdateTime(DateUtils.getNowDateStr());
			roleDao.saveOrUpdate(role);
			// 更改用户为该项目项目经理
			// User manager =
			// userService.findByUserId(project.getManager().getId());
			manager.setRole(role);
			manager.setDepartment(dep);
			userDao.saveOrUpdate(manager);
			// 为项目创建库房
			Department proDepHouse = new Department();
			proDepHouse.setDepName(project.getName() + "库房");
			proDepHouse.setDepType(Department.DEP_TYPE_HOUSE);
			proDepHouse.setParent(dep);
			// System.out.println(proDepHouse.getParent().getId()+"@@@@@@@@@@@@@@@@");
			proDepHouse.setStatus(Department.DEP_STATUS_VALID);
			proDepHouse.setUpdateTime(DateUtils.getNowDateStr());
			departmentDao.saveOrUpdate(proDepHouse);
			Role role1 = new Role();
			role1.setDepartment(proDepHouse);
			role1.setDescription("库房管理员");
			role1.setName("库房管理员");
			role1.setUpdateTime(DateUtils.getNowDateStr());
			roleDao.saveOrUpdate(role1);
			StoreRoom sr = new StoreRoom();
			sr.setCreateTime(DateUtils.getNowDateStr());
			sr.setName(proDepHouse.getDepName());
			sr.setProject(projectDao.findByDepart(dep.getId()));
			sr.setStatus(StoreRoom.STOREROOM_VALID);
			storeRoomDao.saveOrUpdate(sr);
		}
	}
}
