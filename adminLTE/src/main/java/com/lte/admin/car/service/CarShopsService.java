package com.lte.admin.car.service;

import cn.jiguang.common.utils.StringUtils;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.car.dao.CarShopsDao;
import com.lte.admin.car.entity.CarShops;
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
public class CarShopsService extends BaseService<CarShops, Integer>{
	@Autowired
	private CarShopsDao carShopsDao;
	/**
	 * 新增车辆门店信息
	 * @param carShops
	 * @return
	 */
	public String saveCarShops(CarShops carShops) {
		return carShopsDao.saveCarShops(carShops);
	}

	/**
	 * 更新车辆门店信息
	 * @param carShops
	 * @return
	 */
	public String updateCarShops(CarShops carShops) {
		return carShopsDao.updateCarShops(carShops);
	}

	/**
	 * 根据条件查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public PageList<CarShops> getList(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<CarShops>) carShopsDao.getCarShopsList(pb,filters);
	}

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public CarShops getOneCarShops(long id){
		return carShopsDao.getOneCarShops(id);
	}

	/**
	 * 根据code查询
	 * @param code
	 * @return
	 */
	public CarShops getOneCarShops(String code){
		return carShopsDao.getOneCarShops(code);
	}

    public List<CarShops> getList() {
		return  carShopsDao.getList();
    }
	public List<Map> getList1() {
		return  carShopsDao.getList1();
	}

	public PageList<Map> getList2(Page<CarShops> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<Map>) carShopsDao.getList(pb,filters);
	}

    public List<String> getShopCities() {
		return  carShopsDao.getShopCities();
    }

	public List<Map> getCarShops(Map<String, Object> filters) {
		return  carShopsDao.getCarShops(filters);
	}

    public Long getDianzhangByShop(Long carShopGet) {
		String sms = carShopsDao.getDianzhangByShop(carShopGet);
		Long id = null;
		if(StringUtils.isNotEmpty(sms)){
			String[] sms1 = sms.split(",");
			id = Long.valueOf(sms1[0]);
		}
		return  id;
    }

    public List<CarShops> getCarShopListTree() {
		return  carShopsDao.getCarShopListTree();
    }

	public List<CarShops> getCarShopsListTreeByParent(String superCode) {
		return  carShopsDao.getCarShopsListTreeByParent(superCode);
	}
	public List<Map> getNearestShop(Map<String, String> filters) {
		return  carShopsDao.getNearestShop(filters);
	}
	public List<Map> getAllBaseList() {
		return  carShopsDao.getAllBaseList();
	}
}

