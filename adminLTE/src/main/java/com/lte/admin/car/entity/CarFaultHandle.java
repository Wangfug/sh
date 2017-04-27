package com.lte.admin.car.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class CarFaultHandle implements Serializable{
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
	private String faultComponengt;
    //附件 	
	private String handleBy;
    //faultOrder 	
	private Long faultOrder;
    //outDangerNo 	
	private Long outDangerNo;
    //getMoney 	
	private Double getMoney;
    //compensator 	
	private String compensator;
    //repairMoney 	
	private Double repairMoney;
    //provideShop 	
	private Long provideShop;
    //faultTime 	
	private java.sql.Timestamp faultTime;
    //repairTime 	
	private java.sql.Timestamp repairTime;
    //faultDescr 	
	private String faultDescr;
    //remark 	
	private String remark;
    //attachment 	
	private String attachment;
	public CarFaultHandle(){
	}

	public CarFaultHandle(
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
		
	public String getFaultComponengt() {
		return this.faultComponengt;
	}

	public void setFaultComponengt(String value) {
		this.faultComponengt = value;
	}
		
	public String getHandleBy() {
		return this.handleBy;
	}

	public void setHandleBy(String value) {
		this.handleBy = value;
	}
		
	public Long getFaultOrder() {
		return this.faultOrder;
	}

	public void setFaultOrder(Long value) {
		this.faultOrder = value;
	}
		
	public Long getOutDangerNo() {
		return this.outDangerNo;
	}

	public void setOutDangerNo(Long value) {
		this.outDangerNo = value;
	}
		
	public Double getGetMoney() {
		return this.getMoney;
	}

	public void setGetMoney(Double value) {
		this.getMoney = value;
	}
		
	public String getCompensator() {
		return this.compensator;
	}

	public void setCompensator(String value) {
		this.compensator = value;
	}
		
	public Double getRepairMoney() {
		return this.repairMoney;
	}

	public void setRepairMoney(Double value) {
		this.repairMoney = value;
	}
		
	public Long getProvideShop() {
		return this.provideShop;
	}

	public void setProvideShop(Long value) {
		this.provideShop = value;
	}
		
	public java.sql.Timestamp getFaultTime() {
		return this.faultTime;
	}

	public void setFaultTime(java.sql.Timestamp value) {
		this.faultTime = value;
	}
		
	public java.sql.Timestamp getRepairTime() {
		return this.repairTime;
	}

	public void setRepairTime(java.sql.Timestamp value) {
		this.repairTime = value;
	}
		
	public String getFaultDescr() {
		return this.faultDescr;
	}

	public void setFaultDescr(String value) {
		this.faultDescr = value;
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
		.append("FaultComponengt",getFaultComponengt())
		.append("HandleBy",getHandleBy())
		.append("FaultOrder",getFaultOrder())
		.append("OutDangerNo",getOutDangerNo())
		.append("GetMoney",getGetMoney())
		.append("Compensator",getCompensator())
		.append("RepairMoney",getRepairMoney())
		.append("ProvideShop",getProvideShop())
		.append("FaultTime",getFaultTime())
		.append("RepairTime",getRepairTime())
		.append("FaultDescr",getFaultDescr())
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
	if(obj instanceof CarFaultHandle == false) return false;
	if(this == obj) return true;
	CarFaultHandle other = (CarFaultHandle)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
