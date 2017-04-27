package com.lte.admin.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.entity.DictType;
import com.lte.admin.system.dao.DictTypeDao;

@Service
@Transactional
public class DictTypeService extends BaseService<DictType, Integer>{

	
	@Autowired
	private DictTypeDao dictTypeDao;
	
	public PageList<DictType> getDzlxList(Page<DictType> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);

		return (PageList<DictType>) dictTypeDao.getDzlxList(pb, filters);
	}

	public List<DictType> selectPlist() {
		return dictTypeDao.selectPlist();
	}

	public void save(DictType dict) {
		dictTypeDao.save(dict);
		
	}

	public void update(DictType dict) {
		dictTypeDao.update(dict);
		
	}

	public DictType get(Long id) {
		return dictTypeDao.get(id);
	}
	
	public DictType getDictByCode(String dictCode) {
		return dictTypeDao.getDictByCode(dictCode);
	}

	public void delete(Integer id) {
		dictTypeDao.del(id);
		
	}

	public List<DictType> selectFlist() {
		return dictTypeDao.selectFlist();
	}

	public List<DictType> getCheck(DictType dict) {
		return dictTypeDao.getCheck(dict);
	}
}
