package com.cumt.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.cumt.dao.BaseTest;
import com.cumt.dto.ProductExecution;
import com.cumt.entity.Product;
import com.cumt.entity.ProductCategory;
import com.cumt.entity.Shop;
import com.cumt.util.ImageUtil;

public class ProductServiceTest extends BaseTest {
	@Autowired
	private ProductService productService;
	
	@Test
	public void testAddProduct() {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(1L);
		Shop shop = new Shop();
		shop.setShopId(1L);
		Product product = new Product(null, "被子", "冬天喝水很暖和呢", null, "20", "18", 0, 
				null, null, null, null, productCategory, shop);
		String file0 = "D:\\image\\aaa.jpg";
		String file1 = "D:\\image\\1.jpg";
		String file2 = "D:\\image\\2.jpg";
		try {
			MultipartFile imgAddr = ImageUtil.path2MultipartFile(file0);
			MultipartFile img0 = ImageUtil.path2MultipartFile(file1);
			MultipartFile img1 = ImageUtil.path2MultipartFile(file2);
			List<MultipartFile> list = new ArrayList<>();
			list.add(img0);
			list.add(img1);
			ProductExecution pe = productService.addProduct(product, imgAddr, list);
			System.out.println(pe.getStateInfo());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
