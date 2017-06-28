
package com.lte.admin.custom.controller;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.custom.entity.Customer;
import com.lte.admin.custom.service.CustomerBalanceChangeService;
import com.lte.admin.custom.service.CustomerService;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.system.utils.UserUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.custom.entity.CustomerBalanceChange;
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
@RequestMapping("web/customerBalanceChange")
public class CustomerBalanceChangeController extends BaseController  {
	@Resource
	private CustomerBalanceChangeService customerBalanceChangeService;
	@Resource
	private CustomerService customerService;

	public CustomerBalanceChange getEntity4Request(HttpServletRequest request) {
			CustomerBalanceChange entity=new CustomerBalanceChange();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createBy"))){
			entity.setCreateBy(Long.valueOf(request.getParameter("createBy")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createTime"))){
			entity.setCreateTime(new Timestamp(DateUtil.stringToDate(request.getParameter("createTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("beforeChange"))){
			entity.setBeforeChange(Double.valueOf(request.getParameter("beforeChange")));
			}
			if(StringUtils.isNotBlank(request.getParameter("afterChange"))){
			entity.setAfterChange(Double.valueOf(request.getParameter("afterChange")));
			}
			if(StringUtils.isNotBlank(request.getParameter("balanceCustomer"))){
			entity.setBalanceCustomer(Long.valueOf(request.getParameter("balanceCustomer")));
			}
		if(StringUtils.isNotBlank(request.getParameter("changeMoney"))){
			entity.setChangeMoney(Double.valueOf(request.getParameter("changeMoney")));
		}
		if(StringUtils.isNotBlank(request.getParameter("type"))){
			entity.setType(Integer.parseInt(request.getParameter("type")));
		}
		if(StringUtils.isNotBlank(request.getParameter("transactionNo"))){
			entity.setTransactionNo(Long.valueOf(request.getParameter("transactionNo")));
		}
		if(StringUtils.isNotBlank(request.getParameter("payWay"))){
			entity.setPayWay(request.getParameter("payWay"));
		}
			return entity;
	}
	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toCarView(HttpServletRequest request){
		List<Customer> customersForBalance = customerService.getList();
		request.getSession().setAttribute("customersForBalance",customersForBalance);
		return "custom/customerBalanceChange";
	}
	@RequestMapping(value = "saveOrUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveCustomerBalanceChange(HttpServletRequest request) {
		MemberLogin user = UserUtil.getCurrentUser();
		CustomerBalanceChange customerBalanceChange = this.getEntity4Request(request);
		String result = "false";
		if(customerBalanceChange.getId()!=null){
			result = customerBalanceChangeService.updateCustomerBalanceChange(customerBalanceChange);
		}else{
			customerBalanceChange.setCreateBy(user.getId());
			customerBalanceChange.setCreateTime(new Timestamp(System.currentTimeMillis()));
			customerBalanceChangeService.saveCustomerBalanceChange(customerBalanceChange);
			result ="success";
		}
		return result;
	}

	@RequestMapping(value = "getList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCarAttachList(HttpServletRequest request) {
		Page<CustomerBalanceChange> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<CustomerBalanceChange> page1 = customerBalanceChangeService.getList(page,filters);
		return getEasyUIData(page1, request);
	}

	/**
	 * 创建门店
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "create",method = {RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletRequest request,String id){
		CustomerBalanceChange customerBalanceChange = null;
		if(StringUtils.isNotBlank(id))
			customerBalanceChange = customerBalanceChangeService.getOneCustomerBalanceChange(Long.parseLong(id));
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("customerBalanceChange",customerBalanceChange);
		return "custom/customerBalanceChangeForm";
	}
}