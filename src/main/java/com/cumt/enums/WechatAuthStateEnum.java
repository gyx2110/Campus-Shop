package com.cumt.enums;
/***
 * 微信状态枚举类
 * @author draymonder
 *
 */
public enum WechatAuthStateEnum {
	LOGINFAIL(-1, "openId输入有误"), SUCCESS(0, "操作成功"), 
	REGISTER_PERSONINFO_ERROR(-1005, "新建用户失败"),REGISTER_WACHAT_ERROR(-1007, "新建微信用户失败"),NULL_AUTH_INFO(-1006, "注册信息为空");

	private int state;

	private String stateInfo;

	private WechatAuthStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static WechatAuthStateEnum stateOf(int index) {
		for (WechatAuthStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
