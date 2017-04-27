package com.lte.admin.car.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class CarInsurance implements Serializable{
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
	private java.sql.Timestamp insuranceStarttime;
    //附件 	
	private java.sql.Timestamp insuranceEndtime;
    //insuranceComp 	
	private String insuranceComp;
    //insuranceType 	
	private String insuranceType;
    //insuranceBy 	
	private String insuranceBy;
    //insuranceSalesman 	
	private String insuranceSalesman;
    //insuranceTypeMoney 	
	private Double insuranceTypeMoney;
    //totalMoney 	
	private Double totalMoney;
    //remark 	
	private String remark;
    //attachment 	
	private String attachment;
	public CarInsurance(){
	}

	public CarInsurance(
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
		
	public java.sql.Timestamp getInsuranceStarttime() {
		return this.insuranceStarttime;
	}

	public void setInsuranceStarttime(java.sql.Timestamp value) {
		this.insuranceStarttime = value;
	}
		
	public java.sql.Timestamp getInsuranceEndtime() {
		return this.insuranceEndtime;
	}

	public void setInsuranceEndtime(java.sql.Timestamp value) {
		this.insuranceEndtime = value;
	}
		
	public String getInsuranceComp() {
		return this.insuranceComp;
	}

	public void setInsuranceComp(String value) {
		this.insuranceComp = value;
	}
		
	public String getInsuranceType() {
		return this.insuranceType;
	}

	public void setInsuranceType(String value) {
		this.insuranceType = value;
	}
		
	public String getInsuranceBy() {
		return this.insuranceBy;
	}

	public void setInsuranceBy(String value) {
		this.insuranceBy = value;
	}
		
	public String getInsuranceSalesman() {
		return this.insuranceSalesman;
	}

	public void setInsuranceSalesman(String value) {
		this.insuranceSalesman = value;
	}
		
	public Double getInsuranceTypeMoney() {
		return this.insuranceTypeMoney;
	}

	public void setInsuranceTypeMoney(Double value) {
		this.insuranceTypeMoney = value;
	}
		
	public Double getTotalMoney() {
		return this.totalMoney;
	}

	public void setTotalMoney(Double value) {
		this.totalMoney = value;
	}
		
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String value) {
		this.remark = value;
	}
		
	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(String value) {
		this.attachment = value;
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
		.append("InsuranceStarttime",getInsuranceStarttime())
		.append("InsuranceEndtime",getInsuranceEndtime())
		.append("InsuranceComp",getInsuranceComp())
		.append("InsuranceType",getInsuranceType())
		.append("InsuranceBy",getInsuranceBy())
		.append("InsuranceSalesman",getInsuranceSalesman())
		.append("InsuranceTypeMoney",getInsuranceTypeMoney())
		.append("TotalMoney",getTotalMoney())
		.append("Remark",getRemark())
		.append("Attachment",getAttachment())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof CarInsurance == false) return false;
	if(this == obj) return true;
	CarInsurance other = (CarInsurance)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
