package com.cumt.enums;
/***
 * 可用状态枚举
 * @author draymonder
 *
 */
public enum EnableStatusEnum {
	UNAVAILABLE(0, "不可用"), AVAILABLE(1, "可用"), CHECK(2, "审核中");
	private int state;
	private String stateInfo;

	private EnableStatusEnum(int state, String stateInfo) {
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
	public static EnableStatusEnum stateOf(int state) {
		for (EnableStatusEnum stateEnum : values()) {
			if (stateEnum.getState() == state) {
				return stateEnum;
			}
		}
		return null;
	}
}
