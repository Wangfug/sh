package com.lte.admin.order.service;

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

		
}

