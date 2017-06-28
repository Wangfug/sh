package com.lte.admin.car.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.dao.CarAttachDao;
import com.lte.admin.car.entity.CarAttach;
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
public class CarAttachService extends BaseService<CarAttach, Integer>{
	@Autowired
	private CarAttachDao carAttachDao;
	/**
	 * 新增车辆挂靠信息
	 * @param carAttach
	 * @return
	 */
	public String saveCarAttach(CarAttach carAttach) {
		return carAttachDao.saveCarAttach(carAttach);
	}

	/**
	 * 更新车辆挂靠信息
	 * @param carAttach
	 * @return
	 */
	public String updateCarAttach(CarAttach carAttach) {
		return carAttachDao.updateCarAttach(carAttach);
	}

	/**
	 * 根据条件查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public PageList<CarAttach> getList(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<CarAttach>) carAttachDao.getCarAttachList(pb,filters);
	}

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public CarAttach getOneCarAttach(long id){
		return carAttachDao.getOneCarAttach(id);
	}

    public void deleteById(Long id) {
		carAttachDao.deleteById(id);
    }

    public CarAttach getOneCarAttachByCar(String carCode) {
		return carAttachDao.getOneCarAttachByCar(carCode);
    }
}

