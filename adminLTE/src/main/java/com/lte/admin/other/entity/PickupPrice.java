package com.lte.admin.other.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class PickupPrice implements Serializable{
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
    //证件号码 	
	private String city;
    //附件 	
	private String area;
    //starttime 	
	private java.sql.Timestamp starttime;
    //endtime 	
	private java.sql.Timestamp endtime;
    //scopePrice 	
	private Double scopePrice;
    //remark 	
	private String remark;
    //beyondPrice 	
	private Double beyondPrice;
    //regulationScope 	
	private String regulationScope;
    //regulationUnit 	
	private String regulationUnit;
    //beyondUnit 	
	private String beyondUnit;
	public PickupPrice(){
	}

	public PickupPrice(
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
		
	public java.sql.Timestamp getStarttime() {
		return this.starttime;
	}

	public void setStarttime(java.sql.Timestamp value) {
		this.starttime = value;
	}
		
	public java.sql.Timestamp getEndtime() {
		return this.endtime;
	}

	public void setEndtime(java.sql.Timestamp value) {
		this.endtime = value;
	}
		
	public Double getScopePrice() {
		return this.scopePrice;
	}

	public void setScopePrice(Double value) {
		this.scopePrice = value;
	}
		
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String value) {
		this.remark = value;
	}
		
	public Double getBeyondPrice() {
		return this.beyondPrice;
	}

	public void setBeyondPrice(Double value) {
		this.beyondPrice = value;
	}
		
	public String getRegulationScope() {
		return this.regulationScope;
	}

	public void setRegulationScope(String value) {
		this.regulationScope = value;
	}
		
	public String getRegulationUnit() {
		return this.regulationUnit;
	}

	public void setRegulationUnit(String value) {
		this.regulationUnit = value;
	}
		
	public String getBeyondUnit() {
		return this.beyondUnit;
	}

	public void setBeyondUnit(String value) {
		this.beyondUnit = value;
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
		.append("Starttime",getStarttime())
		.append("Endtime",getEndtime())
		.append("ScopePrice",getScopePrice())
		.append("Remark",getRemark())
		.append("BeyondPrice",getBeyondPrice())
		.append("RegulationScope",getRegulationScope())
		.append("RegulationUnit",getRegulationUnit())
		.append("BeyondUnit",getBeyondUnit())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof PickupPrice == false) return false;
	if(this == obj) return true;
	PickupPrice other = (PickupPrice)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
