package com.lte.admin.order.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class OrderBill implements Serializable{
	private static final long serialVersionUID = 72L;
	
    //编号 	
	private Long id;
    //创建人编号 	
	private Integer createBy;
    //创建时间 	
	private java.sql.Timestamp createTime;
    //状态 	
	private Integer state;
    //修改时间 	
	private java.sql.Timestamp lastTime;
    //修改人 	
	private Long lastBy;
    //发票抬头类型 	
	private String billType;
    //发票抬头内容 	
	private String billTitle;
    //寄送地址 街道，门牌号 	
	private String address;
    //联系电话 	
	private String linkphone;
    //所在区域 省市区 	
	private String area;
    //纳税人识别号 	
	private String taxpayerCode;
    //开户银行 	
	private String depositBank;
    //银行账号 	
	private String bankAccount;
    //收件人姓名 	
	private String addresseeName;
    //orderId 	
	private Long orderId;
    //customerId 	
	private Long customerId;
	public OrderBill(){
	}

	public OrderBill(
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
		
	public Integer getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(Integer value) {
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
		
	public String getBillType() {
		return this.billType;
	}

	public void setBillType(String value) {
		this.billType = value;
	}
		
	public String getBillTitle() {
		return this.billTitle;
	}

	public void setBillTitle(String value) {
		this.billTitle = value;
	}
		
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String value) {
		this.address = value;
	}
		
	public String getLinkphone() {
		return this.linkphone;
	}

	public void setLinkphone(String value) {
		this.linkphone = value;
	}
		
	public String getArea() {
		return this.area;
	}

	public void setArea(String value) {
		this.area = value;
	}
		
	public String getTaxpayerCode() {
		return this.taxpayerCode;
	}

	public void setTaxpayerCode(String value) {
		this.taxpayerCode = value;
	}
		
	public String getDepositBank() {
		return this.depositBank;
	}

	public void setDepositBank(String value) {
		this.depositBank = value;
	}
		
	public String getBankAccount() {
		return this.bankAccount;
	}

	public void setBankAccount(String value) {
		this.bankAccount = value;
	}
		
	public String getAddresseeName() {
		return this.addresseeName;
	}

	public void setAddresseeName(String value) {
		this.addresseeName = value;
	}
		
	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long value) {
		this.orderId = value;
	}
		
	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long value) {
		this.customerId = value;
	}

public String toString() {
	return new ToStringBuilder(this,org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE)
		.append("Id",getId())
		.append("CreateBy",getCreateBy())
		.append("CreateTime",getCreateTime())
		.append("State",getState())
		.append("LastTime",getLastTime())
		.append("LastBy",getLastBy())
		.append("BillType",getBillType())
		.append("BillTitle",getBillTitle())
		.append("Address",getAddress())
		.append("Linkphone",getLinkphone())
		.append("Area",getArea())
		.append("TaxpayerCode",getTaxpayerCode())
		.append("DepositBank",getDepositBank())
		.append("BankAccount",getBankAccount())
		.append("AddresseeName",getAddresseeName())
		.append("OrderId",getOrderId())
		.append("CustomerId",getCustomerId())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof OrderBill == false) return false;
	if(this == obj) return true;
	OrderBill other = (OrderBill)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
