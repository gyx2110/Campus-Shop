package com.cumt.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cumt.dto.ShopCategoryExecution;
import com.cumt.entity.ShopCategory;

/***
 * 店铺种类Service
 * 
 * @author draymonder
 *
 */
public interface ShopCategoryService {
	/**
	 * 条件获取店铺类别分页列表
	 * 
	 * @param shopCategoryCondition
	 *            查询条件
	 * @param pageIndex
	 *            第几页
	 * @param pageSize
	 *            每页条数
	 * @return
	 */
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition, int rowIndex, int pageSize);

	/***
	 * 增加店铺类别
	 * 
	 * @param shopCategory
	 * @param shopCategoryImg
	 * @return
	 */
	ShopCategoryExecution addShopCategory(ShopCategory shopCategory, MultipartFile shopCategoryImg);

	/***
	 * 修改店铺类别
	 * 
	 * @param shopCategory
	 * @param shopCategoryImg
	 * @return
	 */
	ShopCategoryExecution modifyShopCategory(ShopCategory shopCategory, MultipartFile shopCategoryImg);

	/**
	 * 根据shopCategory_Id查询店铺分类信息
	 * 
	 * @param shopCategory
	 * @return
	 */
	ShopCategory getShopCategoryById(long shopCategoryId);
}
