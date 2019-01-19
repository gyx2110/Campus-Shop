package com.cumt.exceptions;

/***
 * Area异常
 * 
 * @author draymonder
 *
 */
public class AreaOperationException extends RuntimeException {
	private static final long serialVersionUID = -1512771573934050550L;

	public AreaOperationException(String msg) {
		super(msg);
	}
}
