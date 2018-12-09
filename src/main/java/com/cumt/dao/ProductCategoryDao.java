package com.cumt.dao;

import java.util.List;

import com.cumt.entity.ProductCategory;

/***
 * 商品分类接口
 * 
 * @author draymonder
 *
 */

public interface ProductCategoryDao {
	/***
	 * 根据相应shopId获取店铺下的商品类别
	 * 
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> queryProductCategoryByShopId(long shopId);

}
