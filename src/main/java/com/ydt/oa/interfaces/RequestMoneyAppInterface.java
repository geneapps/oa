package com.ydt.oa.interfaces;

import java.util.List;

import com.giro.common.exception.GiroException;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.FileLog;
import com.ydt.oa.entity.RequestMoney;
import com.ydt.oa.entity.User;

public interface RequestMoneyAppInterface extends ApplicationInterface {
	//public static final String APPLICATION_TYPE= "REQUESTMONEY";

	public void updateRequestMoneyApp(Application application, RequestMoney requestMoney,List<FileLog> fileLogList, User user) throws GiroException;
	

}
