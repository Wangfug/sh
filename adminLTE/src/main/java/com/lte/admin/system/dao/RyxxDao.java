package com.lte.admin.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lte.admin.entity.*;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.dao.BaseDao;

/**
 * 用户DAO
 * 
 * @author ty
 * @date 2015年1月13日
 */
@Repository
public class RyxxDao extends BaseDao {

	public Ryxx findByloginName(String psncode) {
//		Ryxx ryxx= new Ryxx();
//		Member member =
		return sqlSessionTemplate.selectOne("com.lte.admin.mapper.MemberMapper.selectBymemberCode1", psncode);
		// return sqlSessionTemplate.selectOne("findByloginName", loginName);
	}

	public List<Gwxx> getRyxxRoles(String id) {
		return sqlSessionTemplate.selectList("getRyxxRolesByRyxxId", id);
	}

	public Ryxx getRyxxById(Long assignee) {
		Ryxx ryxx = new Ryxx();
		Member member = sqlSessionTemplate.selectOne("com.lte.admin.mapper.MemberMapper.selectByPrimaryKey", assignee);
		ryxx.setMemberName(member.getMemberName());
		ryxx.setPsnname(member.getMemberCode());
		ryxx.setMobile(member.getMobile());
		return ryxx;
	}

	public List<Ryxx> getRyxxList(PageBounds pb, Map<String, Object> filters) {
		return sqlSessionTemplate.selectList("findRyxxList", filters, pb);

	}

	public List<Ryxx> findRyxxByJob(String pkOmJob) {
		return sqlSessionTemplate.selectList("findRyxxByJob", pkOmJob);
	}

	public void save(Ryxx ryxx) {
		sqlSessionTemplate.insert("com.lte.admin.mapper.MemberMapper.saveRyxx", ryxx);
	}

	public void delete(Long id) {
		sqlSessionTemplate.delete("com.lte.admin.mapper.MemberMapper.deleteByPrimaryKey", id);

	}

	public void update(Ryxx ryxx) {
		Member member = new Member();
		member.setId(Long.valueOf(ryxx.getId()));
		member.setMemberName(ryxx.getMemberName());
		member.setMemberCode(ryxx.getPsnname());
		member.setMobile(ryxx.getMobile());
		sqlSessionTemplate.update("com.lte.admin.mapper.MemberMapper.updateByPrimaryKeySelective", member);

	}

	public List<RyxxLogin> getRygwList(String id, String loginName) {
		Map<String, String> search = new HashMap<String, String>();
		search.put("psncode", loginName);
		search.put("pk_psnbasdoc", id);
		return sqlSessionTemplate.selectList("findRygwList", search);
	}

	public Ryxx getRyxxByKey(RyxxKey key) {
		return sqlSessionTemplate.selectOne("findRyxxByKey", key);
	}

	public List<UserRole> getRoleByUserId(String userId) {
		return sqlSessionTemplate.selectList("getUserRolesByUserId", userId);
	}

	public void updatePwd(Ryxx ryxx) {
		sqlSessionTemplate.update("com.lte.admin.mapper.MemberMapper.updatePwd", ryxx);

	}

	public int updateSyncRyxx(String timestr) {
		return sqlSessionTemplate.update("updateSyncRyxx", timestr);
	}

}
