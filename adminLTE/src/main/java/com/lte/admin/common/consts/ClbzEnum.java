/**
 * JBEnum.java
 *
 * @date 2015年12月22日
 * @author yanzai
 */
package com.lte.admin.common.consts;

/**
 * 处理标记枚举
 * 
 * @author jiangkun
 *
 */
public enum ClbzEnum {

	/** 0 ：未处理 */
	CLBZ_UNDO("0", "未处理"),

	/** 1 ：已处理 */
	CLBZ_DONE("1", "已处理");

	// 获取枚举code对应名字方法
	public static String getEnumName(String code) {

		for (ClbzEnum enumObj : ClbzEnum.values()) {
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

	private ClbzEnum(String code, String name) {

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
