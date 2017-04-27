
package com.lte.admin.custom.controller;
import com.lte.admin.custom.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.custom.entity.Customer;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class CustomerController extends BaseController  {
	@Resource
	private CustomerService customerService;

	public Customer getEntity4Request(HttpServletRequest request) {
			Customer entity=new Customer();
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
			if(StringUtils.isNotBlank(request.getParameter("name"))){
			entity.setName(request.getParameter("name"));
			}
			if(StringUtils.isNotBlank(request.getParameter("mobilePhone"))){
			entity.setMobilePhone(request.getParameter("mobilePhone"));
			}
			if(StringUtils.isNotBlank(request.getParameter("password"))){
			entity.setPassword(request.getParameter("password"));
			}
			if(StringUtils.isNotBlank(request.getParameter("email"))){
			entity.setEmail(request.getParameter("email"));
			}
			if(StringUtils.isNotBlank(request.getParameter("attachmenrt"))){
			entity.setAttachmenrt(request.getParameter("attachmenrt"));
			}
			if(StringUtils.isNotBlank(request.getParameter("balance"))){
			entity.setBalance(Double.valueOf(request.getParameter("balance")));
			}
			if(StringUtils.isNotBlank(request.getParameter("identityCard"))){
			entity.setIdentityCard(Long.valueOf(request.getParameter("identityCard")));
			}
			if(StringUtils.isNotBlank(request.getParameter("drivingLicence"))){
			entity.setDrivingLicence(Long.valueOf(request.getParameter("drivingLicence")));
			}
			if(StringUtils.isNotBlank(request.getParameter("otherCard"))){
			entity.setOtherCard(request.getParameter("otherCard"));
			}
			if(StringUtils.isNotBlank(request.getParameter("integral"))){
			entity.setIntegral(Long.valueOf(request.getParameter("integral")));
			}
			return entity;
	}
}