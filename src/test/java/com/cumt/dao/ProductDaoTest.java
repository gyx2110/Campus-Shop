package com.cumt.dao;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cumt.entity.Product;
import com.cumt.entity.ProductCategory;
import com.cumt.entity.ProductImg;
import com.cumt.entity.Shop;

public class ProductDaoTest extends BaseTest {
	@Autowired
	private ProductDao productDao;

	// @Ignore
	@Test
	public void testInsertProduct() {
		ProductCategory pc = new ProductCategory();
		pc.setProductCategoryId(1L);
		Shop sp = new Shop();
		sp.setShopId(1L);
		ProductImg pi = new ProductImg();
		pi.setProductImgId(1L);
		// Product pro = new Product(productId, productName, productDesc, imgAddr,
		// normalPrice, promotionPrice, priority, createTime, lastEditTime,
		// enableStatus, productImgList, productCategory, shop)
		Product pro1 = new Product(null, "充气娃娃", "舒服的体验", "\\upload\\item\\shop2\\2018120522153315025.jpg", "100", "99",
				2, new Date(), new Date(), 1, null, pc, sp);
		int num = productDao.insertProduct(pro1);
		System.out.println(num);
	}
}
