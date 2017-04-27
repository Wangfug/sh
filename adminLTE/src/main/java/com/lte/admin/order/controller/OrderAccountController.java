
package com.lte.admin.order.controller;
import com.lte.admin.order.service.OrderAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.order.entity.OrderAccount;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class OrderAccountController extends BaseController  {
	@Resource
	private OrderAccountService orderAccountService;

	public OrderAccount getEntity4Request(HttpServletRequest request) {
			OrderAccount entity=new OrderAccount();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createBy"))){
			entity.setCreateBy(Long.valueOf(request.getParameter("createBy")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createTime"))){
			entity.setCreateTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("createTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("totalMoney"))){
			entity.setTotalMoney(Double.valueOf(request.getParameter("totalMoney")));
			}
			if(StringUtils.isNotBlank(request.getParameter("realPay"))){
			entity.setRealPay(Double.valueOf(request.getParameter("realPay")));
			}
			if(StringUtils.isNotBlank(request.getParameter("bylongOrder"))){
			entity.setBylongOrder(Long.valueOf(request.getParameter("bylongOrder")));
			}
			if(StringUtils.isNotBlank(request.getParameter("customer"))){
			entity.setCustomer(Long.valueOf(request.getParameter("customer")));
			}
			if(StringUtils.isNotBlank(request.getParameter("payWay"))){
			entity.setPayWay(request.getParameter("payWay"));
			}
			if(StringUtils.isNotBlank(request.getParameter("payAmount"))){
			entity.setPayAmount(request.getParameter("payAmount"));
			}
			if(StringUtils.isNotBlank(request.getParameter("acceptWay"))){
			entity.setAcceptWay(request.getParameter("acceptWay"));
			}
			if(StringUtils.isNotBlank(request.getParameter("acceptAmount"))){
			entity.setAcceptAmount(request.getParameter("acceptAmount"));
			}
			if(StringUtils.isNotBlank(request.getParameter("acceptMan"))){
			entity.setAcceptMan(request.getParameter("acceptMan"));
			}
			return entity;
	}
}