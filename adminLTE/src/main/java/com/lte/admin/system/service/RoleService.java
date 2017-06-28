package com.lte.admin.system.service;

import java.util.List;
import java.util.Map;

import com.lte.admin.entity.StaffJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.entity.Company;
import com.lte.admin.entity.Dept;
import com.lte.admin.system.dao.RoleDao;

/**
 * 角色service
 * 
 * @author ty
 * @date 2015年1月13日
 */
@Service
@Transactional(readOnly = true)
public class RoleService extends BaseService<StaffJob, Integer> {

	@Autowired
	private RoleDao roleDao;

	public StaffJob getStaffJob(Long roleId) {
		return roleDao.getStaffJob(roleId);
	}

	public PageList<StaffJob> searchall(Page<StaffJob> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);

		return (PageList<StaffJob>) roleDao.getStaffJobListAll(pb, filters);
	}

	public List<Dept> getBmListTree(String id) {
		return roleDao.getBmListTree(id);
	}

	public void save(StaffJob role) {
		role.setDeleteFlag((byte)0);
		roleDao.save(role);

	}

	public void delete(Long id) {
		StaffJob role = new StaffJob();
		role.setId(id);
		role.setDeleteFlag((byte)1);
		roleDao.update(role);

	}

	public void update(StaffJob role) {
		roleDao.update(role);

	}

	public StaffJob get(Long id) {
		return roleDao.getStaffJob(id);
	}

	public PageList<StaffJob> search(Page<StaffJob> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);

		return (PageList<StaffJob>) roleDao.getStaffJobList(pb, filters);
	}

	public PageList<Map> search1(Page<StaffJob> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);

		return (PageList<Map>) roleDao.getStaffJobList1(pb, filters);
	}

	public List<Dept> getDeptListTree() {
		return roleDao.getDeptListTree();
	}

    public List<Map<String,Object>> getStaffJobList() {
		return roleDao.getStaffJobList();
    }

	public List<Map<String,Object>> getStaffJobListBySP(String shopCode) {
		return roleDao.getStaffJobListBySP(shopCode);
	}
}
