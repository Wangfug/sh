
package com.lte.admin.order.controller;
import com.lte.admin.common.consts.OrderWorkTypeEnum;
import com.lte.admin.custom.entity.TbaseEmployee;
import com.lte.admin.custom.service.TbaseEmployeeService;
import com.lte.admin.entity.DictType;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.order.entity.OrderInfo;
import com.lte.admin.order.entity.OrderState;
import com.lte.admin.order.service.OrderInfoService;
import com.lte.admin.order.service.OrderWorkService;
import com.lte.admin.system.service.DictTypeService;
import com.lte.admin.system.utils.UserUtil;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.order.entity.OrderWork;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Andy
 */
@Controller
@RequestMapping("web/orderWork")
public class OrderWorkController extends BaseController  {
	@Resource
	private OrderWorkService orderWorkService;
	@Resource
	private DictTypeService dictTypeService;
	@Resource
	private TbaseEmployeeService tbaseEmployeeService;
	@Resource
	private OrderInfoService orderInfoService;

	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toOrderView(HttpServletRequest request,String state){
		List<DictType> dictTypes = dictTypeService.getChildrenByParent("OrderWorkState");
		request.setAttribute("dictTypes",dictTypes);
		request.setAttribute("orderstate",state);
		List<DictType> dictBeans = dictTypeService.getChildrenByParent("OrderState");
		JSONArray jsonArray = JSONArray.fromObject(dictBeans);
		request.getSession().setAttribute("dictsForOrderWork",jsonArray);
		return "order/orderWorkList";
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
		OrderWork orderWork = getEntity4Request(request);
		if(orderWork.getId()!=null){
			orderWork.setLastTime(new Timestamp(System.currentTimeMillis()));
			orderWork.setLastBy(user.getId());
			orderWorkService.update(orderWork);
		}else{
			orderWork.setCreateTime(new Timestamp(System.currentTimeMillis()));
			orderWork.setCreateBy(user.getId());
			orderWork.setLastTime(new Timestamp(System.currentTimeMillis()));
			orderWork.setLastBy(user.getId());
//			orderWork.setOrderNo("10000001");
			orderWorkService.save(orderWork);
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
			orderWorkService.deleteById(id);
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
	public Map<String, Object> getOrderList(HttpServletRequest request,String state){
		MemberLogin memberLogin = (MemberLogin)request.getSession().getAttribute("user");
		PageList<Map> page1 = new PageList<Map>();
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		Page<OrderWork> page = getPage(request);
		try{
			if(memberLogin.getJobCode()!=null&&memberLogin.getJobCode().startsWith("DZ")){
				filters.put("shopCode",memberLogin.getShopCode());
			}else if(memberLogin.getJobCode()!=null&&memberLogin.getJobCode().startsWith("MG")){
				filters.put("parentCode",memberLogin.getShopCode());
			}else if(memberLogin.getJobCode()!=null&&memberLogin.getJobCode().startsWith("KF")){
				filters.put("parentCode",memberLogin.getShopCode());
			}
			if(StringUtils.isNotEmpty(state)){
				filters.put("state",state);
			}
			page1 = orderWorkService.getList1(page, filters);
		}catch(Exception e){
				return null;
		}
		return getEasyUIData(page1, request);
	}
	/**
	 * 创建工单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "create",method = {RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletRequest request,String id){
		OrderWork orderWork = null;
		if(StringUtils.isNotBlank(id))
			orderWork = orderWorkService.get(Long.parseLong(id));
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("id",id);
		request.setAttribute("orderWork",orderWork);
		return "order/orderWorkForm";
	}
	public OrderWork getEntity4Request(HttpServletRequest request) {
			OrderWork entity=new OrderWork();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createBy"))){
			entity.setCreateBy(Long.valueOf(request.getParameter("createBy")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createTime"))){
			entity.setCreateTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("createTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("orderType"))){
			entity.setOrderType(Integer.valueOf(request.getParameter("orderType")));
			}
			if(StringUtils.isNotBlank(request.getParameter("lastTime"))){
			entity.setLastTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("lastTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("lastBy"))){
			entity.setLastBy(Long.valueOf(request.getParameter("lastBy")));
			}
			if(StringUtils.isNotBlank(request.getParameter("way"))){
			entity.setWay(request.getParameter("way"));
			}
			if(StringUtils.isNotBlank(request.getParameter("address"))){
			entity.setAddress(request.getParameter("address"));
			}
			if(StringUtils.isNotBlank(request.getParameter("person"))){
			entity.setPerson(request.getParameter("person"));
			}
			if(StringUtils.isNotBlank(request.getParameter("orderState"))){
			entity.setOrderState(request.getParameter("orderState"));
			}
			if(StringUtils.isNotBlank(request.getParameter("carShop"))){
			entity.setCarShop(Long.valueOf(request.getParameter("carShop")));
			}
			if(StringUtils.isNotBlank(request.getParameter("eno"))){
			entity.setEno(Long.valueOf(request.getParameter("eno")));
			}
			if(StringUtils.isNotBlank(request.getParameter("orderNo"))){
			entity.setOrderNo(request.getParameter("orderNo"));
			}
			if(StringUtils.isNotBlank(request.getParameter("attachment"))){
			entity.setAttachment(request.getParameter("attachment"));
			}
			if(StringUtils.isNotBlank(request.getParameter("carId"))){
			entity.setCarId(Long.valueOf(request.getParameter("carId")));
			}
			if(StringUtils.isNotBlank(request.getParameter("carCheckDetail"))){
			entity.setCarCheckDetail(request.getParameter("carCheckDetail"));
			}
			return entity;
	}

	/**
	 * 根据订单号生成工单
	 */
	@RequestMapping(value = "createOrderWork", method = RequestMethod.GET)
	public String createOrderWork(HttpServletRequest request,String orderNo){
		MemberLogin user = (MemberLogin)request.getSession().getAttribute("user");
		TbaseEmployee employee = tbaseEmployeeService.getOneByCreateBy(user.getMemberCode());
		String shopCode = user.getShopCode();
		List<Map<String,Object>> emps = tbaseEmployeeService.getEmpByShop(shopCode);
		Map<String,Object> filter = new HashMap<String,Object>();
		filter.put("orderNo",orderNo);
		filter.put("shopCode",shopCode);
		List<Map<String,Object>> works = orderWorkService.getByOrderShop(filter);
		for(Map<String,Object> map:works){
			Integer type = (Integer)map.get("order_type");
			if(0==type&&emps.size()>0){
				request.setAttribute("getInfo",map.get("id")+","+emps.get(0).get("id"));
			}
			if(1==type&&emps.size()>0){
				request.setAttribute("returnInfo",map.get("id")+","+emps.get(0).get("id"));
			}
		}
		request.setAttribute("orderNo",orderNo);
		request.setAttribute("emps",emps);
		request.setAttribute("works",works);
		request.setAttribute("action","assignOrder");
		return "order/orderWorkAssign";
	}
	/**
	 * 增加订单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "assignOrder",method = RequestMethod.POST)
	@ResponseBody
	public String assignOrder(HttpServletRequest request,String getInfo,String returnInfo,String orderNo){
		String result = "false";
		try{
			OrderInfo orderInfo =  orderInfoService.getByOrderNo(orderNo);
			if(StringUtils.isNotEmpty(getInfo)){
				String[] getInfos = getInfo.split(",");
				OrderWork ow1 = new OrderWork();
				ow1.setEno(Long.valueOf(getInfos[1]));
				ow1.setId(Long.valueOf(getInfos[0]));
				orderWorkService.update(ow1);
			}
			if(StringUtils.isNotEmpty(returnInfo)){
				String[] returnInfos = returnInfo.split(",");
				OrderWork ow2 = new OrderWork();
				ow2.setEno(Long.valueOf(returnInfos[1]));
				ow2.setId(Long.valueOf(returnInfos[0]));
				orderWorkService.update(ow2);
			}
			orderInfo.setState(10003);
			OrderState orderState = new OrderState();
			orderState.setOrderNo(orderNo);
			orderState.setCreateBy(orderInfo.getCreateBy());
			orderState.setCreateTime(new Timestamp(System.currentTimeMillis()));
			orderState.setOrderStateInfo("分配工单");
			orderState.setState(10003);
			orderInfoService.update(orderInfo,orderState);
			result = "success";
		}catch (Exception e){
			result = "false";
		}
		return result;
	}


}