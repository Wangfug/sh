package com.lte.admin.order.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.order.entity.OrderCarCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.order.dao.OrderCarCheckDao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Andy
 */
@Service
@Transactional
public class OrderCarCheckService extends BaseService<OrderCarCheck, Integer>{
	@Autowired
	private OrderCarCheckDao orderCarCheckDao;
	public PageList<OrderCarCheck> getList(Page<OrderCarCheck> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<OrderCarCheck>) orderCarCheckDao.getCustomerList(pb, filters);
	}

	public void save(OrderCarCheck orderCarCheck) {
		orderCarCheckDao.save(orderCarCheck);
	}

	public void update(OrderCarCheck orderCarCheck) {
		orderCarCheckDao.update(orderCarCheck);
	}

	public void deleteById(Long id) {
		orderCarCheckDao.deleteById(id);
	}
}

