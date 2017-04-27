package com.lte.admin.car.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class CarMaintenance implements Serializable{
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
	private String maintenanceBy;
    //附件 	
	private java.sql.Timestamp maintenanceTime;
    //maintenanceOrder 	
	private String maintenanceOrder;
    //maintenanceContent 	
	private String maintenanceContent;
    //maintenanceMoney 	
	private Double maintenanceMoney;
    //remark 	
	private String remark;
    //attachment 	
	private String attachment;
	public CarMaintenance(){
	}

	public CarMaintenance(
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
		
	public String getMaintenanceBy() {
		return this.maintenanceBy;
	}

	public void setMaintenanceBy(String value) {
		this.maintenanceBy = value;
	}
		
	public java.sql.Timestamp getMaintenanceTime() {
		return this.maintenanceTime;
	}

	public void setMaintenanceTime(java.sql.Timestamp value) {
		this.maintenanceTime = value;
	}
		
	public String getMaintenanceOrder() {
		return this.maintenanceOrder;
	}

	public void setMaintenanceOrder(String value) {
		this.maintenanceOrder = value;
	}
		
	public String getMaintenanceContent() {
		return this.maintenanceContent;
	}

	public void setMaintenanceContent(String value) {
		this.maintenanceContent = value;
	}
		
	public Double getMaintenanceMoney() {
		return this.maintenanceMoney;
	}

	public void setMaintenanceMoney(Double value) {
		this.maintenanceMoney = value;
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
		.append("MaintenanceBy",getMaintenanceBy())
		.append("MaintenanceTime",getMaintenanceTime())
		.append("MaintenanceOrder",getMaintenanceOrder())
		.append("MaintenanceContent",getMaintenanceContent())
		.append("MaintenanceMoney",getMaintenanceMoney())
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
	if(obj instanceof CarMaintenance == false) return false;
	if(this == obj) return true;
	CarMaintenance other = (CarMaintenance)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
