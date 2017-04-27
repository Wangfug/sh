package com.lte.admin.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.entity.Bm;
import com.lte.admin.entity.Gwxx;
import com.lte.admin.entity.Role;
import com.lte.admin.system.dao.GwxxDao;

/**
 * 角色service
 * 
 * @author ty
 * @date 2015年1月13日
 */
@Service
@Transactional(readOnly = true)
public class GwxxService extends BaseService<Gwxx, Integer> {

	@Autowired
	private GwxxDao roleDao;

	public Role getRole(Long roleId) {
		return roleDao.getRole(roleId);
	}

	public PageList<Gwxx> search(Page<Gwxx> page, String id, String gsbz) {
		PageBounds pb = createPageBounds(page);

		return (PageList<Gwxx>) roleDao.getRoleList(pb, id, gsbz);
	}

	public List<Bm> getBmListTree(String id) {
		return roleDao.getBmListTree(id);
	}

}
