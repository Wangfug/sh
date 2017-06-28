package com.lte.admin.system.dao;

import java.util.List;
import java.util.Map;

import com.lte.admin.common.persistence.Page;
import com.lte.admin.entity.Dept;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.entity.StaffJob;

@Repository
public class MemberJobDao extends BaseDao {

	public List<StaffJob> getJobList(PageBounds pb, String companyCode) {
		return sqlSessionTemplate.selectList(
				"com.lte.admin.mapper.StaffJobMapper.getJobsByCompanyCode", companyCode);
	}

	/**
	 * 根据部门获取职位
	 * @param pb 分页参数
	 * @param filters 查询条件
	 * @return
	 */
	public List<StaffJob> getJobList1(PageBounds pb, Map<String,Object> filters) {
		return sqlSessionTemplate.selectList(
				"com.lte.admin.mapper.StaffJobMapper.getJobsByFiter",filters,pb);
	}
	public List<StaffJob> getJobList1(PageBounds pb) {
		return sqlSessionTemplate.selectList(
				"com.lte.admin.mapper.StaffJobMapper.getJobs","",pb);
	}

	public StaffJob getOneStaffJob(long id) {
		return sqlSessionTemplate.selectOne(
				"com.lte.admin.mapper.StaffJobMapper.selectByPrimaryKey", id);
	}

	public String updateStaffJob(StaffJob staffJob) {
		String result = "error";
		int res = sqlSessionTemplate.insert(
				"com.lte.admin.mapper.StaffJobMapper.updateByPrimaryKeySelective", staffJob);
		if(res==1)
			result = "success";
		return result;
	}
	public String saveStaffJob(StaffJob staffJob) {
		String result = "error";
		int res = sqlSessionTemplate.insert(
				"com.lte.admin.mapper.StaffJobMapper.insertSelective", staffJob);
		if(res==1)
			result = "success";
		return result;
	}

    public List<Dept> getDeptList(PageBounds page, Map<String, Object> filters) {
		return sqlSessionTemplate.selectList(
				"com.lte.admin.mapper.DeptMapper.getJobsByDeptCode",filters,page);
    }

	public Map getOneStaffJob1(long id) {
		return sqlSessionTemplate.selectOne(
				"com.lte.admin.mapper.StaffJobMapper.selectByPrimaryKey1", id);
	}

    public StaffJob getOneStaffJobByCode(String gwxx) {
		return sqlSessionTemplate.selectOne(
				"com.lte.admin.mapper.StaffJobMapper.getOneStaffJobByCode", gwxx);
    }
}
