package com.cumt.interceptor;
/***
 * 商品操作异常
 * @author draymonder
 *
 */
public class ProductOperationException extends RuntimeException{
	private static final long serialVersionUID = -8271832167279545336L;

	public ProductOperationException(String msg) {
		super(msg);
	}
}
