
package com.lte.admin.custom.controller;
import com.lte.admin.custom.service.CustomerCreditCardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.custom.entity.CustomerCreditCard;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
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
}