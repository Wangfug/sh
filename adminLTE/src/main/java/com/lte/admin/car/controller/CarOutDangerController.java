
package com.lte.admin.car.controller;
import com.lte.admin.car.service.CarOutDangerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.car.entity.CarOutDanger;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class CarOutDangerController extends BaseController  {
	@Resource
	private CarOutDangerService carOutDangerService;

	public CarOutDanger getEntity4Request(HttpServletRequest request) {
			CarOutDanger entity=new CarOutDanger();
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
			if(StringUtils.isNotBlank(request.getParameter("insuranceTime"))){
			entity.setInsuranceTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("insuranceTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("insuranceType"))){
			entity.setInsuranceType(request.getParameter("insuranceType"));
			}
			if(StringUtils.isNotBlank(request.getParameter("orderNo"))){
			entity.setOrderNo(Long.valueOf(request.getParameter("orderNo")));
			}
			if(StringUtils.isNotBlank(request.getParameter("repairMoney"))){
			entity.setRepairMoney(Double.valueOf(request.getParameter("repairMoney")));
			}
			if(StringUtils.isNotBlank(request.getParameter("compensation"))){
			entity.setCompensation(request.getParameter("compensation"));
			}
			if(StringUtils.isNotBlank(request.getParameter("badComponent"))){
			entity.setBadComponent(request.getParameter("badComponent"));
			}
			if(StringUtils.isNotBlank(request.getParameter("beforeImage"))){
			entity.setBeforeImage(request.getParameter("beforeImage"));
			}
			if(StringUtils.isNotBlank(request.getParameter("afterImage"))){
			entity.setAfterImage(request.getParameter("afterImage"));
			}
			if(StringUtils.isNotBlank(request.getParameter("remark"))){
			entity.setRemark(request.getParameter("remark"));
			}
			return entity;
	}
}