package com.lte.admin.mobile.customer;

import com.lte.admin.car.entity.CarShops;
import com.lte.admin.car.service.CarShopsService;
import com.lte.admin.common.response.ServiceResponse;
import com.lte.admin.common.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Andy
 */
@Controller
@RequestMapping("mobile/customer")
public class CarShopController extends BaseController  {
	@Resource
	private CarShopsService carShopsService;

	@RequestMapping(value = "getCarCities", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String getCarCities(){
		ServiceResponse serviceResponse = new ServiceResponse();
		try{
			List<String> cities = carShopsService.getShopCities();
			serviceResponse.setData(cities);
			serviceResponse.setStatus(1);
			serviceResponse.setInfo("获取成功");
		}catch (Exception e){
			e.printStackTrace();
			serviceResponse.setData("");
			serviceResponse.setStatus(0);
			serviceResponse.setInfo("获取失败");
		}
		String json =  serviceResponse.objectToJson();
		return json;
	}

	@RequestMapping(value = "getCarShops", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String getCarShops(String city,String lon ,String lat){
		ServiceResponse serviceResponse = new ServiceResponse();
		try{
			Map<String,Object> filters = new HashMap();
			filters.put("city",city);
			if((null != lon && lon != "") && (null != lat && lat != "")){
				filters.put("lon",lon);
				filters.put("lat",lat);
			}
			List<Map> carShops = carShopsService.getCarShops(filters);
			serviceResponse.setData(carShops);
			serviceResponse.setStatus(1);
			serviceResponse.setInfo("获取成功");
		}catch (Exception e){
			e.printStackTrace();
			serviceResponse.setData("");
			serviceResponse.setStatus(0);
			serviceResponse.setInfo("获取失败");
		}
		String json =  serviceResponse.objectToJson();
		return json;
	}

}