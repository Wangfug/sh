
package com.lte.admin.car.controller;
import com.lte.admin.car.service.CarRentPriceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.car.entity.CarRentPrice;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class CarRentPriceController extends BaseController  {
	@Resource
	private CarRentPriceService carRentPriceService;

	public CarRentPrice getEntity4Request(HttpServletRequest request) {
			CarRentPrice entity=new CarRentPrice();
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
			if(StringUtils.isNotBlank(request.getParameter("city"))){
			entity.setCity(request.getParameter("city"));
			}
			if(StringUtils.isNotBlank(request.getParameter("area"))){
			entity.setArea(request.getParameter("area"));
			}
			if(StringUtils.isNotBlank(request.getParameter("brand"))){
			entity.setBrand(request.getParameter("brand"));
			}
			if(StringUtils.isNotBlank(request.getParameter("model"))){
			entity.setModel(request.getParameter("model"));
			}
			if(StringUtils.isNotBlank(request.getParameter("pricdeByDay"))){
			entity.setPricdeByDay(Double.valueOf(request.getParameter("pricdeByDay")));
			}
			if(StringUtils.isNotBlank(request.getParameter("priceByHour"))){
			entity.setPriceByHour(Double.valueOf(request.getParameter("priceByHour")));
			}
			if(StringUtils.isNotBlank(request.getParameter("carShop"))){
			entity.setCarShop(Long.valueOf(request.getParameter("carShop")));
			}
			return entity;
	}
}