
package com.lte.admin.custom.controller;
import com.lte.admin.custom.service.CustomerCredentialService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.custom.entity.CustomerCredential;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class CustomerCredentialController extends BaseController  {
	@Resource
	private CustomerCredentialService customerCredentialService;

	public CustomerCredential getEntity4Request(HttpServletRequest request) {
			CustomerCredential entity=new CustomerCredential();
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
			if(StringUtils.isNotBlank(request.getParameter("credentialType"))){
			entity.setCredentialType(request.getParameter("credentialType"));
			}
			if(StringUtils.isNotBlank(request.getParameter("credentialCode"))){
			entity.setCredentialCode(request.getParameter("credentialCode"));
			}
			if(StringUtils.isNotBlank(request.getParameter("attachment"))){
			entity.setAttachment(request.getParameter("attachment"));
			}
			if(StringUtils.isNotBlank(request.getParameter("getTime"))){
			entity.setGetTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("getTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("frontView"))){
			entity.setFrontView(Long.valueOf(request.getParameter("frontView")));
			}
			if(StringUtils.isNotBlank(request.getParameter("backView"))){
			entity.setBackView(Long.valueOf(request.getParameter("backView")));
			}
			return entity;
	}
}