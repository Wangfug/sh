package com.lte.admin.car.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class CarOutOrIn implements Serializable{
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
	private String reason;
    //附件 	
	private Long approveBy;
    //outEmp 	
	private Long outEmp;
    //outPosition 	
	private String outPosition;
    //dispatchNo 	
	private String dispatchNo;
    //outTime 	
	private java.sql.Timestamp outTime;
    //remark 	
	private String remark;
    //inOrOut 	
	private String inOrOut;
    //carShop 	
	private Long carShop;
	public CarOutOrIn(){
	}

	public CarOutOrIn(
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
		
	public String getReason() {
		return this.reason;
	}

	public void setReason(String value) {
		this.reason = value;
	}
		
	public Long getApproveBy() {
		return this.approveBy;
	}

	public void setApproveBy(Long value) {
		this.approveBy = value;
	}
		
	public Long getOutEmp() {
		return this.outEmp;
	}

	public void setOutEmp(Long value) {
		this.outEmp = value;
	}
		
	public String getOutPosition() {
		return this.outPosition;
	}

	public void setOutPosition(String value) {
		this.outPosition = value;
	}
		
	public String getDispatchNo() {
		return this.dispatchNo;
	}

	public void setDispatchNo(String value) {
		this.dispatchNo = value;
	}
		
	public java.sql.Timestamp getOutTime() {
		return this.outTime;
	}

	public void setOutTime(java.sql.Timestamp value) {
		this.outTime = value;
	}
		
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String value) {
		this.remark = value;
	}
		
	public String getInOrOut() {
		return this.inOrOut;
	}

	public void setInOrOut(String value) {
		this.inOrOut = value;
	}
		
	public Long getCarShop() {
		return this.carShop;
	}

	public void setCarShop(Long value) {
		this.carShop = value;
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
		.append("Reason",getReason())
		.append("ApproveBy",getApproveBy())
		.append("OutEmp",getOutEmp())
		.append("OutPosition",getOutPosition())
		.append("DispatchNo",getDispatchNo())
		.append("OutTime",getOutTime())
		.append("Remark",getRemark())
		.append("InOrOut",getInOrOut())
		.append("CarShop",getCarShop())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof CarOutOrIn == false) return false;
	if(this == obj) return true;
	CarOutOrIn other = (CarOutOrIn)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
