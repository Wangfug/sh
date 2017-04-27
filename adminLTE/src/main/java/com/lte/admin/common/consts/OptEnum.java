/**
 * OptEnum.java
 *
 * @date 2015年12月22日
 * @author yanzai
 */
package com.lte.admin.common.consts;

/**
 * 操作标识枚举
 * 
 * @author yanzai
 *
 */
public enum OptEnum {
	/** tempsave ：暂存操作 */
	TEMP_SAVE("tempsave", "暂存操作"),

	/** submit ：提交操作 */
	SUBMIT("submit", "提交操作"),

	/** delete ：删除操作 */
	DELETE("delete", "删除操作"),

	/** confirm ：确认操作 */
	CONFIRM("confirm", "确认操作"),

	/** ba ：备案操作 */
	BA("ba", "备案操作"),

	/** publish ：发布操作 */
	PUBLISH("publish", "发布操作");

	// 获取枚举code对应名字方法
	public static String getEnumName(String code) {

		for (OptEnum enumObj : OptEnum.values()) {
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

	private OptEnum(String code, String name) {

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
