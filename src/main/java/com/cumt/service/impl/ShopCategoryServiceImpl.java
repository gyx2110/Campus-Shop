package com.cumt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cumt.dao.ShopCategoryDao;
import com.cumt.entity.ShopCategory;
import com.cumt.service.ShopCategoryService;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService{
	@Autowired
	private ShopCategoryDao shopCategoryDao;

	@Override
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition, int rowIndex, int pageSize) {
		return shopCategoryDao.queryShopCategory(shopCategoryCondition, rowIndex, pageSize);
	}

	@Override
	public ShopCategory getShopCategoryById(long shopCategoryId) {
		return shopCategoryDao.selectShopCategoryById(shopCategoryId);
	}

	
}
