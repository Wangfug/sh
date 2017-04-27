
package com.lte.admin.order.controller;
import com.lte.admin.order.service.OrderInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.order.entity.OrderInfo;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class OrderInfoController extends BaseController  {
	@Resource
	private OrderInfoService orderInfoService;

	public OrderInfo getEntity4Request(HttpServletRequest request) {
			OrderInfo entity=new OrderInfo();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createBy"))){
			entity.setCreateBy(Long.valueOf(request.getParameter("createBy")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createTime"))){
			entity.setCreateTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("createTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("state"))){
			entity.setState(Integer.valueOf(request.getParameter("state")));
			}
			if(StringUtils.isNotBlank(request.getParameter("lastTime"))){
			entity.setLastTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("lastTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("lastBy"))){
			entity.setLastBy(Long.valueOf(request.getParameter("lastBy")));
			}
			if(StringUtils.isNotBlank(request.getParameter("customer"))){
			entity.setCustomer(Long.valueOf(request.getParameter("customer")));
			}
			if(StringUtils.isNotBlank(request.getParameter("orderNo"))){
			entity.setOrderNo(request.getParameter("orderNo"));
			}
			if(StringUtils.isNotBlank(request.getParameter("realTimeStart"))){
			entity.setRealTimeStart(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("realTimeStart")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("realTimeEnd"))){
			entity.setRealTimeEnd(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("realTimeEnd")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("totalFee"))){
			entity.setTotalFee(Double.valueOf(request.getParameter("totalFee")));
			}
			if(StringUtils.isNotBlank(request.getParameter("reserveTimeStart"))){
			entity.setReserveTimeStart(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("reserveTimeStart")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("payTime"))){
			entity.setPayTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("payTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("payWay"))){
			entity.setPayWay(request.getParameter("payWay"));
			}
			if(StringUtils.isNotBlank(request.getParameter("payAccount"))){
			entity.setPayAccount(request.getParameter("payAccount"));
			}
			if(StringUtils.isNotBlank(request.getParameter("reserveType"))){
			entity.setReserveType(request.getParameter("reserveType"));
			}
			if(StringUtils.isNotBlank(request.getParameter("carId"))){
			entity.setCarId(Long.valueOf(request.getParameter("carId")));
			}
			if(StringUtils.isNotBlank(request.getParameter("reserveTimeEnd"))){
			entity.setReserveTimeEnd(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("reserveTimeEnd")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("realCarType"))){
			entity.setRealCarType(request.getParameter("realCarType"));
			}
			if(StringUtils.isNotBlank(request.getParameter("realPay"))){
			entity.setRealPay(Double.valueOf(request.getParameter("realPay")));
			}
			if(StringUtils.isNotBlank(request.getParameter("finalFee"))){
			entity.setFinalFee(Long.valueOf(request.getParameter("finalFee")));
			}
			return entity;
	}
}