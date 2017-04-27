package com.lte.admin.system.service;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.google.common.base.Objects;
import com.lte.admin.common.utils.security.Encodes;
import com.lte.admin.entity.Member;
import com.lte.admin.entity.MemberJob;
import com.lte.admin.entity.Permission;
import com.lte.admin.system.utils.UserUtil;
import com.lte.admin.system.utils.UsernamePasswordCaptchaToken;

@Service
@DependsOn({ "memberDao" })
public class MemberRealm extends AuthorizingRealm {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PermissionService permissionService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authcToken;
		Member member = memberService.getMember(token.getUsername());

		if (member != null) {
			byte[] salt = Encodes.decodeHex(member.getSalt());
			ShiroMember shiroMember = new ShiroMember(member.getMemberCode(), member.getMemberName());
			// 设置用户session
			Session session = SecurityUtils.getSubject().getSession();
			session.setAttribute("user", member);
			return new SimpleAuthenticationInfo(shiroMember, member.getPassword(), ByteSource.Util.bytes(salt), getName());
		} else {
			return null;
		}
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroMember shiroMember = (ShiroMember) principals.getPrimaryPrincipal();

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 赋予岗位
		for (MemberJob memberJob : memberService.getMemberJobs(shiroMember.getMemberCode())) {
			info.addRole(memberJob.getJobCode());
		}

		// 赋予权限
		if (UserUtil.getCurrentUser().getMemberName().equals("admin")) {
			for (Permission permission : permissionService.getAll()) {
				if (StringUtils.isNotBlank(permission.getPermCode()))
					info.addStringPermission(permission.getPermCode());
			}
		} else {
			for (Permission permission : permissionService.getPermissionsmenu(shiroMember.getMemberCode(),
					shiroMember.getDeptCode())) {
				if (StringUtils.isNotBlank(permission.getPermCode()))
					info.addStringPermission(permission.getPermCode());
			}
		}
		return info;
	}
	
	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@SuppressWarnings("static-access")
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(memberService.HASH_ALGORITHM);
		matcher.setHashIterations(memberService.HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}
	
	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroMember implements Serializable {
		private static final long serialVersionUID = -3975891750587753599L;
		public String memberCode;
		public String memberName;
		public String deptCode;
		public String jobCode;
		public String companyCode;

		public ShiroMember(String memberCode, String memberName) {
			this.memberCode = memberCode;
			this.memberName = memberName;
		}

		public ShiroMember(String memberCode, String memberName, String deptCode, String jobCode, String companyCode) {
			this.memberCode = memberCode;
			this.memberName = memberName;
			this.deptCode = deptCode;
			this.jobCode = jobCode;
			this.companyCode = companyCode;
		}

		public String getMemberCode() {
			return memberCode;
		}

		public void setMemberCode(String memberCode) {
			this.memberCode = memberCode;
		}

		public String getDeptCode() {
			return deptCode;
		}

		public void setDeptCode(String deptCode) {
			this.deptCode = deptCode;
		}

		public String getJobCode() {
			return jobCode;
		}

		public void setJobCode(String jobCode) {
			this.jobCode = jobCode;
		}

		public void setMemberName(String memberName) {
			this.memberName = memberName;
		}


		public String getMemberName() {
			return memberName;
		}

		public String getCompanyCode() {
			return companyCode;
		}

		public void setCompanyCode(String companyCode) {
			this.companyCode = companyCode;
		}

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return memberCode;
		}

		/**
		 * 重载hashCode,只计算loginName;
		 */
		@Override
		public int hashCode() {
			return Objects.hashCode(memberCode);
		}

		/**
		 * 重载equals,只计算loginName;
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			ShiroMember other = (ShiroMember) obj;
			if (memberCode == null) {
				if (other.memberCode != null) {
					return false;
				}
			} else if (!memberCode.equals(other.memberCode)) {
				return false;
			}
			return true;
		}
	}
	
	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}
}
