package com.lte.admin.common.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao {
	/**
	 * Mybatis执行实体
	 */
	@Autowired
	protected SqlSessionTemplate sqlSessionTemplate;
}
