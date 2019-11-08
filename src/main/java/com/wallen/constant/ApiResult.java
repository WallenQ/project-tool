package com.wallen.constant;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * ApiResult
 *
 * @author Wallen
 * 2019/10/14 13:53
 */
public class ApiResult<T> {
	private int code;
	private String message;
	private T data;

	public ApiResult() {
	}

	public ApiResult(int code, String msg) {
		this(code, msg, null);
	}

	public ApiResult(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public ApiResult(ApiCodeEnum apiCodeEnum, T data) {
		this.code = apiCodeEnum.getCode();
		this.message = apiCodeEnum.getMessage();
		this.data = data;
	}

	public ApiResult(Errors errors) {
		StringBuilder message = new StringBuilder();
		errors.getFieldErrors().forEach((ObjectError error) -> {
			message.append(error.getDefaultMessage() + "\n");
		});
		this.code = 1;
		this.message = message.toString();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/**
	 * 默认成功
	 *
	 * @return
	 */
	public static ApiResult success() {
		ApiResult apiResult = new ApiResult();
		apiResult.setCode(ApiCodeEnum.DEFAULT_SUCCESS.getCode());
		apiResult.setMessage(ApiCodeEnum.DEFAULT_SUCCESS.getMessage());
		return apiResult;
	}

	/**
	 * 自定义成功
	 *
	 * @param message
	 * @return
	 */
	public static ApiResult success(String message) {
		ApiResult apiResult = new ApiResult();
		apiResult.setCode(ApiCodeEnum.DEFAULT_SUCCESS.getCode());
		apiResult.setMessage(message);
		return apiResult;
	}

	/**
	 * 默认警告
	 *
	 * @return
	 */
	public static ApiResult warning() {
		ApiResult apiResult = new ApiResult();
		apiResult.setCode(ApiCodeEnum.DEFAULT_SUCCESS.getCode());
		apiResult.setMessage(ApiCodeEnum.DEFAULT_WARNING.getMessage());
		return apiResult;
	}

	/**
	 * 自定义警告
	 *
	 * @param message
	 * @return
	 */
	public static ApiResult warning(String message) {
		ApiResult apiResult = new ApiResult();
		apiResult.setCode(ApiCodeEnum.DEFAULT_WARNING.getCode());
		apiResult.setMessage(message);
		return apiResult;
	}

	/**
	 * 默认失败
	 *
	 * @return
	 */
	public static ApiResult fail() {
		ApiResult apiResult = new ApiResult();
		apiResult.setCode(ApiCodeEnum.DEFAULT_ERROR.getCode());
		apiResult.setMessage(ApiCodeEnum.DEFAULT_ERROR.getMessage());
		return apiResult;
	}

	/**
	 * 自定义失败
	 *
	 * @param message
	 * @return
	 */
	public static ApiResult fail(String message) {
		ApiResult apiResult = new ApiResult();
		apiResult.setCode(ApiCodeEnum.DEFAULT_ERROR.getCode());
		apiResult.setMessage(message);
		return apiResult;
	}
}