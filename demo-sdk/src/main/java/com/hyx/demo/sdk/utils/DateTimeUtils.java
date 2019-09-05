package com.hyx.demo.sdk.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @ClassName:DateTimeUtils <br/> 
 * @Reason:   TODO ADD REASON. <br/> 
 * @Date:     2019年9月5日 上午10:54:39 <br/> 
 * @author   huangyaxiong 
 * Copyright (c) 2019, hyx_java2012@163.com All Rights Reserved.       
 */
public class DateTimeUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeUtils.class);
	/** 定义常量 **/
	public static final String DATE_FULL_MIN_STR = "yyyy-MM-dd HH:mm";
	public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_LONG_STR = "yyyy-MM-dd kk:mm:ss.SSS";
	public static final String DATE_SMALL_STR = "yyyy-MM-dd";
	public static final String DATE_TIME = "HH:mm:ss";
	public static final String DATE_TIME_STR = "HHmmss";
	public static final String DATE_HOUR_STR = "yyyyMMddHH";
	public static final String DATE_KEY_STR = "yyMMddHHmmss";
	public static final String DATE_All_KEY_STR = "yyyyMMddHHmmss";
	public static final String DATE_LONG_KEY_STR = "yyMMddHHmmssSSS";
	public static final String DATE_SMALL_KEY_STR = "yyyyMMdd";
	public static final String DATE_YYMMDD_KEY_STR = "yyMMdd";
	public static final String DATE_TIME_MILLISECOND_STR = "HHmmSSSSS";
	public static final String DAET_SOURCE_TIME = "00:00-03:00";
    private static final DateFormat tidyDateFormat = new SimpleDateFormat("yyyyMMdd");
    private static final DateFormat tidyTimeFormat = new SimpleDateFormat("HHmmss");
    private static final DateFormat shortDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat yearMonthFormat = new SimpleDateFormat("yyyy-MM");
    private static DateFormat briefDateFormat = new SimpleDateFormat("MM-dd HH:mm");
    private static final DateFormat middleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final DateFormat longDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final DateFormat compactDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    public static String getCurrDt() {
        return formatTidyDate(new Date());
    }

    public static String getCurrTm() {
        return formatTidyTime(new Date());
    }

    public static String getCurrDtTm() {
        return formatCompactDate(new Date());
    }

    public static String formatTidyDate(Date date) {
        if (date == null)
            return "";
        return tidyDateFormat.format(date);
    }

    public static String formatTidyTime(Date date) {
        if (date == null)
            return "";
        return tidyTimeFormat.format(date);
    }

    public static Date parseTidyDate(String value) throws ParseException {
        if (value == null)
            return null;
        return tidyDateFormat.parse(value);
    }

    public static Date parseTidyDate(String value, Date defaultValue) {
        try {
            if (value == null)
                return defaultValue;
            return tidyDateFormat.parse(value);
        } catch (ParseException e) {
            LOGGER.debug("incorrect tidy date, default used.", e);
        }
        return defaultValue;
    }

    public static Date parseTidyDateLast(String value, Date defaultValue) {
        try {
            if (value == null)
                return defaultValue;
            return new Date(tidyDateFormat.parse(value).getTime() + 86399000L);
        } catch (ParseException e) {
            LOGGER.debug("incorrect tidy date, default used.", e);
        }
        return defaultValue;
    }

    public static String formatShortDate(Date date) {
        if (date == null)
            return "";
        return shortDateFormat.format(date);
    }

    public static String formatYearMonthDate(Date date) {
        if (date == null)
            return "";
        return yearMonthFormat.format(date);
    }

    public static Date parseShortDate(String value) throws ParseException {
        if (value == null)
            return null;
        return shortDateFormat.parse(value);
    }

    public static Date parseShortDate(String value, Date defaultValue) {
        try {
            if (value == null)
                return defaultValue;
            return shortDateFormat.parse(value);
        } catch (ParseException e) {
            LOGGER.debug("incorrect short date, default used.", e);
        }
        return defaultValue;
    }

    public static Date parseShort59Date(String value) throws ParseException {
        return new Date(parseShortDate(value).getTime() + 86399999L);
    }

    public static Date parseShort59Date(Date tempDate) {
        return new Date(parseShortDate(formatShortDate(tempDate), tempDate).getTime() + 86399999L);
    }

    public static String formatBriefDate(Date date) {
        if (date == null)
            return "";
        return briefDateFormat.format(date);
    }

    public static String formatMiddleDate(Date date) {
        if (date == null)
            return "";
        return middleDateFormat.format(date);
    }

    public static Date parseMiddleDate(String value) throws ParseException {
        if (value == null)
            return null;
        return middleDateFormat.parse(value);
    }

    public static Date parseMiddleDate(String value, Date defaultValue) {
        try {
            if (value == null) {
                return defaultValue;
            }
            return middleDateFormat.parse(value);
        } catch (ParseException e) {
            LOGGER.debug("incorrect middle date, default used.", e);
        }
        return defaultValue;
    }

    public static String formatLongDate(Date date) {
        if (date == null)
            return "";
        return longDateFormat.format(date);
    }

    public static Date parseLongDate(String value) throws ParseException {
        if (value == null)
            return null;
        return longDateFormat.parse(value);
    }

    public static Date parseLongDate(String value, Date defaultValue) {
        try {
            if (value == null)
                return defaultValue;
            return longDateFormat.parse(value);
        } catch (ParseException e) {
            LOGGER.debug("incorrect long date, default used.", e);
        }
        return defaultValue;
    }

    public static String formatCompactDate(Date date) {
        if (date == null)
            return "";
        return compactDateFormat.format(date);
    }

    public static String formatHMSCompactDate(Date date) {
        if (date == null)
            return "";
        return compactDateFormat.format(date).substring(8, 14);
    }

    public static String formatDHMSCompactDate(Date date) {
        if (date == null)
            return "";
        return compactDateFormat.format(date).substring(6, 14);
    }

    public static String formatMDHMSCompactDate(Date date) {
        if (date == null)
            return "";
        return compactDateFormat.format(date).substring(4, 14);
    }

    public static String formatMDCompactDate(Date date) {
        if (date == null)
            return "";
        return compactDateFormat.format(date).substring(4, 8);
    }

    public static Date parseCompactDate(String value) throws ParseException {
        if (value == null)
            return null;
        return compactDateFormat.parse(value);
    }

    public static Date parseCompactDate(String value, Date defaultValue) {
        try {
            if (value == null)
                return defaultValue;
            return compactDateFormat.parse(value);
        } catch (ParseException e) {
            LOGGER.debug("incorrect compact date, default used.", e);
        }
        return defaultValue;
    }
    
    /**
     * 在当前日期时间增加需要的毫秒数，并按照yyyyMMddHHmmss格式返回新的日期时间
     * @param milliSecond 需要增加的毫秒数
     * @return
     */
    public static String getAddedDate(long milliSecond) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        date.setTime(date.getTime() + milliSecond);
        return sdf.format(date);
    }
    
    /**
     * 获取当天日期的前一天
     * @return
     */
    public static String getBeforeOrAfterDay(int dayNum) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        calendar.add(Calendar.DAY_OF_YEAR, dayNum);
        Date date = calendar.getTime();
        return sdf.format(date);
    }
    
    /**
     * 比较2个日期的大小(yyyyMMddhhmmss)
     * @param date1
     * @param date2
     * @return
     */
    public static int compare_time(String date1,String date2){
        DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
         try {
             Date dt1 = df.parse(date1);
             Date dt2 = df.parse(date2);
             if (dt1.getTime() > dt2.getTime()) {
                 return 1;
             } else if (dt1.getTime() < dt2.getTime()) {
                 return -1;
             } else {
                 return 0;
             }
         } catch (Exception exception) {
             exception.printStackTrace();
         }
        return 0;
        
    }
    
    /**
     * 获取指定日期(yyyyMMdd格式)的前N天或者后N天
     * @return
     */
    public static String getBeforeOrAfterDay(String currDate,int dayNum) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse(currDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.DAY_OF_YEAR, dayNum);
        Date date = calendar.getTime();
        return sdf.format(date);
    }
    
    /**
     * 获取当前时间距离一天结束的剩余秒数
     * @param currentDate
     * @return
     */
    public static long getRemainSecondsOneDay(Date currentDate) {
        LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault()).plusDays(1).withHour(0).withMinute(0)
                .withSecond(0).withNano(0);
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault());
        long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
        return  seconds;
    }
	
	/**
	 * 时间转字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            格式
	 * @return
	 */
	public static String date2Str(Date date, String format) {
		if (null != date && !"".equals(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}
		return null;
	}
	
	/**
	 * 指定指定日期字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date str2Date(String date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		try {
			return df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
 