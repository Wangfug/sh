package com.lte.admin.order.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class OrderWork implements Serializable{
	private static final long serialVersionUID = 72L;
	
    //编号 	
	private Long id;
    //创建人编号 	
	private Long createBy;
    //创建时间 	
	private java.sql.Timestamp createTime;
    //状态 	
	private Integer orderType;
    //修改时间 	
	private java.sql.Timestamp lastTime;
    //修改人 	
	private Long lastBy;
    //way 	
	private String way;
    //address 	
	private String address;
    //person 	
	private String person;
    //orderState 	
	private String orderState;
    //carShop 	
	private Long carShop;
    //eno 	
	private Long eno;
    //orderId 	
	private Long orderId;
    //attachment 	
	private String attachment;
    //carId 	
	private Long carId;
    //carCheckDetail 	
	private String carCheckDetail;
	public OrderWork(){
	}

	public OrderWork(
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
		
	public Integer getOrderType() {
		return this.orderType;
	}

	public void setOrderType(Integer value) {
		this.orderType = value;
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
		
	public String getWay() {
		return this.way;
	}

	public void setWay(String value) {
		this.way = value;
	}
		
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String value) {
		this.address = value;
	}
		
	public String getPerson() {
		return this.person;
	}

	public void setPerson(String value) {
		this.person = value;
	}
		
	public String getOrderState() {
		return this.orderState;
	}

	public void setOrderState(String value) {
		this.orderState = value;
	}
		
	public Long getCarShop() {
		return this.carShop;
	}

	public void setCarShop(Long value) {
		this.carShop = value;
	}
		
	public Long getEno() {
		return this.eno;
	}

	public void setEno(Long value) {
		this.eno = value;
	}
		
	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long value) {
		this.orderId = value;
	}
		
	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(String value) {
		this.attachment = value;
	}
		
	public Long getCarId() {
		return this.carId;
	}

	public void setCarId(Long value) {
		this.carId = value;
	}
		
	public String getCarCheckDetail() {
		return this.carCheckDetail;
	}

	public void setCarCheckDetail(String value) {
		this.carCheckDetail = value;
	}

public String toString() {
	return new ToStringBuilder(this,org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE)
		.append("Id",getId())
		.append("CreateBy",getCreateBy())
		.append("CreateTime",getCreateTime())
		.append("OrderType",getOrderType())
		.append("LastTime",getLastTime())
		.append("LastBy",getLastBy())
		.append("Way",getWay())
		.append("Address",getAddress())
		.append("Person",getPerson())
		.append("OrderState",getOrderState())
		.append("CarShop",getCarShop())
		.append("Eno",getEno())
		.append("OrderId",getOrderId())
		.append("Attachment",getAttachment())
		.append("CarId",getCarId())
		.append("CarCheckDetail",getCarCheckDetail())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof OrderWork == false) return false;
	if(this == obj) return true;
	OrderWork other = (OrderWork)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
