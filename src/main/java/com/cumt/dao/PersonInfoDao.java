package com.cumt.dao;

import com.cumt.entity.PersonInfo;

/***
 * 用户信息接口
 * @author draymonder
 *
 */
public interface PersonInfoDao {
	/***
	 * 通过用户Id查询用户
	 * @param userId
	 * @return
	 */
	PersonInfo queryPersonInfoById(long userId);
	/***
	 * 插入用户信息
	 * @param personInfo
	 * @return
	 */
	int insertPersonInfo(PersonInfo personInfo);
}
