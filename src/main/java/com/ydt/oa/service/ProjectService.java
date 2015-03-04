package com.ydt.oa.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.giro.common.util.DateUtils;
import com.ydt.oa.dao.ApplicationDao;
import com.ydt.oa.dao.ApproveFlowConfigDao;
import com.ydt.oa.dao.ApproveFlowDao;
import com.ydt.oa.dao.ProjectDao;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.ApproveFlow;
import com.ydt.oa.entity.Department;
import com.ydt.oa.entity.Project;
import com.ydt.oa.entity.StoreRoom;
import com.ydt.oa.entity.SystemParam;
import com.ydt.oa.entity.User;
import com.ydt.oa.interfaces.ContractAppInterface;

/**
 * 项目管理相关的逻辑层
 * 
 * @author caochun
 * 
 */
@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class ProjectService {

	@Autowired
	private ProjectDao projectDao;

	/**
	 * 显示全部的项目列表
	 * 
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public Pagination list(int currPage, int pageSize) {

		return projectDao.list(currPage, pageSize);
	}
	
	public Pagination findProject(Project project ,int currPage, int pageSize ){
		return projectDao.findProject(project,currPage, pageSize);
	}

	/*
	 * public Application findById(String proId) {
	 * 
	 * return approveDao.findById(proId); }
	 */
	public Project findById(String id) {

		return projectDao.findById(id);
	}
	public Project findByName(String name){
		return projectDao.findByName(name);
	}
	public Project findByDepart(String id){
		return projectDao.findByDepart(id);
	}
	public List<Project> queryProjects() {

		return projectDao.queryProjects();
		
	}

}
