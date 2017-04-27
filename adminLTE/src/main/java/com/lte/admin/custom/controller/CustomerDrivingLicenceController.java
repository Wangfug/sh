
package com.lte.admin.custom.controller;
import com.lte.admin.custom.service.CustomerDrivingLicenceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.custom.entity.CustomerDrivingLicence;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class CustomerDrivingLicenceController extends BaseController  {
	@Resource
	private CustomerDrivingLicenceService customerDrivingLicenceService;

	public CustomerDrivingLicence getEntity4Request(HttpServletRequest request) {
			CustomerDrivingLicence entity=new CustomerDrivingLicence();
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
			if(StringUtils.isNotBlank(request.getParameter("quasiDrivingType"))){
			entity.setQuasiDrivingType(request.getParameter("quasiDrivingType"));
			}
			if(StringUtils.isNotBlank(request.getParameter("getTime"))){
			entity.setGetTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("getTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("fileNumber"))){
			entity.setFileNumber(request.getParameter("fileNumber"));
			}
			if(StringUtils.isNotBlank(request.getParameter("attachment"))){
			entity.setAttachment(request.getParameter("attachment"));
			}
			return entity;
	}
}