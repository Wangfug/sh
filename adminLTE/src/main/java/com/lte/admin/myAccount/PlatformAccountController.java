package com.lte.admin.myAccount;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import cn.jiguang.common.utils.StringUtils;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.order.service.OrderWorkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lte.admin.common.web.BaseController;
import com.lte.admin.system.utils.UserUtil;

@Controller
@RequestMapping("myAccount/platformAccount")
public class PlatformAccountController extends BaseController {

	/**
	 * 视图页面共用路径URL
	 */
	private final static String VIEW_URL = "myAccount/ptAccount/";
	@Resource
	private OrderWorkService OrderWorkService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		MemberLogin user = (MemberLogin)request.getSession().getAttribute("user");
		StringBuilder content = new StringBuilder();
		String jobCode = user.getJobCode();
		String type = "admin";
		try{
			if(StringUtils.isNotEmpty(jobCode)){
				if(jobCode.startsWith("DZ")){
					type = "DZ";
					Long notAssign = OrderWorkService.getNotAssign(user.getShopCode());
					model.addAttribute("notAssign",notAssign);
				}
				if(jobCode.startsWith("KF")){
					type = "KF";
					content.append("我是客服，么么哒！");
				}
				if(jobCode.startsWith("MG")){
					type = "MG";
					content.append("我是经理，呵呵呵！");
				}
				if(jobCode.startsWith("CW")){
					type = "CW";
					content.append("我是财务，萌萌哒！");
				}
			}
			model.addAttribute("type",type);
			model.addAttribute("content",content);
		}catch(Exception e){
			return null;
		}

//		model.addAttribute("content","<div>您有新的订单了<a></a></div>");
		return VIEW_URL + "home";
	}
}
