
package com.lte.admin.custom.controller;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.custom.service.CustomerCredentialService;
import com.lte.admin.entity.DictType;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.system.service.DictTypeService;
import com.lte.admin.system.utils.UserUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.custom.entity.CustomerCredential;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Andy
 */
@Controller
@RequestMapping("web/customerCredential")
public class CustomerCredentialController extends BaseController  {
	@Resource
	private CustomerCredentialService customerCredentialService;
	@Resource
	private DictTypeService dictTypeService;
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
			if(StringUtils.isNotBlank(request.getParameter("customerId"))){
				entity.setCustomer(Long.valueOf(request.getParameter("customerId")));
			}
			return entity;
	}
	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toCarView(HttpServletRequest request){
		List<DictType> dictForCredential = dictTypeService.getChildrenByParent("CredentialType");
		request.getSession().setAttribute("dictForCredential",dictForCredential);
		return "custom/customerCredential";
	}
	@RequestMapping(value = "saveOrUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveCustomerCredential(HttpServletRequest request) {
		MemberLogin user = UserUtil.getCurrentUser();
		CustomerCredential customerCredential = this.getEntity4Request(request);
		String result = "false";
		if(customerCredential.getId()!=null){
			customerCredential.setCustomer(2l);
			result = customerCredentialService.updateCustomerCredential(customerCredential);
		}else{
			customerCredential.setState(1);
			customerCredential.setCreateBy(user.getId());
			customerCredential.setCustomer(2l);
			customerCredential.setCreateTime(new Timestamp(System.currentTimeMillis()));
			customerCredentialService.saveCustomerCredential(customerCredential);
			result = "success";
		}
		return result;
	}

	@RequestMapping(value = "getList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCarAttachList(HttpServletRequest request) {
		Page<CustomerCredential> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<Map> page1 = customerCredentialService.getList(page,filters);
		return getEasyUIData(page1, request);
	}

	/**
	 * 创建证件
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "create",method = {RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletRequest request,String id){
		CustomerCredential customerCredential = null;
		if(StringUtils.isNotBlank(id))
			customerCredential = customerCredentialService.getOneCustomerCredential(Long.parseLong(id));
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("customerCredential",customerCredential);
		return "custom/customerCredentialForm";
	}

	/**
	 * 根据证件类型获取证件
	 */
	@RequestMapping(value = "getCredentialsByType",method = RequestMethod.POST)
	@ResponseBody
	public List getCredentialsByType(HttpServletRequest request,String type){
		List<CustomerCredential> customerCredentials = new ArrayList<CustomerCredential>();
		List<Map> customerCredentials1 = new ArrayList<Map>();
		if(StringUtils.isNotBlank(type))
			customerCredentials = customerCredentialService.getCredentialsByType(type);
//		boolean flag = false;
		for(CustomerCredential cc:customerCredentials){
			Map map = new HashMap();
			map.put("id",cc.getCredentialType());
			map.put("text",cc.getCredentialCode());
//			if(!flag){
//				map.put("selected","true");
//				flag = true;
//			}
			customerCredentials1.add(map);
		}
		return customerCredentials1;
	}


}