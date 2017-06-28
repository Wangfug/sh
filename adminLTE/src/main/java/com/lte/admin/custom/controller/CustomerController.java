
package com.lte.admin.custom.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.entity.Car;
import com.lte.admin.car.entity.CarAttach;
import com.lte.admin.car.entity.CarAttachApply;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.custom.entity.Customer;
import com.lte.admin.custom.entity.CustomerCredential;
import com.lte.admin.custom.service.CustomerCredentialService;
import com.lte.admin.custom.service.CustomerDrivingLicenceService;
import com.lte.admin.custom.service.CustomerService;
import com.lte.admin.entity.DictType;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.mobile.common.PushExample;
import com.lte.admin.system.service.DictTypeService;
import com.lte.admin.system.utils.UserUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author Andy
 */
@Controller
@RequestMapping("web/customer")
public class CustomerController extends BaseController  {
	@Resource
	private CustomerService customerService;
	@Resource
	private CustomerDrivingLicenceService customerDrivingLicenceService;
	@Resource
	private CustomerCredentialService customerCredentialService;
	@Resource
	private DictTypeService dictTypeService;

	public Customer getEntity4Request(HttpServletRequest request) {
			Customer entity=new Customer();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createBy"))){
			entity.setCreateBy(Long.valueOf(request.getParameter("createBy")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createTime"))){
			entity.setCreateTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("createTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("state"))){//状态：0-默认，1-等待审核，2-认证已通过，3-认证未通过，4-黑名单
			entity.setState(Integer.valueOf(request.getParameter("state")));
			}
			if(StringUtils.isNotBlank(request.getParameter("lastTime"))){
			entity.setLastTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("lastTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("lastBy"))){
			entity.setLastBy(Long.valueOf(request.getParameter("lastBy")));
			}
			if(StringUtils.isNotBlank(request.getParameter("name"))){
			entity.setName(request.getParameter("name"));
			}
			if(StringUtils.isNotBlank(request.getParameter("mobilePhone"))){
			entity.setMobilePhone(request.getParameter("mobilePhone"));
			}
			if(StringUtils.isNotBlank(request.getParameter("password"))){
			entity.setPassword(request.getParameter("password"));
			}
			if(StringUtils.isNotBlank(request.getParameter("email"))){
			entity.setEmail(request.getParameter("email"));
			}
			if(StringUtils.isNotBlank(request.getParameter("attachmenrt"))){
			entity.setAttachmenrt(request.getParameter("attachmenrt"));
			}
			if(StringUtils.isNotBlank(request.getParameter("balance"))){
			entity.setBalance(Double.valueOf(request.getParameter("balance")));
			}
			if(StringUtils.isNotBlank(request.getParameter("identityCard"))){
			entity.setIdentityCard(Long.valueOf(request.getParameter("identityCard")));
			}
			if(StringUtils.isNotBlank(request.getParameter("drivingLicence"))){
			entity.setDrivingLicence(Long.valueOf(request.getParameter("drivingLicence")));
			}
			if(StringUtils.isNotBlank(request.getParameter("otherCard"))){
			entity.setOtherCard(request.getParameter("otherCard"));
			}
			if(StringUtils.isNotBlank(request.getParameter("integral"))){
			entity.setIntegral(Long.valueOf(request.getParameter("integral")));
			}
			if(StringUtils.isNotBlank(request.getParameter("img"))){
				entity.setImg(request.getParameter("img"));
			}

		if(StringUtils.isNotBlank(request.getParameter("authReason"))){
			entity.setAuthReason(request.getParameter("authReason"));
		}

			return entity;
	}

	/**
	 * 用户列表默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(HttpServletRequest request) {
		List<Map> drivingLicencesForCustomer = customerDrivingLicenceService.getList();
		request.getSession().setAttribute("drivingLicencesForCustomer",drivingLicencesForCustomer);
		List<Map> credentialtype = customerCredentialService.getList1();
		request.getSession().setAttribute("Credentials",credentialtype);
		List<DictType> dictTypesForcredentialtype = dictTypeService.getChildrenByParent("CredentialType");
		request.getSession().setAttribute("dictTypesForcredentialtype",dictTypesForcredentialtype);

		return "custom/customerList";
	}

	@RequestMapping(value = "getList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public  Map<String, Object>  getCustomerList(HttpServletRequest request) {
		Page<Customer> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<Map> page1 = customerService.getList1(page, filters);
		return getEasyUIData(page1, request);
	}

	@RequestMapping(value = "saveOrUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveCustomer(HttpServletRequest request,String credentialtype,String credentialcode) {
		MemberLogin user = UserUtil.getCurrentUser();
		Customer customer = this.getEntity4Request(request);
		long id = 0l;
		CustomerCredential credential = customerCredentialService.getByCode(credentialcode);
		if(credential==null){
			credential = new CustomerCredential();
			credential.setCreateBy(user.getId());
			credential.setCreateTime(new Timestamp(System.currentTimeMillis()));
			credential.setState(1);
			credential.setCredentialType(credentialtype);
			credential.setCredentialCode(credentialcode);
			customerCredentialService.saveCustomerCredential(credential);
		}
		String result = "false";
		if(customer.getId()!=null){
			customer.setLastTime(new Timestamp(System.currentTimeMillis()));
			customer.setLastBy(user.getId());
			result = customerService.updateCustomer(customer);
		}else{
			customer.setState(0);
			customer.setCreateBy(user.getId());
			customer.setCreateTime(new Timestamp(System.currentTimeMillis()));
			customer.setLastBy(user.getId());
			customer.setLastTime(new Timestamp(System.currentTimeMillis()));
			result = customerService.saveCustomer(customer);
		}
		return result;
	}
	/**
	 * 创建门店
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "create",method = {RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletRequest request,String id){
		Map customer = null;
		if(StringUtils.isNotBlank(id))
			customer = customerService.getOneCustomer1(Long.parseLong(id));
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("customer",customer);
		return "custom/customerForm";
	}

	/**
	 * （审核）用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "authCustomer",method = {RequestMethod.GET,RequestMethod.POST})
	public String authCustomer(HttpServletRequest request,String id){
		Map<String, Object> customer = null;
		if(StringUtils.isNotBlank(id)){
			customer = customerService.getOneCustomerAuth(Long.parseLong(id));
		}
		request.setAttribute("action","saveOrUpdate");
		String img = customer.get("img").toString();
		JSONObject allImg = JSONObject.fromObject(img);
		request.setAttribute("customer",customer);
		request.setAttribute("allImg",allImg);
		return "custom/customerAuthForm";
	}
	/**
	 * 用户信息审核结果
	 */
	@RequestMapping(value = "auth", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String auth(Long id,String type,String reason){
		String returnMsg = "false";
		Customer customer = customerService.getOneCustomer(id);
		try{
			if("2".equals(type)){//2-通过，3-不通过，4-加入黑名单
				customer.setState(2);//2通过
			}else if("3".equals(type)){
				customer.setState(3);
				customer.setAuthReason(reason);
			}else if("4".equals(type)){
				customer.setState(4);
			}
			returnMsg = customerService.updateCustomer(customer);
			if(returnMsg == "success"){
				PushExample.sendAppMsg(customer.getPassword(),"您的认证已通过！");
			}else{
				PushExample.sendAppMsg(customer.getPassword(),"您的认证未通过，原因如下："+reason);
			}
		}catch(Exception e){
			returnMsg = "false";
		}
		return returnMsg;
	}

}