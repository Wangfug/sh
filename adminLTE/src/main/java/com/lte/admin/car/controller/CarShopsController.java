
package com.lte.admin.car.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.entity.CarShops;
import com.lte.admin.car.service.CarService;
import com.lte.admin.car.service.CarShopsService;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.entity.DictType;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.system.service.DictTypeService;
import com.lte.admin.system.utils.UserUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@RequestMapping("web/carShops")
public class CarShopsController extends BaseController  {
	@Resource
	private CarShopsService carShopsService;
	@Resource
	private DictTypeService dictTypeService;
	/**
	 * 跳转主页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toCarView(HttpServletRequest request){
		List<DictType> dictTypesForCarShops = dictTypeService.getChildrenByParent("ShopType");
		request.getSession().setAttribute("dictTypesForCarShops",dictTypesForCarShops);
//		List<Map> managersForCarShops = tbaseEmployeeService.getManager();
//		request.getSession().setAttribute("managersForCarShops",managersForCarShops);
		return "car/carShops";
	}

	/**
	 * 跳转地图选取页
	 */
	@RequestMapping(value = "chooseMap",method = RequestMethod.GET)
	public String tochooseMapForm(HttpServletRequest request){
		return "other/chooseMapForm";
	}

	public CarShops getEntity4Request(HttpServletRequest request) {
			CarShops entity=new CarShops();
			if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
			}
			if(StringUtils.isNotBlank(request.getParameter("shopName"))){
			entity.setShopName(request.getParameter("shopName"));
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
			if(ArrayUtils.isNotEmpty(request.getParameterValues("address"))){
				String[] address = request.getParameterValues("address");
				String str1 = "";
				for(String str:address){
					str1+=str+",";
				}
				entity.setAddress(str1.substring(0,str1.length()-1));
			}
			if(ArrayUtils.isNotEmpty(request.getParameterValues("attachment"))){
				String att = "";
				String[] atts = request.getParameterValues("attachment");
				for(String str:atts){
					att+=str+",";
				}
				if(StringUtils.isNotEmpty(att)){
					att = att.substring(0,att.length()-1);
				}
			entity.setAttachment(att);
			}
			if(StringUtils.isNotBlank(request.getParameter("evaluate"))){
			entity.setEvaluate(request.getParameter("evaluate"));
			}
			if(StringUtils.isNotBlank(request.getParameter("businessStart"))){
			entity.setBusinessStart(request.getParameter("businessStart"));
			}
			if(StringUtils.isNotBlank(request.getParameter("phone"))){
			entity.setPhone(request.getParameter("phone"));
			}
			if(StringUtils.isNotBlank(request.getParameter("stiffPhone"))){
			entity.setStiffPhone(request.getParameter("stiffPhone"));
			}
			if(StringUtils.isNotBlank(request.getParameter("shopType"))){
			entity.setShopType(request.getParameter("shopType"));
			}
			if(StringUtils.isNotBlank(request.getParameter("country"))){
			entity.setCountry(request.getParameter("country"));
			}
			if(StringUtils.isNotBlank(request.getParameter("province"))){
			entity.setProvince(request.getParameter("province"));
			}
			if(StringUtils.isNotBlank(request.getParameter("city"))){
			entity.setCity(request.getParameter("city"));
			}
			if(StringUtils.isNotBlank(request.getParameter("area"))){
			entity.setArea(request.getParameter("area"));
			}
			if(StringUtils.isNotBlank(request.getParameter("businessEnd"))){
			entity.setBusinessEnd(new java.sql.Timestamp(DateUtil.stringToDate(request.getParameter("businessEnd")).getTime()));
			}
			if(StringUtils.isNotBlank(request.getParameter("shopManager"))){
			entity.setShopManager(request.getParameter("shopManager"));
			}
			if(StringUtils.isNotBlank(request.getParameter("postcode"))){
			entity.setPostcode(request.getParameter("postcode"));
			}
			if(StringUtils.isNotBlank(request.getParameter("remark"))){
			entity.setRemark(request.getParameter("remark"));
			}
			if(StringUtils.isNotBlank(request.getParameter("yysj"))){
				entity.setYysj(request.getParameter("yysj"));
			}
			if(StringUtils.isNotBlank(request.getParameter("shopCode"))){
				entity.setShopCode(request.getParameter("shopCode"));
			}
			if(StringUtils.isNotBlank(request.getParameter("parentCode"))){
				entity.setParentCode(request.getParameter("parentCode"));
			}
			if(StringUtils.isNotBlank(request.getParameter("sellShop"))){
				entity.setSellShop(request.getParameter("sellShop"));
			}
			if(StringUtils.isNotBlank(request.getParameter("lon"))){
				entity.setLon(Double.parseDouble(request.getParameter("lon")));
			}
			if(StringUtils.isNotBlank(request.getParameter("lat"))){
				entity.setLat(Double.parseDouble(request.getParameter("lat")));
			}
			return entity;
	}
	@RequestMapping(value = "saveOrUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveCarShops(HttpServletRequest request) {
		MemberLogin user = UserUtil.getCurrentUser();
		CarShops carShops = this.getEntity4Request(request);
		String result = "false";
		if(carShops.getId()!=null){
			carShops.setLastTime(new Timestamp(System.currentTimeMillis()));
			carShops.setLastBy(user.getId());
			result = carShopsService.updateCarShops(carShops);
		}else{
			carShops.setState(1);
			carShops.setCountry("1");
			carShops.setCreateBy(user.getId());
			carShops.setCreateTime(new Timestamp(System.currentTimeMillis()));
			carShops.setLastBy(user.getId());
			carShops.setLastTime(new Timestamp(System.currentTimeMillis()));
			result = carShopsService.saveCarShops(carShops);
		}
		return result;
	}

	@RequestMapping(value = "getList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCarAttachList(HttpServletRequest request) {
		Page<CarShops> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<Map> page1 = carShopsService.getList2(page,filters);
		return getEasyUIData(page1, request);
	}

	/**
	 * 创建门店
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "create",method = {RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletRequest request,String id){
		CarShops carShops = null;
		if(StringUtils.isNotBlank(id))
			carShops = carShopsService.getOneCarShops(Long.parseLong(id));
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("carShop",carShops);
		return "car/carShopsForm";
	}
	/**
	 * 创建门店
	 * @param request
	 * @return
	 */
	@RequiresPermissions("sys:role:addShop")
	@RequestMapping(value = "create1",method = {RequestMethod.GET,RequestMethod.POST})
	public String create1(HttpServletRequest request,String parentCode){
		CarShops carShops = new CarShops();
		carShops.setParentCode(parentCode);
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("carShop",carShops);
		return "car/carShopsForm";
	}
	/**
	 * 根据code查门店
	 */
	@RequestMapping(value = "getCarShopByCode", method = RequestMethod.GET)
	@ResponseBody
	public CarShops getCarShopByCode(String code) {
		return carShopsService.getOneCarShops(code);
	}
}