package com.cumt.exceptions;

/***
 * ShopCategory异常
 * 
 * @author draymonder
 *
 */
public class ShopCategoryOperationException extends RuntimeException {
	private static final long serialVersionUID = 5423986306645291467L;

	public ShopCategoryOperationException(String msg) {
		super(msg);
	}
}
