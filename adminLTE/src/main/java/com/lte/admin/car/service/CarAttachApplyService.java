package com.lte.admin.car.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.dao.CarAttachApplyDao;
import com.lte.admin.car.entity.CarAttachApply;
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
public class CarAttachApplyService extends BaseService<CarAttachApply, Integer> {
	@Autowired
	private CarAttachApplyDao carAttachApplyDao;

	public String saveCarAttachApply(CarAttachApply carAttachApply) {
		return carAttachApplyDao.saveCarAttachApply(carAttachApply);
	}

	public String updateCarAttachApply(CarAttachApply carAttachApply) {
		return carAttachApplyDao.updateCarAttachApply(carAttachApply);
	}

	/**
	 * 根据条件查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public PageList<Map> getList(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<Map>) carAttachApplyDao.getCarAttachApplyList(pb,filters);
	}

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public CarAttachApply getOneCarAttachApply(long id){
		return carAttachApplyDao.getOneCarAttachApply(id);
	}

	public void deleteById(Long id) {
		carAttachApplyDao.deleteById(id);
	}
}

