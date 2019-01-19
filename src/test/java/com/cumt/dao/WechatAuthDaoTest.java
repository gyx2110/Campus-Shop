package com.cumt.dao;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cumt.entity.PersonInfo;
import com.cumt.entity.WechatAuth;

public class WechatAuthDaoTest extends BaseTest {
	@Autowired
	private WechatAuthDao wechatAuthDao;
	
	@Test
	public void testQuery() {
		String openId = "111";
		WechatAuth w = wechatAuthDao.queryWechatInfoByOpenId(openId);
		System.out.println(w);
	}
	@Test
	public void testAdd() {
		PersonInfo p = new PersonInfo();
		p.setUserId(1L);
		WechatAuth w = new WechatAuth(null, "555", new Date(), p);
		System.out.println(wechatAuthDao.insertWechatAuth(w));
	}
}
