/**
 * JBEnum.java
 *
 * @date 2015年12月22日
 * @author yanzai
 */
package com.lte.admin.common.consts;

/**
 * 充值标记枚举
 * 
 * @author jiangkun
 *
 */
public enum CzbjEnum {

	/** 0 ：错误 */
	CZBJ_ERROR("0", "错误"),

	/** 1 ：未充值 */
	CZBJ_NORECHARGE("1", "未充值"),

	/** 2 ：已充值 */
	CZBJ_RECHARGED("2", "已充值");

	// 获取枚举code对应名字方法
	public static String getEnumName(String code) {

		for (CzbjEnum enumObj : CzbjEnum.values()) {
			if (code.equals(enumObj.getCode())) {
				return enumObj.name;
			}
		}
		return "";
	}

	// 成员变量code
	private String code;

	// 成员变量value
	private String name;

	private CzbjEnum(String code, String name) {

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
