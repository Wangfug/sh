package com.lte.admin.common.consts;

/**
 * 是否已预授权：0：没有/1：完成
 * @author wfg
 * @date 2017年05月21日
 */
public enum PreAuthorizedEnum {

	NOT_PRE_AUTHORIZED("0", "未授权"),

	PRE_AUTHORIZED("1", "已授权");

	// 获取枚举code对应名字方法
	public static String getPreAuthorized(String code) {
		for (PreAuthorizedEnum preAuthorizedEnum : PreAuthorizedEnum.values()) {
			if (code.equals(preAuthorizedEnum.getCode())) {
				return preAuthorizedEnum.name;
			}
		}
		return "";
	}

	// 成员变量code
	private String code;

	// 成员变量value
	private String name;

	private PreAuthorizedEnum(String code, String name) {

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
