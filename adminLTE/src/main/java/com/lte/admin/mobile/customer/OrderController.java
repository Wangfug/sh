package com.lte.admin.mobile.customer;

import com.alibaba.fastjson.JSONObject;
import com.lte.admin.car.entity.Car;
import com.lte.admin.car.entity.CarOccupy;
import com.lte.admin.car.service.CarRentPriceService;
import com.lte.admin.car.service.CarService;
import com.lte.admin.car.service.CarShopsService;
import com.lte.admin.common.consts.InvoiceEnum;
import com.lte.admin.common.consts.OrderWayEnum;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.response.ServiceResponse;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.common.utils.MathUtils;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.custom.entity.*;
import com.lte.admin.custom.service.CustomerDiscountHoldService;
import com.lte.admin.custom.service.CustomerDiscountService;
import com.lte.admin.custom.service.CustomerService;
import com.lte.admin.custom.service.TbaseEmployeeService;
import com.lte.admin.entity.DictType;
import com.lte.admin.mobile.common.ChargeExample;
import com.lte.admin.mobile.common.PushExample;
import com.lte.admin.order.entity.*;
import com.lte.admin.order.service.OrderBillService;
import com.lte.admin.order.service.OrderEvaluateService;
import com.lte.admin.order.service.OrderInfoService;
import com.lte.admin.order.service.OrderWorkService;
import com.lte.admin.system.service.DictTypeService;
import com.pingplusplus.Pingpp;
import com.pingplusplus.model.Charge;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Andy
 */
@Controller
@RequestMapping("mobile/customer")
public class OrderController extends BaseController {
    @Resource
    private OrderInfoService orderInfoService;
    @Resource
    private CustomerService customerService;
    @Resource
    private CarShopsService carShopsService;
    @Resource
    private CarService carService;
    @Resource
    private CarRentPriceService carRentPriceService;
    @Resource
    private CustomerDiscountService customerDiscountService;
    @Resource
    private CustomerDiscountHoldService customerDiscountHoldService;
    @Resource
    private DictTypeService dictTypeService;
    @Resource
    private CarShopsService carShopService;
    @Resource
    private OrderWorkService orderWorkService;
    @Resource
    private TbaseEmployeeService tbaseEmployeeService;
    @Resource
    private OrderEvaluateService evaluateService;
    @Resource
    private OrderBillService billService;

    @Resource
    private CustomerDiscountHoldService holdService;

