package com.lte.admin.system.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.entity.Dept;
import com.lte.admin.entity.Dept;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DeptDao extends BaseDao {

	public Dept getOneDept(long id) {
		return sqlSessionTemplate.selectOne(
				"com.lte.admin.mapper.DeptMapper.selectByPrimaryKey", id);
	}
	public Dept getOneDept(String code) {
		return sqlSessionTemplate.selectOne(
				"com.lte.admin.mapper.DeptMapper.selectByPrimaryKey2", code);
	}
	public Dept getOneDept(Map<String,Object> map) {
		return sqlSessionTemplate.selectOne(
				"com.lte.admin.mapper.DeptMapper.selectByPrimaryKey1",map);
	}

	public String updateDept(Dept dept) {
		String result = "error";
		int res = sqlSessionTemplate.insert(
				"com.lte.admin.mapper.DeptMapper.updateByPrimaryKeySelective", dept);
		if(res==1)
			result = "success";
		return result;
	}
	public String saveDept(Dept dept) {
		String result = "error";
		int res = sqlSessionTemplate.insert(
				"com.lte.admin.mapper.DeptMapper.insertSelective", dept);
		if(res==1)
			result = "success";
		return result;
	}

    public List<Dept> getDeptList(PageBounds page, Map<String, Object> filters) {
		return sqlSessionTemplate.selectList(
				"com.lte.admin.mapper.DeptMapper.getAllList",filters,page);
    }
	public List<Dept> getDeptList() {
		return sqlSessionTemplate.selectList(
				"com.lte.admin.mapper.DeptMapper.getAllList");
	}

	public List<Dept> getDeptListByCompany(String comCode) {
		return sqlSessionTemplate.selectList(
				"com.lte.admin.mapper.DeptMapper.getDeptListByCompany",comCode);
	}

	public List<Dept> getBmListTreeByParent(String parentCode) {
		return sqlSessionTemplate.selectList(
				"com.lte.admin.mapper.DeptMapper.getBmListTreeByParent",parentCode);
	}
}
