package com.cumt.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cumt.dao.BaseTest;
import com.cumt.dto.WechatAuthExecution;
import com.cumt.entity.PersonInfo;
import com.cumt.entity.WechatAuth;

public class WechatAuthServiceTest extends BaseTest{
	@Autowired 
	private WechatAuthService wechatAuthService;
	
	@Test
	public void testRegister() {
		PersonInfo p = new PersonInfo(null, "draymonder", null, null, null, null, null, null, null);
		p.setUserType(1);
		WechatAuth we = new WechatAuth(null, "xxxxxx", null, p);
		WechatAuthExecution cc = wechatAuthService.register(we);
		System.out.println(cc.getStateInfo());
	}
}
