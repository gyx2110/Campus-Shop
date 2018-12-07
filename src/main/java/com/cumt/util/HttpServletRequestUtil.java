package com.cumt.util;

import javax.servlet.http.HttpServletRequest;
/***
 * HttpServlet请求获取参数工具类
 * @author draymonder
 *
 */
public class HttpServletRequestUtil {
	public static int getInt(HttpServletRequest req, String key) {
		try {
			return Integer.decode(req.getParameter(key));
		}
		catch(Exception e) {
			return -1;
		}
	}
	
	public static long getLong(HttpServletRequest req, String key) {
		try {
			return Long.valueOf(req.getParameter(key));
		}
		catch(Exception e) {
			return -1;
		}
	}
	
	public static Double getDouble(HttpServletRequest request, String key) {
		try {
			return Double.valueOf(request.getParameter(key));
		} catch (Exception e) {
			return -1d;
		}
	}

	public static Boolean getBoolean(HttpServletRequest request, String key) {
		try {
			return Boolean.valueOf(request.getParameter(key));
		} catch (Exception e) {
			return false;
		}
	}
	
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
