package com.cumt.service.impl;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cumt.dao.ShopDao;
import com.cumt.dto.ShopExecution;
import com.cumt.entity.Area;
import com.cumt.entity.Shop;
import com.cumt.entity.ShopCategory;
import com.cumt.enums.OperationStatusEnum;
import com.cumt.enums.ShopStateEnum;
import com.cumt.service.ShopService;
import com.cumt.util.ImageUtil;
import com.cumt.util.PathUtil;

import exceptions.ShopOperationException;
/***
 * 店铺Service 实现类
 * @author draymonder
 *
 */
@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;
	
	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, MultipartFile shopImg) {
		// 空值判断
		if(shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		Area area = shop.getArea();
		if(area == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		ShopCategory shopcategory = shop.getShopCategory();
		if(shopcategory == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOPCATEGORY);
		}
		
		try {
			// 店铺初始值
			shop.setEnableStatus(0); 
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			// 添加店铺信息
			int effectedNum = shopDao.insertShop(shop);
			if(effectedNum <= 0) {
				throw new ShopOperationException("店铺创建失败");
			}else {
				if(shopImg != null) {
					try {
						addShopImg(shop, shopImg);
					}catch(Exception e) {
						throw new ShopOperationException("addShopImg error:"+e.getMessage());
					}
					effectedNum = shopDao.updateShop(shop);
					if(effectedNum <= 0) {
						throw new ShopOperationException("更新图片地址失败");
					}
				}
			}
		}catch(Exception e) {
			throw new ShopOperationException("addShop error:"+e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

	private void addShopImg(Shop shop, MultipartFile shopImg) {
		// 获取shop的图片目录的相对值路径
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.genarateThumbnail(shopImg, dest);
		shop.setShopImg(shopImgAddr);
	}

	@Override
	public Shop getShopById(long shopId) {
		return shopDao.queryShopById(shopId);
	}

	@Override
	@Transactional
	public ShopExecution updateShop(Shop shop, MultipartFile shopImg) throws ShopOperationException {
		if(shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		if(shop.getShopId() == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOPID);
		}
		try {
			if(shopImg != null) {
				Shop tmpShop = shopDao.queryShopById(shop.getShopId());
				if(tmpShop.getShopImg() != null && "".equals(tmpShop.getShopImg()) != true) {
					ImageUtil.deleteFileOrPath(tmpShop.getShopImg());
				}
				addShopImg(shop, shopImg);
			}
			shop.setLastEditTime(new Date());
			int effectedNum = shopDao.updateShop(shop);
			if(effectedNum > 0) {
				shop = shopDao.queryShopById(shop.getShopId());
				return new ShopExecution(ShopStateEnum.SUCCESS, shop);
			}else {
				return new ShopExecution(ShopStateEnum.INNER_ERROR);
			}
		}catch(Exception e) {
			throw new ShopOperationException(ShopStateEnum.EDIT_ERROR.getStateInfo() + " "+ e.getMessage());
		}
	}

}
