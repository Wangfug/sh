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
import com.lte.admin.entity.Zdlx;
import com.lte.admin.system.dao.ZdlxDao;

/**
 * 字典service
 * 
 * @author ty
 * @date 2015年1月13日
 */
@Service
@Transactional(readOnly = true)
public class ZdlxService extends BaseService<Zdlx, Integer> {

	@Autowired
	private ZdlxDao zdlxDao;

	public PageList<Zdlx> getDzlxList(Page<Zdlx> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);

		return (PageList<Zdlx>) zdlxDao.getDzlxList(pb, filters);
	}

	public List<Zdlx> selectPlist() {
		return zdlxDao.selectPlist();
	}

	public void save(Zdlx dict) {
		zdlxDao.save(dict);

	}

	public void update(Zdlx dict) {
		zdlxDao.update(dict);

	}

	public Zdlx get(Integer id) {
		return zdlxDao.get(id);
	}

	public void delete(Integer id) {
		zdlxDao.del(id);

	}

	public List<Zdlx> selectFlist() {
		return zdlxDao.selectFlist();
	}

	public List<Zdlx> getCheck(Zdlx dict) {
		return zdlxDao.getCheck(dict);
	}

}
