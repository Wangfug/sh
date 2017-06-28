package com.lte.admin.order.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.order.entity.OrderCarCheck;
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
public class OrderCarCheckDao  extends BaseDao {
    public List<OrderCarCheck> getCustomerList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.order.OrderCarCheckMapper.getAllList", filters, pb);
    }

    public void save(OrderCarCheck orderCarCheck) {
        Object obj = sqlSessionTemplate.selectList("com.lte.admin.mapper.order.OrderCarCheckMapper.insertSelective", orderCarCheck);
        return ;
    }

    public void update(OrderCarCheck orderCarCheck) {
        Object obj = sqlSessionTemplate.selectList("com.lte.admin.mapper.order.OrderCarCheckMapper.updateByPrimaryKeySelective", orderCarCheck);
        return ;
    }

    public void deleteById(Long id) {
        Object obj = sqlSessionTemplate.selectList("com.lte.admin.mapper.order.OrderCarCheckMapper.deleteByPrimaryKey", id);
        return ;
    }
}

