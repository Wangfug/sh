
package com.lte.admin.car.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.entity.Car;
import com.lte.admin.car.entity.CarFaultHandle;
import com.lte.admin.car.entity.CarOutDanger;
import com.lte.admin.car.entity.CarShops;
import com.lte.admin.car.service.CarFaultHandleService;
import com.lte.admin.car.service.CarService;
import com.lte.admin.car.service.CarShopsService;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.entity.DictType;
import com.lte.admin.order.service.OrderInfoService;
import com.lte.admin.system.service.DictTypeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.ArrayUtils;
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
@RequestMapping("web/carFaultHandle")
public class CarFaultHandleController extends BaseController  {
	@Resource
	private CarFaultHandleService carFaultHandleService;
	@Resource
	private DictTypeService dictTypeService;
	@Resource
	private CarShopsService carShopsService;
	@Resource
	private CarService carService;
	@Resource
	private OrderInfoService orderInfoService;

	public CarFaultHandle getEntity4Request(HttpServletRequest request) {
			CarFaultHandle entity=new CarFaultHandle();
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
			if(ArrayUtils.isNotEmpty(request.getParameterValues("faultComponengt"))){
				String[] componengts = request.getParameterValues("faultComponengt");
				String carComponengt="";
				for(String componengt:componengts){
					carComponengt+=componengt+",";
				}
			entity.setFaultComponengt(carComponengt);
			}
			if(StringUtils.isNotBlank(request.getParameter("handleBy"))){
			entity.setHandleBy(request.getParameter("handleBy"));
			}
			if(StringUtils.isNotBlank(request.getParameter("faultOrder"))){
			entity.setFaultOrder(Long.valueOf(request.getParameter("faultOrder")));
			}
			if(StringUtils.isNotBlank(request.getParameter("outDangerNo"))){
			entity.setOutDangerNo(Long.valueOf(request.getParameter("outDangerNo")));
			}
			if(StringUtils.isNotBlank(request.getParameter("getMoney"))){
			entity.setGetMoney(Double.valueOf(request.getParameter("getMoney")));
			}
			if(StringUtils.isNotBlank(request.getParameter("compensator"))){
			entity.setCompensator(request.getParameter("compensator"));
			}
			if(StringUtils.isNotBlank(request.getParameter("repairMoney"))){
			entity.setRepairMoney(Double.valueOf(request.getParameter("repairMoney")));
			}
			if(StringUtils.isNotBlank(request.getParameter("provideShop"))){
			entity.setProvideShop(Long.valueOf(request.getParameter("provideShop")));
			}
			if(StringUtils.isNotBlank(request.getParameter("faultTime"))){
			entity.setFaultTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("faultTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("repairTime"))){
			entity.setRepairTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("repairTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("faultDescr"))){
			entity.setFaultDescr(request.getParameter("faultDescr"));
			}
			if(StringUtils.isNotBlank(request.getParameter("remark"))){
			entity.setRemark(request.getParameter("remark"));
			}
			if(StringUtils.isNotBlank(request.getParameter("attachment"))){
			entity.setAttachment(request.getParameter("attachment"));
			}
			return entity;
	}
	/**
	 * 车辆信息列表默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(HttpServletRequest request) {
		List<DictType> dictsForCarFaultComponengt = dictTypeService.getChildrenByParent("CarComponent");
		JSONArray jsonArray = JSONArray.fromObject(dictsForCarFaultComponengt);
		request.getSession().setAttribute("dictsForCarFaultComponengt", jsonArray);

		return "car/carFault";
	}

	@RequestMapping(value = "saveOrUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveCarBreakRule(HttpServletRequest request) {
		CarFaultHandle carFaultHandle = this.getEntity4Request(request);
		String result = "false";
		if(carFaultHandle.getId()!=null){
			carFaultHandle.setLastTime(new Timestamp(System.currentTimeMillis()));
			result = carFaultHandleService.updateCarFaultHandle(carFaultHandle);
		}else{
			carFaultHandle.setCreateTime(new Timestamp(System.currentTimeMillis()));
			result = carFaultHandleService.saveCarFaultHandle(carFaultHandle);
		}
		return result;
	}

	/**
	 *新增车辆故障处理信息
	 */
	@RequestMapping(value = "addCarFaultHandle", method = {RequestMethod.GET,RequestMethod.POST})
	public String addForm(HttpServletRequest request,Model model) {
		model.addAttribute("action", "saveOrUpdate");

		List<CarShops> page1 = carShopsService.getList();
		JSONArray jsonArray1 = JSONArray.fromObject(page1);
		model.addAttribute("carShops", jsonArray1);

		List<Map> cars = carService.getList1();
		JSONArray jsonArray2 = JSONArray.fromObject(cars);
		model.addAttribute("cars",jsonArray2);

		List<Map> orders = orderInfoService.getList();
		JSONArray jsonArray3 = JSONArray.fromObject(orders);
		model.addAttribute("orders",orders);

		return "car/carFaultForm";
	}

	/**
	 *修改车辆故障信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		List<DictType> dictTypeBeans = dictTypeService.getChildrenByParent("CarComponent");
//		CarFaultHandle carFaultHandle = carFaultHandleService.getOneCarFaultHandle(id);
		Map carFaultHandleDetail = carFaultHandleService.getOneCarFaultHandleDetail(id);
		request.setAttribute("carFaultHandle", carFaultHandleDetail);
		model.addAttribute("action", "saveOrUpdate");
		StringBuilder carState = new StringBuilder();

		List<Map> cars = carService.getList1();
		JSONArray jsonArray2 = JSONArray.fromObject(cars);
		request.setAttribute("cars",jsonArray2);

		Page<CarShops> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<CarShops> page1 = carShopsService.getList(page,filters);
		JSONArray jsonArray1 = JSONArray.fromObject(page1);
		request.setAttribute("carShops", jsonArray1);

		List<Map> orders = orderInfoService.getList();
		JSONArray jsonArray3 = JSONArray.fromObject(orders);
		request.setAttribute("orders",orders);

		if(carFaultHandleDetail!=null && carFaultHandleDetail.get("fault_componengt") != null){
			String[] componengs = carFaultHandleDetail.get("fault_componengt").toString().split(",");
			JSONArray jsonArray = JSONArray.fromObject(componengs);
			request.setAttribute("componengs",jsonArray);
		}
		return "car/carFaultForm";
	}

	@RequestMapping(value = "getCarFaultHandleList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCarAttachList(HttpServletRequest request) {
		Page<CarFaultHandle> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<CarFaultHandle> page1 = carFaultHandleService.getList(page,filters);
		return getEasyUIData(page1, request);
	}

	@RequestMapping(value = "getCarFaultHandleDetailList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCarFaultHandleDetailList(HttpServletRequest request,Model model) {
		Page page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		List<Map> map = carFaultHandleService.getAllDetail(page,filters);
		PageList<Map> page1 = (PageList<Map>) map;
		return getEasyUIData(page1, request);
	}
}