package com.lte.admin.common.consts;

/**
 * 删除标志
 * 
 * @author jiangkun
 * @date 2015年12月21日
 */
public enum ScbzEnum {

	/** 数据状态 未删除 ：0 */
	STATE_NORMAL("0", "未删除"),

	/** 数据状态 已删除 ：1 */
	STATE_DELETED("1", "已删除");

	// 获取枚举code对应名字方法
	public static String getScbzName(String code) {
		for (ScbzEnum scbzEnum : ScbzEnum.values()) {
			if (code.equals(scbzEnum.getCode())) {
				return scbzEnum.name;
			}
		}
		return "";
	}

	// 成员变量code
	private String code;

	// 成员变量value
	private String name;

	private ScbzEnum(String code, String name) {

		this.code = code;
		this.name = name;
	}

	public String getCode() {

		return code;
	}

	public void setCode(String code) {

		this.code = code;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}
}
