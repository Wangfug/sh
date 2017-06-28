package com.lte.admin.system.service;

import java.util.List;
import java.util.Map;

import com.lte.admin.car.dao.CarShopsDao;
import com.lte.admin.car.entity.CarShops;
import com.lte.admin.custom.dao.TbaseEmployeeDao;
import com.lte.admin.custom.entity.TbaseEmployee;
import com.lte.admin.entity.*;
import com.lte.admin.system.dao.UserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.common.utils.security.Digests;
import com.lte.admin.common.utils.security.Encodes;
import com.lte.admin.system.dao.RyxxDao;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 用户service
 * 
 * @author ty
 * @date 2015年1月13日
 */
@Service
@Transactional(readOnly = false)
public class RyxxService extends BaseService<Ryxx, Integer> {

	/** 加密方法 */
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8; // 盐长度

	@Autowired
	private RyxxDao ryxxDao;
	@Autowired
	private TbaseEmployeeDao tbaseEmployeeDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private CarShopsDao carShopsDao;

	/**
	 * 保存用户
	 * 
	 * @param
	 */
	@Transactional(readOnly = false)
	public void save(Ryxx ryxx) {
		entryptPassword(ryxx);
		// ryxx.setCreateDate(DateUtils.getSysTimestamp());
		 ryxxDao.save(ryxx);
	}

	/**
	 * 保存员工
	 */

	@Transactional(readOnly = false)
	public boolean save(Ryxx ryxx,TbaseEmployee tbaseEmployee) {
		entryptPassword(ryxx);
		// ryxx.setCreateDate(DateUtils.getSysTimestamp());
		boolean result = false;
		try{
			ryxxDao.save(ryxx);
			tbaseEmployeeDao.saveTbaseEmployee(tbaseEmployee);
			result = true;
		}catch(Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result = false;
		}
		return result;
	}
	/**
	 * 构建UserRole
	 *
	 * @param memberCode
	 * @param jobCode
	 * @return UserRole
	 */
	private UserRole getUserRole(String memberCode, String jobCode) {
		UserRole ur = new UserRole();
		ur.setMemberCode(memberCode);
		ur.setJobCode(jobCode);
		return ur;
	}


	/**
	 * 修改密码
	 * 
	 * @param ryxx
	 */
	@Transactional(readOnly = false)
	public void updatePwd(Ryxx ryxx) {
		entryptPassword(ryxx);
		ryxxDao.updatePwd(ryxx);
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false)
	public boolean delete(Long id,Long empId) {
		boolean res = false;
		try{
			if (!isSupervisor(id)){
				ryxxDao.delete(id);
				tbaseEmployeeDao.delete(empId);
				res = true;
			}
		}catch(Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			res = false;
		}
		return res;
	}

	/**
	 * 按登录名查询用户
	 * 
	 * @param loginName
	 * @return 用户对象
	 */
	public Ryxx getRyxx(String loginName) {
		return ryxxDao.findByloginName(loginName);
	}

	/**
	 * 判断是否超级管理员
	 * 
	 * @param id
	 * @return boolean
	 */
	private boolean isSupervisor(Long id) {
		return id == 1;
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(Ryxx ryxx) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		ryxx.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(ryxx.getPassword().getBytes(), salt, HASH_INTERATIONS);
		ryxx.setPassword(Encodes.encodeHex(hashPassword));
	}

	/**
	 * 验证原密码是否正确
	 * 
	 * @param ryxx
	 * @param
	 * @return
	 */
	public boolean checkPassword(Ryxx ryxx, String oldPassword) {
		byte[] salt = Encodes.decodeHex(ryxx.getSalt());
		byte[] hashPassword = Digests.sha1(oldPassword.getBytes(), salt, HASH_INTERATIONS);
		if (ryxx.getPassword().equals(Encodes.encodeHex(hashPassword))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 修改用户登录
	 * 
	 * @param
	 */
	// public void updateRyxxLogin(Ryxx ryxx){
	// ryxx.setLoginCount((ryxx.getLoginCount()==null?0:ryxx.getLoginCount())+1);
	// ryxx.setPreviousVisit(ryxx.getLastVisit());
	// ryxx.setLastVisit(DateUtils.getSysTimestamp());
	// ryxxDao.update(ryxx);
	// }

	public List<Gwxx> getRyxxRoles(String id) {
		return ryxxDao.getRyxxRoles(id);
	}

	public PageList<Ryxx> getRyxxList(Page<Ryxx> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);

		return (PageList<Ryxx>) ryxxDao.getRyxxList(pb, filters);
	}

	public void update(Ryxx ryxx) {
		ryxxDao.update(ryxx);

	}
	public boolean update(Ryxx ryxx,TbaseEmployee tbaseEmployee) {
		boolean result = false;
		try{
			ryxxDao.update(ryxx);
			tbaseEmployeeDao.updateTbaseEmployee(tbaseEmployee);
			result = true;
		}catch(Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result = false;
		}
		return result;


	}

	public List<RyxxLogin> getRygwList(String id, String loginName) {
		return ryxxDao.getRygwList(id, loginName);

	}

	public Ryxx getRyxxByKey(RyxxKey key) {
		return ryxxDao.getRyxxByKey(key);

	}

	public List<UserRole> getRoleByUserId(String userId) {
		return ryxxDao.getRoleByUserId(userId);
	}

	public Ryxx getRyxxById(Long assignee) {
		return ryxxDao.getRyxxById(assignee);
	}

	// public PageList<Ryxx> getRyxxList(int pageIndex, int
	// pageSize,List<PropertyFilter> filters)
	// throws Exception {
	//
	// }

	// public PageList<Ryxx> getRyxxList(Page<Ryxx> page, List<PropertyFilter>
	// filters) {
	// // TODO Auto-generated method stub
	// return (PageList<Ryxx>) getPageList(RyxxMapper.class, "getRyxxList",
	// filters, pageIndex, pageSize);
	// }
}