    @RequestMapping(value = "getOrderList", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String getOrderList(String token, String pageNum, String pageSize) {
        ServiceResponse serviceResponse = new ServiceResponse();
        try {
            Page<OrderInfo> page = new Page<>();
            if (StringUtils.isNotBlank(pageNum) && StringUtils.isNotBlank(pageSize)) {
                int num = Integer.parseInt(pageNum);
                int size = Integer.parseInt(pageSize);
                page.setPageNo(num);
                page.setPageSize(size);
            }
            Map<String, Object> filters = new HashMap<>();
            filters.put("token", token);
            List<Map> page1 = orderInfoService.getListByCustomer(page, filters);
            if (null != page1 && page1.size() > 0) {
                for (Map map : page1) {
                    String way = map.get("way").toString();
                    if (way.equals(OrderWayEnum.BYSELF.getCode())) {
                        map.put("way", OrderWayEnum.BYSELF.getName());
                    } else if (way.equals(OrderWayEnum.BYSHOP.getCode())) {
                        map.put("way", OrderWayEnum.BYSHOP.getName());
                    }
                }
            }
            serviceResponse.setData(page1);
            serviceResponse.setStatus(1);
            serviceResponse.setInfo("获取成功");
        } catch (Exception e) {
            e.printStackTrace();
            serviceResponse.setData("");
            serviceResponse.setStatus(0);
            serviceResponse.setInfo("获取失败");
        }
        String json = serviceResponse.objectToJson();
        return json;
    }

    @RequestMapping(value = "getOrderDetail", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String getOrderDetail(String orderNo) {
        ServiceResponse serviceResponse = new ServiceResponse();
        try {
                Map<String,Object> order =  orderInfoService.getDetailByOrderNo(orderNo);
                serviceResponse.setData(order);
                serviceResponse.setStatus(1);
                serviceResponse.setInfo("获取成功");
        } catch (Exception e) {
            e.printStackTrace();
            serviceResponse.setStatus(0);
            serviceResponse.setInfo("获取失败");
        }
        String json = serviceResponse.objectToJson();
        return json;
    }

    /**
     * 创建订单
     *
     * @param token
     * @param req
     * @return
     */
    @RequestMapping(value = "createOrder", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String createOrder(String token, HttpServletRequest req) {
        ServiceResponse serviceResponse = new ServiceResponse();
        try {
            //生成订单
            SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
            //客户号
            Map<String, Object> filter = new HashMap<>();
            filter.put("password", token);
            Long customer = customerService.getOneCustomerByMobile(filter).getId();
            orderInfo.setCustomer(customer);
            //预订/实际取车时间
            List<DictType> dictBeans = dictTypeService.getChildrenByParent("CarTimeSet");
            int setTime = 0;
            if (dictBeans != null && dictBeans.size() > 0) {
                setTime = Integer.valueOf(dictBeans.get(0).getName());
            }
//			Timestamp timeBeginForReserve = new Timestamp(DateUtil.stringToDate(req.getParameter("timeBeginForReserve")).getTime());
            Long time1 = DateUtil.stringToDate("2017-05-05 15:00:00").getTime() - setTime * 60 * 60 * 1000;
            Timestamp timeBeginForReserve = new Timestamp(time1);

            //预订/实际还车时间
//			Timestamp timeEndForReserve = new Timestamp(DateUtil.stringToDate(req.getParameter("timeEndForReserve")).getTime());
            Long time2 = DateUtil.stringToDate("2017-05-06 14:00:00").getTime() + setTime * 60 * 60 * 1000;
            Timestamp timeEndForReserve = new Timestamp(time2);
            //支付时间
//			Timestamp timeForPay = new Timestamp(DateUtil.stringToDate(req.getParameter("timeForPay")).getTime());
            //支付方式
            String payWay = req.getParameter("payWay");
            //支付账号
            String payAccount = req.getParameter("payAccount");
            //品牌型号
            String reserveCarType = req.getParameter("reserveCarType");
            //实际交易车辆
//			Long carId = 0l;
            //实际车型
//			String realCarType = req.getParameter("realCarType");
            //实际支付金额
//			Double realPay = Double.valueOf(req.getParameter("reserveCarType"));
            //费用总额
//			Double totalMoney = Double.valueOf(req.getParameter("totalMoney"));
            //最终费用
            Long finalFee = 0l;
            //订单号
//			String orderNo =  sdf.format(new Date())+orderInfoService.getOrderNo();

            //根据选择条件获取车辆
            //车辆品牌
            String brand = "宝马";
            //车辆型号
            String model = "B2";
            Map<String, Object> filters = new HashMap<String, Object>();
            filters.put("model", model);//型号
            filters.put("brand", brand);//品牌
            filters.put("reserveTimeStart", timeBeginForReserve);//预订开始时间 timeBeginForReserve
            filters.put("reserveTimeEnd", timeEndForReserve);//预订结束时间 timeEndForReserve
            Long carId = carService.getCarByFilter(filters);
            CarOccupy occupy = new CarOccupy();
            occupy.setCreateTime(new Timestamp(System.currentTimeMillis()));
            occupy.setState(0);
            if (carId == 0) {
                //回滚善变的操作
                serviceResponse.setData("");
                serviceResponse.setStatus(0);
                serviceResponse.setInfo("库存没有该种车型，创建订单失败");
            } else {
//				Occ
                serviceResponse.setData("");
                serviceResponse.setStatus(1);
                serviceResponse.setInfo("创建订单成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            serviceResponse.setData("");
            serviceResponse.setStatus(0);
            serviceResponse.setInfo("获取失败");
        }
        return serviceResponse.objectToJson();
    }

    /**
     * 车型确认
     *
     * @return
     */
    @RequestMapping(value = "validateCar", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String validateCar(String carInfo) {//getWay取车方式：1-门店取车/0上门送车;isDeductible:1选择了不计免赔/0：未选择
        ServiceResponse serviceResponse = new ServiceResponse();

        carInfo = carInfo.replaceAll("&quot;", "\\\"");
        JSONObject jsStr = JSONObject.parseObject(carInfo);
        String brand = jsStr.getString("carBrand");
        String model = jsStr.getString("carModel");
        String shopId = jsStr.getString("shopId");
        String days = jsStr.getString("days");
        String getWay = jsStr.getString("getWay");
        String isDeductible = jsStr.getString("isDeductible");
        String couponId = jsStr.getString("couponId");

        if (StringUtils.isNotBlank(brand) && StringUtils.isNotBlank(model) && StringUtils.isNotBlank(days)) {
            try {
                Map<String, Object> map = calculateMoney(brand, model, shopId, days, getWay, isDeductible, couponId);
                serviceResponse.setData(map);
                serviceResponse.setStatus(1);
                serviceResponse.setInfo("获取成功");
            } catch (Exception e) {
                e.printStackTrace();
                serviceResponse.setData("");
                serviceResponse.setStatus(0);
                serviceResponse.setInfo("获取失败");
            }
        } else {
            serviceResponse.setData("");
            serviceResponse.setStatus(2);
            serviceResponse.setInfo("传参失败");
        }
        String json = serviceResponse.objectToJson();
        return json;
    }


    /**
     * 订单确认
     *
     * @return
     */
    @RequestMapping(value = "validateOrder", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String validateOrder(String token, String carInfo) {//getWay取车方式：1-门店取车/0上门送车;isDeductible:1选择了不计免赔/0：未选择
        ServiceResponse serviceResponse = new ServiceResponse();

        carInfo = carInfo.replaceAll("&quot;", "\\\"");
        JSONObject jsStr = JSONObject.parseObject(carInfo);
        String brand = jsStr.getString("carBrand");
        String model = jsStr.getString("carModel");
        String shopId = jsStr.getString("shopId");
        String shopId2 = jsStr.getString("shopId2");
        String days = jsStr.getString("days");
        String getWay = jsStr.getString("getWay");
        String isDeductible = jsStr.getString("isDeductible");
        String startTime = jsStr.getString("startTime");
        String endTime = jsStr.getString("endTime");
        String city = jsStr.getString("city");
        String getAdreess = jsStr.getString("addrTake");
        String rerurnAdreess = jsStr.getString("addrBack");
        String isGetBill = jsStr.getString("isGetBill");

        String lonTake = jsStr.getString("lonTake");
        String latTake = jsStr.getString("latTake");
        String lonBack = jsStr.getString("lonBack");
        String latBack = jsStr.getString("latBack");
        String couponId = jsStr.getString("couponId");
        String holdId = jsStr.getString("holdId");

        if (StringUtils.isNotBlank(brand) && StringUtils.isNotBlank(model) && StringUtils.isNotBlank(days) && StringUtils.isNotBlank(city) && StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            try {
                Map<String, Object> filter = new HashMap();
                Car car = null;
                filter.put("brand", brand);
                filter.put("model", model);
                filter.put("startTime", startTime);
                filter.put("endTime", endTime);

                List<Car> cars = null;
                List<Map> shopTake = new ArrayList<>();
                if (OrderWayEnum.BYSHOP.getCode().equals(getWay) && StringUtils.isNotBlank(lonTake) && StringUtils.isNotBlank(latTake)) {//上门送取，选择比较近门店
                    Map<String, String> coordinate = new HashMap();
                    coordinate.put("lon", lonTake);
                    coordinate.put("lat", latTake);
                    coordinate.put("city", city);
                    shopTake = carShopsService.getNearestShop(coordinate);

                    for (int a = 0; a < shopTake.size(); a++) {
                        shopId = shopTake.get(a).get("id") + "";//依次寻找近的门店
                        filter.put("shopId", shopId);
                        cars = carService.getCarsByShopId(filter);
                        if (null != cars && cars.size() > 0) break;
                    }
                    if (null != cars && cars.size() > 0) {//如果该门店有车
                        car = cars.get(0);
                    }
                    Map<String, String> coordinate1 = new HashMap();
                    coordinate1.put("lon", lonBack);
                    coordinate1.put("lat", latBack);
                    coordinate1.put("city", city);
                    List<Map> list1 = carShopsService.getNearestShop(coordinate);
                    if (null != list1.get(0)) {
                        shopId2 = list1.get(0).get("id") + "";//最近的送车门店
                    }
                } else if (OrderWayEnum.BYSELF.getCode().equals(getWay)) {//门店取

                    filter.put("shopId", shopId);
                    cars = carService.getCarsByShopId(filter);
                    if (null != cars && cars.size() > 0) {//如果该门店有车
                        car = cars.get(0);
                    } else {//如果该门店没车，去查询该城市是否有车
                        filter.put("city", city);
                        cars = carService.getCarsByCity(filter);
                        if (null != cars && cars.size() > 0) {//如果该城市有车
                            car = cars.get(0);
                        }
                    }

                }

                if (null != car) {//查询到了车
                    Map<String, Object> map = calculateMoney(brand, model, shopId, days, getWay, isDeductible, couponId);
                    Map<String, Object> filters = new HashMap<>();
                    filters.put("password", token);
                    Customer customer = customerService.getOneCustomerByMobile(filters);
                    //-------订单表
                    OrderInfo orderInfo = new OrderInfo();
                    orderInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    orderInfo.setState(10001);
                    orderInfo.setCustomer(customer.getId());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
                    String orderNo = sdf.format(new Date()) + orderInfoService.getOrderNo();
                    orderInfo.setOrderNo(orderNo);

                    orderInfo.setTotalFee(Double.parseDouble(map.get("allMoney").toString()));//订单总金额
                    double realMoney = Double.parseDouble(map.get("realMoney").toString());

                    orderInfo.setRealPay(realMoney);//实际支付金额
                    orderInfo.setRealTimeStart(new java.sql.Timestamp(DateUtil.stringToDate(startTime).getTime()));
                    orderInfo.setReserveTimeStart(new java.sql.Timestamp(DateUtil.stringToDate(startTime).getTime()));
                    orderInfo.setRealTimeEnd(new java.sql.Timestamp(DateUtil.stringToDate(endTime).getTime()));
                    orderInfo.setReserveTimeEnd(new java.sql.Timestamp(DateUtil.stringToDate(endTime).getTime()));
                    orderInfo.setRealCarType(brand + model);
                    orderInfo.setReserveType(brand + model);
                    orderInfo.setCarId(car.getId());
                    orderInfo.setDeleteFlag(0);
                    if (OrderWayEnum.BYSELF.getCode().equals(getWay)) {//门店取还
                        orderInfo.setWay(OrderWayEnum.BYSELF.getCode());//门店
                        orderInfo.setCarShopGet(Long.valueOf(shopId));
                        orderInfo.setCarShopReturn(Long.valueOf(shopId2));
                    } else if (OrderWayEnum.BYSHOP.getCode().equals(getWay)) {//上门送取
                        orderInfo.setWay(OrderWayEnum.BYSHOP.getCode());
                        orderInfo.setCarShopGet(Long.valueOf(shopId));
                        orderInfo.setCarShopReturn(Long.valueOf(shopId2));
                        orderInfo.setAddressGet(getAdreess);
                        orderInfo.setAddressReturn(rerurnAdreess);
                    }
                    OrderBill newBill = null;
                    if ("1".equals(isGetBill)) {//开发票
                        orderInfo.setInvoice(InvoiceEnum.INVOICE.getCode());
                        String billId = jsStr.getString("billId");
                        if(StringUtils.isNotBlank(billId)){
                            OrderBill bill = billService.get(Long.parseLong(billId));
                            newBill = new OrderBill();
                            newBill.setCreateBy(customer.getId());
                            newBill.setCreateTime(new Timestamp(System.currentTimeMillis()));
                            newBill.setState(0);//0未寄出，1-已寄出
                            newBill.setBillType(bill.getBillType());
                            newBill.setBillTitle(bill.getBillTitle());
                            newBill.setAddress(bill.getAddress());
                            newBill.setLinkphone(bill.getLinkphone());
                            newBill.setArea(bill.getArea());
                            newBill.setTaxpayerCode(bill.getTaxpayerCode());
                            newBill.setDepositBank(bill.getDepositBank());
                            newBill.setBankAccount(bill.getBankAccount());
                            newBill.setAddresseeName(bill.getAddresseeName());
                            newBill.setOrderNo(orderNo);
                            newBill.setCustomerId(customer.getId());
                            newBill.setReadonly(1);//1-用户开具需要寄出的发票，0-模板
                        }
                    } else {//不开发票
                        orderInfo.setInvoice(InvoiceEnum.NOT_INVOICE.getCode());
                    }

                    //----车辆占用表
                    CarOccupy occupy = new CarOccupy();
                    occupy.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    occupy.setTimeStart(new java.sql.Timestamp(DateUtil.stringToDate(startTime).getTime()));
                    occupy.setTimeEnd(new java.sql.Timestamp(DateUtil.stringToDate(endTime).getTime()));
                    occupy.setCarId(car.getId());
                    occupy.setCreateBy(customer.getId());
                    occupy.setState(1);//1-有效 / 0-无效
                    occupy.setOrderNo(orderInfo.getOrderNo());

                    //-------订单费用表
                    OrderFee fee = new OrderFee();
                    fee.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    fee.setCarRentFee(Double.parseDouble(map.get("dayPrice").toString()));
                    fee.setSendCarFee(Double.parseDouble(map.get("sendCarMoney").toString()));
                    fee.setBaseFee(Double.parseDouble(map.get("feeInsurance").toString()));
                    fee.setAdditionalBujimianpei(Double.parseDouble(map.get("deductibleFee").toString()));
                    fee.setHandingCharge(Double.parseDouble(map.get("counterFee").toString()));//手续费
                    fee.setTotalFee(Double.parseDouble(map.get("allMoney").toString()));
                    fee.setOrderNo(orderInfo.getOrderNo());
                    fee.setCreateBy(customer.getId());
                    List<DictType> authorizationFee = dictTypeService.getChildrenByParent("AuthorizationFee");//预授权费用
                    double authorization = 0;
                    if (authorizationFee != null && authorizationFee.size() > 0) {
                        authorization = Double.parseDouble(authorizationFee.get(0).getName());
                    }
                    fee.setPreAuthorized(authorization);
                    CustomerDiscountHold hold =null;
                    if(null!=holdId && holdId !=""){
                        hold = customerDiscountHoldService.getOneCustomerDiscountHold(Long.parseLong(holdId));
                        hold.setOrderNo(orderInfo.getOrderNo());
                    }
                    boolean rsul = orderInfoService.create(orderInfo, occupy, fee,hold,newBill);
                    if (rsul) {
                        Map<String, Object> map2 = new HashMap();
                        map2.put("allMoney", map.get("allMoney").toString());
                        map2.put("realMoney", map.get("realMoney").toString());
                        map2.put("orderNo", orderInfo.getOrderNo());
                        serviceResponse.setData(map2);
                        serviceResponse.setStatus(1);
                        serviceResponse.setInfo("下单成功");
                    } else {
                        serviceResponse.setStatus(2);
                        serviceResponse.setInfo("下单失败");
                    }

                } else {//该城市没车，下单失败
                    serviceResponse.setData("");
                    serviceResponse.setStatus(0);
                    serviceResponse.setInfo("该城市暂无该车型");
                }

            } catch (Exception e) {
                e.printStackTrace();
                serviceResponse.setData("");
                serviceResponse.setStatus(2);
                serviceResponse.setInfo("获取失败");
            }
        } else {
            serviceResponse.setData("");
            serviceResponse.setStatus(3);
            serviceResponse.setInfo("获取失败");
        }
        String json = serviceResponse.objectToJson();
        return json;
    }

    public Map calculateMoney(String brand, String model, String shopId, String days, String getWay, String isDeductible, String couponId) throws Exception {
        double getDay = Double.parseDouble(days);
        Map<String, Object> filters = new HashMap<>();
        filters.put("brand", brand);
        filters.put("model", model);
        if (null != shopId && shopId != "") {
            filters.put("shopId", shopId);
        }
        Map carRent = carRentPriceService.getDeatilByModels(filters);
        Map<String, Object> map = new HashMap<>();
        if (null != carRent && !carRent.isEmpty()) {
            double dayPrice = Double.parseDouble(carRent.get("day_price_shop").toString());
            dayPrice = dayPrice * getDay;//租车费

            double feeInsurance = Double.parseDouble(carRent.get("fee_insurance").toString());
            feeInsurance = feeInsurance * getDay;//基本保险费

            double deductibleFee = 0;
            if ("1".equals(isDeductible)) {//如果选择了不计免陪
                deductibleFee = Double.parseDouble(carRent.get("fee_deductible").toString());
                deductibleFee = deductibleFee * getDay;//不计免赔
            }

            List<DictType> fee1 = dictTypeService.getChildrenByParent("CounterFee");//手续费
            double counterFee = 0;
            if (fee1 != null && fee1.size() > 0) {
                counterFee = Double.parseDouble(fee1.get(0).getName());
                counterFee = counterFee * getDay;
            }

            double sendCarMoney = 0;
            if (OrderWayEnum.BYSHOP.getCode().equals(getWay)) {//如果是上门送车，需要送车服务费
                List<DictType> money = dictTypeService.getChildrenByParent("SendCarMoney");
                if (money != null && money.size() > 0) {
                    sendCarMoney = Double.parseDouble(money.get(0).getName());
                }
            }
            double allMoney = dayPrice + feeInsurance + deductibleFee + counterFee + sendCarMoney;
            ;
            double realMoney = allMoney;
            if (null != couponId && !"".equals(couponId)) {
                CustomerDiscount discount = customerDiscountService.getOneCustomerDiscount(Long.parseLong(couponId));
                if (null != discount && (discount.getMinimumConsumption() <= allMoney)) {
                    realMoney = allMoney - discount.getDiscountMoney();
                    if (realMoney <= 0) {
                        realMoney = 0;
                    }
                    map.put("discountMoney", discount.getDiscountMoney());
                }
            } else {
                map.put("discountMoney", 0);
            }
            map.put("realMoney", realMoney);
            map.put("dayPrice", dayPrice);//车辆租赁费
            map.put("feeInsurance", feeInsurance);//基本保险费
            map.put("deductibleFee", deductibleFee);//不计免赔非
            map.put("counterFee", counterFee);//手续费
            map.put("sendCarMoney", sendCarMoney);//送车服务费
            map.put("allMoney", allMoney);
        }
        return map;
    }

    /**
     * 创建工单
     *
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "createOrderWork", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String createOrderWork(String brand, String model, String shopId, String days, String startTime, String endTime) {
        ServiceResponse serviceResponse = new ServiceResponse();
        try {
            //
            Map<String, Object> filters = new HashMap<>();
            if (StringUtils.isNotBlank(brand)) {
                filters.put("brand", brand);
            }
            if (StringUtils.isNotBlank(model)) {
                filters.put("model", model);
            }
            if (StringUtils.isNotBlank(shopId)) {
                filters.put("shopId", Long.valueOf(shopId));
            }
            if (StringUtils.isNotBlank(brand)) {
                filters.put("brand", brand);
            }
            if (StringUtils.isNotBlank(brand)) {
                filters.put("brand", brand);
            }
            serviceResponse.setData("");
            serviceResponse.setStatus(1);
            serviceResponse.setInfo("获取成功");
        } catch (Exception e) {
            e.printStackTrace();
            serviceResponse.setData("");
            serviceResponse.setStatus(0);
            serviceResponse.setInfo("获取失败");
        }
        return serviceResponse.objectToJson();
    }

    /**
     * 支付
     */
    @RequestMapping(value = "payMoney", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String payMoney(String orderNo, String payWay, String token) {
        ServiceResponse serviceResponse = new ServiceResponse();
        try {
            //
            Map<String, Object> filter = new HashMap<String, Object>();
            filter.put("password", token);
            Customer customer = customerService.getOneCustomerByMobile(filter);
            OrderInfo orderInfo = orderInfoService.getByOrderNo(orderNo);
            Double realPay = 0.0;
            boolean validate = true;
            if (orderInfo != null && customer != null) {
                realPay = orderInfo.getRealPay();
                double totalFee = orderInfo.getTotalFee();
               CustomerDiscountHold hold =  customerDiscountHoldService.getByOrderNo(orderNo);
                if (null != hold) { //优惠券
                    validate = false;
                    long holdId = hold.getId();
                    Map map = customerDiscountHoldService.getDetail(holdId);
                    String state = map.get("state").toString();//优惠券状态1-未使用/    0-已使用
                    String minimum = map.get("minimum_consumption").toString();//优惠券满多少使用
                    String validtime = map.get("validtime").toString();//有效时间
                    SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    validtime = myformat.format(map.get("validtime"));
                    Date endTime = DateUtil.stringToDate(validtime);
//						String validtime  = "2017-06-19 15:33:53";
                    boolean validateTime = DateUtil.compareDate(endTime, new Date());
                    if (null != minimum && (totalFee > Double.parseDouble(minimum)) && validateTime && "1".equals(state)) {
                        validate = true;
                    } else {
                        serviceResponse.setStatus(0);//余额支付结果
                        serviceResponse.setInfo("优惠券已失效，请重新下单");
                    }
                }
                if (validate) {//未使用优惠 或者 优惠券可用
                    if (realPay > 0) {
                        if ("balance".equals(payWay)) {//余额支付
                            String result = payByBalance(realPay, customer, orderNo, payWay);
                            serviceResponse.setData(result);
                            if ("success".equals(result)) {
                                serviceResponse.setStatus(1);//余额支付结果
                                serviceResponse.setInfo("balance");
                            } else {
                                serviceResponse.setStatus(0);//余额支付结果
                                serviceResponse.setInfo("余额支付失败");
                            }

                        } else {//ping++
                            Charge charge = ChargeExample.payByPing(payWay, realPay, orderNo);
                            serviceResponse.setData(charge);
                            serviceResponse.setStatus(1);//ping++
                            serviceResponse.setInfo("pingpp");//ping++
                        }

                    } else {
                        serviceResponse.setStatus(1);//ping++
                        serviceResponse.setInfo("");//ping++
                    }
                }

            } else {
                serviceResponse.setStatus(0);
                serviceResponse.setInfo("获取失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            serviceResponse.setStatus(0);
            serviceResponse.setInfo("获取异常");
        }
        return serviceResponse.objectToJson();
    }

    /**
     * 支付成功回调
     */
    @RequestMapping(value = "backMethod", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String backMethod(String token, String orderNo, String channel) {
        ServiceResponse serviceResponse = new ServiceResponse();
        try {
            Map<String, Object> filter = new HashMap<String, Object>();
            filter.put("password", token);
            Customer customer = customerService.getOneCustomerByMobile(filter);
            OrderInfo orderInfo = orderInfoService.getByOrderNo(orderNo);
            if (orderInfo != null && customer != null) {
                //修改订单状态
                orderInfo.setPayState("2");//支付状态：2已支付
                orderInfo.setPayWay(channel);
                orderInfo.setState(10002);
                orderInfo.setLastTime(new Timestamp(System.currentTimeMillis()));
                orderInfo.setPayTime(new Timestamp(System.currentTimeMillis()));
                DictType dictType = dictTypeService.getDictByCode("10002");
                //订单状态
                OrderState orderState = new OrderState();
                orderState.setCreateTime(new Timestamp(System.currentTimeMillis()));
                orderState.setCreateBy(orderInfo.getCreateBy());
                orderState.setOrderNo(orderInfo.getOrderNo());
                orderState.setState(1);
                if (dictType != null)
                    orderState.setOrderStateInfo(dictType.getName());
                //结算表
                OrderAccount account = new OrderAccount();
                account.setBelongOrder(orderInfo.getOrderNo());
                account.setCreateTime(new Timestamp(System.currentTimeMillis()));
                account.setPayWay(channel);
                account.setCustomer(customer.getId());
                account.setRealPay(orderInfo.getRealPay());
                account.setTotalMoney(orderInfo.getTotalFee());
                account.setState("1");
                account.setUpdateTime(new Timestamp(System.currentTimeMillis()));

                boolean result = false;
                CustomerDiscountHold hold =  customerDiscountHoldService.getByOrderNo(orderNo);
                if (null != hold) {
                        hold.setState(0);//0-已使用；1-未使用
                }
                long integral=customer.getIntegral();
                integral = integral+(long) Math.ceil(orderInfo.getRealPay());//积分
                customer.setIntegral(integral);

                result = customerService.updateOrderState(orderInfo, account, orderState, hold,customer);//更新表
                if (result) {
                    //取车单
                    OrderWork work1 = new OrderWork();
                    work1.setWay(orderInfo.getWay());
                    work1.setState(1);
                    work1.setCreateBy(customer.getId());
                    work1.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    work1.setLastTime(new Timestamp(System.currentTimeMillis()));
                    work1.setOrderState("130001");
                    work1.setOrderNo(orderNo);
                    if ("1".equals(orderInfo.getWay())) {
                        work1.setCarShop(orderInfo.getCarShopGet());
                    } else if ("0".equals(orderInfo.getWay())) {
                        work1.setAddress(orderInfo.getAddressGet());
                    }
                    work1.setCarId(orderInfo.getCarId());
                    work1.setOrderType(0);
                    work1.setRunTime(orderInfo.getReserveTimeStart());
                    //获取店长
                    Long dianzhang1 = carShopService.getDianzhangByShop(orderInfo.getCarShopGet());
                    work1.setEno(dianzhang1);
//					work1.setEno(2L);//测试账号
                    //还车单
                    OrderWork work2 = new OrderWork();
                    work2.setWay(orderInfo.getWay());
                    work2.setState(1);
                    work2.setCreateBy(customer.getId());
                    work2.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    work2.setLastTime(new Timestamp(System.currentTimeMillis()));
                    work2.setOrderState("130004");
                    work2.setOrderNo(orderNo);
                    if ("1".equals(orderInfo.getWay())) {
                        work2.setCarShop(orderInfo.getCarShopReturn());
                    } else if ("0".equals(orderInfo.getWay())) {
                        work2.setAddress(orderInfo.getAddressReturn());
                    }
                    work2.setCarId(orderInfo.getCarId());
                    work2.setOrderType(1);
                    //获取店长
                    Long dianzhang2 = carShopService.getDianzhangByShop(orderInfo.getCarShopReturn());
                    work2.setEno(dianzhang2);
//					work2.setEno(2L);//测试账号
                    work2.setRunTime(orderInfo.getReserveTimeEnd());
                    orderWorkService.save(work1, work2);
                    //取车推送
                    TbaseEmployee employee = tbaseEmployeeService.getOneTbaseEmployee(dianzhang1);
                    if (null != employee && null != employee.getToken()) {
                        System.out.println("employee-id" + employee.getId() + "token" + employee.getToken());
                        PushExample.sendAppMsg(employee.getToken(), "您有新的取车工单啦！");
                    }
                    //还车推送
                    TbaseEmployee employee2 = tbaseEmployeeService.getOneTbaseEmployee(dianzhang2);
                    if (null != employee2 && null != employee2.getToken()) {
                        PushExample.sendAppMsg(employee2.getToken(), "您有新的还车工单啦！");
                    }
                    serviceResponse.setStatus(1);
                    serviceResponse.setInfo("回调成功");
                } else {
                    serviceResponse.setStatus(3);
                    serviceResponse.setInfo("更新失败");
                }
            } else {
                serviceResponse.setStatus(0);
                serviceResponse.setInfo("回调失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            serviceResponse.setStatus(2);
            serviceResponse.setInfo("回调异常");
        }
        return serviceResponse.objectToJson();
    }

    /**
     * 余额支付
     *
     * @param money
     * @param customer
     */
    public String payByBalance(Double money, Customer customer, String orderNo, String channel) {
        String result = "fail";
        try {
            Double lockBalance = customer.getLockBalance();
            if (lockBalance == null)
                lockBalance = 0.0;
            if (customer.getBalance() != null && MathUtils.sub(customer.getBalance(), lockBalance) > money) {
                Double oldBalance = customer.getBalance();
                Double newBalance = MathUtils.sub(customer.getBalance(), money);
                customer.setBalance(newBalance);
                CustomerBalanceChange balanceChange = new CustomerBalanceChange();
                //余额变更记录单
                balanceChange.setBeforeChange(oldBalance);
                balanceChange.setAfterChange(newBalance);
                balanceChange.setBalanceCustomer(customer.getId());
                balanceChange.setCreateBy(customer.getId());
                balanceChange.setCreateTime(new Timestamp(System.currentTimeMillis()));
                balanceChange.setType(0);
                balanceChange.setChangeMoney(money);
                balanceChange.setTransactionNo(Long.valueOf(orderNo));
                balanceChange.setPayWay(channel);
                customerService.updateCustomer(customer, balanceChange);
                result = "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 订单评价
     */
    @RequestMapping(value = "orderEvaluate", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String orderEvaluate(String orderInfo,String token) {
        ServiceResponse serviceResponse = new ServiceResponse();
        try {
            orderInfo = orderInfo.replaceAll("&quot;", "\\\"");
            JSONObject jsStr = JSONObject.parseObject(orderInfo);
            String carState = jsStr.getString("carState");
            String getCar = jsStr.getString("getCar");
            String returnCar = jsStr.getString("returnCar");
            String totalState = jsStr.getString("totalState");
            String orderNo = jsStr.getString("orderNo");

            String evaluateInfo = jsStr.getString("evaluateInfo");
            Map<String, Object> filters = new HashMap<>();
            filters.put("password", token);
            Customer customer = customerService.getOneCustomerByMobile(filters);
            if(null != customer){
                OrderInfo order =  orderInfoService.getByOrderNo(orderNo);
                order.setState(10007);
                OrderEvaluate evaluate = new OrderEvaluate();
                evaluate.setCreateBy(customer.getId());
                evaluate.setCreateTime(new Timestamp(System.currentTimeMillis()));
                evaluate.setContent(evaluateInfo);
                evaluate.setVehicleCondition(carState);
                evaluate.setGetVehicleService(getCar);
                evaluate.setReturnVehicleService(returnCar);
                evaluate.setTotalService(totalState);
                evaluate.setOrderNo(orderNo);
                evaluateService.save(evaluate);
                orderInfoService.update(order);
                serviceResponse.setStatus(1);
                serviceResponse.setInfo("评价成功");
            }else{
                serviceResponse.setStatus(0);
                serviceResponse.setInfo("评价失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            serviceResponse.setStatus(2);
            serviceResponse.setInfo("评价异常");
        }
        return serviceResponse.objectToJson();
    }

    /**
     * 判断近期是否有多个订单未支付
     */
    @RequestMapping(value = "notPayOrder", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String notPayOrder(String token) {
        return null;
    }

    /**
     * ping++测试支付
     */
    @RequestMapping(value = "testPay", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String testPay() {
        ServiceResponse serviceResponse = new ServiceResponse();
        /**
         * 设置请求签名密钥，密钥对需要你自己用 openssl 工具生成，如何生成可以参考帮助中心：https://help.pingxx.com/article/123161；
         * 生成密钥后，需要在代码中设置请求签名的私钥(rsa_private_key.pem)；
         * 然后登录 [Dashboard](https://dashboard.pingxx.com)->点击右上角公司名称->开发信息->商户公钥（用于商户身份验证）
         * 将你的公钥复制粘贴进去并且保存->先启用 Test 模式进行测试->测试通过后启用 Live 模式
         */
        // 设置 API Key
//			Pingpp.apiKey = apiKey;

        // 设置私钥路径，用于请求签名
//        Pingpp.privateKeyPath = privateKeyFilePath;
        /**
         * 或者直接设置私钥内容
         Pingpp.privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
         "... 私钥内容字符串 ...\n" +
         "-----END RSA PRIVATE KEY-----\n";
         */
        Pingpp.privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
                "MIICXQIBAAKBgQDW0IIFx/qJEyFv8Grim4J2LAXkvFeGkmBORxVmtouCHKCRjzh0\n" +
                "+vxpNZjlz5eufNPDRLfWCFBhcRABJr3ClF9D782ZFxWHC2cqH5sl7RgB61FemOZS\n" +
                "IrF3B3rBiM3m4yK4WVMGLcAVc4+FLC2vDOziTDJJUmHw9Ftom0jJSaViEQIDAQAB\n" +
                "AoGAMHphvXhB/AlLNH3nQTijGpQYyGQQxJfoEAJOvB1aIoTfX7oYnZ2+tOeZl9Nf\n" +
                "gcs95wVtD2pU6YigegIKNQ1S6hIoXatrslSFGTQGO68RzNvGoSRwSn0T7LxQ9Kay\n" +
                "JYfeqfugvbLSMJ+kMu7AE7THvG9IKDzt7VaJwGg4gbMFl4ECQQDvSMAnyExCkhko\n" +
                "ZsSo3P3A8XVsf0Xu2BWbx6SIChv87Em+Es34O1xu+69DOd44YZPeSoxstedZ8XMy\n" +
                "q3pVRWS5AkEA5dInAISNsNoXNknQQ4wO54lDcExXVaEPcO6skd6eGnKiodmxWE+7\n" +
                "ca3bvnVq5mqs2XzYicInXdkinsab6yTsGQJBAOFDiuqozOoq4hY/0UnradQ3PEVc\n" +
                "9/gnYwhZhVIwujJ8O9v9lfvn3lf5BwDxvHs1mWA+rD43bUomXT3JYwxdnLkCQCIg\n" +
                "pvIIvRG6ESBarbaaIcx4Ma+HZqCsniJjDvVKXagHxebK8O7wSCGFc9BAl3NH/Ar/\n" +
                "4xfx4O6qw91YGOc+QGECQQDS7M9DVk9+Bed7DqX79t6vZs432Ei2Iba0skIve6cE\n" +
                "NpMHL2VcLkQKRgx+38HFDOl0JIgK68XZWI8FggvxMIMK\n" +
                "-----END RSA PRIVATE KEY-----\n";
        try {
            // Charge 示例
            ChargeExample chargeExample = new ChargeExample();
//		System.out.println("------- 创建 charge -------");
            String channel = "wx";
            Double money = 1.00;
            String orderNo = "2017060100001";
            Charge charge = chargeExample.createCharge(channel, money, orderNo);
            serviceResponse.setData(charge);
            serviceResponse.setStatus(1);
            serviceResponse.setInfo("成功");
        } catch (Exception e) {
            e.printStackTrace();
            serviceResponse.setData("");
            serviceResponse.setStatus(0);
            serviceResponse.setInfo("失败");
        }
        return serviceResponse.objectToJson();
    }

    public static void main(String[] args) {
        PushExample.sendAppMsg("582629e55fbf4044931760a2b4d19780", "您有新的还车工单啦！");
    }
}