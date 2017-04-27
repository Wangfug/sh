
package com.lte.admin.other.controller;
import com.lte.admin.other.service.ExceptionThrowService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.other.entity.ExceptionThrow;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class ExceptionThrowController extends BaseController  {
	@Resource
	private ExceptionThrowService exceptionThrowService;

	public ExceptionThrow getEntity4Request(HttpServletRequest request) {
			ExceptionThrow entity=new ExceptionThrow();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createBy"))){
			entity.setCreateBy(Long.valueOf(request.getParameter("createBy")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createTime"))){
			entity.setCreateTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("createTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("exceptionOrder"))){
			entity.setExceptionOrder(Long.valueOf(request.getParameter("exceptionOrder")));
			}
			if(StringUtils.isNotBlank(request.getParameter("handleBy"))){
			entity.setHandleBy(request.getParameter("handleBy"));
			}
			if(StringUtils.isNotBlank(request.getParameter("isHandle"))){
			entity.setIsHandle(request.getParameter("isHandle"));
			}
			return entity;
	}
}