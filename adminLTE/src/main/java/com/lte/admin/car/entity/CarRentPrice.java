package com.lte.admin.car.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class CarRentPrice implements Serializable{
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
	private String city;
    //证件号码 	
	private String area;
    //附件 	
	private String brand;
    //model 	
	private String model;
    //pricdeByDay 	
	private Double pricdeByDay;
    //priceByHour 	
	private Double priceByHour;
    //carShop 	
	private Long carShop;
	public CarRentPrice(){
	}

	public CarRentPrice(
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
		
	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String value) {
		this.brand = value;
	}
		
	public String getModel() {
		return this.model;
	}

	public void setModel(String value) {
		this.model = value;
	}
		
	public Double getPricdeByDay() {
		return this.pricdeByDay;
	}

	public void setPricdeByDay(Double value) {
		this.pricdeByDay = value;
	}
		
	public Double getPriceByHour() {
		return this.priceByHour;
	}

	public void setPriceByHour(Double value) {
		this.priceByHour = value;
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
		.append("City",getCity())
		.append("Area",getArea())
		.append("Brand",getBrand())
		.append("Model",getModel())
		.append("PricdeByDay",getPricdeByDay())
		.append("PriceByHour",getPriceByHour())
		.append("CarShop",getCarShop())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof CarRentPrice == false) return false;
	if(this == obj) return true;
	CarRentPrice other = (CarRentPrice)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
