package com.miapsoft.common.exception;
/**
 * 
 * <p>Title: BussinessException.java</p>
 * <p>Description: 业务异常类</p>
 * @author: 李杰
 * @time: 2017-3-29
 */
public class BussinessException extends RuntimeException{

	private static final long serialVersionUID = 3977788115434211532L;
	
	private String message;//异常错误信息
	
	public BussinessException(String message, Throwable cause) {
		super(cause);
		this.message = message;
	}
	public BussinessException(String message) {
	    super(message);
	    this.message = message;
	}
	public String getMyMessage(){
		return message;
	}
}
