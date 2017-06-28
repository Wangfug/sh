package com.lte.admin.car.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Andy
 */
public class CarAttachApply implements Serializable{
	private static final long serialVersionUID = 72L;
    //编号
	private Long id;
    //创建时间
	private java.sql.Timestamp createTime;
    //状态
	private Integer state;

	private String driveLicImg;
    //otherRemark
	private String carAttachDetail;
    //remark
	private String carInfo;
	//申请人
	private Long customerId;
	//申请挂靠门店
	private Long carShop;

	public Long getCarShop() {
		return carShop;
	}

	public void setCarShop(Long carShop) {
		this.carShop = carShop;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDriveLicImg() {
		return driveLicImg;
	}

	public void setDriveLicImg(String driveLicImg) {
		this.driveLicImg = driveLicImg;
	}

	public String getCarAttachDetail() {
		return carAttachDetail;
	}

	public void setCarAttachDetail(String carAttachDetail) {
		this.carAttachDetail = carAttachDetail;
	}

	public String getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(String carInfo) {
		this.carInfo = carInfo;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public String toString() {
		return new ToStringBuilder(this, org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE)
				.append("Id", getId())
				.append("CreateTime", getCreateTime())
				.append("State", getState())
				.append("DriveLicImg", getDriveLicImg())
				.append("CarAttachDetail", getCarAttachDetail())
				.append("CustomerId", getCustomerId())
				.append("CarInfo", getCarInfo())
				.toString();
	}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof CarAttachApply == false) return false;
	if(this == obj) return true;
	CarAttachApply other = (CarAttachApply)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
	}
}
