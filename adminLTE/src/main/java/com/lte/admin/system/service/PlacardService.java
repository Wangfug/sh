package com.lte.admin.system.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.common.utils.DateUtils;
import com.lte.admin.entity.Placard;
import com.lte.admin.system.dao.PlacardDao;

@Service
@Transactional
public class PlacardService extends BaseService<Placard, Integer> {
	
	@Autowired
	private PlacardDao placardDao;
	
	public PageList<Placard> getList(Page<Placard> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);

		return (PageList<Placard>) placardDao.getList(pb, filters);
	}

	public void save(Placard dict) {
		dict.setCreateTime(DateUtils.getSysDate());
		dict.setUpdateTime(DateUtils.getSysDate());
		placardDao.save(dict);
	}

	public void update(Placard dict) {
		dict.setUpdateTime(DateUtils.getSysDate());
		placardDao.update(dict);
	}

	public Placard get(Long id) {
		return placardDao.get(id);
	}

	public List<Placard> getAllTzggList() {
		return placardDao.getAllTzggList();
	}
}
