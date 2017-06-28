package com.lte.admin.custom.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.custom.entity.CustomerCreditCard;
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
public class CustomerCreditCardDao  extends BaseDao {
    public String saveCustomerCreditCard(CustomerCreditCard customerCreditCard){
        sqlSessionTemplate.insert("com.lte.admin.mapper.custom.CustomerCreditCardMapper.insertSelective",customerCreditCard);
        return "success";
    }

    public String updateCustomerCreditCard(CustomerCreditCard customerCreditCard){
        sqlSessionTemplate.update("com.lte.admin.mapper.custom.CustomerCreditCardMapper.updateByPrimaryKeySelective",customerCreditCard);
        return "success";
    }

    /**
     * 根据条件查询
     * @param pb
     * @param filters
     * @return
     */
    public List<Map> getCustomerCreditCardList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerCreditCardMapper.getAllList", filters, pb);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public CustomerCreditCard getOneCustomerCreditCard(long id){
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.CustomerCreditCardMapper.selectByPrimaryKey",id);
    }

    public List<CustomerCreditCard> getList() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerCreditCardMapper.getAllList");
    }
}

