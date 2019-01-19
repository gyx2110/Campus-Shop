package com.cumt.service.impl;

import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cumt.dao.PersonInfoDao;
import com.cumt.dao.WechatAuthDao;
import com.cumt.dto.WechatAuthExecution;
import com.cumt.entity.PersonInfo;
import com.cumt.entity.WechatAuth;
import com.cumt.enums.EnableStatusEnum;
import com.cumt.enums.WechatAuthStateEnum;
import com.cumt.exceptions.WechatAuthOperationException;
import com.cumt.service.WechatAuthService;

import ch.qos.logback.classic.Logger;

/***
 * 微信用户测试类
 * 
 * @author draymonder
 *
 */
@Service
public class WechatAuthServiceImpl implements WechatAuthService {
	private static Logger log = (Logger) LoggerFactory.getLogger(WechatAuthServiceImpl.class);
	@Autowired
	private WechatAuthDao wechatAuthDao;

	@Autowired
	private PersonInfoDao personInfoDao;

	@Override
	public WechatAuth getWechatAuthByOpenId(String openId) {
		return wechatAuthDao.queryWechatInfoByOpenId(openId);
	}

	@Override
	@Transactional
	public WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException {
		// 空值判断
		if(wechatAuth == null || wechatAuth.getOpenId() == null) {
			return new WechatAuthExecution(WechatAuthStateEnum.NULL_AUTH_INFO);
		}
		try {
			wechatAuth.setCreateTime(new Date());
			// 如果微信帐号里夹带着用户信息并且用户Id为空，则认为该用户第一次使用平台(且通过微信登录)
			// 用PersonInfoDao 创建用户
			if(wechatAuth.getPersonInfo() != null && wechatAuth.getPersonInfo().getUserId() == null) {
				try {
					PersonInfo newp = wechatAuth.getPersonInfo();
					// 设置为可用
					newp.setEnableStatus(EnableStatusEnum.AVAILABLE.getState());
					newp.setCreateTime(new Date());
					int effectedNum = personInfoDao.insertPersonInfo(newp);
					if(effectedNum <= 0) {
						throw new WechatAuthOperationException(WechatAuthStateEnum.REGISTER_PERSONINFO_ERROR.getStateInfo());
					}
					wechatAuth.setPersonInfo(newp);
				} catch (Exception e) {
					log.error("插入用户信息失败: " +e.toString());
					throw new WechatAuthOperationException("插入用户信息失败: " +e.toString());
				}
			}
			// 创建本平台的wechatAuth记录
			int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
			if(effectedNum <= 0) {
				throw new WechatAuthOperationException(WechatAuthStateEnum.REGISTER_WACHAT_ERROR.getStateInfo());
			} else {
				return new WechatAuthExecution(WechatAuthStateEnum.SUCCESS, wechatAuth);
			}
		} catch(Exception e) {
			log.error("插入wechat用户信息失败: " +e.toString());
			throw new WechatAuthOperationException("插入wechat用户信息失败: " +e.toString());
		}
	}

}
