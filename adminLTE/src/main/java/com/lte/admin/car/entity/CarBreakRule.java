package com.lte.admin.car.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Andy
 */
public class CarBreakRule implements Serializable{
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
    //illegalContent 	
	private String illegalContent;
    //违章罚款 	
	private String violationFine;
    //违章扣费 	
	private String illegalDeduction;
    //customerId 	
	private Long customerId;
    //attachment 	
	private String attachment;
    //carId 	
	private Long carId;

	private java.sql.Timestamp illegalTime;

	private String illegalNo;
	 private Long dealShop;
	private String illegalPosition;
	private String remark;
	private String orderNo;
	public CarBreakRule(){
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public CarBreakRule(
		Long id
	){
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIllegalPosition() {
		return illegalPosition;
	}

	public void setIllegalPosition(String illegalPosition) {
		this.illegalPosition = illegalPosition;
	}

	public Timestamp getIllegalTime() {
		return illegalTime;
	}

	public void setIllegalTime(Timestamp illegalTime) {
		this.illegalTime = illegalTime;
	}

	public String getIllegalNo() {
		return illegalNo;
	}

	public void setIllegalNo(String illegalNo) {
		this.illegalNo = illegalNo;
	}

	public Long getDealShop() {
		return dealShop;
	}

	public void setDealShop(Long dealShop) {
		this.dealShop = dealShop;
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
		
	public String getIllegalContent() {
		return this.illegalContent;
	}

	public void setIllegalContent(String value) {
		this.illegalContent = value;
	}
		
	public String getViolationFine() {
		return this.violationFine;
	}

	public void setViolationFine(String value) {
		this.violationFine = value;
	}
		
	public String getIllegalDeduction() {
		return this.illegalDeduction;
	}

	public void setIllegalDeduction(String value) {
		this.illegalDeduction = value;
	}
		
	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long value) {
		this.customerId = value;
	}
		
	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(String value) {
		this.attachment = value;
	}
		
	public Long getCarId() {
		return this.carId;
	}

	public void setCarId(Long value) {
		this.carId = value;
	}

public String toString() {
	return new ToStringBuilder(this,org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE)
		.append("id",getId())
		.append("CreateBy",getCreateBy())
		.append("CreateTime",getCreateTime())
		.append("State",getState())
		.append("LastTime",getLastTime())
		.append("LastBy",getLastBy())
		.append("IllegalContent",getIllegalContent())
		.append("ViolationFine",getViolationFine())
		.append("IllegalDeduction",getIllegalDeduction())
		.append("CustomerId",getCustomerId())
		.append("Attachment",getAttachment())
		.append("CarId",getCarId())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof CarBreakRule == false) return false;
	if(this == obj) return true;
	CarBreakRule other = (CarBreakRule)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
