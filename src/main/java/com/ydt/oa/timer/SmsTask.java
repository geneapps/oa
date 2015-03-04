package com.ydt.oa.timer;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.giro.common.util.StringUtils;
import com.ydt.oa.entity.SystemParam;
import com.ydt.oa.service.SmsService;
import com.ydt.oa.service.SystemConfigService;


/**
 * 短信发送定时器
 * @author caochun
 *
 */
public class SmsTask{
	@Autowired
	private SmsService smsService;
	@Autowired
	private SystemConfigService configService;
	
	private static final Logger logger = Logger.getLogger(SmsTask.class);
	
	public void send(){
		try{
//			logger.info("正在发送短信……");
			String flag = configService.findParamValue(SystemParam.PARAM_SMS_TASK_FLAG);
		//	System.out.println("定时器启动。。。。。。。。。" + flag);
			if(StringUtils.isNull(flag) || flag.equals("0")){
				configService.setParam("短信定时器标志", SystemParam.PARAM_SMS_TASK_FLAG, "1");
			//	System.out.println("diao   ");
				smsService.sendSmsFormDb();
				configService.setParam("短信定时器标志", SystemParam.PARAM_SMS_TASK_FLAG, "0");
			}			
			
		}catch(Exception e){
			logger.error(e);
		}
	}
	
}
