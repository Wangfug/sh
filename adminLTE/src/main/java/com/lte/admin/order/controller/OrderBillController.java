
package com.lte.admin.order.controller;
import com.lte.admin.custom.entity.CustomerBalanceCash;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.order.entity.OrderBill;
import com.lte.admin.order.service.OrderBillService;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.system.utils.UserUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Map;

/**
 * @author Andy
 */
@Controller
@RequestMapping("web/orderBill")
public class OrderBillController extends BaseController  {
	@Resource
	private OrderBillService orderBillService;
	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toOrderView(HttpServletRequest request,String state,String readonly){
		request.setAttribute("billState",state);
		request.setAttribute("billReadonly",readonly);
		return "order/orderBillList";
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
		OrderBill orderBill = getEntity4Request(request);
		if(orderBill.getId()!=null){
			orderBill.setLastTime(new Timestamp(System.currentTimeMillis()));
			orderBill.setLastBy(user.getId());
			orderBillService.update(orderBill);
		}else{
			orderBill.setCreateTime(new Timestamp(System.currentTimeMillis()));
			orderBill.setCreateBy(user.getId());
			orderBill.setLastTime(new Timestamp(System.currentTimeMillis()));
			orderBill.setLastBy(user.getId());
			orderBill.setOrderNo("100000001");
			orderBillService.save(orderBill);
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
			orderBillService.deleteById(id);
			return "success";
		}

		return "false";
	}

	/**
	 * 获取订单列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getOrderBillList",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getOrderBillList(HttpServletRequest request,String state,String readonly){
		Page<OrderBill> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<OrderBill> page1 = null;
		try{
			filters.put("state",state);
			filters.put("readonly",readonly);
			page1 = orderBillService.getList(page, filters);
		}catch(Exception e){
			return null;
		}
		return getEasyUIData(page1, request);
	}

	@RequestMapping(value = "updateBill", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String updateBill(String id,String express ,String expressNo) {
		MemberLogin user = UserUtil.getCurrentUser();
		OrderBill bill = orderBillService.get(Long.parseLong(id));
		String result = "false";
		try{
		if(null != bill){
			bill.setLastBy(user.getId());
			bill.setLastTime(new Timestamp(System.currentTimeMillis()));
			bill.setState(1);
			bill.setExpress(express);
			bill.setExpressNo(expressNo);
			orderBillService.update(bill);
			result = "success";
		}
		}catch (Throwable e){
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 创建工单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "create",method = {RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletRequest request,String id){
		OrderBill orderBill = null;
		if(StringUtils.isNotBlank(id))
			orderBill = orderBillService.get(Long.parseLong(id));
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("id",id);
		request.setAttribute("orderBill",orderBill);
		return "order/orderBillForm";
	}
	public OrderBill getEntity4Request(HttpServletRequest request) {
			OrderBill entity=new OrderBill();
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
			if(StringUtils.isNotBlank(request.getParameter("billType"))){
			entity.setBillType(request.getParameter("billType"));
			}
			if(StringUtils.isNotBlank(request.getParameter("billTitle"))){
			entity.setBillTitle(request.getParameter("billTitle"));
			}
			if(ArrayUtils.isNotEmpty(request.getParameterValues("address"))){
				String[] address = request.getParameterValues("address");
				String str1 = "";
				for(String str:address){
					str1+=str+",";
				}
			entity.setAddress(str1.substring(0,str1.length()-1));
			}
			if(StringUtils.isNotBlank(request.getParameter("linkphone"))){
			entity.setLinkphone(request.getParameter("linkphone"));
			}
			if(StringUtils.isNotBlank(request.getParameter("area"))){
			entity.setArea(request.getParameter("area"));
			}
			if(StringUtils.isNotBlank(request.getParameter("taxpayerCode"))){
			entity.setTaxpayerCode(request.getParameter("taxpayerCode"));
			}
			if(StringUtils.isNotBlank(request.getParameter("depositBank"))){
			entity.setDepositBank(request.getParameter("depositBank"));
			}
			if(StringUtils.isNotBlank(request.getParameter("bankAccount"))){
			entity.setBankAccount(request.getParameter("bankAccount"));
			}
			if(StringUtils.isNotBlank(request.getParameter("addresseeName"))){
			entity.setAddresseeName(request.getParameter("addresseeName"));
			}
			if(StringUtils.isNotBlank(request.getParameter("orderNo"))){
			entity.setOrderNo(request.getParameter("orderNo"));
			}
			if(StringUtils.isNotBlank(request.getParameter("customerId"))){
			entity.setCustomerId(Long.valueOf(request.getParameter("customerId")));
			}
		if(StringUtils.isNotBlank(request.getParameter("express"))){
			entity.setExpress(request.getParameter("express"));
		}
		if(StringUtils.isNotBlank(request.getParameter("expressNo"))){
			entity.setExpressNo(request.getParameter("expressNo"));
		}
			return entity;
	}
}