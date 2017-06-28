package com.lte.admin.mobile.customer;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.response.ServiceResponse;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.custom.entity.Customer;
import com.lte.admin.custom.service.CustomerService;
import com.lte.admin.order.entity.OrderBill;
import com.lte.admin.order.service.OrderBillService;
import com.lte.admin.other.entity.Activity;
import com.lte.admin.other.service.ActivityService;
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

/**
 * Created by 王福桂 on 2017/6/9.
 * 公开的数据
 */
@Controller
@RequestMapping("mobile/customer")
public class InvoiceController extends BaseController {
    @Resource
    private OrderBillService orderBillService;@Resource
    private CustomerService customerService;

    @RequestMapping(value = "addInvoiceModel", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String addInvoiceModel(String id,String token,String invoiceType,String title,String phone,String bank,String bankAccount,
                                  String taxpayerIdentNum,String name,String address,String area){
        ServiceResponse serviceResponse = new ServiceResponse();
        try{
            Map<String, Object> map = new HashMap<>();
            map.put("password", token);
            Customer customer= customerService.getOneCustomerByMobile(map);
            OrderBill orderBill = null;
            if(null != customer){
                if(StringUtils.isNotBlank(id)){
                    Long idIn = Long.valueOf(id);
                    orderBill = orderBillService.get(idIn);
                    orderBill.setLastBy(customer.getId());
                    orderBill.setLastTime(new Timestamp(System.currentTimeMillis()));
                }else{
                    orderBill = new OrderBill();
                    orderBill.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    orderBill.setState(1);
                    orderBill.setCreateBy(customer.getId());
                    orderBill.setLastBy(customer.getId());
                    orderBill.setLastTime(new Timestamp(System.currentTimeMillis()));
                }
                //所属用户
                orderBill.setCustomerId(customer.getId());
                //发票类型
                orderBill.setBillType(invoiceType);
                //抬头
                orderBill.setBillTitle(title);
                //电话
                orderBill.setLinkphone(phone);
                //开户银行名称
                orderBill.setDepositBank(bank);
                //开户银行号码
                orderBill.setBankAccount(bankAccount);
                //纳税人识别号
                orderBill.setTaxpayerCode(taxpayerIdentNum);
                //纳税人名称
                orderBill.setAddresseeName(name);
                //地址
                orderBill.setAddress(address);
                //城市
                orderBill.setArea(area);
                if(orderBill.getId()!=null){
                    orderBillService.update(orderBill);
                    serviceResponse.setInfo("修改发票模板成功");
                }else{
                    orderBillService.save(orderBill);
                    serviceResponse.setInfo("添加发票模板成功");
                }
                serviceResponse.setStatus(1);
            }else{
                serviceResponse.setInfo("获取用户信息失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            serviceResponse.setInfo("获取异常");
        }
        return serviceResponse.objectToJson();
    }

    @RequestMapping(value = "getInvoiceModelList", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String getInvoiceModelList(String token,HttpServletRequest request){
        ServiceResponse serviceResponse = new ServiceResponse();
        try{
            Map<String, Object> map = new HashMap<>();
            map.put("password", token);
            Customer customer= customerService.getOneCustomerByMobile(map);
            Page<OrderBill> page = getPage(request);
            page.setOrderBy("create_time");
            page.setOrder("Desc");
            if(null != customer){
                Map<String,Object> filter = new HashMap<String,Object>();
//                filter.put("state","1");
                filter.put("cusId",customer.getId());
                filter.put("readonly","0");
                PageList<OrderBill> invoices = orderBillService.getList(page,filter);
                serviceResponse.setData(invoices);
                serviceResponse.setInfo("获取多条发票成功");
                serviceResponse.setStatus(1);
            }else{
                serviceResponse.setInfo("获取用户信息失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            serviceResponse.setInfo("获取异常");
        }
        return serviceResponse.objectToJson();
    }

    @RequestMapping(value = "getOneInvoiceModel", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String getOneInvoiceModel(String token,String id){
        ServiceResponse serviceResponse = new ServiceResponse();
        try{
            Map<String, Object> map = new HashMap<>();
            map.put("password", token);
            Customer customer= customerService.getOneCustomerByMobile(map);
            OrderBill orderBill = null;
            if(null != customer){
                orderBill = orderBillService.get(Long.valueOf(id));
                serviceResponse.setData(orderBill);
                serviceResponse.setInfo("获取发票成功");
                serviceResponse.setStatus(1);
            }else{
                serviceResponse.setInfo("获取用户信息失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            serviceResponse.setInfo("获取异常");
        }
        return serviceResponse.objectToJson();
    }
/**
 * 删除发票
 */
@RequestMapping(value = "deleteOneInvoiceModel", method = {RequestMethod.GET,RequestMethod.POST})
@ResponseBody
public String deleteOneInvoiceModel(String token,String id){
    ServiceResponse serviceResponse = new ServiceResponse();
    try{
        Map<String, Object> map = new HashMap<>();
        map.put("password", token);
        Customer customer= customerService.getOneCustomerByMobile(map);
        OrderBill orderBill = null;
        if(null != customer){
            orderBill = orderBillService.get(Long.valueOf(id));
            if(customer.getId().equals(orderBill.getCustomerId())){
                orderBillService.deleteById(Long.valueOf(id));
                serviceResponse.setInfo("删除成功");
                serviceResponse.setStatus(1);
            }else{
                serviceResponse.setInfo("不允许删除他人的发票");
            }
        }else{
            serviceResponse.setInfo("获取用户信息失败");
        }

    }catch (Exception e){
        e.printStackTrace();
        serviceResponse.setInfo("删除异常");
    }
    return serviceResponse.objectToJson();
}


}
