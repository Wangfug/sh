
package com.lte.admin.other.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.other.entity.TbaseCity;
import com.lte.admin.other.service.TbaseCityService;
import com.lte.admin.system.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
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
@RequestMapping("web/tbaseCity")
public class TbaseCityController extends BaseController  {
	@Resource
	private TbaseCityService tbaseCityService;

	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toOtherView(HttpServletRequest request){
		List<Map> citysForCreate = tbaseCityService.getList1();
		request.getSession().setAttribute("citysForCreate",citysForCreate);
		return "other/tbaseCityList";
	}

	/**
	 * 增加/修改城市
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveOrUpdate",method = RequestMethod.POST)
	@ResponseBody
	public String saveOrUpdate(HttpServletRequest request){
		MemberLogin user = UserUtil.getCurrentUser();
		TbaseCity tbaseCity = getEntity4Request(request);
		String firstword = PinyinUtil1.cn2FirstSpell(tbaseCity.getCityName());
			String pinyin = PinyinUtil1.cn2Spell(tbaseCity.getCityName());
			tbaseCity.setCityPinyin(pinyin);
			firstword = firstword.substring(0,1).toUpperCase();
			tbaseCity.setCityFirstWord(firstword);
		if(tbaseCity.getId()!=null){
			tbaseCity.setLastTime(new Timestamp(System.currentTimeMillis()));
			tbaseCity.setLastBy(user.getId());
			tbaseCityService.update(tbaseCity);
		}else{
			tbaseCity.setCreateTime(new Timestamp(System.currentTimeMillis()));
			tbaseCity.setCreateBy(user.getId());
			tbaseCity.setLastTime(new Timestamp(System.currentTimeMillis()));
			tbaseCity.setLastBy(user.getId());
			tbaseCityService.save(tbaseCity);
		}
		return "success";
	}

	/**
	 * 获取城市列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getTbaseCityList",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getTbaseCityList(HttpServletRequest request){
		Page<TbaseCity> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<TbaseCity> page1 = tbaseCityService.getList(page, filters);
		return getEasyUIData(page1, request);
	}
	/**
	 * 创建或修改城市
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "create",method = {RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletRequest request,String id){
		TbaseCity tbaseCity = null;
		if(StringUtils.isNotBlank(id))
			tbaseCity = tbaseCityService.get(Long.parseLong(id));
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("tbaseCity",tbaseCity);
		return "other/tbaseCityForm";
	}
//	@RequestMapping(value = "setCity",method = {RequestMethod.GET})
//	@ResponseBody
//	public String setCity(HttpServletRequest request){
//		List<TbaseCity> citys = tbaseCityService.getList();
//		for(TbaseCity city:citys){
//			String firstword = PinyinUtil1.cn2FirstSpell(city.getCityName());
//			String pinyin = PinyinUtil1.cn2Spell(city.getCityName());
//			city.setCityPinyin(pinyin);
//			firstword = firstword.substring(0,1).toUpperCase();
//			city.setCityFirstWord(firstword);
//			tbaseCityService.update(city);
//		}
//		String pinyin = PinyinUtil1.cn2FirstSpell("我是");
//		return "success";
//	}
	
	public TbaseCity getEntity4Request(HttpServletRequest request) {
			TbaseCity entity=new TbaseCity();
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
			if(StringUtils.isNotBlank(request.getParameter("cityName"))){
			entity.setCityName(request.getParameter("cityName"));
			}
			if(StringUtils.isNotBlank(request.getParameter("cityPinyin"))){
			entity.setCityPinyin(request.getParameter("cityPinyin"));
			}
			if(StringUtils.isNotBlank(request.getParameter("cityThreeCode"))){
			entity.setCityThreeCode(request.getParameter("cityThreeCode"));
			}
			if(StringUtils.isNotBlank(request.getParameter("cityFirstWord"))){
			entity.setCityFirstWord(request.getParameter("cityFirstWord"));
			}
			if(StringUtils.isNotBlank(request.getParameter("parentCity"))){
			entity.setParentCity(Long.valueOf(request.getParameter("parentCity")));
			}
			return entity;
	}
}