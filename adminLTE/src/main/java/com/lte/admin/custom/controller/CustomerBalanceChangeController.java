
package com.lte.admin.custom.controller;
import com.lte.admin.custom.service.CustomerBalanceChangeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.custom.entity.CustomerBalanceChange;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class CustomerBalanceChangeController extends BaseController  {
	@Resource
	private CustomerBalanceChangeService customerBalanceChangeService;

	public CustomerBalanceChange getEntity4Request(HttpServletRequest request) {
			CustomerBalanceChange entity=new CustomerBalanceChange();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createBy"))){
			entity.setCreateBy(Long.valueOf(request.getParameter("createBy")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createTime"))){
			entity.setCreateTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("createTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("beforeChange"))){
			entity.setBeforeChange(Long.valueOf(request.getParameter("beforeChange")));
			}
			if(StringUtils.isNotBlank(request.getParameter("afterChange"))){
			entity.setAfterChange(Long.valueOf(request.getParameter("afterChange")));
			}
			if(StringUtils.isNotBlank(request.getParameter("balanceCustomer"))){
			entity.setBalanceCustomer(Long.valueOf(request.getParameter("balanceCustomer")));
			}
			return entity;
	}
}