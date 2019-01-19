package com.cumt.service;

import com.cumt.entity.PersonInfo;

/***
 * 个人信息服务接口
 * @author draymonder
 *
 */
public interface PersonInfoService {
	/***
	 * 根据用户Id获取personInfo信息
	 * @param id
	 * @return
	 */
	public PersonInfo getPersonInfoById(long userId);
}
