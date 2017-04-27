
package com.lte.admin.car.controller;
import com.lte.admin.car.service.CarMaintenanceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.car.entity.CarMaintenance;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class CarMaintenanceController extends BaseController  {
	@Resource
	private CarMaintenanceService carMaintenanceService;

	public CarMaintenance getEntity4Request(HttpServletRequest request) {
			CarMaintenance entity=new CarMaintenance();
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
			if(StringUtils.isNotBlank(request.getParameter("maintenanceBy"))){
			entity.setMaintenanceBy(request.getParameter("maintenanceBy"));
			}
			if(StringUtils.isNotBlank(request.getParameter("maintenanceTime"))){
			entity.setMaintenanceTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("maintenanceTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("maintenanceOrder"))){
			entity.setMaintenanceOrder(request.getParameter("maintenanceOrder"));
			}
			if(StringUtils.isNotBlank(request.getParameter("maintenanceContent"))){
			entity.setMaintenanceContent(request.getParameter("maintenanceContent"));
			}
			if(StringUtils.isNotBlank(request.getParameter("maintenanceMoney"))){
			entity.setMaintenanceMoney(Double.valueOf(request.getParameter("maintenanceMoney")));
			}
			if(StringUtils.isNotBlank(request.getParameter("remark"))){
			entity.setRemark(request.getParameter("remark"));
			}
			if(StringUtils.isNotBlank(request.getParameter("attachment"))){
			entity.setAttachment(request.getParameter("attachment"));
			}
			return entity;
	}
}