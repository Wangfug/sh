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
	private Double beforeChange;

	private Double afterChange;
	//balanceCustomer
	private Long balanceCustomer;

	//afterChange
	private Double changeMoney;//变动金额
	//balanceCustomer
	private int type;//0-支出/1-收入

	//balanceCustomer
	private Long transactionNo;//交易单号：订单号/充值单号（new）

	private String payWay;//交易方式

	private long changeId;//主键

	public long getChangeId() {
		return changeId;
	}

	public void setChangeId(long changeId) {
		this.changeId = changeId;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public Double getChangeMoney() {
		return changeMoney;
	}

	public void setChangeMoney(Double changeMoney) {
		this.changeMoney = changeMoney;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Long getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(Long transactionNo) {
		this.transactionNo = transactionNo;
	}

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
		
	public Double getBeforeChange() {
		return this.beforeChange;
	}

	public void setBeforeChange(Double value) {
		this.beforeChange = value;
	}
		
	public Double getAfterChange() {
		return this.afterChange;
	}

	public void setAfterChange(Double value) {
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
			.append("ChangeMoney",getChangeMoney())
			.append("Type",getType())
			.append("TransactionNo",getTransactionNo())
			.append("PayWay",getPayWay())
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
