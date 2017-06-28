package com.lte.admin.other.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.other.entity.Activity;
import com.lte.admin.other.entity.ActivityInv;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class ActivityInvDao  extends BaseDao {
    public List<ActivityInv> getList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.other.activity_invMapper.getAllList", filters, pb);
    }

    public void save(ActivityInv activityInv) {
        Object obj = sqlSessionTemplate.insert("com.lte.admin.mapper.other.activity_invMapper.insertSelective", activityInv);
        return ;
    }

    public void update(ActivityInv activityInv) {
        Object obj = sqlSessionTemplate.update("com.lte.admin.mapper.other.activity_invMapper.updateByPrimaryKeySelective", activityInv);
        return ;
    }

    public void deleteById(Long id) {
        Object obj = sqlSessionTemplate.delete("com.lte.admin.mapper.other.activity_invMapper.deleteByPrimaryKey", id);
        return ;
    }
    public ActivityInv get(long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.other.activity_invMapper.selectByPrimaryKey", id);
    }
    public ActivityInv get(Map<String, Object> filter) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.other.activity_invMapper.selectByPrimaryKey1", filter);
    }

    public List<Map<String,Object>> getInvByACT(Long id) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.other.activity_invMapper.getInvByACT", id);
    }
}

