package com.lte.admin.other.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class ExceptionThrow implements Serializable{
	private static final long serialVersionUID = 72L;
	
    //编号 	
	private Long id;
    //创建人编号 	
	private Long createBy;
    //创建时间 	
	private java.sql.Timestamp createTime;
    //状态 	
	private Long exceptionOrder;
    //handleBy 	
	private String handleBy;
    //isHandle 	
	private String isHandle;
	public ExceptionThrow(){
	}

	public ExceptionThrow(
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
		
	public Long getExceptionOrder() {
		return this.exceptionOrder;
	}

	public void setExceptionOrder(Long value) {
		this.exceptionOrder = value;
	}
		
	public String getHandleBy() {
		return this.handleBy;
	}

	public void setHandleBy(String value) {
		this.handleBy = value;
	}
		
	public String getIsHandle() {
		return this.isHandle;
	}

	public void setIsHandle(String value) {
		this.isHandle = value;
	}

public String toString() {
	return new ToStringBuilder(this,org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE)
		.append("Id",getId())
		.append("CreateBy",getCreateBy())
		.append("CreateTime",getCreateTime())
		.append("ExceptionOrder",getExceptionOrder())
		.append("HandleBy",getHandleBy())
		.append("IsHandle",getIsHandle())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof ExceptionThrow == false) return false;
	if(this == obj) return true;
	ExceptionThrow other = (ExceptionThrow)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
