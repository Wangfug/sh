
package com.lte.admin.car.controller;
import com.lte.admin.car.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.car.entity.Car;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

/**
 * @author Andy
 */
@Controller
@RequestMapping("")
public class CarController extends BaseController  {
	@Resource
	private CarService carService;

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
			if(StringUtils.isNotBlank(request.getParameter("carType"))){
			entity.setCarType(request.getParameter("carType"));
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
			if(StringUtils.isNotBlank(request.getParameter("owner"))){
			entity.setOwner(Long.valueOf(request.getParameter("owner")));
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
			if(StringUtils.isNotBlank(request.getParameter("buyYime"))){
			entity.setBuyYime(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("buyYime")).getTime()));
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
			if(StringUtils.isNotBlank(request.getParameter("bindObj"))){
			entity.setBindObj(request.getParameter("bindObj"));
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
			return entity;
	}
}