package com.lte.admin.car.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.car.entity.CarShops;
import com.lte.admin.common.dao.BaseDao;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class CarShopsDao  extends BaseDao {
    public String saveCarShops(CarShops carShops){
        sqlSessionTemplate.insert("com.lte.admin.mapper.car.CarShopsMapper.insertSelective",carShops);
        return "success";
    }

    public String updateCarShops(CarShops carShops){
        sqlSessionTemplate.update("com.lte.admin.mapper.car.CarShopsMapper.updateByPrimaryKeySelective",carShops);
        return "success";
    }

    /**
     * 根据条件查询
     * @param pb
     * @param filters
     * @return
     */
    public List<CarShops> getCarShopsList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarShopsMapper.getAllList", filters, pb);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public CarShops getOneCarShops(long id){
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.car.CarShopsMapper.selectByPrimaryKey",id);
    }
    /**
     * 根据code查询
     * @param code
     * @return
     */
    public CarShops getOneCarShops(String code){
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.car.CarShopsMapper.selectByPrimaryKey1",code);
    }

    public List<CarShops> getList() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarShopsMapper.getAllList");
    }

    public List<Map> getList1() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarShopsMapper.getAllList1");
    }

    public List<Map> getList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarShopsMapper.getAllList2", filters, pb);
    }

    public List<String> getShopCities() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarShopsMapper.getShopCities");
    }

    public List<Map> getCarShops(Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarShopsMapper.getCarShops", filters);
    }

    public String getDianzhangByShop(Long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.car.CarShopsMapper.getDianzhangByShop",id);
    }

    public List<CarShops> getCarShopListTree() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarShopsMapper.getCarShopListTree");
    }

    public List<CarShops> getCarShopsListTreeByParent(String superCode) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarShopsMapper.getCarShopsListTreeByParent",superCode);
    }

    public CarShops getCarShopByJob(String jobCode) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.car.CarShopsMapper.getCarShopByJob",jobCode);
    }

    public List<Map> getNearestShop(Map<String, String> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarShopsMapper.getNearestShop",filters);
    }
    public List<Map> getAllBaseList() {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarShopsMapper.getAllBaseList");
    }
}

