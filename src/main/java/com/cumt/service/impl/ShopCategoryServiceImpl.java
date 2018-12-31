package com.cumt.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cumt.cache.JedisUtil;
import com.cumt.dao.ShopCategoryDao;
import com.cumt.dto.ShopCategoryExecution;
import com.cumt.entity.ShopCategory;
import com.cumt.enums.OperationStatusEnum;
import com.cumt.enums.ShopCategoryStateEnum;
import com.cumt.exceptions.ShopCategoryOperationException;
import com.cumt.service.ShopCategoryService;
import com.cumt.util.ImageUtil;
import com.cumt.util.PathUtil;

/***
 * 店铺种类Service实现类
 * 
 * @author draymonder
 *
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
	@Autowired
	private ShopCategoryDao shopCategoryDao;

	@Autowired
	private JedisUtil.Keys jedisKeys;

	@Autowired
	private JedisUtil.Strings jedisStrings;

	private static Logger logger = LoggerFactory.getLogger(ShopCategoryServiceImpl.class);

	@Override
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition, int rowIndex, int pageSize) {
		// return shopCategoryDao.queryShopCategory(shopCategoryCondition, rowIndex,
		// pageSize);
		
	}

	@Override
	public ShopCategory getShopCategoryById(long shopCategoryId) {
		return shopCategoryDao.selectShopCategoryById(shopCategoryId);
	}

	@Override
	@Transactional
	public ShopCategoryExecution addShopCategory(ShopCategory shopCategory, MultipartFile shopCategoryImg) {
		if (shopCategory == null) {
			return new ShopCategoryExecution(ShopCategoryStateEnum.NULL_SHOP_CATEGORY);
		}
		shopCategory.setCreateTime(new Date());
		shopCategory.setLastEditTime(new Date());
		if (shopCategoryImg != null) {
			addShopCategoryImg(shopCategory, shopCategoryImg);
		}
		try {
			int effectedNum = shopCategoryDao.insertShopCategory(shopCategory);
			if (effectedNum <= 0) {
				throw new ShopCategoryOperationException(ShopCategoryStateEnum.EDIT_ERROR.getStateInfo());
			}
		} catch (Exception e) {
			throw new ShopCategoryOperationException(ShopCategoryStateEnum.EDIT_ERROR.getStateInfo() + e.getMessage());
		}
		return new ShopCategoryExecution(OperationStatusEnum.SUCCESS, shopCategory);
	}

	@Override
	@Transactional
	public ShopCategoryExecution modifyShopCategory(ShopCategory shopCategory, MultipartFile shopCategoryImg) {
		if (shopCategory == null) {
			return new ShopCategoryExecution(ShopCategoryStateEnum.NULL_SHOP_CATEGORY);
		}
		if (shopCategory.getShopCategoryId() == null) {
			return new ShopCategoryExecution(ShopCategoryStateEnum.NULL_SHOP_CATEGORY_ID);
		}
		shopCategory.setLastEditTime(new Date());
		if (shopCategoryImg != null) {
			ShopCategory originShopCategory = shopCategoryDao.selectShopCategoryById(shopCategory.getShopCategoryId());
			if (originShopCategory.getShopCategoryImg() != null) {
				// 如果原来ShopCategory有图片 那就把之前的删除掉
				ImageUtil.deleteFileOrPath(originShopCategory.getShopCategoryImg());
			}
			addShopCategoryImg(shopCategory, shopCategoryImg);
		}
		try {
			int effectedNum = shopCategoryDao.updateShopCategory(shopCategory);
			if (effectedNum <= 0) {
				throw new ShopCategoryOperationException(ShopCategoryStateEnum.EDIT_ERROR.getStateInfo());
			}
		} catch (Exception e) {
			throw new ShopCategoryOperationException(ShopCategoryStateEnum.EDIT_ERROR.getStateInfo() + e.getMessage());
		}
		return new ShopCategoryExecution(OperationStatusEnum.SUCCESS, shopCategory);
	}

	/**
	 * 添加店铺类别图片
	 * 
	 * @param shopCategory
	 *            店铺类别实体类
	 * @param shopCategoryImg
	 *            店铺类别图片
	 */
	private void addShopCategoryImg(ShopCategory shopCategory, MultipartFile shopCategoryImg) {
		// 获取图片存储路径，将图片放在相应店铺类别的文件夹下
		String dest = PathUtil.getShopCategoryImagePath();
		String generateShopCategoryImg = ImageUtil.generateShopCategoryImg(shopCategoryImg, dest);
		shopCategory.setShopCategoryImg(generateShopCategoryImg);
	}

}
