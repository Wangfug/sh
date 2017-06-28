package com.lte.admin.custom.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.custom.entity.TbaseEmployee;
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
public class TbaseEmployeeDao  extends BaseDao {
    public String saveTbaseEmployee(TbaseEmployee tbaseEmployee){
        sqlSessionTemplate.insert("com.lte.admin.mapper.custom.TbaseEmployeeMapper.insertSelective",tbaseEmployee);
        return "success";
    }

    public String updateTbaseEmployee(TbaseEmployee tbaseEmployee){
        sqlSessionTemplate.update("com.lte.admin.mapper.custom.TbaseEmployeeMapper.updateByPrimaryKeySelective",tbaseEmployee);
        return "success";
    }

    /**
     * 根据条件查询
     * @param pb
     * @param filters
     * @return
     */
    public List<Map> getTbaseEmployeeList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.TbaseEmployeeMapper.getAllList", filters, pb);
    }
    /**
     * 根据条件查询
     * @param pb
     * @param filters
     * @return
     */
    public List<Map> getEmpListByDZ(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.TbaseEmployeeMapper.getEmpListByDZ", filters, pb);
    }
    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public TbaseEmployee getOneTbaseEmployee(long id){
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.TbaseEmployeeMapper.selectByPrimaryKey",id);
    }

    public List<TbaseEmployee> getList() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.TbaseEmployeeMapper.getAllList");
    }

    /*public List<Map> getManager() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.TbaseEmployeeMapper.getManager");
    }
*/
    public TbaseEmployee getListByToken(Map<String, Object> filters) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.TbaseEmployeeMapper.selectByToken",filters);
    }

    public TbaseEmployee getOneByCreateBy(String code) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.TbaseEmployeeMapper.getOneByCreateBy",code);
    }

    public Map<String,Object> getEmployeeByToken(String token) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.TbaseEmployeeMapper.getEmployeeByToken",token);
    }
    public TbaseEmployee getEmployeeByToken1(String token) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.TbaseEmployeeMapper.getEmployeeByToken1",token);
    }

    public List<Map<String,Object>> getEmpByShop(String shopCode) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.custom.TbaseEmployeeMapper.getEmpByShop",shopCode);
    }

    public Map<String,Object> getEmployeeInfo(long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.TbaseEmployeeMapper.getEmployeeInfo",id);
    }

    public void delete(Long id) {
        sqlSessionTemplate.delete("com.lte.admin.mapper.custom.TbaseEmployeeMapper.deleteByPrimaryKey",id);
    }

    public Integer getEno() {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.custom.TbaseEmployeeMapper.getEno");
    }
}

