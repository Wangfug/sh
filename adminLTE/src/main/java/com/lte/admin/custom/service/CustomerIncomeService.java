package com.lte.admin.custom.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
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
	/**
	 * 新增车辆门店信息
	 * @param customerIncome
	 * @return
	 */
	public String saveCustomerIncome(CustomerIncome customerIncome) {
		return customerIncomeDao.saveCustomerIncome(customerIncome);
	}

	/**
	 * 更新车辆门店信息
	 * @param customerIncome
	 * @return
	 */
	public String updateCustomerIncome(CustomerIncome customerIncome) {
		return customerIncomeDao.updateCustomerIncome(customerIncome);
	}

	/**
	 * 根据条件查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public PageList<CustomerIncome> getList(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<CustomerIncome>) customerIncomeDao.getCustomerIncomeList(pb,filters);
	}

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public CustomerIncome getOneCustomerIncome(long id){
		return customerIncomeDao.getOneCustomerIncome(id);
	}

	public List<CustomerIncome> getList() {
		return  customerIncomeDao.getList();
	}
		
}

