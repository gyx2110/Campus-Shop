package com.cumt.dao;

import com.cumt.entity.WechatAuth;

/***
 * 微信用户接口
 * 
 * @author draymonder
 *
 */
public interface WechatAuthDao {
	/***
	 * 通过openId查询对应本平台的微信帐号
	 * @param openId
	 * @return
	 */
	WechatAuth queryWechatInfoByOpenId(String openId);
	/***
	 * 添加对应本平台的微信帐号
	 * @param wechatAuth
	 * @return
	 */
	int insertWechatAuth(WechatAuth wechatAuth);
}
