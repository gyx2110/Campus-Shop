package com.cumt.service;

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
import com.cumt.dto.ShopExecution;
import com.cumt.entity.Area;
import com.cumt.entity.PersonInfo;
import com.cumt.entity.Shop;
import com.cumt.entity.ShopCategory;

import exceptions.ShopOperationException;

public class ShopServiceTest extends BaseTest{
	@Autowired
	private ShopService shopService; 
	
	@Ignore
	@Test
	public void testAddShop() throws IOException {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		ShopCategory shopCategory = new ShopCategory();
		Area area = new Area();
		owner.setUserId(1L);
		area.setAreaId(1);
		shopCategory.setShopCategoryId(1L);
		shop.setOwner(owner);
		shop.setShopCategory(shopCategory);
		shop.setArea(area);
		shop.setShopAddr("testService");
		shop.setShopName("test店铺Service");
		shop.setShopDesc("testService");
		shop.setShopImg("testService");
		shop.setPhone("testService");
		shop.setPriority(1);
		shop.setCreateTime(new Date());
		shop.setAdvice("审核中");
		String filePath = "D:\\image\\header.jpg";
		ShopExecution se = shopService.addShop(shop, path2MultipartFile(filePath));
		System.out.println("ShopExecution.state" + se.getState());
		System.out.println("ShopExecution.stateInfo" + se.getStateInfo());
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
	
	@Ignore
	@Test
	public void testGetShopById() {
		Shop shop = shopService.getShopById(1L);
		System.out.println(shop.getShopName() + " " + shop.getArea().getAreaName() + " "+ shop.getShopCategory().getShopCategoryName());
	}
	
	
	@Ignore
	@Test
	public void testUpdateShop() throws ShopOperationException, IOException {
		Shop shop = new Shop();
		shop.setShopId(2L);
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1L);
		shop.setOwner(owner);
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(1L);
		shop.setShopCategory(sc);
		String str = "d:\\image\\php.jpg";
		//shop.setShopImg(path2MultipartFile(str));;
		ShopExecution se = shopService.updateShop(shop, path2MultipartFile(str));
		System.out.println(se.getStateInfo());
	}
	
	@Test
	public void testGetShopList() {
		Shop shopCondition = new Shop();
		ShopCategory sp = new ShopCategory();
		sp.setShopCategoryId(1L);
		shopCondition.setShopCategory(sp);
		ShopExecution se = shopService.getShopList(shopCondition, 2, 2);
		System.out.println(se.getStateInfo() +" " + se.getCount());
	}
}
