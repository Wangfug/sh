package com.lte.admin.other.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.other.entity.TbaseAttachment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class TbaseAttachmentDao  extends BaseDao {
    public List<TbaseAttachment> getList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.other.t_base_attachmentMapper.getAllList", filters, pb);
    }

    public void save(TbaseAttachment tbaseAttachment) {
        Object obj = sqlSessionTemplate.insert("com.lte.admin.mapper.other.t_base_attachmentMapper.insertSelective", tbaseAttachment);
        return ;
    }

    public void update(TbaseAttachment tbaseAttachment) {
        Object obj = sqlSessionTemplate.update("com.lte.admin.mapper.other.t_base_attachmentMapper.updateByPrimaryKeySelective", tbaseAttachment);
        return ;
    }

    public void deleteById(Long id) {
        Object obj = sqlSessionTemplate.delete("com.lte.admin.mapper.other.t_base_attachmentMapper.deleteByPrimaryKey", id);
        return ;
    }
    public TbaseAttachment get(long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.other.t_base_attachmentMapper.selectByPrimaryKey", id);
    }
}

