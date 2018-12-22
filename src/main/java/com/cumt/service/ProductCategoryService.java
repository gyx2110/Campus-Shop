package com.cumt.service;

import java.util.List;

import com.cumt.dto.ProductCategoryExecution;
import com.cumt.entity.ProductCategory;
import com.cumt.exceptions.ProductCategoryOperationException;

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

	/***
	 * 批量添加商品类别
	 * @param list
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution batchInsertProductCategory(List<ProductCategory> list)
			throws ProductCategoryOperationException;
	/***
	 * 将对应商店(shopId)下的商品类别删除(productCategoryId)
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryOperationException;
}
