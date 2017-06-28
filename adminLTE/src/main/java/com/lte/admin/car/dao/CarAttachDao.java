package com.lte.admin.car.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.car.entity.CarAttach;
import com.lte.admin.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class CarAttachDao  extends BaseDao {
    /**
     * 新增车辆挂靠信息
     * @param carAttach
     * @return
     */
    public String saveCarAttach(CarAttach carAttach){
        sqlSessionTemplate.insert("com.lte.admin.mapper.car.CarAttachMapper.insertSelective",carAttach);
        return "success";
    }
    /**
     * 更新车辆挂靠信息
     * @param carAttach
     * @return
     */
    public String updateCarAttach(CarAttach carAttach){
        sqlSessionTemplate.update("com.lte.admin.mapper.car.CarAttachMapper.updateByPrimaryKeySelective",carAttach);
        return "success";
    }

    /**
     * 根据条件查询
     * @param pb
     * @param filters
     * @return
     */
    public List<CarAttach> getCarAttachList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarAttachMapper.getAllList", filters, pb);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public CarAttach getOneCarAttach(long id){
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.car.CarAttachMapper.selectByPrimaryKey",id);
    }

    public void deleteById(Long id) {
        sqlSessionTemplate.delete("com.lte.admin.mapper.car.CarAttachMapper.deleteByPrimaryKey",id);
    }

    public CarAttach getOneCarAttachByCar(String carCode) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.car.CarAttachMapper.getOneCarAttachByCar",carCode);
    }
}

