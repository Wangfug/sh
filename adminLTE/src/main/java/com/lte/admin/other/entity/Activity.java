package com.lte.admin.other.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * @author Andy
 */
public class Activity implements Serializable{
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
    //activityPage 	
	private String activityPage;
    //违章罚款 	
	private String activityPosition;
    //违章扣费 	
	private String activityNo;
    //activityName 	
	private String activityName;
    //activityStart 	
	private java.sql.Timestamp activityStart;
    //activityEnd 	
	private java.sql.Timestamp activityEnd;
    //activityType 	
	private String activityType;
    //activitySort 	
	private String activitySort;
    //attachment 	
	private String attachment;
    //activityRemark 	
	private String activityRemark;
	// 	是否推送到主页0-不推送  1-推送
	private String mainPage;
	//主标题
	private String mainTitle;
	//内容
	private String content;
	//副标题
	private String subTitle;
	//价格 元/天
	private Double price;
	//优惠券
	private Long  coupon;


	public Activity(){
	}

	public Activity(
		Long id
	){
		this.id = id;
	}

	public Long getCoupon() {
		return coupon;
	}

	public void setCoupon(Long coupon) {
		this.coupon = coupon;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public String getMainTitle() {
		return mainTitle;
	}

	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
		
	public String getActivityPage() {
		return this.activityPage;
	}

	public void setActivityPage(String value) {
		this.activityPage = value;
	}
		
	public String getActivityPosition() {
		return this.activityPosition;
	}

	public void setActivityPosition(String value) {
		this.activityPosition = value;
	}
		
	public String getActivityNo() {
		return this.activityNo;
	}

	public void setActivityNo(String value) {
		this.activityNo = value;
	}
		
	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String value) {
		this.activityName = value;
	}
		
	public java.sql.Timestamp getActivityStart() {
		return this.activityStart;
	}

	public void setActivityStart(java.sql.Timestamp value) {
		this.activityStart = value;
	}
		
	public java.sql.Timestamp getActivityEnd() {
		return this.activityEnd;
	}

	public void setActivityEnd(java.sql.Timestamp value) {
		this.activityEnd = value;
	}
		
	public String getActivityType() {
		return this.activityType;
	}

	public void setActivityType(String value) {
		this.activityType = value;
	}
		
	public String getActivitySort() {
		return this.activitySort;
	}

	public void setActivitySort(String value) {
		this.activitySort = value;
	}
		
	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(String value) {
		this.attachment = value;
	}
		
	public String getActivityRemark() {
		return this.activityRemark;
	}

	public void setActivityRemark(String value) {
		this.activityRemark = value;
	}

public String toString() {
	return new ToStringBuilder(this,org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE)
		.append("id",getId())
		.append("CreateBy",getCreateBy())
		.append("CreateTime",getCreateTime())
		.append("State",getState())
		.append("LastTime",getLastTime())
		.append("LastBy",getLastBy())
		.append("ActivityPage",getActivityPage())
		.append("ActivityPosition",getActivityPosition())
		.append("ActivityNo",getActivityNo())
		.append("ActivityName",getActivityName())
		.append("ActivityStart",getActivityStart())
		.append("ActivityEnd",getActivityEnd())
		.append("ActivityType",getActivityType())
		.append("ActivitySort",getActivitySort())
		.append("Attachment",getAttachment())
		.append("ActivityRemark",getActivityRemark())
		.toString();
}

public int hashCode() {
	return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
}

public boolean equals(Object obj) {
	if(obj instanceof Activity == false) return false;
	if(this == obj) return true;
	Activity other = (Activity)obj;
	return new EqualsBuilder()
		.append(getId(),other.getId())
		.isEquals();
}
}
