package com.cumt.dao;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cumt.entity.PersonInfo;

public class PersonInfoDaoTest extends BaseTest{
	@Autowired 
	private PersonInfoDao personInfoDao;
	
	@Ignore
	@Test
	public void testQuery() {
		long id = 1L;
		PersonInfo p = personInfoDao.queryPersonInfoById(id);
		System.out.println(p);
	}
	
	@Ignore
	@Test
	public void testAddUser() {
		PersonInfo p = new PersonInfo(null, "大饼", "111", "draymonders@gmail.com", 
				"1", 1, 2, new Date(), new Date());
		int num = personInfoDao.insertPersonInfo(p);
		System.out.println(num);
	}
}
