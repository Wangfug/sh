
package com.lte.admin.other.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.entity.DictType;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.other.entity.TbaseCompany;
import com.lte.admin.other.service.TbaseCityService;
import com.lte.admin.other.service.TbaseCompanyService;
import com.lte.admin.system.service.DictTypeService;
import com.lte.admin.system.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author Andy
 */
@Controller
@RequestMapping("web/tbaseCompany")
public class TbaseCompanyController extends BaseController  {
	@Resource
	private TbaseCompanyService tbaseCompanyService;
	@Resource
	private TbaseCityService tbaseCityService;
	@Resource
	private DictTypeService dictTypeService;
	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toOtherView(HttpServletRequest request) {
		List<Map> citysForCompany = tbaseCityService.getList1();
		request.getSession().setAttribute("citysForCompany",citysForCompany);
		List<DictType> dictsForCompany = dictTypeService.getChildrenByParent("ComType");
		request.getSession().setAttribute("dictsForCompany",dictsForCompany);
		List<Map> companysForCompany = tbaseCompanyService.getList();
		request.getSession().setAttribute("companysForCompany",companysForCompany);
		return "other/tbaseCompanyList";
	}

	/**
	 * 增加活动
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveOrUpdate",method = RequestMethod.POST)
	@ResponseBody
	public String saveOrUpdate(HttpServletRequest request){
		MemberLogin user = UserUtil.getCurrentUser();
		TbaseCompany tbaseCompany = getEntity4Request(request);
		if(tbaseCompany.getId()!=null){
			tbaseCompanyService.update(tbaseCompany);
		}else{
			tbaseCompanyService.save(tbaseCompany);
		}
		return "success";
	}

	/**
	 * 获取公司列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getTbaseCompanyList",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getTbaseCompanyList(HttpServletRequest request){
		Page<TbaseCompany> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<Map> page1 = tbaseCompanyService.getList1(page, filters);
		return getEasyUIData(page1, request);
	}

	/**
	 * 创建或修改城市
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "create",method = {RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletRequest request,String id){
		TbaseCompany tbaseCompany = null;
		if(StringUtils.isNotBlank(id))
			tbaseCompany = tbaseCompanyService.get(Long.parseLong(id));
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("tbaseCompany",tbaseCompany);
		return "other/tbaseCompanyForm";
	}

	public TbaseCompany getEntity4Request(HttpServletRequest request) {
			TbaseCompany entity=new TbaseCompany();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
			}
			if(StringUtils.isNotBlank(request.getParameter("companyCode"))){
			entity.setCompanyCode(request.getParameter("companyCode"));
			}
			if(StringUtils.isNotBlank(request.getParameter("companyName"))){
			entity.setCompanyName(request.getParameter("companyName"));
			}
			if(StringUtils.isNotBlank(request.getParameter("parentComanyCode"))){
			entity.setParentComanyCode(request.getParameter("parentComanyCode"));
			}
			if(StringUtils.isNotBlank(request.getParameter("companyType"))){
			entity.setCompanyType(request.getParameter("companyType"));
			}
			if(StringUtils.isNotBlank(request.getParameter("updateTime"))){
			entity.setUpdateTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("updateTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("cityId"))){
			entity.setCityId(Long.valueOf(request.getParameter("cityId")));
			}
			return entity;
	}
}