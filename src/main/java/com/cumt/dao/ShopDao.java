package com.cumt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cumt.entity.Shop;

/***
 * 商店接口
 * 
 * @author draymonder
 *
 */
public interface ShopDao {

	/**
	 * 新增店铺
	 * 
	 * @param shop
	 * @return 1 插入成功 -1 插入失败
	 */
	int insertShop(Shop shop);

	/**
	 * 更新店铺
	 * 
	 * @param shop
	 * @return 1 插入成功 -1 插入失败
	 */
	int updateShop(Shop shop);

	/**
	 * @param 检索店铺
	 */
	Shop queryShopById(long shopId);

	/**
	 * 根据店铺条件 检索数量
	 * 
	 * @param shopCondition
	 * @return
	 */
	int queryShopCount(@Param("shopCondition") Shop shopCondition);

	/**
	 * 根据店铺条件检索出相应的ShopList
	 * 
	 * @param shopCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("pageIndex") int rowIndex,
			@Param("pageSize") int pageSize);
}
