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
import com.lte.admin.entity.Gwxx;
import com.lte.admin.entity.Permission;
import com.lte.admin.entity.Ryxx;
import com.lte.admin.entity.UserRole;
import com.lte.admin.system.utils.UserUtil;
import com.lte.admin.system.utils.UsernamePasswordCaptchaToken;

/**
 * 用户登录授权service(shrioRealm)
 * 
 * @author ty
 * @date 2015年1月14日
 */
@Service
@DependsOn({ "ryxxDao", "permissionDao", "rolePermissionDao" })
public class RyxxRealm extends AuthorizingRealm {

	@Autowired
	private RyxxService ryxxService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PermissionService permissionService;

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authcToken;
		Ryxx ryxx = ryxxService.getRyxx(token.getUsername());

		if (ryxx != null && doCaptchaValidate(token)) {
			byte[] salt = Encodes.decodeHex(ryxx.getSalt());
			ShiroRyxx shiroRyxx = new ShiroRyxx(ryxx.getPkPsnbasdoc(), ryxx.getPsncode(), ryxx.getPsnname());
			// 设置用户session
			Session session = SecurityUtils.getSubject().getSession();
			session.setAttribute("user", ryxx);
			return new SimpleAuthenticationInfo(shiroRyxx, ryxx.getPassword(), ByteSource.Util.bytes(salt), getName());
		} else {
			return null;
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroRyxx shiroRyxx = (ShiroRyxx) principals.getPrimaryPrincipal();
		// Ryxx ryxx = ryxxService.getRyxx(shiroRyxx.loginName);

		// 把principals放session中 key=ryxxId value=principals
		// SecurityUtils.getSubject().getSession().setAttribute(ryxx.getPkPsnbasdoc(),SecurityUtils.getSubject().getPrincipals());

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 赋予岗位
		for (Gwxx ryxxRole : ryxxService.getRyxxRoles(shiroRyxx.getId())) {
			info.addRole(ryxxRole.getJobcode());
		}
		// 赋予角色
//		for (UserRole userRole : userRoleService.findRoleListByUserId(shiroRyxx.getId())) {
//			info.addRole(roleService.get(userRole.getRoleId()).getRoleCode());
//		}
//		// 赋予权限
//		if (UserUtil.getCurrentUser().getName().equals("admin")) {
//			for (Permission permission : permissionService.getAll()) {
//				if (StringUtils.isNotBlank(permission.getPermCode()))
//					info.addStringPermission(permission.getPermCode());
//			}
//		} else {
//			for (Permission permission : permissionService.getPermissionsmenu(shiroRyxx.getId(),
//					shiroRyxx.getBm().substring(0, 4))) {
//				// for(Permission
//				// permission:permissionService.getPermissions(shiroRyxx.getId())){
//				if (StringUtils.isNotBlank(permission.getPermCode()))
//					info.addStringPermission(permission.getPermCode());
//			}
//		}
		// 设置登录次数、时间
		// ryxxService.updateRyxxLogin(ryxx);
		return info;
	}

	/**
	 * 验证码校验
	 * 
	 * @param token
	 * @return boolean
	 */
	protected boolean doCaptchaValidate(UsernamePasswordCaptchaToken token) {
		// String captcha = (String)
		// SecurityUtils.getSubject().getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		// if (captcha != null &&!captcha.equalsIgnoreCase(token.getCaptcha())){
		// throw new CaptchaException("验证码错误！");
		// }
		return true;
	}

	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@SuppressWarnings("static-access")
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(ryxxService.HASH_ALGORITHM);
		matcher.setHashIterations(ryxxService.HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}

	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroRyxx implements Serializable {
		private static final long serialVersionUID = -1373760761780840081L;
		public String id;
		public String loginName;
		public String name;
		public String bm;
		// public String bmName;
		public String gw;
		// public String gwName;
		public String gs;

		public ShiroRyxx(String id, String loginName, String name) {
			this.id = id;
			this.loginName = loginName;
			this.name = name;
		}

		public ShiroRyxx(String id, String loginName, String name, String bm, String bmName, String gw, String gwName,
				String gs) {
			this.id = id;
			this.loginName = loginName;
			this.name = name;
			this.bm = bm;
			// this.bmName = bmName;
			this.gw = gw;
			// this.gwName = gwName;
			this.gs = gs;
		}

		public String getLoginName() {
			return loginName;
		}

		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}

		public String getBm() {
			return bm;
		}

		public void setBm(String bm) {
			this.bm = bm;
		}

		// public String getBmName() {
		// return bmName;
		// }
		//
		// public void setBmName(String bmName) {
		// this.bmName = bmName;
		// }

		public String getGw() {
			return gw;
		}

		public void setGw(String gw) {
			this.gw = gw;
		}

		// public String getGwName() {
		// return gwName;
		// }
		//
		// public void setGwName(String gwName) {
		// this.gwName = gwName;
		// }

		public String getGs() {
			return gs;
		}

		public void setGs(String gs) {
			this.gs = gs;
		}

		public void setId(String id) {
			this.id = id;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return loginName;
		}

		/**
		 * 重载hashCode,只计算loginName;
		 */
		@Override
		public int hashCode() {
			return Objects.hashCode(loginName);
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
			ShiroRyxx other = (ShiroRyxx) obj;
			if (loginName == null) {
				if (other.loginName != null) {
					return false;
				}
			} else if (!loginName.equals(other.loginName)) {
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
