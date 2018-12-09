package com.cumt.util;

/***
 * 分页计算工具类
 * 
 * @author draymonder
 *
 */
public class PageCalculator {
	/***
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return
	 */
	public static int calculateRowIndex(int pageIndex, int pageSize) {
		return pageIndex > 0 ? (pageIndex - 1) * pageSize : 0;
	}
}
