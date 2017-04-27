package com.lte.admin.other.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class CompAssociated implements Serializable{
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
    //comName 	
	private String comName;
    //违章罚款 	
	private String corporation;
    //country 	
	private String country;
    //province 	
	private String province;
    //area 	
	private String area;
    //comAddress 	
	private String comAddress;
    //phone 	
	private String phone;
    //remark 	
	private String remark;
    //attachment 	
	private String attachment;
	public CompAssociated(){
	}

	public CompAssociated(
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
		
	public String getComName() {
		return this.comName;
	}

	public void setComName(String value) {
		this.comName = value;
	}
		
	public String getCorporation() {
		return this.corporation;
	}

	public void setCorporation(String value) {
		this.corporation = value;
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
		
	public String getArea() {
		return this.area;
	}

	public void setArea(String value) {
		this.area = value;
	}
		
	public String getComAddress() {
		return this.comAddress;
	}

	public void setComAddress(String value) {
		this.comAddress = value;
	}
		
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String value) {
		this.phone = value;
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
		.append("ComName",getComName())
		.append("Corporation",getCorporation())
		.append("Country",getCountry())
		.append("Province",getProvince())
		.append("Area",getArea())
		.append("ComAddress",getComAddress())
		.append("Phone",getPhone())
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
	if(obj instanceof CompAssociated == false) return false;
	if(this == obj) return true;
	CompAssociated other = (CompAssociated)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
