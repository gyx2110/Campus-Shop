package com.cumt.interceptor;

/***
 * 店铺种类操作异常
 * 
 * @author draymonder
 *
 */
public class ShopCategoryOperationException extends RuntimeException {

	// 序列化
	private static final long serialVersionUID = 8093088260622729536L;

	public ShopCategoryOperationException(String msg) {
		super(msg);
	}
}
