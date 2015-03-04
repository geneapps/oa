package com.giro.common.util;


import java.math.BigDecimal;


public class IntUtils {
	
	public static int toInt(String s){
		try{
			return Integer.parseInt(s);
		}catch(Exception e){
			return 0;
		}
	}
	

}
