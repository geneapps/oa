package com.ydt.oa.interfaces;

import com.giro.common.exception.GiroException;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.Project;
import com.ydt.oa.entity.User;

/**
 *  项目申请接口
 * @author caochun
 *
 */
public interface ProjectAppInterface extends ApplicationInterface {
	public static final String APPLICATION_TYPE= "PROJECT";

	public void updateProjectApp(Application application, Project project, User user) throws GiroException;

}
