
package com.lte.admin.car.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.entity.Car;
import com.lte.admin.car.entity.CarBreakRule;
import com.lte.admin.car.entity.CarShops;
import com.lte.admin.car.service.CarBreakRuleService;
import com.lte.admin.car.service.CarService;
import com.lte.admin.car.service.CarShopsService;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.order.entity.OrderInfo;
import com.lte.admin.order.service.OrderInfoService;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Andy
 */
@Controller
@RequestMapping("web/carBreakRule")
public class CarBreakRuleController extends BaseController  {
	@Resource
	private CarBreakRuleService carBreakRuleService;
	@Resource
	private CarService carService;
	@Resource
	private CarShopsService carShopsService;
	@Resource
	private OrderInfoService orderInfoService;
	public CarBreakRule getEntity4Request(HttpServletRequest request) {
			CarBreakRule entity=new CarBreakRule();
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
			if(StringUtils.isNotBlank(request.getParameter("illegalContent"))){
			entity.setIllegalContent(request.getParameter("illegalContent"));
			}
			if(StringUtils.isNotBlank(request.getParameter("violationFine"))){
			entity.setViolationFine(request.getParameter("violationFine"));
			}
			if(StringUtils.isNotBlank(request.getParameter("illegalDeduction"))){
			entity.setIllegalDeduction(request.getParameter("illegalDeduction"));
			}
			if(StringUtils.isNotBlank(request.getParameter("customerId"))){
			entity.setCustomerId(Long.valueOf(request.getParameter("customerId")));
			}
			if(StringUtils.isNotBlank(request.getParameter("attachment"))){
			entity.setAttachment(request.getParameter("attachment"));
			}
			if(StringUtils.isNotBlank(request.getParameter("carId"))){
			entity.setCarId(Long.valueOf(request.getParameter("carId")));
			}
			if(StringUtils.isNotBlank(request.getParameter("illegalTime"))){
				entity.setIllegalTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("illegalTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("illegalNo"))){
				entity.setIllegalNo(request.getParameter("illegalNo"));
			}
			if(StringUtils.isNotBlank(request.getParameter("dealShop"))){
				entity.setDealShop(Long.valueOf(request.getParameter("dealShop")));
			}
			if(StringUtils.isNotBlank(request.getParameter("illegalPosition"))){
				entity.setIllegalPosition(request.getParameter("illegalPosition"));
			}
			if(StringUtils.isNotBlank(request.getParameter("remark"))){
				entity.setRemark(request.getParameter("remark"));
			}
			if(StringUtils.isNotBlank(request.getParameter("orderNo"))){
				entity.setOrderNo(request.getParameter("orderNo"));
			}
			return entity;
	}
	/**
	 * 车辆违章默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(HttpServletRequest request) {
		List<Map> shops = carShopsService.getAllBaseList();
		JSONArray jsonArray1 = JSONArray.fromObject(shops);
		request.getSession().setAttribute("carShops", jsonArray1);

		return "car/carBreakRule";
	}

	@RequestMapping(value = "saveOrUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveCarBreakRule(HttpServletRequest request) {
		CarBreakRule carBreakRule = this.getEntity4Request(request);
		String result = "false";
		try{
			Long car = carBreakRule.getCarId();
			String orderNo = carBreakRule.getOrderNo();
			Timestamp time = carBreakRule.getIllegalTime();
			String weizhangNO = carBreakRule.getIllegalNo();
			if(car==null||orderNo==null||time==null){
				result = "车、订单号、违章时间为空！";
			}else{
				if(StringUtils.isNotEmpty(weizhangNO)){

				}
				CarBreakRule carBreakRule1 = carBreakRuleService.getOnecarBreakRule(weizhangNO);



				Map<String,Object> filter = new HashMap<String,Object>();
				filter.put("illTime",time);
				filter.put("carId",car);
				String orderNo1 = orderInfoService.getForBreakRule(filter);
				if(orderNo.equals(orderNo1)){
					OrderInfo orderInfo = orderInfoService.getByOrderNo(orderNo);
					carBreakRule.setCustomerId(orderInfo.getCustomer());
					if(carBreakRule.getId()!=null){
						carBreakRule.setLastTime(new Timestamp(System.currentTimeMillis()));
						result = carBreakRuleService.updateCarBreakRule(carBreakRule);
					}else{
						carBreakRule.setCreateTime(new Timestamp(System.currentTimeMillis()));
						result = carBreakRuleService.saveCarBreakRule(carBreakRule);
					}
				}else{
					result = "订单号与违章车辆不匹配，请检查违章时间、车辆是否与订单匹配，如有疑问请联系管理员";
				}
			}
		}catch(Exception e){
			result = "false";
		}
		return result;
	}

	@RequestMapping(value = "getCarBreakRuleList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCarAttachList(HttpServletRequest request) {
		Page<CarBreakRule> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<CarBreakRule> page1 = carBreakRuleService.getList(page,filters);
		return getEasyUIData(page1, request);
	}
	/**
	 *修改车辆信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		Map carBreakRule = carBreakRuleService.getOnecarBreakRuleDetail(id);
		model.addAttribute("carBreakRule", carBreakRule);
		model.addAttribute("action", "saveOrUpdate");

		List<Map> cars = carService.getList1();
		JSONArray jsonArray2 = JSONArray.fromObject(cars);
		model.addAttribute("cars",jsonArray2);

		List<CarShops> shops = carShopsService.getList();
		JSONArray jsonArray1 = JSONArray.fromObject(shops);
//		model.addAttribute("carShops", jsonArray1);
		model.addAttribute("carShops", jsonArray1);
		return "car/carBreakRuleForm";
	}

	/**
	 *新增车辆违章信息
	 */
	@RequestMapping(value = "addCarBreakRule", method = {RequestMethod.GET,RequestMethod.POST})
	public String addCarInsurance(Model model, HttpServletRequest request) {
		model.addAttribute("action", "saveOrUpdate");

		List<Map> cars = carService.getList1();
		JSONArray jsonArray2 = JSONArray.fromObject(cars);
		request.setAttribute("cars",jsonArray2);

		List<CarShops> shops = carShopsService.getList();
		JSONArray jsonArray1 = JSONArray.fromObject(shops);
//		model.addAttribute("carShops", jsonArray1);
		model.addAttribute("carShops", jsonArray1);
		return "car/carBreakRuleForm";
	}

	@RequestMapping(value = "carBreakRuleDetailList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCarBreakRuleDetailList(HttpServletRequest request) {
		Page<CarBreakRule> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<Map> page1 = carBreakRuleService.getDetailList(page,filters);
		return getEasyUIData(page1, request);
	}
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath.trim());
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}
}