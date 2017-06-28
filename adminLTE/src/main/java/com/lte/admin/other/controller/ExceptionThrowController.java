
package com.lte.admin.other.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.entity.DictType;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.other.entity.CompAssociated;
import com.lte.admin.other.entity.ExceptionThrow;
import com.lte.admin.other.service.ExceptionThrowService;
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
@RequestMapping("web/exceptionThrow")
public class ExceptionThrowController extends BaseController  {
	@Resource
	private ExceptionThrowService exceptionThrowService;
	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toOtherView(){
		return "other/exceptionThrowList";
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
		 ExceptionThrow  exceptionThrow = getEntity4Request(request);
		if( exceptionThrow.getId()!=null){
			exceptionThrowService.update( exceptionThrow);
		}else{
			exceptionThrow.setCreateTime(new Timestamp(System.currentTimeMillis()));
			exceptionThrow.setCreateBy(user.getId());
			exceptionThrowService.save( exceptionThrow);
		}
		return "success";
	}

	/**
	 * 获取订单列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getExceptionThrowList",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getExceptionThrowList(HttpServletRequest request){
		Page< ExceptionThrow> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList< ExceptionThrow> page1 = exceptionThrowService.getList(page, filters);
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
		model.addAttribute("action", "saveOrUpdate");
		ExceptionThrow exceptionThrow = exceptionThrowService.get(id);
		model.addAttribute("exceptionThrow", exceptionThrow);
		return "other/exceptionThrowForm";
	}

	/**
	 *新增信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model) {
		model.addAttribute("action", "saveOrUpdate");
		return "other/exceptionThrowForm";
	}


	public ExceptionThrow getEntity4Request(HttpServletRequest request) {
			ExceptionThrow entity=new ExceptionThrow();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createBy"))){
			entity.setCreateBy(Long.valueOf(request.getParameter("createBy")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createTime"))){
			entity.setCreateTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("createTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("exceptionOrder"))){
			entity.setExceptionOrder(Long.valueOf(request.getParameter("exceptionOrder")));
			}
			if(StringUtils.isNotBlank(request.getParameter("handleBy"))){
			entity.setHandleBy(request.getParameter("handleBy"));
			}
			if(StringUtils.isNotBlank(request.getParameter("isHandle"))){
			entity.setIsHandle(request.getParameter("isHandle"));
			}
			return entity;
	}
}