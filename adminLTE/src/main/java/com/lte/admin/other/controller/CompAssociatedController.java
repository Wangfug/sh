
package com.lte.admin.other.controller;
import com.lte.admin.other.service.CompAssociatedService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.other.entity.CompAssociated;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class CompAssociatedController extends BaseController  {
	@Resource
	private CompAssociatedService compAssociatedService;

	public CompAssociated getEntity4Request(HttpServletRequest request) {
			CompAssociated entity=new CompAssociated();
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
			if(StringUtils.isNotBlank(request.getParameter("comName"))){
			entity.setComName(request.getParameter("comName"));
			}
			if(StringUtils.isNotBlank(request.getParameter("corporation"))){
			entity.setCorporation(request.getParameter("corporation"));
			}
			if(StringUtils.isNotBlank(request.getParameter("country"))){
			entity.setCountry(request.getParameter("country"));
			}
			if(StringUtils.isNotBlank(request.getParameter("province"))){
			entity.setProvince(request.getParameter("province"));
			}
			if(StringUtils.isNotBlank(request.getParameter("area"))){
			entity.setArea(request.getParameter("area"));
			}
			if(StringUtils.isNotBlank(request.getParameter("comAddress"))){
			entity.setComAddress(request.getParameter("comAddress"));
			}
			if(StringUtils.isNotBlank(request.getParameter("phone"))){
			entity.setPhone(request.getParameter("phone"));
			}
			if(StringUtils.isNotBlank(request.getParameter("remark"))){
			entity.setRemark(request.getParameter("remark"));
			}
			if(StringUtils.isNotBlank(request.getParameter("attachment"))){
			entity.setAttachment(request.getParameter("attachment"));
			}
			return entity;
	}
}