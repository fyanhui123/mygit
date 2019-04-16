package com.tapi.tcs.vc.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;


/**
 * 对日期进行操作的几个公共方法
 */
public final class DateUtils {
	// private static org.apache.commons.logging.Log log =
	// org.apache.commons.logging.LogFactory
	// .getLog(DateUtils.class);
	private static final String YEAR = "yyyy";
	private static final String MONTH = "MM";
	private static final String DAY = "dd";
	private static String defaultDatePattern = "yyyy-MM-dd"; // 日期格式
	private static final String QUOTE = "'";
	private static final String HQL_AND = " and ";
	// private static final String MESSAGE_BUNDLE_KEY = "date.default_format";
	// //日期格式默认key
	private static ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<SimpleDateFormat>(); // 日期格式化类

/*  
    static {
//        try {
//            Locale locale = LocaleContextHolder.getLocale();
//            defaultDatePattern = ResourceBundle.getBundle(MESSAGE_BUNDLE_KEY, locale).getString(MESSAGE_BUNDLE_KEY);
//        } catch (MissingResourceException e) {
//        	log.error(e.getMessage());
//        }
//		sdf = new SimpleDateFormat(defaultDatePattern);
    }*/
    
    /** 
     * 构造方法
     */
    protected DateUtils() {
    	//nothing
    	super();
    }
    
    /**
	 * 根据传入日期，获取该日期所在年的第一天
	 * 
	 * @return begin day of year
	 */
	public Date getBeginDayOfYear() {
		return getBeginDayOfYear(new Date());
	}
    /**
	 * 根据传入日期，获取该日期所在年的第一天
	 * 
	 * @param date 日期
	 * @return begin day of year
	 */
	public static Date getBeginDayOfYear(Date date) {
		Date d = date == null ? new Date() : date;
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(d);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		return calendar.getTime();
	}
	
	/**
	 * 
	 * @param dateValue 输入参数 按一下格式 yyyy-MM
	 * @return 一个月的第一天
	 */
	public static Date getFirstDayOfMonth(String dateValue) {
		dateValue = dateValue + "-01";
		return parse(dateValue);
	}
	
	
	
	

	/**
	 * 
	 * @param dateValue 输入参数 按一下格式 yyyy-MM
	 * @return 一个月的第一天
	 */
	public  static Date getLastDayOfMonth(String dateValue) {
		Date fitstDayOfMonth = getFirstDayOfMonth(dateValue);
		Calendar cTools = Calendar.getInstance();
		cTools.setTime(fitstDayOfMonth);
		cTools.set(Calendar.MONTH, cTools.get(Calendar.MONTH) + 1);
		cTools.set(Calendar.DAY_OF_YEAR, cTools.get(Calendar.DAY_OF_YEAR) - 1);
		return cTools.getTime();
	}
	
	
	/**
	 * 根据传入日期，获取该日期所在年的最后一天
	 * 
	 * @return end day of year
	 */
	public Date getEndDayOfYear() {
		return getEndDayOfYear(new Date());
	}
	/**
	 * 根据传入日期，获取该日期所在年的最后一天
	 * 
	 * @param date 日期
	 * @return end day of year
	 */
	public static Date getEndDayOfYear(Date date) {
		Date d = date == null ? new Date() : date;
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(d);
		calendar.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar.set(Calendar.DAY_OF_MONTH, 31);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		return calendar.getTime();
	}
    
	/**
	 * 形成一个日期区间的查询hsql
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param dateField
	 *            字段名称
	 * @return value
	 */
	public static String getSqlDate(Date startDate, Date endDate,
			String dateField) {

		String value = "";
		if (startDate != null) {
			value += HQL_AND + getCharField(dateField) + " >= '"
					+ format(startDate) + QUOTE;
		}
		if (endDate != null) {
			value += HQL_AND + getCharField(dateField) + " <= '"
					+ format(endDate) + QUOTE;
		}
		return value;
	}

	/**
	 * 形成一个日期查询hsql
	 * 
	 * @param date
	 *            日期
	 * @param dateField
	 *            字段名
	 * @return value
	 */
	public static String getSqlDate(Date date, String dateField) {

		String value = "";
		if (date != null) {
			value += HQL_AND + getCharField(dateField) + " = '" + format(date)
					+ QUOTE;
		}

		return value;
	}

	/**
	 * 形成一个日期查询hsql
	 * 
	 * @param date
	 *            日期
	 * @param dateField
	 *            字段名
	 * @return value
	 */
	public static String getSqlDate(String date, String dateField) {

		String value = "";
		if (date != null) {
			value += HQL_AND + getCharField(dateField) + " = '" + date + QUOTE;
		}

		return value;
	}

	/**
	 * 形成to_char的字段显示
	 * 
	 * @param field
	 *            字段名称
	 * @return charField
	 */
	public static String getCharField(String field) {
		String charField = "to_char(" + field + ",'yyyy-MM-dd')";
		return charField;
	}

