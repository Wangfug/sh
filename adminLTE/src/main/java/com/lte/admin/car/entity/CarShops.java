package com.lte.admin.car.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class CarShops implements Serializable{
	private static final long serialVersionUID = 72L;
	
    //编号 	
	private Long id;
    //店铺名称 	
	private String shopName;
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
    //地址 	
	private String address;
    //店铺图片 	
	private String attachment;
    //评价 	
	private String evaluate;
    //营业时间 	
	private String businessStart;
    //电话 	
	private String phone;
    //直线 	
	private String stiffPhone;
    //shopType 	
	private String shopType;
    //country 	
	private String country;
    //province 	
	private String province;
    //city 	
	private String city;
    //area 	
	private String area;
    //businessEnd 	
	private java.sql.Timestamp businessEnd;
    //shopManager 	
	private Long shopManager;
    //postcode 	
	private String postcode;
    //remark 	
	private String remark;
	public CarShops(){
	}

	public CarShops(
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
		
	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String value) {
		this.shopName = value;
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
		
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String value) {
		this.address = value;
	}
		
	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(String value) {
		this.attachment = value;
	}
		
	public String getEvaluate() {
		return this.evaluate;
	}

	public void setEvaluate(String value) {
		this.evaluate = value;
	}
		
	public String getBusinessStart() {
		return this.businessStart;
	}

	public void setBusinessStart(String value) {
		this.businessStart = value;
	}
		
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String value) {
		this.phone = value;
	}
		
	public String getStiffPhone() {
		return this.stiffPhone;
	}

	public void setStiffPhone(String value) {
		this.stiffPhone = value;
	}
		
	public String getShopType() {
		return this.shopType;
	}

	public void setShopType(String value) {
		this.shopType = value;
	}
		
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String value) {
		this.country = value;
	}
		
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String value) {
		this.province = value;
	}
		
	public String getCity() {
		return this.city;
	}

	public void setCity(String value) {
		this.city = value;
	}
		
	public String getArea() {
		return this.area;
	}

	public void setArea(String value) {
		this.area = value;
	}
		
	public java.sql.Timestamp getBusinessEnd() {
		return this.businessEnd;
	}

	public void setBusinessEnd(java.sql.Timestamp value) {
		this.businessEnd = value;
	}
		
	public Long getShopManager() {
		return this.shopManager;
	}

	public void setShopManager(Long value) {
		this.shopManager = value;
	}
		
	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String value) {
		this.postcode = value;
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
		.append("ShopName",getShopName())
		.append("CreateBy",getCreateBy())
		.append("CreateTime",getCreateTime())
		.append("State",getState())
		.append("LastTime",getLastTime())
		.append("LastBy",getLastBy())
		.append("Address",getAddress())
		.append("Attachment",getAttachment())
		.append("Evaluate",getEvaluate())
		.append("BusinessStart",getBusinessStart())
		.append("Phone",getPhone())
		.append("StiffPhone",getStiffPhone())
		.append("ShopType",getShopType())
		.append("Country",getCountry())
		.append("Province",getProvince())
		.append("City",getCity())
		.append("Area",getArea())
		.append("BusinessEnd",getBusinessEnd())
		.append("ShopManager",getShopManager())
		.append("Postcode",getPostcode())
		.append("Remark",getRemark())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof CarShops == false) return false;
	if(this == obj) return true;
	CarShops other = (CarShops)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
