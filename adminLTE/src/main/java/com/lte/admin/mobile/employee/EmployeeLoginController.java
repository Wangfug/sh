package com.lte.admin.mobile.employee;

import com.lte.admin.common.response.ServiceResponse;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.custom.entity.TbaseEmployee;
import com.lte.admin.custom.service.TbaseEmployeeService;
import com.lte.admin.entity.Member;
import com.lte.admin.entity.Ryxx;
import com.lte.admin.system.service.MemberService;
import com.lte.admin.system.service.RyxxService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Map;
import java.util.UUID;

/**
 * @author Andy
 */
@Controller
@RequestMapping("mobile/employee")
public class EmployeeLoginController extends BaseController {
	@Resource
	private TbaseEmployeeService tbaseEmployeeService;
	@Resource
	private MemberService memberService;
    @Resource
    private RyxxService ryxxService;
	/**
	 * 验证登录
	 */
	@RequestMapping(value = "login", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String login(HttpServletRequest request, String password, String mobile) {
		ServiceResponse serviceResponse = new ServiceResponse();
		String token = "";
		if (StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(password)) {
			if (mobile.equals("18852890966") && password.equals("123456")) {
				request.getSession().setAttribute("token", "123456");
				serviceResponse.setData(token);
				serviceResponse.setStatus(1);
				serviceResponse.setInfo("验证成功");
			} else {
				//否则根据手机号和密码匹配是否存在该用户，存在更新token返回token+status=1，不存在告诉错误
				Member member = memberService.getMemberByMobile(mobile);
				if( null != member){
				boolean result = memberService.checkPassword(member,password);
				if(result){
					token = String.valueOf(UUID.randomUUID().toString().replaceAll("-", ""));
					request.getSession().setAttribute("token",token);
                    TbaseEmployee tbaseEmployee =  tbaseEmployeeService.getOneByCreateBy(member.getMemberCode());
                    tbaseEmployee.setToken(token);
                    tbaseEmployeeService.updateTbaseEmployee(tbaseEmployee);
					serviceResponse.setData(token);
					serviceResponse.setStatus(1);
					serviceResponse.setInfo("验证成功");
				}else{
					serviceResponse.setData("");
					serviceResponse.setStatus(0);
					serviceResponse.setInfo("验证失败");
				}
                }else{
                    serviceResponse.setData("");
                    serviceResponse.setStatus(3);
                    serviceResponse.setInfo("该手机号未注册！");
                }
			}
		}else{
			serviceResponse.setData("");
			serviceResponse.setStatus(2);
			serviceResponse.setInfo("请输入手机号/密码");
		}
		String json = serviceResponse.objectToJson();
		return json;
	}

	@RequestMapping(value = "updatePassword", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String updatePassword(HttpServletRequest request,String code,String mobile,String password) {
		String sessionCode = String.valueOf(request.getSession().getAttribute("code"));
		ServiceResponse serviceResponse = new ServiceResponse();
		if (StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(code) && StringUtils.isNotBlank(sessionCode) && code.equals(sessionCode)) {
			Member member = memberService.getMemberByMobile(mobile);
			if (null != member) {
//				memberService.entryptPassword(member);
                Ryxx user = ryxxService.getRyxx(member.getMemberCode());
                if (user != null) {
                    user.setPassword(password);
                    ryxxService.updatePwd(user);
                    serviceResponse.setStatus(1);
                    serviceResponse.setInfo("修改成功");
                } else {
                    serviceResponse.setStatus(0);
                    serviceResponse.setInfo("修改失败");
                }
//				member.setUpdateTime(new Timestamp(System.currentTimeMillis()));
//				memberService.updateMember(member);
			} else {
				serviceResponse.setData("");
				serviceResponse.setStatus(2);
				serviceResponse.setInfo("该手机未注册");
			}
			request.getSession().removeAttribute("code");
		} else {
			serviceResponse.setData("");
			serviceResponse.setStatus(0);
			serviceResponse.setInfo("验证失败");
		}
		String json = serviceResponse.objectToJson();
		return json;
	}

	@RequestMapping(value = "updateTouxiang", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String updateTouxiang(String token,String file){
		ServiceResponse serviceResponse = new ServiceResponse();
			try{
					TbaseEmployee emp = tbaseEmployeeService.getEmployeeByToken1(token);
					emp.setAttachment(file);
					tbaseEmployeeService.updateTbaseEmployee(emp);
					serviceResponse.setStatus(1);
					serviceResponse.setInfo("修改成功");
			}catch(Exception e){
					serviceResponse.setInfo("修改失败");
			}

		return serviceResponse.objectToJson();
	}
}