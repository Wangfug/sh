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
//import com.lte.admin.system.service.ZdlxService;
//import com.lte.admin.system.utils.UserUtil;
//
///**
// * 字典controller
// * 
// * @author ty
// * @date 2015年1月13日
// */
//@Controller
//@RequestMapping("system/zdlx")
//public class ZdlxController extends BaseController {
//
//	@Autowired
//	private ZdlxService zdlxService;
//
//	/**
//	 * 默认页面
//	 */
//	@RequestMapping(method = RequestMethod.GET)
//	public String zdlxlist() {
//		return "system/zdlxlist";
//	}
//
//	/**
//	 * 获取字典json
//	 */
//	@RequestMapping(value = "json", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> dictList(HttpServletRequest request) {
//		Page<Zdlx> page = getPage(request);
//		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
//		PageList<Zdlx> page1 = zdlxService.getDzlxList(page, filters);
//		for (Zdlx zdlx : page1) {
//			if (zdlx.getParentLx().intValue() == 0) {
//				zdlx.setParentLxName("根节点");
//			} else {
//				zdlx.setParentLxName(zdlxService.get(zdlx.getParentLx()).getZdlxmc());
//			}
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
//		model.addAttribute("zdlx", new Zdlx());
//		model.addAttribute("action", "create");
//		List<Zdlx> zdlxBeans = zdlxService.selectPlist();
//		StringBuilder zglsxCombo = new StringBuilder("<option value='0'>根节点</option>");
//		String ZGLSX_COMBO = "<option value='%s'>%s</option>";
//		for (Zdlx tmp : zdlxBeans) {
//			zglsxCombo.append(String.format(ZGLSX_COMBO, tmp.getZdlxid(), tmp.getZdlxmc()));
//		}
//		model.addAttribute("zdlxComboHtml", zglsxCombo.toString());
//		return "system/zdlxForm";
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
//	public String create(@Valid Zdlx dict, Model model) {
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
//		zdlxService.save(dict);
//		return "success";
//	}
//
//	private boolean checkDup(Zdlx dict) {
//		List<Zdlx> dd = zdlxService.getCheck(dict);
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
//		Zdlx olx = zdlxService.get(id);
//		model.addAttribute("zdlx", olx);
//		model.addAttribute("action", "update");
//		List<Zdlx> zdlxBeans = zdlxService.selectPlist();
//		StringBuilder zglsxCombo = new StringBuilder("<option value='0'>根节点</option>");
//		String ZGLSX_COMBO = "<option value='%s'>%s</option>";
//		String ZGLSX_COMBOs = "<option value='%s' selected>%s</option>";
//		for (Zdlx tmp : zdlxBeans) {
//			if (olx.getZdlxid().intValue() == tmp.getZdlxid().intValue()) {
//				continue;
//			}
//			if (olx.getParentLx().intValue() == tmp.getZdlxid().intValue()) {
//				zglsxCombo.append(String.format(ZGLSX_COMBOs, tmp.getZdlxid(), tmp.getZdlxmc()));
//			} else {
//				zglsxCombo.append(String.format(ZGLSX_COMBO, tmp.getZdlxid(), tmp.getZdlxmc()));
//
//			}
//		}
//		model.addAttribute("zdlxComboHtml", zglsxCombo.toString());
//		return "system/zdlxForm";
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
//	public String update(@Valid @ModelAttribute @RequestBody Zdlx dict, Model model) {
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
//		zdlxService.update(dict);
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
//		// zdlxService.delete(id);
//		Zdlx dict = zdlxService.get(id);
//		dict.setZxbj("1");
//		dict.setUpdateId(UserUtil.getCurrentUser().getId());
//		dict.setUpdateTime(new Date());
//		dict.setUpdateName(UserUtil.getCurrentUser().getName());
//		zdlxService.update(dict);
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
