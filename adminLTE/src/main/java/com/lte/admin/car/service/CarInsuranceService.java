package com.lte.admin.car.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.dao.CarInsuranceDao;
import com.lte.admin.car.entity.CarInsurance;
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
public class CarInsuranceService extends BaseService<CarInsurance, Integer>{
	@Autowired
	private CarInsuranceDao carInsuranceDao;
	/**
	 * 新增车辆保险信息
	 * @param carInsurance
	 * @return
	 */
	public String saveCarInsurance(CarInsurance carInsurance) {
		return carInsuranceDao.saveCarInsurance(carInsurance);
	}

	/**
	 * 更新车辆保险信息
	 * @param carInsurance
	 * @return
	 */
	public String updateCarInsurance(CarInsurance carInsurance) {
		return carInsuranceDao.updateCarInsurance(carInsurance);
	}

	/**
	 * 根据条件查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public PageList<CarInsurance> getList(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<CarInsurance>) carInsuranceDao.getCarInsuranceList(pb,filters);
	}
	public List<Map> getAllDetail(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return carInsuranceDao.getAllDetail(pb,filters);
	}
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public CarInsurance getOneCarInsurance(long id){
		return carInsuranceDao.getOneCarInsurance(id);
	}

	public Map getOneCarInsuranceDetail(Long id) {
		return carInsuranceDao.getOneCarInsuranceDetail(id);
	}

	public void deleteById(Long id) {
		carInsuranceDao.deleteById(id);
	}
}

