package com.lte.admin.common.consts;

/**
 * 工单类型：0：送车单/1：还车单
 * @author jiangkun
 * @date 2015年12月21日
 */
public enum InvoiceEnum {

	/** 是否开发票 开发票 ：0 */
	INVOICE("0", "开发票"),

	/** 是否开发票 不开发票 ：1 */
	NOT_INVOICE("1", "不开发票");

	// 获取枚举code对应名字方法
	public static String getAffirmInvoice(String code) {
		for (InvoiceEnum affirmInvoiceEnum : InvoiceEnum.values()) {
			if (code.equals(affirmInvoiceEnum.getCode())) {
				return affirmInvoiceEnum.name;
			}
		}
		return "";
	}

	// 成员变量code
	private String code;

	// 成员变量value
	private String name;

	private InvoiceEnum(String code, String name) {

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
