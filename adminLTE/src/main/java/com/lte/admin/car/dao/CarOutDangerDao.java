package com.lte.admin.car.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.car.entity.CarOutDanger;
import com.lte.admin.car.entity.CarOutDanger;
import com.lte.admin.common.dao.BaseDao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class CarOutDangerDao  extends BaseDao {
    public String saveCarOutDanger(CarOutDanger carOutDanger){
        sqlSessionTemplate.insert("com.lte.admin.mapper.car.CarOutDangerMapper.insertSelective",carOutDanger);
        return "success";
    }

    public String updateCarOutDanger(CarOutDanger carOutDanger){
        sqlSessionTemplate.update("com.lte.admin.mapper.car.CarOutDangerMapper.updateByPrimaryKeySelective",carOutDanger);
        return "success";
    }

    /**
     * 根据条件查询
     * @param pb
     * @param filters
     * @return
     */
    public List<CarOutDanger> getCarOutDangerList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarOutDangerMapper.getAllList", filters, pb);
    }

    /**
     * 根据条件查询
     * @param pb
     * @param filters
     * @return
     */
    public List<Map> getCarOutDangerList1(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarOutDangerMapper.getAllList1", filters, pb);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public CarOutDanger getOneCarOutDanger(long id){
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.car.CarOutDangerMapper.selectByPrimaryKey",id);
    }

    public void deleteById(Long id) {
        sqlSessionTemplate.delete("com.lte.admin.mapper.car.CarOutDangerMapper.deleteByPrimaryKey",id);
    }
}

