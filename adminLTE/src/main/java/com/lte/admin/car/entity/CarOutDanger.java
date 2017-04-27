package com.lte.admin.car.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class CarOutDanger implements Serializable{
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
    //证件类型 	
	private Long carId;
    //证件号码 	
	private java.sql.Timestamp insuranceTime;
    //附件 	
	private String insuranceType;
    //orderNo 	
	private Long orderNo;
    //repairMoney 	
	private Double repairMoney;
    //compensation 	
	private String compensation;
    //badComponent 	
	private String badComponent;
    //beforeImage 	
	private String beforeImage;
    //afterImage 	
	private String afterImage;
    //remark 	
	private String remark;
	public CarOutDanger(){
	}

	public CarOutDanger(
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
		
	public Long getCarId() {
		return this.carId;
	}

	public void setCarId(Long value) {
		this.carId = value;
	}
		
	public java.sql.Timestamp getInsuranceTime() {
		return this.insuranceTime;
	}

	public void setInsuranceTime(java.sql.Timestamp value) {
		this.insuranceTime = value;
	}
		
	public String getInsuranceType() {
		return this.insuranceType;
	}

	public void setInsuranceType(String value) {
		this.insuranceType = value;
	}
		
	public Long getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(Long value) {
		this.orderNo = value;
	}
		
	public Double getRepairMoney() {
		return this.repairMoney;
	}

	public void setRepairMoney(Double value) {
		this.repairMoney = value;
	}
		
	public String getCompensation() {
		return this.compensation;
	}

	public void setCompensation(String value) {
		this.compensation = value;
	}
		
	public String getBadComponent() {
		return this.badComponent;
	}

	public void setBadComponent(String value) {
		this.badComponent = value;
	}
		
	public String getBeforeImage() {
		return this.beforeImage;
	}

	public void setBeforeImage(String value) {
		this.beforeImage = value;
	}
		
	public String getAfterImage() {
		return this.afterImage;
	}

	public void setAfterImage(String value) {
		this.afterImage = value;
	}
		
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String value) {
		this.remark = value;
	}

public String toString() {
	return new ToStringBuilder(this,org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE)
		.append("Id",getId())
		.append("CreateBy",getCreateBy())
		.append("CreateTime",getCreateTime())
		.append("State",getState())
		.append("LastTime",getLastTime())
		.append("LastBy",getLastBy())
		.append("CarId",getCarId())
		.append("InsuranceTime",getInsuranceTime())
		.append("InsuranceType",getInsuranceType())
		.append("OrderNo",getOrderNo())
		.append("RepairMoney",getRepairMoney())
		.append("Compensation",getCompensation())
		.append("BadComponent",getBadComponent())
		.append("BeforeImage",getBeforeImage())
		.append("AfterImage",getAfterImage())
		.append("Remark",getRemark())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof CarOutDanger == false) return false;
	if(this == obj) return true;
	CarOutDanger other = (CarOutDanger)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
