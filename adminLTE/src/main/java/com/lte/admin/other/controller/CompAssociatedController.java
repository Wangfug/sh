
package com.lte.admin.other.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.entity.DictType;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.other.entity.CompAssociated;
import com.lte.admin.other.service.CompAssociatedService;
import com.lte.admin.system.service.DictTypeService;
import com.lte.admin.system.utils.UserUtil;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author Andy
 */
@Controller
@RequestMapping("web/compAssociated")
public class CompAssociatedController extends BaseController  {
	@Resource
	private CompAssociatedService compAssociatedService;
	@Resource
	private DictTypeService dictTypeService;

	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toOtherView(HttpServletRequest request){
		List<DictType> dictBeans = dictTypeService.getChildrenByParent("CompanyType");
		JSONArray jsonArray = JSONArray.fromObject(dictBeans);
		request.setAttribute("companyType",jsonArray);

		List<DictType> dictBeans1 = dictTypeService.getChildrenByParent("CredentialType");
		JSONArray jsonArray1 = JSONArray.fromObject(dictBeans1);
		request.setAttribute("idCardType",jsonArray1);

		return "other/compAssociatedList";
	}

	@RequestMapping(value = "saveOrUpdate",method = RequestMethod.POST)
	@ResponseBody
	public String saveOrUpdate(HttpServletRequest request){
		MemberLogin user = UserUtil.getCurrentUser();
		CompAssociated compAssociated = getEntity4Request(request);
		if(compAssociated.getId()!=null){
			compAssociated.setLastTime(new Timestamp(System.currentTimeMillis()));
			compAssociated.setLastBy(user.getId());
			compAssociatedService.update(compAssociated);
		}else{
			compAssociated.setCreateTime(new Timestamp(System.currentTimeMillis()));
			compAssociated.setCreateBy(user.getId());
			compAssociated.setLastTime(new Timestamp(System.currentTimeMillis()));
			compAssociated.setLastBy(user.getId());
			compAssociatedService.save(compAssociated);
		}
		return "success";
	}

	/**
	 * 获取订单列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getCompAssociatedList",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCompAssociatedList(HttpServletRequest request){
		Page<CompAssociated> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<CompAssociated> page1 = compAssociatedService.getList(page, filters);
		return getEasyUIData(page1, request);
	}

	/**
	 *修改信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		CompAssociated compAssociated = compAssociatedService.get(id);
		model.addAttribute("compAssociated", compAssociated);
		model.addAttribute("action", "saveOrUpdate");

		List<DictType> dictBeans = dictTypeService.getChildrenByParent("CredentialType");
		JSONArray jsonArray = JSONArray.fromObject(dictBeans);
		model.addAttribute("idCardType",jsonArray);

		List<DictType> dictBeans1 = dictTypeService.getChildrenByParent("CompanyType");
		JSONArray jsonArray1 = JSONArray.fromObject(dictBeans1);
		model.addAttribute("companyType",jsonArray1);

		return "other/compAssociatedForm";
	}

	/**
	 *新增信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model) {
		model.addAttribute("action", "saveOrUpdate");
		List<DictType> dictBeans = dictTypeService.getChildrenByParent("CredentialType");
		JSONArray jsonArray = JSONArray.fromObject(dictBeans);
		model.addAttribute("idCardType",jsonArray);

		List<DictType> dictBeans1 = dictTypeService.getChildrenByParent("CompanyType");
		JSONArray jsonArray1 = JSONArray.fromObject(dictBeans1);
		model.addAttribute("companyType",jsonArray1);

		return "other/compAssociatedForm";
	}


	public CompAssociated getEntity4Request(HttpServletRequest request) {
			CompAssociated entity=new CompAssociated();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createBy"))){
			entity.setCreateBy(Long.valueOf(request.getParameter("createBy")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createTime"))){
			entity.setCreateTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("createTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("state"))){
			entity.setState(Integer.valueOf(request.getParameter("state")));
			}
			if(StringUtils.isNotBlank(request.getParameter("lastTime"))){
			entity.setLastTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("lastTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("lastBy"))){
			entity.setLastBy(Long.valueOf(request.getParameter("lastBy")));
			}
			if(StringUtils.isNotBlank(request.getParameter("comName"))){
			entity.setComName(request.getParameter("comName"));
			}
			if(StringUtils.isNotBlank(request.getParameter("corporation"))){
			entity.setCorporation(request.getParameter("corporation"));
			}
			if(StringUtils.isNotBlank(request.getParameter("country"))){
			entity.setCountry(request.getParameter("country"));
			}
			if(StringUtils.isNotBlank(request.getParameter("province"))){
			entity.setProvince(request.getParameter("province"));
			}
			if(StringUtils.isNotBlank(request.getParameter("area"))){
			entity.setArea(request.getParameter("area"));
			}
			if(StringUtils.isNotBlank(request.getParameter("comAddress"))){
			entity.setComAddress(request.getParameter("comAddress"));
			}
			if(StringUtils.isNotBlank(request.getParameter("phone"))){
			entity.setPhone(request.getParameter("phone"));
			}
			if(StringUtils.isNotBlank(request.getParameter("remark"))){
			entity.setRemark(request.getParameter("remark"));
			}
			if(StringUtils.isNotBlank(request.getParameter("attachment"))){
			entity.setAttachment(request.getParameter("attachment"));
			}
		if(StringUtils.isNotBlank(request.getParameter("type"))){
			entity.setType(Integer.valueOf(request.getParameter("type")));
		}

		if(StringUtils.isNotBlank(request.getParameter("idCardType"))){
			entity.setIdCardType(Integer.valueOf(request.getParameter("idCardType")));
		}
		if(StringUtils.isNotBlank(request.getParameter("idCardNo"))){
			entity.setIdCardNo(request.getParameter("idCardNo"));
		}

			return entity;
	}

}