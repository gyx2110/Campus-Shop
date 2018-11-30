package com.cumt.dao;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.cumt.entity.Area;

public class AreaDaoTest extends BaseTest{
	@Autowired
	private AreaDao areaDao;
	
	@Test
	public void testQueryArea() {
		List<Area> areaList = areaDao.queryArea();
		assertEquals(5, areaList.size());
	}
}
