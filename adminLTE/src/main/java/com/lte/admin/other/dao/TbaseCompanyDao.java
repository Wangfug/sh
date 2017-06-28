package com.lte.admin.other.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.entity.Company;
import com.lte.admin.other.entity.TbaseCompany;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class TbaseCompanyDao  extends BaseDao {
    public List<TbaseCompany> getList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.other.t_base_companyMapper.getAllList", filters, pb);
    }

    public void save(TbaseCompany tbaseCompany) {
        Object obj = sqlSessionTemplate.insert("com.lte.admin.mapper.other.t_base_companyMapper.insertSelective", tbaseCompany);
        return ;
    }

    public void update(TbaseCompany tbaseCompany) {
        Object obj = sqlSessionTemplate.update("com.lte.admin.mapper.other.t_base_companyMapper.updateByPrimaryKeySelective", tbaseCompany);
        return ;
    }

    public void deleteById(Long id) {
        Object obj = sqlSessionTemplate.delete("com.lte.admin.mapper.other.t_base_companyMapper.deleteByPrimaryKey", id);
        return ;
    }
    public TbaseCompany get(long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.other.t_base_companyMapper.selectByPrimaryKey", id);
    }

    public List<Map> getList1(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.other.t_base_companyMapper.getAllList1", filters, pb);
    }

    public List<Map> getList() {
            return sqlSessionTemplate.selectList("com.lte.admin.mapper.other.t_base_companyMapper.getAllList2");
    }

    /**
     * 根据父级公司编码查公司
     * @param parentComanyCode
     * @return
     */
    public List<TbaseCompany> getCompanyListTreeByParent(String parentComanyCode) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.other.t_base_companyMapper.getCompanyListTreeByParent",parentComanyCode);
    }
//查出所有根公司
    public List<Company> getCompanyListTree() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.DeptMapper.findBmListTreeRoot");
    }
}

