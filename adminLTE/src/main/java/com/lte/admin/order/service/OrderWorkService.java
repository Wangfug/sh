package com.lte.admin.order.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.dao.CarDao;
import com.lte.admin.car.dao.CarOutOrInDao;
import com.lte.admin.car.entity.Car;
import com.lte.admin.car.entity.CarOutOrIn;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.order.dao.OrderInfoDao;
import com.lte.admin.order.entity.OrderInfo;
import com.lte.admin.order.entity.OrderWork;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.order.dao.OrderWorkDao;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Andy
 */
@Service
@Transactional
public class OrderWorkService extends BaseService<OrderWork, Integer>{
	@Autowired
	private OrderWorkDao orderWorkDao;
	@Autowired
	private OrderInfoDao orderInfoDao;
	@Autowired
	private CarDao carDao;
	@Autowired
	private CarOutOrInDao carOutOrInDao;
	public PageList<OrderWork> getList(Page<OrderWork> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<OrderWork>) orderWorkDao.getCustomerList(pb, filters);
	}
	public PageList<Map> getList1(Page<OrderWork> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<Map>) orderWorkDao.getCustomerList1(pb, filters);
	}

	public void save(OrderWork orderWork) {
		orderWorkDao.save(orderWork);
	}
	public void save(OrderWork sendWork,OrderWork returnWork) {
		orderWorkDao.save(sendWork);
		orderWorkDao.save(returnWork);
	}

	public void update(OrderWork orderWork) {
		orderWorkDao.update(orderWork);
	}
	public boolean update(OrderWork orderWork,OrderInfo orderInfo,Car car,CarOutOrIn carOutOrIn) {
		boolean result = false;
		try{
			orderWorkDao.update(orderWork);
			orderInfoDao.update(orderInfo);
			carDao.updateCar(car);
			carOutOrInDao.saveCarOutOrIn(carOutOrIn);
			result = true;
		}catch(Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;
	}
	public void deleteById(Long id) {
		orderWorkDao.deleteById(id);
	}

	public OrderWork get(long id) {
		return orderWorkDao.get(id);
	}

    public PageList<Map> getListByToken(Page<OrderWork> page, Map<String, Object> filters) {
			PageBounds pb = createPageBounds(page);
			return (PageList<Map>) orderWorkDao.getListByToken(pb, filters);
    }

    public List<Map<String,Object>> getByOrder(String orderNo) {
		return orderWorkDao.getByOrder(orderNo);
    }
	public List<OrderWork> getByOrder1(String orderNo) {
		Map<String,Object> filter = new HashMap<String,Object>();
		filter.put("orderNo",orderNo);
		return orderWorkDao.getByOrder(filter);
	}

	/**
	 * 根据员工和订单查询工单
	 * @param filter
	 * @return
	 */
	public List<Map<String,Object>> getByOrderShop(Map<String,Object> filter) {
		return orderWorkDao.getByOrderShop(filter);
	}

	public List<OrderWork> getByOrderFor(String orderNo) {
		return orderWorkDao.getByOrderFor(orderNo);
	}

    public Long getReturnNumber(String token) {
		return orderWorkDao.getReturnNumber(token);
    }

	public Long getGetNumber(String token) {
		return orderWorkDao.getGetNumber(token);
	}

	public OrderWork getByOrder(Map<String,Object> filter) {
		List<OrderWork> works = orderWorkDao.getByOrder(filter);
		if(CollectionUtils.isNotEmpty(works)){
			return works.get(0);
		}
		return null;
	}

    public Long getNotAssign(String shopCode) {
		return orderWorkDao.getNotAssign(shopCode);
    }

}

