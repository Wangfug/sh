package com.lte.admin.other.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.other.dao.TbaseCityDao;
import com.lte.admin.other.entity.TbaseCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Andy
 */
@Service
@Transactional
public class TbaseCityService extends BaseService<TbaseCity, Integer>{
	@Autowired
	private TbaseCityDao tbaseCityDao;

	public PageList<TbaseCity> getList(Page<TbaseCity> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<TbaseCity>) tbaseCityDao.getList(pb, filters);
	}
	public List<TbaseCity> getList() {
		return (List<TbaseCity>) tbaseCityDao.getList();
	}

	public void save(TbaseCity tbaseCity) {
		tbaseCityDao.save(tbaseCity);
	}

	public void update(TbaseCity tbaseCity) {
		tbaseCityDao.update(tbaseCity);
	}

	public void deleteById(Long id) {
		tbaseCityDao.deleteById(id);
	}

	public TbaseCity get(long id) {
		return tbaseCityDao.get(id);
	}

	public List<Map> getList1() {
		return tbaseCityDao.getList1();
	}

    public List<Map> getNameList(Long pid) {
		return tbaseCityDao.getNameList(pid);
    }
}

