package com.lte.admin.other.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.other.dao.PickupPriceDao;
import com.lte.admin.other.entity.PickupPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author Andy
 */
@Service
@Transactional
public class PickupPriceService extends BaseService<PickupPrice, Integer>{
	@Autowired
	private PickupPriceDao pickupPriceDao;

	public PageList<PickupPrice> getList(Page<PickupPrice> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<PickupPrice>) pickupPriceDao.getList(pb, filters);
	}

	public void save(PickupPrice pickupPrice) {
		pickupPriceDao.save(pickupPrice);
	}

	public void update(PickupPrice pickupPrice) {
		pickupPriceDao.update(pickupPrice);
	}

	public void deleteById(Long id) {
		pickupPriceDao.deleteById(id);
	}

	public PickupPrice get(long id) {
		return pickupPriceDao.get(id);
	}
		
}

