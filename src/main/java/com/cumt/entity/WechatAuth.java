package com.cumt.entity;

import java.util.Date;

/***
 * 微信用户实体类
 * 
 * @author draymonder
 *
 */
public class WechatAuth {
	// id
	private Long wechatAuthId;
	// open_id
	private String openId;
	// 创建时间
	private Date createTime;
	// 个人信息
	private PersonInfo personInfo;

	public WechatAuth() {}
	/***
	 * 
	 * @param wechatAuthId
	 * @param openId
	 * @param createTime
	 * @param personInfo
	 */
	public WechatAuth(Long wechatAuthId, String openId, Date createTime, PersonInfo personInfo) {
		super();
		this.wechatAuthId = wechatAuthId;
		this.openId = openId;
		this.createTime = createTime;
		this.personInfo = personInfo;
	}

	public Long getWechatAuthId() {
		return wechatAuthId;
	}

	public void setWechatAuthId(Long wechatAuthId) {
		this.wechatAuthId = wechatAuthId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public PersonInfo getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}
	@Override
	public String toString() {
		return "WechatAuth [wechatAuthId=" + wechatAuthId + ", openId=" + openId + ", createTime=" + createTime
				+ ", personInfo=" + personInfo + ", getWechatAuthId()=" + getWechatAuthId() + ", getOpenId()="
				+ getOpenId() + ", getCreateTime()=" + getCreateTime() + ", getPersonInfo()=" + getPersonInfo()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
}
