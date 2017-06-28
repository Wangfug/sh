package com.lte.admin.custom.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.custom.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class CustomerDao  extends BaseDao {

    /**
     * 保存客户对象
     * @param customer
     * @return
     */
    public String saveCustomer(Customer customer){
        sqlSessionTemplate.insert("com.lte.admin.mapper.custom.CustomerMapper.insertCustomerSelective",customer);
        return "success";
    }

    /**
     * 根据条件筛选用户或者不传参查询所有用户
     * @param pb
     * @param filters
     * @return
     */
    public List<Customer> getCustomerList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerMapper.getAllList", filters, pb);
    }

    /**
     * 根据条件筛选用户或者不传参查询所有用户
     * @param pb
     * @param filters
     * @return
     */
    public List<Map> getCustomerList1(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerMapper.getAllList2", filters, pb);
    }


    public String updateCustomer(Customer customer){
        sqlSessionTemplate.update("com.lte.admin.mapper.custom.CustomerMapper.updateByPrimaryKeySelective",customer);
        return "success";
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public Customer getOneCustomer(long id){
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.CustomerMapper.selectByPrimaryKey",id);
    }

    public List<Customer> getList() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerMapper.getAllList");
    }
    public List<Map> getList1() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.CustomerMapper.getAllList1");
    }

    public Map getOneCustomer1(long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.CustomerMapper.selectByPrimaryKey1",id);
    }

    public Customer getOneCustomerByMobile(Map<String, Object> filters) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.CustomerMapper.getOneByMobileOrPsw",filters);
    }

    public Map<String, Object> getMyDetail(Map<String, Object> filters) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.CustomerMapper.getMyDetail",filters);
    }

    public Map<String, Object> getCustomerInfo(Map<String, Object> filters) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.CustomerMapper.getCustomerInfo",filters);
    }

    public Map<String, Object> getOneCustomerAuth(long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.CustomerMapper.getOneCustomerAuth",id);
    }
}

