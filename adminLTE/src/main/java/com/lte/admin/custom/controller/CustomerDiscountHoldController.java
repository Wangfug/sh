
package com.lte.admin.custom.controller;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.custom.entity.CustomerDiscountHold;
import com.lte.admin.custom.service.CustomerDiscountHoldService;
import com.lte.admin.custom.service.CustomerDiscountService;
import com.lte.admin.custom.service.CustomerService;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.system.utils.UserUtil;
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
import java.util.List;
import java.util.Map;

/**
 * @author Andy
 */
@Controller
@RequestMapping("web/customerDiscountHold")
public class CustomerDiscountHoldController extends BaseController  {
	@Resource
	private CustomerDiscountHoldService customerDiscountHoldService;
	@Resource
	private CustomerService customerService;
	@Resource
	private CustomerDiscountService customerDiscountService;
	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toCarView(HttpServletRequest request){
		List<Map> customers = customerService.getList1();
		List<Map> dicounts = customerDiscountService.getList1();
		request.getSession().setAttribute("customersForHoldDiscount",customers);
		request.getSession().setAttribute("discountsForHoldDiscount",dicounts);
		return "custom/customerDiscountHold";
	}
	@RequestMapping(value = "saveOrUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveCustomerDiscountHold(HttpServletRequest request) {
		MemberLogin user = UserUtil.getCurrentUser();
		CustomerDiscountHold customerDiscountHold = this.getEntity4Request(request);
		String result = "false";
		if(customerDiscountHold.getId()!=null){
			result = customerDiscountHoldService.updateCustomerDiscountHold(customerDiscountHold);
		}else{
			customerDiscountHold.setCreateBy(user.getId());
			customerDiscountHold.setCreateTime(new Timestamp(System.currentTimeMillis()));
			result = customerDiscountHoldService.saveCustomerDiscountHold(customerDiscountHold);
		}
		return result;
	}

	@RequestMapping(value = "getList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCarAttachList(HttpServletRequest request) {
		Page<CustomerDiscountHold> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<CustomerDiscountHold> page1 = customerDiscountHoldService.getList(page,filters);
		return getEasyUIData(page1, request);
	}

	/**
	 * 创建门店
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "create",method = {RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletRequest request,String id){
		CustomerDiscountHold customerDiscountHold = null;
		if(StringUtils.isNotBlank(id))
			customerDiscountHold = customerDiscountHoldService.getOneCustomerDiscountHold(Long.parseLong(id));
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("customerDiscountHold",customerDiscountHold);
		return "custom/customerDiscountHoldForm";
	}

	public CustomerDiscountHold getEntity4Request(HttpServletRequest request) {
			CustomerDiscountHold entity=new CustomerDiscountHold();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createBy"))){
			entity.setCreateBy(Long.valueOf(request.getParameter("createBy")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createTime"))){
			entity.setCreateTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("createTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("discount"))){
			entity.setDiscount(Long.valueOf(request.getParameter("discount")));
			}
			if(StringUtils.isNotBlank(request.getParameter("customer"))){
			entity.setCustomer(Long.valueOf(request.getParameter("customer")));
			}
			return entity;
	}
}