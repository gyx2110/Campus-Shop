package com.cumt.dao;

import java.util.Date;
import java.util.List;

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

	@Ignore
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

	@Ignore
	@Test
	public void testUpdateProduct() {
		ProductCategory pc = new ProductCategory();
		pc.setProductCategoryId(2L);
		Shop sp = new Shop();
		sp.setShopId(1L);
		ProductImg pi = new ProductImg();
		pi.setProductImgId(1L);
		Product pro1 = new Product(2L, "充气", "舒服的体验,很适合你", "", "199", "89", 0, new Date(), new Date(), 0, null, pc, sp);
		int num = productDao.updateProduct(pro1);
		System.out.println(num);
	}

	@Ignore
	@Test
	public void testSelectProductById() {
		long productId = 2L;
		Product product = productDao.selectProductById(productId);
		System.out.println(product.getProductName());
		List<ProductImg> list = product.getProductImgList();
		// System.out.println(product.getProductImgList().size());
		System.out.println(list.size());
		for (ProductImg i : list) {
			System.out.println(i.getImgAddr());
		}
	}
	
	@Ignore
	@Test
	public void testSelectProductList() {
		//List<Product> list = productDao.selectProductList(null, 0, 100);
		// System.out.println(list.size());
		Product pro = new Product();
		pro.setEnableStatus(1);
		int size = productDao.selectProductCount(pro);
		System.out.println(size);
	}
	
	@Ignore
	@Test
	public void testUpdateProductCategoryToNull() {
		Long productCategoryId = 1L;
		int size = productDao.updateProductCategoryToNull(productCategoryId);
		System.out.println(size);
	}
}
