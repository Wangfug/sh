package com.lte.admin.custom.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.custom.dao.CustomerDao;
import com.lte.admin.custom.entity.Customer;
import com.lte.admin.custom.entity.CustomerCreditCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.custom.dao.CustomerCreditCardDao;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Andy
 */
@Service
@Transactional
public class CustomerCreditCardService extends BaseService<CustomerCreditCard, Integer>{
	@Autowired
	private CustomerCreditCardDao customerCreditCardDao;
	@Autowired
	private CustomerDao customerDao;
	/**
	 * 新增信用卡信息
	 * @param customerCreditCard
	 * @return
	 */
	public String saveCustomerCreditCard(CustomerCreditCard customerCreditCard) {
		return customerCreditCardDao.saveCustomerCreditCard(customerCreditCard);
	}
	/**
	 * 新增信用卡信息
	 * @param customerCreditCard
	 * @return
	 */
	public boolean saveCustomerCreditCard(Customer customer, CustomerCreditCard customerCreditCard) {
		boolean res = false;
		try{
			customerDao.updateCustomer(customer);
			customerCreditCardDao.saveCustomerCreditCard(customerCreditCard);
			res = true;
		}catch(Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			res = false;
		}
		return res;
	}
	/**
	 * 更新信用卡信息
	 * @param customerCreditCard
	 * @return
	 */
	public String updateCustomerCreditCard(CustomerCreditCard customerCreditCard) {
		return customerCreditCardDao.updateCustomerCreditCard(customerCreditCard);
	}

	/**
	 * 根据条件查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public PageList<Map> getList(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<Map>) customerCreditCardDao.getCustomerCreditCardList(pb,filters);
	}

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public CustomerCreditCard getOneCustomerCreditCard(long id){
		return customerCreditCardDao.getOneCustomerCreditCard(id);
	}

	public List<CustomerCreditCard> getList() {
		return  customerCreditCardDao.getList();
	}
		
}

