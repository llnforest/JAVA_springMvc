package com.common.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

/**  
 * 时间工具类
 * @author:wangzhen
 * @version:V1.0
 * 2018年8月22日  
 */
public class DateUtil {
	
	/**
	 * 默认时间格式
	 */
    static String simpleDateFormat = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * 获取当前时间字符串（默认时间格式）
     * 2018年8月22日
     * @return
     * author:wangzhen
     */
	public static String getNowDateStr(){
		 return  DateFormatUtils.format(new Date(), simpleDateFormat);
	}
	/**
	 * 获取当前时间字符串（根据传入的格式）
	 * 2018年8月22日
	 * @param pattern
	 * @return
	 * author:wangzhen
	 */
	public static String getNowDateStr(String pattern){
		if (pattern == null){
			pattern = simpleDateFormat;
        }
		 return  DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 获取传入日期的字符串（默认时间格式）
	 * 2018年8月22日
	 * @param date
	 * @param pattern
	 * @return
	 * author:wangzhen
	 */
	public static String getDateStr(Date date){
		if (date == null){
            return "";
        }
		return  DateFormatUtils.format(date, simpleDateFormat);
	}
	
	
	/**
	 * 获取传入日期的字符串（根据传入格式）
	 * 2018年8月22日
	 * @param date
	 * @param pattern
	 * @return
	 * author:wangzhen
	 */
	public static String getDateStr(Date date,String pattern){
		if (date == null){
            return "";
        }
		return  DateFormatUtils.format(date, pattern);
	}
	
	/**
	 * 将传入格式字符串按照默认格式转为日期
	 * 2018年8月22日
	 * @param pattern
	 * @return
	 * author:wangzhen
	 */
	public static Date getDate(String dateStr){
		 SimpleDateFormat sdf = new SimpleDateFormat(simpleDateFormat);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	/**
	 * 将传入格式字符串按照指定格式转为日期
	 * 2018年8月22日
	 * @param pattern
	 * @return
	 * author:wangzhen
	 */
	public static Date getDate(String dateStr,String pattern){
		 SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
	}

	/**
	 * 获取多长时间之后的时间
	 * 2019年2月22日
	 * @param date
	 * @param seconds
	 * @return
	 * @author:Lynn
	 */
	public static Date addSeconds(Date date,int seconds){
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
	}
	
	public static Date getNextDate(Date date,int num){
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day + (num));
        return calendar.getTime();
	}
	
	public static void main(String[] args) {
		DateUtil dateUtil = new DateUtil();
		System.out.println(dateUtil.getNowDateStr());
		System.out.println(dateUtil.getNowDateStr("yyyy-MM-dd"));
		System.out.println(dateUtil.getDateStr(new Date()));
		System.out.println(dateUtil.getDateStr(new Date(),"yyyy-MM-dd HH/mm/ss"));
		System.out.println(dateUtil.getDate("2018-08-22 23:37:12","yyyy-MM-dd HH:mm:ss"));
		System.out.println(new Date());
	}
}
