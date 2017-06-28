package com.lte.admin.order.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.order.entity.OrderAccount;
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
public class OrderAccountDao  extends BaseDao {
    public List<OrderAccount> getCustomerList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.order.OrderAccountMapper.getAllList", filters, pb);
    }

    public void save(OrderAccount orderAccount) {
        Object obj = sqlSessionTemplate.insert("com.lte.admin.mapper.order.OrderAccountMapper.insertSelective", orderAccount);
        return ;
    }

    public void update(OrderAccount orderAccount) {
        Object obj = sqlSessionTemplate.update("com.lte.admin.mapper.order.OrderAccountMapper.updateByPrimaryKeySelective", orderAccount);
        return ;
    }

    public void deleteById(Long id) {
        Object obj = sqlSessionTemplate.delete("com.lte.admin.mapper.order.OrderAccountMapper.deleteByPrimaryKey", id);
        return ;
    }

    public OrderAccount get(long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.order.OrderAccountMapper.selectByPrimaryKey", id);
    }
}

