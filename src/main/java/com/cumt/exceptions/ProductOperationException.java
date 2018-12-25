package com.cumt.exceptions;

/***
 * Product异常
 * 
 * @author draymonder
 *
 */
public class ProductOperationException extends RuntimeException {

	private static final long serialVersionUID = 5076172298827469013L;

	public ProductOperationException(String msg) {
		super(msg);
	}
}
