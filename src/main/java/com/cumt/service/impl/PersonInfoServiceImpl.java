package com.cumt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cumt.dao.PersonInfoDao;
import com.cumt.entity.PersonInfo;
import com.cumt.service.PersonInfoService;

/***
 * 个人信息服务实现类
 * @author draymonder
 *
 */
@Service
public class PersonInfoServiceImpl implements PersonInfoService{

	@Autowired
	private PersonInfoDao personInfoDao;
	@Override
	public PersonInfo getPersonInfoById(long userId) {
		return personInfoDao.queryPersonInfoById(userId);
	}

}
