package com.lte.admin.mobile.customer;

import com.alibaba.fastjson.JSONObject;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.entity.CarAttachApply;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.response.ServiceResponse;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.common.utils.MathUtils;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.custom.entity.*;
import com.lte.admin.custom.service.*;
import com.lte.admin.entity.DictType;
import com.lte.admin.mobile.common.ChargeExample;
import com.lte.admin.order.entity.OrderInfo;
import com.lte.admin.other.entity.Activity;
import com.lte.admin.other.entity.ActivityInv;
import com.lte.admin.other.service.ActivityInvService;
import com.lte.admin.other.service.ActivityService;
import com.lte.admin.system.service.DictTypeService;
import com.pingplusplus.model.Charge;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Andy
 */
@Controller
@RequestMapping("mobile/customer")
public class CustomerMobileController extends BaseController {
    @Resource
    private CustomerService customerService;
    @Resource
    private DictTypeService dictTypeService;
    @Resource
    private ActivityInvService activityInvService;
    @Resource
    private ActivityService activityService;
    @Resource
    private CustomerDiscountService customerDiscountService;
    @Resource
    private CustomerCreditCardService customerCreditCardService;
    @Resource
    private CustomerDiscountHoldService customerDiscountHoldService;
    @Resource
    private CustomerBalanceCashService cashService;
    @Resource
    private CustomerBalanceChangeService changeService;

