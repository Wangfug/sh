package com.lte.admin.myAccount;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lte.admin.common.web.BaseController;

@Controller
@RequestMapping("myAccount/branchAccount")
public class BranchAccountController extends BaseController {

	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {

		return "myAccount/brAccount/home";

	}
}
