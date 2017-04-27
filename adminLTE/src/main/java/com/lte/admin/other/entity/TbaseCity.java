package com.lte.admin.other.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class TbaseCity implements Serializable{
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
    //准驾车型 	
	private String cityName;
    //领证时间 	
	private String cityPinyin;
    //档案编号 	
	private String cityThreeCode;
    //cityFirstWord 	
	private String cityFirstWord;
    //parentCity 	
	private Long parentCity;
	public TbaseCity(){
	}

	public TbaseCity(
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
		
	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String value) {
		this.cityName = value;
	}
		
	public String getCityPinyin() {
		return this.cityPinyin;
	}

	public void setCityPinyin(String value) {
		this.cityPinyin = value;
	}
		
	public String getCityThreeCode() {
		return this.cityThreeCode;
	}

	public void setCityThreeCode(String value) {
		this.cityThreeCode = value;
	}
		
	public String getCityFirstWord() {
		return this.cityFirstWord;
	}

	public void setCityFirstWord(String value) {
		this.cityFirstWord = value;
	}
		
	public Long getParentCity() {
		return this.parentCity;
	}

	public void setParentCity(Long value) {
		this.parentCity = value;
	}

public String toString() {
	return new ToStringBuilder(this,org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE)
		.append("Id",getId())
		.append("CreateBy",getCreateBy())
		.append("CreateTime",getCreateTime())
		.append("State",getState())
		.append("LastTime",getLastTime())
		.append("LastBy",getLastBy())
		.append("CityName",getCityName())
		.append("CityPinyin",getCityPinyin())
		.append("CityThreeCode",getCityThreeCode())
		.append("CityFirstWord",getCityFirstWord())
		.append("ParentCity",getParentCity())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof TbaseCity == false) return false;
	if(this == obj) return true;
	TbaseCity other = (TbaseCity)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
