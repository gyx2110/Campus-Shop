package com.cumt.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.cumt.entity.ProductImg;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductImgDaoTest extends BaseTest {
	@Autowired
	private ProductImgDao productImgDao;
	
	@Ignore
	@Test
	public void testBatchInsertProductImg() {
		ProductImg productImg1 = new ProductImg(null, "\\upload\\item\\shop2\\2018120522153315025.jpg", "test", 0, new Date(), 1L);//
		ProductImg productImg2 = new ProductImg(null, "\\upload\\item\\shop2\\2018120522153315025.jpg", "test", 0, new Date(), 1L);
		List<ProductImg> list = new ArrayList<>();
		list.add(productImg1);
		list.add(productImg2);
		int t = productImgDao.batchInsertProductImg(list);
		System.out.println(t);
	}
	@Ignore
	@Test
	public void testSelectProductImgListByProductId() {
		long productId = 2L;
		List<ProductImg> list = productImgDao.queryProductImgListByProductId(productId);
		System.out.println(list.size());
	}
	
}
