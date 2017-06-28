package com.lte.admin.car.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.dao.CarAttachApplyDao;
import com.lte.admin.car.dao.CarAttachDao;
import com.lte.admin.car.dao.CarDao;
import com.lte.admin.car.entity.Car;
import com.lte.admin.car.entity.CarAttach;
import com.lte.admin.car.entity.CarAttachApply;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Map;

/**
 * @author Andy
 */
@Service
@Transactional
public class CarService extends BaseService<Car, Integer>{
	@Autowired
	private CarDao carDao;
	@Autowired
	private CarAttachDao carAttachDao;
	@Autowired
	private CarAttachApplyDao carAttachApplyDao;
	/**
	 * 新增车辆信息
	 * @param car
	 * @return
	 */
	public long saveCar(Car car) {
		return carDao.saveCar(car);
	}

	/**
	 * 新增挂靠信息
	 * @param car
	 * @return
	 */
	public boolean saveCar(Car car, CarAttach carAttach, CarAttachApply carAttachApply) {
		boolean result = false;
		try{
			if(car.getId()!=null){
				carDao.updateCar(car);
//				carAttachDao.updateCarAttach(carAttach);
			}else{
				carDao.saveCar(car);
//				carAttachDao.saveCarAttach(carAttach);
			}
			if(carAttach.getId()!=null){
//				carDao.updateCar(car);
				carAttachDao.updateCarAttach(carAttach);
			}else{
//				carDao.saveCar(car);
				carAttachDao.saveCarAttach(carAttach);
			}
			carAttachApplyDao.updateCarAttachApply(carAttachApply);
			result = true;
		}catch(Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result = false;
		}
		return result;

	}



	/**
	 * 更新车辆信息
	 * @param car
	 * @return
	 */
	public String updateCar(Car car) {
		return carDao.updateCar(car);
	}

	public PageList<Car> getList(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<Car>) carDao.getCarList(pb, filters);
	}

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public Car getOneCar(long id){
		return carDao.getOneCar(id);
	}

    public List<Car> getList() {
		return carDao.getList();
    }
	public List<Map> getList1() {
		return carDao.getList1();
	}
	public void deleteById(Long id) {
		carDao.deleteById(id);
	}

    public List<Map> getCarByShop(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return carDao.getCarByShop(pb,filters);
    }
	public Long getCarByFilter(Map<String, Object> filters) {
		return carDao.getCarByFilter(filters);
	}

	public List<String> getCarBrands() {
		return carDao.getCarBrands();
	}

	public Car getCarByCode(String carCode) {
		return carDao.getCarByCode(carCode);
	}

	public List<Car> getCarsByShopId(Map<String, Object> filter) {
		return carDao.getCarsByShopId(filter);
	}
	public List<Car> getCarsByCity(Map<String, Object> filter) {
		return carDao.getCarsByCity(filter);
	}

    public Map<String,Object> getCarByCode1(String carCode) {
		return carDao.getCarByCode1(carCode);
    }

	public PageList<Car> getListByShop1(Page<Car> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList)carDao.getListByShop1(pb,filters);
	}

	public PageList<Car> getListByShop2(Page<Car> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList)carDao.getListByShop1(pb,filters);
	}
	public List<Map> getCarBrandModel() {
		return carDao.getCarBrandModel();
	}
}

