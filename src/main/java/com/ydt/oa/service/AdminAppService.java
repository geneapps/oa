package com.ydt.oa.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.giro.common.exception.GiroException;
import com.giro.common.util.StringUtils;
import com.ydt.oa.dao.AdminDao;
import com.ydt.oa.dao.FileLogDao;
import com.ydt.oa.entity.Admin;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.FileLog;
import com.ydt.oa.entity.User;
import com.ydt.oa.interfaces.AdminAppInterface;

/**
 * 系统设置业务逻辑层
 * 
 * @author caochun
 * 
 */
@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class AdminAppService implements AdminAppInterface {

	@Autowired
	private ApproveService approveService;
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private FileLogDao fileLogDao;
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;
	private Admin admin;

	/**
	 * 更新行政申请单
	 */
	@Override
	public void updateAdminApp(Application application, Admin admin, List<FileLog> fileLogList, User user)
			throws GiroException {

		user = userService.findByUserId(user.getId());
		application.setUser(user);
		application.setDepartment(user.getDepartment());
		application.setApplyType(Application.APP_TYPE_ADMIN);
		admin.setTitle(application.getTitle());
		if (StringUtils.isNull(application.getId())) {
			// 保存行政申请信息
			admin.setStatus(Admin.ADMIN_WAITINGAPPROVE);
			adminDao.saveOrUpdate(admin);
			if (fileLogList != null) {
				System.out.println("11");
				String[] ids = new String[fileLogList.size()];
				for (int i = 0; i < fileLogList.size(); i++) {
					ids[i] = fileLogList.get(i).getId();
				}
				Collection<FileLog> fileLogs = fileLogDao.findByIdList(ids);
				System.out.println("22");
				for (FileLog fileLog : fileLogs) {
					fileLog.setBusinessNo(admin.getId());
					fileLog.setBusinessType(Admin.FILE_TYPE);
				}
				fileLogDao.saveOrUpdateAll(fileLogs);
				System.out.println("33");
			}
			// 将合同id保存到Application中
			application.setApplyNo(admin.getId());
			// 添加新的申请
			approveService.createApp(application, user);
		} else {
			// 修改申请
			approveService.updateApp(application, user);
			Admin oldAdmin = adminDao.findById(admin.getId());
			// 清楚文件绑定
			Collection<FileLog> oldFileList = fileLogDao.findByBusinessNo(Admin.FILE_TYPE, oldAdmin.getId());
			for (FileLog file : oldFileList) {
				file.setBusinessNo(null);
			}
			fileLogDao.saveOrUpdateAll(oldFileList);
			// 保存请款申请信息
			oldAdmin.setTitle(oldAdmin.getTitle());
			oldAdmin.setAdminType(oldAdmin.getAdminType());
			oldAdmin.setInstruction(oldAdmin.getInstruction());
			oldAdmin.setStatus(Admin.ADMIN_WAITINGAPPROVE);
			adminDao.saveOrUpdate(oldAdmin);
			if (fileLogList != null) {
				//System.out.println("11111111111");
				String[] ids = new String[fileLogList.size()];
				for (int i = 0; i < fileLogList.size(); i++) {
					ids[i] = fileLogList.get(i).getId();
				}
				//System.out.println("22222222");
				Collection<FileLog> fileLogs = fileLogDao.findByIdList(ids);
				for (FileLog fileLog : fileLogs) {
					fileLog.setBusinessType(Admin.FILE_TYPE);
					fileLog.setBusinessNo(oldAdmin.getId());
				}
				fileLogDao.saveOrUpdateAll(fileLogs);
				//System.out.println("33333");
			}
		}
	}

	@Override
	public void deleteApp(Application application) {

		application = approveService.findById(application.getId());
		if (application.getApplyType().equals(Application.APP_TYPE_ADMIN)) {
			admin = adminService.findById(application.getApplyNo());
			admin.setStatus(Admin.ADMIN_DELETE);
			adminDao.saveOrUpdate(admin);
		}
	}

	@Override
	public void approve(Application application) {

		application = approveService.findById(application.getId());
		if (application.getApplyType().equals(Application.APP_TYPE_ADMIN)) {
			admin = adminService.findById(application.getApplyNo());
			if (application.getStatus() == Application.APP_STATUS_APPROVED) {
				admin.setStatus(Admin.ADMIN_VALID);
			} else {
				admin.setStatus(Admin.ADMIN_INVALID);
			}
			adminDao.saveOrUpdate(admin);
		}
	}
}
