/**
 * JBEnum.java
 *
 * @date 2015年12月22日
 * @author yanzai
 */
package com.lte.admin.common.consts;

import com.lte.admin.common.utils.StringUtils;

/**
 * 结清标记枚举
 * 
 * @author jiangkun
 *
 */
public enum CpgsEnum {

	/** 1 ：汇通达 */
	CPGS_HTD("1", "汇通达"),

	/** 2 ：五星控股 */
	CPGS_5STAR("2", "五星"),

	/** 3 ：江苏三创 */
	CPGS_JSSC("3", "三创");
	// 获取枚举code对应名字方法
	public static String getEnumName(String code) {

		for (CpgsEnum enumObj : CpgsEnum.values()) {
			if (code.equals(enumObj.getCode())) {
				return enumObj.name;
			}
		}
		return "";
	}

	// 获取枚举name对应code的方法
	public static String getEnumCode(String str) {
		String cpgsNm = "";

		if (StringUtils.isBlank(str)) {
			return "";
		}
		for (CpgsEnum enumObj : CpgsEnum.values()) {
			cpgsNm = enumObj.getName();
			if (str.indexOf(cpgsNm) >= 0) {
				return enumObj.code;
			}
		}
		return "";
	}

	// 成员变量code
	private String code;

	// 成员变量value
	private String name;

	private CpgsEnum(String code, String name) {

		this.code = code;
		this.name = name;
	}

	public String getCode() {

		return this.code;
	}

	public String getName() {

		return this.name;
	}

	public void setCode(String code) {

		this.code = code;
	}

	public void setName(String name) {

		this.name = name;
	}
}
