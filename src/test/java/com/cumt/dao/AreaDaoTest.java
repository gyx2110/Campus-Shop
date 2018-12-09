package com.cumt.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.cumt.entity.Area;

/***
 * 区域Dao层 测试
 * 
 * @author draymonder
 *
 */

public class AreaDaoTest extends BaseTest {
	@Autowired
	private AreaDao areaDao;

	@Ignore
	@Test
	public void testQueryArea() {
		List<Area> areaList = areaDao.queryArea();
		assertEquals(5, areaList.size());
	}
}
