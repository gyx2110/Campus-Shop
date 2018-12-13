package com.cumt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cumt.entity.Product;

/***
 * 商品数据接口
 * @author draymonder
 *
 */
public interface ProductDao {
	/***
	 * 输入条件：商品名（模糊），商品状态，店铺Id，商品类别
	 */
	
	/***
	 * 根据查询条件获取相应条件的商品 并且根据分页条件返回相应结果
	 * @param productCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<Product> selectProductList(@Param("productCondition")Product productCondition,
			@Param("rowIndex")int rowIndex, @Param("pageSize")int pageSize);
	
	
	/***
	 * 根据查询条件获取相应条件的商品总数
	 * @param productCondition
	 * @return
	 */
	int selectProductCount(@Param("productCondition")Product productCondition);
	
	/***
	 * 根据相应的productId返回相应的商品
	 * @param productId
	 * @return
	 */
	Product selectProductById(long productId);
	
	/***
	 * 添加商品
	 * @param product
	 * @return
	 */
	int insertProduct(Product product);
	
	/***
	 * 删除商品类别时将商品记录类别置空
	 * @param productCategoryId
	 * @return
	 */
	int updateProductCategoryToNull(long productCategoryId);
}
