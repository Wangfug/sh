package com.lte.admin.common.consts;

/**
 * 发布标志
 * 
 * @author jiangkun
 * @date 2015年12月21日
 */
public enum FbztEnum {

	/** 数据状态 对象外/未发布 ：0 */
	STATE_NOTARGET("0", "未发布"),

	/** 数据状态 待发布 ：1 */
	STATE_NEEDPUBLISH("1", "待发布"),

	/** 数据状态 已发布 ：2 */
	STATE_PUBLISHED("2", "已发布");

	// 获取枚举code对应名字方法
	public static String getFbztName(String code) {
		for (FbztEnum fbztEnum : FbztEnum.values()) {
			if (code.equals(fbztEnum.getCode())) {
				return fbztEnum.name;
			}
		}
		return "";
	}

	// 成员变量code
	private String code;

	// 成员变量value
	private String name;

	private FbztEnum(String code, String name) {

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
