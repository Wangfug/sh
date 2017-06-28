package com.lte.admin.other.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.other.dao.ExceptionThrowDao;
import com.lte.admin.other.entity.ExceptionThrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author Andy
 */
@Service
@Transactional
public class ExceptionThrowService extends BaseService<ExceptionThrow, Integer>{
	@Autowired
	private ExceptionThrowDao exceptionThrowDao;

	public PageList<ExceptionThrow> getList(Page<ExceptionThrow> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<ExceptionThrow>) exceptionThrowDao.getList(pb, filters);
	}

	public void save(ExceptionThrow exceptionThrow) {
		exceptionThrowDao.save(exceptionThrow);
	}

	public void update(ExceptionThrow exceptionThrow) {
		exceptionThrowDao.update(exceptionThrow);
	}

	public void deleteById(Long id) {
		exceptionThrowDao.deleteById(id);
	}

	public ExceptionThrow get(long id) {
		return exceptionThrowDao.get(id);
	}
}

