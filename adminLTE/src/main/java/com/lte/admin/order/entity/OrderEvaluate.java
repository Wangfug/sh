package com.lte.admin.order.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class OrderEvaluate implements Serializable{
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
    //评分 	
	private String grade;
    //评价内容 100字 	
	private String content;
    //车辆情况 	
	private String vehicleCondition;
    //取车服务 	
	private String getVehicleService;
    //还车服务 	
	private String returnVehicleService;
    //openOrder 	
	private String openOrder;
    //closeOrder 	
	private String closeOrder;
    //totalService 	
	private String totalService;
    //orderId 	
	private String orderNo;
    //attachment 	
	private String attachment;
    //openOpinion 	
	private String openOpinion;
    //closeOpinion 	
	private String closeOpinion;
    //totalOpinion 	
	private String totalOpinion;
	public OrderEvaluate(){
	}

	public OrderEvaluate(
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
		
	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String value) {
		this.grade = value;
	}
		
	public String getContent() {
		return this.content;
	}

	public void setContent(String value) {
		this.content = value;
	}
		
	public String getVehicleCondition() {
		return this.vehicleCondition;
	}

	public void setVehicleCondition(String value) {
		this.vehicleCondition = value;
	}
		
	public String getGetVehicleService() {
		return this.getVehicleService;
	}

	public void setGetVehicleService(String value) {
		this.getVehicleService = value;
	}
		
	public String getReturnVehicleService() {
		return this.returnVehicleService;
	}

	public void setReturnVehicleService(String value) {
		this.returnVehicleService = value;
	}
		
	public String getOpenOrder() {
		return this.openOrder;
	}

	public void setOpenOrder(String value) {
		this.openOrder = value;
	}
		
	public String getCloseOrder() {
		return this.closeOrder;
	}

	public void setCloseOrder(String value) {
		this.closeOrder = value;
	}
		
	public String getTotalService() {
		return this.totalService;
	}

	public void setTotalService(String value) {
		this.totalService = value;
	}
		
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String value) {
		this.orderNo = value;
	}
		
	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(String value) {
		this.attachment = value;
	}
		
	public String getOpenOpinion() {
		return this.openOpinion;
	}

	public void setOpenOpinion(String value) {
		this.openOpinion = value;
	}
		
	public String getCloseOpinion() {
		return this.closeOpinion;
	}

	public void setCloseOpinion(String value) {
		this.closeOpinion = value;
	}
		
	public String getTotalOpinion() {
		return this.totalOpinion;
	}

	public void setTotalOpinion(String value) {
		this.totalOpinion = value;
	}

public String toString() {
	return new ToStringBuilder(this,org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE)
		.append("Id",getId())
		.append("CreateBy",getCreateBy())
		.append("CreateTime",getCreateTime())
		.append("State",getState())
		.append("LastTime",getLastTime())
		.append("LastBy",getLastBy())
		.append("Grade",getGrade())
		.append("Content",getContent())
		.append("VehicleCondition",getVehicleCondition())
		.append("GetVehicleService",getGetVehicleService())
		.append("ReturnVehicleService",getReturnVehicleService())
		.append("OpenOrder",getOpenOrder())
		.append("CloseOrder",getCloseOrder())
		.append("TotalService",getTotalService())
		.append("OrderNo",getOrderNo())
		.append("Attachment",getAttachment())
		.append("OpenOpinion",getOpenOpinion())
		.append("CloseOpinion",getCloseOpinion())
		.append("TotalOpinion",getTotalOpinion())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof OrderEvaluate == false) return false;
	if(this == obj) return true;
	OrderEvaluate other = (OrderEvaluate)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
