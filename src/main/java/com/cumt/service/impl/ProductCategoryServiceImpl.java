package com.cumt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cumt.dao.ProductCategoryDao;
import com.cumt.dto.ProductCategoryExecution;
import com.cumt.entity.ProductCategory;
import com.cumt.enums.OperationStatusEnum;
import com.cumt.enums.ProductCategoryStateEnum;
import com.cumt.service.ProductCategoryService;

import exceptions.ProductCategoryOperationException;

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

	@Override
	@Transactional
	public ProductCategoryExecution batchInsertProductCategory(List<ProductCategory> list)
			throws ProductCategoryOperationException {
		if (list != null && !list.isEmpty()) {
			try {
				int effectedNum = productCategoryDao.batchInsertProductCategory(list);
				if (effectedNum <= 0) {
					throw new ProductCategoryOperationException(
							ProductCategoryStateEnum.ADD_ERROR.getStateInfo());
				} else {
					return new ProductCategoryExecution(OperationStatusEnum.SUCCESS, list);
				}
			} catch (Exception e) {
				throw new ProductCategoryOperationException(ProductCategoryStateEnum.ADD_ERROR.getStateInfo());
			}
		}else {
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}		
	}

	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryOperationException {
		try {
			int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if(effectedNum <= 0) {
				throw new ProductCategoryOperationException(ProductCategoryStateEnum.EDIT_ERROR.getStateInfo());
			}else {
				return new ProductCategoryExecution(OperationStatusEnum.SUCCESS, effectedNum);
			}
		}catch(Exception e) {
			throw new ProductCategoryOperationException(ProductCategoryStateEnum.EDIT_ERROR.getStateInfo() + "\n" + e.getMessage());
		}
	}
}
