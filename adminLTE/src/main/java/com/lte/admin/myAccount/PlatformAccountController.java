package com.lte.admin.myAccount;

import javax.validation.Valid;

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
	
	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {

		return VIEW_URL + "home";
	}
}
