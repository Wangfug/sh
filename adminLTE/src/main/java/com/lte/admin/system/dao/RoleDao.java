package com.lte.admin.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.entity.Company;
import com.lte.admin.entity.Dept;
import com.lte.admin.entity.Role;

/**
 * 角色DAO
 * 
 * @author ty
 * @date 2015年1月13日
 */
@Repository
public class RoleDao extends BaseDao {

	public Role getRole(Long roleId) {
		return sqlSessionTemplate.selectOne("getRoleById", roleId);
	}

	public List<Role> getRoleList(PageBounds pb, Map<String, Object> filters) {
		return sqlSessionTemplate.selectList("findRoleList", filters, pb);
	}

	public List<Dept> getBmListTree(String parentCode) {
		return sqlSessionTemplate.selectList("com.lte.admin.mapper.DeptMapper.findBmListTree", parentCode);
	}
	
	public List<Company> getCompanyListTree() {
		return sqlSessionTemplate.selectList("com.lte.admin.mapper.DeptMapper.findBmListTreeRoot");
	}

	public void save(Role role) {
		sqlSessionTemplate.insert("saveRole", role);

	}

	public void delete(Integer id) {
		sqlSessionTemplate.delete("delRole", id);

	}

	public void update(Role role) {
		sqlSessionTemplate.update("updateRole", role);

	}

	public List<Role> getRoleListAll(PageBounds pb, Map<String, Object> filters) {
		return sqlSessionTemplate.selectList("findRoleListAll", filters, pb);
	}

}
