package com.cumt.enums;

/***
 * @Description: 枚举店铺类别信息
 * @author draymonder
 *
 */
public enum ShopCategoryStateEnum {
	EDIT_ERROR(-2001, "店铺编辑失败"),
	NULL_SHOP_CATEGORY(-2002, "shopCategory信息为空"),
	NULL_SHOP_CATEGORY_ID(-2003, "shopCategoryId为空");
	
	private int state;
	private String stateInfo;
	
	private ShopCategoryStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}


	public String getStateInfo() {
		return stateInfo;
	}
	
	/***
	 * @Description: 返回状态
	 * @param state
	 * @return
	 */
	public static ShopCategoryStateEnum stateOf(int state) {
		for(ShopCategoryStateEnum shopCategoryStateEnum : values()) {
			if(shopCategoryStateEnum.getState() == state) {
				return shopCategoryStateEnum;
			}
		}
		return null;
	}
}
