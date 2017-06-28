package com.lte.admin.car.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.dao.CarOccupyDao;
import com.lte.admin.car.entity.CarOccupy;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author Andy
 */
@Service
@Transactional
public class CarOccupyService extends BaseService<CarOccupy, Integer>{
	@Autowired
	private CarOccupyDao carOccupyDao;
	/**
	 * 新增车辆占用信息
	 * @param carOccupy
	 * @return
	 */
	public String saveCarOccupy(CarOccupy carOccupy) {
		return carOccupyDao.saveCarOccupy(carOccupy);
	}

	/**
	 * 更新车辆占用信息
	 * @param carOccupy
	 * @return
	 */
	public String updateCarOccupy(CarOccupy carOccupy) {
		return carOccupyDao.updateCarOccupy(carOccupy);
	}

	/**
	 * 根据条件查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public PageList<Map> getList(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<Map>) carOccupyDao.getCarOccupyList(pb,filters);
	}

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public CarOccupy getOneCarOccupy(long id){
		return carOccupyDao.getOneCarOccupy(id);
	}

	public void deleteById(Long id) {
		carOccupyDao.deleteById(id);
	}
		
}

