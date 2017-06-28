package com.lte.admin.order.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.consts.OrderWorkTypeEnum;
import com.lte.admin.common.consts.ScbzEnum;
import com.lte.admin.order.entity.OrderWork;
import com.lte.admin.common.dao.BaseDao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class OrderWorkDao  extends BaseDao {
    public List<OrderWork> getCustomerList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.order.OrderWorkMapper.getAllList", filters, pb);
    }
    public List<Map> getCustomerList1(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.order.OrderWorkMapper.getAllList1", filters, pb);
    }

    public void save(OrderWork orderWork) {
        Object obj = sqlSessionTemplate.insert("com.lte.admin.mapper.order.OrderWorkMapper.insertSelective", orderWork);
        return ;
    }

    public void update(OrderWork orderWork) {
        Object obj = sqlSessionTemplate.update("com.lte.admin.mapper.order.OrderWorkMapper.updateByPrimaryKeySelective", orderWork);
        return ;
    }

    public void deleteById(Long id) {
        Object obj = sqlSessionTemplate.selectList("com.lte.admin.mapper.order.OrderWorkMapper.deleteByPrimaryKey", id);
        return ;
    }

    public OrderWork get(long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.order.OrderWorkMapper.selectByPrimaryKey", id);
    }

    public List<Map> getListByToken(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.order.OrderWorkMapper.getListByToken", filters, pb);
    }

    public List<Map<String,Object>> getByOrder(String orderNo) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.order.OrderWorkMapper.getByOrder",orderNo);
    }
    /**
     * 多条件查询
     * @param filter
     * @return
     */
    public List<Map<String,Object>> getByOrderShop(Map<String,Object> filter) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.order.OrderWorkMapper.getByOrderShop",filter);
    }

    public Long getReturnNumber(String token) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.order.OrderWorkMapper.getReturnNumber",token);
    }

    public Long getGetNumber(String token) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.order.OrderWorkMapper.getGetNumber",token);
    }

    public List<OrderWork> getByOrder(Map<String,Object> filter) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.order.OrderWorkMapper.getByOrder1",filter);
    }

    public List<OrderWork> getByOrderFor(String orderNo) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.order.OrderWorkMapper.getByOrderFor",orderNo);
    }

    public Long getNotAssign(String shopCode) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.order.OrderWorkMapper.getNotAssign",shopCode);
    }
}

