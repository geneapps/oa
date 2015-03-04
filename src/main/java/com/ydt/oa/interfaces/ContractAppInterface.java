package com.ydt.oa.interfaces;

import java.util.List;

import com.giro.common.exception.GiroException;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.Contract;
import com.ydt.oa.entity.FileLog;
import com.ydt.oa.entity.User;

/**
 * 合同申请接口
 * @author caochun
 *
 */
public interface ContractAppInterface extends ApplicationInterface {
//	public static final String APPLICATION_TYPE= "CONTRACT";

	public void updateContractApp(String  id,Application application, Contract contract,List<FileLog> fileLogList, User user) throws GiroException;

}
