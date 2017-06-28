package com.lte.admin.car.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class CarAttach implements Serializable{
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
    //carId 	
	private Long carId;
    //违章罚款 	
	private Long customerId;
    //otherRemark 	
	private String otherRemark;
    //attachStart 	
	private java.sql.Timestamp attachStart;
    //attachEnd 	
	private java.sql.Timestamp attachEnd;
    //remark 	
	private String remark;
    //attachement 	
	private String attachement;
	//状态
	private Integer type;
	private String carCode;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public CarAttach(){
	}

	public String getCarCode() {
		return carCode;
	}

	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}

	public CarAttach(
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
		
	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long value) {
		this.customerId = value;
	}
		
	public String getOtherRemark() {
		return this.otherRemark;
	}

	public void setOtherRemark(String value) {
		this.otherRemark = value;
	}
		
	public java.sql.Timestamp getAttachStart() {
		return this.attachStart;
	}

	public void setAttachStart(java.sql.Timestamp value) {
		this.attachStart = value;
	}
		
	public java.sql.Timestamp getAttachEnd() {
		return this.attachEnd;
	}

	public void setAttachEnd(java.sql.Timestamp value) {
		this.attachEnd = value;
	}
		
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String value) {
		this.remark = value;
	}
		
	public String getAttachement() {
		return this.attachement;
	}

	public void setAttachement(String value) {
		this.attachement = value;
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
		.append("CustomerId",getCustomerId())
		.append("OtherRemark",getOtherRemark())
		.append("AttachStart",getAttachStart())
		.append("AttachEnd",getAttachEnd())
		.append("Remark",getRemark())
		.append("Attachement",getAttachement())
			.append("Type",getType())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof CarAttach == false) return false;
	if(this == obj) return true;
	CarAttach other = (CarAttach)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
