
package com.lte.admin.order.controller;
import com.lte.admin.order.service.OrderEvaluateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.order.entity.OrderEvaluate;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class OrderEvaluateController extends BaseController  {
	@Resource
	private OrderEvaluateService orderEvaluateService;

	public OrderEvaluate getEntity4Request(HttpServletRequest request) {
			OrderEvaluate entity=new OrderEvaluate();
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
			if(StringUtils.isNotBlank(request.getParameter("grade"))){
			entity.setGrade(request.getParameter("grade"));
			}
			if(StringUtils.isNotBlank(request.getParameter("content"))){
			entity.setContent(request.getParameter("content"));
			}
			if(StringUtils.isNotBlank(request.getParameter("vehicleCondition"))){
			entity.setVehicleCondition(request.getParameter("vehicleCondition"));
			}
			if(StringUtils.isNotBlank(request.getParameter("getVehicleService"))){
			entity.setGetVehicleService(request.getParameter("getVehicleService"));
			}
			if(StringUtils.isNotBlank(request.getParameter("returnVehicleService"))){
			entity.setReturnVehicleService(request.getParameter("returnVehicleService"));
			}
			if(StringUtils.isNotBlank(request.getParameter("openOrder"))){
			entity.setOpenOrder(request.getParameter("openOrder"));
			}
			if(StringUtils.isNotBlank(request.getParameter("closeOrder"))){
			entity.setCloseOrder(request.getParameter("closeOrder"));
			}
			if(StringUtils.isNotBlank(request.getParameter("totalService"))){
			entity.setTotalService(request.getParameter("totalService"));
			}
			if(StringUtils.isNotBlank(request.getParameter("orderId"))){
			entity.setOrderId(Long.valueOf(request.getParameter("orderId")));
			}
			if(StringUtils.isNotBlank(request.getParameter("attachment"))){
			entity.setAttachment(request.getParameter("attachment"));
			}
			if(StringUtils.isNotBlank(request.getParameter("openOpinion"))){
			entity.setOpenOpinion(request.getParameter("openOpinion"));
			}
			if(StringUtils.isNotBlank(request.getParameter("closeOpinion"))){
			entity.setCloseOpinion(request.getParameter("closeOpinion"));
			}
			if(StringUtils.isNotBlank(request.getParameter("totalOpinion"))){
			entity.setTotalOpinion(request.getParameter("totalOpinion"));
			}
			return entity;
	}
}