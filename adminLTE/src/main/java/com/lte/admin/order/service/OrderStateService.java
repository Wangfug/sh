package com.lte.admin.order.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.order.entity.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.order.dao.OrderStateDao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Andy
 */
@Service
@Transactional
public class OrderStateService extends BaseService<OrderState, Integer>{
	@Autowired
	private OrderStateDao orderStateDao;

	public PageList<OrderState> getList(Page<OrderState> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<OrderState>) orderStateDao.getOrderList(pb, filters);
	}

	public void save(OrderState orderState) {
		orderStateDao.save(orderState);
	}

	public void update(OrderState orderState) {
		orderStateDao.update(orderState);
	}

	public void deleteById(Long id) {
		orderStateDao.deleteById(id);
	}

    public OrderState get(long id) {
		return orderStateDao.get(id);
    }
}

