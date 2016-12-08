package com.jx.common.util;

public class StringUtil {
	
	public static String firstToUpper(String str) {
		
		return str.replaceFirst(str.substring(0, 1),str.substring(0, 1).toUpperCase()) ;
//        char[] cs=name.toCharArray();
//        cs[0]-=32;
//        return String.valueOf(cs);
	}
	
	public static String firstToLower(String str) {
		
		return str.replaceFirst(str.substring(0, 1),str.substring(0, 1).toLowerCase()) ;
	}
	
}
