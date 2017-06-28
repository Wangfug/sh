
package com.lte.admin.car.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.entity.Car;
import com.lte.admin.car.entity.CarInsurance;
import com.lte.admin.car.service.CarInsuranceService;
import com.lte.admin.car.service.CarService;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.entity.DictType;
import com.lte.admin.system.service.DictTypeService;
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
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author Andy
 */
@Controller
@RequestMapping("web/carInsurance")
public class CarInsuranceController extends BaseController  {
	@Resource
	private CarInsuranceService carInsuranceService;
	@Resource
	private CarService carService;

	@Resource
	private DictTypeService dictTypeService;


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
			if(StringUtils.isNotBlank(request.getParameter("insuranceId"))){
				entity.setInsuranceId(request.getParameter("insuranceId"));
			}
			return entity;
	}

	/**
	 * 车辆保险列表默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(HttpServletRequest request) {
		List<DictType> dictBeans = dictTypeService.getChildrenByParent("InsuranceType");
		JSONArray array = JSONArray.fromObject(dictBeans);
		request.setAttribute("dictsForInsurance",array);

		return "car/carInsurance";
	}

	@RequestMapping(value = "saveOrUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveCarInsurance(HttpServletRequest request) {
		CarInsurance carInsurance = this.getEntity4Request(request);
		String result = "false";
		if(carInsurance.getId()!=null){
			carInsurance.setLastTime(new Timestamp(System.currentTimeMillis()));
			result = carInsuranceService.updateCarInsurance(carInsurance);
		}else{
			carInsurance.setCreateTime(new Timestamp(System.currentTimeMillis()));
			result = carInsuranceService.saveCarInsurance(carInsurance);
		}
		return result;
	}

	@RequestMapping(value = "getCarInsuranceList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCarAttachList(HttpServletRequest request) {
		Page<CarInsurance> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<CarInsurance> page1 = carInsuranceService.getList(page,filters);
		return getEasyUIData(page1, request);
	}

	@RequestMapping(value = "getCarInsuranceDetailList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCarInsuranceDetailList(HttpServletRequest request) {
		Page page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<Map> page1 = (PageList<Map> )carInsuranceService.getAllDetail(page,filters);
		return getEasyUIData(page1, request);
	}
	/**
	 *新增车辆保险信息
	 */
	@RequestMapping(value = "addCarInsurance", method = {RequestMethod.GET,RequestMethod.POST})
	public String addCarInsurance(Model model,HttpServletRequest request) {
		model.addAttribute("action", "saveOrUpdate");

		List<Map> cars = carService.getList1();
		JSONArray jsonArray2 = JSONArray.fromObject(cars);
		request.setAttribute("cars",jsonArray2);

		List<DictType> dictBeans = dictTypeService.getChildrenByParent("InsuranceType");
		JSONArray array = JSONArray.fromObject(dictBeans);
		request.setAttribute("dictsForInsurance",array);

		return "car/carInsuranceForm";
	}

	/**
	 *修改车辆保险信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		Map carInsurance = carInsuranceService.getOneCarInsuranceDetail(id);
		model.addAttribute("carInsurance", carInsurance);
		model.addAttribute("action", "saveOrUpdate");
		List<Map> cars = carService.getList1();
		JSONArray jsonArray2 = JSONArray.fromObject(cars);
		request.setAttribute("cars",jsonArray2);

		List<DictType> dictBeans = dictTypeService.getChildrenByParent("InsuranceType");
		JSONArray array = JSONArray.fromObject(dictBeans);
		request.setAttribute("dictsForInsurance",array);

		return "car/carInsuranceForm";
	}
}