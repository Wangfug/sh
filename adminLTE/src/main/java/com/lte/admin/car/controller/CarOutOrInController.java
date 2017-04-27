
package com.lte.admin.car.controller;
import com.lte.admin.car.service.CarOutOrInService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.car.entity.CarOutOrIn;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class CarOutOrInController extends BaseController  {
	@Resource
	private CarOutOrInService carOutOrInService;

	public CarOutOrIn getEntity4Request(HttpServletRequest request) {
			CarOutOrIn entity=new CarOutOrIn();
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
			if(StringUtils.isNotBlank(request.getParameter("carId"))){
			entity.setCarId(Long.valueOf(request.getParameter("carId")));
			}
			if(StringUtils.isNotBlank(request.getParameter("reason"))){
			entity.setReason(request.getParameter("reason"));
			}
			if(StringUtils.isNotBlank(request.getParameter("approveBy"))){
			entity.setApproveBy(Long.valueOf(request.getParameter("approveBy")));
			}
			if(StringUtils.isNotBlank(request.getParameter("outEmp"))){
			entity.setOutEmp(Long.valueOf(request.getParameter("outEmp")));
			}
			if(StringUtils.isNotBlank(request.getParameter("outPosition"))){
			entity.setOutPosition(request.getParameter("outPosition"));
			}
			if(StringUtils.isNotBlank(request.getParameter("dispatchNo"))){
			entity.setDispatchNo(request.getParameter("dispatchNo"));
			}
			if(StringUtils.isNotBlank(request.getParameter("outTime"))){
			entity.setOutTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("outTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("remark"))){
			entity.setRemark(request.getParameter("remark"));
			}
			if(StringUtils.isNotBlank(request.getParameter("inOrOut"))){
			entity.setInOrOut(request.getParameter("inOrOut"));
			}
			if(StringUtils.isNotBlank(request.getParameter("carShop"))){
			entity.setCarShop(Long.valueOf(request.getParameter("carShop")));
			}
			return entity;
	}
}