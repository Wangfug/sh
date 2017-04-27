package com.lte.admin.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.entity.UserRole;

/**
 * 用户角色DAO
 * 
 * @author ty
 * @date 2015年1月13日
 */
@Repository
public class UserRoleDao extends BaseDao {

	/**
	 * 删除用户角色
	 * 
	 * @param userId
	 * @param roleId
	 */
	public void deleteUR(String userId, Long roleId) {
		UserRole ur = new UserRole();
		ur.setRoleId(roleId);
		ur.setUserId(userId);
		sqlSessionTemplate.delete("delUserRolesByUser", ur);
		// String hql="delete UserRole ur where ur.user.id=?0 and
		// ur.role.id=?1";
		// batchExecute(hql, userId,roleId);
	}

	/**
	 * 查询用户拥有的角色id集合
	 * 
	 * @param userId
	 * @return 结果集合
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> findRoleIds(String userId) {
		return null;
		// String hql="select ur.role.id from UserRole ur where ur.user.id=?0";
		// Query query= createQuery(hql, userId);
		// return query.list();
	}

	public List<UserRole> findRoleListByUserId(String userId) {
		return sqlSessionTemplate.selectList("getUserRolesByUserId", userId);
	}

	public void save(UserRole userRole) {
		sqlSessionTemplate.insert("saveUserRolesByUser", userRole);

	}

	public List<UserRole> findRoleListByUserIdandroleid(String userId, Long roleid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userId);
		map.put("roleid", roleid + "");
		return sqlSessionTemplate.selectList("getUserRolesByUserIdandroleid", map);
	}

}
