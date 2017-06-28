package com.lte.admin.system.service;

import java.util.ArrayList;
import java.util.List;

import com.lte.admin.car.dao.CarShopsDao;
import com.lte.admin.car.entity.CarShops;
import com.lte.admin.common.utils.StringUtils;
import com.lte.admin.custom.dao.TbaseEmployeeDao;
import com.lte.admin.custom.entity.TbaseEmployee;
import com.lte.admin.entity.Member;
import com.lte.admin.entity.Ryxx;
import com.lte.admin.system.dao.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lte.admin.common.service.BaseService;
import com.lte.admin.entity.UserRole;
import com.lte.admin.system.dao.UserRoleDao;

/**
 * 用户角色service
 * 
 * @author ty
 * @date 2015年1月14日
 */
@Service
@Transactional(readOnly = true)
public class UserRoleService extends BaseService<UserRole, Integer> {

	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private CarShopsDao carShopsDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private TbaseEmployeeDao tbaseEmployeeDao;

	/**
	 * 添加修改用户角色
	 * 
	 * @param
	 * @param oldList
	 * @param newList
	 */
	@Transactional(readOnly = false)
	public void updateUserRole(String userId, List<UserRole> oldList, List<String> newList) {
		Member member = memberDao.getMemberByCode(userId);
		List<String> oldidList = new ArrayList<String>();
		for (UserRole rp1 : oldList) {
			oldidList.add(rp1.getJobCode());
		}
		// 是否删除
		for (int i = 0, j = oldidList.size(); i < j; i++) {
			if (!newList.contains(oldidList.get(i))) {
				userRoleDao.deleteUR(userId, oldidList.get(i));
				String oldId = oldidList.get(i);
				if (oldId.startsWith("DZ")) {
					CarShops shop = carShopsDao.getCarShopByJob(oldId);
//				shop
					if(shop!=null){
						String[] manageIds = str2Array(shop.getShopManager());
						String[] manageNames = str2Array(shop.getDianzhang());
						if(manageIds!=null){
							int index = 0;
							TbaseEmployee employee = tbaseEmployeeDao.getOneByCreateBy(member.getMemberCode());
							for(String str1:manageIds){
								String str2 = employee.getId()+"";
								if(str2.equals(str1)){
									manageIds[index] = null;
								}
								index++;
							}
						}
						if(manageNames!=null){
							int index = 0;
							for(String str1:manageNames){
								if(member.getMemberName().equals(str1)){
									manageNames[index] = null;
								}
								index++;
							}
						}
						shop.setShopManager(array2Str(manageIds));
						shop.setDianzhang(array2Str(manageNames));
						carShopsDao.updateCarShops(shop);
					}
				}

			}
		}

		// 是否添加
		for (int m = 0, n = newList.size(); m < n; m++) {
			if (!oldidList.contains(newList.get(m))) {
				userRoleDao.save(getUserRole(userId, newList.get(m)));
				// 是否给予店长
				if (newList.get(m).startsWith("DZ")) {
					CarShops shop = carShopsDao.getCarShopByJob(newList.get(m));
					String oldIds = shop.getShopManager()==null?"":shop.getShopManager();
					String oldNames = shop.getDianzhang()==null?"":shop.getDianzhang();
					TbaseEmployee employee = tbaseEmployeeDao.getOneByCreateBy(member.getMemberCode());
					if("".equals(oldIds)){
						shop.setShopManager(employee.getId()+"");
					}else{
						shop.setShopManager(oldIds+","+employee.getId());
					}
					if("".equals(oldNames)){
						shop.setDianzhang(member.getMemberName());
					}else{
						shop.setDianzhang(oldNames+","+member.getMemberName());
					}

					carShopsDao.updateCarShops(shop);
				}
			}
		}
	}
	private String[] str2Array(String obj){
		if(obj==null)
			obj = "";
		return obj.split(",");
	}
	private String array2Str(String[] objs){
		String result = "";
		for(String str:objs){
			if(str!=null)
			result+=str+",";
		}
		if(StringUtils.isNotBlank(result))
			result = result.substring(0,result.length()-1);
		return result;
	}


	/**
	 * 单一配置人员角色 code
	 */
	@Transactional(readOnly = false)
	public void save(String memberCode, String jobCode) {
		userRoleDao.save(getUserRole(memberCode, jobCode));
	}
	/**
	 * 单一配置人员角色 user
	 */
	@Transactional(readOnly = false)
	public void save(Ryxx user,List<String> newList) {
		for(String addOne:newList){
			userRoleDao.save(getUserRole(user.getPsnname(), addOne));
			if (addOne.startsWith("DZ")) {
				CarShops shop = carShopsDao.getCarShopByJob(addOne);
				String oldIds = shop.getShopManager()==null?"":shop.getShopManager();
				String oldNames = shop.getDianzhang()==null?"":shop.getDianzhang();
				TbaseEmployee employee = tbaseEmployeeDao.getOneByCreateBy(user.getPsnname());
				if("".equals(oldIds)){
					shop.setShopManager(employee.getId()+"");
				}else{
					shop.setShopManager(oldIds+","+employee.getId());
				}
				if("".equals(oldNames)){
					shop.setDianzhang(user.getMemberName());
				}else{
					shop.setDianzhang(oldNames+","+user.getMemberName());
				}
				carShopsDao.updateCarShops(shop);
//				CarShops shop = carShopsDao.getCarShopByJob(addOne);
//				String oldIds = shop.getShopManager()==null?"":shop.getShopManager();
//				String oldNames = shop.getDianzhang()==null?"":shop.getDianzhang();
//				shop.setShopManager(oldIds+","+user.getId());
//				shop.setDianzhang(oldNames+","+user.getMemberName());
//				carShopsDao.updateCarShops(shop);
			}
		}
	}


	/**
	 * 删除多条人员角色
	 */
	@Transactional(readOnly = false)
	public void delete(Ryxx user, List<String> deleteList) {
		for(String deleteOne:deleteList){
			//删除权限
			delete(user.getPsnname(),deleteOne);
			//删除门店中店长信息
				if (deleteOne.startsWith("DZ")) {
					CarShops shop = carShopsDao.getCarShopByJob(deleteOne);
//				shop
					if(shop!=null){
						String[] manageIds = str2Array(shop.getShopManager());
						String[] manageNames = str2Array(shop.getDianzhang());
						if(manageIds!=null){
							int index = 0;
							for(String str1:manageIds){
								String str2 = user.getId()+"";
								if(str2.equals(str1)){
									manageIds[index] = null;
								}
								index++;
							}
						}
						if(manageNames!=null){
							int index = 0;
							for(String str1:manageNames){
								if(user.getMemberName().equals(str1)){
									manageNames[index] = null;
								}
								index++;
							}
						}
						shop.setShopManager(array2Str(manageIds));
						shop.setDianzhang(array2Str(manageNames));
						carShopsDao.updateCarShops(shop);
					}
				}
		}
	}


	/**
	 * 单一删除人员角色
	 */
	@Transactional(readOnly = false)
	public void delete(String memberCode, String jobCode) {
		userRoleDao.deleteUR(memberCode, jobCode);

	}

	public String getDianyuan(String deptCode){
		return userRoleDao.getDianyuan(deptCode);
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
	 * 获取用户拥有角色id集合
	 * 
	 * @param userId
	 * @return 结果集合
	 */
	public List<UserRole> findRoleListByUserId(String userId) {
		return userRoleDao.findRoleListByUserId(userId);
	}

}
