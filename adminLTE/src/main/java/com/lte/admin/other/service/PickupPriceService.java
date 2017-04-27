package com.lte.admin.other.service;

import com.lte.admin.other.entity.PickupPrice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.other.dao.PickupPriceDao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Andy
 */
@Service
@Transactional
public class PickupPriceService extends BaseService<PickupPrice, Integer>{
	@Autowired
	private PickupPriceDao pickupPriceDao;

		
}

