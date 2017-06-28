package com.lte.admin.system.web;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.lte.admin.car.entity.CarShops;
import com.lte.admin.car.service.CarShopsService;
import com.lte.admin.entity.*;
import com.lte.admin.other.entity.TbaseCompany;
import com.lte.admin.other.service.TbaseCompanyService;
import com.lte.admin.system.service.DeptService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.mapper.TreeNode;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.persistence.PropertyFilter;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.system.service.RolePermissionService;
import com.lte.admin.system.service.RoleService;

/**
 * 岗位角色controller
 *
 * @author ty
 * @date 2015年1月13日
 */
@Controller
@RequestMapping("system/role")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private RolePermissionService rolePermissionService;
	@Autowired
	private TbaseCompanyService tbaseCompanyService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private CarShopsService carShopsService;
	/**
	 * 默认页面
	 *
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "system/roleList";
	}

	/**
	 * 角色集合(JSON)
	 */
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Page<StaffJob> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		PageList<StaffJob> page1 = roleService.searchall(page, filters);
		return getEasyUIData(page1, request);
	}

	/**
	 * 角色集合(JSON)
	 */
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "jsona", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getDataA(HttpServletRequest request,String deptCode) {
		Page<StaffJob> page = getPage(request);
		Map<String, Object> filters = PropertyFilter.buildFromHttpRequest(request);
		filters.put("deptCode",deptCode);
//		PageList<StaffJob> page1 = roleService.search(page, filters);
		PageList<Map> page1 = roleService.search1(page, filters);
		return getEasyUIData(page1, request);
	}

	/**
	 * 获取角色拥有的权限ID集合
	 *
	 * @param id
	 * @return
	 */
	@RequiresPermissions("sys:role:permView")
	@RequestMapping("{id}/json")
	@ResponseBody
	public List<GwPermission> getRolePermissions(@PathVariable("id") String id) {
		List<GwPermission> permissionIdList = rolePermissionService.getPermissionIds(id);
		return permissionIdList;
	}
	/**
	 * 获取角色拥有的权限ID集合
	 *
	 * @param id
	 * @return
	 */
	/*@RequiresPermissions("sys:role:permView")
	@RequestMapping("{id}/json")
	@ResponseBody
	public List<GwPermission> getRolePermissions(@PathVariable("id") String id) {
		List<GwPermission> permissionIdList = rolePermissionService.getPermissionIds(id);
		return permissionIdList;
	}*/



	/**
	 * 修改角色权限
	 *
	 * @param id
	 * @param
	 * @return
	 */
	@RequiresPermissions("sys:role:permUpd")
	@RequestMapping(value = "{id}/updatePermission")
	@ResponseBody
	public String updateRolePermission(@PathVariable("id") String id, @RequestBody List<Long> newRoleIdList,
			HttpSession session) {
		List<GwPermission> oldRoleIdList = rolePermissionService.getPermissionIds(id);

		// 获取application中的sessions
		@SuppressWarnings("rawtypes")
		HashSet sessions = (HashSet) session.getServletContext().getAttribute("sessions");
		if (null != sessions) {// 当前如果有正在使用的用户，需要更新正在使用的用户的权限

			@SuppressWarnings("unchecked")
			Iterator<Session> iterator = sessions.iterator();
			PrincipalCollection pc = null;

			// 遍历sessions
			while (iterator.hasNext()) {
				HttpSession s = (HttpSession) iterator.next();
				Ryxx user = (Ryxx) s.getAttribute("user");
				// if(user.getId()==id){
				pc = (PrincipalCollection) s.getAttribute(user.getPkPsnbasdoc());
				// 清空该用户权限缓存
				rolePermissionService.clearUserPermCache(pc);
				s.removeAttribute(user.getPkPsnbasdoc());
				break;
				// }
			}
		}

		rolePermissionService.updateRolePermission(id, oldRoleIdList, newRoleIdList);

		return "success";
	}
	/**
	 * 修改角色权限
	 *
	 * @param id
	 * @param
	 * @return
	 */
	/*@RequiresPermissions("sys:role:permUpd")
	@RequestMapping(value = "{id}/updatePermission")
	@ResponseBody
	public String updateRolePermission(@PathVariable("id") String id, @RequestBody List<Long> newRoleIdList,
									   HttpSession session) {
		List<GwPermission> oldRoleIdList = rolePermissionService.getPermissionIds(id);

		// 获取application中的sessions
		@SuppressWarnings("rawtypes")
		HashSet sessions = (HashSet) session.getServletContext().getAttribute("sessions");
		if (null != sessions) {// 当前如果有正在使用的用户，需要更新正在使用的用户的权限

			@SuppressWarnings("unchecked")
			Iterator<Session> iterator = sessions.iterator();
			PrincipalCollection pc = null;

			// 遍历sessions
			while (iterator.hasNext()) {
				HttpSession s = (HttpSession) iterator.next();
				Ryxx user = (Ryxx) s.getAttribute("user");
				// if(user.getId()==id){
				pc = (PrincipalCollection) s.getAttribute(user.getPkPsnbasdoc());
				// 清空该用户权限缓存
				rolePermissionService.clearUserPermCache(pc);
				s.removeAttribute(user.getPkPsnbasdoc());
				break;
				// }
			}
		}

		rolePermissionService.updateRolePermission(id, oldRoleIdList, newRoleIdList);

		return "success";
	}*/

	/**
	 * 添加角色跳转
	 * 
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:role:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("role", new StaffJob());
		model.addAttribute("action", "create");
		return "system/roleForm";
	}

	/**
	 * 添加角色
	 * 
	 * @param role
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:role:add")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid StaffJob role, Model model) {
		roleService.save(role);
		return "success";
	}

	/**
	 * 修改角色跳转
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:role:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("role", roleService.get(id));
		model.addAttribute("action", "update");
		return "system/roleForm";
	}

	/**
	 * 修改角色
	 * 
	 * @param role
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:role:update")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@Valid @ModelAttribute("role") StaffJob role, Model model) {
		roleService.update(role);
		return "success";
	}

	/**
	 * 删除角色
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("sys:role:delete")
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") Long id) {
		roleService.delete(id);
		return "success";
	}

	@ModelAttribute
	public void getRole(@RequestParam(value = "id", defaultValue = "-1") String id, Model model) {
//		if (id != -1) {
//			// model.addAttribute("role", roleService.get(id));
//		}
	}

	@RequestMapping(value = "bmlist", method = RequestMethod.GET)
	@ResponseBody
	public List<TreeNode> bmlist(@RequestParam(value = "id", defaultValue = "") String id,@RequestParam(value = "type", defaultValue = "")String type) {
		List<TreeNode> treeList = new ArrayList<TreeNode>();
		if (StringUtils.isBlank(id)) {
			//门店取代公司
//			List<Company> companyList = tbaseCompanyService.getCompanyListTree();
			List<CarShops> companyRootList = carShopsService.getCarShopListTree();
			for (CarShops carShops : companyRootList) {
				TreeNode e = new TreeNode();
				e.setId(carShops.getShopCode());
				e.setText(carShops.getShopName());
				e.setState("closed");
				Map<String,String> attrs = new HashMap<String,String>();
				attrs.put("type","公司");
				e.setAttributes(attrs);
				treeList.add(e);
			}
		} else {
			if("公司".equals(type)){
				//根据公司找子公司
//				List<TbaseCompany> companyList = tbaseCompanyService.getCompanyListTreeByParent(id);
				List<CarShops> carShopsSubList = carShopsService.getCarShopsListTreeByParent(id);
				for (CarShops carShops : carShopsSubList) {
					TreeNode e = new TreeNode();
					e.setId(carShops.getShopCode());
					e.setText(carShops.getShopName());
					e.setState("closed");
					Map<String,String> attrs = new HashMap<String,String>();
					attrs.put("type","公司");
					e.setAttributes(attrs);
					treeList.add(e);
				}
				//根据公司找部门
				List<Dept> bmlist = deptService.getDeptListByCompany(id);
				for (Dept dept : bmlist) {
					TreeNode e = new TreeNode();
					e.setId(dept.getDeptCode());
					e.setText(dept.getDeptName());
					e.setState("closed");
					e.setIconCls("tree-file");
					Map<String,String> attrs = new HashMap<String,String>();
					attrs.put("type","部门");
					attrs.put("comCode",dept.getCompanyCode());
					e.setAttributes(attrs);
					treeList.add(e);
				}
			}else if("部门".equals(type)){
				//根据部门找子部门
				List<Dept> bmlist = deptService.getBmListTreeByParent(id);
				for (Dept dept : bmlist) {
					TreeNode e = new TreeNode();
					e.setId(dept.getDeptCode());
					e.setText(dept.getDeptName());
					e.setState("closed");
					e.setIconCls("tree-file");
					Map<String,String> attrs = new HashMap<String,String>();
					attrs.put("type","部门");
					attrs.put("comCode",dept.getCompanyCode());
					e.setAttributes(attrs);
					treeList.add(e);
				}
			}
		}
		return treeList;
	}
}
