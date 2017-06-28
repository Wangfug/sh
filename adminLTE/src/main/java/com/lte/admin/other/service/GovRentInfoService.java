package com.lte.admin.other.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.other.entity.GovRentInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lte.admin.other.dao.GovRentInfoDao;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Andy
 */
@Service
@Transactional
public class GovRentInfoService extends BaseService<GovRentInfo, Integer> {
	@Autowired
	private GovRentInfoDao govRentInfoDao;
	public PageList<GovRentInfo> getList(Page<GovRentInfo> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<GovRentInfo>) govRentInfoDao.getList(pb, filters);
	}

	public void save(GovRentInfo govRentInfo) {
		govRentInfoDao.save(govRentInfo);
	}

	public void update(GovRentInfo govRentInfo) {
		govRentInfoDao.update(govRentInfo);
	}

	public void deleteById(Long id) {
		govRentInfoDao.deleteById(id);
	}

	public GovRentInfo get(long id) {
		return govRentInfoDao.get(id);
	}

	public GovRentInfo getByCustomer(Long id) {
		return govRentInfoDao.getByCustomer(id);
	}
}

