
package com.lte.admin.car.controller;
import com.lte.admin.car.service.CarInsuranceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.car.entity.CarInsurance;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class CarInsuranceController extends BaseController  {
	@Resource
	private CarInsuranceService carInsuranceService;

	public CarInsurance getEntity4Request(HttpServletRequest request) {
			CarInsurance entity=new CarInsurance();
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
			if(StringUtils.isNotBlank(request.getParameter("insuranceStarttime"))){
			entity.setInsuranceStarttime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("insuranceStarttime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("insuranceEndtime"))){
			entity.setInsuranceEndtime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("insuranceEndtime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("insuranceComp"))){
			entity.setInsuranceComp(request.getParameter("insuranceComp"));
			}
			if(StringUtils.isNotBlank(request.getParameter("insuranceType"))){
			entity.setInsuranceType(request.getParameter("insuranceType"));
			}
			if(StringUtils.isNotBlank(request.getParameter("insuranceBy"))){
			entity.setInsuranceBy(request.getParameter("insuranceBy"));
			}
			if(StringUtils.isNotBlank(request.getParameter("insuranceSalesman"))){
			entity.setInsuranceSalesman(request.getParameter("insuranceSalesman"));
			}
			if(StringUtils.isNotBlank(request.getParameter("insuranceTypeMoney"))){
			entity.setInsuranceTypeMoney(Double.valueOf(request.getParameter("insuranceTypeMoney")));
			}
			if(StringUtils.isNotBlank(request.getParameter("totalMoney"))){
			entity.setTotalMoney(Double.valueOf(request.getParameter("totalMoney")));
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