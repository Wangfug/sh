package com.lte.admin.order.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.order.entity.OrderFee;
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
public class OrderFeeDao  extends BaseDao {
    public List<OrderFee> getCustomerList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.order.OrderFeeMapper.getAllList", filters, pb);
    }

    public long save(OrderFee orderFee) {
        sqlSessionTemplate.insert("com.lte.admin.mapper.order.OrderFeeMapper.insertSelective", orderFee);
        long feeId = orderFee.getFeeId();
        return feeId;
    }

    public void update(OrderFee orderFee) {
        Object obj = sqlSessionTemplate.update("com.lte.admin.mapper.order.OrderFeeMapper.updateByPrimaryKeySelective", orderFee);
        return ;
    }

    public void deleteById(Long id) {
        Object obj = sqlSessionTemplate.delete("com.lte.admin.mapper.order.OrderFeeMapper.deleteByPrimaryKey", id);
        return ;
    }

    public OrderFee get(long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.order.OrderFeeMapper.selectByPrimaryKey", id);
    }
}