    @RequestMapping(value = "getMyDetail", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String getBalance(String token){
        ServiceResponse serviceResponse = new ServiceResponse();
        try{
            Map<String, Object> map = new HashMap<>();
            map.put("password", token);
            Map<String, Object> detail= customerService.getMyDetail(map);
            if(null != detail){
                serviceResponse.setData(detail);
                serviceResponse.setStatus(1);
                serviceResponse.setInfo("获取成功");
            }else{
                serviceResponse.setData("");
                serviceResponse.setStatus(2);
                serviceResponse.setInfo("获取失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            serviceResponse.setData("");
            serviceResponse.setStatus(0);
            serviceResponse.setInfo("获取异常");
        }
        String json =  serviceResponse.objectToJson();
        return json;
    }

    @RequestMapping(value = "addBalance", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String addBalance(String token,Double money,String channel){
        ServiceResponse serviceResponse = new ServiceResponse();
        try{
            Map<String, Object> map = new HashMap<>();
            map.put("password", token);
            Customer customer= customerService.getOneCustomerByMobile(map);
            if(null != customer){
                String transactionNo = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+(int)(Math.random()*9000+1000);
                Charge charge = ChargeExample.payByPing(channel, money, transactionNo);
                Map<String ,Object> map1 = new HashMap<>();
                map1.put("charge",charge);
                map1.put("transactionNo",transactionNo);
                serviceResponse.setData(map1);
                serviceResponse.setStatus(1);
                serviceResponse.setInfo("获取成功");
            }else{
                serviceResponse.setData("");
                serviceResponse.setStatus(2);
                serviceResponse.setInfo("获取失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            serviceResponse.setData("");
            serviceResponse.setStatus(0);
            serviceResponse.setInfo("获取异常");
        }
        String json =  serviceResponse.objectToJson();
        return json;
    }

    @RequestMapping(value = "addBalanceSuccess", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String addBalanceSuccess(String token,Double money,String transactionNo,String channel){
        ServiceResponse serviceResponse = new ServiceResponse();
        try{
            Map<String, Object> map = new HashMap<>();
            map.put("password", token);
            Customer customer= customerService.getOneCustomerByMobile(map);
            if(null != customer){

                Double oldBalance = customer.getBalance();
                Double newBalance = MathUtils.add(oldBalance,money);
                customer.setBalance(newBalance);
                CustomerBalanceChange balanceChange = new CustomerBalanceChange();
                //余额变更记录单
                balanceChange.setBeforeChange(oldBalance);
                balanceChange.setAfterChange(newBalance);
                balanceChange.setBalanceCustomer(customer.getId());
                balanceChange.setCreateBy(customer.getId());
                balanceChange.setCreateTime(new Timestamp(System.currentTimeMillis()));
                balanceChange.setType(1);//1充值/0支出/2提现
                balanceChange.setChangeMoney(money);
                balanceChange.setTransactionNo(Long.valueOf(transactionNo));
                balanceChange.setPayWay(channel);
                customerService.updateCustomer(customer,balanceChange);
                serviceResponse.setStatus(1);
                serviceResponse.setInfo("成功");
            }else{
                serviceResponse.setData("");
                serviceResponse.setStatus(2);
                serviceResponse.setInfo("获取失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            serviceResponse.setData("");
            serviceResponse.setStatus(0);
            serviceResponse.setInfo("获取异常");
        }
        String json =  serviceResponse.objectToJson();
        return json;
    }


    @RequestMapping(value = "getCustomer", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String getCustomer(String token){
        ServiceResponse serviceResponse = new ServiceResponse();
        try{
            Map<String, Object> map = new HashMap<>();
            map.put("password", token);
            Customer customer= customerService.getOneCustomerByMobile(map);
            serviceResponse.setData(customer);
            serviceResponse.setStatus(1);
            serviceResponse.setInfo("获取成功");
        }catch (Exception e){
            e.printStackTrace();
            serviceResponse.setData("");
            serviceResponse.setStatus(0);
            serviceResponse.setInfo("获取异常");
        }
        return serviceResponse.objectToJson();
    }

    /**
     * 获取姓名和身份证号
     * @param token
     * @return
     */
    @RequestMapping(value = "getCustomerInfo", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String getCustomerInfo(String token){
        ServiceResponse serviceResponse = new ServiceResponse();
        try{
            Map<String, Object> map = new HashMap<>();
            map.put("password", token);
            Map<String, Object> customer= customerService.getCustomerInfo(map);
            serviceResponse.setData(customer);
            serviceResponse.setStatus(1);
            serviceResponse.setInfo("获取成功");
        }catch (Exception e){
            e.printStackTrace();
            serviceResponse.setData("");
            serviceResponse.setStatus(0);
            serviceResponse.setInfo("获取异常");
        }
        return serviceResponse.objectToJson();
    }

    /**
     * 判断实名认证
     * @param token
     * @return
     */
    @RequestMapping(value = "authentication", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String authentication(String token){
        ServiceResponse serviceResponse = new ServiceResponse();
        try{
            Map<String, Object> map = new HashMap<>();
            map.put("password", token);
            Customer customer= customerService.getOneCustomerByMobile(map);
            if(customer.getIdentityCard()!=null){
                serviceResponse.setData(customer);
                serviceResponse.setStatus(1);
                serviceResponse.setInfo("已认证");
            }else{
                serviceResponse.setInfo("未认证");
            }
        }catch (Exception e){
            e.printStackTrace();
            serviceResponse.setInfo("查询异常");
        }
        return serviceResponse.objectToJson();
    }


    /**
     * 实名认证
     * @param token
     * @return
     */
    @RequestMapping(value = "authenticate", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String authenticate(String token,String customerInfo){
        ServiceResponse serviceResponse = new ServiceResponse();
        try{
            Map<String, Object> map = new HashMap<>();
            map.put("password", token);
            Customer customer= customerService.getOneCustomerByMobile(map);
            if(null != customer && null != customerInfo &&  customerInfo!= ""){
                customerInfo = customerInfo.replaceAll("&quot;","\\\"");
                JSONObject jsStr = JSONObject.parseObject(customerInfo);
                customer.setLastTime(new Timestamp(System.currentTimeMillis()));
                customer.setName(jsStr.getString("name"));
                String idCardImg = jsStr.getString("idCardImg");
                String driveCardImg = jsStr.getString("driveCardImg");
                String realImg = jsStr.getString("myImg");
                String cardType = jsStr.getString("cardType");
                Map<String,Object> map1 = new HashMap<>();
                map1.put("idCardImg",idCardImg);
                map1.put("driveCardImg",driveCardImg);
                map1.put("realImg",realImg);
                String jsonImg = new JSONObject().toJSONString(map1);
                customer.setImg(jsonImg);
                customer.setState(1);

                CustomerCredential credential = new CustomerCredential();
                credential.setCreateBy(customer.getId());
                credential.setState(1);
                credential.setCreateTime(new Timestamp(System.currentTimeMillis()));
                credential.setCredentialType(cardType);
                credential.setCredentialCode(jsStr.getString("idCardNo"));
                credential.setCustomer(customer.getId());

                CustomerDrivingLicence drivingLicence = new CustomerDrivingLicence();
                drivingLicence.setCreateBy(customer.getId());
                drivingLicence.setCreateTime(new Timestamp(System.currentTimeMillis()));
                drivingLicence.setState(1);
                String getTime = jsStr.getString("getTime");
                String ableDrive = jsStr.getString("ableDrive");
                String driveNo = jsStr.getString("driveNo");
                drivingLicence.setQuasiDrivingType(ableDrive);
                drivingLicence.setGetTime(new Timestamp(DateUtil.stringToDate(getTime).getTime()));
                drivingLicence.setFileNumber(driveNo);

                customerService.auth(customer,drivingLicence,credential);
                serviceResponse.setStatus(1);
                serviceResponse.setInfo("实名认证成功");
            }else{
                serviceResponse.setStatus(0);
                serviceResponse.setInfo("实名认证失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            serviceResponse.setStatus(2);
            serviceResponse.setInfo("认证异常");
        }
        return serviceResponse.objectToJson();
    }

    /**
     * 获取证件类型
     * @return
     */
    @RequestMapping(value = "getCardType", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String getCardType() {
        ServiceResponse serviceResponse = new ServiceResponse();
        try {
            List<DictType> dictForCredential = dictTypeService.getChildrenByParent("CredentialType");
                serviceResponse.setData(dictForCredential);
                serviceResponse.setStatus(1);
                serviceResponse.setInfo("获取成功");
        } catch (Exception e) {
            e.printStackTrace();
            serviceResponse.setStatus(0);
            serviceResponse.setInfo("获取异常");
        }
        return serviceResponse.objectToJson();
    }
    /**
     * 参加活动
     * @return
     */
    @RequestMapping(value = "joinACT", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String joinACT(String token,String type,Long actId) {
        ServiceResponse serviceResponse = new ServiceResponse();
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("password", token);
            Customer customer = customerService.getOneCustomerByMobile(map);
            ActivityInv actInv = new ActivityInv();

            if(customer!=null){
                actInv.setActivityId(actId);
                actInv.setCreateTime(new Timestamp(System.currentTimeMillis()));
                actInv.setState(0);
                actInv.setActivityInv(customer.getId());
                if("1".equals(type)){
                    //我要租车
                    activityInvService.save(actInv);
                    serviceResponse.setStatus(1);
                    serviceResponse.setInfo("预订成功");
                }else if("2".equals(type)){
                    //我要参加活动
                    activityInvService.save(actInv);
                    serviceResponse.setStatus(1);
                    serviceResponse.setInfo("报名成功");
                }else if("3".equals(type)){
                    //我要领券
                    synchronized (this){
                        actInv.setState(1);
                        Activity activity = activityService.get(actId);
                        CustomerDiscount customerDiscount =customerDiscountService.getOneCustomerDiscount(activity.getCoupon());
                        Long number = customerDiscount.getDiscountNumber();
//                        CustomerDiscountHold customerDiscountHold = new CustomerDiscountHold();
                        Map<String,Object> filter = new HashMap<String,Object>();
                        filter.put("cusId",customer.getId());
                        filter.put("disId",customerDiscount.getId());
//                        CustomerDiscountHold customerDiscountHold =
//                                customerDiscountHoldService.getByCusAndDis(filter);
                        Integer count = customerDiscountHoldService.getByCusAndDis(filter);
                        if(number!=null&&number>0){
                            if(count == 0){
                                customerDiscount.setDiscountNumber(number-1);
                                CustomerDiscountHold customerDiscountHold =new CustomerDiscountHold();
                                customerDiscountHold.setCreateBy(customer.getId());
                                customerDiscountHold.setCreateTime(new Timestamp(System.currentTimeMillis()));
                                customerDiscountHold.setCustomer(customer.getId());
                                customerDiscountHold.setDiscount(activity.getCoupon());
                                customerDiscountHold.setState(1);
//                                boolean result = false;
                                boolean result = activityInvService.save(actInv,customerDiscount,customerDiscountHold);
                                if(result){
                                    serviceResponse.setStatus(1);
                                    serviceResponse.setInfo("领取成功！");
                                }else{
                                    serviceResponse.setInfo("领取异常，请稍后重试！");
                                }
                            }else{
                                serviceResponse.setInfo("该账号已经领取过了！");
                            }
                        }else{
                            serviceResponse.setInfo("领取失败，券已抢光！");
                        }
                    }
                }
            }else{
                serviceResponse.setInfo("用户不存在");
            }
        } catch (Exception e) {
            serviceResponse.setInfo("报名异常，请稍后重试");
        }
        return serviceResponse.objectToJson();
    }
    /**
     * 检查是否参与
     * @return
     */
    @RequestMapping(value = "getACTInvInfo", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String getACTInvInfo(String token,Long actId) {
        ServiceResponse serviceResponse = new ServiceResponse();
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("password", token);
            Customer customer = customerService.getOneCustomerByMobile(map);
            if(customer!=null){
                Map<String, Object> filter = new HashMap<String, Object>();
                filter.put("activityId", actId);
                filter.put("customerId", customer.getId());
                ActivityInv actInv = activityInvService.get(filter);
                serviceResponse.setData(actInv);
                serviceResponse.setStatus(1);
                serviceResponse.setInfo("查询参与信息成功");
            }else{
                serviceResponse.setInfo("用户不存在");
            }
        } catch (Exception e) {
            serviceResponse.setInfo("查询参与信息异常");
        }
        return serviceResponse.objectToJson();
    }
    /**
     * 添加信用卡
     */
    @RequestMapping(value = "addCredit", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String addCredit(String token,HttpServletRequest request) {
        ServiceResponse serviceResponse = new ServiceResponse();
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("password", token);
            Customer customer = customerService.getOneCustomerByMobile(map);
            if(customer!=null){
                CustomerCreditCard creditCard =getCustomerCreditCard(request);
                creditCard.setCustomer(customer.getId());
                creditCard.setCreditState(1);
                String res = customerCreditCardService.saveCustomerCreditCard(creditCard);
                if("success".equals(res)){
                    serviceResponse.setStatus(1);
                    serviceResponse.setInfo("成功添加信用卡");
                }
            }else{
                serviceResponse.setInfo("用户不存在");
            }
        } catch (Exception e) {
            serviceResponse.setInfo("添加信用卡异常");
        }
        return serviceResponse.objectToJson();
    }
    public CustomerCreditCard getCustomerCreditCard(HttpServletRequest request){
        CustomerCreditCard creditCard = new CustomerCreditCard();
        if(StringUtils.isNotBlank(request.getParameter("creditBank"))){
            creditCard.setCreditBank(request.getParameter("creditBank"));
        }
        if(StringUtils.isNotBlank(request.getParameter("creditNo"))){
            creditCard.setCreditNo(request.getParameter("creditNo"));
        }
        if(StringUtils.isNotBlank(request.getParameter("phone"))){
            creditCard.setLinkephone(request.getParameter("phone"));
        }
        if(StringUtils.isNotBlank(request.getParameter("securityCode"))){
            creditCard.setSecurity(request.getParameter("securityCode"));
        }
        if(StringUtils.isNotBlank(request.getParameter("creditBank"))){
            creditCard.setValidityTime(new Timestamp(DateUtil.stringToDate(request.getParameter("validityTime")).getTime()));
        }
        return creditCard;
    }
    /*
查询优惠券列表
*/
    @RequestMapping(value = "getDiscountList",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String getDiscountList(String token,HttpServletRequest request,String pageNum,String pageSize,String availableTime,String allMoney){//available=1-取有效时间，不传就取所有
        ServiceResponse sr = new ServiceResponse();
        try{
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("password", token);
            Customer customer= customerService.getOneCustomerByMobile(map);
            Page<CustomerDiscount> page = getPage(request);

            if(StringUtils.isNotBlank(pageNum) && StringUtils.isNotBlank(pageSize)){
                int num = Integer.parseInt(pageNum);
                int size = Integer.parseInt(pageSize);
                page.setPageNo(num);
                page.setPageSize(size);
            }else{
                page.setPageSize(50);
            }
            if(null != customer){
                Map<String,Object> filter = new HashMap<String,Object>();
                if(StringUtils.isNotBlank(availableTime)){
                    filter.put("availableTime",availableTime);
                    filter.put("state","1");
                    filter.put("cusId",customer.getId());
                    filter.put("disState","1");
                    if(StringUtils.isNotBlank(allMoney)){
                        filter.put("allMoney",Double.parseDouble(allMoney));
                    }
                }else{
                    filter.put("state","0");
                    filter.put("cusId",customer.getId());
                    filter.put("disState","0");
                }
                PageList<Map> dicounts = customerDiscountService.getAllDiscount(page,filter);
                sr.setData(dicounts);
                sr.setInfo("获取优惠券成功");
                sr.setStatus(1);
            }else{
                sr.setInfo("获取用户信息失败");
            }
        }catch(Exception e){
            e.printStackTrace();
            sr.setInfo("获取活动失败");
        }
        return sr.objectToJson();
    }

    /**
     * 用户申请提现
     * @param token
     * @param cashInfo
     * @return
     */
    @RequestMapping(value = "withdrawCash",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String withdrawCash(String token,String cashInfo){
        ServiceResponse respond = new ServiceResponse();
        synchronized(this) {
            try {
                cashInfo = cashInfo.replaceAll("&quot;", "\\\"");
                JSONObject jsStr = JSONObject.parseObject(cashInfo);
                String bankName = jsStr.getString("bankName");
                String bankNum = jsStr.getString("bankNum");
                String money = jsStr.getString("money");

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("password", token);
                Customer customer = customerService.getOneCustomerByMobile(map);

                if (null != customer) {
                    if (StringUtils.isNotBlank(money) && Double.parseDouble(money) >= 100 && customer.getBalance() >= Double.parseDouble(money)) {
                        CustomerBalanceCash cash = new CustomerBalanceCash();
                        cash.setCreateBy(customer.getId());
                        cash.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        cash.setMoney(Double.parseDouble(money));
                        cash.setName(customer.getName());
                        cash.setBank(bankName);
                        cash.setAccountNum(Long.valueOf(bankNum));
                        cash.setState(0);//0-申请提现中1-已提现
                        CustomerBalanceChange balanceChange = new CustomerBalanceChange();
                        //余额变更记录单
                        balanceChange.setBeforeChange(customer.getBalance());
                        balanceChange.setAfterChange(customer.getBalance() - Double.parseDouble(money));
                        balanceChange.setBalanceCustomer(customer.getId());
                        balanceChange.setCreateBy(customer.getId());
                        balanceChange.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        balanceChange.setType(2);//1充值/0租车/2提现
                        balanceChange.setChangeMoney(Double.parseDouble(money));
                        customer.setBalance(customer.getBalance() - Double.parseDouble(money));
                        boolean returnMsg = cashService.createCashInfo(customer, balanceChange, cash);
                        if (returnMsg) {
                            respond.setStatus(1);
                            respond.setInfo("申请提现成功");
                        } else {
                            respond.setStatus(0);
                            respond.setInfo("申请提现失败");
                        }
                    } else {
                        respond.setStatus(4);
                        respond.setInfo("提现金额异常");
                    }

                } else {
                    respond.setData("");
                    respond.setStatus(3);
                    respond.setInfo("提现失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                respond.setData("");
                respond.setStatus(2);
                respond.setInfo("申请提现异常");
            }
        }
        return respond.objectToJson();
    }

    /**
     * 用户申请提现
     * @param token
     * @return
     */
    @RequestMapping(value = "balanceHistory",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String balanceHistory(String token,HttpServletRequest request,String pageNum ,String  pageSize){
        ServiceResponse respond = new ServiceResponse();
        Page<CustomerDiscount> page = getPage(request);
            try {
                if(StringUtils.isNotBlank(pageNum) && StringUtils.isNotBlank(pageSize)){
                    int num = Integer.parseInt(pageNum);
                    int size = Integer.parseInt(pageSize);
                    page.setPageNo(num);
                    page.setPageSize(size);
                }else{
                    page.setPageSize(50);
                }
                Map<String, Object> map = new HashMap<>();
                map.put("password", token);
                Customer customer= customerService.getOneCustomerByMobile(map);

                if(null != customer){
                    Map<String,Object> filter = new HashMap<String,Object>();
                    filter.put("createBy",customer.getId());
                    PageList<Map> history = changeService.getHistory(page,filter);
                    respond.setData(history);
                    respond.setStatus(1);
                    respond.setInfo("查询成功");
                }else{
                    respond.setData("");
                    respond.setStatus(0);
                    respond.setInfo("查询失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                respond.setData("");
                respond.setStatus(0);
                respond.setInfo("查询异常");
            }
        return respond.objectToJson();
    }

}