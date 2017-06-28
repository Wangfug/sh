package com.lte.admin.system.dao;

import java.util.List;
import java.util.Map;

import com.lte.admin.entity.StaffJob;
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

	public StaffJob getStaffJob(Long roleId) {
		return sqlSessionTemplate.selectOne("com.lte.admin.mapper.StaffJobMapper.getStaffJobById", roleId);
	}

	public List<StaffJob> getStaffJobList(PageBounds pb, Map<String, Object> filters) {
		return sqlSessionTemplate.selectList("com.lte.admin.mapper.StaffJobMapper.findStaffJobList", filters, pb);
	}
	public List<Map<String,Object>> getStaffJobList() {
		return sqlSessionTemplate.selectList("com.lte.admin.mapper.StaffJobMapper.findStaffJobList2");
	}
	public List<Map> getStaffJobList1(PageBounds pb, Map<String, Object> filters) {
		return sqlSessionTemplate.selectList("com.lte.admin.mapper.StaffJobMapper.findStaffJobList1", filters, pb);
	}
	public List<Dept> getBmListTree(String parentCode) {
		return sqlSessionTemplate.selectList("com.lte.admin.mapper.DeptMapper.findBmListTree", parentCode);
	}
	
	public void save(StaffJob role) {
		sqlSessionTemplate.insert("com.lte.admin.mapper.StaffJobMapper.saveStaffJob", role);

	}

	public void delete(Integer id) {
		sqlSessionTemplate.delete("com.lte.admin.mapper.StaffJobMapper.delStaffJob", id);

	}

	public void update(StaffJob role) {
		sqlSessionTemplate.update("com.lte.admin.mapper.StaffJobMapper.updateStaffJob", role);

	}

	public List<StaffJob> getStaffJobListAll(PageBounds pb, Map<String, Object> filters) {
		return sqlSessionTemplate.selectList("com.lte.admin.mapper.StaffJobMapper.findStaffJobListAll", filters, pb);
	}

	public List<Dept> getDeptListTree() {
		return sqlSessionTemplate.selectList("com.lte.admin.mapper.DeptMapper.getDeptListTree");
	}

    public List<Map<String,Object>> getStaffJobListBySP(String shopCode) {
		return sqlSessionTemplate.selectList("com.lte.admin.mapper.StaffJobMapper.getStaffJobListBySP",shopCode);
    }
}
