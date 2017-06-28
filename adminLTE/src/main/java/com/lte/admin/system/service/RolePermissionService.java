package com.lte.admin.system.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lte.admin.common.service.BaseService;
import com.lte.admin.entity.GwPermission;
import com.lte.admin.entity.RolePermission;
import com.lte.admin.system.dao.RolePermissionDao;

/**
 * 角色权限service
 * 
 * @author ty
 * @date 2015年1月13日
 */
@Service
@Transactional(readOnly = true)
public class RolePermissionService extends BaseService<RolePermission, Integer> {

	@Autowired
	private RolePermissionDao rolePermissionDao;

	/**
	 * 获取角色权限id集合
	 * 
	 * @param
	 * @return List
	 */
	public List<GwPermission> getPermissionIds(String roleId) {
		return rolePermissionDao.findPermissionIds(roleId);
	}

	/**
	 * 修改角色权限
	 * 
	 * @param id
	 * @param oldList
	 * @param newList
	 */
	@Transactional(readOnly = false)
	public void updateRolePermission(String id, List<GwPermission> oldList, List<Long> newList) {
		List<Long> oldidList = new ArrayList<Long>();
		for (GwPermission rp1 : oldList) {
			oldidList.add(rp1.getPermissionId());
		}
		// 是否删除
		for (int i = 0, j = oldList.size(); i < j; i++) {
			if (!newList.contains(oldidList.get(i))) {
				rolePermissionDao.deleteRP(id, oldidList.get(i));
			}
		}

		// 是否添加
		for (int i = 0, j = newList.size(); i < j; i++) {
			if (!oldidList.contains(newList.get(i))) {
				rolePermissionDao.save(getRolePermission(id, newList.get(i)));
			}
		}
	}

	/**
	 * 清空该角色用户的权限缓存
	 */
	public void clearUserPermCache(PrincipalCollection pc) {
//		RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
//		RyxxRealm userRealm = (RyxxRealm) securityManager.getRealms().iterator().next();
//		userRealm.clearCachedAuthorizationInfo(pc);
	}
	/**
	 * 赋予角色
	 */
	/*public void save(String member,String job) {
		rolePermissionDao.save(getRolePermission(id, newList.get(i)));
	}
	*/

	/**
	 * 构造角色权限对象
	 * 
	 * @param roleId
	 * @param permissionId
	 * @return RolePermission
	 */
	private GwPermission getRolePermission(String roleId, Long permissionId) {
		GwPermission rp = new GwPermission();
		rp.setPkOmJob(roleId);
		rp.setPermissionId(permissionId);
		return rp;
	}

}
