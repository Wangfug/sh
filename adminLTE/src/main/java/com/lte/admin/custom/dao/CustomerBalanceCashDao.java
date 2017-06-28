package com.lte.admin.custom.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.custom.entity.CustomerBalanceCash;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class CustomerBalanceCashDao  extends BaseDao {
    /**
     * 新增一条记录
     * @return
     */
    public long saveCustomerBalanceCash(CustomerBalanceCash cash){
        sqlSessionTemplate.insert("com.lte.admin.mapper.custom.CustomerBalanceCashMapper.insertSelective",cash);
        long id = cash.getCashId();
        return id;
    }
    /**
     * 修改一条记录
     * @return
     */
    public String updateCustomerBalanceCash(CustomerBalanceCash cash){
        sqlSessionTemplate.update("com.lte.admin.mapper.custom.CustomerBalanceCashMapper.updateByPrimaryKeySelective",cash);
        return "success";
    }

    /**
     * 根据条件查询
     * @param pb
     * @param filters
     * @return
     */
    public List<CustomerBalanceCash> getCustomerBalanceCashList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerBalanceCashMapper.getAllList", filters, pb);
    }

    public List<Map> getUserList() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerBalanceCashMapper.getUserList");
    }

    public Map<String,Object> getOneCash(long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.CustomerBalanceCashMapper.getOneCash",id);
    }

    public CustomerBalanceCash getOneCashById(long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.CustomerBalanceCashMapper.selectByPrimaryKey",id);
    }
}

