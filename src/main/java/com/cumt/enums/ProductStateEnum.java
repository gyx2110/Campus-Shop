package com.cumt.enums;

/***
 * 商品状态枚举
 * 
 * @author draymonder
 *
 */
public enum ProductStateEnum {
	PRODUCT_EMPTY(-2001, "请输入商品信息"), EDIT_ERROR(-2002, "商品编辑失败"), EMPTY(-2003, "商品为空"), PRODUCT_ID_EMPTY(-2004,
			"商品ID为空");
	private int state;
	private String stateInfo;

	private ProductStateEnum(int state, String stateInfo) {
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
	 * 返回相应的状态信息
	 * @param state
	 * @return
	 */
	public static ProductStateEnum stateOf(int state) {
		for (ProductStateEnum stateEnum : values()) {
			if (stateEnum.getState() == state) {
				return stateEnum;
			}
		}
		return null;
	}
}
