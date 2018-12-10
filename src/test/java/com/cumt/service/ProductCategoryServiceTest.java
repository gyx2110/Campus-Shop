package com.cumt.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cumt.dao.BaseTest;
import com.cumt.dto.ProductCategoryExecution;
import com.cumt.entity.ProductCategory;

public class ProductCategoryServiceTest extends BaseTest {
	@Autowired
	private ProductCategoryService productCategoryService;

	@Ignore
	@Test
	public void testGetProductCategoryList() {
		List<ProductCategory> list = productCategoryService.getProductCategoryList(1L);
		System.out.println(list.size());
	}
	
	@Ignore
	@Test
	public void testBatchInsertProductCategory() {
		List<ProductCategory> list = new ArrayList<>();
		ProductCategory p = new ProductCategory(null, (long)(12+2)/2, "测试商品种类"+(12+2)/2, 12*2, new Date());
		list.add(p);
		ProductCategoryExecution productCategoryExecution = productCategoryService.batchInsertProductCategory(list);
		System.out.println(productCategoryExecution.getProductCategoryList().size());
	}
	
	@Ignore
	@Test
	public void testDeleteProductCategory() {
		long shopId = 2L;
		long productCategoryId = 20L;
		ProductCategoryExecution productCategoryExecution = productCategoryService.deleteProductCategory(productCategoryId, shopId);
		System.out.println(productCategoryExecution.getEffectedNum());
	}
}
