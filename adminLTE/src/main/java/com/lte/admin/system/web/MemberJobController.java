package com.lte.admin.system.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lte.admin.car.entity.CarShops;
import com.lte.admin.car.service.CarShopsService;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.utils.DateUtil;
import com.lte.admin.entity.*;
import com.lte.admin.other.entity.TbaseCompany;
import com.lte.admin.other.service.TbaseCompanyService;
import com.lte.admin.system.service.DeptService;
import com.lte.admin.system.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.mapper.TreeNode;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.system.service.MemberJobService;
import com.lte.admin.system.service.RolePermissionService;
import com.lte.admin.system.service.RoleService;

@Controller
@RequestMapping("system/memberJob")
public class MemberJobController extends BaseController  {

	@Autowired
	private RolePermissionService rolePermissionService;
	
	@Autowired
	private MemberJobService memberJobService;
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private TbaseCompanyService tbaseCompanyService;
	@Autowired
	private CarShopsService carShopsService;
	
	
	/**
	 * 默认页面
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "system/memberJobList";
	}

	/**
	 * 默认页面
	 *
	 * @return
	 */
	@RequestMapping(value = "dept" ,method = RequestMethod.GET)
	public String list1() {
		return "system/deptList";
	}

	@RequestMapping(value = "bmlist", method = RequestMethod.GET)
	@ResponseBody
	public List<TreeNode> bmlist() {
		List<TreeNode> treeList = new ArrayList<TreeNode>();
		//查出所有的上层公司
		//门店取代公司
//		List<Company> companyRootList = tbaseCompanyService.getCompanyListTree();
		List<CarShops> companyRootList = carShopsService.getCarShopListTree();
		//遍历所有上层公司
		for (CarShops carShops : companyRootList) {
			//生成公司基本属性的节点
			TreeNode com = new TreeNode();
			com.setId(carShops.getShopCode());
			com.setText(carShops.getShopName());
			com.setState("close");
			Map<String,String> attrsForCom = new HashMap<String,String>();
			attrsForCom.put("companyCode",carShops.getShopCode());
			attrsForCom.put("type","carShop");
			com.setAttributes(attrsForCom);
			List<TreeNode> nodeSubList = getCarShopsBySuper(carShops.getShopCode());
			com.setChildren(nodeSubList);
			treeList.add(com);
		}
		return treeList;
	}

	public List<TreeNode> getCarShopsBySuper(String superCode){
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		//子公司
//		List<TbaseCompany> companySubList = tbaseCompanyService.getCompanyListTreeByParent(superCode);
		List<CarShops> carShopsSubList = carShopsService.getCarShopsListTreeByParent(superCode);
		if(carShopsSubList.size()>0){
			for(CarShops carShops:carShopsSubList){
				TreeNode node = new TreeNode();
				node.setId(carShops.getShopCode());
				node.setText(carShops.getShopName());
				node.setState("close");
				Map<String,String> attrsForCom = new HashMap<String,String>();
				attrsForCom.put("companyCode",carShops.getShopCode());
				attrsForCom.put("type","carShop");
				node.setAttributes(attrsForCom);
				node.setChildren(getCarShopsBySuper(carShops.getShopCode()));
				nodes.add(node);
			}
		}
		//部门
		List<Dept> deptRootList = deptService.getDeptListByCompany(superCode);
		if(deptRootList.size()>0){
			for(Dept dept:deptRootList){
				TreeNode node = new TreeNode();
				node.setId(dept.getDeptCode());
				node.setText(dept.getDeptName());
				node.setState("close");
				Map<String,String> attrsForCom = new HashMap<String,String>();
				attrsForCom.put("companyCode",dept.getCompanyCode());
				attrsForCom.put("type","dept");
				node.setAttributes(attrsForCom);
				node.setChildren(getDeptBySuper(dept.getDeptCode()));
				nodes.add(node);
//				List<Dept> deptSubList = deptService.getBmListTreeByParent(dept.getDeptCode());
			}
		}
		return nodes;
	}

