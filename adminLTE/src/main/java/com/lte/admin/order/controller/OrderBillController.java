
package com.lte.admin.order.controller;
import com.lte.admin.order.service.OrderBillService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.order.entity.OrderBill;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class OrderBillController extends BaseController  {
	@Resource
	private OrderBillService orderBillService;

	public OrderBill getEntity4Request(HttpServletRequest request) {
			OrderBill entity=new OrderBill();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createBy"))){
			entity.setCreateBy(Integer.valueOf(request.getParameter("createBy")));
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
			if(StringUtils.isNotBlank(request.getParameter("billType"))){
			entity.setBillType(request.getParameter("billType"));
			}
			if(StringUtils.isNotBlank(request.getParameter("billTitle"))){
			entity.setBillTitle(request.getParameter("billTitle"));
			}
			if(StringUtils.isNotBlank(request.getParameter("address"))){
			entity.setAddress(request.getParameter("address"));
			}
			if(StringUtils.isNotBlank(request.getParameter("linkphone"))){
			entity.setLinkphone(request.getParameter("linkphone"));
			}
			if(StringUtils.isNotBlank(request.getParameter("area"))){
			entity.setArea(request.getParameter("area"));
			}
			if(StringUtils.isNotBlank(request.getParameter("taxpayerCode"))){
			entity.setTaxpayerCode(request.getParameter("taxpayerCode"));
			}
			if(StringUtils.isNotBlank(request.getParameter("depositBank"))){
			entity.setDepositBank(request.getParameter("depositBank"));
			}
			if(StringUtils.isNotBlank(request.getParameter("bankAccount"))){
			entity.setBankAccount(request.getParameter("bankAccount"));
			}
			if(StringUtils.isNotBlank(request.getParameter("addresseeName"))){
			entity.setAddresseeName(request.getParameter("addresseeName"));
			}
			if(StringUtils.isNotBlank(request.getParameter("orderId"))){
			entity.setOrderId(Long.valueOf(request.getParameter("orderId")));
			}
			if(StringUtils.isNotBlank(request.getParameter("customerId"))){
			entity.setCustomerId(Long.valueOf(request.getParameter("customerId")));
			}
			return entity;
	}
}