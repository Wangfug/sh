package com.lte.admin.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.entity.Zdlx;

/**
 * 字典DAO
 * 
 * @author ty
 * @date 2015年1月13日
 */
@Repository
public class ZdlxDao extends BaseDao {

	public List<Zdlx> getDzlxList(PageBounds pb, Map<String, Object> filters) {
		return sqlSessionTemplate.selectList("findZdlxList", filters, pb);
	}

	public List<Zdlx> selectPlist() {
		return sqlSessionTemplate.selectList("findPZdlxList");
	}

	public void save(Zdlx dict) {
		sqlSessionTemplate.insert("saveZdlx", dict);

	}

	public void update(Zdlx dict) {
		sqlSessionTemplate.update("updateZdlx", dict);

	}

	public Zdlx get(Integer id) {
		return sqlSessionTemplate.selectOne("findZdlxById", id);
	}

	public void del(Integer id) {
		sqlSessionTemplate.delete("delZdlx", id);

	}

	public List<Zdlx> selectFlist() {
		return sqlSessionTemplate.selectList("findFZdlxList");
	}

	public List<Zdlx> getCheck(Zdlx dict) {
		return sqlSessionTemplate.selectList("getCheckZdlx", dict);
	}

}
