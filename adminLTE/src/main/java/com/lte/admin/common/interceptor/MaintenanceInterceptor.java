package com.lte.admin.common.interceptor;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lte.admin.entity.Ryxx;
import com.lte.admin.system.utils.UserUtil;

/**
 * 日志拦截器
 * 
 * @author ty
 * @date 2015年1月14日
 */
public class MaintenanceInterceptor implements HandlerInterceptor {

	private int shour; // 小时
	private int sminute; // 分钟
	private int ehour; // 小时
	private int eminute; // 分钟
	private String maintenancePage;// 页面的URL

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}


	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	public int getShour() {
		return shour;
	}

	public void setShour(int shour) {
		this.shour = shour;
	}

	public int getSminute() {
		return sminute;
	}

	public void setSminute(int sminute) {
		this.sminute = sminute;
	}

	public int getEhour() {
		return ehour;
	}

	public void setEhour(int ehour) {
		this.ehour = ehour;
	}

	public int getEminute() {
		return eminute;
	}

	public void setEminute(int eminute) {
		this.eminute = eminute;
	}

	public String getMaintenancePage() {
		return maintenancePage;
	}

	public void setMaintenancePage(String maintenancePage) {
		this.maintenancePage = maintenancePage;
	}

}
