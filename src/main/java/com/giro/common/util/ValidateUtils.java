package com.giro.common.util;


public class ValidateUtils {
	
	public static boolean checkEmail(String value, int length) {  
        return value.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")&&value.length()<=length;  
} 
	
	/** 
     * 检查电话输入是否正确 
     * 正确格式 012-87654321、0123-87654321、0123－7654321 
     * @param value 
     * @return 
     */  
    public static boolean checkTel(String value) {  
        return value.matches("\\d{4}-\\d{8}|\\d{4}-\\d{7}|\\d(3)-\\d(8)");    
    }  
  
    /** 
     * 检查手机输入是否正确 
     *  
     * @param value 
     * @return 
     */  
    public static boolean checkMobile(String value) {
    	
        return value.matches("^[1]\\d{10}");  
    } 
	
    public static boolean checkId(String value){  
        return value.matches("[a-zA-Z][a-zA-Z0-9_]{4,15}$");  
    }
    
    public static boolean checkLength(String value, int min , int max) {
    	
    	if(StringUtils.isNull(value)) return false;
    	
    	if(value.length()<min || value.length()>max) return false;
    	
        return true;
    } 
	
}
