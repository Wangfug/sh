package com.lte.admin.custom.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
/**
 * @author Andy
 */
public class Customer implements Serializable{
	private static final long serialVersionUID = 72L;
	
    //编号 	
	private Long id;
    //创建人编号 	
	private Long createBy;
    //创建时间 	
	private java.sql.Timestamp createTime;
    //状态 	
	private Integer state;//状态：0-默认，1-等待审核，2-认证已通过，3-认证未通过，4-黑名单
    //修改时间 	
	private java.sql.Timestamp lastTime;
    //修改人 	
	private Long lastBy;
    //证件类型 	
	private String name;
    //证件号码 	
	private String mobilePhone;
    //附件 	
	private String password;
    //email 	
	private String email;
    //attachmenrt 	
	private String attachmenrt;
    //balance 	
	private Double balance;
    //identityCard 	
	private Long identityCard;
    //drivingLicence 	
	private Long drivingLicence;
    //otherCard 	
	private String otherCard;
    //integral 	
	private Long integral;
	//锁定余额
	private Double lockBalance;

	private String img;//身份证照片+实时照片

	private String authReason;

	public String getAuthReason() {
		return authReason;
	}

	public void setAuthReason(String authReason) {
		this.authReason = authReason;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Customer(){
	}

	public Customer(
		Long id
	){
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long value) {
		this.id = value;
	}
		
	public Long getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(Long value) {
		this.createBy = value;
	}
		
	public java.sql.Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.sql.Timestamp value) {
		this.createTime = value;
	}
		
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer value) {
		this.state = value;
	}
		
	public java.sql.Timestamp getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(java.sql.Timestamp value) {
		this.lastTime = value;
	}
		
	public Long getLastBy() {
		return this.lastBy;
	}

	public void setLastBy(Long value) {
		this.lastBy = value;
	}
		
	public String getName() {
		return this.name;
	}

	public void setName(String value) {
		this.name = value;
	}
		
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String value) {
		this.mobilePhone = value;
	}
		
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String value) {
		this.password = value;
	}
		
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String value) {
		this.email = value;
	}
		
	public String getAttachmenrt() {
		return this.attachmenrt;
	}

	public void setAttachmenrt(String value) {
		this.attachmenrt = value;
	}
		
	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double value) {
		this.balance = value;
	}
		
	public Long getIdentityCard() {
		return this.identityCard;
	}

	public void setIdentityCard(Long value) {
		this.identityCard = value;
	}
		
	public Long getDrivingLicence() {
		return this.drivingLicence;
	}

	public void setDrivingLicence(Long value) {
		this.drivingLicence = value;
	}
		
	public String getOtherCard() {
		return this.otherCard;
	}

	public void setOtherCard(String value) {
		this.otherCard = value;
	}
		
	public Long getIntegral() {
		return this.integral;
	}

	public void setIntegral(Long value) {
		this.integral = value;
	}

	public Double getLockBalance() {
		return lockBalance;
	}

	public void setLockBalance(Double lockBalance) {
		this.lockBalance = lockBalance;
	}

	public String toString() {
	return new ToStringBuilder(this,org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE)
		.append("Id",getId())
		.append("CreateBy",getCreateBy())
		.append("CreateTime",getCreateTime())
		.append("State",getState())
		.append("LastTime",getLastTime())
		.append("LastBy",getLastBy())
		.append("Name",getName())
		.append("MobilePhone",getMobilePhone())
		.append("Password",getPassword())
		.append("Email",getEmail())
		.append("Attachmenrt",getAttachmenrt())
		.append("Balance",getBalance())
		.append("IdentityCard",getIdentityCard())
		.append("DrivingLicence",getDrivingLicence())
		.append("OtherCard",getOtherCard())
		.append("Integral",getIntegral())
			.append("Img",getImg())
			.append("AuthReason",getAuthReason())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof Customer == false) return false;
	if(this == obj) return true;
	Customer other = (Customer)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
