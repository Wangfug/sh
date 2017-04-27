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
import com.lte.admin.entity.Tzgg;
import com.lte.admin.system.dao.TzggDao;

/**
 * 字典service
 * 
 * @author ty
 * @date 2015年1月13日
 */
@Service
@Transactional(readOnly = true)
public class TzggService extends BaseService<Tzgg, Integer> {

	@Autowired
	private TzggDao tzggDao;

	public PageList<Tzgg> getList(Page<Tzgg> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);

		return (PageList<Tzgg>) tzggDao.getList(pb, filters);
	}

	public List<Tzgg> selectPlist() {
		return tzggDao.selectPlist();
	}

	public void save(Tzgg dict) {
		tzggDao.save(dict);

	}

	public void update(Tzgg dict) {
		tzggDao.update(dict);

	}

	public Tzgg get(Long id) {
		return tzggDao.get(id);
	}

	public void delete(Integer id) {
		tzggDao.del(id);

	}

	public List<Tzgg> selectFlist() {
		return tzggDao.selectFlist();
	}

	public List<Tzgg> getCheck(Tzgg dict) {
		return tzggDao.getCheck(dict);
	}

	public List<Tzgg> getAllTzggList() {
		return tzggDao.getAllTzggList();
	}
}
