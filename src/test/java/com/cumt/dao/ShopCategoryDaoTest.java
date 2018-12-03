package com.cumt.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cumt.entity.ShopCategory;

/***
 * 店铺种类测试
 * @author draymonder
 *
 */

public class ShopCategoryDaoTest extends BaseTest{
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	@Ignore
	@Test
	public void testQueryShopCategory() {
		List<ShopCategory> list = shopCategoryDao.queryShopCategory(
				new ShopCategory(), 0, 1);
		assertEquals(1, list.size());
		ShopCategory mainCategory = new ShopCategory();
		mainCategory.setShopCategoryId(1L);
		
		ShopCategory testCategory = new ShopCategory();
		testCategory.setShopCategoryId(2L);
		testCategory.setParent(mainCategory);
		list = shopCategoryDao.queryShopCategory(testCategory, 0, 5);
		for(ShopCategory sp : list) {
			System.out.println(sp.getShopCategoryName());
		}
		System.out.println(list.size());
	}
	
	@Ignore
	@Test
	public void testInsertShopCategory() {
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryName("数码");
		shopCategory.setShopCategoryDesc("数码包括新型电子设备");
		shopCategory.setShopCategoryImg("");
		shopCategory.setPriority(10);
		shopCategory.setCreateTime(new Date());
		shopCategory.setLastEditTime(new Date());
		int ok = shopCategoryDao.insertShopCategory(shopCategory);
		assertEquals(1, ok);
	}
	
	@Ignore
	@Test
	public void testUpdateShopCategory() {
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(2L);
		shopCategory.setShopCategoryName("咖啡");
		shopCategory.setShopCategoryDesc("咖啡巨好喝的 欢迎品尝");
		shopCategory.setShopCategoryImg("");
		shopCategory.setPriority(1);
		// shopCategory.setCreateTime(new Date());
		shopCategory.setLastEditTime(new Date());
		int ok = shopCategoryDao.updateShopCategory(shopCategory);
		assertEquals(1, ok);
	}
	
	@Test
	public void testSelectShopCategoryById() {
		ShopCategory shopCategory = shopCategoryDao.selectShopCategoryById(1);
		
		System.out.println(shopCategory.getShopCategoryName());
		
	}
}
