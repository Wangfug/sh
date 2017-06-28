package com.lte.admin.custom.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.custom.entity.CustomerDiscount;
import com.lte.admin.custom.entity.CustomerDiscountHold;
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
public class CustomerDiscountHoldDao  extends BaseDao {
    public String saveCustomerDiscountHold(CustomerDiscountHold customerDiscountHold){
        sqlSessionTemplate.insert("com.lte.admin.mapper.custom.CustomerDiscountHoldMapper.insertSelective",customerDiscountHold);
        return "success";
    }

    public String updateCustomerDiscountHold(CustomerDiscountHold customerDiscountHold){
        sqlSessionTemplate.update("com.lte.admin.mapper.custom.CustomerDiscountHoldMapper.updateByPrimaryKeySelective",customerDiscountHold);
        return "success";
    }

    /**
     * 根据条件查询
     * @param pb
     * @param filters
     * @return
     */
    public List<CustomerDiscountHold> getCustomerDiscountHoldList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerDiscountHoldMapper.getAllList", filters, pb);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public CustomerDiscountHold getOneCustomerDiscountHold(long id){
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.CustomerDiscountHoldMapper.selectByPrimaryKey",id);
    }

    public List<CustomerDiscountHold> getList() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerDiscountHoldMapper.getAllList");
    }

    public Map getDetail(long holdId) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.CustomerDiscountHoldMapper.getOneDetail",holdId);
    }

    public CustomerDiscountHold getByOrderNo(String orderNo) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.CustomerDiscountHoldMapper.getByOrderNo",orderNo);
    }

    public Integer getByCusAndDis(Map<String, Object> filter) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.CustomerDiscountHoldMapper.getByCusAndDis",filter);
    }
}

