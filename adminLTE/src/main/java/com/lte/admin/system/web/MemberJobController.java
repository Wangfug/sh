package com.lte.admin.system.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.mapper.TreeNode;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.web.BaseController;
import com.lte.admin.entity.Company;
import com.lte.admin.entity.StaffJob;
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
	
	
	/**
	 * 默认页面
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "system/memberJobList";
	}

	@RequestMapping(value = "bmlist", method = RequestMethod.GET)
	@ResponseBody
	public List<TreeNode> bmlist() {
		List<TreeNode> treeList = new ArrayList<TreeNode>();
		List<Company> companyList = roleService.getCompanyListTree();
		for (Company company : companyList) {
			TreeNode e = new TreeNode();
			e.setId(company.getCompanyCode());
			e.setText(company.getCompanyName());
			e.setState("open");

			treeList.add(e);
		}			

		return treeList;
	}
	
	/**
	 * 岗位集合(JSON)
	 */
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request,
			@RequestParam(value = "deptCode", defaultValue = "") String deptCode) {
		Page<StaffJob> page = getPage(request);
		if (deptCode.equals("")) {
			return new HashMap<String, Object>();
		}
		PageList<StaffJob> page1 = memberJobService.search(page, deptCode);
		return getEasyUIData(page1, request);
	}
}
