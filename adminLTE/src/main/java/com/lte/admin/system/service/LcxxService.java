package com.lte.admin.system.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.entity.Blsx;
import com.lte.admin.system.dao.LcxxDao;

@Service
@Transactional(readOnly = true)
public class LcxxService extends BaseService<Blsx, Integer> {
	@Autowired
	private LcxxDao lcxxDao;

	public PageList<Blsx> getLcxxList(Page<Blsx> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);

		return (PageList<Blsx>) lcxxDao.getLcxxList(pb, filters);
	}
}
