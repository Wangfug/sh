
package com.lte.admin.other.controller;
import com.lte.admin.other.service.TcityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.other.entity.Tcity;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class TcityController extends BaseController  {
	@Resource
	private TcityService tcityService;

	public Tcity getEntity4Request(HttpServletRequest request) {
			Tcity entity=new Tcity();
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
			if(StringUtils.isNotBlank(request.getParameter("cityName"))){
			entity.setCityName(request.getParameter("cityName"));
			}
			if(StringUtils.isNotBlank(request.getParameter("cityPinyin"))){
			entity.setCityPinyin(request.getParameter("cityPinyin"));
			}
			if(StringUtils.isNotBlank(request.getParameter("cityThreeCode"))){
			entity.setCityThreeCode(request.getParameter("cityThreeCode"));
			}
			if(StringUtils.isNotBlank(request.getParameter("cityFirstWord"))){
			entity.setCityFirstWord(request.getParameter("cityFirstWord"));
			}
			if(StringUtils.isNotBlank(request.getParameter("parentCity"))){
			entity.setParentCity(Long.valueOf(request.getParameter("parentCity")));
			}
			return entity;
	}
}