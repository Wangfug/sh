package com.lte.admin.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.entity.StaffJob;
import com.lte.admin.system.dao.MemberJobDao;

@Service
@Transactional
public class MemberJobService extends BaseService<StaffJob, Integer> {

	@Autowired
	private MemberJobDao memberJobDao;

	public PageList<StaffJob> search(Page<StaffJob> page, String id) {
		PageBounds pb= createPageBounds(page);
		try {
//			List<StaffJob> list = memberJobDao.getJobList(pb,id);
//			PageList<StaffJob> pl = new PageList<StaffJob>(list);
//			return pl;
			return (PageList<StaffJob>) memberJobDao.getJobList(pb,id);
		} catch (Exception ex) {
			return null;
		}
		
	}
}
