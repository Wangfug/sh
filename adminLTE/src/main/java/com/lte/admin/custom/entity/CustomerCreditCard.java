package com.lte.admin.custom.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class CustomerCreditCard implements Serializable{
	private static final long serialVersionUID = 72L;
	
    //编号 	
	private Long id;
    //创建人编号 	
	private Long createBy;
    //创建时间 	
	private java.sql.Timestamp createTime;
    //状态 	
	private Integer creditState;
    //creditNo 	
	private String creditNo;
    //creditBank 	
	private String creditBank;
    //security 	
	private String security;
    //linkephone 	
	private String linkephone;
    //validityTime 	
	private java.sql.Timestamp validityTime;
    //customer 	
	private Long customer;
	public CustomerCreditCard(){
	}

	public CustomerCreditCard(
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
		
	public Integer getCreditState() {
		return this.creditState;
	}

	public void setCreditState(Integer value) {
		this.creditState = value;
	}
		
	public String getCreditNo() {
		return this.creditNo;
	}

	public void setCreditNo(String value) {
		this.creditNo = value;
	}
		
	public String getCreditBank() {
		return this.creditBank;
	}

	public void setCreditBank(String value) {
		this.creditBank = value;
	}
		
	public String getSecurity() {
		return this.security;
	}

	public void setSecurity(String value) {
		this.security = value;
	}
		
	public String getLinkephone() {
		return this.linkephone;
	}

	public void setLinkephone(String value) {
		this.linkephone = value;
	}
		
	public java.sql.Timestamp getValidityTime() {
		return this.validityTime;
	}

	public void setValidityTime(java.sql.Timestamp value) {
		this.validityTime = value;
	}
		
	public Long getCustomer() {
		return this.customer;
	}

	public void setCustomer(Long value) {
		this.customer = value;
	}

public String toString() {
	return new ToStringBuilder(this,org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE)
		.append("Id",getId())
		.append("CreateBy",getCreateBy())
		.append("CreateTime",getCreateTime())
		.append("CreditState",getCreditState())
		.append("CreditNo",getCreditNo())
		.append("CreditBank",getCreditBank())
		.append("Security",getSecurity())
		.append("Linkephone",getLinkephone())
		.append("ValidityTime",getValidityTime())
		.append("Customer",getCustomer())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof CustomerCreditCard == false) return false;
	if(this == obj) return true;
	CustomerCreditCard other = (CustomerCreditCard)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
