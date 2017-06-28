
package com.lte.admin.car.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.entity.Car;
import com.lte.admin.car.entity.CarOutDanger;
import com.lte.admin.car.service.CarOutDangerService;
import com.lte.admin.car.service.CarService;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.entity.DictType;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.order.entity.OrderInfo;
import com.lte.admin.order.service.OrderInfoService;
import com.lte.admin.system.service.DictTypeService;
import com.lte.admin.system.utils.UserUtil;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.ArrayUtils;
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
@RequestMapping("web/carOutDanger")
public class CarOutDangerController extends BaseController  {
	@Resource
	private CarOutDangerService carOutDangerService;
	@Resource
	private DictTypeService dictTypeService;
	@Resource
	private OrderInfoService orderInfoService;
	@Resource
	private CarService carService;
	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toCarView(HttpServletRequest request){
		List<DictType> dictBeans = dictTypeService.getChildrenByParent("InsuranceType");
		JSONArray array = JSONArray.fromObject(dictBeans);
		request.getSession().setAttribute("dictsForInsurance1",array);
		request.getSession().setAttribute("dictsForInsurance",dictBeans);
		return "car/carOutDanger";
	}
	public CarOutDanger getEntity4Request(HttpServletRequest request) {
			CarOutDanger entity=new CarOutDanger();
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
			if(StringUtils.isNotBlank(request.getParameter("insuranceTime"))){
			entity.setInsuranceTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("insuranceTime")).getTime()));
			}
			if(ArrayUtils.isNotEmpty(request.getParameterValues("insuranceType"))){
				String[] insuranceTypes = request.getParameterValues("insuranceType");
				String insuranceType = "";
				for(String str:insuranceTypes){
					insuranceType+=str+",";
				}
			entity.setInsuranceType(insuranceType.substring(0,insuranceType.length()-1));
			}
			if(StringUtils.isNotBlank(request.getParameter("orderNo"))){
			entity.setOrderNo(Long.valueOf(request.getParameter("orderNo")));
			}
			if(StringUtils.isNotBlank(request.getParameter("repairMoney"))){
			entity.setRepairMoney(Double.valueOf(request.getParameter("repairMoney")));
			}
			if(StringUtils.isNotBlank(request.getParameter("compensation"))){
			entity.setCompensation(request.getParameter("compensation"));
			}
			if(StringUtils.isNotBlank(request.getParameter("badComponent"))){
			entity.setBadComponent(request.getParameter("badComponent"));
			}
			if(StringUtils.isNotBlank(request.getParameter("beforeImage"))){
			entity.setBeforeImage(request.getParameter("beforeImage"));
			}
			if(StringUtils.isNotBlank(request.getParameter("afterImage"))){
			entity.setAfterImage(request.getParameter("afterImage"));
			}
			if(StringUtils.isNotBlank(request.getParameter("remark"))){
			entity.setRemark(request.getParameter("remark"));
			}
			return entity;
	}
	@RequestMapping(value = "saveOrUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveCarOutDanger(HttpServletRequest request) {
		MemberLogin user = UserUtil.getCurrentUser();
		CarOutDanger carOutDanger = this.getEntity4Request(request);

		String result = "false";
		if(carOutDanger.getId()!=null){
			carOutDanger.setLastBy(user.getId());
			carOutDanger.setLastTime(new Timestamp(System.currentTimeMillis()));
			result = carOutDangerService.updateCarOutDanger(carOutDanger);
		}else{
			carOutDanger.setState(1);
			carOutDanger.setCreateBy(user.getId());
			carOutDanger.setCreateTime(new Timestamp(System.currentTimeMillis()));
			carOutDanger.setLastBy(user.getId());
			carOutDanger.setLastTime(new Timestamp(System.currentTimeMillis()));
			result = carOutDangerService.saveCarOutDanger(carOutDanger);
		}
		return result;
	}

	/**
	 * 创建车辆出险
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "create",method = {RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletRequest request,String id){
		List<Car> cars = carService.getList();
		List<Map> orders = orderInfoService.getList();
		CarOutDanger carOutDanger = null;
		if(StringUtils.isNotBlank(id))
			carOutDanger = carOutDangerService.getOneCarOutDanger(Long.parseLong(id));
//		List<DictType> dictBeans = dictTypeService.getChildrenByParent("InsuranceType");
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("carOutDanger",carOutDanger);
		if(carOutDanger!=null&&carOutDanger.getInsuranceType()!=null){
			String[] insuranceType = carOutDanger.getInsuranceType().split(",");
			JSONArray jsonArray = JSONArray.fromObject(insuranceType);
			request.setAttribute("insuranceType",jsonArray);
		}
		request.setAttribute("cars",cars);
		request.setAttribute("orders",orders);
		return "car/carOutDangerForm";
	}


	@RequestMapping(value = "getList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCarAttachList(HttpServletRequest request) {
		Page<CarOutDanger> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<Map> page1 = carOutDangerService.getList(page,filters);
		return getEasyUIData(page1, request);
	}
}