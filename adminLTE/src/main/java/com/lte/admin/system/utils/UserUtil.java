package com.lte.admin.system.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.lte.admin.entity.MemberLogin;
import com.lte.admin.system.service.MemberRealm.ShiroMember;

public class UserUtil {

	/**
	 * 获取当前用户对象shiro
	 * 
	 * @return shirouser
	 */
	public static ShiroMember getCurrentShiroUser() {
		ShiroMember user = (ShiroMember) SecurityUtils.getSubject().getPrincipal();
		return user;
	}

	/**
	 * 获取当前用户session
	 * 
	 * @return session
	 */
	public static Session getSession() {
		Session session = SecurityUtils.getSubject().getSession();
		return session;
	}

	/**
	 * 获取当前用户httpsession
	 * 
	 * @return httpsession
	 */
	public static Session getHttpSession() {
		Session session = SecurityUtils.getSubject().getSession();
		return session;
	}

	/**
	 * 获取当前用户对象
	 * 
	 * @return user
	 */
	public static MemberLogin getCurrentUser() {
		Session session = SecurityUtils.getSubject().getSession();
		if (null != session) {
			if ((MemberLogin) session.getAttribute("user") != null) {
				return (MemberLogin) session.getAttribute("user");
			} else {
				return null;
			}
		} else {
			return null;
		}

	}
}
