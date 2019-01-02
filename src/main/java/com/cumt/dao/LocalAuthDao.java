package com.cumt.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.cumt.entity.LocalAuth;

/***
 * 本地用户接口
 * 
 * @author draymonder
 *
 */
public interface LocalAuthDao {
	/***
	 * 账号密码查询相应用户
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	LocalAuth queryLocalByUsernameAndPwd(@Param("username") String username, @Param("password") String password);

	/***
	 * Id查询用户
	 * 
	 * @param userId
	 * @return
	 */
	LocalAuth queryLocalByUserId(@Param("userId") long userId);

	/***
	 * 添加本地用户
	 * 
	 * @param localAuth
	 * @return
	 */
	int insertLocalAuth(LocalAuth localAuth);

	/***
	 * 通过Id, username, pwd更改密码
	 * 
	 * @param userId
	 * @param username
	 * @param password
	 * @param newPassword
	 * @param lastEditTime
	 * @return
	 */
	int updateLocalAuth(@Param("userId") Long userId, @Param("username") String username,
			@Param("password") String password, @Param("newPassword") String newPassword,
			@Param("lastEditTime") Date lastEditTime);
}
