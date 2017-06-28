
package com.lte.admin.car.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.entity.Car;
import com.lte.admin.car.entity.CarAttach;
import com.lte.admin.car.service.CarAttachService;
import com.lte.admin.car.service.CarService;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.system.service.DictTypeService;
import com.lte.admin.system.utils.UserUtil;
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
@RequestMapping("web/carAttachExamine")
public class CarAttachController extends BaseController  {
	@Resource
	private CarAttachService carAttachService;
	@Resource
	private DictTypeService dictTypeService;
	@Resource
	private CarService carService;
	public CarAttach getEntity4Request(HttpServletRequest request) {
			CarAttach entity=new CarAttach();
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
			if(StringUtils.isNotBlank(request.getParameter("customerId"))){
			entity.setCustomerId(Long.valueOf(request.getParameter("customerId")));
			}
			if(StringUtils.isNotBlank(request.getParameter("otherRemark"))){
			entity.setOtherRemark(request.getParameter("otherRemark"));
			}
			if(StringUtils.isNotBlank(request.getParameter("attachStart"))){
			entity.setAttachStart(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("attachStart")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("attachEnd"))){
			entity.setAttachEnd(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("attachEnd")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("remark"))){
			entity.setRemark(request.getParameter("remark"));
			}
			if(StringUtils.isNotBlank(request.getParameter("attachement"))){
			entity.setAttachement(request.getParameter("attachement"));
			}
			if(StringUtils.isNotBlank(request.getParameter("type"))){
				entity.setType(Integer.valueOf(request.getParameter("type")));
			}
			return entity;
	}

	/**
	 * 跳转主页
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "car/carAttach";
	}

	@RequestMapping(value = "saveOrUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveCarAttach(HttpServletRequest request) {
		CarAttach carAttach = this.getEntity4Request(request);
		String result = "false";
		if(carAttach.getId()!=null){
			result = carAttachService.updateCarAttach(carAttach);
		}else{
			result = carAttachService.saveCarAttach(carAttach);
		}
		return result;
	}
	@RequestMapping(value = "getList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCarAttachList(HttpServletRequest request) {
		Page<CarAttach> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<CarAttach> page1 = carAttachService.getList(page,filters);
		return getEasyUIData(page1, request);
	}
	/**
	 * 删除挂靠车辆
	 * @param request
	 * @return
	*/
	@RequestMapping(value = "delete",method = RequestMethod.GET)
	@ResponseBody
	public String delete(HttpServletRequest request){
		Long id = 0l;
		if(StringUtils.isNotBlank(request.getParameter("id"))){
			id = Long.parseLong(request.getParameter("id"));
			carAttachService.deleteById(id);
			return "success";
		}
		return "false";
	}

	/**
	 * 创建挂靠车辆
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "create",method = {RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletRequest request,String id){
		List<Car> cars = carService.getList();
		CarAttach carAttach = null;
		if(StringUtils.isNotBlank(id))
			carAttach = carAttachService.getOneCarAttach(Long.parseLong(id));
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("carAttach",carAttach);
		request.setAttribute("cars",cars);
		return "car/carAttachForm";
	}
	/**
	 * show挂靠订单
	 * @param request
	 * @return
	 */
	/*@RequestMapping(value = "getAttachOrder",method = {RequestMethod.GET,RequestMethod.POST})
	public String getAttachOrder(HttpServletRequest request){
		return "order/carAttachOrderList";
	}*/

}