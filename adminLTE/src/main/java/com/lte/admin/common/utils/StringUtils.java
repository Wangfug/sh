package com.lte.admin.common.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * 
 * @author ThinkGem
 * @version 2013-05-22
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	public static String lowerFirst(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0, 1).toLowerCase() + str.substring(1);
		}
	}

	public static String upperFirst(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		}
	}

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)) {
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 * 
	 * @param str
	 *            目标字符串
	 * @param length
	 *            截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 缩略字符串（替换html）
	 * 
	 * @param str
	 *            目标字符串
	 * @param length
	 *            截取长度
	 * @return
	 */
	public static String rabbr(String str, int length) {
		return abbr(replaceHtml(str), length);
	}

	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val) {
		if (val == null) {
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val) {
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val) {
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val) {
		return toLong(val).intValue();
	}

	/**
	 * 字符串长度不足补齐
	 * 
	 * @param source
	 *            要补齐的源字符串
	 * @param totalLen
	 *            补齐后的总长度
	 * @param fillChar
	 *            补齐进去的字符
	 * @param isLeftFill
	 *            true为左补齐，false为右补齐
	 * @return 补齐后的字符串
	 * @author yanzai
	 */
	public static String stringFill(String source, int totalLen, String fillChar, boolean isLeftFill) {

		if (source.length() >= totalLen)
			return source;

		StringBuilder result = new StringBuilder(totalLen);
		int len = totalLen - source.length();
		if (isLeftFill) {
			for (; len > 0; len--) {
				result.append(fillChar);
			}
			result.append(source);
		} else {
			result.append(source);
			for (; len > 0; len--) {
				result.append(fillChar);
			}
		}
		return result.toString();
	}

	/**
	 * 将用参数split分割的字符串(默认为;)转换成Map返回。<br>
	 * 如果传入的字符串为空，返回null。<br>
	 * 格式：paramName1=paramValue1;paramNam2=paramValue2
	 * 
	 * @param stringParams
	 * @param split
	 *            分割字符
	 * @return Map<String, String>
	 * @author yanzai
	 */
	public static Map<String, String> stringParamsToMap(String stringParams, String split) {

		if (isBlank(stringParams))
			return null;
		if (isBlank(split)) {
			split = ";";
		}
		String[] cs = splitString(stringParams, split);

		HashMap<String, String> map = new HashMap<String, String>();

		for (int i = 0; i < cs.length; i++) {
			String param = cs[i].trim();
			if (param.length() == 0)
				continue;

			int eqSybol = param.indexOf('=');

			if (eqSybol < 0) {
				throw new RuntimeException(
						param + " is invalid. should be:paramName=paramValue;paramNam2=paramValue2.");
			}

			String name = param.substring(0, eqSybol);
			String value = param.substring(eqSybol + 1);

			map.put(name, value);
		}

		return map;
	}

	/**
	 * 分割字串，把返回字符数组中每个字符串进行trim()<br>
	 * 本方法本身不会添加空格，也不会把前后空格删除。
	 * 
	 * @param toSplit
	 *            原始字符串
	 * @param delimiter
	 *            分割字符串
	 * @return 字符串数组
	 */
	public static String[] splitString(String toSplit, String delimiter) {

		if (toSplit == null)
			return new String[0];

		int arynum = 0, intIdx = 0, intIdex = 0, div_length = delimiter.length();
		if (toSplit.compareTo(EMPTY) != 0) {
			if (toSplit.indexOf(delimiter) != -1) {
				intIdx = toSplit.indexOf(delimiter);
				for (int intCount = 1;; intCount++) {
					if (toSplit.indexOf(delimiter, intIdx + div_length) != -1) {
						intIdx = toSplit.indexOf(delimiter, intIdx + div_length);
						arynum = intCount;
					} else {
						arynum += 2;
						break;
					}
				}
			} else
				arynum = 1;
		} else
			arynum = 0;

		intIdx = 0;
		intIdex = 0;
		String[] returnStr = new String[arynum];

		if (toSplit.compareTo(EMPTY) != 0) {
			if (toSplit.indexOf(delimiter) != -1) {
				intIdx = toSplit.indexOf(delimiter);
				returnStr[0] = toSplit.substring(0, intIdx);
				for (int intCount = 1;; intCount++) {
					if (toSplit.indexOf(delimiter, intIdx + div_length) != -1) {
						intIdex = toSplit.indexOf(delimiter, intIdx + div_length);
						returnStr[intCount] = toSplit.substring(intIdx + div_length, intIdex);
						intIdx = toSplit.indexOf(delimiter, intIdx + div_length);
					} else {
						returnStr[intCount] = toSplit.substring(intIdx + div_length, toSplit.length());
						break;
					}
				}
			} else {
				returnStr[0] = toSplit.substring(0, toSplit.length());
				return returnStr;
			}
		} else {
			return returnStr;
		}
		return returnStr;
	}

	/**
	 * 
	 * @param s
	 * @param defaultValue
	 * @return
	 */
	public static int toInt(String s, int defaultValue) {

		if (s == null)
			return defaultValue;

		try {
			return new Integer(s).intValue();
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 
	 * @param s
	 * @param defaultValue
	 * @return
	 */
	public static float toFloat(String s, float defaultValue) {

		if (s == null)
			return defaultValue;

		try {
			return new Float(s).floatValue();
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 把string转换成int
	 * 
	 * @param s
	 *            要转换成int的String
	 * @return 如果转换失败，返回-1
	 */
	public static int toInt(String s) {

		return toInt(s, -1);
	}

	/**
	 * 将金额转换成BigDecimal类型
	 * 
	 * @param je
	 * @return
	 */
	public static BigDecimal toBigDecimal(String je) {
		BigDecimal ret = null;
		je = trim(je);
		if (isBlank(je)) {
			return null;
		}
		try {
			if (je.indexOf(",") > -1) {
				je = je.replaceAll(",", "");
			}
			if (je.indexOf(".") != je.lastIndexOf(("."))) {
				int first = je.indexOf(".");
				int second = je.indexOf(".", first + 1);
				je = je.substring(0, second);
			}
			ret = new BigDecimal(je);
		} catch (Exception e) {
			return null;
		}
		return ret;
	}

	/**
	 * 
	 * @param s
	 * @param defaultValue
	 * @return
	 */
	public static boolean toBoolean(String s, boolean defaultValue) {

		if (s == null)
			return defaultValue;

		try {
			return Boolean.valueOf(s).booleanValue();
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 删除数组中相同的元素。<br>
	 * 例如数组中元素为a b b c c c,合并重复元素后为a b c
	 * 
	 * @param s
	 *            原始数组
	 * @return 不含重复元素的数组。
	 */
	public static String[] mergeDuplicateArray(String[] s) {

		List<String> list = Arrays.asList(s);
		Set<String> set = new HashSet<String>(list);
		return set.toArray(new String[set.size()]);
	}

	/**
	 * 
	 */
	public static final Charset UTF_8 = Charset.forName("UTF-8");

	/**
	 * 首字母变小写
	 */
	public static String firstCharToLowerCase(String str) {

		Character firstChar = str.charAt(0);
		String tail = str.substring(1);
		str = Character.toLowerCase(firstChar) + tail;
		return str;
	}

	/**
	 * 把null变成EMPTY_STR<br>
	 * 
	 */
	public static String dealNull(String str) {

		String returnstr = null;
		if (str == null)
			returnstr = "";
		else
			returnstr = str;
		return returnstr;
	}

	/**
	 * 首字母变大写
	 */
	public static String firstCharToUpperCase(String str) {

		Character firstChar = str.charAt(0);
		String tail = str.substring(1);
		str = Character.toUpperCase(firstChar) + tail;
		return str;
	}

	/**
	 * 字符串为 null 或者为 "" 时返回 true
	 */
	public static boolean isBlank(String str) {

		if (str == null)
			return true;
		if (str.trim().length() == 0)
			return true;
		return false;
	}

	/**
	 * 字符串不为 null 而且不为 EMPTY_STR 时返回 true
	 */
	public static boolean notBlank(String str) {

		return !isBlank(str);
	}

	/**
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean notBlank(String... strings) {

		if (strings == null)
			return false;
		for (String str : strings)
			if (isBlank(str))
				return false;
		return true;
	}

	/**
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean isBlank(String... strings) {

		return !notBlank(strings);
	}

	/**
	 * 
	 * @param paras
	 * @return
	 */
	public static boolean notNull(Object... paras) {

		if (paras == null) {
			return false;
		}
		for (Object obj : paras)
			if (obj == null) {
				return false;
			} else if (((obj instanceof Map) && 0 == ((Map<?, ?>) obj).size())
					|| ((obj instanceof Collection) && 0 == ((Collection<?>) obj).size())) {
				return false;
			}
		return true;
	}

	/**
	 * 
	 * @param paras
	 * @return
	 */
	public static boolean isNull(Object... paras) {

		return !notNull(paras);
	}

	/**
	 * 字符字节取得默认“UTF-8”
	 */
	public static byte[] getBytes(String strKit) {

		return strKit.getBytes(UTF_8);

	}

	/**
	 * 不区分大小写<br>
	 * 
	 */
	public static boolean contains(String str, String strComp) {

		return str.toUpperCase().contains(strComp.toUpperCase());
	}

	public static boolean hasLength(CharSequence str) {

		return (str != null) && (str.length() > 0);
	}

	public static boolean hasText(CharSequence str) {

		if (!hasLength(str))
			return false;
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i)))
				return true;
		}
		return false;
	}

	public static String toNotNullString(Object obj) {

		String res = String.valueOf(obj);
		if ("null".equalsIgnoreCase(res))
			return "";
		return res;
	}

	/**
	 * 判断某个字符串是否存在于数组中
	 * 
	 * @param stringArray
	 *            原数组
	 * @param source
	 *            查找的字符串
	 * @return 是否找到
	 * @author yanzai
	 */
	public static boolean containInStrAry(String source, String[] stringArray) {

		if (notBlank(source) && stringArray.length > 0) {

			// 转换为list
			List<String> tempList = Arrays.asList(stringArray);

			// 利用list的包含方法,进行判断
			if (tempList.contains(source)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	public static String toNull(String str) {
		if (!StringUtils.isBlank(str) && str.equalsIgnoreCase("null")) {
			return null;
		} else {
			return str;
		}
	}
}
