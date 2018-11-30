package com.cumt.dao;

import com.cumt.entity.Shop;

public interface ShopDao {
	
	/**
	 * 新增店铺
	 * @param shop 
	 * @return 
	 */
	int insertShop(com.cumt.entity.Shop shop);
	
	/**
	 * 更新店铺
	 * @param shop 
	 * @return 
	 */
	int updateShop(Shop shop);
}
