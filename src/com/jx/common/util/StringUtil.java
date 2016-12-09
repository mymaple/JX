package com.jx.common.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

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
	
	
	public static final int LEFT_SPACE = 0;
	  public static final int RIGHT_SPACE = 1;
	  public static final int TRUNC_LEFT = 0;
	  public static final int TRUNC_RIGHT = 1;

	  public static boolean startsWithIgnoreCase(String base, String start)
	  {
	    if (base.length() < start.length()) {
	      return false;
	    }
	    return base.regionMatches(true, 0, start, 0, start.length());
	  }

	  public static boolean endsWithIgnoreCase(String base, String end)
	  {
	    if (base.length() < end.length()) {
	      return false;
	    }
	    return base.regionMatches(true, base.length() - end.length(), end, 0, 
	      end.length());
	  }

	  public static int parseInt(String value, int defaultValue)
	  {
	    if (value == null)
	      return defaultValue;
	    try
	    {
	      return Integer.parseInt(value); } catch (Exception e) {
	    }
	    return defaultValue;
	  }

	  public static boolean parseBoolean(String attribute, boolean defaultValue)
	  {
	    if (attribute == null) {
	      return defaultValue;
	    }

	    return attribute.equals("true");
	  }

	  public static String trim(String string)
	  {
	    return string == null ? "" : string.trim();
	  }

	  public static String leftTrim(String str)
	  {
	    if (str == null) {
	      return "";
	    }
	    byte[] bytes = str.getBytes();
	    int index = 0;
	    while (true)
	    {
	      byte ch = bytes[index];
	      if (ch != 32)
	        break;
	      index++;
	    }
	    return str.substring(index);
	  }

	  public static String rightTrim(String str)
	  {
	    if (str == null) {
	      return "";
	    }
	    byte[] bytes = str.getBytes();
	    int index = length(str);

	    if (index == 0) {
	      return "";
	    }
	    index--;
	    do
	    {
	      byte ch = bytes[index];
	      if (ch != 32)
	        break;
	      index--;
	    }while (index >= 0);

	    return new String(str.getBytes(), 0, index + 1);
	  }

	  public static String allTrim(String str)
	  {
	    if (str == null)
	      return "";
	    String tmp = str.trim();
	    if (tmp.equals(""))
	      return "";
	    int idx = 0;
	    int len = 0;
	    len = tmp.length();
	    idx = tmp.indexOf(" ");
	    while (idx > 0) {
	      tmp = tmp.substring(0, idx) + tmp.substring(idx + 1, len);
	      idx = tmp.indexOf(" ");
	      len = tmp.length();
	    }

	    return tmp;
	  }

	  public static String alignStr(String str, int len, int direct, int truncWay)
	  {
	    return alignStr(str, len, direct, truncWay, ' ');
	  }

	  public static String alignStr(String str, int len, int direct, int truncWay, char fixStr)
	  {
	    if (str == null) {
	      return "";
	    }
	    byte[] b = getBytes(str);
	    int l = length(str);
	    if (l >= len) {
	      if (truncWay == 0) {
	        return new String(b, l - len, len);
	      }
	      return new String(b, 0, len);
	    }

	    StringBuffer sb = new StringBuffer(str);
	    for (int i = l; i < len; i++) {
	      if (direct == 0)
	        sb = sb.insert(0, fixStr);
	      else
	        sb = sb.append(fixStr);
	    }
	    return sb.substring(0);
	  }

	  public static String toUTF8String(String s) {
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < s.length(); i++) {
	      char c = s.charAt(i);
	      if ((c >= 0) && (c <= 'ÿ')) {
	        sb.append(c); } else {
	        byte[] b;
	        try {
	          b = Character.toString(c).getBytes("utf-8");
	        }
	        catch (Exception ex)
	        {
	          System.out.println(ex);
	          b = new byte[0];
	        }
	        for (int j = 0; j < b.length; j++) {
	          int k = b[j];
	          if (k < 0)
	            k += 256;
	          sb.append("%" + Integer.toHexString(k).toUpperCase());
	        }
	      }
	    }
	    return sb.toString();
	  }

	  /** @deprecated */
	  public static int length(String str)
	  {
	    if (str == null)
	      return 0;
	    try {
	      return new String(str.getBytes("GBK"), "8859_1").length(); } catch (UnsupportedEncodingException e) {
	    }
	    return -1;
	  }

	  public static byte[] getBytes(String str)
	  {
	    try
	    {
	      return str.getBytes("GBK");
	    } catch (UnsupportedEncodingException e) {
	      e.printStackTrace();
	    }return "".getBytes();
	  }

	  public static String stringNumberAdd(String numberStr, int increaseValue, int minLength)
	  {
	    if ((numberStr == null) || (numberStr.trim().equals(""))) {
	      numberStr = "0";
	    }

	    int stringLength = numberStr.length();
	    if (stringLength < minLength) {
	      stringLength = minLength;
	    }
	    if (increaseValue != 0) {
	      numberStr = 
	        Long.toString(Long.parseLong(numberStr) + increaseValue);
	    }
	    stringLength -= numberStr.length();

	    for (int i = 0; i < stringLength; i++) {
	      numberStr = "0" + numberStr;
	    }
	    return numberStr;
	  }

	  public static String trimStringNumberRightZero(String numberStr)
	  {
	    if ((numberStr == null) || (numberStr.trim().equals(""))) {
	      return "";
	    }
	    int i = numberStr.length();
	    while ((i > 0) && (numberStr.charAt(i - 1) == '0')) {
	      i--;
	    }
	    return numberStr.substring(0, i);
	  }

	  public static String trimStringNumberLeftZero(String numberStr)
	  {
	    if ((numberStr == null) || (numberStr.trim().equals(""))) {
	      return "";
	    }
	    int i = 0;
	    while (numberStr.charAt(i) == '0') i++;

	    return numberStr.substring(i);
	  }

	  public static final ArrayList splitString(String targetString, String seperator)
	  {
	    if ((targetString == null) || (targetString.trim().equals(""))) {
	      return new ArrayList();
	    }
	    ArrayList resultStrs = new ArrayList();
	    String singleStatement = "";
	    int fromIndex = 0;
	    int endIndex = 0;
	    boolean breakFor = false;
	    while (fromIndex < targetString.length()) {
	      endIndex = targetString.indexOf(seperator, fromIndex);
	      if (endIndex == -1) {
	        endIndex = targetString.length();
	        breakFor = true;
	      }
	      singleStatement = targetString.substring(fromIndex, endIndex);
	      if ((singleStatement != null) && (!singleStatement.trim().equals(""))) {
	        resultStrs.add(singleStatement);
	      }
	      fromIndex = endIndex + 1;
	      if (breakFor) {
	        break;
	      }
	    }
	    return resultStrs;
	  }
	
}
