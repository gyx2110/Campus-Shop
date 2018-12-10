package com.cumt.util;

import javax.servlet.http.HttpServletRequest;

/***
 * HttpServlet请求获取参数工具类
 * 
 * @author draymonder
 *
 */
public class HttpServletRequestUtil {
	/***
	 * 
	 * @param req
	 * @param key
	 * @return
	 */
	public static int getInt(HttpServletRequest req, String key) {
		try {
			return Integer.decode(req.getParameter(key));
		} catch (Exception e) {
			return -1;
		}
	}
	/***
	 * 
	 * @param req
	 * @param key
	 * @return
	 */
	public static long getLong(HttpServletRequest req, String key) {
		try {
			return Long.valueOf(req.getParameter(key));
		} catch (Exception e) {
			return -1;
		}
	}
	/***
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static Double getDouble(HttpServletRequest request, String key) {
		try {
			return Double.valueOf(request.getParameter(key));
		} catch (Exception e) {
			return -1d;
		}
	}
	/***
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static Boolean getBoolean(HttpServletRequest request, String key) {
		try {
			return Boolean.valueOf(request.getParameter(key));
		} catch (Exception e) {
			return false;
		}
	}
	
	/***
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static String getString(HttpServletRequest request, String key) {
		try {
			String result = request.getParameter(key);
			if (result != null) {
				result = result.trim();
			}
			if ("".equals(result))
				result = null;
			return result;
		} catch (Exception e) {
			return null;
		}
	}
}
