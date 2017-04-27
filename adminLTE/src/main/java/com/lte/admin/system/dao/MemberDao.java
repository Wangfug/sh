package com.lte.admin.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.entity.Member;
import com.lte.admin.entity.MemberJob;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.entity.Ryxx;

@Repository
public class MemberDao extends BaseDao {
	
	public Member findByMemberCode(String memberCode) {
		return sqlSessionTemplate.selectOne(
				"com.lte.admin.mapper.MemberMapper.selectBymemberCode", memberCode);
	}
	
	public List<MemberJob> getMemberJobs(String memberCode) {
		return sqlSessionTemplate.selectList(
				"com.lte.admin.mapper.MemberJobMapper.selectByMemberCode", memberCode);
	}
	
	public List<MemberLogin> getRygwList(String memberCode) {
		return sqlSessionTemplate.selectList(
				"com.lte.admin.mapper.MemberMapper.findRygwList", memberCode);
	}
	
	public MemberLogin findByByMemberCodeAndJobCode(Map<String, String> map) {
		return sqlSessionTemplate.selectOne(
				"com.lte.admin.mapper.MemberMapper.findByByMemberCodeAndJobCode", map);
	}
	
	public List<MemberLogin> getMemberList(PageBounds pb, Map<String, Object> filters) {
		return sqlSessionTemplate.selectList("com.lte.admin.mapper.MemberMapper.getMemberList", filters, pb);

	}
}
