package com.lte.admin.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lte.admin.common.service.BaseService;
import com.lte.admin.entity.Permission;
import com.lte.admin.system.dao.PermissionDao;

/**
 * 权限service
 * 
 * @author ty
 * @date 2015年1月13日
 */
@Service
@Transactional
public class PermissionService extends BaseService<Permission, Integer> {

	@Autowired
	private PermissionDao permissionDao;

	/**
	 * 获取角色拥有的权限集合
	 * 
	 * @param userId
	 * @return 结果集合
	 */
	public List<Permission> getPermissions(String userId) {
		return permissionDao.findPermissions(userId);
	}


	/**
	 * 获取所有菜单
	 * 
	 * @return 菜单集合
	 */
	public List<Permission> getMenus() {
		return permissionDao.findMenus();
	}

	/**
	 * 获取菜单下的操作
	 * 
	 * @param pid
	 * @return 操作集合
	 */
	public List<Permission> getMenuOperation(Integer pid) {
		return permissionDao.findMenuOperation(pid);
	}

	public Permission get(Long id) {
		return permissionDao.get(id);
	}

	public List<Permission> getAll() {
		return permissionDao.getAll();
	}

	public void save(Permission permission) {
		permissionDao.save(permission);
	}

	public void delete(Long id) {
		permissionDao.delete(id);

	}

	public void update(Permission permission) {
		permissionDao.update(permission);

	}

	public List<Permission> getPermissionsmenu(String memberCode, String deptCode) {
		Map<String, String> m = new HashMap<String, String>();
		m.put("memberCode", memberCode);
		m.put("deptCode", deptCode);
		return permissionDao.findPermissionsmenu(m);
	}

}
