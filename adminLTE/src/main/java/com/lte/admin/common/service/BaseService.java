package com.lte.admin.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.utils.StringUtils;

/**
 * 基础service 所有service继承该类 提供基础的实体操作方法 使用HibernateDao
 * <T,PK>进行业务对象的CRUD操作,子类需重载getEntityDao()函数提供该DAO.
 * 
 * @author ty
 * @date 2014年8月20日 上午11:21:14
 * @param <T>
 * @param <PK>
 */
public abstract class BaseService<T, PK extends Serializable> implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Autowired
	protected SqlSessionFactoryBean sqlSessionFactory;

	@Autowired
	protected DictionaryService dictionary;
	/**
	 * log输出对象
	 */
	protected Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 查询分页数据
	 * 
	 * @param mapperClass
	 * @param sqlId
	 * @param sqlParameter
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	protected List<?> getPageList(Class<?> mapperClass, String sqlId, Object sqlParameter, int pageIndex, int pageSize)
			throws Exception {
		SqlSession session = null;
		try {
			SqlSessionFactory sessionFactory = sqlSessionFactory.getObject();
			session = SqlSessionUtils.getSqlSession(sessionFactory);
			if (pageIndex <= 0) {
				pageIndex = 1;
			}
			if (pageSize <= 0) {
				pageSize = 10;
			}
			PageBounds pageBounds = new PageBounds(pageIndex, pageSize);
			return session.selectList(mapperClass.getName() + "." + sqlId, sqlParameter, pageBounds);
		} finally {
			session.close();
		}

	}

	/**
	 * 创建分页信息
	 */
	public PageBounds createPageBounds(Page<T> form) {

		// 分页属性
		PageBounds pb = null;

		if (StringUtils.isEmpty(form.getOrderBy())) {
			pb = new PageBounds(form.getPageNo(), form.getPageSize());
		} else {
			pb = new PageBounds(form.getPageNo(), form.getPageSize(), Order.create(form.getOrderBy(), form.getOrder()));
		}

		return pb;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * 取得message内容
	 * 
	 * @param code
	 * @param args
	 * @return
	 */
	public String getMessage(String code, Object... args) {
		return applicationContext.getMessage(code, args, new Locale("zh", "CN"));
	}
}
