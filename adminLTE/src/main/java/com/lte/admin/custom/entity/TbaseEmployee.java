package com.lte.admin.custom.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class TbaseEmployee implements Serializable{
	private static final long serialVersionUID = 72L;
	
    //编号 	
	private Long id;
    //创建人编号 	
	private String createBy;
    //创建时间 	
	private java.sql.Timestamp createTime;
    //状态 	
	private Integer state;
    //修改时间 	
	private java.sql.Timestamp lastTime;
    //修改人 	
	private Long lastBy;
    //员工职务 	
//	private String job;
    //工号 	
	private String eno;
    //所属门店 	
//	private Long carShop;
    //attachment 	
	private String attachment;
    //员工姓名 	
//	private String ename;
    //电话 	
//	private String ephone;
	//移动端识别密码
	private String token;
	//邮箱
	private String email;
	//证件
	private Long credential;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public TbaseEmployee(){
	}

	public TbaseEmployee(
		Long id
	){
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getCredential() {
		return credential;
	}

	public void setCredential(Long credential) {
		this.credential = credential;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long value) {
		this.id = value;
	}
		
	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String value) {
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
		

	public String getEno() {
		return this.eno;
	}

	public void setEno(String value) {
		this.eno = value;
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
		.append("Eno",getEno())
		.append("Attachment",getAttachment())
		.append("token",getToken())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof TbaseEmployee == false) return false;
	if(this == obj) return true;
	TbaseEmployee other = (TbaseEmployee)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
