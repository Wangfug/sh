package com.lte.admin.car.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.car.entity.CarMaintenance;
import com.lte.admin.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class CarMaintenanceDao  extends BaseDao {
    public String saveCarMaintenance(CarMaintenance carMaintenance){
        sqlSessionTemplate.insert("com.lte.admin.mapper.car.CarMaintenanceMapper.insertSelective",carMaintenance);
        return "success";
    }

    public String updateCarMaintenance(CarMaintenance carMaintenance){
        sqlSessionTemplate.update("com.lte.admin.mapper.car.CarMaintenanceMapper.updateByPrimaryKeySelective",carMaintenance);
        return "success";
    }

    /**
     * 根据条件查询
     * @param pb
     * @param filters
     * @return
     */
    public List<CarMaintenance> getCarMaintenanceList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarMaintenanceMapper.getAllList", filters, pb);
    }
    /**
     * 根据条件查询
     * @param pb
     * @param filters
     * @return
     */
    public List<Map> getCarMaintenanceList1(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarMaintenanceMapper.getAllList1", filters, pb);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public CarMaintenance getOneCarMaintenance(long id){
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.car.CarMaintenanceMapper.selectByPrimaryKey",id);
    }

    public void deleteById(Long id) {
        sqlSessionTemplate.selectOne("com.lte.admin.mapper.car.CarMaintenanceMapper.deleteByPrimaryKey",id);
    }
}

