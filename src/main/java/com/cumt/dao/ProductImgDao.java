package com.cumt.dao;

import java.util.List;

import com.cumt.entity.ProductImg;

public interface ProductImgDao {
	/***
	 * 批量添加商品详情图片
	 * @param list
	 * @return
	 */
	int batchInsertProductImg(List<ProductImg> list);
}
