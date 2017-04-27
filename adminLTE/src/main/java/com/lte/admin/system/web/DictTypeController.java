package com.lte.admin.system.web;

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
import com.lte.admin.common.consts.ErrorCodeConst;
import com.lte.admin.common.exception.AdminLteException;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.utils.DateUtils;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.entity.DictType;
import com.lte.admin.system.service.DictTypeService;
import com.lte.admin.system.utils.UserUtil;

@Controller
@RequestMapping("system/dictType")
public class DictTypeController extends BaseController {

	@Autowired
	private DictTypeService dictTypeService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String DictTypelist() {
		return "system/dictTypeList";
	}

	/**
	 * 获取字典json
	 */
	@RequestMapping(value = "json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> dictList(HttpServletRequest request) {
		Page<DictType> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<DictType> page1 = dictTypeService.getDzlxList(page, filters);
		for (DictType dictType : page1) {
			if(dictType.getParentCode().equals("0")){
				dictType.setParentName("根节点");
			}else{
				dictType.setParentName(dictTypeService.getDictByCode(dictType.getParentCode()).getName());
			}
		}
		return getEasyUIData(page1,request);

	}

	/**
	 * 添加字典跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("DictType", new DictType());
		model.addAttribute("action", "create");
		List<DictType> DictTypeBeans = dictTypeService.selectPlist();
		StringBuilder zglsxCombo = new StringBuilder("<option value='0'>根节点</option>");
		String ZGLSX_COMBO = "<option value='%s'>%s</option>";
		for (DictType tmp : DictTypeBeans) {
			zglsxCombo.append(String.format(ZGLSX_COMBO, tmp.getId(), tmp.getName()));
		}
		model.addAttribute("dictTypeComboHtml", zglsxCombo.toString());
		return "system/dictTypeForm";
	}

	/**
	 * 添加字典
	 * 
	 * @param dict
	 * @param model
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid DictType dict, Model model) {
		boolean checkflag = false;
		if(dict!=null){
			checkflag = checkDup(dict);
		}
		if(checkflag){
			throw new AdminLteException(ErrorCodeConst.SYSTEM_ERROR, "编码重复，请确认");
		}
		dict.setCreateCode(UserUtil.getCurrentUser().getMemberCode());
		dict.setCreateName(UserUtil.getCurrentUser().getMemberName());
		dict.setUpdateCode(UserUtil.getCurrentUser().getMemberCode());
		dict.setUpdateName(UserUtil.getCurrentUser().getMemberName());
		dict.setCreateTime(DateUtils.getSysDate());
		dict.setUpdateTime(DateUtils.getSysDate());
		dict.setDeleteFlag((byte)0);
		dictTypeService.save(dict);
		return "success";
	}

	private boolean checkDup(DictType dict) {
		List<DictType> dd = dictTypeService.getCheck(dict);
		if(dd!=null && dd.size()>=1){
			return true;
		}
		return false;
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
		 DictType olx = dictTypeService.get(id);
		model.addAttribute("dictType", olx);
		model.addAttribute("action", "update");
		List<DictType> dictTypeBeans = dictTypeService.selectPlist();
		StringBuilder zglsxCombo = new StringBuilder("<option value='0'>根节点</option>");
		String ZGLSX_COMBO = "<option value='%s'>%s</option>";
		String ZGLSX_COMBOs = "<option value='%s' selected>%s</option>";
		for (DictType tmp : dictTypeBeans) {
			if(olx.getId().intValue()==tmp.getId().intValue()){
				continue;
			}
			if(dictTypeService.getDictByCode(tmp.getParentCode()).getId().intValue() == tmp.getId().intValue()){
				zglsxCombo.append(String.format(ZGLSX_COMBOs, tmp.getId(), tmp.getName()));
			}else{
				zglsxCombo.append(String.format(ZGLSX_COMBO, tmp.getId(), tmp.getName()));

			}
		}
		model.addAttribute("dictTypeComboHtml", zglsxCombo.toString());
		return "system/dictTypeForm";
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
	public String update(@Valid @ModelAttribute @RequestBody DictType dict, Model model) {
		boolean checkflag = false;
		if(dict!=null){
			checkflag = checkDup(dict);
		}
		if(checkflag){
			throw new AdminLteException(ErrorCodeConst.SYSTEM_ERROR,"编码重复，请确认");
		}
		dict.setUpdateCode(UserUtil.getCurrentUser().getMemberCode());
		dict.setUpdateTime(new Date());
		dict.setUpdateName(UserUtil.getCurrentUser().getMemberName());
		dictTypeService.update(dict);
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
		//dictTypeService.delete(id);
		DictType dict = dictTypeService.get(id);
		dict.setDeleteFlag((byte)1);
		dict.setUpdateCode(UserUtil.getCurrentUser().getMemberCode());
		dict.setUpdateTime(new Date());
		dict.setUpdateName(UserUtil.getCurrentUser().getMemberName());
		dictTypeService.update(dict);
		return "success";
	}

	@ModelAttribute
	public void getDict(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		if (id != -1) {
			// model.addAttribute("dict", dictService.get(id));
		}
	}
}
