package com.lte.admin.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.entity.Bm;
import com.lte.admin.entity.Gwxx;
import com.lte.admin.entity.Role;

/**
 * 角色DAO
 * 
 * @author ty
 * @date 2015年1月13日
 */
@Repository
public class GwxxDao extends BaseDao {

	public Role getRole(Long roleId) {
		return sqlSessionTemplate.selectOne("getRoleById", roleId);
	}

	public List<Gwxx> getRoleList(PageBounds pb, String id, String gsbz) {
		if (gsbz.equals("02")) {
			return sqlSessionTemplate.selectList("findRoleListByBm", id, pb);
		} else {
			return sqlSessionTemplate.selectList("findRoleListByBmcode", id, pb);
		}
	}

	public List<Gwxx> getRoleListByBmAndJobName(String gscode, String gwname) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("gscode", gscode);
		map.put("gwname", gwname);
		return sqlSessionTemplate.selectList("findRoleListByBmAndJobName", map);
	}

	public List<Bm> getBmListTree(String id) {
		if (id != null && !id.equals("")) {
			return sqlSessionTemplate.selectList("findBmListTree", id);
		} else {
			return sqlSessionTemplate.selectList("findBmListTreeRoot");
		}

	}

	public int updateSyncGwxx(String timestr) {
		return sqlSessionTemplate.update("updateSyncGwxx", timestr);
	}

	public int updateSyncGsxx(String timestr) {
		sqlSessionTemplate.update("updateSyncGsxx", timestr);
		return sqlSessionTemplate.update("updateSyncGsxxCode", timestr);
	}

	public int updateSyncBmxx(String timestr) {
		return sqlSessionTemplate.update("updateSyncBmxx", timestr);
	}

}
