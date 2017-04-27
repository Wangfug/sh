package com.lte.admin.car.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class Car implements Serializable{
	private static final long serialVersionUID = 72L;
	
    //编号 	
	private Long id;
    //名称 	
	private String carName;
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
    //车型 	
	private String carType;
    //cartonNumber 	
	private String cartonNumber;
    //displacement 	
	private String displacement;
    //blockNumber 	
	private String blockNumber;
    //车主 	
	private Long owner;
    //addtionalService 	
	private String addtionalService;
    //carShop 	
	private Long carShop;
    //carCode 	
	private String carCode;
    //engineNo 	
	private String engineNo;
    //frameNo 	
	private String frameNo;
    //color 	
	private String color;
    //buyYime 	
	private java.sql.Timestamp buyYime;
    //brand 	
	private String brand;
    //model 	
	private String model;
    //leaveFactoryTime 	
	private java.sql.Timestamp leaveFactoryTime;
    //bindObj 	
	private String bindObj;
    //remark1 	
	private String remark1;
    //remark2 	
	private String remark2;
    //remark3 	
	private String remark3;
    //attachment 	
	private String attachment;
    //vehicleLicense 	
	private Long vehicleLicense;
	public Car(){
	}

	public Car(
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
		
	public String getCarName() {
		return this.carName;
	}

	public void setCarName(String value) {
		this.carName = value;
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
		
	public String getCarType() {
		return this.carType;
	}

	public void setCarType(String value) {
		this.carType = value;
	}
		
	public String getCartonNumber() {
		return this.cartonNumber;
	}

	public void setCartonNumber(String value) {
		this.cartonNumber = value;
	}
		
	public String getDisplacement() {
		return this.displacement;
	}

	public void setDisplacement(String value) {
		this.displacement = value;
	}
		
	public String getBlockNumber() {
		return this.blockNumber;
	}

	public void setBlockNumber(String value) {
		this.blockNumber = value;
	}
		
	public Long getOwner() {
		return this.owner;
	}

	public void setOwner(Long value) {
		this.owner = value;
	}
		
	public String getAddtionalService() {
		return this.addtionalService;
	}

	public void setAddtionalService(String value) {
		this.addtionalService = value;
	}
		
	public Long getCarShop() {
		return this.carShop;
	}

	public void setCarShop(Long value) {
		this.carShop = value;
	}
		
	public String getCarCode() {
		return this.carCode;
	}

	public void setCarCode(String value) {
		this.carCode = value;
	}
		
	public String getEngineNo() {
		return this.engineNo;
	}

	public void setEngineNo(String value) {
		this.engineNo = value;
	}
		
	public String getFrameNo() {
		return this.frameNo;
	}

	public void setFrameNo(String value) {
		this.frameNo = value;
	}
		
	public String getColor() {
		return this.color;
	}

	public void setColor(String value) {
		this.color = value;
	}
		
	public java.sql.Timestamp getBuyYime() {
		return this.buyYime;
	}

	public void setBuyYime(java.sql.Timestamp value) {
		this.buyYime = value;
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
		
	public java.sql.Timestamp getLeaveFactoryTime() {
		return this.leaveFactoryTime;
	}

	public void setLeaveFactoryTime(java.sql.Timestamp value) {
		this.leaveFactoryTime = value;
	}
		
	public String getBindObj() {
		return this.bindObj;
	}

	public void setBindObj(String value) {
		this.bindObj = value;
	}
		
	public String getRemark1() {
		return this.remark1;
	}

	public void setRemark1(String value) {
		this.remark1 = value;
	}
		
	public String getRemark2() {
		return this.remark2;
	}

	public void setRemark2(String value) {
		this.remark2 = value;
	}
		
	public String getRemark3() {
		return this.remark3;
	}

	public void setRemark3(String value) {
		this.remark3 = value;
	}
		
	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(String value) {
		this.attachment = value;
	}
		
	public Long getVehicleLicense() {
		return this.vehicleLicense;
	}

	public void setVehicleLicense(Long value) {
		this.vehicleLicense = value;
	}

public String toString() {
	return new ToStringBuilder(this,org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE)
		.append("Id",getId())
		.append("CarName",getCarName())
		.append("CreateBy",getCreateBy())
		.append("CreateTime",getCreateTime())
		.append("State",getState())
		.append("LastTime",getLastTime())
		.append("LastBy",getLastBy())
		.append("CarType",getCarType())
		.append("CartonNumber",getCartonNumber())
		.append("Displacement",getDisplacement())
		.append("BlockNumber",getBlockNumber())
		.append("Owner",getOwner())
		.append("AddtionalService",getAddtionalService())
		.append("CarShop",getCarShop())
		.append("CarCode",getCarCode())
		.append("EngineNo",getEngineNo())
		.append("FrameNo",getFrameNo())
		.append("Color",getColor())
		.append("BuyYime",getBuyYime())
		.append("Brand",getBrand())
		.append("Model",getModel())
		.append("LeaveFactoryTime",getLeaveFactoryTime())
		.append("BindObj",getBindObj())
		.append("Remark1",getRemark1())
		.append("Remark2",getRemark2())
		.append("Remark3",getRemark3())
		.append("Attachment",getAttachment())
		.append("VehicleLicense",getVehicleLicense())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof Car == false) return false;
	if(this == obj) return true;
	Car other = (Car)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
