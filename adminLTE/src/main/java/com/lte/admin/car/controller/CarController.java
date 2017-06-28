
package com.lte.admin.car.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.entity.Car;
import com.lte.admin.car.entity.CarAttach;
import com.lte.admin.car.entity.CarShops;
import com.lte.admin.car.service.CarAttachService;
import com.lte.admin.car.service.CarService;
import com.lte.admin.car.service.CarShopsService;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.custom.entity.Customer;
import com.lte.admin.custom.service.CustomerService;
import com.lte.admin.entity.Dict;
import com.lte.admin.entity.DictType;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.other.entity.CompAssociated;
import com.lte.admin.other.service.CompAssociatedService;
import com.lte.admin.system.service.DictTypeService;
import com.lte.admin.system.utils.UserUtil;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Andy
 */
@Controller
@RequestMapping("web/car")
public class CarController extends BaseController  {
	@Resource
	private CarService carService;
	@Resource
	private CarShopsService carShopsService;
	@Resource
	private DictTypeService dictTypeService;
	@Resource
	private CompAssociatedService compAssociatedService;
	@Resource
	private CarAttachService carAttachService;
	@Resource
	private CustomerService customerService;

	public Car getEntity4Request(HttpServletRequest request) {
			Car entity=new Car();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
			}
			if(StringUtils.isNotBlank(request.getParameter("carName"))){
			entity.setCarName(request.getParameter("carName"));
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
			if(ArrayUtils.isNotEmpty(request.getParameterValues("carType"))){
			String[] types = request.getParameterValues("carType");
			String carType="";
			for(String type:types){
				carType+=type+",";
			}
			entity.setCarType(carType);
			}
			if(StringUtils.isNotBlank(request.getParameter("cartonNumber"))){
			entity.setCartonNumber(request.getParameter("cartonNumber"));
			}
			if(StringUtils.isNotBlank(request.getParameter("displacement"))){
			entity.setDisplacement(request.getParameter("displacement"));
			}
			if(StringUtils.isNotBlank(request.getParameter("blockNumber"))){
			entity.setBlockNumber(request.getParameter("blockNumber"));
			}
			if(StringUtils.isNotBlank(request.getParameter("belong"))){
			entity.setBelong(Integer.valueOf(request.getParameter("belong")));
			}
			if(StringUtils.isNotBlank(request.getParameter("addtionalService"))){
			entity.setAddtionalService(request.getParameter("addtionalService"));
			}
			if(StringUtils.isNotBlank(request.getParameter("carShop"))){
			entity.setCarShop(Long.valueOf(request.getParameter("carShop")));
			}
			if(StringUtils.isNotBlank(request.getParameter("carCode"))){
			entity.setCarCode(request.getParameter("carCode"));
			}
			if(StringUtils.isNotBlank(request.getParameter("engineNo"))){
			entity.setEngineNo(request.getParameter("engineNo"));
			}
			if(StringUtils.isNotBlank(request.getParameter("frameNo"))){
			entity.setFrameNo(request.getParameter("frameNo"));
			}
			if(StringUtils.isNotBlank(request.getParameter("color"))){
			entity.setColor(request.getParameter("color"));
			}
			if(StringUtils.isNotBlank(request.getParameter("buyTime"))){
			entity.setBuyTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("buyTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("brand"))){
			entity.setBrand(request.getParameter("brand"));
			}
			if(StringUtils.isNotBlank(request.getParameter("model"))){
			entity.setModel(request.getParameter("model"));
			}
			if(StringUtils.isNotBlank(request.getParameter("leaveFactoryTime"))){
			entity.setLeaveFactoryTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("leaveFactoryTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("bindObj1")) || StringUtils.isNotBlank(request.getParameter("bindObj2")) || StringUtils.isNotBlank(request.getParameter("bindObj3"))){
			String belong =request.getParameter("belong");
			String bindObj1 = request.getParameter("bindObj1");
				String bindObj2 = request.getParameter("bindObj2");
				String bindObj3 = request.getParameter("bindObj3");
			if("50001".equals(belong)){
				entity.setBindObj("舜昊自营");
			}else if("50002".equals(belong)){//联营
				entity.setBindObj(request.getParameter("bindObj1"));
				}else if("50003".equals(belong)){//挂靠个人
				entity.setBindObj(request.getParameter("bindObj3"));
				}else if("50004".equals(belong)){//挂靠公司
				entity.setBindObj(request.getParameter("bindObj2"));
				}
			}
			if(StringUtils.isNotBlank(request.getParameter("remark1"))){
			entity.setRemark1(request.getParameter("remark1"));
			}
			if(StringUtils.isNotBlank(request.getParameter("remark2"))){
			entity.setRemark2(request.getParameter("remark2"));
			}
			if(StringUtils.isNotBlank(request.getParameter("remark3"))){
			entity.setRemark3(request.getParameter("remark3"));
			}
			if(StringUtils.isNotBlank(request.getParameter("attachment"))){
			entity.setAttachment(request.getParameter("attachment"));
			}
			if(StringUtils.isNotBlank(request.getParameter("vehicleLicense"))){
			entity.setVehicleLicense(Long.valueOf(request.getParameter("vehicleLicense")));
			}
			if(StringUtils.isNotBlank(request.getParameter("moneyBuy"))){
				entity.setMoneyBuy(Double.valueOf(request.getParameter("moneyBuy")));
			}
			if(StringUtils.isNotBlank(request.getParameter("intime"))){
				entity.setIntime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("intime")).getTime()));
			}
			return entity;
	}

	public CarAttach getEntity5Request(HttpServletRequest request) {
		CarAttach entity=new CarAttach();
		if(StringUtils.isNotBlank(request.getParameter("createBy"))){
			entity.setCreateBy(Long.valueOf(request.getParameter("createBy")));
		}
		if(StringUtils.isNotBlank(request.getParameter("carId"))){
			entity.setCarId(Long.valueOf(request.getParameter("carId")));
		}
		if(StringUtils.isNotBlank(request.getParameter("customerId"))){
			entity.setCustomerId(Long.valueOf(request.getParameter("customerId")));
		}

		if(StringUtils.isNotBlank(request.getParameter("attachStart3"))){
			String belong =request.getParameter("belong");
			if("50003".equals(belong)){//挂靠个人
				entity.setAttachStart(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("attachStart3")).getTime()));
			}
		}
		if(StringUtils.isNotBlank(request.getParameter("attachStart2"))){
			String belong =request.getParameter("belong");
			if("50004".equals(belong)){//挂靠公司
				entity.setAttachStart(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("attachStart2")).getTime()));
			}
		}
		if(StringUtils.isNotBlank(request.getParameter("attachEnd3"))){
			String belong =request.getParameter("belong");
			if("50003".equals(belong)){//挂靠个人
				entity.setAttachEnd(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("attachEnd3")).getTime()));
			}
		}
		if(StringUtils.isNotBlank(request.getParameter("attachEnd2"))){
			String belong =request.getParameter("belong");
			if("50004".equals(belong)){//挂靠公司
				entity.setAttachEnd(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("attachEnd2")).getTime()));
			}
		}
		if(StringUtils.isNotBlank(request.getParameter("otherRemark3"))){
			String belong =request.getParameter("belong");
			if("50003".equals(belong)){//挂靠个人
				entity.setOtherRemark(request.getParameter("otherRemark3"));
			}
		}
		if(StringUtils.isNotBlank(request.getParameter("otherRemark2"))){
			String belong =request.getParameter("belong");
			if("50004".equals(belong)){//挂靠公司
				entity.setOtherRemark(request.getParameter("otherRemark2"));
			}
		}
		return entity;
	}

	/**
	 * 车辆信息列表默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(HttpServletRequest request) {
		List<DictType> dictBeans = dictTypeService.getChildrenByParent("CarState");
		JSONArray jsonArray = JSONArray.fromObject(dictBeans);
		request.getSession().setAttribute("dictsForCar",jsonArray);
		StringBuilder carStates = new StringBuilder(
				"<option value='0'>车辆状态</option>");
		String car_state = "<option value='%s'>%s</option>";
		for (DictType tmp : dictBeans) {
			carStates.append(String.format(car_state, tmp.getCode(), tmp.getName()));
		}
		request.getSession().setAttribute("carStates", carStates.toString());
		List<CarShops> shops = carShopsService.getList();
		JSONArray jsonArray1 = JSONArray.fromObject(shops);
		request.getSession().setAttribute("carShops", jsonArray1);

		List<DictType> dictBeans1 = dictTypeService.getChildrenByParent("CarManageType");
		JSONArray jsonArray2 = JSONArray.fromObject(dictBeans1);
		request.getSession().setAttribute("carManageType",jsonArray2);

		List<DictType> dictBeans2 = dictTypeService.getChildrenByParent("CarModel");
		JSONArray jsonArray3 = JSONArray.fromObject(dictBeans2);
		request.getSession().setAttribute("CarModel",jsonArray3);

		return "car/carInfo";
	}

	@RequestMapping(value = "saveOrUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveCar(HttpServletRequest request) {
		MemberLogin user = UserUtil.getCurrentUser();
		Car car = this.getEntity4Request(request);
		String result = "false";
		if(car.getId()!=null){
			result = carService.updateCar(car);
		}else{
			car.setCreateTime(new Timestamp(System.currentTimeMillis()));
			if(user!=null&&!"admin".equals(user.getMemberCode())){
				CarShops shop = carShopsService.getOneCarShops(user.getShopCode());
				if("1".equals(shop.getSellShop())){
					car.setCarShop(shop.getId());
				}
			}
			carService.saveCar(car);
			result="success";

			if("50003".equals(car.getBelong()+"")){
				long carId = car.getCarId();
				CarAttach attach = this.getEntity5Request(request);
				attach.setCreateTime(new java.sql.Timestamp(new Date().getTime()));
				attach.setCarId(carId);
				attach.setCustomerId(Long.valueOf(car.getBindObj()));
				attach.setType(1);//1-挂靠个人
				result = carAttachService.saveCarAttach(attach);
			}else if("50004".equals(car.getBelong()+"")){
				long carId = car.getCarId();
				CarAttach attach = this.getEntity5Request(request);
				attach.setCreateTime(new java.sql.Timestamp(new Date().getTime()));
				attach.setCarId(carId);
				attach.setType(2);//2-挂靠公司
				attach.setCustomerId(Long.valueOf(car.getBindObj()));
				result = carAttachService.saveCarAttach(attach);
			}
		}
		return result;
	}

	/**
	 *修改车辆信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
//		List<DictType> dictTypeBeans = dictTypeService.getChildrenByParent("CarState");
		Car car = carService.getOneCar(id);
		model.addAttribute("car", car);
		model.addAttribute("action", "saveOrUpdate");
		return "car/carForm";
	}

	/**
	 *新增车辆信息
	 */
	@RequestMapping(value = "add", method = {RequestMethod.GET,RequestMethod.POST})
	public String addForm(Model model,HttpServletRequest request) {
		model.addAttribute("action", "saveOrUpdate");
		List<DictType> dictBeans = dictTypeService.getChildrenByParent("CarManageType");
		JSONArray jsonArray = JSONArray.fromObject(dictBeans);
		request.setAttribute("carManageType",jsonArray);

		List<DictType> dictBeans1 = dictTypeService.getChildrenByParent("CarModel");
		JSONArray jsonArray3 = JSONArray.fromObject(dictBeans1);
		request.setAttribute("CarModel",jsonArray3);

		List<CompAssociated> comps = compAssociatedService.getAllList();
		JSONArray jsonArray1 = JSONArray.fromObject(comps);
		request.setAttribute("compAssociateds",jsonArray1);

		List<Customer> customers = customerService.getList();
		JSONArray jsonArray2 = JSONArray.fromObject(customers);
		request.setAttribute("customers",jsonArray2);

		return "car/addCarForm";
	}

	@RequestMapping(value = "getCarList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCarList(HttpServletRequest request) {
		Page<Car> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		MemberLogin memberLogin = (MemberLogin)request.getSession().getAttribute("user");
		PageList<Car> page1 = null;
		try{
			if(!"admin".equals(memberLogin.getMemberCode())){
				String jobCode = memberLogin.getJobCode();
				String shopCode = memberLogin.getShopCode();
//				if(jobCode.startsWith("DZ")||jobCode.startsWith("KF")){
//					page1 = carService.getListByShop1(page,filters);
//				}else if(jobCode.startsWith("MG")){
					CarShops carShops = carShopsService.getOneCarShops(shopCode);
					String pCode = carShops.getParentCode();
					if("SH".equals(shopCode)){
						//查询所有
						page1 = carService.getList(page,filters);
					}else if(!"SH".equals(shopCode)&&"SH".equals(pCode)){
						//查询市级车辆
						filters.put("shopCode",shopCode);
						page1 = carService.getListByShop1(page,filters);
					}else if(shopCode.endsWith("ZY")||shopCode.endsWith("LY")){
						filters.put("shopCode",shopCode);
						page1 = carService.getListByShop2(page,filters);
					}else if("1".equals(carShops.getSellShop())){
						filters.put("shopId",carShops.getId());
						page1 = carService.getList(page,filters);
					}
//				}
			}else{
				page1 = carService.getList(page,filters);
			}
		}catch(Exception e){
			return null;
		}
		return getEasyUIData(page1, request);
	}

	@RequestMapping(value = "uploadTuPian", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String uploadTuPian(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		PropertiesUtil properyUtilUtil = new PropertiesUtil();
		//上传文件存在的位置
//        String path=request.getSession().getServletContext().getRealPath("uploadImg");
//        String path = properyUtil.readValue("UPLOAD_IMG_PATH");
		String path = "D:/resource/uploadfile";
		String savePath = CommonUtil.uploadFile(path, file, request, response);//上传图片
		String result="";
		if( null != savePath && ""!= savePath){
			result=savePath;//上传成功
		}
		return result;
	}
	/**
	 * 查询车辆信息
	 */
	@RequestMapping(value = "viewCarInfo",method = RequestMethod.GET)
	public String viewCarInfo(HttpServletRequest request,String carCode) {
		try{
		Map<String,Object> carDetail = carService.getCarByCode1(carCode);
		if(carDetail.get("car_type")!=null){
			String carType = (String)carDetail.get("car_type");
			String[] carTypes = carType.split(",");
			carType = "" ;
			for(String str:carTypes){
				DictType dict = dictTypeService.getDictByCode(str);
				carType+=dict.getName()+",";
			}
			if(!"".equals(carType)){
				carType=carType.substring(0,carType.length());
			}
			carDetail.put("carType",carType);
			request.setAttribute("carDetail",carDetail);
		}
		}catch(Exception e){
			return null;
		}
		return "car/carInfoDetail";
	}

	/**
	 * 上架或下架
	 */
	@ResponseBody
	@RequestMapping(value = "changeIsSale",method = {RequestMethod.GET,RequestMethod.POST})
	public String changeIsSale(HttpServletRequest request,String carCode,Integer type) {
		String result = "false";
		try{
			Car car = carService.getCarByCode(carCode);
			if(car!=null){
				car.setState(type);
				carService.updateCar(car);
				result = "success";;
			}else{
				result = "未找到车辆！";;
			}
		}catch(Exception e){
			 result = "系统错误！";;
		}
		return result;
	}
	/**
	 * 查询车辆信息
	 */
	@ResponseBody
	@RequestMapping(value = "checkCarCode",method = {RequestMethod.GET,RequestMethod.POST})
	public String checkCarCode(HttpServletRequest request,String carCode) {
		String result = "false";
		try{
			Car car = carService.getCarByCode(carCode);
			if(car==null){
				result = "true";
			}else{
				result = "false";
			}
		}catch(Exception e){
			result = "系统错误！";
		}
		return result;
	}
}