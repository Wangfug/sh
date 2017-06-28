package com.lte.admin.custom.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
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
	/**
	 * 新增打折卡信息
	 * @param customerDiscount
	 * @return
	 */
	public String saveCustomerDiscount(CustomerDiscount customerDiscount) {
		return customerDiscountDao.saveCustomerDiscount(customerDiscount);
	}

	/**
	 * 更新打折卡信息
	 * @param customerDiscount
	 * @return
	 */
	public String updateCustomerDiscount(CustomerDiscount customerDiscount) {
		return customerDiscountDao.updateCustomerDiscount(customerDiscount);
	}

	/**
	 * 根据条件查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public PageList<CustomerDiscount> getList(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<CustomerDiscount>) customerDiscountDao.getCustomerDiscountList(pb,filters);
	}
	/**
	 * 根据用户查询有效优惠券
	 */
	public PageList<Map> getAllDiscount(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<Map>) customerDiscountDao.getAllDiscount(pb,filters);
	}

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public CustomerDiscount getOneCustomerDiscount(long id){
		return customerDiscountDao.getOneCustomerDiscount(id);
	}

	public List<CustomerDiscount> getList() {
		return  customerDiscountDao.getList();
	}

	public List<Map> getList1() {
		return  customerDiscountDao.getList1();
	}

    public List<CustomerDiscount> getEffectiveList() {
		return customerDiscountDao.getEffectiveList();
    }

}

