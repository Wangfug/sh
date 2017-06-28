
package com.lte.admin.custom.controller;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.custom.service.CustomerDrivingLicenceService;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.system.utils.UserUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.custom.entity.CustomerDrivingLicence;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Map;

/**
 * @author Andy
 */
@Controller
@RequestMapping("web/customerDrivingLicence")
public class CustomerDrivingLicenceController extends BaseController  {
	@Resource
	private CustomerDrivingLicenceService customerDrivingLicenceService;

	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toCarView(HttpServletRequest request){
		return "custom/customerDrivingLicence";
	}
	@RequestMapping(value = "saveOrUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveCustomerDrivingLicence(HttpServletRequest request) {
		MemberLogin user = UserUtil.getCurrentUser();
		CustomerDrivingLicence customerDrivingLicence = this.getEntity4Request(request);
		String result = "false";
		if(customerDrivingLicence.getId()!=null){
			customerDrivingLicence.setCreateTime(new Timestamp(System.currentTimeMillis()));
			customerDrivingLicence.setLastBy(user.getId());
			result = customerDrivingLicenceService.updateCustomerDrivingLicence(customerDrivingLicence);

		}else{
			customerDrivingLicence.setState(1);
			customerDrivingLicence.setCreateBy(user.getId());
			customerDrivingLicence.setCreateTime(new Timestamp(System.currentTimeMillis()));
			customerDrivingLicence.setLastBy(user.getId());
			customerDrivingLicence.setLastTime(new Timestamp(System.currentTimeMillis()));
			customerDrivingLicenceService.saveCustomerDrivingLicence(customerDrivingLicence);
			result = "success";
		}
		return result;
	}

	@RequestMapping(value = "getList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCarAttachList(HttpServletRequest request) {
		Page<CustomerDrivingLicence> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<CustomerDrivingLicence> page1 = customerDrivingLicenceService.getList(page,filters);
		return getEasyUIData(page1, request);
	}

	/**
	 * 创建驾驶证
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "create",method = {RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletRequest request,String id){
		CustomerDrivingLicence customerDrivingLicence = null;
		if(StringUtils.isNotBlank(id))
			customerDrivingLicence = customerDrivingLicenceService.getOneCustomerDrivingLicence(Long.parseLong(id));
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("customerDrivingLicence",customerDrivingLicence);
		return "custom/customerDrivingLicenceForm";
	}

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