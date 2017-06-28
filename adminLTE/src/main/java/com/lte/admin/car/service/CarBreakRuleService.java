package com.lte.admin.car.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.dao.CarBreakRuleDao;
import com.lte.admin.car.entity.CarBreakRule;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Andy
 */
@Service
@Transactional
public class CarBreakRuleService extends BaseService<CarBreakRule, Integer>{
	@Autowired
	private CarBreakRuleDao carBreakRuleDao;
	/**
	 * 新增车辆违章信息
	 * @param carBreakRule
	 * @return
	 */
	public String saveCarBreakRule(CarBreakRule carBreakRule) {
		return carBreakRuleDao.saveCarBreakRule(carBreakRule);
	}

	/**
	 * 更新车辆违章信息
	 * @param carBreakRule
	 * @return
	 */
	public String updateCarBreakRule(CarBreakRule carBreakRule) {
		return carBreakRuleDao.updateCarBreakRule(carBreakRule);
	}

	/**
	 * 根据条件查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public PageList<CarBreakRule> getList(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<CarBreakRule>) carBreakRuleDao.getCarBreakRuleList(pb,filters);
	}
	/**
	 * 根据条件查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public PageList<Map<String,Object>> getList1(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<Map<String,Object>>) carBreakRuleDao.getCarBreakRuleList1(pb,filters);
	}

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public CarBreakRule getOnecarBreakRule(long id){
		return carBreakRuleDao.getOnecarBreakRule(id);
	}
	public CarBreakRule getOnecarBreakRule(String weizhangNO){
		return carBreakRuleDao.getOnecarBreakRule(weizhangNO);
	}

    public PageList<Map> getDetailList(Page<CarBreakRule> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return  (PageList<Map>)carBreakRuleDao.getDetailList(pb,filters);
    }

    public Map getOnecarBreakRuleDetail(Long id) {
		return carBreakRuleDao.getOnecarBreakRuleDetail(id);
    }

	public void deleteById(Long id) {
		carBreakRuleDao.deleteById(id);
	}
}

