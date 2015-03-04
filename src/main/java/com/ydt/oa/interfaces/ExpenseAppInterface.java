package com.ydt.oa.interfaces;

import java.util.List;

import com.giro.common.exception.GiroException;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.CostDetails;
import com.ydt.oa.entity.Expense;
import com.ydt.oa.entity.FileLog;
import com.ydt.oa.entity.User;

public interface ExpenseAppInterface extends ApplicationInterface {
	//public static final String APPLICATION_TYPE= "REQUESTMONEY";

	public void updateExpenseApp(Application application, Expense expense,String typeParma, List<CostDetails> costList,List<FileLog> fileLogList,User user) throws GiroException;
	

}
