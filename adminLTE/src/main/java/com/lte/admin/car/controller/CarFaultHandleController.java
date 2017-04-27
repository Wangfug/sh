
package com.lte.admin.car.controller;
import com.lte.admin.car.service.CarFaultHandleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.car.entity.CarFaultHandle;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class CarFaultHandleController extends BaseController  {
	@Resource
	private CarFaultHandleService carFaultHandleService;

	public CarFaultHandle getEntity4Request(HttpServletRequest request) {
			CarFaultHandle entity=new CarFaultHandle();
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
			if(StringUtils.isNotBlank(request.getParameter("faultComponengt"))){
			entity.setFaultComponengt(request.getParameter("faultComponengt"));
			}
			if(StringUtils.isNotBlank(request.getParameter("handleBy"))){
			entity.setHandleBy(request.getParameter("handleBy"));
			}
			if(StringUtils.isNotBlank(request.getParameter("faultOrder"))){
			entity.setFaultOrder(Long.valueOf(request.getParameter("faultOrder")));
			}
			if(StringUtils.isNotBlank(request.getParameter("outDangerNo"))){
			entity.setOutDangerNo(Long.valueOf(request.getParameter("outDangerNo")));
			}
			if(StringUtils.isNotBlank(request.getParameter("getMoney"))){
			entity.setGetMoney(Double.valueOf(request.getParameter("getMoney")));
			}
			if(StringUtils.isNotBlank(request.getParameter("compensator"))){
			entity.setCompensator(request.getParameter("compensator"));
			}
			if(StringUtils.isNotBlank(request.getParameter("repairMoney"))){
			entity.setRepairMoney(Double.valueOf(request.getParameter("repairMoney")));
			}
			if(StringUtils.isNotBlank(request.getParameter("provideShop"))){
			entity.setProvideShop(Long.valueOf(request.getParameter("provideShop")));
			}
			if(StringUtils.isNotBlank(request.getParameter("faultTime"))){
			entity.setFaultTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("faultTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("repairTime"))){
			entity.setRepairTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("repairTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("faultDescr"))){
			entity.setFaultDescr(request.getParameter("faultDescr"));
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