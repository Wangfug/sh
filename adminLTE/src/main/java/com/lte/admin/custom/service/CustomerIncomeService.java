package com.lte.admin.custom.service;

import com.lte.admin.custom.entity.CustomerIncome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.custom.dao.CustomerIncomeDao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Andy
 */
@Service
@Transactional
public class CustomerIncomeService extends BaseService<CustomerIncome, Integer>{
	@Autowired
	private CustomerIncomeDao customerIncomeDao;

		
}

