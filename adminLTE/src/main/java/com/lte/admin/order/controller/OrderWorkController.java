
package com.lte.admin.order.controller;
import com.lte.admin.order.service.OrderWorkService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.order.entity.OrderWork;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class OrderWorkController extends BaseController  {
	@Resource
	private OrderWorkService orderWorkService;

	public OrderWork getEntity4Request(HttpServletRequest request) {
			OrderWork entity=new OrderWork();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createBy"))){
			entity.setCreateBy(Long.valueOf(request.getParameter("createBy")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createTime"))){
			entity.setCreateTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("createTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("orderType"))){
			entity.setOrderType(Integer.valueOf(request.getParameter("orderType")));
			}
			if(StringUtils.isNotBlank(request.getParameter("lastTime"))){
			entity.setLastTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("lastTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("lastBy"))){
			entity.setLastBy(Long.valueOf(request.getParameter("lastBy")));
			}
			if(StringUtils.isNotBlank(request.getParameter("way"))){
			entity.setWay(request.getParameter("way"));
			}
			if(StringUtils.isNotBlank(request.getParameter("address"))){
			entity.setAddress(request.getParameter("address"));
			}
			if(StringUtils.isNotBlank(request.getParameter("person"))){
			entity.setPerson(request.getParameter("person"));
			}
			if(StringUtils.isNotBlank(request.getParameter("orderState"))){
			entity.setOrderState(request.getParameter("orderState"));
			}
			if(StringUtils.isNotBlank(request.getParameter("carShop"))){
			entity.setCarShop(Long.valueOf(request.getParameter("carShop")));
			}
			if(StringUtils.isNotBlank(request.getParameter("eno"))){
			entity.setEno(Long.valueOf(request.getParameter("eno")));
			}
			if(StringUtils.isNotBlank(request.getParameter("orderId"))){
			entity.setOrderId(Long.valueOf(request.getParameter("orderId")));
			}
			if(StringUtils.isNotBlank(request.getParameter("attachment"))){
			entity.setAttachment(request.getParameter("attachment"));
			}
			if(StringUtils.isNotBlank(request.getParameter("carId"))){
			entity.setCarId(Long.valueOf(request.getParameter("carId")));
			}
			if(StringUtils.isNotBlank(request.getParameter("carCheckDetail"))){
			entity.setCarCheckDetail(request.getParameter("carCheckDetail"));
			}
			return entity;
	}
}