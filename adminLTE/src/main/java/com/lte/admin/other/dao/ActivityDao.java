package com.lte.admin.other.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.other.entity.Activity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class ActivityDao  extends BaseDao {
    
    public List<Activity> getList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.other.activityMapper.getAllList", filters, pb);
    }
    public List<Map> getList1(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.other.activityMapper.getAllList1", filters, pb);
    }
    public List<Activity> getList() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.other.activityMapper.getAllList");
    }

    public void save(Activity activity) {
        Object obj = sqlSessionTemplate.insert("com.lte.admin.mapper.other.activityMapper.insertSelective", activity);
        return ;
    }

    public void update(Activity activity) {
        Object obj = sqlSessionTemplate.update("com.lte.admin.mapper.other.activityMapper.updateByPrimaryKeySelective", activity);
        return ;
    }

    public void deleteById(Long id) {
        Object obj = sqlSessionTemplate.delete("com.lte.admin.mapper.other.activityMapper.deleteByPrimaryKey", id);
        return ;
    }
    public Activity get(long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.other.activityMapper.selectByPrimaryKey", id);
    }

    public Activity getHotOneACT(String activityType) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.other.activityMapper.getHotOneACT",activityType);
    }

    public Activity getHotOneACT(Map<String,Object> filter) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.other.activityMapper.getHotOneACT1",filter);
    }
    public List<Map> getHotACTList(Map<String,Object> filter) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.other.activityMapper.getHotACTList",filter);
    }
}

