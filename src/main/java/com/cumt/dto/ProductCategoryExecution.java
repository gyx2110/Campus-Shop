package com.cumt.dto;

import java.util.List;

import com.cumt.entity.ProductCategory;
import com.cumt.enums.ProductCategoryStateEnum;

/***
 * 商品类别返回结果信息
 * 
 * @author draymonder
 *
 */
public class ProductCategoryExecution {
	// 结果状态
	private int state;
	// 状态标识
	private String stateInfo;

	// 增删改用
	private ProductCategory productCategory;
	private List<ProductCategory> productCategoryList;

	public ProductCategoryExecution() {
	}

	// 商品类别操作失败使用的构造器
	public ProductCategoryExecution(ProductCategoryStateEnum productCategoryStateEnum) {
		this.state = productCategoryStateEnum.getState();
		this.stateInfo = productCategoryStateEnum.getStateInfo();
	}

	// 商品类别操作成功使用的构造器，单个ProductCategory
	public ProductCategoryExecution(ProductCategoryStateEnum productCategoryStateEnum,
			ProductCategory productCategory) {
		this.state = productCategoryStateEnum.getState();
		this.stateInfo = productCategoryStateEnum.getStateInfo();
		this.productCategory = productCategory;
	}

	// 店铺类别操作成功的时候使用的构造器 返回ProductCategory
	public ProductCategoryExecution(ProductCategoryStateEnum productCategoryStateEnum, List<ProductCategory> list) {
		this.state = productCategoryStateEnum.getState();
		this.stateInfo = productCategoryStateEnum.getStateInfo();
		this.productCategoryList = list;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public List<ProductCategory> getProductCategoryList() {
		return productCategoryList;
	}

	public void setProductCategoryList(List<ProductCategory> productCategoryList) {
		this.productCategoryList = productCategoryList;
	}

}
