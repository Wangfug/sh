package com.lte.admin.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.entity.Zdxx;

/**
 * 字典DAO
 * 
 * @author ty
 * @date 2015年1月13日
 */
@Repository
public class ZdxxDao extends BaseDao {

	public List<Zdxx> getZdxxList(PageBounds pb, Map<String, Object> filters) {
		return sqlSessionTemplate.selectList("findZdxxList", filters, pb);
	}

	public void save(Zdxx dict) {
		sqlSessionTemplate.insert("saveZdxx", dict);

	}

	public Zdxx get(Integer id) {
		return sqlSessionTemplate.selectOne("getZdxxByid", id);
	}

	public void update(Zdxx dict) {
		sqlSessionTemplate.insert("updateZdxx", dict);

	}

	public List<Zdxx> getCheck(Zdxx dict) {
		return sqlSessionTemplate.selectList("getCheckZdxx", dict);
	}
}
