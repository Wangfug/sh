package com.lte.admin.common.consts;

/**
 * 工单类型：0：送车单/1：还车单
 * @author jiangkun
 * @date 2015年12月21日
 */
public enum OrderWorkTypeEnum {

	/** 数据状态 未删除 ：0 */
	SEND_CAR("0", "取车单"),

	/** 数据状态 已删除 ：1 */
	GET_CAR("1", "还车单");

	// 获取枚举code对应名字方法
	public static String getOrderWorkType(String code) {
		for (OrderWorkTypeEnum orderWorkTypeEnum : OrderWorkTypeEnum.values()) {
			if (code.equals(orderWorkTypeEnum.getCode())) {
				return orderWorkTypeEnum.name;
			}
		}
		return "";
	}

	// 成员变量code
	private String code;

	// 成员变量value
	private String name;

	private OrderWorkTypeEnum(String code, String name) {

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
