package com.cumt.dao;

import java.util.List;

import com.cumt.entity.ProductImg;

/***
 * 商品详情图片接口
 * 
 * @author draymonder
 *
 */
public interface ProductImgDao {
	/***
	 * 批量添加商品详情图片
	 * 
	 * @param list
	 * @return
	 */
	int batchInsertProductImg(List<ProductImg> list);

	/***
	 * 根据商品获取商品详情图列表
	 * 
	 * @param productId
	 * @return
	 */
	List<ProductImg> selectProductImgListByProductId(long productId);

	/***
	 * 根据商品Id删除商品详情图
	 * 
	 * @param productId
	 * @return
	 */
	int deleteProductImgByProductId(long productId);
}
