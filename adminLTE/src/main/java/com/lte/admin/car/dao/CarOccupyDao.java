package com.lte.admin.car.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.car.entity.CarOccupy;
import com.lte.admin.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class CarOccupyDao  extends BaseDao {
    public String saveCarOccupy(CarOccupy carOccupy){
        sqlSessionTemplate.insert("com.lte.admin.mapper.car.CarOccupyMapper.insertSelective",carOccupy);
        return "success";
    }

    public String updateCarOccupy(CarOccupy carOccupy){
        sqlSessionTemplate.update("com.lte.admin.mapper.car.CarOccupyMapper.updateByPrimaryKeySelective",carOccupy);
        return "success";
    }

    /**
     * 根据条件查询
     * @param pb
     * @param filters
     * @return
     */
    public List<Map> getCarOccupyList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarOccupyMapper.getAllList1", filters, pb);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public CarOccupy getOneCarOccupy(long id){
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.car.CarOccupyMapper.selectByPrimaryKey",id);
    }

    public void deleteById(Long id) {
        sqlSessionTemplate.delete("com.lte.admin.mapper.car.CarOccupyMapper.deleteByPrimaryKey",id);
    }
}

