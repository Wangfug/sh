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
import com.lte.admin.entity.Zdxx;
import com.lte.admin.system.dao.ZdxxDao;

/**
 * 字典service
 * 
 * @author ty
 * @date 2015年1月13日
 */
@Service
@Transactional(readOnly = true)
public class ZdxxService extends BaseService<Zdxx, Integer> {

	@Autowired
	private ZdxxDao zdxxDao;

	public PageList<Zdxx> search(Page<Zdxx> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<Zdxx>) zdxxDao.getZdxxList(pb, filters);
	}

	public void save(Zdxx dict) {
		zdxxDao.save(dict);

	}

	public Zdxx get(Integer id) {
		return zdxxDao.get(id);
	}

	public void update(Zdxx dict) {
		zdxxDao.update(dict);

	}

	public List<Zdxx> getCheck(Zdxx dict) {
		return zdxxDao.getCheck(dict);
	}
}
