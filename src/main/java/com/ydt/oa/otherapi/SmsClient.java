package com.ydt.oa.otherapi;

import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ydt.oa.entity.SystemParam;
import com.ydt.oa.service.SystemConfigService;


/**
 * 与短信网关发送短信
 * @author caochun
 *
 */
@Service
public class SmsClient {
	@Autowired
	public SystemConfigService sysService;
	
	private static final Logger logger = Logger.getLogger(SmsClient.class);
	
	public boolean sendSmsToGateway(String mobile,String msg,String time){
		
		try {
		    HttpClient client = new HttpClient();//定义client对象
		    client.getHttpConnectionManager().getParams().setConnectionTimeout(60000);//设置连接超时时间为2秒（连接初始化时间）
		    
		    String url = sysService.findParamValue(SystemParam.PARAM_SMS_GATEWAY_URL).replaceAll("%mobile%", mobile).replaceAll("%msg%", URLEncoder.encode(msg,"GB2312")).replaceAll("%time%", time);
//		    String url = sysService.findParamValue(SystemParam.PARAM_SMS_GATEWAY_URL).replaceAll("%mobile%", mobile).replaceAll("%msg%", msg).replaceAll("%time%", time);
//		    System.out.println(url);
		    GetMethod method = new GetMethod(url);//
		    int statusCode = client.executeMethod(method);//状态，一般200为OK状态，其他情况会抛出如404,500,403等错误
		    if (statusCode == HttpStatus.SC_OK) {
		    	String result = method.getResponseBodyAsString();
			    logger.info(result);
			    if(result!=null && result.startsWith("000")){
			    	return true;
			    }
		    }
		    
		   
		    
		    

		    client.getHttpConnectionManager().closeIdleConnections(1);
		    
		    
		}catch(Exception e) {
			logger.error(e);
//			return false;			
		}
		
		return false;
		
	
	}
}
