package com.lte.admin.system.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lte.admin.common.consts.DictConsts;
import com.lte.admin.common.service.DictionaryService;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.entity.DictBean;
import com.lte.admin.entity.Tzgg;
import com.lte.admin.system.service.TzggService;
import com.lte.admin.system.utils.UserUtil;

@Controller
@RequestMapping("system/home")
public class HomeController extends BaseController {

	@Autowired
	private TzggService tzggService;

	@Autowired
	protected DictionaryService dictionary;

	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {
//		String gsdm = UserUtil.getCurrentUser().getCompanyCode();
		String gsdm = UserUtil.getCurrentUser().getDeptCode();
		String userBz = UserUtil.getCurrentUser().getUserBz();
		String accountInfoUrl = getAccountInfoUrl(userBz);
		model.addAttribute("userBz", userBz);
		model.addAttribute("accountInfoUrl", accountInfoUrl);

		return "system/index";
	}

	@RequestMapping(value = "indexmain", method = RequestMethod.GET)
	public String homemain(Model model) {
		String userBz = UserUtil.getCurrentUser().getUserBz();
		String accountInfoUrl = getAccountInfoUrl(userBz);
		model.addAttribute("userBz", userBz);
		model.addAttribute("accountInfoUrl", accountInfoUrl);

//		// 取得通知公告的内容
//		List<DictBean> tzggLxBeans = dictionary.getZdxx(DictConsts.ZDLX_TZGGLX);
//		model.addAttribute("tzggLxBeans", tzggLxBeans);
//
//		List<Tzgg> tzggList = tzggService.getAllTzggList();
//		model.addAttribute("tzggList", tzggList);

		return "system/indexmain";
	}

	/**
	 * 根据用户级别 不同，设置相应的我的账户页面链接
	 * 
	 * @param userBz
	 *            用户级别标志
	 * @return 相应的 我的账户页面链接
	 */
	private String getAccountInfoUrl(String userBz) {
		String accountInfoUrl = "myAccount/platformAccount";
//		if (userBz.equals(DictConsts.GSBZ_FB)) {
//			accountInfoUrl = "myAccount/branchAccount";
//		} else if (userBz.equals(DictConsts.GSBZ_ZB)) {
//			accountInfoUrl = "myAccount/headquarterAccount";
//		}
		return accountInfoUrl;
	}
}
