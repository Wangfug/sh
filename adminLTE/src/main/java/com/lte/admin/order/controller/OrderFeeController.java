
package com.lte.admin.order.controller;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.order.entity.OrderFee;
import com.lte.admin.order.service.OrderFeeService;
import com.lte.admin.system.utils.UserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.order.entity.OrderFee;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
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
@RequestMapping("web/orderFee")
public class OrderFeeController extends BaseController  {
	@Resource
	private OrderFeeService orderFeeService;
	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toOrderView(){
		return "order/orderFeeList";
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
		OrderFee orderFee = getEntity4Request(request);
		if(orderFee.getId()!=null){
			orderFee.setLastTime(new Timestamp(System.currentTimeMillis()));
			orderFee.setLastBy(user.getId());
			orderFeeService.update(orderFee);
		}else{
			orderFee.setCreateTime(new Timestamp(System.currentTimeMillis()));
			orderFee.setCreateBy(user.getId());
			orderFee.setLastTime(new Timestamp(System.currentTimeMillis()));
			orderFee.setLastBy(user.getId());
			orderFee.setOrderNo("100000001");
			orderFeeService.save(orderFee);
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
			orderFeeService.deleteById(id);
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
		Page<OrderFee> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<OrderFee> page1 = orderFeeService.getList(page, filters);
		return getEasyUIData(page1, request);
	}
	/**
	 * 创建订单费用表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "create",method = {RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletRequest request,String id){
		OrderFee orderFee = null;
		if(StringUtils.isNotBlank(id))
			orderFee = orderFeeService.get(Long.parseLong(id));
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("id",id);
		request.setAttribute("orderFee",orderFee);
		return "order/orderFeeForm";
	}
	public OrderFee getEntity4Request(HttpServletRequest request) {
			OrderFee entity=new OrderFee();
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
			if(StringUtils.isNotBlank(request.getParameter("carRentFee"))){
			entity.setCarRentFee(Double.valueOf(request.getParameter("carRentFee")));
			}
			if(StringUtils.isNotBlank(request.getParameter("sendCarFee"))){
			entity.setSendCarFee(Double.valueOf(request.getParameter("sendCarFee")));
			}
			if(StringUtils.isNotBlank(request.getParameter("baseFee"))){
			entity.setBaseFee(Double.valueOf(request.getParameter("baseFee")));
			}
			if(StringUtils.isNotBlank(request.getParameter("otherFee"))){
			entity.setOtherFee(Double.valueOf(request.getParameter("otherFee")));
			}
			if(StringUtils.isNotBlank(request.getParameter("additionalBujimianpei"))){
			entity.setAdditionalBujimianpei(Double.valueOf(request.getParameter("additionalBujimianpei")));
			}
			if(StringUtils.isNotBlank(request.getParameter("handingCharge"))){
			entity.setHandingCharge(Double.valueOf(request.getParameter("handingCharge")));
			}
			if(StringUtils.isNotBlank(request.getParameter("additionalFeeForThree"))){
			entity.setAdditionalFeeForThree(Double.valueOf(request.getParameter("additionalFeeForThree")));
			}
			if(StringUtils.isNotBlank(request.getParameter("orderNo"))){
			entity.setOrderNo(request.getParameter("orderNo"));
			}
			if(StringUtils.isNotBlank(request.getParameter("totalFee"))){
			entity.setTotalFee(Double.valueOf(request.getParameter("totalFee")));
			}
			if(StringUtils.isNotBlank(request.getParameter("preAuthorized"))){
			entity.setPreAuthorized(Double.valueOf(request.getParameter("preAuthorized")));
			}
			return entity;
	}
}