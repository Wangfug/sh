package com.lte.admin.common.consts;

/**
 * 逾期标志标志
 * 
 * @author jiangkun
 * @date 2015年12月21日
 */
public enum YqbjEnum {

	/** 正常：0 */
	YQBJ_NORMAL("0", "正常"),

	/** 到期：1 */
	YQBJ_MATURITY("1", "到期"),

	/** 逾期 ：2 */
	YQBJ_OVERDUE("2", "逾期");

	// 获取枚举code对应名字方法
	public static String getYqbjName(String code) {
		for (YqbjEnum yqbjEnum : YqbjEnum.values()) {
			if (code.equals(yqbjEnum.getCode())) {
				return yqbjEnum.name;
			}
		}
		return "";
	}

	// 成员变量code
	private String code;

	// 成员变量value
	private String name;

	private YqbjEnum(String code, String name) {

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
