
package com.lte.admin.custom.controller;
import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.service.CarShopsService;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.custom.entity.CustomerCredential;
import com.lte.admin.custom.service.CustomerCredentialService;
import com.lte.admin.custom.service.TbaseEmployeeService;
import com.lte.admin.entity.*;
import com.lte.admin.system.service.*;
import com.lte.admin.system.utils.UserUtil;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.custom.entity.TbaseEmployee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author Andy
 */
@Controller
@RequestMapping("web/tbaseEmployee")
public class TbaseEmployeeController extends BaseController  {
	@Resource
	private TbaseEmployeeService tbaseEmployeeService;
	@Resource
	private DictTypeService dictTypeService;
	@Resource
	private CarShopsService carShopsService;
	@Autowired
	private RyxxService userService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private CustomerCredentialService customerCredentialService;
	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toCarView(HttpServletRequest request){
		List<DictType> jobTypesForEmployee = dictTypeService.getChildrenByParent("JobType");
		request.getSession().setAttribute("jobTypesForEmployee",jobTypesForEmployee);
		List<Map> carShopsForEmployee = carShopsService.getList1();
		request.getSession().setAttribute("carShopsForEmployee",carShopsForEmployee);
		return "custom/tbaseEmployee";
	}
	/*@RequestMapping(value = "saveOrUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveTbaseEmployee(HttpServletRequest request,@Valid Ryxx user,
									Model model, String confirmPassword, String email, String eno) {
		MemberLogin memberLogin = (MemberLogin)request.getSession().getAttribute("user");
		String result = "false";
		user.setPassword(confirmPassword);
		boolean flag = false;
		if(StringUtils.isNotEmpty(user.getId())){
			flag = true;
			userService.update(user);
		}else{
			userService.save(user);
		}
		Member member = memberService.getMember(user.getPsnname());
		String[] jobCodes = request.getParameterValues("jobCode");
		if(member!=null){
			if(flag){
				TbaseEmployee tbaseEmployee = tbaseEmployeeService.getOneByCreateBy(member.getId());
				tbaseEmployee.setEmail(email);
				tbaseEmployee.setEno(eno);
				tbaseEmployeeService.updateTbaseEmployee(tbaseEmployee);
				List<MemberJob> roles = memberService.getMemberJobs(member.getMemberCode());
				List<String> newList1 = new ArrayList<String>();
				List<String> deleteList = new ArrayList<String>();
				List<String> newList = new ArrayList<String>();
				if(!ArrayUtils.isEmpty(jobCodes)){
					List<String> newList2 = Arrays.asList(jobCodes);
					for(MemberJob job:roles){
						if(!newList2.contains(job.getJobCode())){
							deleteList.add(job.getJobCode());
						}
						newList1.add(job.getJobCode());
					}
					for(String str:newList2){
						if(!newList1.contains(str)){
							newList.add(str);
						}
					}
				}
				for(String deleteOne:deleteList){
					userRoleService.delete(user.getPsnname(),deleteOne);
				}
				for(String addOne:newList){
					userRoleService.save(user.getPsnname(),addOne);
				}
			}else{
				TbaseEmployee tbaseEmployee = new TbaseEmployee();
				tbaseEmployee.setCreateBy(Long.valueOf(user.getId()));
				tbaseEmployee.setCreateTime(new Timestamp(System.currentTimeMillis()));
				tbaseEmployee.setState(1);
				tbaseEmployee.setEmail(email);
				tbaseEmployee.setEno(eno);
				tbaseEmployeeService.saveTbaseEmployee(tbaseEmployee);
				List<String> newList = Arrays.asList(jobCodes);
				for(String addOne:newList){
					userRoleService.save(user.getPsnname(),addOne);
				}
			}
			result = "success";
		}
		return result;
	}
*/

