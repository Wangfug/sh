package com.lte.admin.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang3.StringUtils;

import com.lte.admin.common.consts.DictConsts;

public class ConvertUtils {

	static {
		registerDateConverter();
	}

	/**
	 * 提取集合中的对象的属性(通过getter函数), 组合成List.
	 * 
	 * @param collection
	 *            来源集合.
	 * @param propertyName
	 *            要提取的属性名.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List convertElementPropertyToList(final Collection collection, final String propertyName) {
		List list = new ArrayList();

		try {
			for (Object obj : collection) {
				list.add(PropertyUtils.getProperty(obj, propertyName));
			}
		} catch (Exception e) {
			throw Reflections.convertReflectionExceptionToUnchecked(e);
		}

		return list;
	}

	/**
	 * 提取集合中的对象的属性(通过getter函数), 组合成由分割符分隔的字符串.
	 * 
	 * @param collection
	 *            来源集合.
	 * @param propertyName
	 *            要提取的属性名.
	 * @param separator
	 *            分隔符.
	 */
	@SuppressWarnings({ "rawtypes" })
	public static String convertElementPropertyToString(final Collection collection, final String propertyName,
			final String separator) {
		List list = convertElementPropertyToList(collection, propertyName);
		return StringUtils.join(list, separator);
	}

	/**
	 * 转换字符串到相应类型.
	 * 
	 * @param value
	 *            待转换的字符串.
	 * @param toType
	 *            转换目标类型.
	 */
	public static Object convertStringToObject(String value, Class<?> toType) {
		try {
			return org.apache.commons.beanutils.ConvertUtils.convert(value, toType);
		} catch (Exception e) {
			throw Reflections.convertReflectionExceptionToUnchecked(e);
		}
	}

	/**
	 * 定义日期Converter的格式: yyyy-MM-dd 或 yyyy-MM-dd HH:mm:ss
	 */
	private static void registerDateConverter() {
		DateConverter dc = new DateConverter();
		dc.setUseLocaleFormat(true);
		dc.setPatterns(new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" });
		org.apache.commons.beanutils.ConvertUtils.register(dc, Date.class);
	}

	/**
	 * 对于金额保留4位小数输出
	 * 
	 * @param dec
	 * @return
	 */
	public static BigDecimal getDecimalScale4(BigDecimal dec) {
		return getDecimalScale(dec, DictConsts.ROUND_FOUR);
	}

	/**
	 * 对于金额保留2位小数输出
	 * 
	 * @param dec
	 * @return
	 */
	public static BigDecimal getDecimalScale2(BigDecimal dec) {
		return getDecimalScale(dec, DictConsts.ROUND_TWO);
	}

	/**
	 * 对于金额保留指定位数小数
	 * 
	 * @param dec
	 * @return
	 */
	private static BigDecimal getDecimalScale(BigDecimal dec, int scale) {
		BigDecimal retDec = BigDecimal.ZERO;
		retDec = dec;
		if (retDec == null) {
			retDec = new BigDecimal("0.00000000000");
		}
		retDec = retDec.setScale(scale, RoundingMode.HALF_UP);
		return retDec;
	}

	/**
	 * 对于输入的数据format成000,000.00的格式
	 * 
	 * @param dec
	 * @return
	 */
	public static String formatCommaAnd2Point(BigDecimal dec) {
		BigDecimal tmpDec = getDecimalScale2(dec);
		DecimalFormat df = new DecimalFormat("#,###.00");
		String decimalStr = df.format(tmpDec).equals(".00") ? "0.00" : df.format(tmpDec);
		if (decimalStr.startsWith(".")) {
			decimalStr = "0" + decimalStr;
		}
		return decimalStr;
	}

	/**
	 * 对于输入的数据format成百分比的格式
	 * 
	 * @param dec
	 * @return
	 */
	public static String formatPercent(BigDecimal dec) {
		String percentStr = "";
		NumberFormat percent = null;
		BigDecimal tmpDec = null;

		if (dec != null) {
			tmpDec = getDecimalScale2(dec);
			percent = NumberFormat.getPercentInstance(); // 建立百分比格式化引用
			percent.setMaximumFractionDigits(2);
			percentStr = percent.format(tmpDec);
		}
		return percentStr;
	}
}
