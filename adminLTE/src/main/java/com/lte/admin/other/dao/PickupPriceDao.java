package com.lte.admin.other.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.other.entity.PickupPrice;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class PickupPriceDao  extends BaseDao {
    public List<PickupPrice> getList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.other.pickup_priceMapper.getAllList", filters, pb);
    }

    public void save(PickupPrice pickupPrice) {
        Object obj = sqlSessionTemplate.insert("com.lte.admin.mapper.other.pickup_priceMapper.insertSelective", pickupPrice);
        return ;
    }

    public void update(PickupPrice pickupPrice) {
        Object obj = sqlSessionTemplate.update("com.lte.admin.mapper.other.pickup_priceMapper.updateByPrimaryKeySelective", pickupPrice);
        return ;
    }

    public void deleteById(Long id) {
        Object obj = sqlSessionTemplate.delete("com.lte.admin.mapper.other.pickup_priceMapper.deleteByPrimaryKey", id);
        return ;
    }
    public PickupPrice get(long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.other.pickup_priceMapper.selectByPrimaryKey", id);
    }
}

