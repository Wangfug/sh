package com.lte.admin.mobile.employee;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.entity.Car;
import com.lte.admin.car.entity.CarOutOrIn;
import com.lte.admin.car.service.CarService;
import com.lte.admin.common.consts.OrderWorkTypeEnum;
import com.lte.admin.common.consts.PreAuthorizedEnum;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.response.ServiceResponse;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.custom.entity.Customer;
import com.lte.admin.custom.service.CustomerService;
import com.lte.admin.custom.service.TbaseEmployeeService;
import com.lte.admin.order.entity.OrderFee;
import com.lte.admin.order.entity.OrderInfo;
import com.lte.admin.order.entity.OrderWork;
import com.lte.admin.order.service.OrderFeeService;
import com.lte.admin.order.service.OrderInfoService;
import com.lte.admin.order.service.OrderWorkService;
import com.lte.admin.system.service.DictTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Andy
 */
@Controller
@RequestMapping("mobile/employee")
public class OrderWorkMobileController extends BaseController  {
	@Resource
	private OrderWorkService orderWorkService;
	@Resource
	private CustomerService customerService;
	@Resource
	private OrderFeeService orderFeeService;
	@Resource
	private OrderInfoService orderInfoService;
	@Resource
	private DictTypeService dictTypeService;
	@Resource
	private TbaseEmployeeService tbaseEmployeeService;
	@Resource
	private CarService carService;
//	@RequestMapping(value = "sendOrderWorkList", method = {RequestMethod.GET,RequestMethod.POST})
//	@ResponseBody
//	public String sendOrderWorkList(String token,String pageNum,String pageSize){
//		Map<String, Object> filters = new HashMap<>();
//		filters.put("token",token);
//		int orderType = Integer.valueOf(OrderWorkTypeEnum.SEND_CAR.getCode());
//		filters.put("orderType",orderType);
//		String json = getList(pageNum,pageSize,filters);
//		return json;
//	}
//
//	@RequestMapping(value = "receiveOrderWorkList", method = {RequestMethod.GET,RequestMethod.POST})
//	@ResponseBody
//	public String receiveOrderWorkList(String token,String pageNum,String pageSize){
//		Map<String, Object> filters = new HashMap<>();
//		filters.put("token",token);
//		int orderType = Integer.valueOf(OrderWorkTypeEnum.GET_CAR.getCode());
//		filters.put("orderType",orderType);
//		String json = getList(pageNum,pageSize,filters);
//		return json;
//	}

	@RequestMapping(value = "getOrderWorkList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String getOrderWorkList(String token,String pageNum,String pageSize,String orderType,String orderState){//orderType:1-取车单；0-送车单;2-所有****---orderState:1:已完成/0：未完成
		Map<String, Object> filters = new HashMap<>();
		filters.put("token",token);
//		int orderType = Integer.valueOf(OrderWorkTypeEnum.GET_CAR.getCode());
		int orderType1;
		if(OrderWorkTypeEnum.GET_CAR.getCode().equals(orderType)){
			orderType1 = Integer.valueOf(OrderWorkTypeEnum.GET_CAR.getCode());//还车
			filters.put("orderType",orderType1);
			if(orderState.equals("1")){//已完成的还车单
				filters.put("orderState","130007");
			}else if(orderState.equals("0")){//未完成的还车单
				filters.put("orderState","130004");
			}
			filters.put("state",1);
		}else if(OrderWorkTypeEnum.SEND_CAR.getCode().equals(orderType)){
			orderType1 = Integer.valueOf(OrderWorkTypeEnum.SEND_CAR.getCode());//送车
			filters.put("orderType",orderType1);
			if(orderState.equals("1")){//已完成的送车单
				filters.put("orderState","130007");
			}else if(orderState.equals("0")){//未完成的送车单
				filters.put("orderState","130001");
			}
			filters.put("state",1);
		}else if("2".equals(orderType)&&"1".equals(orderState)){
			filters.put("orderState","130007");
		}else{
			filters.put("state",1);
		}
		String json = getList(pageNum,pageSize,filters);
		return json;
	}

