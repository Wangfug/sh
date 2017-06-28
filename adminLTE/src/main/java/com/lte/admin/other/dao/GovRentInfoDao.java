package com.lte.admin.other.dao;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.common.dao.BaseDao;
import com.lte.admin.other.entity.GovRentInfo;
import org.springframework.stereotype.Repository;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class GovRentInfoDao  extends BaseDao {
    public List<GovRentInfo> getList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.other.GovRentInfoMapper.getAllList", filters, pb);
    }

    public void save(GovRentInfo govRentInfo) {
        sqlSessionTemplate.insert("com.lte.admin.mapper.other.GovRentInfoMapper.insertSelective", govRentInfo);
    }

    public void update(GovRentInfo govRentInfo) {
        sqlSessionTemplate.update("com.lte.admin.mapper.other.GovRentInfoMapper.updateByPrimaryKeySelective", govRentInfo);
    }

    public void deleteById(Long id) {
        sqlSessionTemplate.delete("com.lte.admin.mapper.other.GovRentInfoMapper.deleteByPrimaryKey", id);
    }
    public GovRentInfo get(long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.other.GovRentInfoMapper.selectByPrimaryKey", id);
    }

    public GovRentInfo getByCustomer(Long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.other.GovRentInfoMapper.getByCustomer", id);
    }
}

