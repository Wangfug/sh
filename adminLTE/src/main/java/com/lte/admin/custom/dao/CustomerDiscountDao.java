package com.lte.admin.custom.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.custom.entity.CustomerDiscount;
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
public class CustomerDiscountDao  extends BaseDao {
    public String saveCustomerDiscount(CustomerDiscount customerDiscount){
        sqlSessionTemplate.insert("com.lte.admin.mapper.custom.CustomerDiscountMapper.insertSelective",customerDiscount);
        return "success";
    }

    public String updateCustomerDiscount(CustomerDiscount customerDiscount){
        sqlSessionTemplate.update("com.lte.admin.mapper.custom.CustomerDiscountMapper.updateByPrimaryKeySelective",customerDiscount);
        return "success";
    }

    /**
     * 根据条件查询
     * @param pb
     * @param filters
     * @return
     */
    public List<CustomerDiscount> getCustomerDiscountList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerDiscountMapper.getAllList", filters, pb);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public CustomerDiscount getOneCustomerDiscount(long id){
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.CustomerDiscountMapper.selectByPrimaryKey",id);
    }

    public List<CustomerDiscount> getList() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerDiscountMapper.getAllList");
    }

    public List<Map> getList1() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerDiscountMapper.getAllList1");
    }

    public List<CustomerDiscount> getEffectiveList() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerDiscountMapper.getEffectiveList");
    }
    public List<Map> getAllDiscount(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerDiscountMapper.getAllDiscount", filters, pb);
    }
}

