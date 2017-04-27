package com.lte.admin.other.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class TbaseCompany implements Serializable{
	private static final long serialVersionUID = 72L;
	
    //id 	
	private Long id;
    //companyCode 	
	private String companyCode;
    //companyName 	
	private String companyName;
    //parentComanyCode 	
	private String parentComanyCode;
    //00：总部公司/01：分部公司/02：平台公司 	
	private String companyType;
    //更新时间 	
	private java.sql.Timestamp updateTime;
    //cityId 	
	private Long cityId;
	public TbaseCompany(){
	}

	public TbaseCompany(
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
		
	public String getCompanyCode() {
		return this.companyCode;
	}

	public void setCompanyCode(String value) {
		this.companyCode = value;
	}
		
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String value) {
		this.companyName = value;
	}
		
	public String getParentComanyCode() {
		return this.parentComanyCode;
	}

	public void setParentComanyCode(String value) {
		this.parentComanyCode = value;
	}
		
	public String getCompanyType() {
		return this.companyType;
	}

	public void setCompanyType(String value) {
		this.companyType = value;
	}
		
	public java.sql.Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(java.sql.Timestamp value) {
		this.updateTime = value;
	}
		
	public Long getCityId() {
		return this.cityId;
	}

	public void setCityId(Long value) {
		this.cityId = value;
	}

public String toString() {
	return new ToStringBuilder(this,org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE)
		.append("Id",getId())
		.append("CompanyCode",getCompanyCode())
		.append("CompanyName",getCompanyName())
		.append("ParentComanyCode",getParentComanyCode())
		.append("CompanyType",getCompanyType())
		.append("UpdateTime",getUpdateTime())
		.append("CityId",getCityId())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof TbaseCompany == false) return false;
	if(this == obj) return true;
	TbaseCompany other = (TbaseCompany)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
