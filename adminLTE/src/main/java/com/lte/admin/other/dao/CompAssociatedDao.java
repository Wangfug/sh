package com.lte.admin.other.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.other.entity.CompAssociated;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class CompAssociatedDao  extends BaseDao {

    public List<CompAssociated> getCompAssociatedList() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.other.comp_associatedMapper.getAllList");
    }

    public List<CompAssociated> getList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.other.comp_associatedMapper.getAllList", filters, pb);
    }

    public void save(CompAssociated compAssociated) {
        Object obj = sqlSessionTemplate.insert("com.lte.admin.mapper.other.comp_associatedMapper.insertSelective", compAssociated);
        return ;
    }

    public void update(CompAssociated compAssociated) {
        Object obj = sqlSessionTemplate.update("com.lte.admin.mapper.other.comp_associatedMapper.updateByPrimaryKeySelective", compAssociated);
        return ;
    }

    public void deleteById(Long id) {
        Object obj = sqlSessionTemplate.delete("com.lte.admin.mapper.other.comp_associatedMapper.deleteByPrimaryKey", id);
        return ;
    }
    public CompAssociated get(long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.other.comp_associatedMapper.selectByPrimaryKey", id);
    }
}

