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
	public void deleteUR(String userId, String roleId) {
		UserRole ur = new UserRole();
		ur.setMemberCode(userId);
		ur.setJobCode(roleId);
		sqlSessionTemplate.delete("com.lte.admin.mapper.StaffJobMapper.delUserRolesByUser", ur);
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

	public List<UserRole> findRoleListByUserId(String memberCode) {
		return sqlSessionTemplate.selectList("com.lte.admin.mapper.StaffJobMapper.getUserRolesByUserId", memberCode);
	}

	public void save(UserRole userRole) {
		sqlSessionTemplate.insert("com.lte.admin.mapper.StaffJobMapper.saveUserRolesByUser", userRole);

	}

	public List<UserRole> findRoleListByUserIdandroleid(String userId, Long roleid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userId);
		map.put("roleid", roleid + "");
		return sqlSessionTemplate.selectList("com.lte.admin.mapper.StaffJobMapper.getUserRolesByUserIdandroleid", map);
	}

    public String getDianyuan(String deptCode) {
		return sqlSessionTemplate.selectOne("com.lte.admin.mapper.StaffJobMapper.getDianyuan", deptCode);
    }
}
