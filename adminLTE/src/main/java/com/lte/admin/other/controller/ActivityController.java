
package com.lte.admin.other.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.response.ServiceResponse;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.custom.entity.Customer;
import com.lte.admin.custom.entity.CustomerDiscount;
import com.lte.admin.custom.entity.CustomerDiscountHold;
import com.lte.admin.custom.service.CustomerDiscountService;
import com.lte.admin.custom.service.CustomerService;
import com.lte.admin.entity.DictType;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.mobile.common.PushExample;
import com.lte.admin.other.entity.Activity;
import com.lte.admin.other.entity.ActivityInv;
import com.lte.admin.other.service.ActivityInvService;
import com.lte.admin.other.service.ActivityService;
import com.lte.admin.other.service.TbaseCityService;
import com.lte.admin.system.service.DictTypeService;
import com.lte.admin.system.utils.UserUtil;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.ArrayUtils;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Andy
 */
@Controller
@RequestMapping("web/activity")
public class ActivityController extends BaseController  {
	@Resource
	private ActivityService activityService;
	@Resource
	private DictTypeService dictTypeService;
	@Resource
	private ActivityInvService activityInvService;
	@Resource
	private CustomerDiscountService customerDiscountService;
	@Resource
	private CustomerService customerService;
	@Resource
	private TbaseCityService tbaseCityService;


	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toOtherView(HttpServletRequest request){
		try{
			List<DictType> dictBeans = dictTypeService.getChildrenByParent("ActivityState");
			JSONArray jsonArray = JSONArray.fromObject(dictBeans);
			request.getSession().setAttribute("activityState",jsonArray);

			List<DictType> dictBeans1 = dictTypeService.getChildrenByParent("ActivityType");
			JSONArray jsonArray1 = JSONArray.fromObject(dictBeans1);
			request.getSession().setAttribute("activityType",jsonArray1);

			List<Map> pcitys =tbaseCityService.getNameList(0l);
			List<String> citysForACT = new ArrayList<>();
			for(Map map:pcitys){
				Long id = (Long)map.get("id");
				List<Map> subcitys =tbaseCityService.getNameList(id);
				for(Map map1:subcitys){
					citysForACT.add((String)map1.get("city_name"));
				}
			}
			request.getSession().setAttribute("citysForACT",citysForACT);
		}catch(Exception e){
			return null;
		}

		return "other/activityList";
	}

	/**
	 *修改信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		Activity activity = activityService.get(id);
		List<CustomerDiscount> discounts = customerDiscountService.getEffectiveList();
		model.addAttribute("EffectiveDiscounts",discounts);
		model.addAttribute("activity", activity);
		model.addAttribute("action", "saveOrUpdate");
		return "other/activityForm";
	}

	/**
	 *新增信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model) {
		List<CustomerDiscount> discounts = customerDiscountService.getEffectiveList();
		model.addAttribute("EffectiveDiscounts",discounts);
		model.addAttribute("action", "saveOrUpdate");

		return "other/activityForm";
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
		Activity activity = getEntity4Request(request);
		String result = "false";
		try{
			CustomerDiscount customerDiscount = new CustomerDiscount();
			customerDiscount.setId(activity.getCoupon());
			customerDiscount.setDiscountState(0);
			if(activity.getId()!=null){
				activity.setLastTime(new Timestamp(System.currentTimeMillis()));
				activity.setLastBy(user.getId());
				result = activityService.update(activity,customerDiscount);
			}else{
				activity.setCreateTime(new Timestamp(System.currentTimeMillis()));
				activity.setCreateBy(user.getId());
				activity.setLastTime(new Timestamp(System.currentTimeMillis()));
				activity.setLastBy(user.getId());
				result = activityService.save(activity,customerDiscount);
			}
		}catch(Exception e){
			result = "false";
		}

		return result;
	}

	/**
	 * 获取订单列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getActivityList",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getActivityList(HttpServletRequest request){
		Page<Activity> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<Activity> page1 = activityService.getList(page, filters);
		return getEasyUIData(page1, request);
	}
	/**
	 * 查看活动参与情况
	 * viewInvInfo
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "viewInvInfo",method = {RequestMethod.GET,RequestMethod.POST})
	public String viewInvInfo(HttpServletRequest request,Long id){
		List<Map<String,Object>> actInvList =activityInvService.getInvByACT(id);
		request.setAttribute("actInvList",actInvList);
		return "other/actInvInfo";
	}

	/**
	 * 审核活动参与
	 * viewInvInfo
	 * @param type
	 * @return
	 */

