package com.lte.admin.other.service;

import com.lte.admin.other.entity.Tcity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.other.dao.TcityDao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Andy
 */
@Service
@Transactional
public class TcityService extends BaseService<Tcity, Integer>{
	@Autowired
	private TcityDao tcityDao;

		
}

