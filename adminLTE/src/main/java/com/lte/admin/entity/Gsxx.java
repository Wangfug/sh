package com.lte.admin.entity;

public class Gsxx {
	/**
	 * 平台公司代码
	 */
	private String gsxxCode;
	/**
	 * 平台公司名称
	 */
	private String gsxxName;
	/**
	 * 平台公司所属分部，在公司表T_GSXX里是分部代码，在V_GSXX视图是分部名称
	 */
	private String ssfb;

	/**
	 * V_GSXX视图分部代码
	 */
	private String fbdm;
	/**
	 * 公司标记0：平台公司/1：分部公司/2：总部公司
	 */
	private String gsbz;

	public String getGsxxCode() {
		return gsxxCode;
	}

	public void setGsxxCode(String gsxxCode) {
		this.gsxxCode = gsxxCode;
	}

	public String getGsxxName() {
		return gsxxName;
	}

	public void setGsxxName(String gsxxName) {
		this.gsxxName = gsxxName;
	}

	public String getSsfb() {
		return ssfb;
	}

	public void setSsfb(String ssfb) {
		this.ssfb = ssfb;
	}

	public String getGsbz() {
		return gsbz;
	}

	public void setGsbz(String gsbz) {
		this.gsbz = gsbz;
	}

	public String getFbdm() {
		return fbdm;
	}

	public void setFbdm(String fbdm) {
		this.fbdm = fbdm;
	}

}