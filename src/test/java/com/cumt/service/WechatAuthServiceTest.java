package com.cumt.service;

import com.cumt.dto.WechatAuthExecution;
import com.cumt.entity.PersonInfo;
import com.cumt.entity.WechatAuth;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatAuthServiceTest{
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
