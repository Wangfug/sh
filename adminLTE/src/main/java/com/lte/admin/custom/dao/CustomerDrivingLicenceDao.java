package com.lte.admin.custom.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.custom.entity.CustomerDrivingLicence;
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
public class CustomerDrivingLicenceDao  extends BaseDao {
    public long saveCustomerDrivingLicence(CustomerDrivingLicence customerDrivingLicence){
        sqlSessionTemplate.insert("com.lte.admin.mapper.custom.CustomerDrivingLicenceMapper.insertSelective",customerDrivingLicence);
        long drivingLicenceId = customerDrivingLicence.getDrivingLicenceId();
        return drivingLicenceId;
    }

    public String updateCustomerDrivingLicence(CustomerDrivingLicence customerDrivingLicence){
        sqlSessionTemplate.update("com.lte.admin.mapper.custom.CustomerDrivingLicenceMapper.updateByPrimaryKeySelective",customerDrivingLicence);
        return "success";
    }

    /**
     * 根据条件查询
     * @param pb
     * @param filters
     * @return
     */
    public List<CustomerDrivingLicence> getCustomerDrivingLicenceList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerDrivingLicenceMapper.getAllList", filters, pb);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public CustomerDrivingLicence getOneCustomerDrivingLicence(long id){
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.CustomerDrivingLicenceMapper.selectByPrimaryKey",id);
    }

    public List<Map> getList() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerDrivingLicenceMapper.getAllList1");
    }
}

