package com.cumt.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cumt.dao.BaseTest;
import com.cumt.entity.ProductCategory;

public class ProductCategoryServiceTest extends BaseTest {
	@Autowired
	private ProductCategoryService productCategoryService;

	@Test
	public void testGetProductCategoryList() {
		List<ProductCategory> list = productCategoryService.getProductCategoryList(1L);
		System.out.println(list.size());
	}
}
