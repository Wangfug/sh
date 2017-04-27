package com.lte.admin.common.consts;

public class ErrorCodeConst {

	// 处理成功
	public static final String RETURN_SUCCESS = "00000";
	// 系统异常
	public static final String SYSTEM_ERROR = "99999";
	// *******************************************************
	// 通用返回码
	// *******************************************************
	// 入参不合法
	public static final String PARAMETER_ERROR = "00001";
	// 登陆超时
	public static final String LOGIN_TIMEOUT = "00002";
	// 文件不存在
	public static final String FILE_NOT_EXISTS = "00003";
	// 文件上传失败
	public static final String FILE_UPLOAD_FAIL = "00004";

	// *******************************************************
	// 管理功能返回码
	// *******************************************************
	// 编码重复
	public static final String SYSTEM_CODE_REPEAT = "10001";

	// *******************************************************
	// 工作流返回码
	// *******************************************************
	// 用户工作流角色错误
	public static final String WORKFLOW_USER_ERROR = "20001";

}
