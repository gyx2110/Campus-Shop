package com.cumt.service;
/***
 * 本地用户service
 * @author draymonder
 *
 */

import com.cumt.dto.LocalAuthExecution;
import com.cumt.entity.LocalAuth;
import com.cumt.exceptions.LocalAuthOperationException;

public interface LocalAuthService {
	/***
	 * 绑定微信，生成平台专属的账号
	 * @param localAuth
	 * @return
	 * @throws LocalAuthOperationException
	 */
	LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException;
	
	/***
	 * 修改平台账号的登陆密码
	 * @param userId
	 * @param username
	 * @param password
	 * @param newPassword
	 * @return
	 * @throws LocalAuthOperationException
	 */
	LocalAuthExecution updateLocalAuth(Long userId, String username, String password, String newPassword) throws LocalAuthOperationException;
	
	/***
	 * 通过账号和密码获取平台账号信息
	 * @param username
	 * @param password
	 * @return
	 */
	LocalAuth getLocalAuthByUsernameAndPwd(String username, String password);

	/***
	 * 通过userId获取平台账号信息
	 * @param userId
	 * @return
	 */
	LocalAuth getLocalAuthById(Long userId);
}
