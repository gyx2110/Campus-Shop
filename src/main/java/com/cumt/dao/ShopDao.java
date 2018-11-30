package com.cumt.dao;

import com.cumt.entity.Shop;

public interface ShopDao {
	
	/**
	 * 新增店铺
	 * @param shop 
	 * @return 1 插入成功 -1 插入失败
	 */
	int insertShop(Shop shop);
	
	/**
	 * 更新店铺
	 * @param shop 
	 * @return 
	 */
	int updateShop(Shop shop);
}
