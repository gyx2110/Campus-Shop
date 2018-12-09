package com.cumt.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cumt.entity.ProductCategory;

public class ProductCategoryDaoTest extends BaseTest {
	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Ignore
	@Test
	public void testQueryProductCategoryByShopId() {
		List<ProductCategory> list = productCategoryDao.queryProductCategoryByShopId(1L);
		for (ProductCategory pc : list) {
			System.out.println(pc.getProductCategoryName());
		}
		System.out.println(list.size());
	}
}
