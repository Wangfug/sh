package com.lte.admin.system.web;

import java.util.ArrayList;
import java.util.List;

import com.lte.admin.common.response.ServiceResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lte.admin.common.utils.Global;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.system.service.MemberRealm.ShiroMember;
import com.lte.admin.system.service.MemberService;
import com.lte.admin.system.utils.UserUtil;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录controller
 * 
 * @author ty
 * @date 2015年1月14日
 */
@Controller
@RequestMapping(value = "{adminPath}")
public class LoginController {

	@Autowired
	public MemberService memberService;

	@Autowired
	private HomeController home;

	/**
	 * 默认页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated() || subject.isRemembered()) {
			return "redirect:" + Global.getAdminPath();
		}
		return "system/login";
	}

	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		ShiroMember su = UserUtil.getCurrentShiroUser();
		Session session = SecurityUtils.getSubject().getSession();
		if ( !(session.getAttribute("user") instanceof MemberLogin)
				|| su.getMemberCode() == null 
				|| su.getMemberCode().equals("")  
				|| (session.getAttribute("user") == null)) {
			List<MemberLogin> list = memberService.getRygwList(su.getMemberCode());
			if (list.size() == 1 && list.get(0) != null) {
				MemberLogin memberLogin = list.get(0);
				
				su.setDeptCode(memberLogin.getDeptCode());
				su.setJobCode(memberLogin.getJobCode());
				su.setCompanyCode(memberLogin.getShopCode());
				session.setAttribute("user", memberLogin);
				return "redirect:" + Global.getAdminPath();
			}

			model.addAttribute("userlist", list);
			return "system/usersel";
		} else {
			
			return home.home(model);
		}
	}

	/**
	 * 默认页面
	 */
	@RequestMapping(value = "seluser", method = RequestMethod.POST)
	public String seluser(@RequestParam(value = "gwxx", defaultValue = "") String gwxx, Model model) {
		if (gwxx.equals("")) {
			return "system/usersel";
		} else {
			ShiroMember su = UserUtil.getCurrentShiroUser();
			MemberLogin memberLogin =
					memberService.getMemberByMemberCodeAndJobCode(su.getMemberCode(), gwxx);
//			MemberLogin memberLogin =
//					memberService.getMemberByMemberCodeAndJobCode(su.getMemberCode(), su.getJobCode());
//			su.setMemberCode(memberLogin.getMemberCode());
//			su.setDeptCode(memberLogin.getDeptCode());
//			su.setJobCode(memberLogin.getJobCode());
//			su.setCompanyCode(memberLogin.getCompanyCode());
			Session session = SecurityUtils.getSubject().getSession();
			session.setAttribute("user", memberLogin);
			String url = "redirect:" + Global.getAdminPath();
			return "redirect:" + Global.getAdminPath();
		}

	}

	/**
	 * 登录失败
	 * 
	 * @param userName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		return "system/login";
	}

	/**
	 * 登出
	 *
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "logout")
	public String logout(Model model) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "system/login";
	}
}
