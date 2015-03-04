package com.ydt.oa.interfaces;

import com.giro.common.exception.GiroException;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.PurchasePlan;
import com.ydt.oa.entity.User;

/**
 * 采购计划申请接口
 * @author caochun
 *
 */
public interface PurchasePlanAppInterface extends ApplicationInterface {
	public static final String APPLICATION_TYPE= "PurchasePlan";

	public void updatePurchasePlanApp(Application application, PurchasePlan purchasePlan, User user) throws GiroException;

}
