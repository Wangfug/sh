
package com.lte.admin.car.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.entity.*;
import com.lte.admin.car.service.CarAttachApplyService;
import com.lte.admin.car.service.CarAttachService;
import com.lte.admin.car.service.CarService;
import com.lte.admin.car.service.CarShopsService;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.custom.entity.Customer;
import com.lte.admin.custom.service.CustomerService;
import com.lte.admin.entity.DictType;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.mobile.common.PushExample;
import com.lte.admin.system.service.DictTypeService;
import com.lte.admin.system.utils.UserUtil;
import org.apache.commons.lang3.ArrayUtils;
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
@RequestMapping("web/carAttachApply")
public class CarAttachApplyController extends BaseController {
	@Resource
	private CarAttachApplyService carAttachApplyService;
    @Resource
    private DictTypeService dictTypeService;
    @Resource
    private CustomerService customerService;
    @Resource
    private CarService carService;
    @Resource
    private CarAttachService carAttachService;
    @Resource
    private CarShopsService carShopsService;
	public CarAttachApply getEntity4Request(HttpServletRequest request) {
			CarAttachApply entity=new CarAttachApply();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
			}
			if(StringUtils.isNotBlank(request.getParameter("createTime"))){
			entity.setCreateTime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("createTime")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("state"))){
			entity.setState(Integer.valueOf(request.getParameter("state")));
			}
			if(StringUtils.isNotBlank(request.getParameter("customerId"))){
			entity.setCustomerId(Long.valueOf(request.getParameter("customerId")));
			}
			if(StringUtils.isNotBlank(request.getParameter("driveLicImg"))){
			entity.setDriveLicImg(request.getParameter("driveLicImg"));
			}
			if(StringUtils.isNotBlank(request.getParameter("carAttachDetail"))){
			entity.setCarAttachDetail(request.getParameter("carAttachDetail"));
			}
			if(StringUtils.isNotBlank(request.getParameter("carInfo"))){
			entity.setCarInfo(request.getParameter("carInfo"));
			}
            if(StringUtils.isNotBlank(request.getParameter("carShop"))){
                entity.setCarShop(Long.valueOf(request.getParameter("carShop")));
            }
			return entity;
	}


	/**
	 * 跳转主页
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "car/carAttachApply";
	}

	@RequestMapping(value = "saveOrUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveCarAttach(HttpServletRequest request) {
		CarAttachApply carAttachApply = this.getEntity4Request(request);
		String result = "false";
		if(carAttachApply.getId()!=null){
			result = carAttachApplyService.updateCarAttachApply(carAttachApply);
		}else{
			result = carAttachApplyService.saveCarAttachApply(carAttachApply);
		}
		return result;
	}
	@RequestMapping(value = "getList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCarAttachApplyList(HttpServletRequest request) {
        MemberLogin user = UserUtil.getCurrentUser();
        Page<CarAttachApply> page = getPage(request);
        Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
        PageList<Map> page1 = null;
            try{
                if("admin".equals(user.getMemberCode())){
                    page1 = carAttachApplyService.getList(page,filters);
                }else{
                    String shopCode = user.getShopCode();
                    CarShops carShops = carShopsService.getOneCarShops(shopCode);
                    if("1".equals(carShops.getSellShop())){
                        filters.put("shopId",carShops.getId());
                        page1 = carAttachApplyService.getList(page,filters);
                    }else{
                        page1 = carAttachApplyService.getList(page,filters);
                    }
                }
            }catch(Exception e){
                    return null;
            }

		return getEasyUIData(page1, request);
	}


    /**
     * 审核挂靠
     * @param id
     * @return
     */
    @RequestMapping(value = "toExamine", method = {RequestMethod.GET,RequestMethod.POST})
    public String toExamine(Long id, HttpServletRequest request,String type){
        List<DictType> dicts = dictTypeService.getChildrenByParent("CarModel");
        CarAttachApply carAttachApply = carAttachApplyService.getOneCarAttachApply(id);
        request.setAttribute("carAttachApply",carAttachApply);
        request.setAttribute("action","texamineResult");
        request.setAttribute("dicts",dicts);
        request.setAttribute("id",id);
        if("1".equals(type)||"2".equals("type")){
            request.setAttribute("type","hide");
        }
        return "car/carAttachApplyForm";
    }
    /**
     * 审核挂靠结果
     * @param id
     * @return
     */
    @RequestMapping(value = "texamineResult", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String texamineResult(Long id, HttpServletRequest request,String type){
        String result = "false";
        CarAttachApply carAttachApply = carAttachApplyService.getOneCarAttachApply(id);
        Customer customer =customerService.getOneCustomer(carAttachApply.getCustomerId());

        try{
            if("0".equals(type)){
                carAttachApply.setState(1);
                Car car = this.getEntity4Request1(request);
                car.setState(20001);
                car.setCarShop(carAttachApply.getCarShop());
                Car car1 = carService.getCarByCode(car.getCarCode());
                CarAttach carAttach = this.getEntity5Request(request);
                CarAttach carAttach1 = null;
                if(car1!=null){
                    car.setId(car1.getId());
                    carAttach1 = carAttachService.getOneCarAttachByCar(car1.getCarCode());
                }else{
                    car.setCreateTime(new Timestamp(System.currentTimeMillis()));
                }
                if(StringUtils.isNotEmpty(car.getCarCode())){
                    car.setBindObj(customer.getName());
                    if(carAttach1!=null){
                        carAttach.setId(carAttach1.getId());
                    }
                    carAttach.setCarCode(car.getCarCode());
                    carAttach.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    carAttach.setCustomerId(carAttachApply.getCustomerId());
                    carAttach.setState(1);
                    carAttach.setType(1);
                    boolean result1 = carService.saveCar(car,carAttach,carAttachApply);
                    if(result1){
                        PushExample.sendAppMsg(customer.getPassword(),"您的车辆已挂靠成功！");
                        result = "success";
                    }else{
                        result = "false";
                    }
                }else{
                    result = "false";
                }


            }else if("1".equals(type)){
                carAttachApply.setState(2);
                carAttachApplyService.updateCarAttachApply(carAttachApply);
                PushExample.sendAppMsg(customer.getPassword(),"您的车辆挂靠失败！");
                result = "success";
            }
        }catch(Exception e){
            result = "false";
        }
        return result;
    }


    public Car getEntity4Request1(HttpServletRequest request) {
        Car entity=new Car();
//        if(StringUtils.isNotBlank(request.getParameter("id"))){
//            entity.setId(Long.valueOf(request.getParameter("id")));
//        }
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
            if(!"".equals(carType))
                carType=carType.substring(0,carType.length());
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
        if(StringUtils.isNotBlank(request.getParameter("finalMileage"))){
            entity.setMileage(request.getParameter("finalMileage"));
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

}