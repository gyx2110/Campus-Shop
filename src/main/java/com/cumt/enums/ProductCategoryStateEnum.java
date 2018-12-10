package com.cumt.enums;

/***
 * 枚举商品类别信息
 * 
 * @author draymonder
 *
 */
public enum ProductCategoryStateEnum {
	EDIT_ERROR(-2000, "商品类别编辑失败"), NULL_PRODUCT_CATEGORY(-2002, "productCategory信息为空"), NULL_PRODUCT_CATEGORY_ID(-2003,
			"productCategoryId为空"), EMPTY_LIST(-2010, "商品类别列表为空"), ADD_ERROR(-2222,
					"插入productCategory失败，可能无相应商店ShopId"), NULL_SHOP(-3333, "Session中无ShopId");
	private int state;
	private String stateInfo;

	private ProductCategoryStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	/**
	 * 返回相应的Enum状态
	 * 
	 * @param state
	 * @return
	 */
	public static ProductCategoryStateEnum stateof(int state) {
		for (ProductCategoryStateEnum productCategoryStateEnum : values()) {
			if (productCategoryStateEnum.getState() == state) {
				return productCategoryStateEnum;
			}
		}
		return null;
	}
}
