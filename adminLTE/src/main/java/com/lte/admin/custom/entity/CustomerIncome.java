package com.lte.admin.custom.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class CustomerIncome implements Serializable{
	private static final long serialVersionUID = 72L;
	
    //编号 	
	private Long id;
    //创建人编号 	
	private Long createBy;
    //创建时间 	
	private java.sql.Timestamp createTime;
    //状态 	
	private Long incomeOrder;
    //incomeCar 	
	private Long incomeCar;
    //incomeCustomer 	
	private Long incomeCustomer;
    //incomeAccount 	
	private Double incomeAccount;
	public CustomerIncome(){
	}

	public CustomerIncome(
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
		
	public Long getIncomeOrder() {
		return this.incomeOrder;
	}

	public void setIncomeOrder(Long value) {
		this.incomeOrder = value;
	}
		
	public Long getIncomeCar() {
		return this.incomeCar;
	}

	public void setIncomeCar(Long value) {
		this.incomeCar = value;
	}
		
	public Long getIncomeCustomer() {
		return this.incomeCustomer;
	}

	public void setIncomeCustomer(Long value) {
		this.incomeCustomer = value;
	}
		
	public Double getIncomeAccount() {
		return this.incomeAccount;
	}

	public void setIncomeAccount(Double value) {
		this.incomeAccount = value;
	}

public String toString() {
	return new ToStringBuilder(this,org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE)
		.append("Id",getId())
		.append("CreateBy",getCreateBy())
		.append("CreateTime",getCreateTime())
		.append("IncomeOrder",getIncomeOrder())
		.append("IncomeCar",getIncomeCar())
		.append("IncomeCustomer",getIncomeCustomer())
		.append("IncomeAccount",getIncomeAccount())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof CustomerIncome == false) return false;
	if(this == obj) return true;
	CustomerIncome other = (CustomerIncome)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
