package com.giro.common.util;


import java.math.BigDecimal;


public class AmountUtils {
	
	public static double amount(String s){
		try{
			return Double.parseDouble(s);
		}catch(Exception e){
			return 0.00;
		}
	}
	
	public static boolean equals(String s1,String s2){
		if(amount(subtract(s1,s2))==0){
			return true;
		}else return false;
	}

	
	public static String add(String s1,String s2){
		 //add, subtract, multiply and divide
		s1 = check(s1);
		s2 = check(s2);
		BigDecimal b1 = new BigDecimal(s1);
		BigDecimal b2 = new BigDecimal(s2);
		
		return b1.add(b2).toString();
	}
	
	public static String subtract(String s1,String s2){
		 //add, subtract, multiply and divide
		
		s1 = check(s1);
		s2 = check(s2);
		
		BigDecimal b1 = new BigDecimal(s1);
		BigDecimal b2 = new BigDecimal(s2);
		
		return b1.subtract(b2).toString();
	}
	
	public static String multiply(String s1,String s2){
		 //add, subtract, multiply and divide
		s1 = check(s1);
		s2 = check(s2);
		BigDecimal b1 = new BigDecimal(s1);
		BigDecimal b2 = new BigDecimal(s2);
		
		return b1.multiply(b2).toString();
	}
	
	public static String divide(String s1,String s2){
		 //add, subtract, multiply and divide
		s1 = check(s1);
		s2 = check(s2);
		BigDecimal b1 = new BigDecimal(s1);
		BigDecimal b2 = new BigDecimal(s2);
		
		return b1.divide(b2).toString();
	}
	
	public static boolean greater(String s1,String s2){
		 //add, subtract, multiply and divide
		s1 = check(s1);
		s2 = check(s2);
		BigDecimal b1 = new BigDecimal(s1);
		BigDecimal b2 = new BigDecimal(s2);
		
		if(b1.subtract(b2).doubleValue()<0){
			return false;
		}
		return true;
	}
	
	private static String check(String s){
		try{
			return Double.parseDouble(s)+"";
		}catch(Exception e){
			return "0.0";
		}
	}
	
	public static void main(String[] args){
		System.out.println((AmountUtils.amount(null)==0));
	}


}
