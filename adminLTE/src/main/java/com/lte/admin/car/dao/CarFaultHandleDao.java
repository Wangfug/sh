package com.lte.admin.car.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.entity.CarFaultHandle;
import com.lte.admin.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class CarFaultHandleDao  extends BaseDao {

    public String saveCarFaultHandle(CarFaultHandle carFaultHandle){
        sqlSessionTemplate.insert("com.lte.admin.mapper.car.CarFaultHandleMapper.insertSelective",carFaultHandle);
        return "success";
    }

    public String updateCarFaultHandle(CarFaultHandle carFaultHandle){
        sqlSessionTemplate.update("com.lte.admin.mapper.car.CarFaultHandleMapper.updateByPrimaryKeySelective",carFaultHandle);
        return "success";
    }

    public List<Map> getAllDetail(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarFaultHandleMapper.getAllDetail", filters, pb);
    }

    /**
     * 根据条件查询
     * @param pb
     * @param filters
     * @return
     */
    public List<CarFaultHandle> getCarFaultHandleList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarFaultHandleMapper.getAllList", filters, pb);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public CarFaultHandle getOneCarFaultHandle(long id){
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.car.CarFaultHandleMapper.selectByPrimaryKey",id);
    }

    public Map getOneCarFaultHandleDetail(Long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.car.CarFaultHandleMapper.selectDetailByPrimaryKey",id);
    }

    public void deleteById(Long id) {
        sqlSessionTemplate.delete("com.lte.admin.mapper.car.CarFaultHandleMapper.deleteByPrimaryKey",id);
    }
}

