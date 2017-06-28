package com.lte.admin.custom.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.custom.entity.CustomerBalanceCash;
import com.lte.admin.custom.entity.CustomerBalanceChange;
import com.lte.admin.custom.service.CustomerBalanceCashService;
import com.lte.admin.custom.service.CustomerService;
import com.lte.admin.entity.MemberLogin;
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
@RequestMapping("web/customerBalanceCash")
public class CustomerBalanceCashController extends BaseController  {
	@Resource
	private CustomerBalanceCashService cashService;
	@Resource
	private CustomerService customerService;

	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toCashView(HttpServletRequest request) {
		List<Map> map = cashService.getUserList();
		request.getSession().setAttribute("userList",map);
		return "custom/customerBalanceCash";
	}
	@RequestMapping(value = "getList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCarAttachList(HttpServletRequest request) {
		Page<CustomerBalanceCash> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<CustomerBalanceCash> page1 = cashService.getList(page,filters);
		return getEasyUIData(page1, request);
	}

	@RequestMapping(value = "cashDetail",method = {RequestMethod.GET,RequestMethod.POST})
	public String cashDetail(HttpServletRequest request,String id){
		Map<String,Object> map = null;
		if(StringUtils.isNotBlank(id)){
			map = cashService.getOneCash(Long.parseLong(id));
		}
		request.setAttribute("customerBalanceCash",map);
		request.setAttribute("action","update");
		return "custom/customerBalanceCashForm";
	}

	@RequestMapping(value = "cashEnd", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String cashEnd(HttpServletRequest request,String id ,String transactionNo) {
		MemberLogin user = UserUtil.getCurrentUser();
		CustomerBalanceCash cash = cashService.getOneCashById(Long.parseLong(id));
		String result = "false";
		if(null != cash){
			cash.setLastBy(user.getId());
			cash.setLastTime(new Timestamp(System.currentTimeMillis()));
			cash.setState(1);
			cash.setTransactionNo(Long.parseLong(transactionNo));
			result = cashService.updateCustomerBalanceCash(cash);
		}
		return result;
	}

	public CustomerBalanceCash getEntity4Request(HttpServletRequest request) {
		CustomerBalanceCash entity=new CustomerBalanceCash();
		if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
		}
		if(StringUtils.isNotBlank(request.getParameter("createBy"))){
			entity.setCreateBy(Long.valueOf(request.getParameter("createBy")));
		}
		if(StringUtils.isNotBlank(request.getParameter("createTime"))){
			entity.setCreateTime(new Timestamp(DateUtil.stringToDate(request.getParameter("createTime")).getTime()));
		}
		if(StringUtils.isNotBlank(request.getParameter("lastBy"))){
			entity.setLastBy(Long.valueOf(request.getParameter("lastBy")));
		}
		if(StringUtils.isNotBlank(request.getParameter("lastTime"))){
			entity.setLastTime(new Timestamp(DateUtil.stringToDate(request.getParameter("lastTime")).getTime()));
		}
		if(StringUtils.isNotBlank(request.getParameter("money"))){
			entity.setMoney(Double.valueOf(request.getParameter("money")));
		}
		if(StringUtils.isNotBlank(request.getParameter("name"))){
			entity.setName(request.getParameter("name"));
		}
		if(StringUtils.isNotBlank(request.getParameter("bank"))){
			entity.setBank(request.getParameter("bank"));
		}
		if(StringUtils.isNotBlank(request.getParameter("accountNum"))){
			entity.setAccountNum(Long.parseLong(request.getParameter("accountNum")));
		}
		if(StringUtils.isNotBlank(request.getParameter("state"))){
			entity.setState(Integer.parseInt(request.getParameter("state")));
		}
		if(StringUtils.isNotBlank(request.getParameter("transactionNo"))){
			entity.setTransactionNo(Long.valueOf(request.getParameter("transactionNo")));
		}
		if(StringUtils.isNotBlank(request.getParameter("balanceChangeId"))){
			entity.setBalanceChangeId(Long.valueOf(request.getParameter("balanceChangeId")));
		}
		return entity;
	}
}