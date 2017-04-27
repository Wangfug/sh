
package com.lte.admin.other.controller;
import com.lte.admin.other.service.TbaseCompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.other.entity.TbaseCompany;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class TbaseCompanyController extends BaseController  {
	@Resource
	private TbaseCompanyService tbaseCompanyService;

	public TbaseCompany getEntity4Request(HttpServletRequest request) {
			TbaseCompany entity=new TbaseCompany();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
			}
			if(StringUtils.isNotBlank(request.getParameter("companyCode"))){
			entity.setCompanyCode(request.getParameter("companyCode"));
			}
			if(StringUtils.isNotBlank(request.getParameter("companyName"))){
			entity.setCompanyName(request.getParameter("companyName"));
			}
			if(StringUtils.isNotBlank(request.getParameter("parentComanyCode"))){
			entity.setParentComanyCode(request.getParameter("parentComanyCode"));
			}
			if(StringUtils.isNotBlank(request.getParameter("companyType"))){
			entity.setCompanyType(request.getParameter("companyType"));
			}
			if(StringUtils.isNotBlank(request.getParameter("updateTime"))){
			entity.setUpdateTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("updateTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("cityId"))){
			entity.setCityId(Long.valueOf(request.getParameter("cityId")));
			}
			return entity;
	}
}