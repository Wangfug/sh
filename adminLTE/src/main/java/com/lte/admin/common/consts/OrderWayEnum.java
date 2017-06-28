package com.lte.admin.common.consts;

/**
 * 取车方式：1：上门送取/2：门店自取
 * @author jiangkun
 * @date 2015年12月21日
 */
public enum OrderWayEnum {

	/** 数据状态 未删除 ：0 */
	BYSHOP("0", "上门送取"),

	/** 数据状态 已删除 ：1 */
	BYSELF("1", "门店取还");

	// 获取枚举code对应名字方法
	public static String getOrderWayEnum(String code) {
		for (OrderWayEnum orderWayEnum : OrderWayEnum.values()) {
			if (code.equals(orderWayEnum.getCode())) {
				return orderWayEnum.name;
			}
		}
		return "";
	}

	// 成员变量code
	private String code;

	// 成员变量value
	private String name;

	private OrderWayEnum(String code, String name) {

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
