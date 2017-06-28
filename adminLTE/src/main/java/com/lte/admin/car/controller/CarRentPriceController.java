
package com.lte.admin.car.controller;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.entity.CarRentPrice;
import com.lte.admin.car.entity.CarShops;
import com.lte.admin.car.service.CarRentPriceService;
import com.lte.admin.car.service.CarService;
import com.lte.admin.car.service.CarShopsService;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.system.utils.UserUtil;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Andy
 */
@Controller
@RequestMapping("web/carRentPrice")
public class CarRentPriceController extends BaseController  {
	@Resource
	private CarRentPriceService carRentPriceService;
	@Resource
	private CarShopsService carShopsService;
	@Resource
	private CarService carService;
	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toCarView(HttpServletRequest request){
		List<CarShops> carShops = carShopsService.getList();
		request.getSession().setAttribute("carShops",JSONArray.fromObject(carShops));
		return "car/carRentPrice";
	}
	/**
	 * 删除车辆价格表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delete",method = RequestMethod.GET)
	@ResponseBody
	public String delete(HttpServletRequest request){
		Long id = 0l;
		if(StringUtils.isNotBlank(request.getParameter("id"))){
			id = Long.parseLong(request.getParameter("id"));
			carRentPriceService.deleteById(id);
			return "success";
		}
		return "false";
	}
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
			if(StringUtils.isNotBlank(request.getParameter("priceByDay"))){
			entity.setPriceByDay(Double.valueOf(request.getParameter("priceByDay")));
			}
			if(StringUtils.isNotBlank(request.getParameter("priceByHour"))){
			entity.setPriceByHour(Double.valueOf(request.getParameter("priceByHour")));
			}
			if(StringUtils.isNotBlank(request.getParameter("carShop"))){
			entity.setCarShop(Long.valueOf(request.getParameter("carShop")));
			}
		if(StringUtils.isNotBlank(request.getParameter("feeInsurance"))){
			entity.setFeeInsurance(Double.valueOf(request.getParameter("feeInsurance")));
		}
		if(StringUtils.isNotBlank(request.getParameter("feeDeductible"))){
			entity.setFeeDeductible(Double.valueOf(request.getParameter("feeDeductible")));
		}
		if(StringUtils.isNotBlank(request.getParameter("hotcar"))){
			entity.setHotcar(Integer.valueOf(request.getParameter("hotcar")));
		}
		if(StringUtils.isNotBlank(request.getParameter("img"))){
			entity.setImg(request.getParameter("img"));
		}
			return entity;
	}

	@RequestMapping(value = "saveOrUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveCarRentPrice(HttpServletRequest request) {
		MemberLogin user = UserUtil.getCurrentUser();
		CarRentPrice carRentPrice = this.getEntity4Request(request);
		String result = "false";
		if(carRentPrice.getId()!=null){
			carRentPrice.setLastBy(user.getId());
			carRentPrice.setLastTime(new Timestamp(System.currentTimeMillis()));
			result = carRentPriceService.updateCarRentPrice(carRentPrice);
		}else{
			carRentPrice.setState(1);
			carRentPrice.setCreateBy(user.getId());
			carRentPrice.setCreateTime(new Timestamp(System.currentTimeMillis()));
			carRentPrice.setLastBy(user.getId());
			carRentPrice.setLastTime(new Timestamp(System.currentTimeMillis()));
			result = carRentPriceService.saveCarRentPrice(carRentPrice);
		}
		return result;
	}

	@RequestMapping(value = "getList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCarAttachList(HttpServletRequest request) {
		Page<CarRentPrice> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<CarRentPrice> page1 = carRentPriceService.getList(page,filters);
		return getEasyUIData(page1, request);
	}

	/**
	 * 创建价格
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "create",method = {RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletRequest request,String id){
		CarRentPrice carRentPrice = null;
		if(StringUtils.isNotBlank(id)) {
			carRentPrice = carRentPriceService.getOneCarRentPrice(Long.parseLong(id));
		}
		List<Map> model = carService.getCarBrandModel();
		List brand = carService.getCarBrands();

		request.setAttribute("brands",brand);
		request.setAttribute("models", JSON.toJSONString(model));
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("carRentPrice",carRentPrice);
		return "car/carRentPriceForm";
	}
}