	/**
	 * 根据父部门构建子部门tree
	 * @param superCode
	 * @return
	 */
	public List<TreeNode> getDeptBySuper( String superCode){
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		List<Dept> deptList = deptService.getBmListTreeByParent(superCode);
		if(deptList.size()>0){
			for(Dept dept:deptList){
				TreeNode node = new TreeNode();
				node.setId(dept.getDeptCode());
				node.setText(dept.getDeptName());
				node.setState("close");
				Map<String,String> attrsForDept = new HashMap<String,String>();
				attrsForDept.put("companyCode",dept.getCompanyCode());
				attrsForDept.put("type","dept");
				node.setAttributes(attrsForDept);
				node.setChildren(getDeptBySuper(dept.getDeptCode()));
				nodes.add(node);
			}
		}
		return nodes;
	}
	/**
	 * 岗位集合(JSON)
	 */
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request,
			@RequestParam(value = "deptCode", defaultValue = "") String deptCode,@RequestParam(value = "companyCode", defaultValue = "") String companyCode) {
		Page<StaffJob> page = getPage(request);
		if (deptCode.equals("")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rows", new PageList<StaffJob>());
			map.put("total", 0);
			return map;
		}
		Map<String,Object> filter = new HashMap<String,Object>();
		filter.put("deptCode",deptCode);
		filter.put("companyCode",companyCode);
		PageList<StaffJob> page1 = memberJobService.search(page,filter);
		if(page1==null)
			page1 = new PageList<StaffJob>();
		return getEasyUIData(page1, request);
	}

	/**
	 * 岗位集合(JSON)
	 */
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "jsonAll", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllData(HttpServletRequest request,
									   @RequestParam(value = "deptCode", defaultValue = "") String deptCode) {
		Page<StaffJob> page = getPage(request);

		PageList<StaffJob> page1 = memberJobService.search(page);
		if(page1==null)
			page1 = new PageList<StaffJob>();
		return getEasyUIData(page1, request);
	}


	/**
	 * 创建职位或修改职位
	 */
	@RequestMapping(value = "create",method = {RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletRequest request,String id,String code){
		Map memberJob = null;
		if(StringUtils.isNotBlank(id))
			memberJob = memberJobService.getOneStaffJob1(Long.parseLong(id));
		else{
			memberJob = new HashMap();
			if(code!=null){
				Dept dept = deptService.getOneDept(code);
				memberJob.put("deptCode",code);
				memberJob.put("shopCode",dept.getCompanyCode());
			}
		}
		request.setAttribute("action","saveOrUpdate");
		request.setAttribute("memberJob",memberJob);
		return "system/memberJobForm";
	}
	@RequestMapping(value = "saveOrUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveStaffJob(HttpServletRequest request,String shopCode) {
		String result = "false";
		StaffJob staffJob = this.getEntity4Request(request);
		if(StringUtils.isNotBlank(staffJob.getDeptCode())&&StringUtils.isNotBlank(shopCode)){
			Dept dept = deptService.getOneDept(staffJob.getDeptCode(),shopCode);
			if(dept!=null){
				staffJob.setDeptId(dept.getId());
				if(staffJob.getId()!=null){
					result = memberJobService.updateStaffJob(staffJob);
				}else{
					result = memberJobService.saveStaffJob(staffJob);
				}
			}else{
				result = "查不到部门";
			}
		}else{
			result = "门店为空";
		}
		return result;
	}
	/**
	 * 获取角色拥有的权限ID集合
	 *
	 * @param
	 * @return
	 *//*
	@RequiresPermissions("sys:role:permView")
	@RequestMapping("{id}/json")
	@ResponseBody
	public List<GwPermission> getRolePermissions(@PathVariable("id") String id) {
		List<GwPermission> permissionIdList = rolePermissionService.getPermissionIds(id);
		return permissionIdList;
	}*/

	public StaffJob getEntity4Request(HttpServletRequest request) {
		StaffJob entity=new StaffJob();
		if(StringUtils.isNotBlank(request.getParameter("id"))){
			entity.setId(Long.valueOf(request.getParameter("id")));
		}
		if(StringUtils.isNotBlank(request.getParameter("jobCode"))){
			entity.setJobCode(request.getParameter("jobCode"));
		}
		if(StringUtils.isNotBlank(request.getParameter("jobName"))){
			entity.setJobName(request.getParameter("jobName"));
		}
		if(StringUtils.isNotBlank(request.getParameter("deptCode"))){
			entity.setDeptCode(request.getParameter("deptCode"));
		}
		if(StringUtils.isNotBlank(request.getParameter("deleteFlag"))){
			entity.setDeleteFlag(Byte.valueOf(request.getParameter("deleteFlag")));
		}
		return entity;
	}

	/**
	 * 获取部门列表
	 */
	@RequestMapping(value = "getDeptList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public  Map<String, Object>  getDeptList(HttpServletRequest request) {
		Page<Dept> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<Dept> page1 = deptService.getDeptList(page, filters);
		return getEasyUIData(page1, request);
	}


	/**
	 * 创建部门或修改部门
	 */
	@RequestMapping(value = "create1",method = {RequestMethod.GET,RequestMethod.POST})
	public String updateDept(HttpServletRequest request,String id){
		Dept dept = null;
		if(StringUtils.isNotBlank(id))
			dept = deptService.getOneDept(Long.parseLong(id));
		request.setAttribute("action","saveOrUpdate1");
		request.setAttribute("dept",dept);
		return "system/deptForm";
	}
	/**
	 * 创建部门或修改部门
	 */
	@RequiresPermissions("sys:role:addDept")
	@RequestMapping(value = "createDept",method = {RequestMethod.GET,RequestMethod.POST})
	public String createDept(HttpServletRequest request,String parentCode,String deptType){
		Dept dept = new Dept();
		if("dept".equals(deptType)){
			Dept dept1 = deptService.getOneDept(parentCode);
			dept.setCompanyCode(dept1.getCompanyCode());
			dept.setParentCode(parentCode);
		}else if("carShop".equals(deptType)){
//			CarShops carShops = carShopsService.getOneCarShops(parentCode);
			dept.setCompanyCode(parentCode);
		}
		request.setAttribute("action","saveOrUpdate1");
		request.setAttribute("dept",dept);
		return "system/deptForm";
	}

	@RequestMapping(value = "saveOrUpdate1", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveDept(HttpServletRequest request) {
		Dept dept = this.getEntity4Request1(request);
		long id = 0l;
		String result = "false";
		if(dept.getId()!=null){
			result = deptService.updateDept(dept);
		}else{
			result = deptService.saveDept(dept);
		}
		return result;
	}

	/**
	 * 获取部门通过request
	 */
	public Dept getEntity4Request1(HttpServletRequest request) {
		Dept entity=new Dept();
		if(StringUtils.isNotBlank(request.getParameter("deptId"))){
			entity.setId(Long.valueOf(request.getParameter("deptId")));
		}
		if(StringUtils.isNotBlank(request.getParameter("deptCode"))){
			entity.setDeptCode(request.getParameter("deptCode"));
		}
		if(StringUtils.isNotBlank(request.getParameter("deptName"))){
			entity.setDeptName(request.getParameter("deptName"));
		}
		if(StringUtils.isNotBlank(request.getParameter("parentCode"))){
			entity.setParentCode(request.getParameter("parentCode"));
		}
		if(StringUtils.isNotBlank(request.getParameter("companyCode"))){
			entity.setCompanyCode(request.getParameter("companyCode"));
		}
		return entity;
	}




}
