package com.lte.admin.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.entity.Log;

/**
 * 日志DAO
 * 
 * @author ty
 * @date 2015年1月13日
 */
@Repository
public class LogDao extends BaseDao {

	/**
	 * 批量删除日志
	 * 
	 * @param ids
	 *            日志id列表
	 */
	public void deleteBatch(List<Integer> idList) {
		// String hql="delete from Log log where log.id in (:idList)";
		// Query query=getSession().createQuery(hql);
		// query.setParameterList("idList", idList);
		// query.executeUpdate();
	}

	public void save(Log log) {
		sqlSessionTemplate.insert("saveLog", log);

	}

	public List<Log> search(PageBounds pb, Map<String, Object> filters) {
		return sqlSessionTemplate.selectList("searchLogs", filters, pb);
	}
}
