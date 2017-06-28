package com.lte.admin.other.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class ActivityInv implements Serializable{
	private static final long serialVersionUID = 72L;
	
    //编号 	
	private Long id;
    //创建时间 	
	private java.sql.Timestamp createTime;
    //状态 	
	private Integer state;
    //证件类型 	
	private Long activityId;
    //证件号码 	
	private Long activityInv;
	//证件号码
	private String failReason;
	public ActivityInv(){
	}

	public String getFailReason() {
		return failReason;
	}
	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	public ActivityInv(
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
		
	public Long getActivityId() {
		return this.activityId;
	}

	public void setActivityId(Long value) {
		this.activityId = value;
	}
		
	public Long getActivityInv() {
		return this.activityInv;
	}

	public void setActivityInv(Long value) {
		this.activityInv = value;
	}

public String toString() {
	return new ToStringBuilder(this,org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE)
		.append("Id",getId())
		.append("CreateTime",getCreateTime())
		.append("State",getState())
		.append("ActivityId",getActivityId())
		.append("ActivityInv",getActivityInv())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof ActivityInv == false) return false;
	if(this == obj) return true;
	ActivityInv other = (ActivityInv)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
