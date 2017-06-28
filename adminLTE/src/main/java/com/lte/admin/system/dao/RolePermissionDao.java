package com.lte.admin.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.entity.GwPermission;

/**
 * 角色权限DAO
 * 
 * @author ty
 * @date 2015年1月13日
 */
@Repository
public class RolePermissionDao extends BaseDao {

	/**
	 * 查询角色拥有的权限id
	 * 
	 * @param jobCode
	 * @return 结果集合
	 */
	@SuppressWarnings("unchecked")
	public List<GwPermission> findPermissionIds(String jobCode) {
		return sqlSessionTemplate.selectList("com.lte.admin.mapper.JobPermissionMapper.findPermissionsByGw", jobCode);
		// String hql="select rp.permission.id from RolePermission rp where
		// rp.role.id=?0";
		// Query query= createQuery(hql, roleId);
		// return query.list();
	}

	/**
	 * 删除角色权限
	 * 
	 * @param id
	 * @param permissionId
	 */
	public void deleteRP(String id, Long permissionId) {
		GwPermission rp = new GwPermission();
		rp.setPermissionId(permissionId);
		rp.setPkOmJob(id);
		sqlSessionTemplate.delete("com.lte.admin.mapper.JobPermissionMapper.deleteGP", rp);
		// String hql="delete RolePermission rp where rp.role.id=?0 and
		// rp.permission.id=?1";
		// batchExecute(hql, roleId,permissionId);
	}

	public void save(GwPermission gwPermission) {
		sqlSessionTemplate.insert("com.lte.admin.mapper.JobPermissionMapper.insertSelective", gwPermission);
	}

	public List<GwPermission> findPermissionsByGwAndPID(String job, String permissionId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("job", job);
		map.put("permissionId", permissionId);
		return sqlSessionTemplate.selectList("com.lte.admin.mapper.JobPermissionMapper.findPermissionsByGwAndPID", map);
	}

}
