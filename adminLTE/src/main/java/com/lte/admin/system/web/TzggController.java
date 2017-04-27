//package com.lte.admin.system.web;
//
//import java.nio.charset.Charset;
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
//import com.lte.admin.common.consts.DictConsts;
//import com.lte.admin.common.persistence.Page;
//import com.lte.admin.common.persistence.PropertyFilter;
//import com.lte.admin.common.service.DictionaryService;
//import com.lte.admin.common.web.BaseController;
//import com.lte.admin.entity.DictBean;
//import com.lte.admin.entity.Tzgg;
//import com.lte.admin.system.service.TzggService;
//import com.lte.admin.system.utils.UserUtil;
//
///**
// * 通知公告controller
// * 
// * @author ty
// * @date 2015年1月13日
// */
//@Controller
//@RequestMapping("system/tzgg")
//public class TzggController extends BaseController {
//
//	@Autowired
//	private TzggService tzggService;
//
//	@Autowired
//	protected DictionaryService dictionary;
//
//	/**
//	 * 默认页面
//	 */
//	@RequestMapping(method = RequestMethod.GET)
//	public String tzgglist(Model model) {
//		List<DictBean> dictBeans = dictionary.getZdxx(DictConsts.ZDLX_TZGGLX);
//		StringBuilder zglsxCombo = new StringBuilder("<option value=''>全部</option>");
//		String ZGLSX_COMBO = "<option value='%s'>%s</option>";
//		for (DictBean tmp : dictBeans) {
//			zglsxCombo.append(String.format(ZGLSX_COMBO, tmp.getValue(), tmp.getName()));
//		}
//		model.addAttribute("lxComboHtml", zglsxCombo.toString());
//		return "system/tzgglist";
//	}
//
//	/**
//	 * 获取字典json
//	 */
//	@RequestMapping(value = "json", method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> dictList(HttpServletRequest request) {
//		Page<Tzgg> page = getPage(request);
//		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
//		filters.put("scbz", "0");
//		PageList<Tzgg> page1 = tzggService.getList(page, filters);
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
//		model.addAttribute("tzgg", new Tzgg());
//		model.addAttribute("action", "create");
//		List<DictBean> dictBeans = dictionary.getZdxx(DictConsts.ZDLX_TZGGLX);
//		StringBuilder zglsxCombo = new StringBuilder();
//		String ZGLSX_COMBO = "<option value='%s'>%s</option>";
//		for (DictBean tmp : dictBeans) {
//			zglsxCombo.append(String.format(ZGLSX_COMBO, tmp.getValue(), tmp.getName()));
//		}
//		model.addAttribute("lxComboHtml", zglsxCombo.toString());
//
//		StringBuilder zdCombo = new StringBuilder();
//		zdCombo.append(String.format(ZGLSX_COMBO, "0", "否"));
//		zdCombo.append(String.format(ZGLSX_COMBO, "1", "是"));
//		model.addAttribute("zdComboHtml", zdCombo.toString());
//		return "system/tzggForm";
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
//	public String create(@Valid Tzgg dict, Model model) {
//		dict.setCreateId(UserUtil.getCurrentUser().getId());
//		dict.setCreateName(UserUtil.getCurrentUser().getName());
//		dict.setUpdateId(UserUtil.getCurrentUser().getId());
//		dict.setUpdateName(UserUtil.getCurrentUser().getName());
//		dict.setScbz("0");
//		tzggService.save(dict);
//		return "success";
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
//	public String updateForm(@PathVariable("id") Long id, Model model) {
//		Tzgg olx = tzggService.get(id);
//		model.addAttribute("tzgg", olx);
//		model.addAttribute("tzggnr", new String(olx.getTznr(), Charset.defaultCharset()));
//		model.addAttribute("action", "update");
//		List<DictBean> dictBeans = dictionary.getZdxx(DictConsts.ZDLX_TZGGLX);
//		StringBuilder zglsxCombo = new StringBuilder();
//		String ZGLSX_COMBO = "<option value='%s'>%s</option>";
//		String ZGLSX_SCOMBO = "<option value='%s' selected>%s</option>";
//		for (DictBean tmp : dictBeans) {
//			if (olx.getLx().equals(tmp.getValue())) {
//				zglsxCombo.append(String.format(ZGLSX_SCOMBO, tmp.getValue(), tmp.getName()));
//			} else {
//				zglsxCombo.append(String.format(ZGLSX_COMBO, tmp.getValue(), tmp.getName()));
//			}
//		}
//		model.addAttribute("lxComboHtml", zglsxCombo.toString());
//		StringBuilder zdCombo = new StringBuilder();
//		if (olx.getZdbz().equals("1")) {
//			zdCombo.append(String.format(ZGLSX_COMBO, "0", "否"));
//			zdCombo.append(String.format(ZGLSX_SCOMBO, "1", "是"));
//		} else {
//			zdCombo.append(String.format(ZGLSX_SCOMBO, "0", "否"));
//			zdCombo.append(String.format(ZGLSX_COMBO, "1", "是"));
//		}
//		model.addAttribute("zdComboHtml", zdCombo.toString());
//		return "system/tzggForm";
//	}
//
//	/**
//	 * 查看
//	 * 
//	 * @param id
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
//	public String viewForm(@PathVariable("id") Long id, Model model) {
//		Tzgg olx = tzggService.get(id);
//		model.addAttribute("tzgg", olx);
//		model.addAttribute("tzggnr", new String(olx.getTznr(), Charset.defaultCharset()));
//		model.addAttribute("action", "view");
//		List<DictBean> dictBeans = dictionary.getZdxx(DictConsts.ZDLX_TZGGLX);
//		StringBuilder zglsxCombo = new StringBuilder();
//		String ZGLSX_COMBO = "<option value='%s'>%s</option>";
//		String ZGLSX_SCOMBO = "<option value='%s' selected>%s</option>";
//		for (DictBean tmp : dictBeans) {
//			if (olx.getLx().equals(tmp.getValue())) {
//				zglsxCombo.append(String.format(ZGLSX_SCOMBO, tmp.getValue(), tmp.getName()));
//			} else {
//				zglsxCombo.append(String.format(ZGLSX_COMBO, tmp.getValue(), tmp.getName()));
//			}
//		}
//		model.addAttribute("lxComboHtml", zglsxCombo.toString());
//		StringBuilder zdCombo = new StringBuilder();
//		if (olx.getZdbz().equals("1")) {
//			zdCombo.append(String.format(ZGLSX_COMBO, "0", "否"));
//			zdCombo.append(String.format(ZGLSX_SCOMBO, "1", "是"));
//		} else {
//			zdCombo.append(String.format(ZGLSX_SCOMBO, "0", "否"));
//			zdCombo.append(String.format(ZGLSX_COMBO, "1", "是"));
//		}
//		model.addAttribute("zdComboHtml", zdCombo.toString());
//		return "system/tzggviewForm";
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
//	public String update(@Valid @ModelAttribute @RequestBody Tzgg dict, Model model) {
//
//		dict.setUpdateId(UserUtil.getCurrentUser().getId());
//		dict.setUpdateTime(new Date());
//		dict.setUpdateName(UserUtil.getCurrentUser().getName());
//		tzggService.update(dict);
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
//	public String delete(@PathVariable("id") Long id) {
//		// tzggService.delete(id);
//		Tzgg dict = tzggService.get(id);
//		dict.setScbz("1");
//		dict.setUpdateId(UserUtil.getCurrentUser().getId());
//		dict.setUpdateTime(new Date());
//		dict.setUpdateName(UserUtil.getCurrentUser().getName());
//		tzggService.update(dict);
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
