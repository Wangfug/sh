
package com.lte.admin.order.controller;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.order.service.OrderEvaluateService;
import com.lte.admin.system.utils.UserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.order.entity.OrderEvaluate;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;
import com.lte.admin.common.persistence.PropertyFilter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Map;
/**
 * @author Andy
 */
@Controller
@RequestMapping("web/orderEvaluate")
public class OrderEvaluateController extends BaseController  {
	@Resource
	private OrderEvaluateService orderEvaluateService;
	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toOrderView(){
		return "order/orderEvaluateList";
	}

	/**
	 * 增加订单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveOrUpdate",method = RequestMethod.POST)
	@ResponseBody
	public String saveOrUpdate(HttpServletRequest request){
		MemberLogin user = UserUtil.getCurrentUser();
		OrderEvaluate orderEvaluate = getEntity4Request(request);
		if(orderEvaluate.getId()!=null){
			orderEvaluate.setLastTime(new Timestamp(System.currentTimeMillis()));
			orderEvaluate.setLastBy(user.getId());
			orderEvaluateService.update(orderEvaluate);
		}else{
			orderEvaluate.setCreateTime(new Timestamp(System.currentTimeMillis()));
			orderEvaluate.setCreateBy(user.getId());
			orderEvaluate.setLastTime(new Timestamp(System.currentTimeMillis()));
			orderEvaluate.setLastBy(user.getId());
			orderEvaluate.setOrderNo("100000001");
			orderEvaluateService.save(orderEvaluate);
		}
		return "success";
	}

	/**
	 * 增加订单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delete",method = RequestMethod.GET)
	@ResponseBody
	public String delete(HttpServletRequest request){
		Long id = 0l;
		if(StringUtils.isNotBlank(request.getParameter("id"))){
			id = Long.parseLong(request.getParameter("id"));
			orderEvaluateService.deleteById(id);
			return "success";
		}

		return "false";
	}

	/**
	 * 获取订单列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getOrderList",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getOrderList(HttpServletRequest request){
		Page<OrderEvaluate> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<OrderEvaluate> page1 = orderEvaluateService.getList(page, filters);
		return getEasyUIData(page1, request);
	}
	/**
	 * 创建工单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "create",method = {RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletRequest request,String id){
		OrderEvaluate orderEvaluate = null;
		if(StringUtils.isNotBlank(id))
			orderEvaluate = orderEvaluateService.get(Long.parseLong(id));
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("id",id);
		request.setAttribute("orderEvaluate",orderEvaluate);
		return "order/orderEvaluateForm";
	}

	public OrderEvaluate getEntity4Request(HttpServletRequest request) {
			OrderEvaluate entity=new OrderEvaluate();
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
			if(StringUtils.isNotBlank(request.getParameter("grade"))){
			entity.setGrade(request.getParameter("grade"));
			}
			if(StringUtils.isNotBlank(request.getParameter("content"))){
			entity.setContent(request.getParameter("content"));
			}
			if(StringUtils.isNotBlank(request.getParameter("vehicleCondition"))){
			entity.setVehicleCondition(request.getParameter("vehicleCondition"));
			}
			if(StringUtils.isNotBlank(request.getParameter("getVehicleService"))){
			entity.setGetVehicleService(request.getParameter("getVehicleService"));
			}
			if(StringUtils.isNotBlank(request.getParameter("returnVehicleService"))){
			entity.setReturnVehicleService(request.getParameter("returnVehicleService"));
			}
			if(StringUtils.isNotBlank(request.getParameter("openOrder"))){
			entity.setOpenOrder(request.getParameter("openOrder"));
			}
			if(StringUtils.isNotBlank(request.getParameter("closeOrder"))){
			entity.setCloseOrder(request.getParameter("closeOrder"));
			}
			if(StringUtils.isNotBlank(request.getParameter("totalService"))){
			entity.setTotalService(request.getParameter("totalService"));
			}
			if(StringUtils.isNotBlank(request.getParameter("orderNo"))){
			entity.setOrderNo(request.getParameter("orderNo"));
			}
			if(StringUtils.isNotBlank(request.getParameter("attachment"))){
			entity.setAttachment(request.getParameter("attachment"));
			}
			if(StringUtils.isNotBlank(request.getParameter("openOpinion"))){
			entity.setOpenOpinion(request.getParameter("openOpinion"));
			}
			if(StringUtils.isNotBlank(request.getParameter("closeOpinion"))){
			entity.setCloseOpinion(request.getParameter("closeOpinion"));
			}
			if(StringUtils.isNotBlank(request.getParameter("totalOpinion"))){
			entity.setTotalOpinion(request.getParameter("totalOpinion"));
			}
			return entity;
	}
}