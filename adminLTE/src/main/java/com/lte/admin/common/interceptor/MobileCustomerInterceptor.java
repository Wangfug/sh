package com.lte.admin.common.interceptor;
import com.lte.admin.custom.entity.Customer;
import com.lte.admin.custom.service.CustomerService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class MobileCustomerInterceptor implements HandlerInterceptor  {

	private final String ADMINSESSION = "token";
	@Resource
	private CustomerService customerService;
	//拦截钱处理
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		Object token = request.getSession().getAttribute(ADMINSESSION);
		String getToken = request.getParameter("token");
		if(null != getToken && getToken!="") {
//			if(token != null && token.toString() !="" && token.toString().equals(getToken)){
//				return true;
//			}else{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("password",getToken);
				Customer customer = customerService.getOneCustomerByMobile(map);
				if(null != customer && customer.getPassword().equals(getToken)){
					request.getSession().setAttribute("token",customer.getPassword());
					return true;
				}else{
					System.out.println("-customer--getToken is not in mysql---");
					return false;
//				}
			}
		}else{
			System.out.println("--customer-getToken==null---");
			return false;
		}
	}

	//拦截后处理
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav) throws Exception {
	}
	//全部完成后处理
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e) throws Exception {
	}
}