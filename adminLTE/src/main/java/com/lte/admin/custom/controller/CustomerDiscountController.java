
package com.lte.admin.custom.controller;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.custom.service.CustomerDiscountService;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.system.utils.UserUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.custom.entity.CustomerDiscount;
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
@RequestMapping("web/customerDiscount")
public class CustomerDiscountController extends BaseController  {
	@Resource
	private CustomerDiscountService customerDiscountService;

	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toCarView(HttpServletRequest request){
		return "custom/customerDiscount";
	}
	@RequestMapping(value = "saveOrUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveCustomerDiscount(HttpServletRequest request) {
		MemberLogin user = UserUtil.getCurrentUser();
		CustomerDiscount customerDiscount = this.getEntity4Request(request);
		String result = "false";
		if(customerDiscount.getId()!=null){
			result = customerDiscountService.updateCustomerDiscount(customerDiscount);
		}else{
			customerDiscount.setCreateBy(user.getId());
			customerDiscount.setCreateTime(new Timestamp(System.currentTimeMillis()));
			result = customerDiscountService.saveCustomerDiscount(customerDiscount);
		}
		return customerDiscount.getId()+"";
	}

	@RequestMapping(value = "getList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCarAttachList(HttpServletRequest request) {
		Page<CustomerDiscount> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<CustomerDiscount> page1 = customerDiscountService.getList(page,filters);
		return getEasyUIData(page1, request);
	}

	/**
	 * 创建门店
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "create",method = {RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletRequest request,String id){
		CustomerDiscount customerDiscount = null;
		if(StringUtils.isNotBlank(id))
			customerDiscount = customerDiscountService.getOneCustomerDiscount(Long.parseLong(id));
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("customerDiscount",customerDiscount);
		return "custom/customerDiscountForm";
	}

	public CustomerDiscount getEntity4Request(HttpServletRequest request) {
			CustomerDiscount entity=new CustomerDiscount();
			if(StringUtils.isNotBlank(request.getParameter("disid"))){
			entity.setId(Long.valueOf(request.getParameter("disid")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createBy"))){
			entity.setCreateBy(Long.valueOf(request.getParameter("createBy")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createTime"))){
			entity.setCreateTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("createTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("discountState"))){
			entity.setDiscountState(Integer.valueOf(request.getParameter("discountState")));
			}
			if(StringUtils.isNotBlank(request.getParameter("discountNumber"))){
			entity.setDiscountNumber(Long.valueOf(request.getParameter("discountNumber")));
			}
			if(StringUtils.isNotBlank(request.getParameter("discountInfo"))){
			entity.setDiscountInfo(request.getParameter("discountInfo"));
			}
			if(StringUtils.isNotBlank(request.getParameter("validtime"))){
			entity.setValidtime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("validtime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("discountMoney"))){
				entity.setDiscountMoney(Double.valueOf(request.getParameter("discountMoney")));
			}
			if(StringUtils.isNotBlank(request.getParameter("minimumConsumption"))){
				entity.setMinimumConsumption(Double.valueOf(request.getParameter("minimumConsumption")));
			}
			if(StringUtils.isNotBlank(request.getParameter("discountTitle"))){
				entity.setDiscountTitle(request.getParameter("discountTitle"));
			}
			return entity;
	}
}