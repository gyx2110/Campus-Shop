package com.cumt.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cumt.dao.BaseTest;
import com.cumt.entity.HeadLine;

public class HeadLineServiceTest extends BaseTest{
	@Autowired
	private HeadLineService headLineService;
	
	@Test
	public void testGet() {
		HeadLine headLine = new HeadLine();
		headLine.setEnableStatus(0);
		List<HeadLine> list = headLineService.getHeadLineList(headLine);
		System.out.println(list.size());
	}
}
