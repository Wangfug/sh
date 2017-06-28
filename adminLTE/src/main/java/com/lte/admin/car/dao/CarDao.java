package com.lte.admin.car.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.car.entity.Car;
import com.lte.admin.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class CarDao  extends BaseDao {
    /**
     * 新增车辆信息
     * @param car
     * @return
     */
    public long saveCar(Car car){
        sqlSessionTemplate.insert("com.lte.admin.mapper.car.CarMapper.insertSelective",car);
        long id = car.getCarId();
        return id;
    }
    /**
     * 更新车辆信息
     * @param car
     * @return
     */
    public String updateCar(Car car){
        sqlSessionTemplate.update("com.lte.admin.mapper.car.CarMapper.updateByPrimaryKeySelective",car);
        return "success";
    }

    /**
     * 根据条件查询
     * @param pb
     * @param filters
     * @return
     */
    public List<Car> getCarList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarMapper.getAllList", filters, pb);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public Car getOneCar(long id){
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.car.CarMapper.selectByPrimaryKey",id);
    }

    public List<Car> getList() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarMapper.getAllList1");
    }
    public List<Map> getList1() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarMapper.getAllList2");
    }

    public void deleteById(Long id) {
        sqlSessionTemplate.delete("com.lte.admin.mapper.car.CarMapper.deleteByPrimaryKey",id);
    }

    public List<Map> getCarByShop(PageBounds pb,Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarMapper.getCarByShop",filters,pb);
    }
    public Long getCarByFilter(Map<String, Object> filters) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.car.CarMapper.getCarByFilter",filters);
    }

    public List<String> getCarBrands() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarMapper.getCarBrands");
    }

    public Car getCarByCode(String carCode) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.car.CarMapper.getCarByCode",carCode);
    }

    public List<Car> getCarsByShopId(Map<String, Object> filter) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarMapper.getCarsByShopId",filter);
    }
    public List<Car> getCarsByCity(Map<String, Object> filter) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarMapper.getCarsByCity",filter);
    }
    public Map<String,Object> getCarByCode1(String carCode) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.car.CarMapper.getCarByCode1",carCode);
    }
    public List<Car> getListByShop1(PageBounds pb,Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarMapper.getListByShop1",filters,pb);
    }
    public List<Car> getListByShop2(PageBounds pb,Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarMapper.getListByShop2",filters,pb);
    }
    public List<Map> getCarBrandModel() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarMapper.getCarBrandModel");
    }
}

