package com.lte.admin.car.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.car.entity.CarOutOrIn;
import com.lte.admin.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class CarOutOrInDao  extends BaseDao {
    public String saveCarOutOrIn(CarOutOrIn carOutOrIn){
        sqlSessionTemplate.insert("com.lte.admin.mapper.car.CarOutOrInMapper.insertSelective",carOutOrIn);
        return "success";
    }

    public String updateCarOutOrIn(CarOutOrIn carOutOrIn){
        sqlSessionTemplate.update("com.lte.admin.mapper.car.CarOutOrInMapper.updateByPrimaryKeySelective",carOutOrIn);
        return "success";
    }

    /**
     * 根据条件查询
     * @param pb
     * @param filters
     * @return
     */
    public List<CarOutOrIn> getCarOutOrInList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarOutOrInMapper.getAllList", filters, pb);
    }
        public List<Map> getAllDetail(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarOutOrInMapper.getAllDetail", filters, pb);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public CarOutOrIn getOneCarOutOrIn(long id){
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.car.CarOutOrInMapper.selectByPrimaryKey",id);
    }

    public void deleteById(Long id) {
        sqlSessionTemplate.delete("com.lte.admin.mapper.car.CarOutOrInMapper.deleteByPrimaryKey",id);
    }
}

