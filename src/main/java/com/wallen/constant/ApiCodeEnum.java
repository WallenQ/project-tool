package com.wallen.constant;

/**
 * 接口返回结果-枚举类
 *
 * @author Wallen
 * 2019/10/14 9:47
 */
public enum ApiCodeEnum {

	/**
	 * 200成功，300警告，500失败
	 */
	DEFAULT_SUCCESS(200, "操作成功"),
	DEFAULT_WARNING(300, "警告！"),
	DEFAULT_ERROR(500, "操作失败");

	private int code;

	private String message;

	ApiCodeEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
