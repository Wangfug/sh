package com.lte.admin.custom.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class CustomerCredential implements Serializable{
	private static final long serialVersionUID = 72L;
	
    //编号 	
	private Long id;
    //创建人编号 	
	private Long createBy;
    //创建时间 	
	private java.sql.Timestamp createTime;
    //状态 	
	private Integer state;
    //证件类型 	
	private String credentialType;
    //证件号码 	
	private String credentialCode;
    //附件 	
	private String attachment;
    //getTime 	
	private java.sql.Timestamp getTime;
    //frontView 	
	private Long frontView;
    //backView 	
	private Long backView;
	public CustomerCredential(){
	}

	public CustomerCredential(
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
		
	public String getCredentialType() {
		return this.credentialType;
	}

	public void setCredentialType(String value) {
		this.credentialType = value;
	}
		
	public String getCredentialCode() {
		return this.credentialCode;
	}

	public void setCredentialCode(String value) {
		this.credentialCode = value;
	}
		
	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(String value) {
		this.attachment = value;
	}
		
	public java.sql.Timestamp getGetTime() {
		return this.getTime;
	}

	public void setGetTime(java.sql.Timestamp value) {
		this.getTime = value;
	}
		
	public Long getFrontView() {
		return this.frontView;
	}

	public void setFrontView(Long value) {
		this.frontView = value;
	}
		
	public Long getBackView() {
		return this.backView;
	}

	public void setBackView(Long value) {
		this.backView = value;
	}

public String toString() {
	return new ToStringBuilder(this,org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE)
		.append("Id",getId())
		.append("CreateBy",getCreateBy())
		.append("CreateTime",getCreateTime())
		.append("State",getState())
		.append("CredentialType",getCredentialType())
		.append("CredentialCode",getCredentialCode())
		.append("Attachment",getAttachment())
		.append("GetTime",getGetTime())
		.append("FrontView",getFrontView())
		.append("BackView",getBackView())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof CustomerCredential == false) return false;
	if(this == obj) return true;
	CustomerCredential other = (CustomerCredential)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
