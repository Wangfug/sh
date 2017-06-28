
package com.lte.admin.car.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.entity.Car;
import com.lte.admin.car.entity.CarOccupy;
import com.lte.admin.car.service.CarOccupyService;
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
@RequestMapping("web/carOccupy")
public class CarOccupyController extends BaseController  {
	@Resource
	private CarOccupyService carOccupyService;
	@Resource
	private DictTypeService dictTypeService;
	@Resource
	private CarService carService;
	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toCarView(HttpServletRequest request){
		List<Car> cars = carService.getList();
		request.getSession().setAttribute("cars",cars);
		return "car/carOccupy";
	}
	public CarOccupy getEntity4Request(HttpServletRequest request) {
			CarOccupy entity=new CarOccupy();
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
			if(StringUtils.isNotBlank(request.getParameter("timeStart"))){
			entity.setTimeStart(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("timeStart")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("timeEnd"))){
			entity.setTimeEnd(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("timeEnd")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("carId"))){
			entity.setCarId(Long.valueOf(request.getParameter("carId")));
			}
			return entity;
	}

	@RequestMapping(value = "saveOrUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveCarOccupy(HttpServletRequest request) {
		CarOccupy carOccupy = this.getEntity4Request(request);
		String result = "false";
		if(carOccupy.getId()!=null){
			carOccupy.setLastTime(new Timestamp(System.currentTimeMillis()));
			result = carOccupyService.updateCarOccupy(carOccupy);
		}else{
			carOccupy.setCreateTime(new Timestamp(System.currentTimeMillis()));
			result = carOccupyService.saveCarOccupy(carOccupy);
		}
		return result;
	}

	@RequestMapping(value = "getList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCarAttachList(HttpServletRequest request) {
		Page<CarOccupy> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<Map> page1 = carOccupyService.getList(page,filters);
		return getEasyUIData(page1, request);
	}

	/**
	 * 创建车辆出险
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "create",method = {RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletRequest request,String id){
		CarOccupy carOccupy = null;
		if(StringUtils.isNotBlank(id))
			carOccupy = carOccupyService.getOneCarOccupy(Long.parseLong(id));
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("carOccupy",carOccupy);
		return "car/carOccupyForm";
	}
}