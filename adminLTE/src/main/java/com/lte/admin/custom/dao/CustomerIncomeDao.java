package com.lte.admin.custom.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.custom.entity.CustomerIncome;
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
public class CustomerIncomeDao  extends BaseDao {
    public String saveCustomerIncome(CustomerIncome customerIncome){
        sqlSessionTemplate.insert("com.lte.admin.mapper.custom.CustomerIncomeMapper.insertSelective",customerIncome);
        return "success";
    }

    public String updateCustomerIncome(CustomerIncome customerIncome){
        sqlSessionTemplate.update("com.lte.admin.mapper.custom.CustomerIncomeMapper.updateByPrimaryKeySelective",customerIncome);
        return "success";
    }

    /**
     * 根据条件查询
     * @param pb
     * @param filters
     * @return
     */
    public List<CustomerIncome> getCustomerIncomeList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerIncomeMapper.getAllList", filters, pb);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public CustomerIncome getOneCustomerIncome(long id){
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.CustomerIncomeMapper.selectByPrimaryKey",id);
    }

    public List<CustomerIncome> getList() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerIncomeMapper.getAllList");
    }
}

