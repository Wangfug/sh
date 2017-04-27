package com.lte.admin.common.consts;

/**
 * 生成状态
 * 
 * @author jiangkun
 * @date 2015年12月21日
 */
public enum ScztEnum {

	/** 数据状态 未生成 ：0 */
	STATE_NOGENERATED("0", "未生成"),

	/** 数据状态 生成成功 ：1 */
	STATE_SUCCESS("1", "生成成功"),

	/** 数据状态 生成失败 ：2 */
	STATE_FAILURE("2", "生成失败"),

	/** 数据状态 支付成功 ：9 */
	STATE_PAYMENT("9", "支付成功");

	// 获取枚举code对应名字方法
	public static String getScztName(String code) {
		for (ScztEnum scztEnum : ScztEnum.values()) {
			if (code.equals(scztEnum.getCode())) {
				return scztEnum.name;
			}
		}
		return "";
	}

	// 成员变量code
	private String code;

	// 成员变量value
	private String name;

	private ScztEnum(String code, String name) {

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
