package com.lte.admin.entity;

public class RyxxKey {
	/**
	 * 人员基本档案主键 PK_PSNBASDOC
	 */
	private String pkPsnbasdoc;
	/**
	 * 公司主键 PK_PSNDOC
	 */
	private String pkPsndoc;
	/**
	 * 人员编码 PSNCODE
	 */
	private String psncode;

	public String getPkPsnbasdoc() {
		return pkPsnbasdoc;
	}

	public void setPkPsnbasdoc(String pkPsnbasdoc) {
		this.pkPsnbasdoc = pkPsnbasdoc == null ? null : pkPsnbasdoc.trim();
	}

	public String getPkPsndoc() {
		return pkPsndoc;
	}

	public void setPkPsndoc(String pkPsndoc) {
		this.pkPsndoc = pkPsndoc == null ? null : pkPsndoc.trim();
	}

	public String getPsncode() {
		return psncode;
	}

	public void setPsncode(String psncode) {
		this.psncode = psncode == null ? null : psncode.trim();
	}
}