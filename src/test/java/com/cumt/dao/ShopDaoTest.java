package com.cumt.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cumt.entity.Area;
import com.cumt.entity.PersonInfo;
import com.cumt.entity.Shop;
import com.cumt.entity.ShopCategory;

public class ShopDaoTest extends BaseTest {
	@Autowired
	private ShopDao shopDao;

	// 表示junit不会碰这个方法
	@Ignore
	@Test
	public void testInsertShop() {
		Shop shop = new Shop();
		// 设置店主信息
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1L);

		// 设置所属区域
		Area area = new Area();
		area.setAreaId(1);

		// 设置店铺种类
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(1L);

		shop.setAdvice("需要更好的装修");
		shop.setArea(area);
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		shop.setLastEditTime(new Date());
		shop.setOwner(owner);
		shop.setPhone("13115201926");
		shop.setPriority(10);
		shop.setShopAddr("江苏省徐州市铜山区");
		shop.setShopCategory(shopCategory);
		shop.setShopDesc("这是一家咖啡厅，欢迎大家品尝新鲜的Coffee");
		shop.setShopImg("待添加");
		shop.setShopName("咖啡之翼");

		int effectedNum = shopDao.insertShop(shop);
		assertEquals(effectedNum, 1);
	}

	@Ignore
	@Test
	public void testUpdateShop() {
		Shop shop = new Shop();
		// 设置店铺ID
		shop.setShopId(2L);
		// 设置店主信息
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1L);

		// 设置所属区域
		Area area = new Area();
		area.setAreaId(3);

		// 设置店铺种类
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(1L);

		shop.setAdvice("已经开业");
		shop.setArea(area);
		shop.setEnableStatus(1);
		shop.setLastEditTime(new Date());
		shop.setOwner(owner);
		shop.setPhone("15930909602");
		shop.setPriority(10);
		shop.setShopAddr("江苏省徐州市铜山区");
		shop.setShopCategory(shopCategory);
		shop.setShopDesc("这是一家游戏厅，欢迎大家来玩!IG牛逼");
		shop.setShopName("畅玩之家");

		int effectedNum = shopDao.updateShop(shop);
		assertEquals(effectedNum, 1);
	}
	
	@Ignore
	@Test
	public void testQueryShopById() {
		long shopId = 1;
		Shop shop = shopDao.queryShopById(1);
		System.out.println(shop.getArea().getAreaName());
		System.out.println(shop.getShopCategory().getShopCategoryName());
		System.out.println(shop.getShopName());
		
	}
}
