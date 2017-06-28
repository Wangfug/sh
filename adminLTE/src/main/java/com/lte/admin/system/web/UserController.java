package com.lte.admin.system.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.lte.admin.custom.entity.TbaseEmployee;
import com.lte.admin.custom.service.TbaseEmployeeService;
import com.lte.admin.entity.*;
import com.lte.admin.other.entity.TbaseCompany;
import com.lte.admin.other.service.TbaseCompanyService;
import com.lte.admin.system.service.DeptService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.system.service.MemberService;
import com.lte.admin.system.service.RyxxService;
import com.lte.admin.system.service.UserRoleService;

/**
 * 用户controller
 * 
 * @author ty
 * @date 2015年1月13日
 */
@Controller
@RequestMapping("system/user")
public class UserController extends BaseController {

	@Autowired
	private RyxxService userService;
	
	@Autowired
	private MemberService memberService;

	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private TbaseEmployeeService tbaseEmployeeService;
	@Autowired
	private TbaseCompanyService tbaseCompanyService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private RyxxService ryxxService;

	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "system/userList";
	}

	/**
	 * 获取用户json
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		MemberLogin memberLogin = (MemberLogin)request.getSession().getAttribute("user");
		Page<MemberLogin> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		if(memberLogin.getJobCode()!=null&&memberLogin.getJobCode().startsWith("DZ")){
			filters.put("comCode",memberLogin.getShopCode());
		}
		PageList<MemberLogin> page1 = memberService.getMemberList(page, filters);
		return getEasyUIData(page1, request);
	}

	/**
	 * 通过用户名查用户
	 */
	@RequestMapping(value = "getUserByLoginName", method = RequestMethod.POST)
	@ResponseBody
	public String getUserByLoginName(String loginName) {
		String result = "false";
		Member m = memberService.getMember(loginName);
		if(m!=null){
			result = "success";
		}
		return result;
	}

	/**
	 * 添加用户跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("sys:user:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {

//		model.addAttribute("user", new Ryxx());
		model.addAttribute("action", "create");
		return "system/userForm";
	}

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @param model
	 */
	@RequiresPermissions("sys:user:add")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid Ryxx user, Model model,String confirmPassword,String email) {
		user.setPassword(confirmPassword);
		userService.save(user);
		String eno = "SH"+tbaseEmployeeService.getEno();
		Member member = memberService.getMember(user.getPsnname());
		if(member!=null){
			TbaseEmployee tbaseEmployee = new TbaseEmployee();
			tbaseEmployee.setCreateBy(user.getPsnname());
			tbaseEmployee.setCreateTime(new Timestamp(System.currentTimeMillis()));
			tbaseEmployee.setState(1);
			tbaseEmployee.setEmail(email);
			tbaseEmployee.setEno(eno);
			tbaseEmployeeService.saveTbaseEmployee(tbaseEmployee);
		}
		return "success";
	}

	/**
	 * 修改用户跳转
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:user:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		// model.addAttribute("user", userService.get(id));
		Ryxx user = userService.getRyxxById(id);
		Map<String,Object> employee = new HashMap<String,Object>();
		if(user!=null){
			TbaseEmployee emp = tbaseEmployeeService.getOneByCreateBy(user.getPsnname());
			if(emp!=null){
				employee.put("psnname",user.getPsnname());
				employee.put("memberName",user.getMemberName());
				employee.put("eno",emp.getEno());
				employee.put("mobile",user.getMobile());
				employee.put("email",emp.getEmail());
				model.addAttribute("id", id);
				model.addAttribute("employee", employee);
			}
		}
		model.addAttribute("action", "update");
		return "system/userForm";
	}

	/**
	 * 修改用户
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:user:update")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@Valid @ModelAttribute @RequestBody Ryxx user, Model model,String email,String eno) {
		userService.update(user);
		Long id =Long.valueOf(user.getId());
		TbaseEmployee emp = tbaseEmployeeService.getOneByCreateBy(user.getPsnname());
		emp.setEmail(email);
		emp.setEno(eno);
		tbaseEmployeeService.updateTbaseEmployee(emp);
		return "success";
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("sys:user:delete")
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") Long id) {
		String result = "false";
		try{
			Member member = memberService.getMember(id);
			TbaseEmployee emp = tbaseEmployeeService.getOneByCreateBy(member.getMemberCode());
			Ryxx ryxx = memberService.member2Ryxx(member);
			List<MemberJob> roles = memberService.getMemberJobs(member.getMemberCode());
			boolean res = userService.delete(member.getId(),emp.getId());
			List<String> deleteList = new ArrayList<String>();
			if(res){
				for(MemberJob job:roles){
					deleteList.add(job.getJobCode());
				}
				userRoleService.delete(ryxx,deleteList);
			}
			result = "success";
		}catch(Exception e){
			result = "false";
		}

		return result;
	}

	/**
	 * 弹窗页-用户拥有的角色
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:user:roleView")
	@RequestMapping(value = "{userId}/userRole")
	public String getUserRole(@PathVariable("userId") String id, Model model) {
//		Map member = memberService.getMemberDetail(Long.valueOf(id));
//		List<Map> companys = tbaseCompanyService.getList();
//		model.addAttribute("coms", companys);
//		List<Dept> depts = deptService.getDeptListByCompany((String)companys.get(0).get("comCode"));
//		model.addAttribute("depts", depts);
//		model.addAttribute("member", member);
		model.addAttribute("userId", id);
		return "system/userRoleList";
	}

	/**
	 * 获取用户拥有的角色ID集合
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("sys:user:roleView")
	@RequestMapping(value = "{id}/role")
	@ResponseBody
	public List<UserRole> getRoleIdList(@PathVariable("id") String id) {
		return userRoleService.findRoleListByUserId(id);
	}

	/**
	 * 根据公司code查部门
	 *
	 * @param comPanyCode
	 * @return
	 */
	@RequestMapping(value = "{comPanyCode}/changeDeptOption")
	@ResponseBody
	public List<Dept> changeDeptOption(@PathVariable("comPanyCode") String comPanyCode) {
		if(StringUtils.isEmpty(comPanyCode))
			comPanyCode = "SH";
		return deptService.getDeptListByCompany(comPanyCode);
	}


	/**
	 * 修改用户拥有的角色
	 * 
	 * @param
	 * @param newRoleList
	 * @return
	 */
	@RequiresPermissions("sys:user:roleUpd")
	@RequestMapping(value = "{id}/updateRole")
	@ResponseBody
	public String updateUserRole(@PathVariable("id") String memberCode, @RequestBody List<String> newRoleList) {
		userRoleService.updateUserRole(memberCode, userRoleService.findRoleListByUserId(memberCode), newRoleList);
		return "success";
	}

	/**
	 * 修改密码跳转
	 */
	@RequestMapping(value = "updatePwd", method = RequestMethod.GET)
	public String updatePwdForm(Model model, HttpSession session, HttpServletRequest req) {
		// System.out.println(RequestContextUtils.getWebApplicationContext(req).getMessage("xdpt.test",new
		// String[]{"啊"}, RequestContextUtils.getLocale(req)));
		model.addAttribute("user", session.getAttribute("user"));
		return "system/updatePwd";
	}

	/**
	 * 修改密码跳转
	 */
	@RequestMapping(value = "updateRyxxPwd/{memberCode}", method = RequestMethod.GET)
	public String updateRyxxPwdForm(@PathVariable("memberCode") String psncode, Model model) {
		Object obj = userService.getRyxx(psncode);
		model.addAttribute("user", userService.getRyxx(psncode));
		return "system/updateRyxxPwd";
	}

	/**
	 * 修改自己密码
	 */
	@RequestMapping(value = "updatePwd", method = RequestMethod.POST)
	@ResponseBody
	public String updatePwd(String oldPassword, String confirmPassword, String psncode, HttpSession session) {
		MemberLogin memberLogin = (MemberLogin) session.getAttribute("user");
		Ryxx user = ryxxService.getRyxx(memberLogin.getMemberCode());
		if (user != null && userService.checkPassword(user, oldPassword)) {
			user.setPassword(confirmPassword);
			userService.updatePwd(user);
			session.setAttribute("user", user);
			return "success";
		} else {
			return "fail";
		}

	}

	/**
	 * 修改密码
	 */
	@RequestMapping(value = "updateRyxxPwd", method = RequestMethod.POST)
	@ResponseBody
	public String updateRyxxPwd(String confirmPassword, String psncode) {
		Ryxx user = userService.getRyxx(psncode);
		if (user != null) {
			user.setPassword(confirmPassword);
			userService.updatePwd(user);
			return "success";
		} else {
			return "fail";
		}

	}

	/**
	 * Ajax请求校验loginName是否唯一。
	 */
	@RequestMapping(value = "checkLoginName")
	@ResponseBody
	public String checkLoginName(String loginName) {
		if (userService.getRyxx(loginName) == null) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * ajax请求校验原密码是否正确
	 * 
	 * @param oldPassword
	 * @param
	 * @return
	 */
	@RequestMapping(value = "checkPwd")
	@ResponseBody
	public String checkPwd(String oldPassword, HttpSession session) {
		MemberLogin memberLogin = (MemberLogin)session.getAttribute("user");
		Ryxx ryxx = ryxxService.getRyxx(memberLogin.getMemberCode());
		if (userService.checkPassword(ryxx , oldPassword)) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * 登录信息
	 * @return
	 */
	public Ryxx memberLogin2Ryxx(MemberLogin memberLogin){
		Ryxx ryxx = new Ryxx();
		ryxx.setMobile(memberLogin.getMobile());
		ryxx.setPsncode(memberLogin.getId()+"");
		ryxx.setMemberName(memberLogin.getMemberName());
		ryxx.setPassword(memberLogin.getPassword());
		ryxx.setDeptcode(memberLogin.getDeptCode());
		ryxx.setSalt(memberLogin.getSalt());
		return ryxx;
}
	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2
	 * Preparable二次部分绑定的效果,先根据form的id从数据库查出Task对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		if (id != -1) {
			// model.addAttribute("user", userService.get(id));
		}
	}

}
