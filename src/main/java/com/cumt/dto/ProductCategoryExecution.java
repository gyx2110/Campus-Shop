package com.cumt.dto;

import java.util.List;

import com.cumt.entity.ProductCategory;
import com.cumt.enums.OperationStatusEnum;
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
	
	private int effectedNum = 0;
	// 增删改用
	private ProductCategory productCategory;
	public int getEffectedNum() {
		return effectedNum;
	}

	public void setEffectedNum(int effectedNum) {
		this.effectedNum = effectedNum;
	}

	private List<ProductCategory> productCategoryList;

	public ProductCategoryExecution() {
	}
	
	/***
	 * 
	 * @param productCategoryStateEnum
	 */
	// 商品类别操作失败使用的构造器
	public ProductCategoryExecution(ProductCategoryStateEnum productCategoryStateEnum) {
		this.state = productCategoryStateEnum.getState();
		this.stateInfo = productCategoryStateEnum.getStateInfo();
	}
	/***
	 * 商品类别操作成功使用的构造器，返回单个ProductCategory
	 * @param productCategoryStateEnum
	 * @param productCategory
	 */
	// 
	public ProductCategoryExecution(ProductCategoryStateEnum productCategoryStateEnum,
			ProductCategory productCategory) {
		this.state = productCategoryStateEnum.getState();
		this.stateInfo = productCategoryStateEnum.getStateInfo();
		this.productCategory = productCategory;
	}
	/***
	 * 店铺类别操作成功的时候使用的构造器 返回ProductCategoryList
	 * @param stateEnum
	 * @param list
	 */
	public ProductCategoryExecution(OperationStatusEnum stateEnum, List<ProductCategory> list) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.productCategoryList = list;
	}

	/***
	 * 店铺类别操作成功的时候使用的构造器 
	 * @param stateEnum
	 * @param effectedNum
	 */
	public ProductCategoryExecution(OperationStatusEnum stateEnum, int effectedNum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.effectedNum = effectedNum;
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
