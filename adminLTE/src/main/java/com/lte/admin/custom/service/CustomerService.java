package com.lte.admin.custom.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.custom.dao.*;
import com.lte.admin.custom.entity.*;
import com.lte.admin.order.dao.OrderAccountDao;
import com.lte.admin.order.dao.OrderInfoDao;
import com.lte.admin.order.dao.OrderStateDao;
import com.lte.admin.order.entity.OrderAccount;
import com.lte.admin.order.entity.OrderInfo;
import com.lte.admin.order.entity.OrderState;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Map;

/**
 * @author Andy
 */
@Service
@Transactional
public class CustomerService extends BaseService<Customer, Integer>{
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CustomerDrivingLicenceDao customerDrivingLicenceDao;
	@Autowired
	private CustomerCredentialDao customerCredentialDao;
	@Autowired
	private CustomerBalanceChangeDao customerBalanceChangeDao;
	@Autowired
	private OrderInfoDao orderInfoDao;
	@Autowired
	private OrderAccountDao orderAccountDao;
	@Autowired
	private OrderStateDao orderStateDao;
	@Autowired
	private CustomerDiscountHoldDao customerDiscountHoldDao;
	/**
	 * 新增用户信息
	 * @param customer
	 * @return
	 */
	public String saveCustomer(Customer customer) {
		return customerDao.saveCustomer(customer);
	}

	/**
	 * 更新用户信息
	 * @param customer
	 * @return
	 */
	public String updateCustomer(Customer customer) {
		return customerDao.updateCustomer(customer);
	}

	/**
	 * 根据条件查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public PageList<Customer> getList(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<Customer>) customerDao.getCustomerList(pb,filters);
	}

	/**
	 * 根据条件查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public PageList<Map> getList1(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<Map>) customerDao.getCustomerList1(pb,filters);
	}

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public Customer getOneCustomer(long id){
		return customerDao.getOneCustomer(id);
	}
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public Map getOneCustomer1(long id){
		return customerDao.getOneCustomer1(id);
	}



	public List<Customer> getList() {
		return  customerDao.getList();
	}

	public List<Map> getList1() {
		return  customerDao.getList1();
	}

	public Customer getOneCustomerByMobile(Map<String, Object> filters){
		return customerDao.getOneCustomerByMobile(filters);
	}

	public Map<String, Object> getMyDetail(Map<String, Object> filters){
		return customerDao.getMyDetail(filters);
	}

	public Map<String, Object> getCustomerInfo(Map<String, Object> filters){
		return customerDao.getCustomerInfo(filters);
	}
    public boolean updateOrderState(OrderInfo orderInfo, OrderAccount orderAccount, OrderState orderState, CustomerDiscountHold hold,Customer customer) {
		boolean result = false;
		try{
			orderInfoDao.update(orderInfo);
			orderAccountDao.save(orderAccount);
			orderStateDao.save(orderState);
			if(null != hold){
				customerDiscountHoldDao.updateCustomerDiscountHold(hold);
			}
			customerDao.updateCustomer(customer);
			result = true;
		}catch(Exception e){
			e.printStackTrace();
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;


    }

	public void updateCustomer(Customer customer, CustomerBalanceChange balanceChange) {
		customerBalanceChangeDao.saveCustomerBalanceChange(balanceChange);
		customerDao.updateCustomer(customer);
	}

	public void auth(Customer customer, CustomerDrivingLicence customerDrivingLicence, CustomerCredential credential) {
		long driveId = customerDrivingLicenceDao.saveCustomerDrivingLicence(customerDrivingLicence);
		long credentialId = customerCredentialDao.saveCustomerCredential(credential);
		customer.setDrivingLicence(driveId);
		customer.setIdentityCard(credentialId);
		customerDao.updateCustomer(customer);
	}

	public Map<String, Object> getOneCustomerAuth(long id) {
			return  customerDao.getOneCustomerAuth(id);
	}
}

