package com.ydt.oa.service;

import java.io.File;
import java.util.List;
//import java.io.InputStream;
//import java.util.List;

import org.apache.log4j.Logger;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.giro.common.util.DateUtils;
import com.giro.common.util.MD5;
import com.ydt.oa.dao.UserDao;
import com.ydt.oa.entity.Material;
import com.ydt.oa.entity.Role;
import com.ydt.oa.entity.User;

/**
 * 用户业务逻辑层
 * @author caochun
 *
 */
@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class UserService {

	private static Logger logger = Logger.getLogger(UserService.class);
	@Autowired
	private UserDao userDao;


	public Pagination list(int currPage, int pageSize) {

		return userDao.getAll(currPage, pageSize);
	}

	public Pagination lookupUser(User user,int currPage,int pageSize) {

		return userDao.lookupUser(user, currPage, pageSize);
	}
	public Pagination query(String key,String type, int currPage, int pageSize) {

		return userDao.query(key, type, currPage, pageSize);
	}
	
	public Pagination departmentUser(String departmentId,String roleId, int currPage, int pageSize) {

		return userDao.queryByDepartment(departmentId,roleId, currPage, pageSize);
	}	


	public User findByUserId(String userId) {

		return userDao.findById(userId);
	}

	public User findByUserNo(String userNo) {

		return userDao.findByUserNo(userNo);
	}
	public User findByUserName(String userName) {

		return userDao.findByUserName(userName);
	}
//  public User findById(String id) {
//	  return userDao.findById(id);
//  }
	public void savePhoto(String userId, File file, String ext) throws GiroException {

//		User user = this.findByUser(userId);
//		if (user == null) {
//			throw new GiroException(-1, "用户不存在");
//		}
//		String savePath = paramDao.getParamValue(Param.PARAM_PHOTO_LOCAL_PATH);
//		try {
//			FileUtils.saveFile(savePath, user.getId() + ext, file);
//			user.setPhoto(user.getId() + ext);
//			userDao.saveOrUpdate(user);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e);
//			throw new GiroException(-1, "保存图片失败");
//		}
	}

		public List<User> suggest(String suggestFields, String keywords) {
			
			return userDao.suggest(suggestFields, keywords);
			}

	


	public User login(String userNo, String password) throws GiroException {

		logger.debug(userNo + " login...");
		User user = findByUserNo(userNo);
		if (user == null) {
			throw new GiroException(-1, "用户不存在！");
		}
		if (!user.getPassword().equals(password)) {
			throw new GiroException(-1, "用户密码错误！");
		}
		if (user.getStatus()== 99) {
			throw new GiroException(-1, "用户不可用");
		}

		user.setLastLogin(DateUtils.getNowDateStr());
		userDao.saveOrUpdate(user);
		return user;
	}


	public void updateUser(User user) throws GiroException {

		user.validate();
		userDao.saveOrUpdate(user);

	}
	
	public void updatePwd(String userId,String old, String newPwd) throws GiroException {

		User user = this.findByUserId(userId);
		
		if (!user.getPassword().equals(MD5.getMD5(old))) {
			throw new GiroException(-1, "用户密码错误！");
		}else{
			user.setPassword(newPwd);
		}
		
		userDao.saveOrUpdate(user);

	}

	public void deleteUser(String userId) {

		User user = userDao.findById(userId);
		userDao.delete(user);
	}
	
	/**
	 * 删除员工
	 * 
	 * @return
	 * @throws GiroException
	 */

	public void delUser(User user)
			throws GiroException {
		user = userDao.findById(user.getId());
		user.setStatus(User.USER_DELETE);
		userDao.saveOrUpdate(user);
		}

	public boolean checkRegName(User user) {

		User obj = userDao.findByUserNo(user.getUserNo());
		if (obj != null)
			return false;
		return true;
	}

	public void regUser(User user) throws GiroException {
		System.out.println(user.getSex()+"@@@@@@@@");
		user.validate();
		User obj = userDao.findByUserNo(user.getUserNo());
		if (obj != null)
			throw new GiroException(-1, "用户已经存在");
		user.setStatus(1);
		userDao.save(user);

	}
	
	public Pagination userList(int currPage,int pageSize) {

		return userDao.userList(currPage, pageSize);
	}

}
