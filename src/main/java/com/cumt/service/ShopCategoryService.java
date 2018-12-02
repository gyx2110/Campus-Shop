package com.cumt.service;

import java.util.List;

import com.cumt.entity.ShopCategory;

public interface ShopCategoryService {
	/**
	 * 条件获取店铺类别分页列表
	 * 
	 * @param shopCategoryCondition 查询条件
	 * @param pageIndex             第几页
	 * @param pageSize              每页条数
	 * @return
	 */
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition,
			int rowIndex, int pageSize);
	
	/**
	 * 根据Id查询商品分类信息
	 * 
	 * @param shopCategory
	 * @return
	 */
	ShopCategory getShopCategoryById(long shopCategoryId);
}
