package com.lte.admin.car.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lte.admin.car.entity.CarInsurance;
import com.lte.admin.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author AndyChan
 * @version 1.0
 */
@Repository
public class CarInsuranceDao  extends BaseDao {
    public String saveCarInsurance(CarInsurance carInsurance){
        sqlSessionTemplate.insert("com.lte.admin.mapper.car.CarInsuranceMapper.insertSelective",carInsurance);
        return "success";
    }

    public String updateCarInsurance(CarInsurance carInsurance){
        sqlSessionTemplate.update("com.lte.admin.mapper.car.CarInsuranceMapper.updateByPrimaryKeySelective",carInsurance);
        return "success";
    }

    /**
     * 根据条件查询
     * @param pb
     * @param filters
     * @return
     */
    public List<CarInsurance> getCarInsuranceList(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarInsuranceMapper.getAllList", filters, pb);
    }

    public List<Map> getAllDetail(PageBounds pb, Map<String, Object> filters) {
        return sqlSessionTemplate.selectList("com.lte.admin.mapper.car.CarInsuranceMapper.getAllDetail", filters, pb);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public CarInsurance getOneCarInsurance(long id){
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.car.CarInsuranceMapper.selectByPrimaryKey",id);
    }

    public Map getOneCarInsuranceDetail(Long id) {
        return sqlSessionTemplate.selectOne("com.lte.admin.mapper.car.CarInsuranceMapper.selectDetailByPrimaryKey",id);
    }

    public void deleteById(Long id) {
        sqlSessionTemplate.delete("com.lte.admin.mapper.car.CarInsuranceMapper.deleteByPrimaryKey",id);
    }
}

