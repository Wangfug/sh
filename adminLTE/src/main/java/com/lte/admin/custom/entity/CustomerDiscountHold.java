package com.lte.admin.custom.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class CustomerDiscountHold implements Serializable{
	private static final long serialVersionUID = 72L;
	
    //编号 	
	private Long id;
    //创建人编号 	
	private Long createBy;
    //创建时间 	
	private java.sql.Timestamp createTime;
    //状态 	
	private Long discount;
    //customer 	
	private Long customer;
	//订单号
	private String orderNo;
	//状态
	private Integer state;
	public CustomerDiscountHold(){
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public CustomerDiscountHold(
		Long id
	){
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
		
	public Long getDiscount() {
		return this.discount;
	}

	public void setDiscount(Long value) {
		this.discount = value;
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
		.append("Discount",getDiscount())
		.append("Customer",getCustomer())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof CustomerDiscountHold == false) return false;
	if(this == obj) return true;
	CustomerDiscountHold other = (CustomerDiscountHold)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
