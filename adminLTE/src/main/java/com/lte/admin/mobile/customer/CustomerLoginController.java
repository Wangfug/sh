package com.lte.admin.mobile.customer;

import com.lte.admin.common.response.ServiceResponse;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.custom.entity.Customer;
import com.lte.admin.custom.service.CustomerService;
import com.lte.admin.order.service.OrderInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Andy
 */
@Controller
@RequestMapping("mobile/customer")
public class CustomerLoginController extends BaseController {
    @Resource
    private CustomerService customerService;

    /**
     * 验证登录
     */
    @RequestMapping(value = "login", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String login(HttpServletRequest request, String code, String mobile) {
        String sessionCode = String.valueOf(request.getSession().getAttribute("code"));
        ServiceResponse serviceResponse = new ServiceResponse();
        if (StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(code) && StringUtils.isNotBlank(sessionCode) && code.equals(sessionCode)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("mobilePhone", mobile);
            Customer customer = customerService.getOneCustomerByMobile(map);
            String token = "";
            if (null == customer) {
                Customer customer1 = new Customer();
                customer1.setCreateTime(new Timestamp(System.currentTimeMillis()));
                customer1.setState(0);
                customer1.setMobilePhone(mobile);
                token = String.valueOf(UUID.randomUUID().toString().replaceAll("-", ""));
                customer1.setPassword(token);//token
                request.getSession().setAttribute("token", token);
                customerService.saveCustomer(customer1);
            } else {
                customer.setLastTime(new Timestamp(System.currentTimeMillis()));
                token = String.valueOf(UUID.randomUUID().toString().replaceAll("-", ""));
                customer.setPassword(token);//token
                request.getSession().setAttribute("token", token);
                customerService.updateCustomer(customer);
            }
            request.getSession().removeAttribute("code");
            serviceResponse.setData(token);
            serviceResponse.setStatus(1);
            serviceResponse.setInfo("验证成功");
        } else {
            serviceResponse.setData("");
            serviceResponse.setStatus(0);
            serviceResponse.setInfo("验证失败");
        }
        String json = serviceResponse.objectToJson();
        return json;
    }
}