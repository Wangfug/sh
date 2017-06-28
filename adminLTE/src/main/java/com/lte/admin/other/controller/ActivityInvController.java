
package com.lte.admin.other.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.custom.entity.Customer;
import com.lte.admin.custom.service.CustomerService;
import com.lte.admin.other.entity.Activity;
import com.lte.admin.other.entity.ActivityInv;
import com.lte.admin.other.service.ActivityInvService;
import com.lte.admin.other.service.ActivityService;
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
@RequestMapping("web/activityInv")
public class ActivityInvController extends BaseController  {
	@Resource
	private ActivityInvService activityInvService;
	@Resource
	private ActivityService activityService;
	@Resource
	private CustomerService customerService;
	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toOtherView(HttpServletRequest request){
		List<Activity> activitys = activityService.getList();
		JSONArray jsonArray = JSONArray.fromObject(activitys);
		request.getSession().setAttribute("activitys",jsonArray);

		List<Customer> customers = customerService.getList();
		JSONArray jsonArray1 = JSONArray.fromObject(customers);
		request.getSession().setAttribute("customers",jsonArray1);

		return "other/activityInvList";
	}

	/**
	 *修改信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		ActivityInv activityInv = activityInvService.get(id);
		model.addAttribute("activityInv", activityInv);
		model.addAttribute("action", "saveOrUpdate");
		return "other/activityInvForm";
	}

	/**
	 *新增信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model) {
		model.addAttribute("action", "saveOrUpdate");
		return "other/activityInvForm";
	}

	/**
	 * 增加活动参与
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveOrUpdate",method = RequestMethod.POST)
	@ResponseBody
	public String saveOrUpdate(HttpServletRequest request){
		ActivityInv activityInv = getEntity4Request(request);
		if(activityInv.getId()!=null){
			activityInvService.update(activityInv);
		}else{
			activityInv.setCreateTime(new Timestamp(System.currentTimeMillis()));
			activityInvService.save(activityInv);
		}
		return "success";
	}

	/**
	 * 获取活动参与列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getActivityInvList",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getActivityInvList(HttpServletRequest request){
		Page<ActivityInv> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<ActivityInv> page1 = activityInvService.getList(page, filters);
		return getEasyUIData(page1, request);
	}

	public ActivityInv getEntity4Request(HttpServletRequest request) {
			ActivityInv entity=new ActivityInv();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createTime"))){
			entity.setCreateTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("createTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("state"))){
			entity.setState(Integer.valueOf(request.getParameter("state")));
			}
			if(StringUtils.isNotBlank(request.getParameter("activityId"))){
			entity.setActivityId(Long.valueOf(request.getParameter("activityId")));
			}
			if(StringUtils.isNotBlank(request.getParameter("activityInv"))){
			entity.setActivityInv(Long.valueOf(request.getParameter("activityInv")));
			}
			return entity;
	}
}