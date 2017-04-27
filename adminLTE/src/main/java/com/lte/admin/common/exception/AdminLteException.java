package com.lte.admin.common.exception;

public class AdminLteException extends RuntimeException {

	private static final long serialVersionUID = -496100063981324757L;

	private String code;

	public AdminLteException() {
		super();
	}

	public AdminLteException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public AdminLteException(String code, String message) {
		super(message);
		this.code = code;
	}

	public AdminLteException(String code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
