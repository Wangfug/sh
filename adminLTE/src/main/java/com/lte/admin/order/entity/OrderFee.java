package com.lte.admin.order.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class OrderFee implements Serializable{
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
    //carRentFee 	
	private Double carRentFee;
    //sendCarFee 	
	private Double sendCarFee;
    //baseFee 	
	private Double baseFee;
    //otherFee 	
	private Double otherFee;
    //additionalBujimianpei 	
	private Double additionalBujimianpei;
    //handingCharge 	
	private Double handingCharge;
    //additionalFeeForThree 	
	private Double additionalFeeForThree;
    //orderId 	
	private Long orderId;
    //totalFee 	
	private Double totalFee;
    //preAuthorized 	
	private Double preAuthorized;
	public OrderFee(){
	}

	public OrderFee(
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
		
	public Double getCarRentFee() {
		return this.carRentFee;
	}

	public void setCarRentFee(Double value) {
		this.carRentFee = value;
	}
		
	public Double getSendCarFee() {
		return this.sendCarFee;
	}

	public void setSendCarFee(Double value) {
		this.sendCarFee = value;
	}
		
	public Double getBaseFee() {
		return this.baseFee;
	}

	public void setBaseFee(Double value) {
		this.baseFee = value;
	}
		
	public Double getOtherFee() {
		return this.otherFee;
	}

	public void setOtherFee(Double value) {
		this.otherFee = value;
	}
		
	public Double getAdditionalBujimianpei() {
		return this.additionalBujimianpei;
	}

	public void setAdditionalBujimianpei(Double value) {
		this.additionalBujimianpei = value;
	}
		
	public Double getHandingCharge() {
		return this.handingCharge;
	}

	public void setHandingCharge(Double value) {
		this.handingCharge = value;
	}
		
	public Double getAdditionalFeeForThree() {
		return this.additionalFeeForThree;
	}

	public void setAdditionalFeeForThree(Double value) {
		this.additionalFeeForThree = value;
	}
		
	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long value) {
		this.orderId = value;
	}
		
	public Double getTotalFee() {
		return this.totalFee;
	}

	public void setTotalFee(Double value) {
		this.totalFee = value;
	}
		
	public Double getPreAuthorized() {
		return this.preAuthorized;
	}

	public void setPreAuthorized(Double value) {
		this.preAuthorized = value;
	}

public String toString() {
	return new ToStringBuilder(this,org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE)
		.append("Id",getId())
		.append("CreateBy",getCreateBy())
		.append("CreateTime",getCreateTime())
		.append("State",getState())
		.append("LastTime",getLastTime())
		.append("LastBy",getLastBy())
		.append("CarRentFee",getCarRentFee())
		.append("SendCarFee",getSendCarFee())
		.append("BaseFee",getBaseFee())
		.append("OtherFee",getOtherFee())
		.append("AdditionalBujimianpei",getAdditionalBujimianpei())
		.append("HandingCharge",getHandingCharge())
		.append("AdditionalFeeForThree",getAdditionalFeeForThree())
		.append("OrderId",getOrderId())
		.append("TotalFee",getTotalFee())
		.append("PreAuthorized",getPreAuthorized())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof OrderFee == false) return false;
	if(this == obj) return true;
	OrderFee other = (OrderFee)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
