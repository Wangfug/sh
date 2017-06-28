
package com.lte.admin.order.controller;
import com.lte.admin.entity.DictType;
import com.lte.admin.entity.Member;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.order.service.OrderStateService;
import com.lte.admin.system.service.DictTypeService;
import com.lte.admin.system.utils.UserUtil;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.order.entity.OrderState;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;
import com.lte.admin.order.entity.OrderBill;
import com.lte.admin.order.service.OrderBillService;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author Andy
 */
@Controller
@RequestMapping("web/orderState")
public class OrderStateController extends BaseController  {
	@Resource
	private OrderStateService orderStateService;
	@Resource
	private DictTypeService dictTypeService;
	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toOrderView(){
		return "order/orderState";
	}

	/**
	 * 增加订单状态
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveOrUpdate",method = RequestMethod.POST)
	@ResponseBody
	public String saveOrUpdate(HttpServletRequest request){
		MemberLogin user = UserUtil.getCurrentUser();
		OrderState orderState = getEntity4Request(request);
		DictType dictBean = null;
		if(orderState.getState()!=null)
			dictBean = dictTypeService.getDictByCode(orderState.getState()+"");
		if(dictBean!=null)
			orderState.setOrderStateInfo(dictBean.getName());
		if(orderState.getId()!=null){
			orderStateService.update(orderState);
		}else{
			orderState.setCreateBy(user.getId());
			orderState.setCreateTime(new Timestamp(System.currentTimeMillis()));
			orderState.setOrderNo("10000001");
			orderStateService.save(orderState);
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
	public String delete(HttpServletRequest request,String id){
			orderStateService.deleteById(Long.parseLong(id));
			return "success";
	}

	/**
	 * 获取订单列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getOrderList",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getOrderList(HttpServletRequest request){
		Page<OrderState> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<OrderState> page1 = orderStateService.getList(page, filters);
		return getEasyUIData(page1, request);
	}
	/**
	 * 创建订单状态
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "create",method = {RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletRequest request,String id){
		OrderState orderState = null;
		if(StringUtils.isNotBlank(id))
			orderState = orderStateService.get(Long.parseLong(id));
		List<DictType> dictBeans = dictTypeService.getChildrenByParent("OrderState");
		request.setAttribute("dictsForOrderState",dictBeans);
		if(orderState!=null)
		request.setAttribute("state",orderState.getState());
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("id",id);
		return "order/orderStateForm";
	}


	public OrderState getEntity4Request(HttpServletRequest request) {
			OrderState entity=new OrderState();
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
			if(StringUtils.isNotBlank(request.getParameter("orderId"))){
			entity.setOrderNo(request.getParameter("orderId"));
			}
			if(StringUtils.isNotBlank(request.getParameter("orderStateInfo"))){
			entity.setOrderStateInfo(request.getParameter("orderStateInfo"));
			}
			return entity;
	}
}