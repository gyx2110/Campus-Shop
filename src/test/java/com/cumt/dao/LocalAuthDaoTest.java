package com.cumt.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.cumt.entity.LocalAuth;
import com.cumt.entity.PersonInfo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 按照顺序测试
public class LocalAuthDaoTest extends BaseTest {
	@Autowired
	private LocalAuthDao localAuthDao;
	public static final String username = "root";
	public static final String pwd = "admin";

	@Ignore
	@Test
	public void testAdd() {
		LocalAuth localAuth = new LocalAuth();
		localAuth.setCreateTime(new Date());
		localAuth.setPassword(pwd);
		localAuth.setUsername(username);
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(1L);
		localAuth.setPersonInfo(personInfo);
		// System.out.println(localAuthDao.insertLocalAuth(localAuth));
		int num = localAuthDao.insertLocalAuth(localAuth);
		assertEquals(num, 1);
	}

	@Test
	public void testBQuery1() {
		LocalAuth localAuth = localAuthDao.queryLocalByUsernameAndPwd(username, "ixiaobing");
		System.out.println(localAuth);
	}

	@Ignore
	@Test
	public void testBQuery2() {
		LocalAuth localAuth = localAuthDao.queryLocalByUserId(1L);
		System.out.println(localAuth);
	}

	@Ignore
	@Test
	public void testUpdate() {
		int num = localAuthDao.updateLocalAuth(1L, username, pwd, "ixiaobing", new Date());
		assertEquals(1, num);
	}
}
