
package com.lte.admin.custom.controller;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.custom.service.CustomerCreditCardService;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.system.utils.UserUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.custom.entity.CustomerCreditCard;
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
@RequestMapping("web/customerCreditCard")
public class CustomerCreditCardController extends BaseController  {
	@Resource
	private CustomerCreditCardService customerCreditCardService;

	public CustomerCreditCard getEntity4Request(HttpServletRequest request) {
			CustomerCreditCard entity=new CustomerCreditCard();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createBy"))){
			entity.setCreateBy(Long.valueOf(request.getParameter("createBy")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createTime"))){
			entity.setCreateTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("createTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("creditState"))){
			entity.setCreditState(Integer.valueOf(request.getParameter("creditState")));
			}
			if(StringUtils.isNotBlank(request.getParameter("creditNo"))){
			entity.setCreditNo(request.getParameter("creditNo"));
			}
			if(StringUtils.isNotBlank(request.getParameter("creditBank"))){
			entity.setCreditBank(request.getParameter("creditBank"));
			}
			if(StringUtils.isNotBlank(request.getParameter("security"))){
			entity.setSecurity(request.getParameter("security"));
			}
			if(StringUtils.isNotBlank(request.getParameter("linkephone"))){
			entity.setLinkephone(request.getParameter("linkephone"));
			}
			if(StringUtils.isNotBlank(request.getParameter("validityTime"))){
			entity.setValidityTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("validityTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("customer"))){
			entity.setCustomer(Long.valueOf(request.getParameter("customer")));
			}
			return entity;
	}

	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toCarView(HttpServletRequest request){
		return "custom/customerCreditCard";
	}
	@RequestMapping(value = "saveOrUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveCustomerCreditCard(HttpServletRequest request) {
		MemberLogin user = UserUtil.getCurrentUser();
		CustomerCreditCard customerCreditCard = this.getEntity4Request(request);
		String result = "false";
		if(customerCreditCard.getId()!=null){
			customerCreditCard.setCustomer(Math.round(Math.random()*3)+1);
			result = customerCreditCardService.updateCustomerCreditCard(customerCreditCard);
		}else{
			customerCreditCard.setCustomer(Math.round(Math.random()*4)+1);
			customerCreditCard.setCreateBy(user.getId());
			customerCreditCard.setCreateTime(new Timestamp(System.currentTimeMillis()));
			result = customerCreditCardService.saveCustomerCreditCard(customerCreditCard);
		}
		return result;
	}

	@RequestMapping(value = "getList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCarAttachList(HttpServletRequest request) {
		Page<CustomerCreditCard> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<Map> page1 = customerCreditCardService.getList(page,filters);
		return getEasyUIData(page1, request);
	}

	/**
	 * 创建门店
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "create",method = {RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletRequest request,String id){
		CustomerCreditCard customerCreditCard = null;
		if(StringUtils.isNotBlank(id))
			customerCreditCard = customerCreditCardService.getOneCustomerCreditCard(Long.parseLong(id));
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("customerCreditCard",customerCreditCard);
		return "custom/customerCreditCardForm";
	}
}