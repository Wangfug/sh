package com.lte.admin.order.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Andy
 */
public class OrderAccount implements Serializable{
	private static final long serialVersionUID = 72L;
	
    //编号 	
	private Long id;
    //创建人编号 	
	private Long createBy;
    //创建时间 	
	private java.sql.Timestamp createTime;
    //状态 	
	private Double totalMoney;
    //realPay 	
	private Double realPay;
    //bylongOrder 	
	private String belongOrder;
    //customer 	
	private Long customer;
    //payWay 	
	private String payWay;
    //payAmount 	
	private String payAmount;
    //acceptWay 	
	private String acceptWay;
    //acceptAmount 	
	private String acceptAmount;
    //acceptMan 	
	private String acceptMan;
	//支付状态
	private String state;
	//修改时间
	private java.sql.Timestamp updateTime;
	public OrderAccount(){
	}

	public OrderAccount(
		Long id
	){
		this.id = id;
	}


	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
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
		
	public Double getTotalMoney() {
		return this.totalMoney;
	}

	public void setTotalMoney(Double value) {
		this.totalMoney = value;
	}
		
	public Double getRealPay() {
		return this.realPay;
	}

	public void setRealPay(Double value) {
		this.realPay = value;
	}
		
	public String getBelongOrder() {
		return this.belongOrder;
	}

	public void setBelongOrder(String value) {
		this.belongOrder = value;
	}
		
	public Long getCustomer() {
		return this.customer;
	}

	public void setCustomer(Long value) {
		this.customer = value;
	}
		
	public String getPayWay() {
		return this.payWay;
	}

	public void setPayWay(String value) {
		this.payWay = value;
	}
		
	public String getPayAmount() {
		return this.payAmount;
	}

	public void setPayAmount(String value) {
		this.payAmount = value;
	}
		
	public String getAcceptWay() {
		return this.acceptWay;
	}

	public void setAcceptWay(String value) {
		this.acceptWay = value;
	}
		
	public String getAcceptAmount() {
		return this.acceptAmount;
	}

	public void setAcceptAmount(String value) {
		this.acceptAmount = value;
	}
		
	public String getAcceptMan() {
		return this.acceptMan;
	}

	public void setAcceptMan(String value) {
		this.acceptMan = value;
	}


	public String toString() {
	return new ToStringBuilder(this,org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE)
		.append("Id",getId())
		.append("CreateBy",getCreateBy())
		.append("CreateTime",getCreateTime())
		.append("TotalMoney",getTotalMoney())
		.append("RealPay",getRealPay())
		.append("BelongOrder",getBelongOrder())
		.append("Customer",getCustomer())
		.append("PayWay",getPayWay())
		.append("PayAmount",getPayAmount())
		.append("AcceptWay",getAcceptWay())
		.append("AcceptAmount",getAcceptAmount())
		.append("AcceptMan",getAcceptMan())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof OrderAccount == false) return false;
	if(this == obj) return true;
	OrderAccount other = (OrderAccount)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
