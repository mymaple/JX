package com.jx.system.util;

import java.util.Random;

import com.jx.system.config.Logger;
import com.jx.system.config.PageData;

public class RandomUtil {
	protected static Logger logger = Logger.getLogger(AppUtil.class);

	public static void main(String[] args) {
		PageData pd = new PageData();
		pd.put("username", "zhangsan");

	}
	
	public static char randomChar() {
		Random r = new Random();
		String s = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
		return s.charAt(r.nextInt(s.length()));
	}
}
