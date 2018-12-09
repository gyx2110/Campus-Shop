package com.cumt.service;

import java.util.List;

import com.cumt.entity.ProductCategory;

/***
 * 商品种类Service
 * 
 * @author draymonder
 *
 */
public interface ProductCategoryService {
	/***
	 * 获取商品类别分页列表
	 * 
	 * @return
	 */
	List<ProductCategory> getProductCategoryList(long shopId);

}
