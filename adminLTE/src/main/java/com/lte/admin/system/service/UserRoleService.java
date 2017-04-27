package com.lte.admin.system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lte.admin.common.service.BaseService;
import com.lte.admin.entity.UserRole;
import com.lte.admin.system.dao.UserRoleDao;

/**
 * 用户角色service
 * 
 * @author ty
 * @date 2015年1月14日
 */
@Service
@Transactional(readOnly = true)
public class UserRoleService extends BaseService<UserRole, Integer> {

	@Autowired
	private UserRoleDao userRoleDao;

	/**
	 * 添加修改用户角色
	 * 
	 * @param id
	 * @param oldList
	 * @param newList
	 */
	@Transactional(readOnly = false)
	public void updateUserRole(String userId, List<UserRole> oldList, List<Long> newList) {
		List<Long> oldidList = new ArrayList<Long>();
		for (UserRole rp1 : oldList) {
			oldidList.add(rp1.getRoleId());
		}
		// 是否删除
		for (int i = 0, j = oldidList.size(); i < j; i++) {
			if (!newList.contains(oldidList.get(i))) {
				userRoleDao.deleteUR(userId, oldidList.get(i));
			}
		}

		// 是否添加
		for (int m = 0, n = newList.size(); m < n; m++) {
			if (!oldidList.contains(newList.get(m))) {
				userRoleDao.save(getUserRole(userId, newList.get(m)));
			}
		}
	}

	/**
	 * 构建UserRole
	 * 
	 * @param userId
	 * @param roleId
	 * @return UserRole
	 */
	private UserRole getUserRole(String userId, Long roleId) {
		UserRole ur = new UserRole();
		ur.setUserId(userId);
		ur.setRoleId(roleId);
		return ur;
	}

	/**
	 * 获取用户拥有角色id集合
	 * 
	 * @param userId
	 * @return 结果集合
	 */
	public List<UserRole> findRoleListByUserId(String userId) {
		return userRoleDao.findRoleListByUserId(userId);
	}

}
