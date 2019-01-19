package com.cumt.exceptions;

/***
 * WechatAuth异常
 * 
 * @author draymonder
 *
 */
public class WechatAuthOperationException extends RuntimeException {
	private static final long serialVersionUID = -4290016045533442745L;

	public WechatAuthOperationException(String msg) {
		super(msg);
	}
}
