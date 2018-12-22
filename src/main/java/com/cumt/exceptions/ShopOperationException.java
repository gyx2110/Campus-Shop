package com.cumt.exceptions;

/***
 * 店铺操作异常
 * 
 * @author draymonder
 *
 */
public class ShopOperationException extends RuntimeException {
	// 序列化
	private static final long serialVersionUID = 1264172016786467069L;

	public ShopOperationException(String msg) {
		super(msg);
	}
}
