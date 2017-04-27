/**
 * JBEnum.java
 *
 * @date 2015年12月22日
 * @author yanzai
 */
package com.lte.admin.common.consts;

/**
 * 结清标记枚举
 * 
 * @author jiangkun
 *
 */
public enum JqbjEnum {

	/** 0 ：未结清 */
	JQBJ_UNSETTLED("0", "未结清"),

	/** 1 ：已结清 */
	JQBJ_SETTLED("1", "已结清");

	// 获取枚举code对应名字方法
	public static String getEnumName(String code) {

		for (JqbjEnum enumObj : JqbjEnum.values()) {
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

	private JqbjEnum(String code, String name) {

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
