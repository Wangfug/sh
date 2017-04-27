package com.lte.admin.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.entity.Gwxx;
import com.lte.admin.entity.Ryxx;
import com.lte.admin.entity.RyxxKey;
import com.lte.admin.entity.RyxxLogin;
import com.lte.admin.entity.UserRole;

/**
 * 用户DAO
 * 
 * @author ty
 * @date 2015年1月13日
 */
@Repository
public class RyxxDao extends BaseDao {

	public Ryxx findByloginName(String psncode) {
		return sqlSessionTemplate.selectOne("findByloginNameRyxx", psncode);
		// return sqlSessionTemplate.selectOne("findByloginName", loginName);
	}

	public List<Gwxx> getRyxxRoles(String id) {
		return sqlSessionTemplate.selectList("getRyxxRolesByRyxxId", id);
	}

	public Ryxx getRyxxById(String assignee) {
		return sqlSessionTemplate.selectOne("findByRyxxId", assignee);
	}

	public List<Ryxx> getRyxxList(PageBounds pb, Map<String, Object> filters) {
		return sqlSessionTemplate.selectList("findRyxxList", filters, pb);

	}

	public List<Ryxx> findRyxxByJob(String pkOmJob) {
		return sqlSessionTemplate.selectList("findRyxxByJob", pkOmJob);
	}

	public void save(Ryxx ryxx) {
		sqlSessionTemplate.insert("saveRyxx", ryxx);

	}

	public void delete(Long id) {
		sqlSessionTemplate.delete("deleteRyxx", id);

	}

	public void update(Ryxx ryxx) {
		sqlSessionTemplate.update("updataRyxx", ryxx);

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
		sqlSessionTemplate.update("updatePwd", ryxx);

	}

	public int updateSyncRyxx(String timestr) {
		return sqlSessionTemplate.update("updateSyncRyxx", timestr);
	}

}
