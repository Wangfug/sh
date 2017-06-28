
package com.lte.admin.custom.controller;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.service.CarService;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.custom.service.CustomerIncomeService;
import com.lte.admin.custom.service.CustomerService;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.order.service.OrderInfoService;
import com.lte.admin.system.utils.UserUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.custom.entity.CustomerIncome;
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
@RequestMapping("web/customerIncome")
public class CustomerIncomeController extends BaseController  {
	@Resource
	private CustomerIncomeService customerIncomeService;
	@Resource
	private OrderInfoService orderInfoService;
	@Resource
	private CarService carService;
	@Resource
	private CustomerService customerService;
	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toCarView(HttpServletRequest request){
		List<Map> ordersForIncome = orderInfoService.getList();
		request.getSession().setAttribute("ordersForIncome",ordersForIncome);
		List<Map> carsForIncome = carService.getList1();
		request.getSession().setAttribute("carsForIncome",carsForIncome);
		List<Map> customersForIncome = customerService.getList1();
		request.getSession().setAttribute("customersForIncome",customersForIncome);

		return "custom/customerIncome";
	}
	@RequestMapping(value = "saveOrUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveCustomerIncome(HttpServletRequest request) {
		MemberLogin user = UserUtil.getCurrentUser();
		CustomerIncome customerIncome = this.getEntity4Request(request);
		String result = "false";
		if(customerIncome.getId()!=null){
			result = customerIncomeService.updateCustomerIncome(customerIncome);
		}else{
			customerIncome.setCreateBy(user.getId());
			customerIncome.setCreateTime(new Timestamp(System.currentTimeMillis()));
			result = customerIncomeService.saveCustomerIncome(customerIncome);
		}
		return result;
	}

	@RequestMapping(value = "getList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCarAttachList(HttpServletRequest request) {
		Page<CustomerIncome> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<CustomerIncome> page1 = customerIncomeService.getList(page,filters);
		return getEasyUIData(page1, request);
	}

	/**
	 * 创建门店
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "create",method = {RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletRequest request,String id){
		CustomerIncome customerIncome = null;
		if(StringUtils.isNotBlank(id))
			customerIncome = customerIncomeService.getOneCustomerIncome(Long.parseLong(id));
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("customerIncome",customerIncome);
		return "custom/customerIncomeForm";
	}
	public CustomerIncome getEntity4Request(HttpServletRequest request) {
			CustomerIncome entity=new CustomerIncome();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createBy"))){
			entity.setCreateBy(Long.valueOf(request.getParameter("createBy")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createTime"))){
			entity.setCreateTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("createTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("incomeOrder"))){
			entity.setIncomeOrder(Long.valueOf(request.getParameter("incomeOrder")));
			}
			if(StringUtils.isNotBlank(request.getParameter("incomeCar"))){
			entity.setIncomeCar(Long.valueOf(request.getParameter("incomeCar")));
			}
			if(StringUtils.isNotBlank(request.getParameter("incomeCustomer"))){
			entity.setIncomeCustomer(Long.valueOf(request.getParameter("incomeCustomer")));
			}
			if(StringUtils.isNotBlank(request.getParameter("incomeAccount"))){
			entity.setIncomeAccount(Double.valueOf(request.getParameter("incomeAccount")));
			}
			return entity;
	}
}