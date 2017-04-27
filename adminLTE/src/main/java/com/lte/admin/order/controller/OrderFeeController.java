
package com.lte.admin.order.controller;
import com.lte.admin.order.service.OrderFeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.order.entity.OrderFee;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class OrderFeeController extends BaseController  {
	@Resource
	private OrderFeeService orderFeeService;

	public OrderFee getEntity4Request(HttpServletRequest request) {
			OrderFee entity=new OrderFee();
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
			if(StringUtils.isNotBlank(request.getParameter("carRentFee"))){
			entity.setCarRentFee(Double.valueOf(request.getParameter("carRentFee")));
			}
			if(StringUtils.isNotBlank(request.getParameter("sendCarFee"))){
			entity.setSendCarFee(Double.valueOf(request.getParameter("sendCarFee")));
			}
			if(StringUtils.isNotBlank(request.getParameter("baseFee"))){
			entity.setBaseFee(Double.valueOf(request.getParameter("baseFee")));
			}
			if(StringUtils.isNotBlank(request.getParameter("otherFee"))){
			entity.setOtherFee(Double.valueOf(request.getParameter("otherFee")));
			}
			if(StringUtils.isNotBlank(request.getParameter("additionalBujimianpei"))){
			entity.setAdditionalBujimianpei(Double.valueOf(request.getParameter("additionalBujimianpei")));
			}
			if(StringUtils.isNotBlank(request.getParameter("handingCharge"))){
			entity.setHandingCharge(Double.valueOf(request.getParameter("handingCharge")));
			}
			if(StringUtils.isNotBlank(request.getParameter("additionalFeeForThree"))){
			entity.setAdditionalFeeForThree(Double.valueOf(request.getParameter("additionalFeeForThree")));
			}
			if(StringUtils.isNotBlank(request.getParameter("orderId"))){
			entity.setOrderId(Long.valueOf(request.getParameter("orderId")));
			}
			if(StringUtils.isNotBlank(request.getParameter("totalFee"))){
			entity.setTotalFee(Double.valueOf(request.getParameter("totalFee")));
			}
			if(StringUtils.isNotBlank(request.getParameter("preAuthorized"))){
			entity.setPreAuthorized(Double.valueOf(request.getParameter("preAuthorized")));
			}
			return entity;
	}
}