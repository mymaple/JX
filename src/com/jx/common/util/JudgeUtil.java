package com.jx.common.util;


public class JudgeUtil {
	
	/**
	 * 判断字符串是否能转换为 非负整数
	 * @param str
	 * @return
	 */
	public static boolean stringCanToFFZS(String str) {
		return str != null && !"".equals(str) && str.matches("^[1-9]\\d*|0$");
	}
	
	/**
	 * 判断字符串是否能转换为 正整数
	 * @param str
	 * @return
	 */
	public static boolean stringCanToZZS(String str) {
		return str != null && !"".equals(str) && str.matches("^[1-9]\\d*$");
	}
	
}
