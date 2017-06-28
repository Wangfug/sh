
package com.lte.admin.order.controller;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.order.entity.OrderAccount;
import com.lte.admin.order.service.OrderAccountService;
import com.lte.admin.system.utils.UserUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.order.entity.OrderAccount;
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
@RequestMapping("web/orderAccount")
public class OrderAccountController extends BaseController  {
	@Resource
	private OrderAccountService orderAccountService;
	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toOrderView(){
		return "order/orderAccountList";
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
		OrderAccount orderAccount = getEntity4Request(request);
		if(orderAccount.getId()!=null){
			orderAccountService.update(orderAccount);
		}else{
			orderAccount.setCreateTime(new Timestamp(System.currentTimeMillis()));
			orderAccount.setCreateBy(user.getId());
			orderAccount.setBelongOrder("100000001");
			orderAccountService.save(orderAccount);
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
			orderAccountService.deleteById(id);
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
		Page<OrderAccount> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<OrderAccount> page1 = orderAccountService.getList(page, filters);
		return getEasyUIData(page1, request);
	}
	/**
	 * 创建订单结算流水单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "create",method = {RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletRequest request,String id){
		OrderAccount orderAccount = null;
		if(StringUtils.isNotBlank(id))
			orderAccount = orderAccountService.get(Long.parseLong(id));
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("id",id);
		request.setAttribute("orderAccount",orderAccount);
		return "order/orderAccountForm";
	}

	public OrderAccount getEntity4Request(HttpServletRequest request) {
			OrderAccount entity=new OrderAccount();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createBy"))){
			entity.setCreateBy(Long.valueOf(request.getParameter("createBy")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createTime"))){
			entity.setCreateTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("createTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("totalMoney"))){
			entity.setTotalMoney(Double.valueOf(request.getParameter("totalMoney")));
			}
			if(StringUtils.isNotBlank(request.getParameter("realPay"))){
			entity.setRealPay(Double.valueOf(request.getParameter("realPay")));
			}
			if(StringUtils.isNotBlank(request.getParameter("bylongOrder"))){
			entity.setBelongOrder(request.getParameter("belongOrder"));
			}
			if(StringUtils.isNotBlank(request.getParameter("customer"))){
			entity.setCustomer(Long.valueOf(request.getParameter("customer")));
			}
			if(StringUtils.isNotBlank(request.getParameter("payWay"))){
			entity.setPayWay(request.getParameter("payWay"));
			}
			if(StringUtils.isNotBlank(request.getParameter("payAmount"))){
			entity.setPayAmount(request.getParameter("payAmount"));
			}
			if(StringUtils.isNotBlank(request.getParameter("acceptWay"))){
			entity.setAcceptWay(request.getParameter("acceptWay"));
			}
			if(StringUtils.isNotBlank(request.getParameter("acceptAmount"))){
			entity.setAcceptAmount(request.getParameter("acceptAmount"));
			}
			if(StringUtils.isNotBlank(request.getParameter("acceptMan"))){
			entity.setAcceptMan(request.getParameter("acceptMan"));
			}
			return entity;
	}
}