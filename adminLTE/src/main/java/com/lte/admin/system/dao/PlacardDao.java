package com.lte.admin.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.entity.Placard;

@Repository
public class PlacardDao extends BaseDao {
	public List<Placard> getList(PageBounds pb, Map<String, Object> filters) {
		return sqlSessionTemplate.selectList("findTzggList", filters,pb);
	}

	public void save(Placard dict) {
		sqlSessionTemplate.insert("com.lte.admin.mapper.PlacardMapper.insert",dict);
		
	}

	public void update(Placard dict) {
		sqlSessionTemplate.update("com.lte.admin.mapper.PlacardMapper.updateByPrimaryKeySelective",dict);
		
	}

	public Placard get(Long id) {
		return sqlSessionTemplate.selectOne("com.lte.admin.mapper.PlacardMapper.selectByPrimaryKey",id);
	}
	
	public List<Placard> getAllTzggList() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("tzbt", null);
		param.put("lx", null);
		param.put("scbz", "0");// TODO define as a const

		return sqlSessionTemplate.selectList("findTzggList", param);
	}
}
