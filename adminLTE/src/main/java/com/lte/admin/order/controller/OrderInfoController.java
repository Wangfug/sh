
package com.lte.admin.order.controller;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.entity.CarShops;
import com.lte.admin.car.service.CarShopsService;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.response.ServiceResponse;
import com.lte.admin.custom.entity.CustomerBalanceCash;
import com.lte.admin.entity.DictBean;
import com.lte.admin.entity.DictType;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.order.dao.OrderStateDao;
import com.lte.admin.order.entity.OrderState;
import com.lte.admin.order.entity.OrderWork;
import com.lte.admin.order.service.OrderInfoService;
import com.lte.admin.order.service.OrderWorkService;
import com.lte.admin.system.service.DictTypeService;
import com.lte.admin.system.utils.UserUtil;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.order.entity.OrderInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Andy
 */
@Controller
@RequestMapping(value= "web/orderInfo")
public class OrderInfoController extends BaseController  {
	@Resource
	private OrderInfoService orderInfoService;
	@Resource
	private DictTypeService dictTypeService;
	@Resource
	private CarShopsService carShopsService;
	@Resource
	private OrderWorkService orderWorkService;
	/**
	 * 跳转订单主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toOrderView(HttpServletRequest request,String type){
		List<DictType> dictBeans = dictTypeService.getChildrenByParent("OrderState");
		StringBuilder zglsxCombo = new StringBuilder(
				"<option value=''>全部订单</option>");
		String ZGLSX_COMBO = "<option value='%s'>%s</option>";
		for (DictType tmp : dictBeans) {
			zglsxCombo.append(
					String.format(ZGLSX_COMBO, tmp.getCode(), tmp.getName()));
		}
		request.setAttribute("orderState", zglsxCombo.toString());
		JSONArray jsonArray = JSONArray.fromObject(dictBeans);
		request.getSession().setAttribute("dictsForOrder",jsonArray);
		request.getSession().setAttribute("dictsForOrder1",dictBeans);
		request.setAttribute("type",type);
		return "order/orderList";
	}

	private List<DictBean> makeTmpData() {
		return null;
	}

	/**
	 * 增加订单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveOrUpdate",method = RequestMethod.POST)
	@ResponseBody
	public String saveOrUpdate(HttpServletRequest request){
		OrderInfo orderInfo = getEntity4Request(request);
		if(orderInfo.getId()!=null){
			MemberLogin user = UserUtil.getCurrentUser();
			orderInfo.setLastBy(user.getId());
			orderInfo.setLastTime(new Timestamp(System.currentTimeMillis()));
			orderInfoService.update(orderInfo);
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
			orderInfo.setOrderNo(sdf.format(new Date())+orderInfoService.getOrderNo());
			orderInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
			orderInfo.setLastTime(new Timestamp(System.currentTimeMillis()));
			orderInfoService.save(orderInfo);
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
			orderInfoService.deleteById(id);
			return "success";
		}

		return "false";
	}

	/**
	 * 获取挂靠订单列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getAttachOrderList",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getAttachOrderList(HttpServletRequest request){
		MemberLogin memberLogin = (MemberLogin)request.getSession().getAttribute("user");
		PageList<Map> page1 = new PageList<Map>();
		Page<OrderInfo> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		try{
			page1 = orderInfoService.getAttachOrderList(page, filters);
		}catch(Exception e){
			return null;
		}
		return getEasyUIData(page1,request);
	}
	/**
	 * 获取订单列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getOrderList",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getOrderList(HttpServletRequest request){
		MemberLogin memberLogin = (MemberLogin)request.getSession().getAttribute("user");
		PageList<Map> page1 = new PageList<Map>();
		Page<OrderInfo> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		try{
		if(memberLogin.getJobCode()!=null){
					CarShops shop =null;
					String shopCode = memberLogin.getShopCode();
					if(StringUtils.isNotEmpty(shopCode)){
						shop = carShopsService.getOneCarShops(shopCode);
					}
					if(shop!=null){
						//查询所有
						if("SH".equals(shop.getShopCode())){
							page1 = orderInfoService.getOrderList(page, filters);
						}
						//自营  或联营门店订单
						if(shop.getShopCode().endsWith("ZY")||shop.getShopCode().endsWith("LY")){
							filters.put("parentCode",memberLogin.getShopCode());
							page1 = orderInfoService.getOrderList(page, filters);
						}
						//市级所有门店订单
						if("SH".equals(shop.getParentCode())&&!"SH".equals(shop.getShopCode())){
								filters.put("parentCode",memberLogin.getShopCode());
								page1 = orderInfoService.getOrderList1(page, filters);
						}
						if("1".equals(shop.getSellShop())){
							filters.put("shopCode",memberLogin.getShopCode());
							page1 = orderInfoService.getOrderList(page, filters);
						}
//						}
					}

//				}
					}else if("admin".equals(memberLogin.getMemberCode())){
						page1 = orderInfoService.getOrderList(page, filters);
					}
			}catch(Exception e){
				return null;
			}

		return getEasyUIData(page1,request);
	}
	/**
	 * 获取订单详情
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "view",method = RequestMethod.GET)
	public String view(HttpServletRequest request,String orderNo){
        Map<String,Object> orderDetail = orderInfoService.getOrderDetail(orderNo);
        List<Map<String,Object>> ways = orderInfoService.getOrderWorkList(orderNo);
        if(orderDetail.get("getShop")!=null){
				Long shopId = (Long)orderDetail.get("getShop");
				CarShops getShop = carShopsService.getOneCarShops(shopId);
			orderDetail.put("getShopName",getShop.getShopName());
			orderDetail.put("getShopCode",getShop.getShopCode());
		}
		if(orderDetail.get("returnShop")!=null){
			Long shopId = (Long)orderDetail.get("returnShop");
			CarShops getShop = carShopsService.getOneCarShops(shopId);
			orderDetail.put("returnShopName",getShop.getShopName());
			orderDetail.put("returnShopCode",getShop.getShopCode());
		}
        for(Map<String,Object> map:ways){
        	if(map.get("type")!=null){
				if(0==(Integer)map.get("type")){
					if("0".equals(map.get("way"))){
						orderDetail.put("getWAY","上门送取");
					}else if("1".equals(map.get("way"))){
						orderDetail.put("getWAY","门店取还");
					}
				}
				if(1==(Integer)map.get("type")){
					if("0".equals(map.get("way"))){
						orderDetail.put("returnWAY","上门送取");
					}else if("1".equals(map.get("way"))){
						orderDetail.put("returnWAY","门店取还");
					}
				}
			}
		}
		MemberLogin memberLogin = (MemberLogin)request.getSession().getAttribute("user");
		String roleType = memberLogin.getJobCode()==null?null:memberLogin.getJobCode().substring(0,2);
        request.setAttribute("roleType",roleType);
		request.setAttribute("orderDetail",orderDetail);
		return "order/orderInfoDetail";
	}
	/**
	 * 修改订单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "update",method = RequestMethod.GET)
	public String update(HttpServletRequest request,String id){
		OrderInfo orderInfo = null;
		if(StringUtils.isNotBlank(id)){
			 orderInfo = orderInfoService.getByPK(Long.parseLong(id));
		}
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("order",orderInfo);
		return "order/orderInfoForm";
	}

	public OrderInfo getEntity4Request(HttpServletRequest request) {
			OrderInfo entity=new OrderInfo();
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
			if(StringUtils.isNotBlank(request.getParameter("customer"))){
			entity.setCustomer(Long.valueOf(request.getParameter("customer")));
			}
			if(StringUtils.isNotBlank(request.getParameter("orderNo"))){
			entity.setOrderNo(request.getParameter("orderNo"));
			}
			if(StringUtils.isNotBlank(request.getParameter("realTimeStart"))){
			entity.setRealTimeStart(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("realTimeStart")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("realTimeEnd"))){
			entity.setRealTimeEnd(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("realTimeEnd")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("totalFee"))){
			entity.setTotalFee(Double.valueOf(request.getParameter("totalFee")));
			}
			if(StringUtils.isNotBlank(request.getParameter("reserveTimeStart"))){
			entity.setReserveTimeStart(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("reserveTimeStart")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("payTime"))){
			entity.setPayTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("payTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("payWay"))){
			entity.setPayWay(request.getParameter("payWay"));
			}
			if(StringUtils.isNotBlank(request.getParameter("payAccount"))){
			entity.setPayAccount(request.getParameter("payAccount"));
			}
			if(StringUtils.isNotBlank(request.getParameter("reserveType"))){
			entity.setReserveType(request.getParameter("reserveType"));
			}
			if(StringUtils.isNotBlank(request.getParameter("carId"))){
			entity.setCarId(Long.valueOf(request.getParameter("carId")));
			}
			if(StringUtils.isNotBlank(request.getParameter("reserveTimeEnd"))){
			entity.setReserveTimeEnd(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("reserveTimeEnd")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("realCarType"))){
			entity.setRealCarType(request.getParameter("realCarType"));
			}
			if(StringUtils.isNotBlank(request.getParameter("realPay"))){
			entity.setRealPay(Double.valueOf(request.getParameter("realPay")));
			}
			if(StringUtils.isNotBlank(request.getParameter("finalFee"))){
			entity.setFinalFee(Long.valueOf(request.getParameter("finalFee")));
			}
			if(StringUtils.isNotBlank(request.getParameter("deleteFlag"))){
				entity.setDeleteFlag(Integer.valueOf(request.getParameter("deleteFlag")));
			}
		if(StringUtils.isNotBlank(request.getParameter("way"))){
			entity.setWay(request.getParameter("way"));
		}
			return entity;
	}

	/**
	 * 取消订单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "cancelOrder",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String cancelOrder(HttpServletRequest request,String orderNo,String reason){
		ServiceResponse serviceResponse = new ServiceResponse();
		try{
			OrderInfo orderInfo = orderInfoService.getByOrderNo(orderNo);
			orderInfo.setState(10006);
			OrderState orderState = new OrderState();
			orderState.setOrderNo(orderNo);
			orderState.setCreateBy(orderInfo.getCreateBy());
			orderState.setCreateTime(new Timestamp(System.currentTimeMillis()));
			orderState.setOrderStateInfo(reason);
			orderState.setState(10006);
			List<OrderWork> works = orderWorkService.getByOrder1(orderNo);
			boolean res = orderInfoService.update(orderInfo,orderState,works);
			if(res){
				serviceResponse.setStatus(1);
				serviceResponse.setInfo("取消成功！");
			}else{
				serviceResponse.setInfo("取消失败！");
			}
		}catch(Exception e){
			serviceResponse.setInfo("取消失败！");
		}
		return serviceResponse.objectToJson();
	}

	/**
	 * 获取最近半小时的订单
	 * @param
	 * @return
	 */
	@RequestMapping(value = "getNewOrder",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String getNewOrder(){
		ServiceResponse serviceResponse = new ServiceResponse();
		try{
			Integer count = orderInfoService.getNewOrderNumber(30);
			Integer count1 = orderInfoService.getNewOrderNumber(1);
			List<Integer> counts = new ArrayList<Integer>();
			counts.add(count);
			counts.add(count1);
			serviceResponse.setData(counts);
			serviceResponse.setStatus(1);
		}catch(Exception e){
			serviceResponse.setInfo("取消失败！");
		}
		return serviceResponse.objectToJson();
	}

}