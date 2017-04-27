
package com.lte.admin.order.controller;
import com.lte.admin.order.service.OrderStateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.order.entity.OrderState;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class OrderStateController extends BaseController  {
	@Resource
	private OrderStateService orderStateService;

	public OrderState getEntity4Request(HttpServletRequest request) {
			OrderState entity=new OrderState();
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
			if(StringUtils.isNotBlank(request.getParameter("orderId"))){
			entity.setOrderId(Long.valueOf(request.getParameter("orderId")));
			}
			if(StringUtils.isNotBlank(request.getParameter("orderStateInfo"))){
			entity.setOrderStateInfo(request.getParameter("orderStateInfo"));
			}
			return entity;
	}
}