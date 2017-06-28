package com.lte.admin.order.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.consts.ScbzEnum;
import com.lte.admin.order.entity.OrderInfo;
import com.lte.admin.common.dao.BaseDao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class OrderInfoDao  extends BaseDao {

    public List<OrderInfo> getCustomerList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.order.OrderInfoMapper.getAllList", filters, pb);
    }

    public List<Map> getListByCustomer(PageBounds pb, Map<String, Object> filters) {
        int deleteFlag = Integer.valueOf(ScbzEnum.STATE_NORMAL.getCode());
        filters.put("deleteFlag",deleteFlag);
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.order.OrderInfoMapper.getListByCustomer", filters, pb);
    }

    public void save(OrderInfo orderInfo) {
        Object obj = sqlSessionTemplate.insert("com.lte.admin.mapper.order.OrderInfoMapper.insertSelective", orderInfo);
        return ;
    }

    public void update(OrderInfo orderInfo) {
        Object obj = sqlSessionTemplate.update("com.lte.admin.mapper.order.OrderInfoMapper.updateByPrimaryKeySelective", orderInfo);
        return ;
    }

    public void deleteById(Long id) {
        Object obj = sqlSessionTemplate.delete("com.lte.admin.mapper.order.OrderInfoMapper.deleteByPrimaryKey", id);
        return ;
    }
    public long getOrderNo(){
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.order.OrderInfoMapper.getOrderNo");
    }
    public List<Map> getOrderList(PageBounds pb,Map<String,Object> filters){
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.order.OrderInfoMapper.getOrderList", filters, pb);
    }

    /**
     * 查市级订单 市所有门店订单
     * @param filters
     * @return
     */
    public List<Map> getOrderList1(PageBounds pb,Map<String,Object> filters){
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.order.OrderInfoMapper.getOrderList66", filters, pb);
    }
    public Map<String,Object> getOrderDetail(String orderNo) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.order.OrderInfoMapper.getOrderDetail", orderNo);
    }

    public OrderInfo getByPK(Long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.order.OrderInfoMapper.selectByPrimaryKey", id);
    }
    public OrderInfo getByOrderNo(String orderNo) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.order.OrderInfoMapper.getByOrderNo", orderNo);
    }

    public List<Map> getList() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.order.OrderInfoMapper.getOrderNoList");
    }

    public Long getComplaintNumber1(Map<String, Object> filter) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.order.OrderInfoMapper.getComplaintNumber1", filter);
    }
    public Long getComplaintNumber2(Map<String, Object> filter) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.order.OrderInfoMapper.getComplaintNumber2", filter);
    }

    public List<Map<String,Object>> getOrderWorkList(String orderNo) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.order.OrderInfoMapper.getOrderWorkList", orderNo);
    }

    public String getForBreakRule(Map<String, Object> filter) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.order.OrderInfoMapper.getForBreakRule", filter);
    }

    public Map<String,Object> getDetailByOrderNo(String orderNo) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.order.OrderInfoMapper.getDetailByOrderNo", orderNo);
    }

    public void closeOrderAuto() {
        sqlSessionTemplate.update("com.lte.admin.mapper.order.OrderInfoMapper.closeOrder");
    }

    public Integer getNewOrderNumber(Integer number) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.order.OrderInfoMapper.getNewOrderNumber",number);
    }

    public List<Map> getAttachOrderList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.order.OrderInfoMapper.getAttachOrderList", filters, pb);
    }
}

