package com.lte.admin.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.entity.Company;
import com.lte.admin.entity.Dept;
import com.lte.admin.entity.Role;
import com.lte.admin.system.dao.RoleDao;

/**
 * 角色service
 * 
 * @author ty
 * @date 2015年1月13日
 */
@Service
@Transactional(readOnly = true)
public class RoleService extends BaseService<Role, Integer> {

	@Autowired
	private RoleDao roleDao;

	public Role getRole(Long roleId) {
		return roleDao.getRole(roleId);
	}

	public PageList<Role> searchall(Page<Role> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);

		return (PageList<Role>) roleDao.getRoleListAll(pb, filters);
	}

	public List<Company> getCompanyListTree() {
		return roleDao.getCompanyListTree();
	}
	
	public List<Dept> getBmListTree(String id) {
		return roleDao.getBmListTree(id);
	}

	public void save(Role role) {
		role.setDelFlag("0");
		roleDao.save(role);

	}

	public void delete(Long id) {
		Role role = new Role();
		role.setId(id);
		role.setDelFlag("1");
		roleDao.update(role);

	}

	public void update(Role role) {
		roleDao.update(role);

	}

	public Role get(Long id) {
		return roleDao.getRole(id);
	}

	public PageList<Role> search(Page<Role> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);

		return (PageList<Role>) roleDao.getRoleList(pb, filters);
	}

}
