package com.lte.admin.car.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.dao.CarFaultHandleDao;
import com.lte.admin.car.entity.CarFaultHandle;
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
public class CarFaultHandleService extends BaseService<CarFaultHandle, Integer>{
	@Autowired
	private CarFaultHandleDao carFaultHandleDao;
	/**
	 * 新增车辆故障处理信息
	 * @param carFaultHandle
	 * @return
	 */
	public String saveCarFaultHandle(CarFaultHandle carFaultHandle) {
		return carFaultHandleDao.saveCarFaultHandle(carFaultHandle);
	}

	/**
	 * 更新车辆故障处理信息
	 * @param carFaultHandle
	 * @return
	 */
	public String updateCarFaultHandle(CarFaultHandle carFaultHandle) {
		return carFaultHandleDao.updateCarFaultHandle(carFaultHandle);
	}

	public List<Map> getAllDetail(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return carFaultHandleDao.getAllDetail(pb,filters);
	}

	/**
	 * 根据条件查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public PageList<CarFaultHandle> getList(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<CarFaultHandle>) carFaultHandleDao.getCarFaultHandleList(pb,filters);
	}

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public CarFaultHandle getOneCarFaultHandle(long id){
		return carFaultHandleDao.getOneCarFaultHandle(id);
	}

	public Map getOneCarFaultHandleDetail(Long id) {
		return carFaultHandleDao.getOneCarFaultHandleDetail(id);
	}

	public void deleteById(Long id) {
		carFaultHandleDao.deleteById(id);
	}
}

