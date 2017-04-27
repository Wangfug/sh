package com.lte.admin.custom.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class CustomerDiscount implements Serializable{
	private static final long serialVersionUID = 72L;
	
    //编号 	
	private Long id;
    //创建人编号 	
	private Long createBy;
    //创建时间 	
	private java.sql.Timestamp createTime;
    //状态 	
	private Integer discountState;
    //discountNumber 	
	private Long discountNumber;
    //discountInfo 	
	private String discountInfo;
    //discountBelong 	
	private String discountBelong;
	public CustomerDiscount(){
	}

	public CustomerDiscount(
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
		
	public Integer getDiscountState() {
		return this.discountState;
	}

	public void setDiscountState(Integer value) {
		this.discountState = value;
	}
		
	public Long getDiscountNumber() {
		return this.discountNumber;
	}

	public void setDiscountNumber(Long value) {
		this.discountNumber = value;
	}
		
	public String getDiscountInfo() {
		return this.discountInfo;
	}

	public void setDiscountInfo(String value) {
		this.discountInfo = value;
	}
		
	public String getDiscountBelong() {
		return this.discountBelong;
	}

	public void setDiscountBelong(String value) {
		this.discountBelong = value;
	}

public String toString() {
	return new ToStringBuilder(this,org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE)
		.append("Id",getId())
		.append("CreateBy",getCreateBy())
		.append("CreateTime",getCreateTime())
		.append("DiscountState",getDiscountState())
		.append("DiscountNumber",getDiscountNumber())
		.append("DiscountInfo",getDiscountInfo())
		.append("DiscountBelong",getDiscountBelong())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof CustomerDiscount == false) return false;
	if(this == obj) return true;
	CustomerDiscount other = (CustomerDiscount)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
