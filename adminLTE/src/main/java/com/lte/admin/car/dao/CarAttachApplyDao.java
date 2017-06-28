package com.lte.admin.car.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.car.entity.CarAttachApply;
import com.lte.admin.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class CarAttachApplyDao  extends BaseDao {
    public String saveCarAttachApply(CarAttachApply carAttachApply){
        sqlSessionTemplate.insert("com.lte.admin.mapper.car.CarAttachApplyMapper.insertSelective",carAttachApply);
        return "success";
    }
    public String updateCarAttachApply(CarAttachApply carAttachApply){
        sqlSessionTemplate.update("com.lte.admin.mapper.car.CarAttachApplyMapper.updateByPrimaryKeySelective",carAttachApply);
        return "success";
    }

    public List<Map> getCarAttachApplyList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarAttachApplyMapper.getAllList", filters, pb);
    }

    public CarAttachApply getOneCarAttachApply(long id){
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.car.CarAttachApplyMapper.selectByPrimaryKey",id);
    }

    public void deleteById(Long id) {
        sqlSessionTemplate.delete("com.lte.admin.mapper.car.CarAttachApplyMapper.deleteByPrimaryKey",id);
    }
}

