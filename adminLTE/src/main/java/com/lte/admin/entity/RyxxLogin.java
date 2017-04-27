package com.lte.admin.entity;

import java.math.BigDecimal;

public class RyxxLogin extends RyxxKey {

	private String gsid;

	private String gsname;

	private String bmid;

	private String bmname;
	private String jobname;
	private String password;

	private String salt;

	private String psnname;

	private String deptcode;

	private String pkDeptdoc;

	private String pkOmJob;

	private String psnclasscode;

	private BigDecimal psnclscope;

	private Long dr;
	private String pkPsndoc;

	public String getPkPsndoc() {
		return pkPsndoc;
	}

	public void setPkPsndoc(String pkPsndoc) {
		this.pkPsndoc = pkPsndoc;
	}

	public String getJobname() {
		return jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

	public String getGsid() {
		return gsid;
	}

	public void setGsid(String gsid) {
		this.gsid = gsid;
	}

	public String getGsname() {
		return gsname;
	}

	public void setGsname(String gsname) {
		this.gsname = gsname;
	}

	public String getBmid() {
		return bmid;
	}

	public void setBmid(String bmid) {
		this.bmid = bmid;
	}

	public String getBmname() {
		return bmname;
	}

	public void setBmname(String bmname) {
		this.bmname = bmname;
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