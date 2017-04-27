package com.lte.admin.system.dao;

import java.util.List;

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
}
