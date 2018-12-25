package com.cumt.exceptions;
/***
 * Shop异常
 * @author draymonder
 *
 */
public class ShopOperationException extends RuntimeException{
	private static final long serialVersionUID = 8275517172794861551L;

	public ShopOperationException(String msg) {
		super(msg);
	}

}
