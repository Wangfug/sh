//package com.lte.admin.system.web;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.github.miemiedev.mybatis.paginator.domain.PageList;
//import com.lte.admin.common.consts.ErrorCodeConst;
//import com.lte.admin.common.exception.AdminLteException;
//import com.lte.admin.common.persistence.Page;
//import com.lte.admin.common.persistence.PropertyFilter;
//import com.lte.admin.common.web.BaseController;
//import com.lte.admin.entity.Zdlx;
//import com.lte.admin.entity.Zdxx;
//import com.lte.admin.system.service.ZdlxService;
//import com.lte.admin.system.service.ZdxxService;
//import com.lte.admin.system.utils.UserUtil;
//
///**
// * 字典controller
// * 
// * @author ty
// * @date 2015年1月13日
// */
//@Controller
//@RequestMapping("system/zdxx")
//public class ZdxxController extends BaseController {
//
//	@Autowired
//	private ZdxxService zdxxService;
//
//	@Autowired
//	private ZdlxService zdlxService;
//
//	/**
//	 * 默认页面
//	 */
//	@RequestMapping(method = RequestMethod.GET)
//	public String zdxxlist() {
//		return "system/zdxxlist";
//	}
//
//	/**
//	 * 获取字典json
//	 */
//	@RequestMapping(value = "json", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> dictList(HttpServletRequest request) {
//		Page<Zdxx> page = getPage(request);
//		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
//		PageList<Zdxx> page1 = zdxxService.search(page, filters);
//		for (Zdxx zdxx : page1) {
//			zdxx.setZdlxName(zdlxService.get(zdxx.getZdlxid()).getZdlxmc());
//		}
//		return getEasyUIData(page1, request);
//
//	}
//
//	/**
//	 * 添加字典跳转
//	 * 
//	 * @param model
//	 */
//	@RequestMapping(value = "create", method = RequestMethod.GET)
//	public String createForm(Model model) {
//		model.addAttribute("zdxx", new Zdxx());
//		model.addAttribute("action", "create");
//		List<Zdlx> zdlxBeans = zdlxService.selectFlist();
//		StringBuilder zglsxCombo = new StringBuilder();
//		String ZGLSX_COMBO = "<option value='%s'>%s</option>";
//		for (Zdlx tmp : zdlxBeans) {
//			zglsxCombo.append(String.format(ZGLSX_COMBO, tmp.getZdlxid(), tmp.getZdlxmc()));
//		}
//		model.addAttribute("zdlxComboHtml", zglsxCombo.toString());
//		return "system/zdxxForm";
//	}
//
//	/**
//	 * 添加字典
//	 * 
//	 * @param dict
//	 * @param model
//	 */
//	@RequestMapping(value = "create", method = RequestMethod.POST)
//	@ResponseBody
//	public String create(@Valid Zdxx dict, Model model) {
//		boolean checkflag = false;
//		if (dict != null) {
//			checkflag = checkDup(dict);
//		}
//		if (checkflag) {
//			throw new AdminLteException(ErrorCodeConst.SYSTEM_CODE_REPEAT, "编码重复，请确认");
//		}
//		dict.setCreateId(UserUtil.getCurrentUser().getId());
//		dict.setCreateName(UserUtil.getCurrentUser().getName());
//		dict.setUpdateId(UserUtil.getCurrentUser().getId());
//		dict.setUpdateName(UserUtil.getCurrentUser().getName());
//		dict.setZxbj("0");
//		zdxxService.save(dict);
//		return "success";
//	}
//
//	private boolean checkDup(Zdxx dict) {
//		List<Zdxx> dd = zdxxService.getCheck(dict);
//		if (dd != null && dd.size() >= 1) {
//			return true;
//		}
//		return false;
//	}
//
//	/**
//	 * 修改字典跳转
//	 * 
//	 * @param id
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
//	public String updateForm(@PathVariable("id") Integer id, Model model) {
//		Zdxx olx = zdxxService.get(id);
//		model.addAttribute("zdxx", olx);
//		model.addAttribute("action", "update");
//		List<Zdlx> zdlxBeans = zdlxService.selectFlist();
//		StringBuilder zglsxCombo = new StringBuilder();
//		String ZGLSX_COMBO = "<option value='%s'>%s</option>";
//		String ZGLSX_COMBOs = "<option value='%s' selected>%s</option>";
//		for (Zdlx tmp : zdlxBeans) {
//			if (olx.getZdlxid().intValue() == tmp.getZdlxid().intValue()) {
//				zglsxCombo.append(String.format(ZGLSX_COMBOs, tmp.getZdlxid(), tmp.getZdlxmc()));
//
//			} else {
//				zglsxCombo.append(String.format(ZGLSX_COMBO, tmp.getZdlxid(), tmp.getZdlxmc()));
//
//			}
//		}
//		model.addAttribute("zdlxComboHtml", zglsxCombo.toString());
//		return "system/zdxxForm";
//	}
//
//	/**
//	 * 修改字典
//	 * 
//	 * @param dict
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "update", method = RequestMethod.POST)
//	@ResponseBody
//	public String update(@Valid @ModelAttribute @RequestBody Zdxx dict, Model model) {
//		boolean checkflag = false;
//		if (dict != null) {
//			checkflag = checkDup(dict);
//		}
//		if (checkflag) {
//			throw new AdminLteException(ErrorCodeConst.SYSTEM_CODE_REPEAT, "编码重复，请确认");
//		}
//		dict.setUpdateId(UserUtil.getCurrentUser().getId());
//		dict.setUpdateTime(new Date());
//		dict.setUpdateName(UserUtil.getCurrentUser().getName());
//		zdxxService.update(dict);
//		return "success";
//	}
//
//	/**
//	 * 删除字典
//	 * 
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping(value = "delete/{id}")
//	@ResponseBody
//	public String delete(@PathVariable("id") Integer id) {
//		// zdxxService.delete(id);
//		Zdxx dict = zdxxService.get(id);
//		dict.setZxbj("1");
//		dict.setUpdateId(UserUtil.getCurrentUser().getId());
//		dict.setUpdateTime(new Date());
//		dict.setUpdateName(UserUtil.getCurrentUser().getName());
//		zdxxService.update(dict);
//		return "success";
//	}
//
//	@ModelAttribute
//	public void getDict(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
//		if (id != -1) {
//			// model.addAttribute("dict", dictService.get(id));
//		}
//	}
//
//}
