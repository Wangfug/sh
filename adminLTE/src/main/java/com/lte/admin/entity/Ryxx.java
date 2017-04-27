package com.lte.admin.entity;

import java.math.BigDecimal;

import com.lte.admin.common.consts.DictConsts;

public class Ryxx extends RyxxKey {
	private String password;

	private String salt;

	private String psnname;

	private String deptcode;

	private String pkDeptdoc;

	private String pkOmJob;

	private String psnclasscode;

	private BigDecimal psnclscope;

	private Long dr;

	private String gsbm;

	private String gsmc;

	private String gsbz;

	private String fbmc;

	private String fbbm;

	/* 是否是分部用户 */
	private boolean isFbUser;

	/* 是否是总部用户 */
	private boolean isZbUser;

	private String userBz;


	/**
	 * @return the userBz
	 */
	public String getUserBz() {
		if (this.isFbUser())
			return DictConsts.GSBZ_FB;
		else if (this.isZbUser())
			return DictConsts.GSBZ_ZB;
		else
			return DictConsts.GSBZ_PT;
	}

	/**
	 * @return the gsmc
	 */
	public String getGsmc() {
		return gsmc;
	}

	/**
	 * @param gsmc
	 *            the gsmc to set
	 */
	public void setGsmc(String gsmc) {
		this.gsmc = gsmc;
	}

	/**
	 * @return the isFbUser
	 */
	public boolean isFbUser() {
		return DictConsts.GSBZ_FB.equals(this.gsbz);
	}

	/**
	 * @param isFbUser
	 *            the isFbUser to set
	 */
	public void setFbUser(boolean isFbUser) {
		this.isFbUser = isFbUser;
	}

	/**
	 * @return the isZbUser
	 */
	public boolean isZbUser() {
		return deptcode.startsWith(DictConsts.ZBGSBM);
	}

	/**
	 * @param isZbUser
	 *            the isZbUser to set
	 */
	public void setZbUser(boolean isZbUser) {
		this.isZbUser = isZbUser;
	}

	/**
	 * @return the fbbm
	 */
	public String getFbbm() {
		return fbbm;
	}

	/**
	 * @param fbbm
	 *            the fbbm to set
	 */
	public void setFbbm(String fbbm) {
		this.fbbm = fbbm;
	}

	/**
	 * @return the fbmc
	 */
	public String getFbmc() {
		return fbmc;
	}

	/**
	 * @param fbmc
	 *            the fbmc to set
	 */
	public void setFbmc(String fbmc) {
		this.fbmc = fbmc;
	}

	/**
	 * @return the gsbm
	 */
	public String getGsbm() {

		return gsbm;
	}

	/**
	 * @param gsbm
	 *            the gsbm to set
	 */
	public void setGsbm(String gsbm) {
		this.gsbm = gsbm;
	}

	/**
	 * @return the gsbz
	 */
	public String getGsbz() {
		return gsbz;
	}

	/**
	 * @param gsbz
	 *            the gsbz to set
	 */
	public void setGsbz(String gsbz) {
		this.gsbz = gsbz;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt == null ? null : salt.trim();
	}

	public String getPsnname() {
		return psnname;
	}

	public void setPsnname(String psnname) {
		this.psnname = psnname == null ? null : psnname.trim();
	}

	public String getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode == null ? null : deptcode.trim();
	}

	public String getPkDeptdoc() {
		return pkDeptdoc;
	}

	public void setPkDeptdoc(String pkDeptdoc) {
		this.pkDeptdoc = pkDeptdoc == null ? null : pkDeptdoc.trim();
	}

	public String getPkOmJob() {
		return pkOmJob;
	}

	public void setPkOmJob(String pkOmJob) {
		this.pkOmJob = pkOmJob == null ? null : pkOmJob.trim();
	}

	public String getPsnclasscode() {
		return psnclasscode;
	}

	public void setPsnclasscode(String psnclasscode) {
		this.psnclasscode = psnclasscode == null ? null : psnclasscode.trim();
	}

	public BigDecimal getPsnclscope() {
		return psnclscope;
	}

	public void setPsnclscope(BigDecimal psnclscope) {
		this.psnclscope = psnclscope;
	}

	public Long getDr() {
		return dr;
	}

	public void setDr(Long dr) {
		this.dr = dr;
	}

	public String getId() {
		return getPkPsnbasdoc();
	}

	public String getName() {
		return psnname;
	}
}