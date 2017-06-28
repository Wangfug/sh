package com.lte.admin.custom.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.custom.dao.CustomerBalanceCashDao;
import com.lte.admin.custom.dao.CustomerBalanceChangeDao;
import com.lte.admin.custom.dao.CustomerDao;
import com.lte.admin.custom.entity.Customer;
import com.lte.admin.custom.entity.CustomerBalanceCash;
import com.lte.admin.custom.entity.CustomerBalanceChange;
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
public class CustomerBalanceCashService extends BaseService<CustomerBalanceCash, Integer>{
	@Autowired
	private CustomerBalanceCashDao customerBalanceCashDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CustomerBalanceChangeDao changeDao;


	public long saveCustomerBalanceCash(CustomerBalanceCash cash) {
		return customerBalanceCashDao.saveCustomerBalanceCash(cash);
	}
	public String updateCustomerBalanceCash(CustomerBalanceCash cash) {
		return customerBalanceCashDao.updateCustomerBalanceCash(cash);
	}
	public CustomerBalanceCash getOneCashById(long id) {
		return customerBalanceCashDao.getOneCashById(id);
	}

	/**
	 * 根据条件查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public PageList<CustomerBalanceCash> getList(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<CustomerBalanceCash>) customerBalanceCashDao.getCustomerBalanceCashList(pb,filters);
	}

	public List<Map> getUserList() {
		return customerBalanceCashDao.getUserList();
	}

    public boolean createCashInfo(Customer customer, CustomerBalanceChange change, CustomerBalanceCash cash) {
		boolean result = false;
		try{
			customerDao.updateCustomer(customer);
			long cashId = customerBalanceCashDao.saveCustomerBalanceCash(cash);
			change.setTransactionNo(cashId);
			changeDao.saveCustomerBalanceChange(change);
			result = true;
		}catch(Exception e){
			e.printStackTrace();
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;
    }

    public Map<String,Object>  getOneCash(long id) {
		return customerBalanceCashDao.getOneCash(id);
    }
}

