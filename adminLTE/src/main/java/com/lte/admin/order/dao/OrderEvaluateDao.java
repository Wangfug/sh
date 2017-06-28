package com.lte.admin.order.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.order.entity.OrderEvaluate;
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
public class OrderEvaluateDao  extends BaseDao {
    public List<OrderEvaluate> getCustomerList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.order.OrderEvaluateMapper.getAllList", filters, pb);
    }

    public void save(OrderEvaluate orderEvaluate) {
        Object obj = sqlSessionTemplate.insert("com.lte.admin.mapper.order.OrderEvaluateMapper.insertSelective", orderEvaluate);
        return ;
    }

    public void update(OrderEvaluate orderEvaluate) {
        Object obj = sqlSessionTemplate.update("com.lte.admin.mapper.order.OrderEvaluateMapper.updateByPrimaryKeySelective", orderEvaluate);
        return ;
    }

    public void deleteById(Long id) {
        Object obj = sqlSessionTemplate.delete("com.lte.admin.mapper.order.OrderEvaluateMapper.deleteByPrimaryKey", id);
        return ;
    }

    public OrderEvaluate get(long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.order.OrderEvaluateMapper.selectByPrimaryKey", id);
    }
}

