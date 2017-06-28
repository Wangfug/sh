package com.lte.admin.order.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.order.entity.OrderBill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.order.dao.OrderBillDao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Andy
 */
@Service
@Transactional
public class OrderBillService extends BaseService<OrderBill, Integer>{
	@Autowired
	private OrderBillDao orderBillDao;
	public PageList<OrderBill> getList(Page<OrderBill> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<OrderBill>) orderBillDao.getCustomerList(pb, filters);
	}

	public void save(OrderBill orderBill) {
		orderBillDao.save(orderBill);
	}

	public void update(OrderBill orderBill) {
		orderBillDao.update(orderBill);
	}

	public void deleteById(Long id) {
		orderBillDao.deleteById(id);
	}

    public OrderBill get(long id) {
		return orderBillDao.get(id);
    }

//    public PageList<OrderBill> getList1(Page<OrderBill> page, Map<String, Object> filter) {
//		PageBounds pb = createPageBounds(page);
//		return (PageList<OrderBill>) orderBillDao.getCustomerList1(pb, filter);
//    }
}

