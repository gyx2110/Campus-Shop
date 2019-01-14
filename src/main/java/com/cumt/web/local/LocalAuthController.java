package com.cumt.web.local;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cumt.dto.LocalAuthExecution;
import com.cumt.entity.LocalAuth;
import com.cumt.entity.PersonInfo;
import com.cumt.enums.LocalAuthStateEnum;
import com.cumt.exceptions.LocalAuthOperationException;
import com.cumt.service.LocalAuthService;
import com.cumt.util.CodeUtil;
import com.cumt.util.HttpServletRequestUtil;

/***
 * 本地用户业务逻辑控制类
 * 
 * @author draymonder
 *
 */

@Controller
@RequestMapping(value = "/local", method = { RequestMethod.GET, RequestMethod.POST })
public class LocalAuthController {
	@Autowired
	private LocalAuthService localAuthService;

	/**
	 * 将用户信息与平台账号绑定
	 * 
	 * @param req
	 * @return
	 */
	@PostMapping("/bindlocalauth")
	@ResponseBody
	private Map<String, Object> bindLocalAuth(HttpServletRequest req) {
		Map<String, Object> modelMap = new HashMap<>();
		if (!CodeUtil.checkVerifyCode(req)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码有误!");
			return modelMap;
		}
		// 获取输入的账号
		String username = HttpServletRequestUtil.getString(req, "username");
		// 密码
		String password = HttpServletRequestUtil.getString(req, "password");
		PersonInfo user = (PersonInfo) req.getSession().getAttribute("user");
		
		System.out.println(username + " " + password);
		// System.out.println(user);
		if(user != null && user.getUserId() != null) {
			if (username != null && password != null ) {
				// 将存在的用户信息 绑定本地用户
				LocalAuth localAuth = new LocalAuth();
				
				localAuth.setUsername(username);
				localAuth.setPassword(password);
				localAuth.setPersonInfo(user);

				LocalAuthExecution localAuthExecution = localAuthService.bindLocalAuth(localAuth);
				if (localAuthExecution.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", localAuthExecution.getStateInfo());
				}

			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "用户名和密码不能为空!");
			}
		} else {
			// System.out.println("wechat not login");
			modelMap.put("success", false);
			modelMap.put("errMsg", "您还未用微信接入!");
		}
		
		return modelMap;
	}

	/**
	 * 更改用户密码
	 * 
	 * @param req
	 * @return
	 */
	@PostMapping("/changelocalpwd")
	@ResponseBody
	private Map<String, Object> changeLocalPwd(HttpServletRequest req) {
		Map<String, Object> modelMap = new HashMap<>();
		if (!CodeUtil.checkVerifyCode(req)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码有误!");
			return modelMap;
		}

		// 获取输入的账号
		String username = HttpServletRequestUtil.getString(req, "username");
		// 密码
		String password = HttpServletRequestUtil.getString(req, "password");
		// 新密码
		String newPassword = HttpServletRequestUtil.getString(req, "newPassword");
		// 获取当前用户信息
		PersonInfo user = (PersonInfo) req.getSession().getAttribute("user");
		if (username != null && password != null && newPassword != null && user != null && user.getUserId() != null
				&& !password.equals(newPassword)) {
			try {
				// 查看原先帐号，看看与输入的帐号是否一致，不一致则认为是非法操作
				LocalAuth localAuth = localAuthService.getLocalAuthById(user.getUserId());
				if (localAuth == null || !localAuth.getUsername().equals(username)) {
					// 不一致则直接退出
					modelMap.put("success", false);
					modelMap.put("errMsg", "输入的帐号非本次登录的帐号!");
					return modelMap;
				}

				// 修改密码
				LocalAuthExecution localAuthExecution = localAuthService.updateLocalAuth(user.getUserId(), username,
						password, newPassword);

				if (localAuthExecution.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
					// 修改成功
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", localAuthExecution.getStateInfo());
				}
			} catch (LocalAuthOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入密码!");
		}
		return modelMap;
	}

	/**
	 * 用户登录
	 * 
	 * @param req
	 * @return
	 */
	@PostMapping("/logincheck")
	@ResponseBody
	private Map<String, Object> loginCheck(HttpServletRequest req) {
		Map<String, Object> modelMap = new HashMap<>();
		// 湖片区是否需要进行验证码校验的标识符
		boolean needVerify = HttpServletRequestUtil.getBoolean(req, "needVerify");
		if (needVerify && !CodeUtil.checkVerifyCode(req)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码有误!");
			return modelMap;
		}

		// 获取输入的账号
		String username = HttpServletRequestUtil.getString(req, "username");
		// 密码
		String password = HttpServletRequestUtil.getString(req, "password");
		if (username != null && password != null) {
			// 判断是否有这个平台账号
			LocalAuth localAuth = localAuthService.getLocalAuthByUsernameAndPwd(username, password);
			if (localAuth != null) {
				// 返回登陆成功信息
				modelMap.put("success", true);
				// Session设置用户信息
				req.getSession().setAttribute("user", localAuth.getPersonInfo());
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "用户名或密码错误!");
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "用户名和密码均不能为空!");
		}
		return modelMap;
	}

	/***
	 * 当用户点击登出按钮的时候注销session
	 * 
	 * @param req
	 * @return
	 */
	@PostMapping("/logout")
	@ResponseBody
	private Map<String, Object> logOut(HttpServletRequest req) {
		Map<String, Object> modelMap = new HashMap<>();
		req.getSession().setAttribute("user", null);
		modelMap.put("success", true);
		return modelMap;
	}
}
