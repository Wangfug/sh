package com.lte.admin.system.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.entity.Dept;
import com.lte.admin.system.dao.DeptDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Andy
 */
@Service
@Transactional
public class DeptService extends BaseService<Dept, Integer> {
	@Autowired
	private DeptDao deptDao;
	/**
	 * 新增用户信息
	 * @param dept
	 * @return
	 */
	public String saveDept(Dept dept) {
		return deptDao.saveDept(dept);
	}

	/**
	 * 更新用户信息
	 * @param dept
	 * @return
	 */
	public String updateDept(Dept dept) {
		return deptDao.updateDept(dept);
	}

	/**
	 * 根据条件查询
	 * @param page
	 * @param filters
	 * @return
	 */
	public PageList<Dept> getDeptList(Page page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<Dept>) deptDao.getDeptList(pb,filters);
	}
	/**
	 * 查询所有Dept
	 * @param
	 * @param
	 * @return
	 */
	public List<Dept> getDeptList() {
		return (List<Dept>) deptDao.getDeptList();
	}

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public Dept getOneDept(long id){
		return deptDao.getOneDept(id);
	}

	/**
	 * 根据code查询
	 * @param code
	 * @return
	 */
	public Dept getOneDept(String code){
		return deptDao.getOneDept(code);
	}

	/**
	 * 根据部门编号和公司编号查询部门
	 * @param
	 * @return
	 */
	public Dept getOneDept(String deptCode,String shopCode){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("deptCode",deptCode);
		map.put("shopCode",shopCode);
		return deptDao.getOneDept(map);
	}

	public List<Dept> getDeptListByCompany(String comCode) {
		return deptDao.getDeptListByCompany(comCode);
	}

	public List<Dept> getBmListTreeByParent(String parentCode) {
		return deptDao.getBmListTreeByParent(parentCode);
	}
}

