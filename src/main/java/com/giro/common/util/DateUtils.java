package com.giro.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;


public class DateUtils {
	
	private static Logger logger = Logger.getLogger(DateUtils.class);
	
	public static String getDateStr(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return sdf.format(date);
		
	}
	
	public static String getDateStrFromTimestamp(String timestamp){
		
		Date date = null;
		
		try{
			date = new Date(Long.parseLong(timestamp));
		}catch(Exception e){
			date = new Date();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return sdf.format(date);
		
	}
	
	public static String getDateStrFromTimestamp(String timestamp, long time){
		
		Date date = null;
		
		try{
			date = new Date(Long.parseLong(timestamp)-time);
		}catch(Exception e){
			date = new Date(System.currentTimeMillis()-time);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return sdf.format(date);
		
	}
	
	public static String getYYYYMMDD(int days){
		
		Date date = null;
		long time = System.currentTimeMillis()+(long)days*24*60*60*1000;
		try{
			date = new Date(time);
		}catch(Exception e){
			date = new Date();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		return sdf.format(date);
		
	}
	
	public static String getDayStrFromTimestamp(String timestamp){
		
		Date date = null;
		
		try{
			date = new Date(Long.parseLong(timestamp));
		}catch(Exception e){
			date = new Date();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		return sdf.format(date);
		
	}
	
	public static String castDateStrToYYYYMMDD(String dateStr, boolean today){
		try {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(dateStr);
		sdf = new SimpleDateFormat("yyyyMMdd");
		if(today){
			Date now = new Date();
			
			if(sdf.format(date).equals(sdf.format(now))){
				return sdf.format(date);
			}		
		}
		
		
		
		return sdf.format(date);
		} catch (ParseException e) {
			return "";
		}
		
	}
	
	public static String getYYYYMMDD(String dateStr){
		try {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(dateStr);
		sdf = new SimpleDateFormat("yyyy-MM-dd");

		
		
		
		return sdf.format(date);
		} catch (ParseException e) {
			return "";
		}
		
	}
	
	public static String getShowTime(String dateStr){
		try {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(dateStr);
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		
		return sdf.format(date);
		} catch (ParseException e) {
			return "";
		}
		
	}
	
	public static String getShowTitleTime(String dateStr){
		try {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(dateStr);
		sdf = new SimpleDateFormat("MM-dd HH:mm");
		
		
		return sdf.format(date);
		} catch (ParseException e) {
			return "";
		}
		
	}
	
	public static String getTimestampFromDateStr(String dateStr){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			date = new Date();
		}
		
		return date.getTime()+"";
		
	}
	
	
	
	public static String getNowDateStr(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	public static String getNowDateYYYYMMDD(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}
	
	public static int getDays(String endTime){
		int quot = 0;
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  try {
			   Date date1 = new Date();
			   Date date2 = sdf.parse( endTime );
			   logger.info("endTime:"+endTime);
			   quot = (int)((date2.getTime() - date1.getTime())/1000/60/60/24);
			   logger.info("days:"+quot);
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  return quot;
		 }
	
	public static void main(String[] args){
		System.out.println(getShowTime("2012-12-12 06:23:30.0"));
//		System.out.println(getDateStrFromTimestamp("1355241600000"));
	}
}
