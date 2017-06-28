package com.lte.admin.custom.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.custom.entity.CustomerCredential;
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
public class CustomerCredentialDao  extends BaseDao {
    public long saveCustomerCredential(CustomerCredential customerCredential){
        sqlSessionTemplate.insert("com.lte.admin.mapper.custom.CustomerCredentialMapper.insertSelective",customerCredential);
        long credentialId = customerCredential.getCredentialId();
        return credentialId;
    }

    public String updateCustomerCredential(CustomerCredential customerCredential){
        sqlSessionTemplate.update("com.lte.admin.mapper.custom.CustomerCredentialMapper.updateByPrimaryKeySelective",customerCredential);
        return "success";
    }

    /**
     * 根据条件查询
     * @param pb
     * @param filters
     * @return
     */
    public List<Map> getCustomerCredentialList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerCredentialMapper.getAllList", filters, pb);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public CustomerCredential getOneCustomerCredential(long id){
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.CustomerCredentialMapper.selectByPrimaryKey",id);
    }

    public List<CustomerCredential> getList() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerCredentialMapper.getAllList");
    }
    public List<Map> getList1() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerCredentialMapper.getAllList1");
    }

    public List<CustomerCredential> getCredentialsByType(String type) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerCredentialMapper.getCredentialsByType",type);
    }

    public CustomerCredential getByCode(String credentialcode) {
        return  sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.CustomerCredentialMapper.getByCode",credentialcode);
    }
}

