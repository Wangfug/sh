package com.lte.admin.custom.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class CustomerDrivingLicence implements Serializable{
	private static final long serialVersionUID = 72L;
	
    //编号 	
	private Long id;
    //创建人编号 	
	private Long createBy;
    //创建时间 	
	private java.sql.Timestamp createTime;
    //状态 	
	private Integer state;
    //修改时间 	
	private java.sql.Timestamp lastTime;
    //修改人 	
	private Long lastBy;
    //准驾车型 	
	private String quasiDrivingType;
    //领证时间 	
	private java.sql.Timestamp getTime;
    //档案编号 	
	private String fileNumber;
    //attachment 	
	private String attachment;
	public CustomerDrivingLicence(){
	}

	public CustomerDrivingLicence(
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
		
	public String getQuasiDrivingType() {
		return this.quasiDrivingType;
	}

	public void setQuasiDrivingType(String value) {
		this.quasiDrivingType = value;
	}
		
	public java.sql.Timestamp getGetTime() {
		return this.getTime;
	}

	public void setGetTime(java.sql.Timestamp value) {
		this.getTime = value;
	}
		
	public String getFileNumber() {
		return this.fileNumber;
	}

	public void setFileNumber(String value) {
		this.fileNumber = value;
	}
		
	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(String value) {
		this.attachment = value;
	}

public String toString() {
	return new ToStringBuilder(this,org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE)
		.append("Id",getId())
		.append("CreateBy",getCreateBy())
		.append("CreateTime",getCreateTime())
		.append("State",getState())
		.append("LastTime",getLastTime())
		.append("LastBy",getLastBy())
		.append("QuasiDrivingType",getQuasiDrivingType())
		.append("GetTime",getGetTime())
		.append("FileNumber",getFileNumber())
		.append("Attachment",getAttachment())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof CustomerDrivingLicence == false) return false;
	if(this == obj) return true;
	CustomerDrivingLicence other = (CustomerDrivingLicence)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
