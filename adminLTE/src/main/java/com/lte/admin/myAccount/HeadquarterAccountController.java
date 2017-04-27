package com.lte.admin.myAccount;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lte.admin.common.web.BaseController;

@Controller
@RequestMapping("myAccount/headquarterAccount")
public class HeadquarterAccountController extends BaseController {

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {

		return "myAccount/hqAccount/home";
	}
}
