package com.ydt.oa.interfaces;

import com.ydt.oa.entity.Application;

/**
 * 申请单删除以及审批接口
 * @author caochun
 *
 */
public interface ApplicationInterface {
	
	/**
	 * 创建单删除成功后调用的方法
	 * @param application
	 * @param flag
	 */
	public void deleteApp(Application application);
	
	/**
	 * 审批结束后后调用的方法
	 * @param application
	 * @param flag
	 */
	public void approve(Application application);

}
