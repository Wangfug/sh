package com.lte.admin.common.utils;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 */
public class ParseDate {
	static Logger log = Logger.getLogger(ParseDate.class);
	/**
	 * 转换短日期格式的字符串为日期.
	 * 
	 * @param s
	 *            yyyy-MM-dd
	 * @return Date
	 */
	public static Date fromShortFormat(final String s) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = sdf.parse(s);
			return d;
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 转换全日期格式的字符串为日期.
	 * 
	 * @param s
	 *            yyyy-MM-dd HH:mm:ss
	 * @return Date
	 */
	public static Date fromFullFormat(final String s) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d = sdf.parse(s);
			return d;
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Date fromFullFormatst(final String s) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date d = sdf.parse(s);
			return d;
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 转换短日期格式(年月)的字符串为日期.
	 * 
	 * @param s
	 *            yyyy-MM
	 * @return Date
	 */
	public static Date fromYearMonthFormat(final String s) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		try {
			Date d = sdf.parse(s);
			return d;
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 转换短格式日期
	 * 
	 * @param String
	 *            yyyy-MM-dd
	 * @return Date
	 */
	public static String parseShortFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	public static String parseFormatStart(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00");
		return sdf.format(date);
	}
	public static String parseFormatEnd(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 23:59");
		return sdf.format(date);
	}
	/**
	 * 转换短格式日期年
	 * 
	 * @param String
	 *            yyyy
	 * @return Date
	 */
	public static String parseShortYearFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(date);
	}
	/**
	 * 转换短格式日期月
	 * 
	 * @param String
	 *            MM
	 * @return Date
	 */
	public static String parseShortMonthFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		return sdf.format(date);
	}
	/**
	 * 转换短格式日期年月
	 * 
	 * @param String
	 *            yyyy-MM
	 * @return Date
	 */
	public static String parseYearMonthFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.format(date);
	}
	/**
	 * 
	 * @param date
	 * yyyy-MM-dd HH:mm
	 * @return
	 */
	public static String parseShortsFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(date);
	}
	/**
	 * 
	 * @param date
	 * yyyy年-MM月-dd日
	 * @return
	 */
	public static String parseShortFormat2(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年-MM月-dd日");
		return sdf.format(date);
	}

	/**
	 * 转换全格式日期
	 * 
	 * @param Date
	 * @return String yyyy-MM-dd HH:mm:ss
	 */
	public static String parseFullFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	public static String parseFullFormatS(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
		return sdf.format(date);
	}
	public static String parseFullFormatH(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		return sdf.format(date);
	}

	/**
	 * 转换为数字 格式（20100505143757）
	 * 
	 * @param date
	 * @return
	 */
	public static Long parseWithNum(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return new Long(sdf.format(date));
	}
	public static Long parseWithNumhh(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		return new Long(sdf.format(date));
	}

	/**
	 * 获取时间差
	 * 
	 * @param old
	 * @param now
	 * @return
	 */
	public static Long compareWithDay(Date old, Date now) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return new Long(sdf.format(now)) - new Long(sdf.format(old));
	}
	
	/**
	 * 获取当前星期几
	 * @param nowDate 今天日期 yyyy-MM-dd
	 * @return
	 */
	public static String getWeekDay(String nowDate){
		Date date= new Date();
        SimpleDateFormat d= new SimpleDateFormat("yyyy-MM-dd");
        try { 
            date= d.parse(nowDate); //自己定义的时间　或者可以接string 参数 
            SimpleDateFormat liuzm = new SimpleDateFormat("E");
            String mydate=liuzm.format(date);//
            return mydate;
        } catch (Exception e) {
            log.error(e.getMessage()); 
        } 
        return null;
	}
	
	public static boolean stringIsNull(String param)
    {
        return (null == param) || ("".equals(param.trim()));
    }
	
	/**
	 * 根据日期获取日期的中国式描述
	 * 
	 * @author WeiZhongJun Dec 7, 2010 5:39:48 PM
	 * @param paramDate
	 *            参数日期
	 * @return String
	 */
	public static String getDateDescibe(Date paramDate) {
		try {
			Date currentDate = new Date();

			// 当前时间和参数时间的毫秒差
			Long interval = currentDate.getTime() - paramDate.getTime();

			if (interval < 0) {
				return "1秒前";
			}

			// 参数时间为多少秒
			Long seconds = interval / 1000;
			// 参数时间为多少分钟
			Long minutes = interval / 60000;
			// // 参数时间为多少小时
			// Long hours = interval / 3600000;

			// 当前时间的字符串形式
			String nowDateStr = ParseDate.parseFullFormat(currentDate);

			// 参数时间的字符串形式
			String paramDateStr = ParseDate.parseFullFormat(paramDate);

			if (stringIsNull(nowDateStr) || stringIsNull(paramDateStr)) {
				return "1秒前";
			}

			if (seconds < 1) {
				return "1秒前";
			}

			// 如果秒数小于60
			if (seconds < 60) {
				return seconds + "秒前";
			}

			// 如果分钟数小于60
			if (minutes < 60) {
				return minutes + "分钟前";
			}

			// // 如果小时数小于24
			// if (hours < 24)
			// {
			// return hours + "小时前";
			// }

			// 参数日期的年份
			String year = paramDateStr.substring(0, 4);
			// 当前年份
			String nowYear = nowDateStr.substring(0, 4);

			// 参数日期的月份
			Long month = Long.parseLong(paramDateStr.substring(5, 7));
			// 当前月份
			Long nowMonth = Long.parseLong(nowDateStr.substring(5, 7));

			// 参数日期几号
			Long date = Long.parseLong(paramDateStr.substring(8, 10));
			// 当前几号
			Long nowDate = Long.parseLong(nowDateStr.substring(8, 10));

			// 参数日期的小时分钟：格式为： XX:XX
			String hourMinute = paramDateStr.substring(10, 16);

			// 年份相同只展示几月几日，小时分钟
			if (nowYear.equals(year)) {
				// 月份，几号都相同
				if (nowMonth.equals(month) && nowDate.equals(date)) {
					return "今天" + hourMinute;
				}

				return month + "月" + date + "日" + hourMinute;
			}

			return year + "年" + month + "月" + date + "日" + hourMinute;
		} catch (Exception e) {
			return "1秒前";
		}
	}
    
    
}
