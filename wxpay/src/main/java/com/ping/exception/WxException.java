package com.ping.exception;

/**
 * 自定义的异常类，用于处理系统预期异常
 * <p>Title: WxException</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月21日上午12:15:17
 * @version 1.0
 */
public class WxException extends Exception{

	//异常信息
	public String message;
	
	public WxException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
