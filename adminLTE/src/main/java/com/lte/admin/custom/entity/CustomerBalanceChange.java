package com.lte.admin.custom.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class CustomerBalanceChange implements Serializable{
	private static final long serialVersionUID = 72L;
	
    //编号 	
	private Long id;
    //创建人编号 	
	private Long createBy;
    //创建时间 	
	private java.sql.Timestamp createTime;
    //状态 	
	private Long beforeChange;
    //afterChange 	
	private Long afterChange;
    //balanceCustomer 	
	private Long balanceCustomer;
	public CustomerBalanceChange(){
	}

	public CustomerBalanceChange(
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
		
	public Long getBeforeChange() {
		return this.beforeChange;
	}

	public void setBeforeChange(Long value) {
		this.beforeChange = value;
	}
		
	public Long getAfterChange() {
		return this.afterChange;
	}

	public void setAfterChange(Long value) {
		this.afterChange = value;
	}
		
	public Long getBalanceCustomer() {
		return this.balanceCustomer;
	}

	public void setBalanceCustomer(Long value) {
		this.balanceCustomer = value;
	}

public String toString() {
	return new ToStringBuilder(this,org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE)
		.append("Id",getId())
		.append("CreateBy",getCreateBy())
		.append("CreateTime",getCreateTime())
		.append("BeforeChange",getBeforeChange())
		.append("AfterChange",getAfterChange())
		.append("BalanceCustomer",getBalanceCustomer())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof CustomerBalanceChange == false) return false;
	if(this == obj) return true;
	CustomerBalanceChange other = (CustomerBalanceChange)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
