package com.lte.admin.custom.service;

import com.lte.admin.custom.entity.CustomerDiscount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.custom.dao.CustomerDiscountDao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Andy
 */
@Service
@Transactional
public class CustomerDiscountService extends BaseService<CustomerDiscount, Integer>{
	@Autowired
	private CustomerDiscountDao customerDiscountDao;

		
}

