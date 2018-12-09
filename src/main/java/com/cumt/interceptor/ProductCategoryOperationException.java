package com.cumt.interceptor;

/***
 * 商品种类操作异常
 * 
 * @author draymonder
 *
 */
public class ProductCategoryOperationException extends RuntimeException {
	private static final long serialVersionUID = 7463947966811904661L;

	public ProductCategoryOperationException(String msg) {
		super(msg);
	}
}
