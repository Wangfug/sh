package com.lte.admin.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.common.utils.security.Digests;
import com.lte.admin.common.utils.security.Encodes;
import com.lte.admin.entity.Member;
import com.lte.admin.entity.MemberJob;
import com.lte.admin.entity.MemberLogin;
import com.lte.admin.entity.Ryxx;
import com.lte.admin.system.dao.MemberDao;

@Service
@Transactional(readOnly = true)
public class MemberService extends BaseService<MemberLogin, Integer> {

	/** 加密方法 */
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8; // 盐长度
	
	@Autowired
	private MemberDao memberDao;
	
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(Member member) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		member.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(member.getPassword().getBytes(), salt, HASH_INTERATIONS);
		member.setPassword(Encodes.encodeHex(hashPassword));
	}
	
	/**
	 * 验证原密码是否正确
	 * 
	 * @param member
	 * @param oldPwd
	 * @return
	 */
	public boolean checkPassword(Member member, String oldPassword) {
		byte[] salt = Encodes.decodeHex(member.getSalt());
		byte[] hashPassword = Digests.sha1(oldPassword.getBytes(), salt, HASH_INTERATIONS);
		if (member.getPassword().equals(Encodes.encodeHex(hashPassword))) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * 按登录名查询用户
	 * 
	 * @param loginName
	 * @return 用户对象
	 */
	public Member getMember(String memberCode) {
		try {
			return memberDao.findByMemberCode(memberCode);
		} catch (Exception ex) {
			return null;
		}
	}
	
	public List<MemberJob> getMemberJobs(String id) {
		return memberDao.getMemberJobs(id);
	}
	
	public List<MemberLogin> getRygwList(String memberCode) {
		if (memberCode.equals("admin")) {
			List<MemberLogin> list = new ArrayList<MemberLogin>();
			MemberLogin admin = new MemberLogin();
			admin.setMemberCode("admin");
			admin.setMemberName("admin");
			admin.setDeptCode("CHN001");
			list.add(admin);
			return list;
		}
		return memberDao.getRygwList(memberCode);
	}
	
	public MemberLogin getMemberByMemberCodeAndJobCode(String memberCode, String jobCode) {
		
		Map<String, String> map = new HashMap<String,String>();
		map.put("memberCode",memberCode );
		map.put("jobCode", jobCode);
		return memberDao.findByByMemberCodeAndJobCode(map);
	}
	
	public PageList<MemberLogin> getMemberList(Page<MemberLogin> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);

		return (PageList<MemberLogin>) memberDao.getMemberList(pb, filters);
	}
}
