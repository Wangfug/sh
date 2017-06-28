package com.lte.admin.order.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class OrderInfo implements Serializable{
	private static final long serialVersionUID = 72L;
	
    //订单号 	
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
    //customer 	
	private Long customer;
    //orderNo 	
	private String orderNo;
    //开始时间 	
	private java.sql.Timestamp realTimeStart;
    //结束时间 	
	private java.sql.Timestamp realTimeEnd;
    //totalFee 	
	private Double totalFee;
    //reserveTimeStart 	
	private java.sql.Timestamp reserveTimeStart;
    //payTime 	
	private java.sql.Timestamp payTime;
    //payWay 	
	private String payWay;
    //payAccount 	
	private String payAccount;
    //reserveType 	
	private String reserveType;
    //carId 	
	private Long carId;
    //reserveTimeEnd 	
	private java.sql.Timestamp reserveTimeEnd;
    //realCarType 	
	private String realCarType;
    //realPay 	
	private Double realPay;
    //finalFee 	
	private Long finalFee;
	//假删除标识
	private Integer deleteFlag;
	//取车方式：0：上门送取/1：门店自取
	private String way;
	//是否开具发票 0：开发票/1：不开发票
	private String invoice;
	//是否预授权：0：上门送取/1：门店自取
	private String isPreAuthorized;
	//支付状态 0：未支付/1：支付部分2:完全支付
	private String payState;
	//订单开始车辆里程
	private String initialMileage;
	//订单结束车辆里程
	private String finalMileage;
	//取车门店
	private Long carShopGet;
	//还车门店
	private Long carShopReturn;
	//取车地址
	private String addressGet;
	//订单结束车辆里程
	private String addressReturn;

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public OrderInfo(){
	}

	public OrderInfo(
		Long id
	){
		this.id = id;
	}

	public Long getCarShopGet() {
		return carShopGet;
	}

	public void setCarShopGet(Long carShopGet) {
		this.carShopGet = carShopGet;
	}

	public Long getCarShopReturn() {
		return carShopReturn;
	}

	public void setCarShopReturn(Long carShopReturn) {
		this.carShopReturn = carShopReturn;
	}

	public String getAddressGet() {
		return addressGet;
	}

	public void setAddressGet(String addressGet) {
		this.addressGet = addressGet;
	}

	public String getAddressReturn() {
		return addressReturn;
	}

	public void setAddressReturn(String addressReturn) {
		this.addressReturn = addressReturn;
	}

	public String getInitialMileage() {
		return initialMileage;
	}

	public void setInitialMileage(String initialMileage) {
		this.initialMileage = initialMileage;
	}

	public String getFinalMileage() {
		return finalMileage;
	}

	public void setFinalMileage(String finalMileage) {
		this.finalMileage = finalMileage;
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
		
	public Long getCustomer() {
		return this.customer;
	}

	public void setCustomer(Long value) {
		this.customer = value;
	}
		
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String value) {
		this.orderNo = value;
	}
		
	public java.sql.Timestamp getRealTimeStart() {
		return this.realTimeStart;
	}

	public void setRealTimeStart(java.sql.Timestamp value) {
		this.realTimeStart = value;
	}
		
	public java.sql.Timestamp getRealTimeEnd() {
		return this.realTimeEnd;
	}

	public void setRealTimeEnd(java.sql.Timestamp value) {
		this.realTimeEnd = value;
	}
		
	public Double getTotalFee() {
		return this.totalFee;
	}

	public void setTotalFee(Double value) {
		this.totalFee = value;
	}
		
	public java.sql.Timestamp getReserveTimeStart() {
		return this.reserveTimeStart;
	}

	public void setReserveTimeStart(java.sql.Timestamp value) {
		this.reserveTimeStart = value;
	}
		
	public java.sql.Timestamp getPayTime() {
		return this.payTime;
	}

	public void setPayTime(java.sql.Timestamp value) {
		this.payTime = value;
	}
		
	public String getPayWay() {
		return this.payWay;
	}

	public void setPayWay(String value) {
		this.payWay = value;
	}
		
	public String getPayAccount() {
		return this.payAccount;
	}

	public void setPayAccount(String value) {
		this.payAccount = value;
	}
		
	public String getReserveType() {
		return this.reserveType;
	}

	public void setReserveType(String value) {
		this.reserveType = value;
	}
		
	public Long getCarId() {
		return this.carId;
	}

	public void setCarId(Long value) {
		this.carId = value;
	}
		
	public java.sql.Timestamp getReserveTimeEnd() {
		return this.reserveTimeEnd;
	}

	public void setReserveTimeEnd(java.sql.Timestamp value) {
		this.reserveTimeEnd = value;
	}
		
	public String getRealCarType() {
		return this.realCarType;
	}

	public void setRealCarType(String value) {
		this.realCarType = value;
	}
		
	public Double getRealPay() {
		return this.realPay;
	}

	public void setRealPay(Double value) {
		this.realPay = value;
	}
		
	public Long getFinalFee() {
		return this.finalFee;
	}

	public void setFinalFee(Long value) {
		this.finalFee = value;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getIsPreAuthorized() {
		return isPreAuthorized;
	}

	public void setIsPreAuthorized(String isPreAuthorized) {
		this.isPreAuthorized = isPreAuthorized;
	}

	public String getPayState() {
		return payState;
	}

	public void setPayState(String payState) {
		this.payState = payState;
	}

	public String toString() {
	return new ToStringBuilder(this,org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE)
		.append("Id",getId())
		.append("CreateBy",getCreateBy())
		.append("CreateTime",getCreateTime())
		.append("State",getState())
		.append("LastTime",getLastTime())
		.append("LastBy",getLastBy())
		.append("Customer",getCustomer())
		.append("OrderNo",getOrderNo())
		.append("RealTimeStart",getRealTimeStart())
		.append("RealTimeEnd",getRealTimeEnd())
		.append("TotalFee",getTotalFee())
		.append("ReserveTimeStart",getReserveTimeStart())
		.append("PayTime",getPayTime())
		.append("PayWay",getPayWay())
		.append("PayAccount",getPayAccount())
		.append("ReserveType",getReserveType())
		.append("CarId",getCarId())
		.append("ReserveTimeEnd",getReserveTimeEnd())
		.append("RealCarType",getRealCarType())
		.append("RealPay",getRealPay())
		.append("FinalFee",getFinalFee())
			.append("way",getWay())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof OrderInfo == false) return false;
	if(this == obj) return true;
	OrderInfo other = (OrderInfo)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
