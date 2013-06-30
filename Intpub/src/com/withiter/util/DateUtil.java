/*
 * Created on 2012-4-20
 *
 * DateUtil.java
 *
 * Copyright (C) 2012 by Withiter Software & Technology Services (Shanghai) Limited.
 * All rights reserved. Withiter Software & Technology Services (Shanghai) Limited 
 * claims copyright in this computer program as an unpublished work. Claim of copyright 
 * does not imply waiver of other rights.
 *
 * NOTICE OF PROPRIETARY RIGHTS
 *
 * This program is a confidential trade secret and the property of 
 * Withiter Software & Technology Services (Shanghai) Limited. Use, examination, 
 * reproduction, disassembly, decompiling, transfer and/or disclosure to others of 
 * all or any part of this software program are strictly prohibited except by express 
 * written agreement with Withiter Software & Technology Services (Shanghai) Limited.
 */
/*
 * ---------------------------------------------------------------------------------
 * Modification History
 * Date               Author                     Version     Description
 * 2012-4-20       Administrator                    1.0         New
 * ---------------------------------------------------------------------------------
 */
package com.withiter.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
  
	/**
	 * 得到当天日期
	 * @return
	 */
	public static String getTodayDate() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}
  
	/**
	 * 得到当天具体时间
	 * @return
	 */
	public static String getTodayTime() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
	/**
	 * @param pattern 格式符
	 * @return 根据pattern格式化当天时间
	 */
	public static String getToday(String pattern) {
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
		return sdf.format(new Date());
	}
	
	/**
	 * @param date 时间
	 * @param pattern 格式符
	 * @return 得到时间格式化后字符串
	 */
	public static String getDate(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	/**
	 * @param strDate 时间
	 * @param pattern 格式符
	 * @return  得到时间字符串格式化后的Date类型
	 */
	public static Date stringToDate(String strDate, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}
	
	/**
	 * @return 得到当前年度
	 */
	public static String getYear() {
		Calendar calendar = Calendar.getInstance();

		return String.valueOf(calendar.get(Calendar.YEAR));
	}
	
	/**
	 * @return 得到当前月份
	 */
	public static String getMonth() {
		Calendar calendar = Calendar.getInstance();

		return String.valueOf(calendar.get(Calendar.MONTH)+1);
	}
	
	/**
	 * @return 得到当前日期
	 */
	public static String getDay() {
		Calendar calendar = Calendar.getInstance();
		
		return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
	}
	
}
