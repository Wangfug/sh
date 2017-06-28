
package com.lte.admin.other.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.other.entity.PickupPrice;
import com.lte.admin.other.service.PickupPriceService;
import com.lte.admin.system.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Map;

/**
 * @author Andy
 */
@Controller
@RequestMapping("web/pickupPrice")
public class PickupPriceController extends BaseController  {
	@Resource
	private PickupPriceService pickupPriceService;
	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toOtherView(){
		return "other/pickupPriceList";
	}

	/**
	 * 增加活动
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveOrUpdate",method = RequestMethod.POST)
	@ResponseBody
	public String saveOrUpdate(HttpServletRequest request){
		MemberLogin user = UserUtil.getCurrentUser();
		PickupPrice pickupPrice = getEntity4Request(request);
		if(pickupPrice.getId()!=null){
			pickupPrice.setLastTime(new Timestamp(System.currentTimeMillis()));
			pickupPrice.setLastBy(user.getId());
			pickupPriceService.update(pickupPrice);
		}else{
			pickupPrice.setCreateTime(new Timestamp(System.currentTimeMillis()));
			pickupPrice.setCreateBy(user.getId());
			pickupPrice.setLastTime(new Timestamp(System.currentTimeMillis()));
			pickupPrice.setLastBy(user.getId());
			pickupPriceService.save(pickupPrice);
		}
		return "success";
	}

	/**
	 * 获取订单列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getPickupPriceList",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getPickupPriceList(HttpServletRequest request){
		Page<PickupPrice> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<PickupPrice> page1 = pickupPriceService.getList(page, filters);
		return getEasyUIData(page1, request);
	}

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