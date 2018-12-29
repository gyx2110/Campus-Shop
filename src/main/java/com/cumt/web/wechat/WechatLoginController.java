package com.cumt.web.wechat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cumt.dto.UserAccessToken;
import com.cumt.dto.WechatAuthExecution;
import com.cumt.dto.WechatUser;
import com.cumt.entity.PersonInfo;
import com.cumt.entity.WechatAuth;
import com.cumt.enums.WechatAuthStateEnum;
import com.cumt.service.PersonInfoService;
import com.cumt.service.WechatAuthService;
import com.cumt.util.wechat.WechatUtil;

/***
 * 获取关注公众号之后的微信用户信息的接口，如果在微信浏览器里访问
 * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx11ef2f7bc10f7270
 * &redirect_uri=http://cumto2o.ml/ssm/wechatlogin/logincheck
 * &role_type=1&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect
 * 则这里将会获取到code,之后再可以通过code获取到access_token 进而获取到用户信息
 * 
 * @author draymonder
 *
 */
@Controller
@RequestMapping("/wechatlogin")
public class WechatLoginController {
	private static Logger log = LoggerFactory.getLogger(WechatLoginController.class);
	private static final String FRONTEND = "1";
	private static final String SHOPEND = "2";

	@Autowired
	private PersonInfoService personInfoService;

	@Autowired
	private WechatAuthService wechatAuthService;

	@RequestMapping(value = "/logincheck", method = RequestMethod.GET)
	public String doGet(HttpServletRequest req, HttpServletResponse resp) {
		log.debug("weixin login get...");
		String code = req.getParameter("code");
		log.debug("weixin login code:" + code);
		// 这个state用来判断用户登录的后台界面
		String roleType = req.getParameter("state");
		
		WechatUser user = null;
		String openId = null;
		WechatAuth auth= null;
		if (null != code) {
			UserAccessToken token = null;
			try {
				// 通过code获取access_token
				token = WechatUtil.getUserAccessToken(code);
				log.debug("weixin login token: " + token.toString());
				// 通过token获取accessToken
				String accessToken = token.getAccessToken();
				// 根据token获取openId
				openId = token.getOpenId();
				user = WechatUtil.getUserInfo(accessToken, openId);
				log.debug("weixin login user:" + user.toString());
				req.getSession().setAttribute("openId", openId);
				auth = wechatAuthService.getWechatAuthByOpenId(openId);
			} catch (Exception e) {
				log.error("error in getUserAccessToken or getUserInfo or findByOpenId: " + e.toString());
			}
		}
		/***
		 * 获取OpenId 看数据库有没有账号 没有相应账号的话，就自动创建上，实现微信与网站的无缝对接
		 * 
		 */
		if(auth == null) {
			// user是从json解析的数据信息  然后将user转换为personInfo
			PersonInfo personInfo = WechatUtil.getPersonInfoFromRequest(user);
			auth = new WechatAuth();
			auth.setOpenId(openId);
			if(FRONTEND.equals(roleType)) {
				personInfo.setUserType(1);
			} else if(SHOPEND.equals(roleType)) {
				personInfo.setUserType(2);
			}
			auth.setPersonInfo(personInfo);
			WechatAuthExecution we = wechatAuthService.register(auth);
			if(we.getState() != WechatAuthStateEnum.SUCCESS.getState()) {
				return null;
			} else {
				personInfo = personInfoService.getPersonInfoById(auth.getPersonInfo().getUserId());
				req.getSession().setAttribute("user", personInfo);
			}
		}
		// 跳转链接
		if (FRONTEND.equals(roleType)) {
			return "front/mainpage";
		} else {
			return "shop/shoplist";
		}
	}

}
// https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx11ef2f7bc10f7270&redirect_uri=http://icumt.ml/ssm/wechatlogin/logincheck&role_type=1&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect
