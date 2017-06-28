package com.lte.admin.car.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.car.entity.CarBreakRule;
import com.lte.admin.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class CarBreakRuleDao  extends BaseDao {
    /**
     * 新增车辆违章信息
     * @param carBreakRule
     * @return
     */
    public String saveCarBreakRule(CarBreakRule carBreakRule){
        sqlSessionTemplate.insert("com.lte.admin.mapper.car.CarBreakRuleMapper.insertSelective",carBreakRule);
        return "success";
    }
    /**
     * 更新车辆违章信息
     * @param carBreakRule
     * @return
     */
    public String updateCarBreakRule(CarBreakRule carBreakRule){
        sqlSessionTemplate.update("com.lte.admin.mapper.car.CarBreakRuleMapper.updateByPrimaryKeySelective",carBreakRule);
        return "success";
    }

    /**
     * 根据条件查询
     * @param pb
     * @param filters
     * @return
     */
    public List<CarBreakRule> getCarBreakRuleList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarBreakRuleMapper.getAllList", filters, pb);
    }

    /**
     * 根据条件查询
     * @param pb
     * @param filters
     * @return
     */
    public List<Map<String,Object>> getCarBreakRuleList1(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarBreakRuleMapper.getAllDetailList", filters, pb);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public CarBreakRule getOnecarBreakRule(long id){
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.car.CarBreakRuleMapper.selectByPrimaryKey",id);
    }
    public CarBreakRule getOnecarBreakRule(String illegalNo){
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.car.CarBreakRuleMapper.getOnecarBreakRule",illegalNo);
    }

    public List<Map> getDetailList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarBreakRuleMapper.getAllDetailList", filters, pb);
    }

    public Map getOnecarBreakRuleDetail(Long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.car.CarBreakRuleMapper.selectDetailByPrimaryKey",id);
    }

    public void deleteById(Long id) {
        sqlSessionTemplate.delete("com.lte.admin.mapper.car.CarBreakRuleMapper.deleteByPrimaryKey",id);
    }
}

