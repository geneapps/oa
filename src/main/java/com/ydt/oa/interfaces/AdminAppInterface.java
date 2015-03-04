package com.ydt.oa.interfaces;

import java.util.List;

import com.giro.common.exception.GiroException;
import com.ydt.oa.entity.Admin;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.FileLog;
import com.ydt.oa.entity.User;

/**
 * 申请单删除以及审批接口
 * @author caochun
 *
 */
public interface AdminAppInterface {
	public static final String APPLICATION_TYPE= "ADMIN";

	public void updateAdminApp(Application application, Admin admin,List<FileLog> fileLogList, User user) throws GiroException;

	void deleteApp(Application application);

	void approve(Application application);
	

}
