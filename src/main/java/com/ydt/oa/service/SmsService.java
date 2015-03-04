package com.ydt.oa.service;


import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giro.common.exception.GiroException;
import com.giro.common.util.DateUtils;
import com.ydt.oa.dao.SmsDao;
import com.ydt.oa.entity.Sms;
import com.ydt.oa.otherapi.SmsClient;



/**
 * 短信业务逻辑层
 * @author caochun
 *
 */
@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class SmsService {
	
	@Autowired
	private SmsDao smsDao;
	
	@Autowired
	private SmsClient smsClient;

	
//	/**
//	 * 手机号码： 支持多个手机号，用户用分号隔开
//     * 短信内容： 支持超过70个汉字的短信
//     * 发送时间：格式为yyyy-MM-dd HH:mm:ss，当该值为空时则为立刻发送
//	 * @param mobile
//	 * @param msg
//	 * @return
//	 */
//	public boolean sendSmsToGateway(String mobile,String msg,String time){
//		
//		String url = "WSCmd=Bolan.Com.Sender.SmsSender.CSend&To="+mobile+"&Content="+msg;
//		
//		Boolean cloudosResult = smsClient.back(true).path(url).accept(
//				MediaType.APPLICATION_XML).type(MediaType.APPLICATION_XML).post(remark,Boolean.class);
//		result.setSuccess(cloudosResult.booleanValue());
//		
//		return false;
//	}
	
	public static void main(String[] args){
		
	}
	
	/**
	 * 
	 * @param mobile
	 * @param msg
	 * @param time
	 * @throws GiroException 
	 */
	public void sendSms(String mobile,String msg,String time,boolean nowSend){
		// 向网关发送失败后，需要保存到数据库里面
		
		if(nowSend){
			if(!smsClient.sendSmsToGateway(mobile,msg,time)){
				
				saveSmsToDb(mobile,msg,time);
			}
		}else{
			saveSmsToDb(mobile,msg,time);
		}		
		
	}
	
	public void sendSms(String mobile,String msg){
		sendSms(mobile,msg,null,false);
	}
	
	private void saveSmsToDb(String mobile,String msg,String time){
//		System.out.println(mobile+":"+msg);
		Sms sms = new Sms();
		sms.setMobile(mobile);
		sms.setMsg(msg);
		sms.setSendDate(time);
		sms.setSendtimes(0);
		sms.setStatus(Sms.STATUS_WAIT_SEND);
		sms.setCreateDate(DateUtils.getNowDateStr());			
		smsDao.saveOrUpdate(sms);
	}
	
//	public void sendValidateCode(String mobile) throws GiroException{
//		
//		if(!ValidateUtils.checkMobile(mobile)){
//			throw new GiroException(-1,"手机号输入不正确");
//		}
//		
//		List<ValidateCode> list = validateCodeDao.getValidateCode(mobile, 3);
//		
//		if(list.size()>0){
//			throw new GiroException(-1,"验证码正在发送，请不要重复获取！");
//		}
//		
//		ValidateCode code = new ValidateCode();
//		code.setMobile(mobile);
//		code.setSendDate(DateUtils.getNowDateStr());
//		code.setStatus(ValidateCode.STATUS_WAIT_CHECK);
//		Random random = new Random();
//		String temp =random.nextInt(999999)+"000000";
//		code.setCode(temp.substring(0, 6));
//		validateCodeDao.saveOrUpdate(code);
//		String msg = "您的注册验证码为："+code.getCode()+"，如非本人操作，请勿理会！详情请登录http://pay.bolaninfo.com";
//		sendSms(mobile,msg,null,true);
//	}
	
//	public boolean checkValidateCode(String mobile,String code){
//		
//		List<ValidateCode> list = validateCodeDao.getValidateCode(mobile, code, 10);
//		
//		if(list.size()<1){
//			return false;
//		}else{
//
//			validateCodeDao.deleteAll(list);
//			
//			return true;
//		}		
//		
//	}
	
	public void sendSmsFormDb(){
		List<Sms> list = smsDao.getAll();
		for(Sms sms: list){
			
//			smsClient.sendSmsToGateway(sms.getMobile(),sms.getMsg(),sms.getSendDate());
//			smsDao.delete(sms);

			if(sms.getStatus()==Sms.STATUS_WAIT_SEND){
				sms.setStatus(Sms.STATUS_SENDING);
				smsDao.saveOrUpdate(sms);
				
				if(smsClient.sendSmsToGateway(sms.getMobile(),sms.getMsg(),sms.getSendDate())){
					smsDao.delete(sms);
				}else{
					if(sms.getSendtimes()>3){
						smsDao.delete(sms);
					}else{
						sms.setSendtimes(sms.getSendtimes()+1);
						sms.setStatus(Sms.STATUS_WAIT_SEND);
						smsDao.saveOrUpdate(sms);
					}
				}
			}
			
		}
	}
	
	
	
	
}
