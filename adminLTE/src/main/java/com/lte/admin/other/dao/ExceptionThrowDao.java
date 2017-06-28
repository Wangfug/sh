package com.lte.admin.other.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.other.entity.ExceptionThrow;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class ExceptionThrowDao  extends BaseDao {
    public List<ExceptionThrow> getList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.other.exception_throwMapper.getAllList", filters, pb);
    }

    public void save(ExceptionThrow exceptionThrow) {
        Object obj = sqlSessionTemplate.insert("com.lte.admin.mapper.other.exception_throwMapper.insertSelective", exceptionThrow);
        return ;
    }

    public void update(ExceptionThrow exceptionThrow) {
        Object obj = sqlSessionTemplate.update("com.lte.admin.mapper.other.exception_throwMapper.updateByPrimaryKeySelective", exceptionThrow);
        return ;
    }

    public void deleteById(Long id) {
        Object obj = sqlSessionTemplate.delete("com.lte.admin.mapper.other.exception_throwMapper.deleteByPrimaryKey", id);
        return ;
    }
    public ExceptionThrow get(long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.other.exception_throwMapper.selectByPrimaryKey", id);
    }
}

