package com.ydt.oa.interfaces;

import java.util.List;

import com.giro.common.exception.GiroException;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.BorrowMoney;
import com.ydt.oa.entity.User;

public interface BorrowMoneyAppInterface extends ApplicationInterface {
	//public static final String APPLICATION_TYPE= "BorrowMONEY";

	public void updateBorrowMoneyApp(Application application, BorrowMoney borrowMoney, User user) throws GiroException;
	

}
