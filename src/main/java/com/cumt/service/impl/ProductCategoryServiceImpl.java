package com.cumt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cumt.dao.ProductCategoryDao;
import com.cumt.entity.ProductCategory;
import com.cumt.service.ProductCategoryService;

/***
 * 商品种类Service实现类
 * 
 * @author draymonder
 *
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Override
	public List<ProductCategory> getProductCategoryList(long shopId) {
		return productCategoryDao.queryProductCategoryByShopId(shopId);
	}
}
