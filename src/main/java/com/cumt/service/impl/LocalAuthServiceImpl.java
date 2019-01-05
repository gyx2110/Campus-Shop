package com.cumt.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cumt.dao.LocalAuthDao;
import com.cumt.dto.LocalAuthExecution;
import com.cumt.entity.LocalAuth;
import com.cumt.enums.LocalAuthStateEnum;
import com.cumt.exceptions.LocalAuthOperationException;
import com.cumt.service.LocalAuthService;
import com.cumt.util.MD5Util;

/***
 * 本地用户 业务实现类
 * @author draymonder
 *
 */
@Service
public class LocalAuthServiceImpl implements LocalAuthService {

	@Autowired
	private LocalAuthDao localAuthDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException {
		// 控制判断
		if (localAuth == null || localAuth.getUsername() == null || localAuth.getPassword() == null
				|| localAuth.getPersonInfo() == null || localAuth.getPersonInfo().getUserId() == null)
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);

		// 获取local绑定的id对应的信息
		LocalAuth tempAuth = localAuthDao.queryLocalByUserId(localAuth.getPersonInfo().getUserId());
		if (tempAuth == null) {
			try {
				// 创建平台账号并绑定
				localAuth.setCreateTime(new Date());
				localAuth.setLastEditTime(new Date());

				// 密码用md5处理
				localAuth.setPassword(MD5Util.getMd5(localAuth.getPassword()));
				
				System.out.println("localAuth: " + localAuth);
				// 插入localAuth !!!
				int nums = localAuthDao.insertLocalAuth(localAuth);
				if (nums <= 0) {
					throw new LocalAuthOperationException("插入失败");
				} else {
					return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS, localAuth);
				}
			} catch (Exception e) {
				throw new LocalAuthOperationException("insertLocalAuth error: " + e.getMessage());
			}
		} else {
			// 之前绑定过
			return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS, tempAuth);
		}
	}

	@Override
	public LocalAuthExecution updateLocalAuth(Long userId, String username, String password, String newPassword)
			throws LocalAuthOperationException {
		if (userId != null && username != null && password != null && newPassword != null
				&& !password.equals(newPassword)) {
			try {
				int nums = localAuthDao.updateLocalAuth(userId, username, password, newPassword, new Date());
				if (nums <= 0) {
					throw new LocalAuthOperationException("update localAuth error\n");
				}
				return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
			} catch (Exception e) {
				throw new LocalAuthOperationException("update localAuth error: " + e.getMessage());
			}
		} else {
			return new LocalAuthExecution(LocalAuthStateEnum.UPDATE_FAIL);
		}
	}

	@Override
	public LocalAuth getLocalAuthByUsernameAndPwd(String username, String password) {
		return localAuthDao.queryLocalByUsernameAndPwd(username, password);
	}

	@Override
	public LocalAuth getLocalAuthById(Long userId) {
		return localAuthDao.queryLocalByUserId(userId);
	}

}