	public String getList(String pageNum,String pageSize,Map filters){
		ServiceResponse serviceResponse = new ServiceResponse();
		try{
			Page<OrderWork> page = new Page<>();
			if(StringUtils.isNotBlank(pageNum+"") && StringUtils.isNotBlank(pageSize+"")){
				int num = Integer.parseInt(pageNum);
				int size = Integer.parseInt(pageSize);
				page.setPageNo(num);
				page.setPageSize(size);
			}
			PageList<Map> page1 = orderWorkService.getListByToken(page, filters);
			serviceResponse.setData(page1);
			serviceResponse.setStatus(1);
			serviceResponse.setInfo("获取成功");
		}catch (Exception e){
			e.printStackTrace();
			serviceResponse.setData("");
			serviceResponse.setStatus(0);
			serviceResponse.setInfo("获取失败");
		}
		String json =  serviceResponse.objectToJson();
		return json;
	}
	/**
	 * 预授权
	 * @param token
	 * @param
	 * @return
	 */
	@RequestMapping(value = "lockBalance", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String lockBalance(String token,String orderNo,String lockBalance,String invoiceType){
		ServiceResponse serviceResponse = new ServiceResponse();
		try{
			if("1".equals(invoiceType)){
				serviceResponse.setData("");
				serviceResponse.setStatus(0);
				serviceResponse.setInfo("信用卡抵扣功能即将上线，请耐心等待！");
			}else if("0".equals(invoiceType)){
				Long customerId = 0l;
				OrderInfo order = orderInfoService.getByOrderNo(orderNo);
				if(order==null){
					serviceResponse.setData("");
					serviceResponse.setInfo("订单不存在！");
				}else if(order.getFinalFee()==null){
					serviceResponse.setData("");
					serviceResponse.setInfo("无效订单！");
				}else{
					customerId = order.getCustomer();
					Customer customer = customerService.getOneCustomer(customerId);
					Double oldLockBalance = customer.getLockBalance();
					if(oldLockBalance==null)
						oldLockBalance = 0.0;
					if("".equals(lockBalance))
						lockBalance = "0";
					Double lockBalanc =  oldLockBalance+Double.valueOf(lockBalance);
					if(customer.getBalance()==null||customer.getBalance()<lockBalanc){
						serviceResponse.setData("");
						serviceResponse.setStatus(0);
						serviceResponse.setInfo("余额不足");
					}else{
						customer.setLockBalance(lockBalanc);
						OrderFee orderFee = orderFeeService.get(order.getFinalFee());
						orderFee.setPreAuthorized(Double.valueOf(lockBalance));
						orderFee.setPreAuthorizedWay(0);
						order.setIsPreAuthorized(PreAuthorizedEnum.PRE_AUTHORIZED.getCode());
						boolean myresult = 	orderInfoService.updateForLockBalance(customer,orderFee,order);
						if(myresult){
							serviceResponse.setData("");
							serviceResponse.setStatus(1);
							serviceResponse.setInfo("预授权成功！");
						}else{
							serviceResponse.setData("");
							serviceResponse.setStatus(2);
							serviceResponse.setInfo("预授权失败！");
						}

					}
				}

			}
		}catch (Exception e){
//			e.printStackTrace();
			serviceResponse.setData("");
			serviceResponse.setStatus(0);
			serviceResponse.setInfo("预授权失败");
		}
		return serviceResponse.objectToJson();
	}


	/**
	 * 获取订单信息
	 * @param token
	 * @param
	 * @return
	 */
	@RequestMapping(value = "getOrderDetail", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String getOrderDetail(String token,String orderNo){
		ServiceResponse serviceResponse = new ServiceResponse();
		try{
			Map<String,Object> contentsForEmployee = orderInfoService.getOrderDetail(orderNo);
			List<Map<String,Object>> works = orderWorkService.getByOrder(orderNo);
			if(StringUtils.isNotEmpty((String)contentsForEmployee.get("carType"))){
				String carType = (String)contentsForEmployee.get("carType");
				int endIndex = carType.indexOf(",");
				if(endIndex!=-1)
				carType = carType.substring(0,endIndex);
				carType = dictTypeService.getDictByCode(carType).getName();
				contentsForEmployee.put("carType",carType);
			}
			for(Map work:works){
				if("0".equals(work.get("order_type")+"")){
					contentsForEmployee.put("shopForGet",work.get("shopName"));
				}
				if("1".equals(work.get("order_type")+"")){
					contentsForEmployee.put("shopForReturn",work.get("shopName"));
				}

			}
			serviceResponse.setData(contentsForEmployee);
			serviceResponse.setStatus(1);
			serviceResponse.setInfo("获取订单成功");
		}catch (Exception e){
//			e.printStackTrace();
			serviceResponse.setStatus(0);
			serviceResponse.setInfo("获取订单失败");
		}
		return serviceResponse.objectToJson();
	}

	/**
	 * 获取工单数量
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping(value = "getOrderWorkNumber", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String getOrderWorkNumber(String token){
		ServiceResponse serviceResponse = new ServiceResponse();
		try{
			Long numForGet = orderWorkService.getGetNumber(token);
			Long numForReturn = orderWorkService.getReturnNumber(token);
			Map<String,Object> filter = new HashMap<String,Object>();
			filter.put("token",token);
			filter.put("orderState","0");
			Long complaint1 = orderInfoService.getComplaintNumber1(filter);
			filter.put("orderState","1");
			Long complaint2 = orderInfoService.getComplaintNumber2(filter);
			if(complaint1==null)
				complaint1 = 0l;
			if(complaint2==null)
				complaint2 = 0l;
			Long complaint = complaint1+complaint2;
			Map<String,Object> result = new HashMap<String,Object>();
			result.put("numForGet",numForGet);
			result.put("numForReturn",numForReturn);
			result.put("complaint",complaint);
			serviceResponse.setData(result);
			serviceResponse.setStatus(1);
			serviceResponse.setInfo("获取工单数量成功");
		}catch (Exception e){
//			e.printStackTrace();
			serviceResponse.setStatus(0);
			serviceResponse.setInfo("获取工单数量失败");
		}
		return serviceResponse.objectToJson();
	}
	/**
	 * 车况确认，完成送车
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping(value = "sendCarConfirm", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String sendCarConfirm(String carRentDetail,String orderNo,String initialMileage,String carCode){
		ServiceResponse serviceResponse = new ServiceResponse();
		Integer orderType = 0;
		try{
			boolean flag = true;
			Car car = null;
			Integer oldMileage = 0;
			Integer currMileage = 0;
			if(StringUtils.isEmpty(carRentDetail)||StringUtils.isEmpty(orderNo)||StringUtils.isEmpty(initialMileage)||
					StringUtils.isEmpty(carCode)){
				flag = false;
				serviceResponse.setInfo("取车确认失败,请求参数不满足条件");
			}else{
				car = carService.getCarByCode(carCode);
				currMileage = Integer.valueOf(initialMileage);
			}
			if(car==null){
				flag = false;
				serviceResponse.setInfo("取车确认失败,未查到车辆");
			}else if(car.getMileage()!=null){
				oldMileage = Integer.valueOf(car.getMileage());
			}
			//&&Integer.valueOf(car.getMileage())>Integer.valueOf(initialMileage)
			if(flag){
				Map<String,Object> filter = new HashMap<String,Object>();
				filter.put("orderNo",orderNo);
				filter.put("orderType",orderType);
				OrderWork sendWork = orderWorkService.getByOrder(filter);
				sendWork.setOrderState("130007");
				sendWork.setLastTime(new Timestamp(System.currentTimeMillis()));
//			int index = carRentDetail.indexOf("&quot;");
				carRentDetail= carRentDetail.replaceAll("&quot;","\\\"");
				sendWork.setCarCheckDetail(carRentDetail);
				sendWork.setState(0);
				sendWork.setCarId(car.getId());
				OrderInfo orderInfo = orderInfoService.getByOrderNo(orderNo);
				orderInfo.setLastTime(new Timestamp(System.currentTimeMillis()));
				orderInfo.setRealTimeStart(new Timestamp(System.currentTimeMillis()));
				orderInfo.setState(10004);
				orderInfo.setInitialMileage(initialMileage);
				orderInfo.setRealCarType(car.getBrand()+car.getModel());
				orderInfo.setCarId(car.getId());
//				orderInfo
				if(currMileage!=0)
				car.setMileage(currMileage+"");
//			sendWork.setWay();
				// 车辆出库
				CarOutOrIn carOutOrIn = new CarOutOrIn();
				carOutOrIn.setCreateBy(orderInfo.getCreateBy());
				carOutOrIn.setCreateTime(new Timestamp(System.currentTimeMillis()));
				carOutOrIn.setCarId(car.getId());
				carOutOrIn.setApproveBy(sendWork.getEno());
				carOutOrIn.setCarShop(sendWork.getCarShop());
				carOutOrIn.setInOrOut("出库");
				carOutOrIn.setLastTime(new Timestamp(System.currentTimeMillis()));
				carOutOrIn.setOutTime(new Timestamp(System.currentTimeMillis()));
				carOutOrIn.setCreateBy(orderInfo.getLastBy());
				carOutOrIn.setOutEmp(sendWork.getEno());
				carOutOrIn.setOrderNo(orderNo);
				carOutOrIn.setReason("取车");
				boolean result1 = orderWorkService.update(sendWork,orderInfo,car,carOutOrIn);
				if(result1){
					serviceResponse.setStatus(1);
					serviceResponse.setInfo("取车确认成功");
				}else{
					serviceResponse.setStatus(2);
					serviceResponse.setInfo("取车确认失败");
				}
			}
		}catch (Exception e){
			serviceResponse.setInfo("取车确认失败");
		}
		return serviceResponse.objectToJson();
	}
	/**
	 * 车况确认，完成还车
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping(value = "returnCarConfirm", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String returnCarConfirm(String carRentDetail,String orderNo,String finalMileage,String carCode){
		ServiceResponse serviceResponse = new ServiceResponse();
		Integer orderType = 1;
		try{

			boolean flag = true;
			Car car = null;
			Integer oldMileage = 0;
			Integer currMileage = 0;
			if(StringUtils.isEmpty(carRentDetail)||StringUtils.isEmpty(orderNo)||StringUtils.isEmpty(finalMileage)||
					StringUtils.isEmpty(carCode)){
				flag = false;
				serviceResponse.setInfo("还车确认失败,请求参数不满足条件");
			}else{
				car = carService.getCarByCode(carCode);
				currMileage = Integer.valueOf(finalMileage);
			}
			if(car==null){
				flag = false;
				serviceResponse.setInfo("还车确认失败,未查到车辆");
			}else if(car.getMileage()!=null){
				oldMileage = Integer.valueOf(car.getMileage());
			}
			if(flag){
				OrderInfo orderInfo = orderInfoService.getByOrderNo(orderNo);
				if(orderInfo.getCarId()!=car.getId()){

				}
				Map<String,Object> filter = new HashMap<String,Object>();
				filter.put("orderNo",orderNo);
				filter.put("orderType",orderType);
				OrderWork returnWork = orderWorkService.getByOrder(filter);
				returnWork.setOrderState("130007");
				returnWork.setLastTime(new Timestamp(System.currentTimeMillis()));
				carRentDetail= carRentDetail.replaceAll("&quot;","\\\"");
				returnWork.setCarCheckDetail(carRentDetail);
				returnWork.setState(0);
				orderInfo.setLastTime(new Timestamp(System.currentTimeMillis()));
				orderInfo.setRealTimeEnd(new Timestamp(System.currentTimeMillis()));
				orderInfo.setState(10005);
				orderInfo.setFinalMileage(finalMileage);
//				sendWork.setWay();
				if(currMileage!=0)
					car.setMileage(currMileage+"");
				// 车辆入库
				CarOutOrIn carOutOrIn = new CarOutOrIn();
				carOutOrIn.setCreateBy(orderInfo.getCreateBy());
				carOutOrIn.setCreateTime(new Timestamp(System.currentTimeMillis()));
				carOutOrIn.setCarId(car.getId());
				carOutOrIn.setApproveBy(returnWork.getEno());
				carOutOrIn.setCarShop(returnWork.getCarShop());
				carOutOrIn.setInOrOut("入库");
				carOutOrIn.setLastTime(new Timestamp(System.currentTimeMillis()));
				carOutOrIn.setCreateBy(orderInfo.getLastBy());
				carOutOrIn.setOutEmp(returnWork.getEno());
				carOutOrIn.setOrderNo(orderNo);
				carOutOrIn.setOutTime(new Timestamp(System.currentTimeMillis()));
				carOutOrIn.setReason("收车");
				boolean result2 = orderWorkService.update(returnWork,orderInfo,car,carOutOrIn);
				if(result2){
					returnWork.setCarCheckDetail(carRentDetail);
					serviceResponse.setStatus(1);
					serviceResponse.setInfo("还车确认成功");
				}else{
					serviceResponse.setStatus(2);
					serviceResponse.setInfo("还车确认失败");
				}

			}
		}catch (Exception e){
//			e.printStackTrace();
			serviceResponse.setStatus(0);
			serviceResponse.setInfo("还车确认失败");
		}
		return serviceResponse.objectToJson();
	}

	/**
	 * 获取员工信息
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping(value = "getEmployeeInfo", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String getEmployeeInfo(String token){
		ServiceResponse serviceResponse = new ServiceResponse();
		try{
			Map<String,Object> eInfo = tbaseEmployeeService.getEmployeeByToken(token);
			serviceResponse.setData(eInfo);
			serviceResponse.setStatus(1);
			serviceResponse.setInfo("获取业务员信息成功");
		}catch (Exception e){
//			e.printStackTrace();
			serviceResponse.setStatus(0);
			serviceResponse.setInfo("获取业务员信息失败");
		}
		return serviceResponse.objectToJson();
	}

}