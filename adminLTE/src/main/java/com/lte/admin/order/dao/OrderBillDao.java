package com.lte.admin.order.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.order.entity.OrderBill;
import com.lte.admin.common.dao.BaseDao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lte.admin.order.entity.OrderBill;
import org.springframework.stereotype.Repository;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class OrderBillDao  extends BaseDao {
    public List<OrderBill> getCustomerList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.order.OrderBillMapper.getAllList", filters, pb);
    }

    public void save(OrderBill orderBill) {
        Object obj = sqlSessionTemplate.insert("com.lte.admin.mapper.order.OrderBillMapper.insertSelective", orderBill);
        return ;
    }

    public void update(OrderBill orderBill) {
        Object obj = sqlSessionTemplate.update("com.lte.admin.mapper.order.OrderBillMapper.updateByPrimaryKeySelective", orderBill);
        return ;
    }

    public void deleteById(Long id) {
        Object obj = sqlSessionTemplate.delete("com.lte.admin.mapper.order.OrderBillMapper.deleteByPrimaryKey", id);
        return ;
    }

    public OrderBill get(long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.order.OrderBillMapper.selectByPrimaryKey", id);
    }
}

