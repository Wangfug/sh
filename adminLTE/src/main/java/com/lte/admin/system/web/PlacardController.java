package com.lte.admin.system.web;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.consts.DictConsts;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.entity.DictBean;
import com.lte.admin.entity.Placard;
import com.lte.admin.system.service.PlacardService;
import com.lte.admin.system.utils.UserUtil;

@Controller
@RequestMapping("system/placard")
public class PlacardController extends BaseController  {

	@Autowired
	private PlacardService placardService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String tzgglist(Model model) {
		List<DictBean> dictBeans = makeTmpData();
		StringBuilder zglsxCombo = new StringBuilder(
				"<option value=''>全部</option>");
		String ZGLSX_COMBO = "<option value='%s'>%s</option>";
		for (DictBean tmp : dictBeans) {
			zglsxCombo.append(
					String.format(ZGLSX_COMBO, tmp.getValue(), tmp.getName()));
		}
		model.addAttribute("lxComboHtml", zglsxCombo.toString());
		return "system/placardList";
	}
	
	private List<DictBean> makeTmpData() {
		List<DictBean> list = new ArrayList<DictBean>();
		DictBean bean = new DictBean();
		bean.setCode("TZGGLX_TZ");
		bean.setValue("1");
		bean.setName("通知");
		list.add(bean);
		
		bean = new DictBean();
		bean.setCode("TZGGLX_GG");
		bean.setValue("2");
		bean.setName("公告");
		list.add(bean);
		
		bean = new DictBean();
		bean.setCode("TZGGLX_WTDY");
		bean.setValue("3");
		bean.setName("问题答疑");
		list.add(bean);
		
		bean = new DictBean();
		bean.setCode("TZGGLX_CZSC");
		bean.setValue("4");
		bean.setName("操作手册");
		list.add(bean);
		
		return list;
	}
	
	
	/**
	 * 获取字典json
	 */
	@RequestMapping(value = "json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> dictList(HttpServletRequest request) {
		Page<Placard> page = getPage(request);
		Map<String, Object> filters = PropertyFilter
				.buildFromHttpRequest(request);
		filters.put("scbz", "0");
		PageList<Placard> page1 = placardService.getList(page, filters);
		return getEasyUIData(page1,request);

	}
	
	
	/**
	 * 添加字典跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("tzgg", new Placard());
		model.addAttribute("action", "create");
		List<DictBean> dictBeans = makeTmpData();
		StringBuilder zglsxCombo = new StringBuilder();
		String ZGLSX_COMBO = "<option value='%s'>%s</option>";
		for (DictBean tmp : dictBeans) {
			zglsxCombo.append(
					String.format(ZGLSX_COMBO, tmp.getValue(), tmp.getName()));
		}
		model.addAttribute("lxComboHtml", zglsxCombo.toString());

		StringBuilder zdCombo = new StringBuilder();
		zdCombo.append(String.format(ZGLSX_COMBO, "0", "否"));
		zdCombo.append(String.format(ZGLSX_COMBO, "1", "是"));
		model.addAttribute("zdComboHtml", zdCombo.toString());
		return "system/placardForm";
	}
	
	
	/**
	 * 添加字典
	 * 
	 * @param dict
	 * @param model
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid Placard dict, Model model) {
		dict.setCreateCode(UserUtil.getCurrentUser().getMemberCode());
		dict.setCreateName(UserUtil.getCurrentUser().getMemberName());
		dict.setUpdateCode(UserUtil.getCurrentUser().getMemberCode());
		dict.setUpdateName(UserUtil.getCurrentUser().getMemberName());
		dict.setDeleteFlag((byte)0);
		placardService.save(dict);
		return "success";
	}
	
	/**
	 * 修改字典跳转
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		Placard olx = placardService.get(id);
		model.addAttribute("tzgg", olx);
		model.addAttribute("tzggnr", new String(olx.getContent().getBytes(), Charset.defaultCharset()));
		model.addAttribute("action", "update");
		List<DictBean> dictBeans = makeTmpData();
		StringBuilder zglsxCombo = new StringBuilder();
		String ZGLSX_COMBO = "<option value='%s'>%s</option>";
		String ZGLSX_SCOMBO = "<option value='%s' selected>%s</option>";
		for (DictBean tmp : dictBeans) {
			if (olx.getType().equals(tmp.getValue())) {
				zglsxCombo.append(String.format(ZGLSX_SCOMBO, tmp.getValue(),
						tmp.getName()));
			} else {
				zglsxCombo.append(String.format(ZGLSX_COMBO, tmp.getValue(),
						tmp.getName()));
			}
		}
		model.addAttribute("lxComboHtml", zglsxCombo.toString());
		StringBuilder zdCombo = new StringBuilder();
		if (olx.getIsTop().equals("1")) {
			zdCombo.append(String.format(ZGLSX_COMBO, "0", "否"));
			zdCombo.append(String.format(ZGLSX_SCOMBO, "1", "是"));
		} else {
			zdCombo.append(String.format(ZGLSX_SCOMBO, "0", "否"));
			zdCombo.append(String.format(ZGLSX_COMBO, "1", "是"));
		}
		model.addAttribute("zdComboHtml", zdCombo.toString());
		return "system/placardForm";
	}
	
	/**
	 * 修改字典
	 * 
	 * @param dict
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@Valid @ModelAttribute @RequestBody Placard dict,
			Model model) {

		dict.setUpdateCode(UserUtil.getCurrentUser().getMemberCode());
		dict.setUpdateTime(new Date());
		dict.setUpdateName(UserUtil.getCurrentUser().getMemberName());
		placardService.update(dict);
		return "success";
	}
	
	/**
	 * 删除字典
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") Long id) {
		Placard dict = placardService.get(id);
		dict.setDeleteFlag((byte)1);
		dict.setUpdateCode(UserUtil.getCurrentUser().getMemberCode());
		dict.setUpdateTime(new Date());
		dict.setUpdateName(UserUtil.getCurrentUser().getMemberName());
		placardService.update(dict);
		return "success";
	}

	@ModelAttribute
	public void getDict(
			@RequestParam(value = "id", defaultValue = "-1") Integer id,
			Model model) {
		if (id != -1) {
			// model.addAttribute("dict", dictService.get(id));
		}
	}
	
	/**
	 * 查看
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewForm(@PathVariable("id") Long id, Model model) {
		Placard olx = placardService.get(id);
		model.addAttribute("tzgg", olx);
		model.addAttribute("content", new String(olx.getContent().getBytes(),Charset.defaultCharset()));
		model.addAttribute("action", "view");
		List<DictBean> dictBeans = makeTmpData();
		StringBuilder zglsxCombo = new StringBuilder();
		String ZGLSX_COMBO = "<option value='%s'>%s</option>";
		String ZGLSX_SCOMBO = "<option value='%s' selected>%s</option>";
		for (DictBean tmp : dictBeans) {
			if (olx.getType().equals(tmp.getValue())) {
				zglsxCombo.append(String.format(ZGLSX_SCOMBO, tmp.getValue(),
						tmp.getName()));
			} else {
				zglsxCombo.append(String.format(ZGLSX_COMBO, tmp.getValue(),
						tmp.getName()));
			}
		}
		model.addAttribute("lxComboHtml", zglsxCombo.toString());
		StringBuilder zdCombo = new StringBuilder();
		if (olx.getIsTop().equals("1")) {
			zdCombo.append(String.format(ZGLSX_COMBO, "0", "否"));
			zdCombo.append(String.format(ZGLSX_SCOMBO, "1", "是"));
		} else {
			zdCombo.append(String.format(ZGLSX_SCOMBO, "0", "否"));
			zdCombo.append(String.format(ZGLSX_COMBO, "1", "是"));
		}
		model.addAttribute("zdComboHtml", zdCombo.toString());
		return "system/placardViewForm";
	}
}
