
package com.lte.admin.order.controller;
import com.lte.admin.order.service.OrderCarCheckService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.order.entity.OrderCarCheck;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;


/**
 * @author Andy
 */
@Controller
@RequestMapping("web/orderCarCheck")
public class OrderCarCheckController extends BaseController  {
	@Resource
	private OrderCarCheckService orderCarCheckService;
	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toOrderView(){
		return "order/orderList";
	}

	/**
	 * 增加订单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveOrUpdate",method = RequestMethod.GET)
	@ResponseBody
	public String saveOrUpdate(HttpServletRequest request){
		Session session = SecurityUtils.getSubject().getSession();
		OrderCarCheck orderCarCheck = getEntity4Request(request);
		if(orderCarCheck.getId()!=null){
			orderCarCheckService.update(orderCarCheck);
		}else{
			orderCarCheckService.save(orderCarCheck);
		}

		return "success";
	}

	/**
	 * 增加订单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delete",method = RequestMethod.GET)
	@ResponseBody
	public String delete(HttpServletRequest request){
		Long id = 0l;
		if(StringUtils.isNotBlank(request.getParameter("id"))){
			id = Long.parseLong(request.getParameter("id"));
			orderCarCheckService.deleteById(id);
			return "success";
		}

		return "false";
	}

	/**
	 * 获取订单列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getOrderList",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getOrderList(HttpServletRequest request){
		Page<OrderCarCheck> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<OrderCarCheck> page1 = orderCarCheckService.getList(page, filters);
		return getEasyUIData(page1, request);
	}
	public OrderCarCheck getEntity4Request(HttpServletRequest request) {
			OrderCarCheck entity=new OrderCarCheck();
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
			if(StringUtils.isNotBlank(request.getParameter("component"))){
			entity.setComponent(request.getParameter("component"));
			}
			if(StringUtils.isNotBlank(request.getParameter("isWell"))){
			entity.setIsWell(request.getParameter("isWell"));
			}
			if(StringUtils.isNotBlank(request.getParameter("remark"))){
			entity.setRemark(request.getParameter("remark"));
			}
			if(StringUtils.isNotBlank(request.getParameter("carId"))){
			entity.setCarId(Long.valueOf(request.getParameter("carId")));
			}
			if(StringUtils.isNotBlank(request.getParameter("attachment"))){
			entity.setAttachment(request.getParameter("attachment"));
			}
			return entity;
	}
}