package com.lte.admin.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.entity.Permission;

/**
 * 权限DAO
 * 
 * @author ty
 * @date 2015年1月13日
 */
@Repository
public class PermissionDao extends BaseDao {

	/**
	 * 查询用户拥有的权限
	 * 
	 * @param userId
	 *            用户id
	 * @return 结果集合
	 */
	public List<Permission> findPermissions(String userId) {
		return sqlSessionTemplate.selectList("findPermissionsByUserId", userId);
	}

	/**
	 * 查询所有的菜单
	 * @param userId
	 * @return 菜单集合
	 */
	public List<Permission> findMenus(){
		return sqlSessionTemplate.selectList("com.lte.admin.mapper.PermissionMapper.findPermissionsMenus");
	}
	
	/**
	 * 查询菜单下的操作权限
	 * 
	 * @param userId
	 * @return
	 */
	public List<Permission> findMenuOperation(Integer pid) {
		return sqlSessionTemplate.selectList("findMenuOperation", pid);
	}

	public Permission get(Long id) {
		return sqlSessionTemplate.selectOne("findPermissionsById", id);
	}

	public List<Permission> getAll() {
		return sqlSessionTemplate.selectList("com.lte.admin.mapper.PermissionMapper.getAllPermissions");
	}

	public void save(Permission permission) {
		sqlSessionTemplate.insert("com.lte.admin.mapper.PermissionMapper.insertSelective", permission);
	}

	public void delete(Long id) {
//		sqlSessionTemplate.delete("delPermissionsgw", id);
//		sqlSessionTemplate.delete("delPermissions", id);
		sqlSessionTemplate.delete("deletePermissionById", id);
	}

	public void update(Permission permission) {
		sqlSessionTemplate.update("updateByPrimaryKeyWithBLOBs", permission);

	}

	public List<Permission> findPermissionsmenu(Map<String, String> m) {
		return sqlSessionTemplate.selectList("com.lte.admin.mapper.PermissionMapper.findPermissionsForMenu", m);
	}
}
