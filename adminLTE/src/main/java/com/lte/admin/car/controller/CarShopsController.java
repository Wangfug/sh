
package com.lte.admin.car.controller;
import com.lte.admin.car.service.CarShopsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.car.entity.CarShops;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class CarShopsController extends BaseController  {
	@Resource
	private CarShopsService carShopsService;

	public CarShops getEntity4Request(HttpServletRequest request) {
			CarShops entity=new CarShops();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
			}
			if(StringUtils.isNotBlank(request.getParameter("shopName"))){
			entity.setShopName(request.getParameter("shopName"));
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
			if(StringUtils.isNotBlank(request.getParameter("address"))){
			entity.setAddress(request.getParameter("address"));
			}
			if(StringUtils.isNotBlank(request.getParameter("attachment"))){
			entity.setAttachment(request.getParameter("attachment"));
			}
			if(StringUtils.isNotBlank(request.getParameter("evaluate"))){
			entity.setEvaluate(request.getParameter("evaluate"));
			}
			if(StringUtils.isNotBlank(request.getParameter("businessStart"))){
			entity.setBusinessStart(request.getParameter("businessStart"));
			}
			if(StringUtils.isNotBlank(request.getParameter("phone"))){
			entity.setPhone(request.getParameter("phone"));
			}
			if(StringUtils.isNotBlank(request.getParameter("stiffPhone"))){
			entity.setStiffPhone(request.getParameter("stiffPhone"));
			}
			if(StringUtils.isNotBlank(request.getParameter("shopType"))){
			entity.setShopType(request.getParameter("shopType"));
			}
			if(StringUtils.isNotBlank(request.getParameter("country"))){
			entity.setCountry(request.getParameter("country"));
			}
			if(StringUtils.isNotBlank(request.getParameter("province"))){
			entity.setProvince(request.getParameter("province"));
			}
			if(StringUtils.isNotBlank(request.getParameter("city"))){
			entity.setCity(request.getParameter("city"));
			}
			if(StringUtils.isNotBlank(request.getParameter("area"))){
			entity.setArea(request.getParameter("area"));
			}
			if(StringUtils.isNotBlank(request.getParameter("businessEnd"))){
			entity.setBusinessEnd(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("businessEnd")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("shopManager"))){
			entity.setShopManager(Long.valueOf(request.getParameter("shopManager")));
			}
			if(StringUtils.isNotBlank(request.getParameter("postcode"))){
			entity.setPostcode(request.getParameter("postcode"));
			}
			if(StringUtils.isNotBlank(request.getParameter("remark"))){
			entity.setRemark(request.getParameter("remark"));
			}
			return entity;
	}
}