package com.lte.admin.custom.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.custom.entity.CustomerCredential;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.custom.dao.CustomerCredentialDao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Andy
 */
@Service
@Transactional
public class CustomerCredentialService extends BaseService<CustomerCredential, Integer>{
	@Autowired
	private CustomerCredentialDao customerCredentialDao;
	/**
	 * 新增车辆门店信息
	 * @param customerCredential
	 * @return
	 */
	public long saveCustomerCredential(CustomerCredential customerCredential) {
		return customerCredentialDao.saveCustomerCredential(customerCredential);
	}

	/**
	 * 更新车辆门店信息
	 * @param customerCredential
	 * @return
	 */
	public String updateCustomerCredential(CustomerCredential customerCredential) {
		return customerCredentialDao.updateCustomerCredential(customerCredential);
	}

	/**
	 * 根据条件查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public PageList<Map> getList(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<Map>) customerCredentialDao.getCustomerCredentialList(pb,filters);
	}

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public CustomerCredential getOneCustomerCredential(long id){
		return customerCredentialDao.getOneCustomerCredential(id);
	}

	public List<CustomerCredential> getList() {
		return  customerCredentialDao.getList();
	}

	public List<Map> getList1() {
		return  customerCredentialDao.getList1();
	}

	public List<CustomerCredential> getCredentialsByType(String type) {
		return customerCredentialDao.getCredentialsByType(type);
	}

	public CustomerCredential getByCode(String credentialcode) {
		return customerCredentialDao.getByCode(credentialcode);
	}
}

