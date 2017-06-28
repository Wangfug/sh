package com.lte.admin.common.consts;

/**
 * 付款状态：0：没有/1：支付部分/2：全额支付
 * @author wfg
 * @date 2017年05月21日
 */
public enum PayStateEnum {

	NOT_PAY("0", "没有"),

	PAY_SOME("1", "支付部分"),

	PAY_ALL("2","全额支付");

	// 获取枚举code对应名字方法
	public static String getPayState(String code) {
		for (PayStateEnum payStateEnum : PayStateEnum.values()) {
			if (code.equals(payStateEnum.getCode())) {
				return payStateEnum.name;
			}
		}
		return "";
	}

	// 成员变量code
	private String code;

	// 成员变量value
	private String name;

	private PayStateEnum(String code, String name) {

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
