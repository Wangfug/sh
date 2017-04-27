
package com.lte.admin.custom.controller;
import com.lte.admin.custom.service.CustomerDiscountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.custom.entity.CustomerDiscount;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class CustomerDiscountController extends BaseController  {
	@Resource
	private CustomerDiscountService customerDiscountService;

	public CustomerDiscount getEntity4Request(HttpServletRequest request) {
			CustomerDiscount entity=new CustomerDiscount();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
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
			if(StringUtils.isNotBlank(request.getParameter("discountBelong"))){
			entity.setDiscountBelong(request.getParameter("discountBelong"));
			}
			return entity;
	}
}