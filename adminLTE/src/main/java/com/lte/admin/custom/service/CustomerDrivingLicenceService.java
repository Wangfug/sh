package com.lte.admin.custom.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.custom.entity.CustomerDrivingLicence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.custom.dao.CustomerDrivingLicenceDao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Andy
 */
@Service
@Transactional
public class CustomerDrivingLicenceService extends BaseService<CustomerDrivingLicence, Integer>{
	@Autowired
	private CustomerDrivingLicenceDao customerDrivingLicenceDao;
	/**
	 * 新增车辆门店信息
	 * @param customerDrivingLicence
	 * @return
	 */
	public long saveCustomerDrivingLicence(CustomerDrivingLicence customerDrivingLicence) {
		return customerDrivingLicenceDao.saveCustomerDrivingLicence(customerDrivingLicence);
	}

	/**
	 * 更新车辆门店信息
	 * @param customerDrivingLicence
	 * @return
	 */
	public String updateCustomerDrivingLicence(CustomerDrivingLicence customerDrivingLicence) {
		return customerDrivingLicenceDao.updateCustomerDrivingLicence(customerDrivingLicence);
	}

	/**
	 * 根据条件查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public PageList<CustomerDrivingLicence> getList(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<CustomerDrivingLicence>) customerDrivingLicenceDao.getCustomerDrivingLicenceList(pb,filters);
	}

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public CustomerDrivingLicence getOneCustomerDrivingLicence(long id){
		return customerDrivingLicenceDao.getOneCustomerDrivingLicence(id);
	}

	public List<Map> getList() {
		return  customerDrivingLicenceDao.getList();
	}
		
}

