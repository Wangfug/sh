package com.lte.admin.mobile.customer;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.response.ServiceResponse;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.custom.entity.Customer;
import com.lte.admin.custom.service.CustomerService;
import com.lte.admin.order.entity.OrderBill;
import com.lte.admin.order.service.OrderBillService;
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
public class GovAndBusRentController extends BaseController {
    @Resource
    private GovRentInfoService govRentInfoService;
    @Resource
    private CustomerService customerService;

    /**
     * 新增政企信息
     * @param token
     * @param id
     * @param comName
     * @param contacts
     * @param phone
     * @param businessType
     * @param city
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addGovRentInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public String addGovRentInfo(String token,String id,String comName,String contacts,String phone,String businessType,
                                    String city){
        ServiceResponse sr = new ServiceResponse();
        try{
            Map<String,Object> filter = new HashMap<String,Object>();
            filter.put("password",token);
            Customer customer = customerService.getOneCustomerByMobile(filter);
            if(customer!=null){
                GovRentInfo govRentInfo = null;
                if(StringUtils.isNotEmpty(id)){
                    govRentInfo = govRentInfoService.get(Long.valueOf(id));
                }else{
                    govRentInfo = new GovRentInfo();
                    govRentInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    govRentInfo.setState(1);
                }
                if(govRentInfo!=null){
                    govRentInfo.setLastTime(new Timestamp(System.currentTimeMillis()));
                    govRentInfo.setComName(comName);
                    govRentInfo.setContacts(contacts);
                    govRentInfo.setContactsPhone(phone);
                    govRentInfo.setBusinessType(businessType);
                    govRentInfo.setCity(city);
                    govRentInfo.setCustomer(customer.getId());
                    if(govRentInfo.getId()!=null){
                        govRentInfoService.update(govRentInfo);
                        sr.setInfo("修改成功 ！");
                    }else{
                        govRentInfoService.save(govRentInfo);
                        sr.setData(govRentInfo.getId());
                        sr.setInfo("添加成功！");
                    }
                    sr.setStatus(1);
                }else{
                    sr.setInfo("获取政企信息失败！");
                }
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
    @RequestMapping(value = "getGovRentInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public String getGovRentInfo(String token){
        ServiceResponse sr = new ServiceResponse();
        try{
            Map<String,Object> filter = new HashMap<String,Object>();
            filter.put("password",token);
            Customer customer = customerService.getOneCustomerByMobile(filter);
            if(customer!=null){
                GovRentInfo govRentInfo = govRentInfoService.getByCustomer(customer.getId());
                if(govRentInfo!=null){
                    sr.setStatus(1);
                    sr.setData(govRentInfo);
                    sr.setInfo("获取政企租车信息成功！");
                }else{
                    sr.setInfo("没有找到政企租车信息！");
                }
            }else{
                sr.setInfo("获取用户信息失败！");
            }
        }catch(Exception e){
            sr.setInfo("添加政企租车出现异常！");
        }
        return sr.objectToJson();
    }
}
