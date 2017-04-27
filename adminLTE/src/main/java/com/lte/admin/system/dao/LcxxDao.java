package com.lte.admin.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.entity.Blsx;

@Repository
public class LcxxDao extends BaseDao {

	public List<Blsx> getLcxxList(PageBounds pb, Map<String, Object> filters) {
		return sqlSessionTemplate.selectList("getList", filters, pb);
	}

}
