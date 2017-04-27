package com.lte.admin.system.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.entity.Ryxx;
import com.lte.admin.entity.UserRole;
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
		Page<MemberLogin> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<MemberLogin> page1 = memberService.getMemberList(page, filters);
		return getEasyUIData(page1, request);
	}

	/**
	 * 添加用户跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("sys:user:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("user", new Ryxx());
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
	public String create(@Valid Ryxx user, Model model) {
		userService.save(user);
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
	public String update(@Valid @ModelAttribute @RequestBody Ryxx user, Model model) {
		userService.update(user);
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
		userService.delete(id);
		return "success";
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
	 * 修改用户拥有的角色
	 * 
	 * @param id
	 * @param newRoleList
	 * @return
	 */
	@RequiresPermissions("sys:user:roleUpd")
	@RequestMapping(value = "{id}/updateRole")
	@ResponseBody
	public String updateUserRole(@PathVariable("id") String id, @RequestBody List<Long> newRoleList) {
		userRoleService.updateUserRole(id, userRoleService.findRoleListByUserId(id), newRoleList);
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
	@RequestMapping(value = "updateRyxxPwd/{psncode}", method = RequestMethod.GET)
	public String updateRyxxPwdForm(@PathVariable("psncode") String psncode, Model model) {
		model.addAttribute("user", userService.getRyxx(psncode));
		return "system/updateRyxxPwd";
	}

	/**
	 * 修改自己密码
	 */
	@RequestMapping(value = "updatePwd", method = RequestMethod.POST)
	@ResponseBody
	public String updatePwd(String oldPassword, String confirmPassword, String psncode, HttpSession session) {
		Ryxx user = (Ryxx) session.getAttribute("user");
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
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "checkPwd")
	@ResponseBody
	public String checkPwd(String oldPassword, HttpSession session) {
		if (userService.checkPassword((Ryxx) session.getAttribute("user"), oldPassword)) {
			return "true";
		} else {
			return "false";
		}
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
