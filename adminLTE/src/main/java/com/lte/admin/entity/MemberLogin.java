package com.lte.admin.entity;

import com.lte.admin.common.consts.DictConsts;

public class MemberLogin {
    private Long id;

    private String memberCode;

    private String password;

    private String salt;

    private String memberName;

    private String mobile;

    private Byte isSuperAdmin;
    
    private String jobCode;
    
    private String jobName;
    
    private String deptCode;

    private String parentCode;

    private String deptName;

    private String deptAttr;

    private Byte isLeaf;
    
    private String companyCode;

    private String companyName;
    
    private String companyType;

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Byte getIsSuperAdmin() {
		return isSuperAdmin;
	}

	public void setIsSuperAdmin(Byte isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptAttr() {
		return deptAttr;
	}

	public void setDeptAttr(String deptAttr) {
		this.deptAttr = deptAttr;
	}

	public Byte getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Byte isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	public String getUserBz() {
		if (this.isFbUser())
			return DictConsts.GSBZ_FB;
		else if (this.isZbUser())
			return DictConsts.GSBZ_ZB;
		else
			return DictConsts.GSBZ_PT;
	}
	
	public boolean isFbUser() {
		return (DictConsts.GSBZ_ZB.equals(this.companyType));
	}
	
	public boolean isZbUser() {
		return this.deptCode.startsWith("CHN");
	}

	@Override
	public String toString() {
		return "MemberLogin [id=" + id + ", memberCode=" + memberCode + ", password=" + password + ", salt=" + salt
				+ ", memberName=" + memberName + ", mobile=" + mobile + ", isSuperAdmin=" + isSuperAdmin + ", jobCode="
				+ jobCode + ", jobName=" + jobName + ", deptCode=" + deptCode + ", parentCode=" + parentCode
				+ ", deptName=" + deptName + ", deptAttr=" + deptAttr + ", isLeaf=" + isLeaf + ", companyCode="
				+ companyCode + ", companyName=" + companyName + "]";
	}

}
