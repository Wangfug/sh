package com.lte.admin.order.service;

import com.lte.admin.order.entity.OrderFee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.order.dao.OrderFeeDao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Andy
 */
@Service
@Transactional
public class OrderFeeService extends BaseService<OrderFee, Integer>{
	@Autowired
	private OrderFeeDao orderFeeDao;

		
}

