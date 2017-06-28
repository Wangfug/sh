package com.lte.admin.custom.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.custom.entity.Customer;
import com.lte.admin.custom.entity.CustomerBalanceChange;

import com.lte.admin.custom.entity.CustomerDiscount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.custom.dao.CustomerBalanceChangeDao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Andy
 */
@Service
@Transactional
public class CustomerBalanceChangeService extends BaseService<CustomerBalanceChange, Integer>{
	@Autowired
	private CustomerBalanceChangeDao customerBalanceChangeDao;
	/**
	 * 新增余额变更记录信息
	 * @param customerBalanceChange
	 * @return
	 */
	public long saveCustomerBalanceChange(CustomerBalanceChange customerBalanceChange) {
		return customerBalanceChangeDao.saveCustomerBalanceChange(customerBalanceChange);
	}

	/**
	 * 更新余额变更记录信息
	 * @param customerBalanceChange
	 * @return
	 */
	public String updateCustomerBalanceChange(CustomerBalanceChange customerBalanceChange) {
		return customerBalanceChangeDao.updateCustomerBalanceChange(customerBalanceChange);
	}

	/**
	 * 根据条件查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public PageList<CustomerBalanceChange> getList(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<CustomerBalanceChange>) customerBalanceChangeDao.getCustomerBalanceChangeList(pb,filters);
	}

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public CustomerBalanceChange getOneCustomerBalanceChange(long id){
		return customerBalanceChangeDao.getOneCustomerBalanceChange(id);
	}

	public List<CustomerBalanceChange> getList() {
		return  customerBalanceChangeDao.getList();
	}

	public PageList<Map> getHistory(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<Map>) customerBalanceChangeDao.getHistory(pb,filters);
	}
}

