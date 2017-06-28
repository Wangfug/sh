package com.lte.admin.custom.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.custom.entity.CustomerDiscount;
import com.lte.admin.custom.entity.CustomerDiscountHold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.custom.dao.CustomerDiscountHoldDao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Andy
 */
@Service
@Transactional
public class CustomerDiscountHoldService extends BaseService<CustomerDiscountHold, Integer>{
	@Autowired
	private CustomerDiscountHoldDao customerDiscountHoldDao;

	/**
	 * 新增打折卡信息
	 * @param customerDiscountHold
	 * @return
	 */
	public String saveCustomerDiscountHold(CustomerDiscountHold customerDiscountHold) {
		return customerDiscountHoldDao.saveCustomerDiscountHold(customerDiscountHold);
	}

	/**
	 * 更新打折卡信息
	 * @param customerDiscountHold
	 * @return
	 */
	public String updateCustomerDiscountHold(CustomerDiscountHold customerDiscountHold) {
		return customerDiscountHoldDao.updateCustomerDiscountHold(customerDiscountHold);
	}

	/**
	 * 根据条件查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public PageList<CustomerDiscountHold> getList(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<CustomerDiscountHold>) customerDiscountHoldDao.getCustomerDiscountHoldList(pb,filters);
	}

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public CustomerDiscountHold getOneCustomerDiscountHold(long id){
		return customerDiscountHoldDao.getOneCustomerDiscountHold(id);
	}

	public List<CustomerDiscountHold> getList() {
		return  customerDiscountHoldDao.getList();
	}

	public Map getDetail(long holdId) {
		return  customerDiscountHoldDao.getDetail(holdId);
	}

	public CustomerDiscountHold getByOrderNo(String orderNo) {
		return  customerDiscountHoldDao.getByOrderNo(orderNo);
	}

    public Integer getByCusAndDis(Map<String,Object> filter) {
		return  customerDiscountHoldDao.getByCusAndDis(filter);
    }
}

