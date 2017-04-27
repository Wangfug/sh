package com.lte.admin.system.utils;

import javax.annotation.PostConstruct;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.lte.admin.common.consts.ErrorCodeConst;
import com.lte.admin.common.exception.AdminLteException;

/**
 * 扩展认证默认过滤
 * 
 * @author ty
 * @date 2014年12月2日 下午10:47:09
 */
public class MultipartFileValidator extends FormAuthenticationFilter {

	private final static long MAX_SIZE = 1024 * 1024 * 1024;

	/**
	 * 文件大小上限
	 */
	private long maxSize = MAX_SIZE;

	/**
	 * 可接受的文件content-type
	 */
	private String[] allowedContentTypes;

	@PostConstruct
	public void afterPropertiesSet() {
		Assert.notEmpty(allowedContentTypes, "The content types allowed to be uploaded must contain one at least!");
	}

	/**
	 * 验证上传文件是否合法，如果不合法那么会抛出异常
	 * 
	 * @param file
	 *            用户上传的文件封装类
	 */
	public void validate(MultipartFile file) {
		Assert.notNull(file, "The multipart file is null!");
		if (file.getSize() > maxSize)
			throw new AdminLteException(ErrorCodeConst.SYSTEM_ERROR, "The file uploaded is out of max file size!");
	}

	/**
	 * 设置文件上传大小上限
	 * 
	 * @param maxSize
	 *            文件上传大小上限
	 */
	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * 设置可接受的文件content-type数组
	 * 
	 * @param allowedContentTypes
	 *            可接受的文件content-type数组
	 */
	public void setAllowedContentTypes(String[] allowedContentTypes) {
		this.allowedContentTypes = allowedContentTypes;
	}

}