package com.cumt.service;

import com.cumt.dto.WechatAuthExecution;
import com.cumt.entity.WechatAuth;
import com.cumt.exceptions.WechatAuthOperationException;

/***
 * 微信用户服务
 * @author draymonder
 *
 */
public interface WechatAuthService {
	/***
	 * 通过openId查找平台对应的微信帐号
	 * @param openId
	 * @return
	 */
	WechatAuth getWechatAuthByOpenId(String openId);

	/***
	 * 注册本平台的微信帐号
	 * @param wechatAuth
	 * @return
	 * @throws WechatAuthOperationException
	 */
	WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException;
	
}
