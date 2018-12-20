package com.cumt.dao;

import java.util.ArrayList;
import java.util.Date;
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
	@Ignore
	@Test
	public void testBatchInsertProductCategory() {
		List<ProductCategory> list = new ArrayList<>();
		ProductCategory p = null;
		for (int i = 1; i <= 5; i++) {
			p = new ProductCategory(null,(long)i,"test"+i+10,i,new Date());
			// p.setCreateTime(new Date());
			// p.setPriority(i);
			// p.setShopId((long)(i));
			// p.setProductCategoryName("测试店铺种类"+(i+3));
			list.add(p);
		}
		for (ProductCategory ps : list) {
			System.out.println(ps);
			System.out.println(ps.getProductCategoryName());
		}
		int effectedNum = productCategoryDao.batchInsertProductCategory(list);
		System.out.println(effectedNum);
	}
	
	@Test
	public void testDelete() {
		long shopId = 4;
		long productCategoryId = 17;
		int num = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
		System.out.println(num);
	}
}
