package com.cumt.exceptions;

/***
 * ProductCategory异常
 * 
 * @author draymonder
 *
 */
public class ProductCategoryOperationException extends RuntimeException{
	private static final long serialVersionUID = 8548352132365847253L;

	public ProductCategoryOperationException(String msg) {
		super(msg);
	}
}
