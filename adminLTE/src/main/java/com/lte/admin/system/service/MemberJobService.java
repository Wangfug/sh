package com.lte.admin.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lte.admin.entity.Dept;
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

	public PageList<StaffJob> search(Page<StaffJob> page,Map<String,Object> filters) {
		PageBounds pb= createPageBounds(page);
		try {
//			PageList<Object> list = (PageList<Object>)memberJobDao.getJobList1(pb,id);
//			int i=0;
//			for(Object obj:list){
//				StaffJob job = new StaffJob();
//				if(obj instanceof StaffJob)
//					list.set(i,(StaffJob)obj);
//i++;
//			}
//			PageList<Object> pl = new PageList<Object>();
//			return list;
			return (PageList<StaffJob>) memberJobDao.getJobList1(pb,filters);
		} catch (Exception ex) {
			return null;
		}
		
	}
	public PageList<StaffJob> search(Page<StaffJob> page) {
		PageBounds pb= createPageBounds(page);
		try {
			return (PageList<StaffJob>) memberJobDao.getJobList1(pb);
		} catch (Exception ex) {
			return null;
		}

	}

	public StaffJob getOneStaffJob(long id) {
		return memberJobDao.getOneStaffJob(id);
	}

	public String updateStaffJob(StaffJob staffJob) {
		return memberJobDao.updateStaffJob(staffJob);
	}

	public String saveStaffJob(StaffJob staffJob) {
		return memberJobDao.saveStaffJob(staffJob);
	}

	public Map getOneStaffJob1(long id) {
		return memberJobDao.getOneStaffJob1(id);
	}

    public StaffJob getOneStaffJobByCode(String gwxx) {
		return memberJobDao.getOneStaffJobByCode(gwxx);
    }
}
