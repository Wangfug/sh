package com.lte.admin.car.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.dao.CarRentPriceDao;
import com.lte.admin.car.entity.CarRentPrice;
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
public class CarRentPriceService extends BaseService<CarRentPrice, Integer>{
	@Autowired
	private CarRentPriceDao carRentPriceDao;
	/**
	 * 新增车辆租赁信息
	 * @param carRentPrice
	 * @return
	 */
	public String saveCarRentPrice(CarRentPrice carRentPrice) {
		return carRentPriceDao.saveCarRentPrice(carRentPrice);
	}

	/**
	 * 更新车辆租赁信息
	 * @param carRentPrice
	 * @return
	 */
	public String updateCarRentPrice(CarRentPrice carRentPrice) {
		return carRentPriceDao.updateCarRentPrice(carRentPrice);
	}

	/**
	 * 根据条件查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public PageList<CarRentPrice> getList(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<CarRentPrice>) carRentPriceDao.getCarRentPriceList(pb,filters);
	}

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public CarRentPrice getOneCarRentPrice(long id){
		return carRentPriceDao.getOneCarRentPrice(id);
	}

    public void deleteById(Long id) {
		carRentPriceDao.deleteById(id);
    }

    public Map getDeatilByModels(Map<String, Object> filters) {
		return  carRentPriceDao.getDeatilByModels(filters);
    }

    public List<Map> getHotCar(String city) {
			return  carRentPriceDao.getHotCar(city);
	}
}

