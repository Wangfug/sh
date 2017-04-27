package com.lte.admin.system.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import com.lte.admin.common.web.BaseController;
import com.lte.admin.entity.Bm;
import com.lte.admin.entity.GwPermission;
import com.lte.admin.entity.Gwxx;
import com.lte.admin.entity.Role;
import com.lte.admin.entity.Ryxx;
import com.lte.admin.system.service.GwxxService;
import com.lte.admin.system.service.RolePermissionService;

/**
 * 岗位角色controller
 * 
 * @author ty
 * @date 2015年1月13日
 */
@Controller
@RequestMapping("system/gwxx")
public class GwxxController extends BaseController {

	@Autowired
	private GwxxService roleService;

	@Autowired
	private RolePermissionService rolePermissionService;

	/**
	 * 默认页面
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "system/gwxxList";
	}

	/**
	 * 岗位集合(JSON)
	 */
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request,
			@RequestParam(value = "bmid", defaultValue = "") String id,
			@RequestParam(value = "gsbz", defaultValue = "") String gsbz) {
		Page<Gwxx> page = getPage(request);
		if (id.equals("")) {
			return new HashMap<String, Object>();
		}
		PageList<Gwxx> page1 = roleService.search(page, id, gsbz);
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
	 * 修改角色权限
	 * 
	 * @param id
	 * @param newRoleList
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
	 * 添加角色跳转
	 * 
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:role:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("role", new Role());
		model.addAttribute("action", "create");
		return "system/gwxxForm";
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
	public String create(@Valid Role role, Model model) {
		// roleService.save(role);
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
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		// model.addAttribute("role", roleService.get(id));
		model.addAttribute("action", "update");
		return "system/gwxxForm";
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
	public String update(@Valid @ModelAttribute("role") Role role, Model model) {
		// roleService.save(role);
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
	public String delete(@PathVariable("id") Integer id) {
		// roleService.delete(id);
		return "success";
	}

	@ModelAttribute
	public void getRole(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
		if (id != -1) {
			// model.addAttribute("role", roleService.get(id));
		}
	}

	@RequestMapping(value = "bmlist", method = RequestMethod.GET)
	@ResponseBody
	public List<TreeNode> bmlist(@RequestParam(value = "id", defaultValue = "") String id) {
		List<Bm> bmlist = roleService.getBmListTree(id);

		List<TreeNode> treeList = new ArrayList<TreeNode>();
		for (Bm bm : bmlist) {
			TreeNode e = new TreeNode();
			e.setId(bm.getBm01());
			// e.setText(bm.getBm02());
			e.setText(bm.getBm02() + "(" + bm.getBm01() + ")");
			Map<String, String> attributes = new HashMap<String, String>();

			if (bm.getBm06() == null) {
				attributes.put("gsbz", "01");
			} else {
				attributes.put("gsbz", bm.getBm06());
			}
			if (bm.getBm04().equals("1")) {
				e.setState("open");
			} else {
				e.setState("closed");
				if (bm.getBm06() != null && bm.getBm06().equals("02")) {
					e.setState("open");
				}
			}

			e.setAttributes(attributes);
			treeList.add(e);
		}
		return treeList;
	}
}
