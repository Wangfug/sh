package com.lte.admin.order.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.order.dao.OrderFeeDao;
import com.lte.admin.order.entity.OrderFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author Andy
 */
@Service
@Transactional
public class OrderFeeService extends BaseService<OrderFee, Integer>{
	@Autowired
	private OrderFeeDao orderFeeDao;
	public PageList<OrderFee> getList(Page<OrderFee> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<OrderFee>) orderFeeDao.getCustomerList(pb, filters);
	}

	public long save(OrderFee orderFee) {
		return orderFeeDao.save(orderFee);
	}

	public void update(OrderFee orderFee) {
		orderFeeDao.update(orderFee);
	}

	public void deleteById(Long id) {
		orderFeeDao.deleteById(id);
	}

    public OrderFee get(long id) {
		return orderFeeDao.get(id);
    }
}

