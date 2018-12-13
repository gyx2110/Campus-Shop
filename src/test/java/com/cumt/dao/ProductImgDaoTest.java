package com.cumt.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cumt.entity.ProductImg;

public class ProductImgDaoTest extends BaseTest {
	@Autowired
	private ProductImgDao productImgDao;
	
	@Test
	public void testBatchInsertProductImg() {
		ProductImg productImg1 = new ProductImg(null, "\\upload\\item\\shop2\\2018120522153315025.jpg", "test", 0, new Date(), 1L);//
		List<ProductImg> list = new ArrayList<>();
		list.add(productImg1);
		int t = productImgDao.batchInsertProductImg(list);
		System.out.println(t);
	}
	
}
