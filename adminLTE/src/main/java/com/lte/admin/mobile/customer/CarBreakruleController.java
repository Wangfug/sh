package com.lte.admin.mobile.customer;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.entity.CarBreakRule;
import com.lte.admin.car.service.CarBreakRuleService;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.response.ServiceResponse;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.custom.entity.Customer;
import com.lte.admin.custom.service.CustomerService;
import com.lte.admin.other.entity.GovRentInfo;
import com.lte.admin.other.service.GovRentInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 王福桂 on 2017/6/9.
 * 公开的数据
 */
@Controller
@RequestMapping("mobile/customer")
public class CarBreakruleController extends BaseController {
    @Resource
    private CarBreakRuleService carBreakRuleService;
    @Resource
    private CustomerService customerService;

    /**
     * 获取违章信息列表
     */
    @ResponseBody
    @RequestMapping(value = "getCarBreakRuleList", method = {RequestMethod.GET, RequestMethod.POST})
    public String getCarBreakRuleList(String token, HttpServletRequest request,String pageSize,String rows){
        ServiceResponse sr = new ServiceResponse();
        try{
            Map<String,Object> filter = new HashMap<String,Object>();
            filter.put("password",token);
            Customer customer = customerService.getOneCustomerByMobile(filter);
            if(customer!=null){
                Map<String,Object> filter1 = new HashMap<String,Object>();
                Page<CarBreakRule> page = getPage(request);
                page.setOrderBy("last_time");
                page.setOrder("DESC");
                if(pageSize==null&&rows==null){
                    page.setPageNo(1);
                    page.setPageSize(20);
                }
                PageList<Map> page1 = carBreakRuleService.getDetailList(page,filter1);
                int count = 0;
                if(page1!=null)
                    count= page1.size();
                sr.setData(page1);
                sr.setInfo("成功获取"+count+"条违章记录");
                sr.setStatus(1);
            }else{
                sr.setInfo("获取用户信息失败！");
            }
        }catch(Exception e){
            sr.setInfo("添加政企租车出现异常！");
        }
        return sr.objectToJson();
    }
    /**
     * 获取政企信息
     * @param token
     */
    @ResponseBody
    @RequestMapping(value = "getCarBreakRule", method = {RequestMethod.GET, RequestMethod.POST})
    public String getCarBreakRule(String token,String id){
        ServiceResponse sr = new ServiceResponse();
        try{
            Map<String,Object> filter = new HashMap<String,Object>();
            filter.put("password",token);
            Customer customer = customerService.getOneCustomerByMobile(filter);
            if(customer!=null){
                Map<String,Object> carBreakRule = carBreakRuleService.getOnecarBreakRuleDetail(Long.valueOf(id));
                sr.setData(carBreakRule);
                sr.setInfo("成功获取一条违章记录");
                sr.setStatus(1);
            }else{
                sr.setInfo("获取用户信息失败！");
            }
        }catch(Exception e){
            sr.setInfo("添加政企租车出现异常！");
        }
        return sr.objectToJson();
    }
}
