package com.lte.admin.mobile.customer;

import com.alibaba.fastjson.JSONObject;
import com.lte.admin.car.service.CarRentPriceService;
import com.lte.admin.car.service.CarService;
import com.lte.admin.car.service.CarShopsService;
import com.lte.admin.common.consts.OrderWayEnum;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.response.ServiceResponse;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.custom.service.CustomerDiscountHoldService;
import com.lte.admin.custom.service.CustomerDiscountService;
import com.lte.admin.entity.DictType;
import com.lte.admin.order.entity.OrderInfo;
import com.lte.admin.order.service.OrderInfoService;
import com.lte.admin.system.service.DictTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Andy
 */
@Controller
@RequestMapping("mobile/customer")
public class CarMobileController extends BaseController {
	@Resource
	private CarService carService;
	@Resource
	private DictTypeService dictTypeService;
	@Resource
	private CarShopsService carShopsService;
	@Resource
	private OrderInfoService orderInfoService;
	@Resource
	private CarRentPriceService carRentPriceService;
	@Resource
	private CustomerDiscountHoldService holdService;

	@RequestMapping(value = "getCarByShop", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String getCarByShop(String carInfo) {
		ServiceResponse serviceResponse = new ServiceResponse();
		if(null != carInfo  &&  carInfo!= "") {
			carInfo = carInfo.replaceAll("&quot;", "\\\"");
			JSONObject jsStr = JSONObject.parseObject(carInfo);
//			String getWay = jsStr.getString("getWay");
			String startTime = jsStr.getString("startTime");
			String endTime = jsStr.getString("endTime");
			String city = jsStr.getString("city");
			String sort = jsStr.getString("sort");
			String order = jsStr.getString("order");
			String pageNum = jsStr.getString("pageNum");
			String pageSize = jsStr.getString("pageSize");
			String carType = jsStr.getString("carType");
			String brand = jsStr.getString("brand");
			String money = jsStr.getString("money");
			try {
				Map<String, Object> map = new HashMap();
				List<DictType> dictBeans = dictTypeService.getChildrenByParent("CarTimeSet");
				int setTime = 0;
				if (dictBeans != null && dictBeans.size() > 0) {
					setTime = Integer.valueOf(dictBeans.get(0).getName());
				}
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");//注意格式化的表达式
				Date startTime1 = format.parse(startTime);
				Date endTime1 = format.parse(endTime);
				Long time1 = startTime1.getTime() - setTime * 60 * 60 * 1000;
				Timestamp timeStartForReserve = new Timestamp(time1);
				Long time2 = endTime1.getTime() + setTime * 60 * 60 * 1000;
				Timestamp timeEndForReserve = new Timestamp(time2);

				String getWay = jsStr.getString("getWay");
				String lonTake = jsStr.getString("lonTake");
				String latTake = jsStr.getString("latTake");
				String shopId=null;
				if (OrderWayEnum.BYSHOP.getCode().equals(getWay) && StringUtils.isNotBlank(lonTake) && StringUtils.isNotBlank(latTake)) {//上门送取，选择比较近门店
					List<Map> shopTake = new ArrayList<>();
					Map<String, String> coordinate = new HashMap();
					coordinate.put("lon", lonTake);
					coordinate.put("lat", latTake);
					coordinate.put("city", city);
					shopTake = carShopsService.getNearestShop(coordinate);
					if(null != shopTake && shopTake.size()>0){//最近的门店
						shopId = shopTake.get(0).get("id")+"";
						map.put("carShopId", shopId);
					}
				}else{
					String carShopId = jsStr.getString("shopId");
					if(null != carShopId && carShopId !=""){
						map.put("carShopId", carShopId);
					}
				}
				map.put("startTime", timeStartForReserve);
				map.put("endTime", timeEndForReserve);
				map.put("city", city);
				if (null != money && money != "" && !money.equals("") && !money.equals("undefined")) {
					if (money.contains("-￥")) {
						map.put("lowPrice", money.substring(money.indexOf("￥") + 1, money.lastIndexOf("-￥")));
						map.put("heighPrice", money.substring(money.indexOf("-￥") + 2, money.lastIndexOf("")));
					}
					if (money.contains("以上")) {
						System.out.println();
						map.put("lowPrice", money.substring(money.indexOf("￥") + 1, money.lastIndexOf("以上")));
						map.put("heighPrice", "10000000");
					}
				}
				if (null != carType && carType != "" && !carType.equals("") && !carType.equals("undefined")) {
					List<String> list = new ArrayList<String>();
					String[] types = carType.split(",");
					for (int i = 0; i < types.length; i++) {
						list.add(types[i]);
					}
					map.put("carType", list);
				}
				if (null != brand && brand != "" && !brand.equals("") && !brand.equals("undefined")) {
					map.put("brand", brand);
				}
				Page page = new Page();
				page.setPageNo(1);
				page.setPageSize(10000);
				page.setOrderBy("day_price_shop");
				page.setOrder("asc");
				if (null != pageNum && pageNum != "") {
					page.setPageNo(Integer.valueOf(pageNum));
				}
				if (null != pageSize && pageSize != "") {
					page.setPageSize(Integer.valueOf(pageSize));
				}
				if (null != sort && sort != "") {
					page.setOrderBy(sort);
				}
				if (null != order && order != "") {
					page.setOrder(order);
				}
				List<Map> cars = carService.getCarByShop(page, map);
				serviceResponse.setData(cars);
				serviceResponse.setStatus(1);
				serviceResponse.setInfo("获取成功");
			} catch (Exception e) {
				e.printStackTrace();
				serviceResponse.setData("");
				serviceResponse.setStatus(0);
				serviceResponse.setInfo("获取失败");
			}
		}else{
			serviceResponse.setStatus(2);
			serviceResponse.setInfo("获取参数失败");
		}
		String json = serviceResponse.objectToJson();
		return json;
	}

	/**
	 * 条件筛选
	 * @return
	 */
	@RequestMapping(value = "getSelected", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String getCarTypes() {
		ServiceResponse serviceResponse = new ServiceResponse();
		try {
			List<DictType>  model = dictTypeService.getChildrenByParent("CarModel");
			List<DictType> money = dictTypeService.getChildrenByParent("CarSelectMoney");
			List<String> brands = carService.getCarBrands();
			Map<String,Object> map = new HashMap<>();
			map.put("carType",model);
			map.put("money",money);
			map.put("brand",brands);
			serviceResponse.setData(map);
			serviceResponse.setStatus(1);
			serviceResponse.setInfo("获取成功");
		} catch (Exception e) {
			e.printStackTrace();
			serviceResponse.setData("");
			serviceResponse.setStatus(0);
			serviceResponse.setInfo("获取失败");
		}
		String json = serviceResponse.objectToJson();
		return json;
	}
	/**
	 * 获取相关费用
	 * @return
	 */
	@RequestMapping(value = "getFee", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String getFee() {
		ServiceResponse serviceResponse = new ServiceResponse();
		try {
			List<DictType> dictBeans = dictTypeService.getChildrenByParent("CounterFee");//手续费
			double counterFee = 0;
			if (dictBeans != null && dictBeans.size() > 0) {
				counterFee = Double.parseDouble(dictBeans.get(0).getName());
			}

			List<DictType> authorizationFee = dictTypeService.getChildrenByParent("AuthorizationFee");//预授权费用
			double authorization = 0;
			if (authorizationFee != null && authorizationFee.size() > 0) {
				authorization = Double.parseDouble(authorizationFee.get(0).getName());
			}

			List<DictType> sendCarMoney = dictTypeService.getChildrenByParent("SendCarMoney");//送车费
			double sendCar = 0;
			if (sendCarMoney != null && sendCarMoney.size() > 0) {
				sendCar = Double.parseDouble(sendCarMoney.get(0).getName());
			}

			Map<String,Object> map = new HashMap<>();
			map.put("counterFee",counterFee);
			map.put("authorizationFee",authorization);
			map.put("sendCarMoney",sendCar);

			serviceResponse.setData(map);
			serviceResponse.setStatus(1);
			serviceResponse.setInfo("获取成功");
		} catch (Exception e) {
			e.printStackTrace();
			serviceResponse.setData("");
			serviceResponse.setStatus(0);
			serviceResponse.setInfo("获取失败");
		}
		String json = serviceResponse.objectToJson();
		return json;
	}

	public String getCarType(String allCarType){
		int endIndex = allCarType.indexOf(",");
		if(endIndex!=-1)
			allCarType = allCarType.substring(0,endIndex);
		System.out.println("-----------"+allCarType);
		String carType = dictTypeService.getDictByCode(allCarType).getName();
		return carType;
	}

	/**
	 * 根据订单号获取订单金额信息
	 * @return
	 */
	@RequestMapping(value = "getOrderMoney", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String getOrderMoney(String orderNo) {
		ServiceResponse serviceResponse = new ServiceResponse();
		if(StringUtils.isNotBlank(orderNo)){
			try {
				OrderInfo orderInfo = orderInfoService.getByOrderNo(orderNo);
				serviceResponse.setData(orderInfo.getRealPay());
				serviceResponse.setStatus(1);
				serviceResponse.setInfo("获取成功");
			} catch (Exception e) {
				e.printStackTrace();
				serviceResponse.setData("");
				serviceResponse.setStatus(0);
				serviceResponse.setInfo("订单查询异常");
			}
		}else{
			serviceResponse.setData("");
			serviceResponse.setStatus(2);
			serviceResponse.setInfo("订单号为空");
		}
		String json = serviceResponse.objectToJson();
		return json;
	}

	/**
	 * 条件筛选
	 * @return
	 */
	@RequestMapping(value = "getHotCar", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String getHotCar(String city) {
		ServiceResponse serviceResponse = new ServiceResponse();
		try {
			List<Map> car =  carRentPriceService.getHotCar(city);
			serviceResponse.setData(car);
			serviceResponse.setStatus(1);
			serviceResponse.setInfo("获取成功");
		} catch (Exception e) {
			e.printStackTrace();
			serviceResponse.setStatus(0);
			serviceResponse.setInfo("获取失败");
		}
		String json = serviceResponse.objectToJson();
		return json;
	}

	@RequestMapping(value = "getHotCarFee", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String getHotCarFee(String carInfo) {
		ServiceResponse serviceResponse = new ServiceResponse();
		if(null != carInfo  &&  carInfo!= "") {
			carInfo = carInfo.replaceAll("&quot;", "\\\"");
			JSONObject jsStr = JSONObject.parseObject(carInfo);
			String startTime = jsStr.getString("startTime");
			String endTime = jsStr.getString("endTime");
			String city = jsStr.getString("city");
			String carType = jsStr.getString("carType");
			String brand = jsStr.getString("brand");
			String model = jsStr.getString("model");
			try {
				Map<String, Object> map = new HashMap();
				List<DictType> dictBeans = dictTypeService.getChildrenByParent("CarTimeSet");
				int setTime = 0;
				if (dictBeans != null && dictBeans.size() > 0) {
					setTime = Integer.valueOf(dictBeans.get(0).getName());
				}
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");//注意格式化的表达式
				Date startTime1 = format.parse(startTime);
				Date endTime1 = format.parse(endTime);
				Long time1 = startTime1.getTime() - setTime * 60 * 60 * 1000;
				Timestamp timeStartForReserve = new Timestamp(time1);
				Long time2 = endTime1.getTime() + setTime * 60 * 60 * 1000;
				Timestamp timeEndForReserve = new Timestamp(time2);

				String getWay = jsStr.getString("getWay");
				String lonTake = jsStr.getString("lonTake");
				String latTake = jsStr.getString("latTake");
				String shopId=null;
				if (OrderWayEnum.BYSHOP.getCode().equals(getWay) && StringUtils.isNotBlank(lonTake) && StringUtils.isNotBlank(latTake)) {//上门送取，选择比较近门店
					List<Map> shopTake = new ArrayList<>();
					Map<String, String> coordinate = new HashMap();
					coordinate.put("lon", lonTake);
					coordinate.put("lat", latTake);
					coordinate.put("city", city);
					shopTake = carShopsService.getNearestShop(coordinate);
					if(null != shopTake && shopTake.size()>0){//最近的门店
						shopId = shopTake.get(0).get("id")+"";
						map.put("carShopId", shopId);
					}
				}else{
					String carShopId = jsStr.getString("shopId");
					if(null != carShopId && carShopId !=""){
						map.put("carShopId", carShopId);
					}
				}
				map.put("startTime", timeStartForReserve);
				map.put("endTime", timeEndForReserve);
				map.put("city", city);
				map.put("carType", carType);
				map.put("brand", brand);
				map.put("model", model);
				Page page = new Page();
				page.setPageNo(1);
				page.setPageSize(1);
				List<Map> cars = carService.getCarByShop(page, map);
				Map result = null;
				if(null != cars && cars.size()>0){
					result = cars.get(0);
					serviceResponse.setData(result);
					serviceResponse.setStatus(1);
					serviceResponse.setInfo("获取成功");
				}else{
					serviceResponse.setStatus(3);
					serviceResponse.setInfo("该车型暂无库存");
				}
			} catch (Exception e) {
				e.printStackTrace();
				serviceResponse.setData("");
				serviceResponse.setStatus(0);
				serviceResponse.setInfo("获取失败");
			}
		}else{
			serviceResponse.setStatus(2);
			serviceResponse.setInfo("获取参数失败");
		}
		String json = serviceResponse.objectToJson();
		return json;
	}

}