package com.cumt.util;

import javax.servlet.http.HttpServletRequest;

import com.google.code.kaptcha.Constants;
/***
 * 验证码校验类
 * @author draymonder
 *
 */
public class CodeUtil {
	/**
	 * 对比校验码
	 * 
	 * @param request
	 * @return
	 */
	public static boolean checkVerifyCode(HttpServletRequest req) {
		String nowCode = HttpServletRequestUtil.getString(req, "verifyCodeActual");
		String trueCode = (String)req.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(nowCode != null && trueCode.equalsIgnoreCase(nowCode)) {
			return true;
		}
		return false;
	}
}
