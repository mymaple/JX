package com.jx.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");

	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");

	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 获取YYYY格式
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}

	/**
	 * 获取YYYYMMDD格式
	 * @return
	 */
	public static String getDays() {
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	 * @Title: compareDate
	 * @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	 * @param s
	 * @param e
	 * @return boolean
	 * @throws
	 * @author luguosui
	 */
	public static boolean compareDate(String s, String e) {
		if (fomatDate(s) == null || fomatDate(e) == null) {
			return false;
		}
		return fomatDate(s).getTime() >= fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}

	public static int getDiffYear(String startTime, String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long aa = 0;
			int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}

	/**
	 * <li>功能描述：时间相减得到天数
	 * @param beginDateStr
	 * @param endDateStr
	 * @return long
	 * @author Administrator
	 */
	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date beginDate = null;
		java.util.Date endDate = null;

		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		// System.out.println("相隔的天数="+day);

		return day;
	}

	/**
	 * 得到n天之后的日期
	 * @param days
	 * @return
	 */
	public static String getAfterDayDate(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdfd.format(date);

		return dateStr;
	}

	/**
	 * 得到n天之后是周几
	 * @param days
	 * @return
	 */
	public static String getAfterDayWeek(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String dateStr = sdf.format(date);

		return dateStr;
	}

	public static void main(String[] args) {
		System.out.println(getDays());
		System.out.println(getAfterDayWeek("3"));
	}
	
	private static final int[] dayArray = { 31, 28, 31, 30, 31, 30, 
		    31, 31, 30, 31, 30, 31 };

		 

		  public static String[] getCloseYearList(String baseYear, int before, int after, boolean inc)
		  {
		    int year = Integer.parseInt(baseYear);
		    if ((before < 0) || (after < 0)) {
		      throw new IllegalArgumentException(
		        "before or after year must be great than zero!");
		    }
		    String[] yearList = new String[before + after + 1];
		    if (inc) {
		      for (int i = 0; i <= before + after; i++)
		        yearList[i] = String.valueOf(year - before + i);
		    }
		    else {
		      for (int i = 0; i <= before + after; i++) {
		        yearList[i] = String.valueOf(year + after - i);
		      }
		    }
		    return yearList;
		  }

		  public static String[] getCurrentCloseYearList(int before, int after, boolean inc)
		  {
		    String year = getCurrentYear();
		    return getCloseYearList(year, before, after, inc);
		  }

		  public static Timestamp getSQLCurTimestamp()
		  {
		    return Timestamp.valueOf(getFormatedCurDateString("-"));
		  }

		  public static String getCurrentYear()
		  {
		    Calendar calendar = new GregorianCalendar();
		    String year = String.valueOf(calendar.get(1));
		    return year;
		  }

		  public static String getFormatedDateString(long time, String delimeter)
		  {
		    Calendar calendar = new GregorianCalendar();
		    calendar.setTimeInMillis(time);
		    return getFormatedDateString(calendar, delimeter);
		  }

		  public static String getFormatedDateString(Calendar calendar, String delimeter)
		  {
		    String year = String.valueOf(calendar.get(1));
		    String month = String.valueOf(calendar.get(2) + 1);
		    if (month.length() == 1) {
		      month = "0" + month;
		    }
		    String day = String.valueOf(calendar.get(5));
		    if (day.length() == 1) {
		      day = "0" + day;
		    }
		    String hour = String.valueOf(calendar.get(11));
		    if (hour.length() == 1) {
		      hour = "0" + hour;
		    }
		    String minute = String.valueOf(calendar.get(12));
		    if (minute.length() == 1) {
		      minute = "0" + minute;
		    }
		    String second = String.valueOf(calendar.get(13));

		    if (second.length() == 1) {
		      second = "0" + second;
		    }
		    String str = "";
		    str = year + delimeter + month + delimeter + day + " " + hour + ":" + 
		      minute + ":" + second;
		    return str;
		  }

		  public static String getFormatedCurDateString(String delimeter)
		  {
		    Calendar calendar = new GregorianCalendar();
		    return getFormatedDateString(calendar, delimeter);
		  }

		  public static String getFormatedDateString(Date date)
		  {
		    return new SimpleDateFormat("yyyy-MM-dd").format(date);
		  }

		  public static int getCurrentHour()
		  {
		    Calendar calendar = new GregorianCalendar();
		    return calendar.get(11);
		  }

		  public static final Date parseDate(String strPattern, String strDate)
		    throws ParseException
		  {
		    SimpleDateFormat df = new SimpleDateFormat(strPattern);
		    Date date = null;
		    date = df.parse(strDate);
		    return date;
		  }

		  public static Date parseDate(String strDate)
		    throws ParseException
		  {
		    Date aDate = parseDate("yyyy-MM-dd", strDate);
		    return aDate;
		  }

		  public static boolean isLeapYear(int year)
		  {
		    if (year % 400 == 0)
		      return true;
		    if (year % 4 == 0)
		    {
		      return year % 100 != 0;
		    }

		    return false;
		  }

		  public static boolean isLeapYear()
		  {
		    Calendar cal = Calendar.getInstance();
		    int year = cal.get(1);
		    return isLeapYear(year);
		  }

		  public static synchronized boolean isLeapYear(Date date)
		  {
		    GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
		    gc.setTime(date);
		    int year = gc.get(1);
		    return isLeapYear(year);
		  }

		  public static synchronized Date getFirstDayOfMonth(Date date)
		  {
		    GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
		    gc.setTime(date);
		    gc.set(5, 1);
		    return gc.getTime();
		  }

		  public static int getLastDayOfMonth(int month)
		  {
		    if ((month < 1) || (month > 12)) {
		      return -1;
		    }
		    int retn = 0;
		    if (month == 2) {
		      if (isLeapYear())
		        retn = 29;
		      else
		        retn = dayArray[(month - 1)];
		    }
		    else {
		      retn = dayArray[(month - 1)];
		    }
		    return retn;
		  }

		  public static Date getLastDayOfMonth(Date date)
		  {
		    GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
		    gc.setTime(date);
		    switch (gc.get(2)) {
		    case 0:
		      gc.set(5, 31);
		      break;
		    case 1:
		      gc.set(5, 28);
		      break;
		    case 2:
		      gc.set(5, 31);
		      break;
		    case 3:
		      gc.set(5, 30);
		      break;
		    case 4:
		      gc.set(5, 31);
		      break;
		    case 5:
		      gc.set(5, 30);
		      break;
		    case 6:
		      gc.set(5, 31);
		      break;
		    case 7:
		      gc.set(5, 31);
		      break;
		    case 8:
		      gc.set(5, 30);
		      break;
		    case 9:
		      gc.set(5, 31);
		      break;
		    case 10:
		      gc.set(5, 30);
		      break;
		    case 11:
		      gc.set(5, 31);
		    }

		    if ((gc.get(2) == 1) && 
		      (isLeapYear(gc.get(1)))) {
		      gc.set(5, 29);
		    }
		    return gc.getTime();
		  }

		  public static Date getNextMonth(Date date)
		  {
		    GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
		    gc.setTime(date);
		    gc.add(2, 1);
		    return gc.getTime();
		  }

		  public static Date getPreviousMonth(Date date)
		  {
		    GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
		    gc.setTime(date);
		    gc.add(2, -1);
		    return gc.getTime();
		  }

		  public static Date getFirstDayOfPreviousMonth(Date date)
		  {
		    GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
		    gc.setTime(date);
		    gc.setTime(getPreviousMonth(gc.getTime()));
		    gc.setTime(getFirstDayOfMonth(gc.getTime()));
		    return gc.getTime();
		  }

		  public static Date getLastDayOfPreviousMonth(Date date)
		  {
		    GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
		    gc.setTime(date);
		    gc.setTime(getPreviousMonth(gc.getTime()));
		    gc.setTime(getLastDayOfMonth(gc.getTime()));
		    return gc.getTime();
		  }

		  public static Date getFirstDayOfNextMonth(Date date)
		  {
		    GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
		    gc.setTime(date);
		    gc.setTime(getNextMonth(gc.getTime()));
		    gc.setTime(getFirstDayOfMonth(gc.getTime()));
		    return gc.getTime();
		  }

		  public static Date getLastDayOfNextMonth(Date date)
		  {
		    GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
		    gc.setTime(date);
		    gc.setTime(getNextMonth(gc.getTime()));
		    gc.setTime(getLastDayOfMonth(gc.getTime()));
		    return gc.getTime();
		  }

		  public static Date getNextDay(Date date)
		  {
		    GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
		    gc.setTime(date);
		    gc.add(5, 1);
		    return gc.getTime();
		  }

		  public static Date getPreviousDay(Date date)
		  {
		    GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
		    gc.setTime(date);
		    gc.add(5, -1);
		    return gc.getTime();
		  }

		  public static Date getNextDays(Date date, int days)
		  {
		    GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
		    gc.setTime(date);
		    gc.add(5, days);
		    return gc.getTime();
		  }

		  public static Date getPreviousDays(Date date, int days)
		  {
		    GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
		    gc.setTime(date);
		    gc.add(5, -days);
		    return gc.getTime();
		  }
	

}
