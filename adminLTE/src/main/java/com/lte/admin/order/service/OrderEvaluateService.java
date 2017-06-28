package com.lte.admin.order.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.order.entity.OrderEvaluate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.order.dao.OrderEvaluateDao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Andy
 */
@Service
@Transactional
public class OrderEvaluateService extends BaseService<OrderEvaluate, Integer>{
	@Autowired
	private OrderEvaluateDao orderEvaluateDao;
	public PageList<OrderEvaluate> getList(Page<OrderEvaluate> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<OrderEvaluate>) orderEvaluateDao.getCustomerList(pb, filters);
	}
	public void save(OrderEvaluate orderEvaluate) {
		orderEvaluateDao.save(orderEvaluate);
	}
	public void update(OrderEvaluate orderEvaluate) {
		orderEvaluateDao.update(orderEvaluate);
	}

	public void deleteById(Long id) {
		orderEvaluateDao.deleteById(id);
	}

    public OrderEvaluate get(long id) {
		return orderEvaluateDao.get(id);
    }
}

