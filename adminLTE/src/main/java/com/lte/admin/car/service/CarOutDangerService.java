package com.lte.admin.car.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.dao.CarOutDangerDao;
import com.lte.admin.car.entity.CarOutDanger;
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
public class CarOutDangerService extends BaseService<CarOutDanger, Integer>{
	@Autowired
	private CarOutDangerDao carOutDangerDao;
	/**
	 * 新增车辆出险信息
	 * @param carOutDanger
	 * @return
	 */
	public String saveCarOutDanger(CarOutDanger carOutDanger) {
		return carOutDangerDao.saveCarOutDanger(carOutDanger);
	}

	/**
	 * 更新车辆出险信息
	 * @param carOutDanger
	 * @return
	 */
	public String updateCarOutDanger(CarOutDanger carOutDanger) {
		return carOutDangerDao.updateCarOutDanger(carOutDanger);
	}

	/**
	 * 根据条件查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public PageList<Map> getList(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<Map>) carOutDangerDao.getCarOutDangerList1(pb,filters);
	}

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public CarOutDanger getOneCarOutDanger(long id){
		return carOutDangerDao.getOneCarOutDanger(id);
	}

	public void deleteById(Long id) {
		carOutDangerDao.deleteById(id);
	}
}

