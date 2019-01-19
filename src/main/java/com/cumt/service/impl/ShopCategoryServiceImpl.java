package com.cumt.service.impl;

import java.io.IOException;
import java.util.ArrayList;
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
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

	private static Logger log = LoggerFactory.getLogger(ShopCategoryServiceImpl.class);

	@Override
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition, int rowIndex, int pageSize) {
		// return shopCategoryDao.queryShopCategory(shopCategoryCondition, rowIndex,
		// pageSize);
		String key = SHOPCATEGORY;
		List<ShopCategory> shopCategoryList = null;
		ObjectMapper mapper = new ObjectMapper();
		// 查询所有一级类别
		if (shopCategoryCondition == null) {
			key = key + "_allfirstlevel";
		} else if (shopCategoryCondition != null && shopCategoryCondition.getParent() != null
				&& shopCategoryCondition.getParent().getShopCategoryId() != null) {
			// 查询对应parentId下的shopCategory
			key = key + "_parent" + shopCategoryCondition.getParent().getShopCategoryId();
		} else if (shopCategoryCondition != null) {
			// 列出所有子类别
			key = key + "_allsecondlevel";
		}
		boolean exists = jedisKeys.exists(key);
		if (!exists) {
			shopCategoryList = shopCategoryDao.queryShopCategory(shopCategoryCondition, rowIndex, pageSize);
			String jsonString;
			try {
				jsonString = mapper.writeValueAsString(shopCategoryList);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				log.error(e.getMessage());
				throw new ShopCategoryOperationException(e.getMessage());
			}
			jedisStrings.set(key, jsonString);
		} else {
			String jsonString = jedisStrings.get(key);
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, ShopCategory.class);
			try {
				// 将相关key对应的value里的的string转换成对象的实体类集合
				shopCategoryList = mapper.readValue(jsonString, javaType);
			} catch (JsonParseException e) {
				e.printStackTrace();
				log.error(e.getMessage());
				throw new ShopCategoryOperationException(e.getMessage());
			} catch (JsonMappingException e) {
				e.printStackTrace();
				log.error(e.getMessage());
				throw new ShopCategoryOperationException(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
				log.error(e.getMessage());
				throw new ShopCategoryOperationException(e.getMessage());
			}
			System.out.println("redis获取 " + key + " success");
			log.debug("redis获取 " + key + " success");
		}
		return shopCategoryList;
	}

	@Override
	public ShopCategory getShopCategoryById(long shopCategoryId) {
		return shopCategoryDao.queryShopCategoryById(shopCategoryId);
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
	public ShopCategoryExecution updateShopCategory(ShopCategory shopCategory, MultipartFile shopCategoryImg) {
		if (shopCategory == null) {
			return new ShopCategoryExecution(ShopCategoryStateEnum.NULL_SHOP_CATEGORY);
		}
		if (shopCategory.getShopCategoryId() == null) {
			return new ShopCategoryExecution(ShopCategoryStateEnum.NULL_SHOP_CATEGORY_ID);
		}
		shopCategory.setLastEditTime(new Date());
		if (shopCategoryImg != null) {
			ShopCategory originShopCategory = shopCategoryDao.queryShopCategoryById(shopCategory.getShopCategoryId());
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
