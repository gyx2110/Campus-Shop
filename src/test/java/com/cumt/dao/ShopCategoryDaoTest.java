package com.cumt.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cumt.entity.ShopCategory;

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
}
