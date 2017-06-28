package com.lte.admin.other.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.custom.dao.CustomerDiscountDao;
import com.lte.admin.custom.dao.CustomerDiscountHoldDao;
import com.lte.admin.custom.entity.CustomerDiscount;
import com.lte.admin.custom.entity.CustomerDiscountHold;
import com.lte.admin.other.dao.ActivityInvDao;
import com.lte.admin.other.entity.Activity;
import com.lte.admin.other.entity.ActivityInv;
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
public class ActivityInvService extends BaseService<ActivityInv, Integer>{
	@Autowired
	private ActivityInvDao activityInvDao;
	@Autowired
	private CustomerDiscountDao customerDiscountDao;
	@Autowired
	private CustomerDiscountHoldDao customerDiscountHoldDao;

	public PageList<ActivityInv> getList(Page<ActivityInv> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<ActivityInv>) activityInvDao.getList(pb, filters);
	}

	public void save(ActivityInv activityInv) {
		activityInvDao.save(activityInv);
	}
	public boolean save(ActivityInv actInv, CustomerDiscount customerDiscount,CustomerDiscountHold customerDiscountHold) {
		boolean result = false;
		try{
			activityInvDao.save(actInv);
			customerDiscountDao.updateCustomerDiscount(customerDiscount);
			customerDiscountHoldDao.saveCustomerDiscountHold(customerDiscountHold);
			result = true;
		}catch(Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result = false;
		}
		return result;
	}
	public void update(ActivityInv activityInv) {
		activityInvDao.update(activityInv);
	}

	public void deleteById(Long id) {
		activityInvDao.deleteById(id);
	}

	public ActivityInv get(long id) {
		return activityInvDao.get(id);
	}
	public ActivityInv get(Map<String, Object> filter) {
		return activityInvDao.get(filter);
	}

    public List<Map<String,Object>> getInvByACT(Long id) {
		return activityInvDao.getInvByACT(id);
    }
}

