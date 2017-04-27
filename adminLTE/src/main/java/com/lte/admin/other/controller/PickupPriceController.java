
package com.lte.admin.other.controller;
import com.lte.admin.other.service.PickupPriceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.other.entity.PickupPrice;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class PickupPriceController extends BaseController  {
	@Resource
	private PickupPriceService pickupPriceService;

	public PickupPrice getEntity4Request(HttpServletRequest request) {
			PickupPrice entity=new PickupPrice();
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
			if(StringUtils.isNotBlank(request.getParameter("starttime"))){
			entity.setStarttime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("starttime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("endtime"))){
			entity.setEndtime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("endtime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("scopePrice"))){
			entity.setScopePrice(Double.valueOf(request.getParameter("scopePrice")));
			}
			if(StringUtils.isNotBlank(request.getParameter("remark"))){
			entity.setRemark(request.getParameter("remark"));
			}
			if(StringUtils.isNotBlank(request.getParameter("beyondPrice"))){
			entity.setBeyondPrice(Double.valueOf(request.getParameter("beyondPrice")));
			}
			if(StringUtils.isNotBlank(request.getParameter("regulationScope"))){
			entity.setRegulationScope(request.getParameter("regulationScope"));
			}
			if(StringUtils.isNotBlank(request.getParameter("regulationUnit"))){
			entity.setRegulationUnit(request.getParameter("regulationUnit"));
			}
			if(StringUtils.isNotBlank(request.getParameter("beyondUnit"))){
			entity.setBeyondUnit(request.getParameter("beyondUnit"));
			}
			return entity;
	}
}