package com.lte.admin.custom.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.custom.entity.CustomerBalanceChange;
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
public class CustomerBalanceChangeDao  extends BaseDao {
    /**
     * 新增一条记录
     * @param customerBalanceChange
     * @return
     */
    public long saveCustomerBalanceChange(CustomerBalanceChange customerBalanceChange){
        sqlSessionTemplate.insert("com.lte.admin.mapper.custom.CustomerBalanceChangeMapper.insertSelective",customerBalanceChange);
        long id = customerBalanceChange.getChangeId();
        return id;
    }
    /**
     * 修改一条记录
     * @param customerBalanceChange
     * @return
     */
    public String updateCustomerBalanceChange(CustomerBalanceChange customerBalanceChange){
        sqlSessionTemplate.update("com.lte.admin.mapper.custom.CustomerBalanceChangeMapper.updateByPrimaryKeySelective",customerBalanceChange);
        return "success";
    }

    /**
     * 根据条件查询
     * @param pb
     * @param filters
     * @return
     */
    public List<CustomerBalanceChange> getCustomerBalanceChangeList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerBalanceChangeMapper.getAllList", filters, pb);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public CustomerBalanceChange getOneCustomerBalanceChange(long id){
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.CustomerBalanceChangeMapper.selectByPrimaryKey",id);
    }

    /**
     * 无条件查询
     * @return
     */
    public List<CustomerBalanceChange> getList() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerBalanceChangeMapper.getAllList");
    }

    public List<Map>  getHistory(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerBalanceChangeMapper.getHistory", filters, pb);
    }
}

