
package com.lte.admin.car.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.entity.Car;
import com.lte.admin.car.entity.CarInsurance;
import com.lte.admin.car.entity.CarOutOrIn;
import com.lte.admin.car.entity.CarShops;
import com.lte.admin.car.service.CarOutOrInService;
import com.lte.admin.car.service.CarService;
import com.lte.admin.car.service.CarShopsService;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.entity.DictType;
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
@RequestMapping("web/carOutOrIn")
public class CarOutOrInController extends BaseController  {
	@Resource
	private CarOutOrInService carOutOrInService;
	@Resource
	private CarShopsService carShopsService;
	@Resource
	private CarService carService;

	public CarOutOrIn getEntity4Request(HttpServletRequest request) {
			CarOutOrIn entity=new CarOutOrIn();
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
			if(StringUtils.isNotBlank(request.getParameter("reason"))){
			entity.setReason(request.getParameter("reason"));
			}
			if(StringUtils.isNotBlank(request.getParameter("approveBy"))){
			entity.setApproveBy(Long.valueOf(request.getParameter("approveBy")));
			}
			if(StringUtils.isNotBlank(request.getParameter("outEmp"))){
			entity.setOutEmp(Long.valueOf(request.getParameter("outEmp")));
			}
			if(StringUtils.isNotBlank(request.getParameter("outPosition"))){
			entity.setOutPosition(request.getParameter("outPosition"));
			}
			if(StringUtils.isNotBlank(request.getParameter("dispatchNo"))){
			entity.setDispatchNo(request.getParameter("dispatchNo"));
			}
			if(StringUtils.isNotBlank(request.getParameter("outTime"))){
			entity.setOutTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("outTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("remark"))){
			entity.setRemark(request.getParameter("remark"));
			}
			if(StringUtils.isNotBlank(request.getParameter("inOrOut"))){
			entity.setInOrOut(request.getParameter("inOrOut"));
			}
			if(StringUtils.isNotBlank(request.getParameter("carShop"))){
			entity.setCarShop(Long.valueOf(request.getParameter("carShop")));
			}
			return entity;
	}

	/**
	 * 车辆出入库列表默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(HttpServletRequest request) {
		return "car/carOutOrIn";
	}

	@RequestMapping(value = "saveOrUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveCarOutOrIn(HttpServletRequest request) {
		CarOutOrIn carOutOrIn = this.getEntity4Request(request);
		String result = "false";
		if(carOutOrIn.getId()!=null){
			carOutOrIn.setLastTime(new Timestamp(System.currentTimeMillis()));
			result = carOutOrInService.updateCarOutOrIn(carOutOrIn);
		}else{
			carOutOrIn.setCreateTime(new Timestamp(System.currentTimeMillis()));
			result = carOutOrInService.saveCarOutOrIn(carOutOrIn);
		}
		return result;
	}

	@RequestMapping(value = "getCarOutOrInList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCarAttachList(HttpServletRequest request) {
		Page<CarOutOrIn> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<CarOutOrIn> page1 = carOutOrInService.getList(page,filters);
		return getEasyUIData(page1, request);
	}

	@RequestMapping(value = "getCarOutOrInDetailList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCarOutOrInDetailList(HttpServletRequest request) {
		Page page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<Map> page1 = (PageList<Map>)carOutOrInService.getAllDetail(page,filters);
		return getEasyUIData(page1, request);
	}

	/**
	 *新增车辆出入库信息
	 */
	@RequestMapping(value = "addCarOutOrIn", method = {RequestMethod.GET,RequestMethod.POST})
	public String addCarOutOrIn(Model model,HttpServletRequest request) {
		model.addAttribute("action", "saveOrUpdate");
		List<CarShops> page1 = carShopsService.getList();
		JSONArray jsonArray1 = JSONArray.fromObject(page1);
		model.addAttribute("carShops", jsonArray1);

		List<Map> cars = carService.getList1();
		JSONArray jsonArray2 = JSONArray.fromObject(cars);
		request.setAttribute("cars",jsonArray2);

		return "car/carOutOrInForm";
	}

	/**
	 *修改车辆出入库信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		CarOutOrIn carOutOrIn = carOutOrInService.getOneCarOutOrIn(id);
		model.addAttribute("carOutOrIn", carOutOrIn);
		model.addAttribute("action", "saveOrUpdate");
		/*StringBuilder carStates = new StringBuilder();
		String state1 = "";
		String state2 = "";
		if(carOutOrIn.getInOrOut().equals("1")){
			state1 = "<option value='1' selected>入库</option>";
			state2 = "<option value='2'>出库</option>";
		}else{
			state1 = "<option value='1'>入库</option>";
			state2 = "<option value='2'selected>出库</option>";
		}
		carStates.append(state1);
		carStates.append(state2);
		request.setAttribute("carStatesHtml", carStates.toString());*/
		List<Map> cars = carService.getList1();
		JSONArray jsonArray2 = JSONArray.fromObject(cars);
		request.setAttribute("cars",jsonArray2);

		List<CarShops> page1 = carShopsService.getList();
		JSONArray jsonArray1 = JSONArray.fromObject(page1);
		model.addAttribute("carShops", jsonArray1);

		return "car/carOutOrInForm";
	}
}