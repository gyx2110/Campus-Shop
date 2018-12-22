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
 * 
 * @author draymonder
 *
 */

public class ShopCategoryDaoTest extends BaseTest {
	@Autowired
	private ShopCategoryDao shopCategoryDao;

	@Test
	public void testQueryShopCategory() {
		long parentId = 1L;
		ShopCategory shopCategoryCondition = new ShopCategory();
		ShopCategory parentShopCategory = new ShopCategory();
		parentShopCategory.setShopCategoryId(parentId);
		shopCategoryCondition.setParent(parentShopCategory);
		List<ShopCategory> list = shopCategoryDao.queryShopCategory(shopCategoryCondition, 0, 100);
		System.out.println(list.size());
		for(ShopCategory sp : list) {
			System.out.println(sp.getShopCategoryName());
		}
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

	@Ignore
	@Test
	public void testSelectShopCategoryById() {
		ShopCategory shopCategory = shopCategoryDao.selectShopCategoryById(1);
		System.out.println(shopCategory.getShopCategoryName());
	}
}
