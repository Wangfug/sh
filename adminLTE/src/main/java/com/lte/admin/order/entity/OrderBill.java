package com.lte.admin.order.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Andy
 */
public class OrderBill implements Serializable{
	private static final long serialVersionUID = 72L;
	
    //编号 	
	private Long id;
    //创建人编号 	
	private Long createBy;
    //创建时间 	
	private java.sql.Timestamp createTime;
    //状态 	
	private Integer state;
    //修改时间 	
	private java.sql.Timestamp lastTime;
    //修改人 	
	private Long lastBy;
    //发票抬头类型 	1--普通发票 2--增值税发票
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
	private String orderNo;
    //customerId 	
	private Long customerId;
	//是否只读  0不是  1只读
	private Integer readonly;

	private String expressNo;//快递单号

	private String express;//快递公司

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Timestamp getLastTime() {
		return lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

	public Long getLastBy() {
		return lastBy;
	}

	public void setLastBy(Long lastBy) {
		this.lastBy = lastBy;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getBillTitle() {
		return billTitle;
	}

	public void setBillTitle(String billTitle) {
		this.billTitle = billTitle;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLinkphone() {
		return linkphone;
	}

	public void setLinkphone(String linkphone) {
		this.linkphone = linkphone;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTaxpayerCode() {
		return taxpayerCode;
	}

	public void setTaxpayerCode(String taxpayerCode) {
		this.taxpayerCode = taxpayerCode;
	}

	public String getDepositBank() {
		return depositBank;
	}

	public void setDepositBank(String depositBank) {
		this.depositBank = depositBank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getAddresseeName() {
		return addresseeName;
	}

	public void setAddresseeName(String addresseeName) {
		this.addresseeName = addresseeName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Integer getReadonly() {
		return readonly;
	}

	public void setReadonly(Integer readonly) {
		this.readonly = readonly;
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
		.append("OrderNo",getOrderNo())
		.append("CustomerId",getCustomerId())
			.append("Readonly",getReadonly())
			.append("Express",getExpress())
			.append("ExpressNo",getExpressNo())
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
