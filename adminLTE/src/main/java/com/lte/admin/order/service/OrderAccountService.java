package com.lte.admin.order.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.order.entity.OrderAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.order.dao.OrderAccountDao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
/**
 * @author Andy
 */
@Service
@Transactional
public class OrderAccountService extends BaseService<OrderAccount, Integer>{
	@Autowired
	private OrderAccountDao orderAccountDao;
	public PageList<OrderAccount> getList(Page<OrderAccount> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<OrderAccount>) orderAccountDao.getCustomerList(pb, filters);
	}

	public void save(OrderAccount OrderAccount) {
		orderAccountDao.save(OrderAccount);
	}

	public void update(OrderAccount OrderAccount) {
		orderAccountDao.update(OrderAccount);
	}

	public void deleteById(Long id) {
		orderAccountDao.deleteById(id);
	}

    public OrderAccount get(long id) {
		return orderAccountDao.get(id);
    }
}

