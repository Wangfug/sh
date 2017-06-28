package com.lte.admin.other.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.other.entity.TbaseCity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class TbaseCityDao  extends BaseDao {
    public List<TbaseCity> getList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.other.t_base_cityMapper.getAllList1", filters, pb);
    }

    public void save(TbaseCity tbaseCity) {
        Object obj = sqlSessionTemplate.insert("com.lte.admin.mapper.other.t_base_cityMapper.insertSelective", tbaseCity);
        return ;
    }

    public void update(TbaseCity tbaseCity) {
        Object obj = sqlSessionTemplate.update("com.lte.admin.mapper.other.t_base_cityMapper.updateByPrimaryKeySelective", tbaseCity);
        return ;
    }

    public void deleteById(Long id) {
        Object obj = sqlSessionTemplate.delete("com.lte.admin.mapper.other.t_base_cityMapper.deleteByPrimaryKey", id);
        return ;
    }
    public TbaseCity get(long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.other.t_base_cityMapper.selectByPrimaryKey", id);
    }
    public List<TbaseCity> getList() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.other.t_base_cityMapper.getAllList");
    }

    public List<TbaseCity> getListByParent(long pid) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.other.t_base_cityMapper.getListByParent",pid);
    }
    public List<Map> getList1() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.other.t_base_cityMapper.getAllList2");
    }

    public List<Map> getNameList(Long pid) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.other.t_base_cityMapper.getNameList",pid);
    }
}

