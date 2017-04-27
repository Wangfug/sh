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
import com.lte.admin.entity.Log;
import com.lte.admin.system.dao.LogDao;

/**
 * 日志service
 * 
 * @author ty
 * @date 2015年1月14日
 */
@Service
@Transactional(readOnly = true)
public class LogService extends BaseService<Log, Integer> {

	@Autowired
	private LogDao logDao;

	/**
	 * 批量删除日志
	 * 
	 * @param idList
	 */
	@Transactional(readOnly = false)
	public void deleteLog(List<Integer> idList) {
		logDao.deleteBatch(idList);
	}

	public void save(Log log) {
		logDao.save(log);

	}

	public PageList<Log> search(Page<Log> logPage, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(logPage);
		return (PageList<Log>) logDao.search(pb, filters);
	}

}
