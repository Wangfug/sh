package com.lte.admin.car.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.dao.CarMaintenanceDao;
import com.lte.admin.car.entity.CarMaintenance;
import com.lte.admin.car.entity.CarMaintenance;
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
public class CarMaintenanceService extends BaseService<CarMaintenance, Integer>{
	@Autowired
	private CarMaintenanceDao carMaintenanceDao;
	/**
	 * 新增车辆故障处理信息
	 * @param carMaintenance
	 * @return
	 */
	public String saveCarMaintenance(CarMaintenance carMaintenance) {
		return carMaintenanceDao.saveCarMaintenance(carMaintenance);
	}

	/**
	 * 更新车辆故障处理信息
	 * @param carMaintenance
	 * @return
	 */
	public String updateCarMaintenance(CarMaintenance carMaintenance) {
		return carMaintenanceDao.updateCarMaintenance(carMaintenance);
	}

	/**
	 * 根据条件查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public PageList<Map> getList(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<Map>) carMaintenanceDao.getCarMaintenanceList1(pb,filters);
	}

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public CarMaintenance getOneCarMaintenance(long id){
		return carMaintenanceDao.getOneCarMaintenance(id);
	}


    public void deleteById(Long id) {
		carMaintenanceDao.deleteById(id);
    }
}

