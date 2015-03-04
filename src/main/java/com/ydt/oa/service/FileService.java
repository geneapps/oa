package com.ydt.oa.service;

import java.io.File;
import java.util.Collection;
import java.util.List;
//import java.io.InputStream;
//import java.util.List;

import org.apache.log4j.Logger;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.giro.common.exception.GiroException;
import com.giro.common.util.DateUtils;
import com.giro.common.util.FileUtils;
//import com.giro.common.util.FileUtils;
//import com.giro.common.util.StringUtils;
import com.ydt.oa.dao.FileLogDao;
import com.ydt.oa.entity.FileLog;
import com.ydt.oa.entity.SystemParam;
import com.ydt.oa.entity.User;

/**
 * 用户业务逻辑层
 * @author caochun
 *
 */
@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class FileService {

	private static Logger logger = Logger.getLogger(FileService.class);
	@Autowired
	private FileLogDao fileLogDao;
	@Autowired
	private UserService userService;
	@Autowired
	private SystemConfigService systemConfigService;

	public List<FileLog> find(String[] ids) throws GiroException {

		return (List<FileLog>) fileLogDao.findByIdList(ids);
	}
	
	public List<FileLog> find(String businessType,String businessNo) {

		return fileLogDao.findByBusinessNo(businessType, businessNo);
	}


	public void saveFile(FileLog fileLog, File file) throws GiroException {

		User user = userService.findByUserId(fileLog.getUploadUser().getId());
		if (user == null) {
			throw new GiroException(-1, "用户不存在");
		}
		String savePath = systemConfigService.findParamValue(SystemParam.PARAM_FILE_LOCAL_PATH);
		try {
			// 存放路径按日期保存
			fileLog.setFilePath("/"+DateUtils.getNowDateYYYYMMDD()+"/");
			fileLog.setFileExt(fileLog.getFileName().substring(fileLog.getFileName().lastIndexOf(".")).toLowerCase());
			fileLogDao.saveOrUpdate(fileLog);
			// 文件保存在服务的名称由log.id+实际后缀组成
			String fileName = fileLog.getId()+fileLog.getFileExt();
			long size = FileUtils.saveFile(savePath+fileLog.getFilePath(), fileName, file);
			fileLog.setFileSize(size);
			fileLog.setUploadTime(DateUtils.getNowDateStr());
			fileLogDao.saveOrUpdate(fileLog);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new GiroException(-1, "保存文件失败");
		}
	}




}
