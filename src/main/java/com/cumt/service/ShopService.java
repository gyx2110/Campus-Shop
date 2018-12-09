package com.cumt.service;

import org.springframework.web.multipart.MultipartFile;

import com.cumt.dto.ShopExecution;
import com.cumt.entity.Shop;

import exceptions.ShopOperationException;

/***
 * 商店Service
 * 
 * @author draymonder
 *
 */
public interface ShopService {

	/***
	 * 根据id查询店铺详情
	 * 
	 * @param shopId
	 * @return
	 */
	Shop getShopById(long shopId);

	/***
	 * 添加店铺
	 * 
	 * @param shop
	 * @param shopImg
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution addShop(Shop shop, MultipartFile shopImg) throws ShopOperationException;

	/***
	 * 更新店铺
	 * 
	 * @param shop
	 * @param shopImg
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution updateShop(Shop shop, MultipartFile shopImg) throws ShopOperationException;

	/***
	 * 获取店铺列表
	 * 
	 * @param shopCondition
	 *            查询条件
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            每页大小
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) throws ShopOperationException;
}