	@RequestMapping(value = "joinInACT",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String joinInACT(Integer type,Long id,String failReason){
		ServiceResponse sr = new ServiceResponse();
		try{
			ActivityInv act = activityInvService.get(id);
			Customer cus = customerService.getOneCustomer(act.getActivityInv());
			Activity activity = activityService.get(act.getActivityId());
			act.setId(id);
			act.setState(type);
			act.setFailReason(failReason);
			activityInvService.update(act);
			if(type==1){
				PushExample.sendAppMsg(cus.getPassword(), "您参加的活动！"+activity.getMainTitle()+"通过了，请留意活动详情！");
			}else if(type==2){
				PushExample.sendAppMsg(cus.getPassword(), "您参加的活动！"+activity.getMainTitle()+
						"未通过申请！原因是"+failReason);
			}
			sr.setStatus(1);
			sr.setInfo("操作成功");
		}catch(Exception e){
			sr.setInfo("操作异常");
		}
		return sr.objectToJson();
	}


	public Activity getEntity4Request(HttpServletRequest request) {
			Activity entity=new Activity();
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
			if(StringUtils.isNotBlank(request.getParameter("activityPage"))){
			entity.setActivityPage(request.getParameter("activityPage"));
			}
			if(ArrayUtils.isNotEmpty(request.getParameterValues("activityPosition"))){
				String position = "";
				String[] positions = request.getParameterValues("activityPosition");
				for(String str:positions){
					position+=str+",";
				}
				if(StringUtils.isNotBlank(position))
					position = position.substring(0,position.length()-1);
				entity.setActivityPosition(position);
			}
			if(StringUtils.isNotBlank(request.getParameter("activityNo"))){
			entity.setActivityNo(request.getParameter("activityNo"));
			}
			if(StringUtils.isNotBlank(request.getParameter("activityName"))){
			entity.setActivityName(request.getParameter("activityName"));
			}
			if(StringUtils.isNotBlank(request.getParameter("activityStart"))){
			entity.setActivityStart(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("activityStart")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("activityEnd"))){
			entity.setActivityEnd(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("activityEnd")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("activityType"))){
			entity.setActivityType(request.getParameter("activityType"));
			}
			if(StringUtils.isNotBlank(request.getParameter("activitySort"))){
			entity.setActivitySort(request.getParameter("activitySort"));
			}
			if(ArrayUtils.isNotEmpty(request.getParameterValues("attachment"))){
				String attachment = "";
				String[] filepath = request.getParameterValues("attachment");
				for(String str:filepath){
					attachment+=str+",";
				}
				if(StringUtils.isNotBlank(attachment))
					attachment = attachment.substring(0,attachment.length()-1);
			entity.setAttachment(attachment);
			}
			if(StringUtils.isNotBlank(request.getParameter("activityRemark"))){
			entity.setActivityRemark(request.getParameter("activityRemark"));
			}
			if(StringUtils.isNotBlank(request.getParameter("mainPage"))){
				entity.setMainPage(request.getParameter("mainPage"));
			}
			if(StringUtils.isNotBlank(request.getParameter("mainTitle"))){
				entity.setMainTitle(request.getParameter("mainTitle"));
			}
			if(StringUtils.isNotBlank(request.getParameter("content"))){
				entity.setContent(request.getParameter("content"));
			}
			if(StringUtils.isNotBlank(request.getParameter("subTitle"))){
				entity.setSubTitle(request.getParameter("subTitle"));
			}
			if(StringUtils.isNotBlank(request.getParameter("price"))){
				entity.setPrice(Double.valueOf(request.getParameter("price")));
			}
			if(StringUtils.isNotBlank(request.getParameter("couponId"))){
				entity.setCoupon(Long.valueOf(request.getParameter("couponId")));
			}
			return entity;
	}
}