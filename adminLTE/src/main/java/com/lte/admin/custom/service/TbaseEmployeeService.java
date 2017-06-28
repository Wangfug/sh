package com.lte.admin.custom.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.custom.entity.TbaseEmployee;

import com.lte.admin.entity.DictType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.custom.dao.TbaseEmployeeDao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Andy
 */
@Service
@Transactional
public class TbaseEmployeeService extends BaseService<TbaseEmployee, Integer>{
	@Autowired
	private TbaseEmployeeDao tbaseEmployeeDao;
	/**
	 * 新增员工信息
	 * @param tbaseEmployee
	 * @return
	 */
	public String saveTbaseEmployee(TbaseEmployee tbaseEmployee) {
		return tbaseEmployeeDao.saveTbaseEmployee(tbaseEmployee);
	}

	/**
	 * 更新员工信息
	 * @param tbaseEmployee
	 * @return
	 */
	public String updateTbaseEmployee(TbaseEmployee tbaseEmployee) {
		return tbaseEmployeeDao.updateTbaseEmployee(tbaseEmployee);
	}

	/**
	 * 根据条件查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public PageList<Map> getList(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<Map>) tbaseEmployeeDao.getTbaseEmployeeList(pb,filters);
	}
	/**
	 * 根据条件查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public PageList<Map> getEmpListByDZ(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<Map>) tbaseEmployeeDao.getEmpListByDZ(pb,filters);
	}

	/**
	 * 根据条件查询
	 * @param filters
	 * @return
	 */
	public TbaseEmployee getListByToken(Map<String, Object> filters) {
		return tbaseEmployeeDao.getListByToken(filters);
	}

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public TbaseEmployee getOneTbaseEmployee(long id){
		return tbaseEmployeeDao.getOneTbaseEmployee(id);
	}

	public List<TbaseEmployee> getList() {
		return  tbaseEmployeeDao.getList();
	}

   /* public List<Map> getManager() {
		return tbaseEmployeeDao.getManager();
    }*/

	public TbaseEmployee getOneByCreateBy(String code) {
		return tbaseEmployeeDao.getOneByCreateBy(code);
	}

    public Map<String,Object> getEmployeeByToken(String token) {
		return tbaseEmployeeDao.getEmployeeByToken(token);
    }
	public TbaseEmployee getEmployeeByToken1(String token) {
		return tbaseEmployeeDao.getEmployeeByToken1(token);
	}

    public List<Map<String,Object>> getEmpByShop(String shopCode) {
		return tbaseEmployeeDao.getEmpByShop(shopCode);
    }

    public Map<String,Object> getEmployeeInfo(long id) {
		return tbaseEmployeeDao.getEmployeeInfo(id);
    }

    public Integer getEno() {
		return tbaseEmployeeDao.getEno();
    }
}

