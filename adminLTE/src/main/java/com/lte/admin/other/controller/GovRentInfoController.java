
package com.lte.admin.other.controller;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.entity.DictType;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.other.service.GovRentInfoService;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.other.entity.GovRentInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Andy
 */
@Controller
@RequestMapping("web/govRentInfo")
public class GovRentInfoController extends BaseController {
	@Resource
	private GovRentInfoService govRentInfoService;

	public GovRentInfo getEntity4Request(HttpServletRequest request) {
			GovRentInfo entity=new GovRentInfo();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createTime"))){
			entity.setCreateTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("createTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("createBy"))){
			entity.setCreateBy(request.getParameter("createBy"));
			}
			if(StringUtils.isNotBlank(request.getParameter("state"))){
			entity.setState(Integer.valueOf(request.getParameter("state")));
			}
			if(StringUtils.isNotBlank(request.getParameter("lastTime"))){
			entity.setLastTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("lastTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("lastBy"))){
			entity.setLastBy(request.getParameter("lastBy"));
			}
			if(StringUtils.isNotBlank(request.getParameter("comName"))){
			entity.setComName(request.getParameter("comName"));
			}
			if(StringUtils.isNotBlank(request.getParameter("contacts"))){
			entity.setContacts(request.getParameter("contacts"));
			}
			if(StringUtils.isNotBlank(request.getParameter("contactsPhone"))){
			entity.setContactsPhone(request.getParameter("contactsPhone"));
			}
			if(StringUtils.isNotBlank(request.getParameter("businessType"))){
			entity.setBusinessType(request.getParameter("businessType"));
			}
			if(StringUtils.isNotBlank(request.getParameter("city"))){
			entity.setCity(request.getParameter("city"));
			}
			return entity;
	}
	/**
	 * 跳转订单主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toOrderView(HttpServletRequest request){

		return "other/govRentList";
	}
	/**
	 * 获取挂靠订单列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "govRentInfoList",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> govRentInfoList(HttpServletRequest request){
		MemberLogin memberLogin = (MemberLogin)request.getSession().getAttribute("user");
		PageList<GovRentInfo> page1 = new PageList<GovRentInfo>();
		Page<GovRentInfo> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		try{
			page1 = govRentInfoService.getList(page, filters);
		}catch(Exception e){
			return null;
		}
		return getEasyUIData(page1,request);
	}
}