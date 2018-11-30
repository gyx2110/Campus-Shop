package com.cumt.entity;

import java.util.Date;

public class PersonInfo {
	// ID
	private Long userId;
	// 姓名
	private String name;
	// 图像地址
	private String profileImg;
	// 邮箱
	private String email;
	// 性别
	private String gender;
	// 启用状态
	private Integer enableStutus;
	// 1.顾客 2.店家 3.超级管理员
	// 店家可以是顾客 超级管理员可以是顾客或者店家
	private Integer userType;
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getEnableStutus() {
		return enableStutus;
	}
	public void setEnableStutus(Integer enableStutus) {
		this.enableStutus = enableStutus;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	// 创建时间
	private Date createTime;
	// 修改时间
	private Date lastEditTime;
}
