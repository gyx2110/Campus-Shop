package com.cumt.dao;

import com.cumt.entity.Area;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/***
 * 区域Dao层 测试
 * 
 * @author draymonder
 *
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class AreaDaoTest extends BaseTest{
	@Autowired
	private AreaDao areaDao;


	@Test
	public void testQueryArea() {
		List<Area> areaList = areaDao.queryArea();
		assertEquals(5, areaList.size());
	}
}
