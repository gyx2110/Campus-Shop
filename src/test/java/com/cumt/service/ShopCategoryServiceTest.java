package com.cumt.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.cumt.dao.BaseTest;
import com.cumt.entity.ShopCategory;

public class ShopCategoryServiceTest extends BaseTest {
	@Autowired
	private ShopCategoryService shopCategoryService;

	@Ignore
	@Test
	public void testGetShopCategoryList() {
		if (shopCategoryService == null) {
			System.out.println("shopCategoryService is null");
		}

		List<ShopCategory> list = shopCategoryService.getShopCategoryList(null, 0, 10);
		for (ShopCategory sp : list) {
			System.out.println(sp.getShopCategoryId());
		}
		System.out.println(list.size());
	}

	// addShopCategory(ShopCategory shopCategory, MultipartFile shopCategoryImg)
	@Ignore
	@Test
	public void testAddShopCategory() throws Exception {
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryName("手机");
		shopCategory.setShopCategoryDesc("手机是信息社会每个人的必需品");
		shopCategory.setPriority(10);
		String imgPath = "d:\\image\\";
		System.out
				.println(shopCategoryService.addShopCategory(shopCategory, path2MultipartFile(imgPath)).getStateInfo());
	}

	// modifyShopCategory(ShopCategory shopCategory, MultipartFile shopCategoryImg)
	@Ignore
	@Test
	public void testmodifyShopCategory() throws Exception {
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(6L);
		shopCategory.setShopCategoryName("手机");
		shopCategory.setShopCategoryDesc("手机是信息社会每个人的必需品");
		shopCategory.setPriority(10);
		String imgPath = "d:\\image\\1.jpg";
		System.out.println(
				shopCategoryService.modifyShopCategory(shopCategory, path2MultipartFile(imgPath)).getStateInfo());
	}

	// getShopCategoryById(long shopCategoryId)
	@Ignore
	@Test
	public void testGetShopCategoryById() {
		ShopCategory shopCategory = shopCategoryService.getShopCategoryById(3L);
		System.out.println(shopCategory.getShopCategoryName());
	}

	/**
	 * filePath to MultipartFile
	 * 
	 * @param filePath
	 * @throws IOException
	 */
	private MultipartFile path2MultipartFile(String filePath) throws IOException {
		File file = new File(filePath);
		FileInputStream input = new FileInputStream(file);
		MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain",
				IOUtils.toByteArray(input));
		return multipartFile;
	}
}