	@RequestMapping(value = "saveOrUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveTbaseEmployee(HttpServletRequest request,@Valid Ryxx user,
									Model model, String confirmPassword, String email,String oldCode) {
		MemberLogin memberLogin = (MemberLogin)request.getSession().getAttribute("user");
		String result = "false";
		user.setPassword(confirmPassword);
//		String type = request.getParameter("certificateType");
		String code = request.getParameter("certificateCode");
		String[] jobCodes = request.getParameterValues("jobCode");
		String eno = "SH"+tbaseEmployeeService.getEno();
		if(StringUtils.isNotEmpty(user.getId())){
			//修改员工表
			TbaseEmployee tbaseEmployee = tbaseEmployeeService.getOneByCreateBy(oldCode);
			CustomerCredential customerCredential = null;
			if(tbaseEmployee.getCredential()!=null){
				customerCredential =customerCredentialService.getOneCustomerCredential(tbaseEmployee.getCredential());
				if(StringUtils.isNotBlank(code)){
					customerCredential.setCredentialCode(code);
					customerCredentialService.updateCustomerCredential(customerCredential);
				}
				tbaseEmployee.setCredential(customerCredential.getId());
			}else{
				customerCredential = new CustomerCredential();
				customerCredential.setCredentialType("60001");
				customerCredential.setCredentialCode(code);
				customerCredential.setCreateTime(new Timestamp(System.currentTimeMillis()));
				customerCredentialService.saveCustomerCredential(customerCredential);
				tbaseEmployee.setCredential(customerCredential.getCredentialId());
			}
			tbaseEmployee.setCreateBy(user.getPsnname());
			tbaseEmployee.setEmail(email);
			tbaseEmployee.setEno(eno);
//			tbaseEmployeeService.updateTbaseEmployee(tbaseEmployee);
			List<MemberJob> roles = memberService.getMemberJobs(user.getPsnname());
			List<String> newList1 = new ArrayList<String>();
			List<String> deleteList = new ArrayList<String>();
			List<String> newList = new ArrayList<String>();
			if(!ArrayUtils.isEmpty(jobCodes)){
				List<String> newList2 = Arrays.asList(jobCodes);
				for(MemberJob job:roles){
					if(!newList2.contains(job.getJobCode())){
						deleteList.add(job.getJobCode());
					}
					newList1.add(job.getJobCode());
				}
				for(String str:newList2){
					if(!newList1.contains(str)){
						newList.add(str);
					}
				}
			}else{
				for(MemberJob job:roles){
						deleteList.add(job.getJobCode());
				}
			}
			boolean res = userService.update(user,tbaseEmployee);
			if(res){
					userRoleService.delete(user,deleteList);
					userRoleService.save(user,newList);
			}
		}else{

			CustomerCredential customerCredential = null;
			customerCredential = new CustomerCredential();
			customerCredential.setCredentialType("60001");
			customerCredential.setCredentialCode(code);
			customerCredential.setCreateTime(new Timestamp(System.currentTimeMillis()));
			customerCredentialService.saveCustomerCredential(customerCredential);

			TbaseEmployee tbaseEmployee = new TbaseEmployee();
			tbaseEmployee.setCreateBy(user.getPsnname());
			tbaseEmployee.setCreateTime(new Timestamp(System.currentTimeMillis()));
			tbaseEmployee.setState(1);
			tbaseEmployee.setEmail(email);
			tbaseEmployee.setEno(eno);
			tbaseEmployee.setCredential(customerCredential.getCredentialId());
//			tbaseEmployeeService.saveTbaseEmployee(tbaseEmployee);
			List<String> newList = Arrays.asList(jobCodes);
			boolean res = userService.save(user,tbaseEmployee);
			if(res){
				userRoleService.save(user,newList);
			}
		}
			result = "success";
		return result;
	}
	/**
	 * 创建或修改员工
	 * @param request
	 * @return
	 */
	@RequiresPermissions("sys:emp:create")
	@RequestMapping(value = "create",method = {RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletRequest request,String id){
		MemberLogin memberLogin = (MemberLogin)request.getSession().getAttribute("user");
		if(memberLogin.getJobCode()!=null&&memberLogin.getJobCode().startsWith("DZ")){
			List<Map<String,Object>> StaffJobs = roleService.getStaffJobListBySP(memberLogin.getShopCode());
			request.setAttribute("StaffJobs",StaffJobs);
		}else{
			List<Map<String,Object>> StaffJobs = roleService.getStaffJobList();
			request.setAttribute("StaffJobs",StaffJobs);
		}
		Map<String,Object> tbaseEmployee = null;
		if(StringUtils.isNotBlank(id)){
			tbaseEmployee = tbaseEmployeeService.getEmployeeInfo(Long.parseLong(id));
			request.setAttribute("type","update");
			if(tbaseEmployee!=null&&tbaseEmployee.get("psnname")!=null){
				String code = (String)tbaseEmployee.get("psnname");
				List<MemberJob> roles = memberService.getMemberJobs(code);
				request.setAttribute("roles", JSON.toJSONString(roles));
			}
		}
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("employee",tbaseEmployee);
		request.setAttribute("id",id);
		return "custom/tbaseEmployeeForm";
	}

	/**
	 * 添加店员
	 * @param request
	 * @return
	 */

	/*@RequiresPermissions("sys:emp:edit")
	@RequestMapping(value = "json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Page<MemberLogin> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<MemberLogin> page1 = memberService.getMemberList(page, filters);
		return getEasyUIData(page1, request);
	}*/

	public TbaseEmployee getEntity4Request(HttpServletRequest request) {
			TbaseEmployee entity=new TbaseEmployee();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createBy"))){
			entity.setCreateBy(request.getParameter("createBy"));
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
			if(StringUtils.isNotBlank(request.getParameter("eno"))){
			entity.setEno(request.getParameter("eno"));
			}
			if(StringUtils.isNotBlank(request.getParameter("attachment"))){
			entity.setAttachment(request.getParameter("attachment"));
			}
			return entity;
	}
}