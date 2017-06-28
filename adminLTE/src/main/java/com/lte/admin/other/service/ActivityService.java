package com.lte.admin.other.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.custom.controller.CustomerDiscountController;
import com.lte.admin.custom.dao.CustomerDiscountDao;
import com.lte.admin.custom.entity.CustomerDiscount;
import com.lte.admin.other.dao.ActivityDao;
import com.lte.admin.other.entity.Activity;
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
public class ActivityService extends BaseService<Activity, Integer>{
	@Autowired
	private ActivityDao activityDao;
    @Autowired
    private CustomerDiscountDao customerDiscountDao;

    public PageList<Activity> getList(Page<Activity> page, Map<String, Object> filters) {
        PageBounds pb = createPageBounds(page);
        return (PageList<Activity>) activityDao.getList(pb, filters);
    }

    public List<Activity> getList() {
        return activityDao.getList();
    }

    public void save(Activity activity) {
        activityDao.save(activity);
    }
    public String save(Activity activity, CustomerDiscount customerDiscount) {
        String result = "false";
        try{
            activityDao.save(activity);
            if(customerDiscount.getId()!=null)
            customerDiscountDao.updateCustomerDiscount(customerDiscount);
            result = "success";
             }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result = "false";
        }
        return result;
    }
    public void update(Activity activity) {
        activityDao.update(activity);
    }
    public String update(Activity activity, CustomerDiscount customerDiscount) {
        String result = "false";
        try{
            activityDao.update(activity);
            if(customerDiscount.getId()!=null)
            customerDiscountDao.updateCustomerDiscount(customerDiscount);
            result = "success";
        }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result = "false";
        }
        return result;
    }

    public void deleteById(Long id) {
        activityDao.deleteById(id);
    }

    public Activity get(long id) {
        return activityDao.get(id);
    }

    public PageList<Map> getList1(Page<Activity> page, Map<String, Object> filter) {
        PageBounds pb = createPageBounds(page);
        return (PageList<Map>) activityDao.getList1(pb, filter);
    }

    public Activity getHotOneACT(String type) {
        return activityDao.getHotOneACT(type);
    }
    public Activity getHotOneACT(Map<String,Object> filter) {
        return activityDao.getHotOneACT(filter);
    }

    public List<Map> getHotACTList(Map<String, Object> filter) {
        return activityDao.getHotACTList(filter);
    }
}

