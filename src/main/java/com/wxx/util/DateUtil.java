package com.wxx.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.wxx.constant.StaticConstant;

public class DateUtil {
	
	private static Logger logger = Logger.getLogger(DateUtil.class);
	/**
	 * 添加天
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addDay(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, day);
		return cal.getTime();
	}

	/**
	 * 添加年
	 * 
	 * @param date
	 * @param year
	 * @return
	 */
	public static Date addYear(Date date, int year) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, year);
		return cal.getTime();
	}

	/**
	 * 添加秒
	 * 
	 * @param date
	 * @param year
	 * @return
	 */
	public static Date addSecond(Date date, int second) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, second);
		return cal.getTime();
	}

	public static boolean afterCurrDate(Date date) {
		return DateUtil.currDate().compareTo(date) > 0 ? true : false;
	}

	public static boolean afterOrEqualsCurrDate(Date date) {
		return DateUtil.afterCurrDate(date) || DateUtil.equalsCurrDate(date);
	}

	public static boolean beforeCurrDate(Date date) {
		return DateUtil.currDate().compareTo(date) < 0 ? true : false;
	}

	public static boolean beforeOrEqualsCurrDate(Date date) {
		return DateUtil.beforeCurrDate(date) || DateUtil.equalsCurrDate(date);
	}

	public static Calendar currCalendarDate() {
		return Calendar.getInstance();
	}

	public static Date currDate() {
		return Calendar.getInstance().getTime();
	}

	public static String currDate(String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(DateUtil.currDate());
	}

	/**
	 * 格式化日期(yyyy-mm-dd)
	 * 
	 * @param date
	 * @return
	 */
	public static String dateFormat(Date date) {
		if ((null == date) || date.toString().equals(StringUtils.EMPTY)) {
			return StringUtils.EMPTY;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	/**
	 * 方法说明 自定义日期格式
	 * 
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String dateFormat(Date date, String pattern) {
		if ((null == date) || date.toString().equals(StringUtils.EMPTY)) {
			return StringUtils.EMPTY;
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * 14位格式化日期
	 * 
	 * @return String
	 */
	public static String dateFormat14(Date date) {
		if ((null == date) || date.toString().equals(StringUtils.EMPTY)) {
			return StringUtils.EMPTY;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(date);
	}

	/**
	 * 8位格式化日期(标准日期)
	 * 
	 * @return String
	 */
	public static String dateFormat8(Date date) {
		if ((null == date) || date.toString().equals(StringUtils.EMPTY)) {
			return StringUtils.leftPad(StringUtils.EMPTY, StaticConstant.EIGHT,
					"0");
		}
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
		return format.format(date);
	}

	public static String dateFormat8yyyyMMdd(Date date) {
		if ((null == date) || date.toString().equals(StringUtils.EMPTY)) {
			return StringUtils.leftPad(StringUtils.EMPTY, StaticConstant.EIGHT,
					"0");
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(date);
	}

	public static String dateFormateYYYYMMDD(Date date) {
		return DateUtil.dateFormat(date, "yyyy-mm-dd");
	}

	/**
	 * 14位格式化日期
	 * 
	 * @return String
	 */
	public static Date dateParse(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			if (logger.isInfoEnabled()) {
				logger.error(e.getMessage(), e);
			}
			return DateUtil.currDate();
		}
	}

	public static boolean equalsCurrDate(Date date) {
		Calendar tmp = DateUtil.currCalendarDate();
		tmp.setTime(date);
		Calendar currCalendarDate = DateUtil.currCalendarDate();
		return (currCalendarDate.get(Calendar.YEAR) == tmp.get(Calendar.YEAR))
				&& (currCalendarDate.get(Calendar.MONTH) == tmp
						.get(Calendar.MONTH))
				&& (currCalendarDate.get(Calendar.DATE) == tmp
						.get(Calendar.DATE));
	}

	public static Date formatDate(String date, String pattern)
			throws ParseException {
		if (StringUtils.isEmpty(date)) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.parse(date);
	}

	public static Date formatDateyyyyMMdd(String date) throws ParseException {
		return DateUtil.formatDate(date, "yyyyMMdd");
	}

	/** 获取当天最大值 */
	public static Date maxCurrDay(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date
					+ " 23:59:59");
		} catch (ParseException e) {
			if (logger.isInfoEnabled()) {
				logger.error(e.getMessage(), e);
			}
			return DateUtil.currDate();
		}
	}

	/**
	 * 格式化日期
	 * 
	 * @return String
	 */
	public static String reverseDate(String date) {
		if (null == date) {
			return StringUtils.EMPTY;
		}
		String d = date.trim();
		if (d.length() != StaticConstant.EIGHT) {
			return StringUtils.EMPTY;
		}
		return d.substring(StaticConstant.SIX, StaticConstant.EIGHT)
				+ d.substring(StaticConstant.FOUR, StaticConstant.SIX)
				+ d.substring(0, StaticConstant.FOUR);
	}

	public static String timeFormat6(Date date) {
		if ((null == date) || date.toString().equals(StringUtils.EMPTY)) {
			return StringUtils.leftPad(StringUtils.EMPTY, StaticConstant.EIGHT,
					"0");
		}
		SimpleDateFormat format = new SimpleDateFormat("HHmmss");
		return format.format(date);
	}

	public static String getCurrentDate() {
		Date currentDate = Calendar.getInstance().getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmsssss");
		return format.format(currentDate);
	}

}
