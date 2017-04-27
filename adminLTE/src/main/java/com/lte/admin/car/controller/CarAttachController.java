
package com.lte.admin.car.controller;
import com.lte.admin.car.service.CarAttachService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.car.entity.CarAttach;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class CarAttachController extends BaseController  {
	@Resource
	private CarAttachService carAttachService;

	public CarAttach getEntity4Request(HttpServletRequest request) {
			CarAttach entity=new CarAttach();
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
			if(StringUtils.isNotBlank(request.getParameter("customerId"))){
			entity.setCustomerId(Long.valueOf(request.getParameter("customerId")));
			}
			if(StringUtils.isNotBlank(request.getParameter("otherRemark"))){
			entity.setOtherRemark(request.getParameter("otherRemark"));
			}
			if(StringUtils.isNotBlank(request.getParameter("attachStart"))){
			entity.setAttachStart(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("attachStart")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("attachEnd"))){
			entity.setAttachEnd(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("attachEnd")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("remark"))){
			entity.setRemark(request.getParameter("remark"));
			}
			if(StringUtils.isNotBlank(request.getParameter("attachement"))){
			entity.setAttachement(request.getParameter("attachement"));
			}
			return entity;
	}
}