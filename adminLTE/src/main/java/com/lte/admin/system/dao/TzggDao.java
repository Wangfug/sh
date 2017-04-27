package com.lte.admin.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.entity.Tzgg;

/**
 * 通知公告DAO
 * 
 * @author ty
 * @date 2015年1月13日
 */
@Repository
public class TzggDao extends BaseDao {

	public List<Tzgg> getList(PageBounds pb, Map<String, Object> filters) {
		return sqlSessionTemplate.selectList("findTzggList", filters, pb);
	}

	public List<Tzgg> selectPlist() {
		return sqlSessionTemplate.selectList("findPTzggList");
	}

	public void save(Tzgg dict) {
		sqlSessionTemplate.insert("saveTzgg", dict);

	}

	public void update(Tzgg dict) {
		sqlSessionTemplate.update("updateTzgg", dict);

	}

	public Tzgg get(Long id) {
		return sqlSessionTemplate.selectOne("findTzggById", id);
	}

	public void del(Integer id) {
		sqlSessionTemplate.delete("delTzgg", id);

	}

	public List<Tzgg> selectFlist() {
		return sqlSessionTemplate.selectList("findFTzggList");
	}

	public List<Tzgg> getCheck(Tzgg dict) {
		return sqlSessionTemplate.selectList("getCheckTzgg", dict);
	}

	public List<Tzgg> getAllTzggList() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("tzbt", null);
		param.put("lx", null);
		param.put("scbz", "0");// TODO define as a const

		return sqlSessionTemplate.selectList("findTzggList", param);
	}

}