	/**
	 * 形成sql中的date显示
	 * 
	 * @param date
	 *            日期
	 * @return sqlDate
	 */
	public static String getSqlDate(String date) {
		String value = "to_date('" + date + "','" + defaultDatePattern + "')";

		return value;
	}

	/**
	 * 
	 * @param date
	 *            日期
	 * @return 日期中的月
	 */
	public static String getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return String.valueOf(calendar.get(Calendar.MONTH));
	}

	/**
	 * 
	 * @param date
	 *            日期
	 * @return 日期中的日
	 */
	public static String getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 获得默认的 date pattern
	 * 
	 * @return string
	 */
	public static String getDatePattern() {
		return defaultDatePattern;
	}

	/**
	 * 获得默认得 date format
	 * 
	 * @return SimpleDateFormat
	 */
	public static SimpleDateFormat getDateFormat() {
		SimpleDateFormat threadSDF = sdf.get();
		if (threadSDF == null) {
			threadSDF = new SimpleDateFormat(defaultDatePattern);
			sdf.set(threadSDF);
		}
		return threadSDF;
	}

	/**
	 * 返回预设Format的当前日期字符串
	 * 
	 * @return string
	 */
	public static String getNow() {
		Date today = new Date();
		return format(today);
	}

	/**
	 * 返回系统日期
	 * 
	 * @return date
	 */
	public static Date now() {
		return new Date();
	}

	/**
	 * 去掉时间
	 * 
	 * @param date
	 *            date
	 * @return date
	 */
	public static Date reset(Date date) {
		return parse(format(date));
	}

	/**
	 * 使用预设Format格式化Date成字符串
	 * 
	 * @param date
	 *            日期
	 * @return string
	 */
	public static String format(final Date date) {
		return date == null ? "" : getDateFormat().format(date);
	}

	/**
	 * 使用参数Format格式化Date成字符串
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            格式
	 * @return string
	 */
	public static String format(final Date date, String pattern) {
		return date == null ? "" : new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 使用预设格式将字符串转为Date
	 * 
	 * @param strDate
	 *            日期(字符串格式）
	 * @return date
	 */
	public static Date parse(final String strDate) {
		try {
			return StringUtils.isBlank(strDate) ? null : getDateFormat().parse(
					strDate);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 使用参数Format将字符串转为Date
	 * 
	 * @param strDate
	 *            日期(字符串格式）
	 * @param pattern
	 *            日期格式
	 * @return date
	 * @throws ParseException
	 *             解析异常
	 */
	public static Date parse(final String strDate, final String pattern) {
		try {
			return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(
					pattern).parse(strDate);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * 在日期上增加数个整年
	 * 
	 * @param date
	 *            日期
	 * @param n
	 *            多少年
	 * @return date
	 */
	public static Date addYear(final Date date, final int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, n);
		return cal.getTime();
	}

	/**
	 * 在日期上增加数个整月
	 * 
	 * @param date
	 *            日期
	 * @param n
	 *            多少月
	 * @return date
	 */
	public static Date addMonth(final Date date, final int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, n);
		return calendar.getTime();
	}

	/**
	 * 在日期上增加数个整日
	 * 
	 * @param date
	 *            日期
	 * @param n
	 *            多少天
	 * @return date
	 */
	public static Date addDay(final Date date, final int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, n);
		return calendar.getTime();
	}

	/**
	 * 校验是否是当月的第一天
	 * 
	 * @param date
	 *            日期
	 * @return true or false
	 */
	public static boolean isFirstDayOfMonth(final Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
			return true;
		}
		return false;
	}

	/**
	 * 校验是否是当月的最好一天
	 * 
	 * @param date
	 *            日期
	 * @return true or false
	 */
	public static boolean isLastDayOfMonth(final Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
			return true;
		}
		return false;
	}

	/**
	 * 获取当月的第一天
	 * 
	 * @param date
	 *            日期
	 * @return date
	 * @throws ParseException
	 *             解析异常
	 */
	public static Date getFirstDayOfMonth(final Date date)
			throws ParseException {
		String str = format(date);
		str = str.substring(0, str.length() - 2) + "01";
		Date result = parse(str);
		return result;
	}

	/**
	 * 获取当月的最后一天
	 * 
	 * @param date
	 *            日期
	 * @return date
	 */
	public static Date getLastDayOfMonth(final Date date) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		/**
		 * 循环到下个月的月初
		 */
		while (calendar.get(Calendar.DAY_OF_MONTH) != 1) {
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		}
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		Date result = calendar.getTime();
		return result;
	}

	/**
	 * 获取两个日期之间的差值
	 * 
	 * @param date1
	 *            日期1
	 * @param date2
	 *            日期2
	 * @return 返回两个日期相差的天数
	 */
	public static int getDifference(final Date date1, final Date date2) {
		int i = compareDate(date1, date2);
		i = Math.abs(i);
		return i;
	}

	/**
	 * @param dt1
	 *            比较日期1
	 * @param dt2
	 *            比较日期2
	 * @return 0:相等 1:大于 -1:小于
	 */
	public static int compare(final Date dt1, final Date dt2) {
		Assert.notNull(dt1);
		Assert.notNull(dt2);
		return dt1.compareTo(dt2);
	}

	/**
	 * 比较两个日期的大小
	 * 
	 * @param date1
	 *            日期1
	 * @param date2
	 *            日期2
	 * @return 如果date1<date2 则return <0; 如果大于date2>date1,则return > 0;
	 *         如果相等则return = 0;
	 */
	public static int compareDate(final Date date1, final Date date2) {
		Assert.notNull(date1);
		Assert.notNull(date2);

/*		String sDate1 = sdf.format(date1);
		String sDate2 = sdf.format(date2);

		Date dt1 = null;
		Date dt2 = null;
		try {
			dt1 = sdf.parse(sDate1);
			dt2 = sdf.parse(sDate2);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		long d1 = dt1.getTime();
		long d2 = dt2.getTime();*/
		long d1 = date1.getTime();
		long d2 = date2.getTime();
		long l = d1 - d2;
		long times = 1000 * 60 * 60 * 24;
		double i = l >= 0 ? 0.5 : -0.5;
		int betweenDays = (int) (l / times + i);

		return betweenDays;
	}

	/**
	 * 功能: 判断是否是闰年.
	 * 
	 * @param year
	 *            给出的 1582 年以后的年份.
	 * @return TRUE 是闰年, FALSE 不是.
	 */
	public static boolean isLeapYear(int year) {

		/*
		 * 能被100整除, 不能被400整除的年份, 不是闰年. 也能被400整除的年份, 是闰年.
		 */
		boolean flag = false;
		if ((year % 100) == 0) {
			if ((year % 400) == 0) {
				flag = true;
			}
		} else if ((year % 4) == 0) {
			/* 不能被100整除, 能被4整除的年份是闰年. */
			flag = true;
		}

		return flag;
	}

	/**
	 * 获取年份信息
	 * 
	 * @param date
	 *            日期
	 * @return yyyy
	 */
	public static String getYear(Date date) {
		return getYear(format(date));
	}

	/**
	 * 获取年
	 * 
	 * @param date
	 *            日期
	 * @return year
	 */
	public static String getYear(String date) {
		String format = getDatePattern();
		int pos = format.indexOf(YEAR);
		String year = date.substring(pos, pos + YEAR.length());
		return year;
	}

	/**
	 * 获取月
	 * 
	 * @param date
	 *            日期
	 * @return month
	 */
	public static String getMonth(String date) {
		String format = getDatePattern();
		int pos = format.indexOf(MONTH);
		String month = date.substring(pos, pos + MONTH.length());
		return month;
	}

	/**
	 * 获取日
	 * 
	 * @param date
	 *            日期
	 * @return day
	 */
	public static String getDay(String date) {
		String format = getDatePattern();
		int pos = format.indexOf(DAY);
		String day = date.substring(pos, pos + DAY.length());
		return day;
	}

	/**
	 * 把毫秒时间转换成对应的日期
	 * 
	 * @param ms
	 *            毫秒
	 * @return 日期
	 */
	public static String generateDate(String ms) {
		Date date = null;
		if (ms != null && StringUtils.isNumeric(ms)) {
			date = new Date(Long.valueOf(ms));
			return format(date);
		} else {
			return getNow();
		}
	}

	/**
	 * 把毫秒时间转换成对应的日期
	 * 
	 * @param ms
	 *            毫秒
	 * @return 日期
	 */
	public static Date parseDate(String ms) {
		Date date = null;
		if (ms != null && StringUtils.isNumeric(ms)) {
			date = new Date(Long.valueOf(ms));
			return date;
		} else {
			return now();
		}
	}

	/**
	 * 
	 * @param dt1
	 *            日期1
	 * @param dt2
	 *            日期2
	 * @return 相差月份数
	 */
	public static int getMonthDifference(Date dt1, Date dt2) {
		Calendar dt1c = Calendar.getInstance();
		dt1c.setTime(dt1);
		Calendar dt2c = Calendar.getInstance();
		dt2c.setTime(dt2);
		return (dt1c.get(Calendar.YEAR) - dt2c.get(Calendar.YEAR)) * 12
				+ (dt1c.get(Calendar.MONTH) - dt2c.get(Calendar.MONTH));
	}
	
	/**
	 * 
	 * @param date 日期
	 * @return 日历
	 */
	public static Calendar getCalendar(Date date) {
		Calendar calendar = null;
		if (date != null) {
			calendar = Calendar.getInstance();
			calendar.setTime(date);
		}
		return calendar;
	}

	public static Date getNow(String pattern){
		if(StringUtils.isEmpty(pattern)){
			pattern = defaultDatePattern;
		}
		return parse(format(now(), pattern));
	}
}
