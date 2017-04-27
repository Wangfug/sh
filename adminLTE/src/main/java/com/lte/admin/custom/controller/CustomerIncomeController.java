
package com.lte.admin.custom.controller;
import com.lte.admin.custom.service.CustomerIncomeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.custom.entity.CustomerIncome;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class CustomerIncomeController extends BaseController  {
	@Resource
	private CustomerIncomeService customerIncomeService;

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