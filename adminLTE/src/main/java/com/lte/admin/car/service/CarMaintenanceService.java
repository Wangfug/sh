package com.lte.admin.car.service;

import com.lte.admin.car.entity.CarMaintenance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.car.dao.CarMaintenanceDao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Andy
 */
@Service
@Transactional
public class CarMaintenanceService extends BaseService<CarMaintenance, Integer>{
	@Autowired
	private CarMaintenanceDao carMaintenanceDao;

		
}

