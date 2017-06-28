package com.lte.admin.order.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.order.entity.OrderState;
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
public class OrderStateDao  extends BaseDao {
    public List<OrderState> getOrderList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.order.OrderStateMapper.getAllList", filters, pb);
    }

    public void save(OrderState orderState) {
        Object obj = sqlSessionTemplate.insert("com.lte.admin.mapper.order.OrderStateMapper.insertSelective", orderState);
        return ;
    }

    public void update(OrderState orderState) {
        Object obj = sqlSessionTemplate.update("com.lte.admin.mapper.order.OrderStateMapper.updateByPrimaryKeySelective", orderState);
        return ;
    }

    public void deleteById(Long id) {
        Object obj = sqlSessionTemplate.delete("com.lte.admin.mapper.order.OrderStateMapper.deleteByPrimaryKey", id);
        return ;
    }

    public OrderState get(long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.order.OrderStateMapper.selectByPrimaryKey", id);
    }
}

