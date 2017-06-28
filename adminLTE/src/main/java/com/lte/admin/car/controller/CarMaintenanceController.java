
package com.lte.admin.car.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.entity.Car;
import com.lte.admin.car.entity.CarMaintenance;
import com.lte.admin.car.service.CarMaintenanceService;
import com.lte.admin.car.service.CarService;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.entity.DictType;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.order.service.OrderInfoService;
import com.lte.admin.system.service.DictTypeService;
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
import java.util.List;
import java.util.Map;

/**
 * @author Andy
 */
@Controller
@RequestMapping("web/carMaintenance")
public class CarMaintenanceController extends BaseController  {
	@Resource
	private CarMaintenanceService carMaintenanceService;
	@Resource
	private DictTypeService dictTypeService;
	@Resource
	private CarService carService;
	@Resource
	private OrderInfoService orderInfoService;
	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toCarView(HttpServletRequest request){
//		List<DictType> dictBeans = dictTypeService.getChildrenByParent("InsuranceType");
//		JSONArray array = JSONArray.fromObject(dictBeans);
//		request.getSession().setAttribute("dictsForInsurance1",array);
		return "car/carMaintenance";
	}
	/**
	 * 删除维保
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delete",method = RequestMethod.GET)
	@ResponseBody
	public String delete(HttpServletRequest request){
		Long id = 0l;
		if(StringUtils.isNotBlank(request.getParameter("id"))){
			id = Long.parseLong(request.getParameter("id"));
			carMaintenanceService.deleteById(id);
			return "success";
		}

		return "false";
	}
	
	
	public CarMaintenance getEntity4Request(HttpServletRequest request) {
			CarMaintenance entity=new CarMaintenance();
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
			if(StringUtils.isNotBlank(request.getParameter("maintenanceBy"))){
			entity.setMaintenanceBy(request.getParameter("maintenanceBy"));
			}
			if(StringUtils.isNotBlank(request.getParameter("maintenanceTime"))){
			entity.setMaintenanceTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("maintenanceTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("maintenanceOrder"))){
			entity.setMaintenanceOrder(request.getParameter("maintenanceOrder"));
			}
			if(StringUtils.isNotBlank(request.getParameter("maintenanceContent"))){
			entity.setMaintenanceContent(request.getParameter("maintenanceContent"));
			}
			if(StringUtils.isNotBlank(request.getParameter("maintenanceMoney"))){
			entity.setMaintenanceMoney(Double.valueOf(request.getParameter("maintenanceMoney")));
			}
			if(StringUtils.isNotBlank(request.getParameter("remark"))){
			entity.setRemark(request.getParameter("remark"));
			}
			if(StringUtils.isNotBlank(request.getParameter("attachment"))){
			entity.setAttachment(request.getParameter("attachment"));
			}
			return entity;
	}

	@RequestMapping(value = "saveOrUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveCarMaintenance(HttpServletRequest request) {
		MemberLogin user = UserUtil.getCurrentUser();
		CarMaintenance carMaintenance = this.getEntity4Request(request);
		String result = "false";
		if(carMaintenance.getId()!=null){
			carMaintenance.setLastBy(user.getId());
			carMaintenance.setLastTime(new Timestamp(System.currentTimeMillis()));
			result = carMaintenanceService.updateCarMaintenance(carMaintenance);
		}else{
			carMaintenance.setState(1);
			carMaintenance.setCreateBy(user.getId());
			carMaintenance.setCreateTime(new Timestamp(System.currentTimeMillis()));
			carMaintenance.setLastBy(user.getId());
			carMaintenance.setLastTime(new Timestamp(System.currentTimeMillis()));
			result = carMaintenanceService.saveCarMaintenance(carMaintenance);
		}
		return result;
	}

	@RequestMapping(value = "getList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCarAttachList(HttpServletRequest request) {
		Page<CarMaintenance> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<Map> page1 = carMaintenanceService.getList(page,filters);
		return getEasyUIData(page1, request);
	}

	/**
	 * 创建车辆维保
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "create",method = {RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletRequest request,String id){
		List<Car> cars = carService.getList();
		List<Map> orders = orderInfoService.getList();
		CarMaintenance carMaintenance = null;
		if(StringUtils.isNotBlank(id))
			carMaintenance = carMaintenanceService.getOneCarMaintenance(Long.parseLong(id));
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("carMaintenance",carMaintenance);
		request.setAttribute("cars",cars);
		request.setAttribute("orders",orders);
		return "car/carMaintenanceForm";
	}
}