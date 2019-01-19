package com.cumt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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

	/***
	 * 批量添加商品类别
	 * 
	 * @param productCategoryList
	 *            商品类别列表
	 * @return
	 */
	int batchInsertProductCategory(List<ProductCategory> productCategoryList);

	/***
	 * 删除商品类别
	 * 
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);
}
