package com.lte.admin.car.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.car.entity.CarRentPrice;
import com.lte.admin.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class CarRentPriceDao  extends BaseDao {
    public String saveCarRentPrice(CarRentPrice carRentPrice){
        sqlSessionTemplate.insert("com.lte.admin.mapper.car.CarRentPriceMapper.insertSelective",carRentPrice);
        return "success";
    }

    public String updateCarRentPrice(CarRentPrice carRentPrice){
        sqlSessionTemplate.update("com.lte.admin.mapper.car.CarRentPriceMapper.updateByPrimaryKeySelective",carRentPrice);
        return "success";
    }

    /**
     * 根据条件查询
     * @param pb
     * @param filters
     * @return
     */
    public List<CarRentPrice> getCarRentPriceList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarRentPriceMapper.getAllList1", filters, pb);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public CarRentPrice getOneCarRentPrice(long id){
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.car.CarRentPriceMapper.selectByPrimaryKey",id);
    }

    public void deleteById(Long id) {
        sqlSessionTemplate.delete("com.lte.admin.mapper.car.CarRentPriceMapper.deleteByPrimaryKey",id);
    }

    public Map getDeatilByModels(Map<String, Object> filters) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.car.CarRentPriceMapper.getDeatilByModels", filters);
    }

    public List<Map> getHotCar(String city) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarRentPriceMapper.getHotCar", city);
    }
}

