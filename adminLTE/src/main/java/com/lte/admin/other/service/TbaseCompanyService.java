package com.lte.admin.other.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.entity.Company;
import com.lte.admin.other.dao.TbaseCompanyDao;
import com.lte.admin.other.entity.TbaseCompany;
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
public class TbaseCompanyService extends BaseService<TbaseCompany, Integer>{
	@Autowired
	private TbaseCompanyDao tbaseCompanyDao;

	public PageList<TbaseCompany> getList(Page<TbaseCompany> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<TbaseCompany>) tbaseCompanyDao.getList(pb, filters);
	}

	public void save(TbaseCompany tbaseCompany) {
		tbaseCompanyDao.save(tbaseCompany);
	}

	public void update(TbaseCompany tbaseCompany) {
		tbaseCompanyDao.update(tbaseCompany);
	}

	public void deleteById(Long id) {
		tbaseCompanyDao.deleteById(id);
	}

	public TbaseCompany get(long id) {
		return tbaseCompanyDao.get(id);
	}

    public PageList<Map> getList1(Page<TbaseCompany> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<Map>) tbaseCompanyDao.getList1(pb, filters);
    }

	public List<Map> getList() {
		return  tbaseCompanyDao.getList();
	}

	/**
	 * 根据父级找子公司
	 */
	public List<TbaseCompany> getCompanyListTreeByParent(String parentCode) {
		return tbaseCompanyDao.getCompanyListTreeByParent(parentCode);
	}

	public List<Company> getCompanyListTree() {
		return tbaseCompanyDao.getCompanyListTree();
	}
}

