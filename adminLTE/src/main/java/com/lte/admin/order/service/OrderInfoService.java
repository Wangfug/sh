package com.lte.admin.order.service;

import com.lte.admin.order.entity.OrderInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.order.dao.OrderInfoDao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Andy
 */
@Service
@Transactional
public class OrderInfoService extends BaseService<OrderInfo, Integer>{
	@Autowired
	private OrderInfoDao orderInfoDao;

		
}

