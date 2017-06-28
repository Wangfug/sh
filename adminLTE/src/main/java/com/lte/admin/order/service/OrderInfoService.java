package com.lte.admin.order.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.dao.CarOccupyDao;
import com.lte.admin.car.entity.CarOccupy;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.custom.dao.CustomerDao;
import com.lte.admin.custom.dao.CustomerDiscountHoldDao;
import com.lte.admin.custom.entity.Customer;
import com.lte.admin.custom.entity.CustomerDiscountHold;
import com.lte.admin.order.dao.*;
import com.lte.admin.order.entity.*;
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
public class OrderInfoService extends BaseService<OrderInfo, Integer>{
	@Autowired
	private OrderInfoDao orderInfoDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private OrderFeeDao orderFeeDao;
	@Autowired
	private CarOccupyDao occupyDao;
	@Autowired
	private CustomerDiscountHoldDao holdDao;
	@Autowired
	private OrderStateDao orderStateDao;
	@Autowired
	private OrderWorkDao orderWorkDao;
	@Autowired
	private OrderBillDao orderBillDao;


	public PageList<OrderInfo> getList(Page<OrderInfo> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<OrderInfo>) orderInfoDao.getCustomerList(pb, filters);
	}
	public List<Map> getListByCustomer(Page<OrderInfo> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return  orderInfoDao.getListByCustomer(pb, filters);
	}
	public void save(OrderInfo orderInfo) {
		orderInfoDao.save(orderInfo);
	}

	public void update(OrderInfo orderInfo) {
		orderInfoDao.update(orderInfo);
	}

	/**
	 * 关闭订单
	 * @param orderInfo
	 * @param orderState
	 */
	public boolean update(OrderInfo orderInfo,OrderState orderState,List<OrderWork> works) {
		boolean res = false;
		try{
			orderInfoDao.update(orderInfo);
			orderStateDao.save(orderState);
			for(OrderWork work:works){
				work.setState(0);
				orderWorkDao.update(work);
			}
			res = true;
		}catch(Exception e){
			res = false;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return res;
	}


	public void update(OrderInfo orderInfo,OrderState orderState) {
		orderInfoDao.update(orderInfo);
		orderStateDao.save(orderState);
	}

	public void deleteById(Long id) {
		orderInfoDao.deleteById(id);
	}
	public long getOrderNo(){
		return orderInfoDao.getOrderNo();
	}
	public PageList<Map> getOrderList(Page<OrderInfo> page,Map<String,Object> filters){
		PageBounds pb = createPageBounds(page);
		return (PageList<Map>)orderInfoDao.getOrderList(pb,filters);
	}

	/**
	 * 查市级订单
	 * @param filters
	 * @return
	 */
	public PageList<Map> getOrderList1(Page<OrderInfo> page,Map<String,Object> filters){
		PageBounds pb = createPageBounds(page);
		return (PageList<Map>)orderInfoDao.getOrderList1(pb,filters);
	}
    public Map<String,Object> getOrderDetail(String orderNo) {
		return orderInfoDao.getOrderDetail(orderNo);
    }

	public OrderInfo getByPK(Long id) {
		return orderInfoDao.getByPK(id);
	}
	public OrderInfo getByOrderNo(String orderNo) {
		return orderInfoDao.getByOrderNo(orderNo);
	}

    public List<Map> getList() {
		return orderInfoDao.getList();
    }
    public boolean updateForLockBalance(Customer customer, OrderFee orderFee,OrderInfo order) {
		boolean result = false;
		try{
			customerDao.updateCustomer(customer);
			orderFeeDao.update(orderFee);
			orderInfoDao.update(order);
			result = true;
		}catch(Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;
	}

	public boolean create(OrderInfo orderInfo, CarOccupy occupy, OrderFee orderFee, CustomerDiscountHold hold, OrderBill newBill) {
		boolean result = false;
		try{
			long feeId = orderFeeDao.save(orderFee);
			orderInfo.setFinalFee(feeId);
			orderInfoDao.save(orderInfo);
			occupyDao.saveCarOccupy(occupy);
			if(null != hold){
				holdDao.updateCustomerDiscountHold(hold);
			}
			if(null != newBill){
				orderBillDao.save(newBill);
			}
			result = true;
		}catch(Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;
	}

	public Long getComplaintNumber1(Map<String,Object> filter) {
		return orderInfoDao.getComplaintNumber1(filter);
	}
	public Long getComplaintNumber2(Map<String,Object> filter) {
		return orderInfoDao.getComplaintNumber2(filter);
	}

	public List<Map<String,Object>> getOrderWorkList(String orderNo) {
		return orderInfoDao.getOrderWorkList(orderNo);
	}

	public Map<String,Object> getDetailByOrderNo(String orderNo) {
		return orderInfoDao.getDetailByOrderNo(orderNo);
	}

    public String getForBreakRule(Map<String, Object> filter) {
		return orderInfoDao.getForBreakRule(filter);
    }

    public void closeOrderAuto() {
		orderInfoDao.closeOrderAuto();
    }

    public Integer getNewOrderNumber(Integer number) {
		return orderInfoDao.getNewOrderNumber(number);
    }

    public PageList<Map> getAttachOrderList(Page<OrderInfo> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<Map>)orderInfoDao.getAttachOrderList(pb,filters);
    }
}

