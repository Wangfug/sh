package com.lte.admin.car.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.dao.CarOutOrInDao;
import com.lte.admin.car.entity.CarOutOrIn;
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
public class CarOutOrInService extends BaseService<CarOutOrIn, Integer>{
	@Autowired
	private CarOutOrInDao carOutOrInDao;
	/**
	 * 新增车辆出入库信息
	 * @param carOutOrIn
	 * @return
	 */
	public String saveCarOutOrIn(CarOutOrIn carOutOrIn) {
		return carOutOrInDao.saveCarOutOrIn(carOutOrIn);
	}

	/**
	 * 更新车辆出入库信息
	 * @param carOutOrIn
	 * @return
	 */
	public String updateCarOutOrIn(CarOutOrIn carOutOrIn) {
		return carOutOrInDao.updateCarOutOrIn(carOutOrIn);
	}

	/**
	 * 根据条件查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public PageList<CarOutOrIn> getList(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<CarOutOrIn>) carOutOrInDao.getCarOutOrInList(pb,filters);
	}
	public List<Map> getAllDetail(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return  carOutOrInDao.getAllDetail(pb,filters);
	}

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public CarOutOrIn getOneCarOutOrIn(long id){
		return carOutOrInDao.getOneCarOutOrIn(id);
	}

	public void deleteById(Long id) {
		carOutOrInDao.deleteById(id);
	}
		
}

