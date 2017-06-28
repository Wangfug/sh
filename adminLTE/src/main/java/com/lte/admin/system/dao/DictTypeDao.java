package com.lte.admin.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.entity.DictType;

@Repository
public class DictTypeDao extends BaseDao {
	public List<DictType> getDzlxList(PageBounds pb, Map<String, Object> filters) {
		return sqlSessionTemplate.selectList("com.lte.admin.mapper.DictTypeMapper.findZdlxList", filters,pb);
	}

	public List<DictType> selectPlist() {
		return sqlSessionTemplate.selectList("com.lte.admin.mapper.DictTypeMapper.findPZdlxList");
	}

	public void save(DictType dict) {
		sqlSessionTemplate.insert("com.lte.admin.mapper.DictTypeMapper.save",dict);
		
	}

	public void update(DictType dict) {
		sqlSessionTemplate.update("com.lte.admin.mapper.DictTypeMapper.updateZdlx",dict);
		
	}

	public DictType get(Long id) {
		return sqlSessionTemplate.selectOne("com.lte.admin.mapper.DictTypeMapper.findZdlxById",id);
	}
	
	public DictType getDictByCode(String dictCode) {
		return sqlSessionTemplate.selectOne(
				"com.lte.admin.mapper.DictTypeMapper.getDictByCode", dictCode);
	}
	public void del(Integer id) {
		sqlSessionTemplate.delete("com.lte.admin.mapper.DictTypeMapper.delZdlx",id);
		
	}

	public List<DictType> selectFlist() {
		return sqlSessionTemplate.selectList("com.lte.admin.mapper.DictTypeMapper.findFZdlxList");
	}

	public List<DictType> getCheck(DictType dict) {
		return sqlSessionTemplate.selectList("com.lte.admin.mapper.DictTypeMapper.getCheckZdlx",dict);
	}
	public List<DictType> getChildrenByParent(String code) {
		return sqlSessionTemplate.selectList("com.lte.admin.mapper.DictTypeMapper.getChildrenByParent",code);
	}
}
